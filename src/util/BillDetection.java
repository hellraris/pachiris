package util;

import java.util.ArrayList;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import com.google.api.services.vision.v1.model.AnnotateImageResponse;
import com.google.api.services.vision.v1.model.BatchAnnotateImagesResponse;
import com.google.api.services.vision.v1.model.EntityAnnotation;

import util.GoogleDetection;
import vo.Bill;
import vo.Item;
import vo.LabelScore;

public class BillDetection {
	private BatchAnnotateImagesResponse googleResponse;
	private String googleDetection;
	private String[][] data;
	private String imgPath;
	private boolean textFlag;

	public BillDetection() {

	}

	public Bill assembleForm(String imgPath) {
		this.imgPath = imgPath;

		runGoogleVision();

		if (textFlag == true) {
			// text가 하나도 없을 경우
			return new Bill(null, null, null, null, "wrongPic");

		}

		ArrayList<LabelScore> labelScoreList = getLabel();
		ArrayList<String> filterWord = new ArrayList<>();
		filterWord.add("text");

		System.out.println("lsl" + labelScoreList);

		boolean con = false;

		for (LabelScore ls : labelScoreList) {
			for (String str : filterWord) {

				if (ls.getLabelName().equals(str)) {
					System.out.println(str);
					con = true;

				}

			}

		}

		if (con == false) {

			return new Bill(null, null, null, null, "wrongPic");

		}

		String storeName = findName();
		System.out.println("sn:" + storeName);
		String address = findAddress();
		System.out.println("add:" + address);
		String date = findDate();
		System.out.println("date:" + date);
		ArrayList<Item> itemList = findItem();
		System.out.println("itemlist:" + itemList);
		String payment = findPaymentMethod();
		System.out.println("pay:" + payment);
		Bill bill = new Bill(storeName, address, date, itemList, payment);
		System.out.println(bill);
		return bill;

	}

	public void runGoogleVision() {

		GoogleDetection google = new GoogleDetection(imgPath);

		googleResponse = google.detect();

		googleDetection = googleResponse.toString();

		System.out.println("googleDetection: " + googleDetection);

		// [i][0]:인덱스 [i][1]:x좌표 [i][2]:y좌표 [i][3]:내용

		String[] arr1 = googleDetection.split("boundingPoly");

		if (arr1.length == 1) {

			textFlag = true;
			return;

		}

		String[][] arr2 = new String[arr1.length - 2][4];

		boolean flag = false; // xy 스위치가 필요할 경우 true
		// flag = true;

		for (int i = 0; i < arr1.length - 2; i++) {

			String index = ((Integer) i).toString();
			arr2[i][0] = index;
			String s = arr1[i + 2];
			String coordinate = s.substring(s.indexOf("vertices") + 16, s.indexOf("}") + 1);
			String x = "";
			String y = "";

			if (flag) {
				y = coordinate.substring(0, coordinate.indexOf(","));
				x = coordinate.substring(coordinate.indexOf(":") + 1, coordinate.length() - 1);
			} else {
				x = coordinate.substring(0, coordinate.indexOf(","));
				y = coordinate.substring(coordinate.indexOf(":") + 1, coordinate.length() - 1);
			}
			arr2[i][1] = x;
			arr2[i][2] = y;
			int j = s.indexOf("description");
			int k = s.length();
			String desc = s.substring(j + 14, k - 5);
			arr2[i][3] = desc;

		}
		data = arr2;

	}

	// 날짜 + 시간
	public String findDate() {
		String result = "";
		String date = "";
		String time = "";
		int date_index = 0;
		for (int i = 0; i < data.length; i++) {

			if (Pattern.matches("^(?:\\d{2}|\\d{4})(?:-|/|\\.)(?:\\d{1}|\\d{2})(?:-|/|\\.)(?:\\d{1}|\\d{2})$",
					data[i][3])) {
				date = data[i][3];
				date_index = i;
				break;
			}
		}

		System.out.println();
		int y = Integer.parseInt(data[date_index][2]); // y축
		ArrayList<String> list = new ArrayList<>();

		for (int i = date_index; i < data.length - 1; i++) {
			String desc = data[i + 1][3];
			String temp_s = data[i + 1][2];
			int temp_i = Integer.parseInt(temp_s);
			if (temp_i > y - 30 && temp_i < y + 30) {

				list.add(data[i + 1][0]);
				if (Pattern.matches("\\d{2}\\:\\d{2}", desc) || Pattern.matches("\\d{2}\\:\\d{2}\\:\\d{2}", desc)) {
					time = desc;
					break;
				}
			}
		} // for

		// time값에 아무것도 안들어감 = 시간 데이터가 해체되어 추출되었다고 가정
		String temp = "";
		if (time.equals("")) {

			for (int i = 0; i < list.size() - 3; i++) {
				int temp_i = Integer.parseInt(list.get(i));
				String temp_s = data[temp_i][3];
				if (Pattern.matches("\\d{2}", temp_s)) {
					temp = temp_s;
					if (Pattern.matches("\\:", data[temp_i + 1][3])) {
						temp += data[temp_i + 1][3];
						if (Pattern.matches("\\d{2}", data[temp_i + 2][3])) {
							temp += data[temp_i + 2][3];
							if (Pattern.matches("\\:", data[temp_i + 3][3])) {
								temp += data[temp_i + 3][3];
								if (Pattern.matches("\\d{2}", data[temp_i + 4][3])) {
									temp += data[temp_i + 4][3];
									time = temp;
									System.out.println("초");
								}
							} else {
								time = temp;
								temp = "";
								break;
							}
						}
					}
				}
			}
		} // if
		temp = "";

		result = date + " " + time;
		return result;
	} // findDate

	// 주소
	// !!WARNING!! 상호명이 district 안에 들어있는 키워드로 시작하면 망합니다 !!WARNING!!
	public String findAddress() {
		String address = "";
		int start_index = 0;
		int y_value = 0;
		ArrayList<String> address_list = new ArrayList<>();
		String[] district = { "경기", "서울", "부산", "인천", "대구", "대전", "광주", "울산", "세종", "강원", "충청", "전라", "경상", "제주" };
		outer: for (int i = 0; i < data.length; i++) {
			if (data[i][3].length() > 1) {
				for (int j = 0; j < district.length; j++) {
					if (data[i][3].substring(0, 2).equals(district[j])) {
						start_index = i;
						y_value = Integer.parseInt(data[i][2]);
						break outer;
					} // inner if
				} // inner for
			} // outer if
		} // outer for
		System.out.println();

		for (int i = start_index; i < data.length; i++) {
			int temp_y = Integer.parseInt(data[i][2]);
			if (temp_y > y_value - 30 && temp_y < y_value + 30) {
				address_list.add(data[i][3]);
				if (data[i][3].matches(".*[0-9].*")) {
					break;
				}
			} else {
				break;
			}
		}
		System.out.println();

		for (int i = 0; i < address_list.size(); i++) {
			String temp = address_list.get(i);
			address += temp;
			switch (temp) {
			case "특별시":
				address = address.substring(0, address.length() - 4) + temp;
				break;
			case "로":
				address = address.substring(0, address.length() - 2) + temp;
				break;
			case "길":
				address = address.substring(0, address.length() - 2) + temp;
				break;

			default:
				break;
			}
			if (i < address_list.size() && !temp.matches(".*[0-9].*")) {
				address += " ";
			}
		}

		return address;
	}

	// 결제방식
	public String findPaymentMethod() {
		String payment = "현금";
		for (int i = 0; i < data.length; i++) {
			if (data[i][3].contains("카드")) {
				payment = "카드";
				break;
			}
		}
		return payment;
	}

	// 품목명, 단가, 총액
	public ArrayList<Item> findItem() {
		int total_index = 0; // "합계" 키워드가 들어간 데이터의 인덱스
		String total = ""; // 합계 금액

		// 영수증에 합계라는 키워드가 없으면 망함^ㅅ^ // 합계 금액을 먼저 찾는다
		outer: for (int i = 0; i < data.length; i++) {
			if (data[i][3].contains("합계") || data[i][3].contains("힙계")) {
				for (int j = i; j < data.length; j++) {
					if (data[j][3].matches(".*[0-9].*")) {
						total_index = i;
						total = data[j][3];
						if (total.length() < 4) {
							total += data[j + 1][3] + data[j + 2][3];
						}
						break outer;
					} // if
				} // for
			}
			// if
		} // for

	
		
		
		ArrayList<Integer> priceIndexList = new ArrayList<>();
		ArrayList<String> priceList = new ArrayList<>();
		for (int i = total_index; i >= 0; i--) {
			if (data[i][3].matches("\\d{2}") || data[i][3].matches("[0-9]")) {
				if (data[i + 1][3].matches("\\.") || data[i + 1][3].matches("\\,")) {
					priceIndexList.add(i);
					priceList.add(data[i][3] + data[i + 1][3] + data[i + 2][3]);
				}
			} else if (data[i][3].matches("\\d{2}[0]")) {
				if (!data[i - 1][3].matches("\\.") && !data[i - 1][3].matches("\\,")) {
					priceIndexList.add(i);
					priceList.add(data[i][3] + data[i + 1][3] + data[i + 2][3]);
				}
			} else if (data[i][3].matches("[0-9]*(?:\\,|\\.)\\d{2}[0]")) {
				priceIndexList.add(i);
				priceList.add(data[i][3]);
			}
		}
		
		
		ArrayList<String> nameList = new ArrayList<>();
		String temp_name = "";
		for (int i = 0; i < priceIndexList.size(); i++) {
			int temp_i = Integer.parseInt(data[priceIndexList.get(i)][2]);
			for (int j = 0; j < priceIndexList.get(i); j++) {
				int y = Integer.parseInt(data[j][2]);
				if (temp_i > y - 30 && temp_i < y + 30) {
					temp_name += data[j][3];
				} // if
			} // inner for
			if (!temp_name.equals("")) {
				nameList.add(temp_name);
			}
			temp_name = "";
		} // outer for
			// 아이템명에 부가세 등의 키워드가 포함된 항목을 제외시킨다
	
		ArrayList<Item> itemList = new ArrayList<>();

		String[] filter = { "부가", "계", "액", "판매", "TEL", "가세" };
		boolean priceflag = true;
		if (priceIndexList.size() == nameList.size()) {
			for (int i = 0; i < priceIndexList.size(); i++) {
				priceflag = true;
				for (int j = 0; j < filter.length; j++) {
					if (nameList.get(i).contains(filter[j])) {
						priceflag = false;
						break;
					} // if
				} // for
				
				
				if (priceflag) {
					String editPrice = "";
					System.out.println(priceList.get(i).toString());
					editPrice = StringUtils.replacePattern(priceList.get(i).toString(), "[.]", "");
					editPrice = StringUtils.replacePattern(priceList.get(i).toString(), "[,]", "");
					

					// 반환할 리스트에 아이템 추가
					itemList.add(new Item(nameList.get(i), Integer.parseInt(editPrice)));
					System.out.println("들어오나요?2");
				} // if
			} // for
		} // if
		;
		total = StringUtils.replacePattern(total.toString(), "[.]", "");
		total = StringUtils.replacePattern(total.toString(), "[,]", "");

		// itemList.add(new Item("total", Integer.parseInt(total)));

		return itemList;

	} // findItem

	// 상호
	// 품목이름, 가격

	// 상호명 찾기

	public String findName() {

		String name = "";

		StringBuilder sb = new StringBuilder();

		ArrayList<AnnotateImageResponse> result;
		result = (ArrayList<AnnotateImageResponse>) googleResponse.getResponses();

		for (AnnotateImageResponse r : result) {

			for (EntityAnnotation text : r.getTextAnnotations()) {
				sb.append(text.getDescription());
			}
		}

		String[] strArry = sb.toString().split("\n");

		for (String string : strArry) {

		}

		for (int i = 0; i < 15; i++) {

			if (-1 != strArry[i].indexOf("점") || -1 != strArry[i].indexOf("(주)") || -1 != strArry[i].indexOf("상호")
					|| -1 != strArry[i].indexOf("매장")) {

				name += strArry[i];
				name = nameFillter(name);
				return name;

			}

			if (strArry[i].matches(".*\\d{3}-\\d{2}-\\d{5}.*") || strArry[i + 1].matches("\\d{3}-\\d{2}-\\d{5}.*")) {

				name += strArry[i];
				name = nameFillter(name);
				return name;
			}

			if (strArry[i].matches(".*\\d{10}.*") || strArry[i + 1].matches("\\d{10}.*")) {

				name += strArry[i];
				name = nameFillter(name);
				return name;
			}

		}

		/////////////////////////// 단어///////////////////
		name = nameFillter(name);
		return name;
	}

	private ArrayList<LabelScore> getLabel() {

		StringBuilder sb = new StringBuilder();

		ArrayList<AnnotateImageResponse> result;

		ArrayList<LabelScore> labelScoreList = new ArrayList<>();

		result = (ArrayList<AnnotateImageResponse>) googleResponse.getResponses();

		for (AnnotateImageResponse r : result) {
			for (EntityAnnotation label : r.getLabelAnnotations()) {
				LabelScore ls = new LabelScore(label.getDescription(), Float.floatToIntBits(label.getScore()));
				labelScoreList.add(ls);
			}
		}

		System.out.println("라벨: \n" + sb.toString());

		return labelScoreList;
	}

	public String nameFillter(String name) {

		name = StringUtils.replacePattern(name, "\\d{3}-\\d{2}-\\d{5}.*", "");
		name = StringUtils.replacePattern(name, "\\d{10}.*", "");
		name = StringUtils.replacePattern(name, ".*[:]", "");
		name = StringUtils.replacePattern(name, ".*[]]", "");

		return name;
	}

}
