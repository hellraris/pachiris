package util;

import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

import org.json.simple.*;

public class ShoppingSearch {
	public final static String BASE_URL = "https://apis.daum.net/shopping/search?apikey=f84268902ba2b184f2b5b2f030f04cf4&q=";
	private ArrayList<String> category_list = new ArrayList<>();
	private String categoryKeyword;

	public ShoppingSearch(){}
	
	public String searchShoppingCategory(String key){
		
		try {
			String keyword = URLEncoder.encode(key, "UTF-8");

			// 기본 셋팅
			URL url = new URL(BASE_URL + keyword + "&result=1&output=json");
			InputStreamReader isr = new InputStreamReader(url.openConnection().getInputStream(), "UTF-8");
			JSONObject object = (JSONObject) JSONValue.parse(isr);

			JSONArray arr = (JSONArray) ((JSONObject) object.get("channel")).get("item");

			for (int i = 0; i < arr.size(); i++) {
				JSONObject data = (JSONObject) arr.get(i);
				String s = (data.get("category_name").toString());
				category_list.add(s);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String word = category_list.get(0);
		if (word == "") {
			System.out.println("검색 결과 없음");
			categoryKeyword = "null";
		} else {
			System.out.println("카테고리 검색 결과: " + word);
			String[] array = word.split("&gt;");
			categoryKeyword = array[array.length -2];
			System.out.println("DB 검색용 카테고리 키워드: " + categoryKeyword);
		}
		return categoryKeyword;
	}

}
