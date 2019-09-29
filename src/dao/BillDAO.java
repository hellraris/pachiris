package dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import util.MybatisConfig;
import vo.Bill;
import vo.Item;

public class BillDAO {

	SqlSessionFactory factory = MybatisConfig.getSqlSessionFactory();
	SqlSession ss;
	
	public String searchRestaurant(String keyword){
		String category = "";
		try {
			ss = factory.openSession();
			category = ss.selectOne("bill.searchRestaurant", keyword);
			if(category.equals("null")){
				int no = ss.selectOne("bill.countRestaurant");
				category = "L01M" + Integer.toString(no);
				Map<String, String> map = new HashMap<>();
				map.put("category_num", "L01");
				map.put("sub_category_num", category);
				map.put("sub_category_name", keyword);
				ss.insert("bill.insertRestaurant", map);
				ss.commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ss.close();
		}
		return category;
	}
	
	public String searchItem(String keyword){
		String category = "";
		try {
			ss = factory.openSession();
			category = ss.selectOne("bill.searchItem", keyword);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ss.close();
		}
		return category;
	}
	
	public int insertBill(Bill bill){
		System.out.println("insertBill method");
		int billNo = 0;
		try {
			ss = factory.openSession();
			ss.insert("bill.insertBill", bill);
			ss.commit();
			
			billNo = ss.selectOne("bill.searchBillNo");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ss.close();
		}
		return billNo;
	}
	
	public void insertItem(List<Item> items){
		System.out.println("insertItem method");
		List<Item> list = items;
		try {
			ss = factory.openSession();
			for (int i = 0; i < list.size(); i++) {
				ss.insert("bill.insertItem", list.get(i));
				ss.commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ss.close();
		}
	}


}
