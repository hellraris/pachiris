package dao;

import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import util.MybatisConfig;
import vo.Customer;

public class CustomerDAO {

	SqlSessionFactory factory = MybatisConfig.getSqlSessionFactory();
	SqlSession ss;

	public void join(Customer c) {

		try {
			System.out.println(c);
			ss = factory.openSession();
			ss.insert("customer.join", c);
			ss.commit();
			ss.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public Customer login(Customer c) {

		Customer customer = new Customer();

		try {
			ss = factory.openSession();
			customer = ss.selectOne("customer.login", c);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return customer;
	}

	
	public Customer loadProfile(String id){
		Customer customer = new Customer();
		try {
			ss = factory.openSession();
			customer = ss.selectOne("customer.loadProfile", id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ss.close();
		}
		return customer;
	}
	
	public void setBudget(HashMap<String, Object> map) {
		try {
			ss = factory.openSession();
			System.out.println("map?? "+ map);
			ss.update("customer.setBudget", map);
			ss.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ss.close();
		}
		
	}
}
