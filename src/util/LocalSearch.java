package util;

import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

import org.json.simple.*;

public class LocalSearch {
	public final static String BASE_URL = "https://apis.daum.net/local/v1/search/keyword.json?apikey=f84268902ba2b184f2b5b2f030f04cf4&query=";
	private ArrayList<String> category_list = new ArrayList<>();
	private String category;

	public LocalSearch(){}
	
	public String searchLocalCategory(String key){
		try {
			String keyword = URLEncoder.encode(key, "UTF-8");

			// 기본 셋팅
			URL url = new URL(BASE_URL + keyword);
			InputStreamReader isr = new InputStreamReader(url.openConnection().getInputStream(), "UTF-8");
			JSONObject object = (JSONObject) JSONValue.parse(isr);

			JSONArray arr = (JSONArray) ((JSONObject) object.get("channel")).get("item");

			for (int i = 0; i < arr.size(); i++) {
				JSONObject data = (JSONObject) arr.get(i);
				String s = (data.get("category").toString());
				System.out.println(s);
				category_list.add(s);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String word = category_list.get(0);
		if (word == "") {
			category = "";
			System.out.println("업체 검색 결과 없음");
		} else {
			int i = word.indexOf(">");
			String s1 = word.substring(0, (i - 1));
			if(s1.equals("음식점")){ //식당이므로 음식점 분류로 넘어간다.
				String s2 = word.substring(6);
				if(s2.contains(">")){
					int j = s2.indexOf(">");
					s2 = s2.substring(0, j-1);
				}
				category = s2;
				System.out.println("카테고리 판별 결과: " + category);
			}else{
				category = "";
				System.out.println("음식점 아님");
			}
		}
		return category;
	} // searchLocalCategory

}
