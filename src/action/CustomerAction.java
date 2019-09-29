package action;

import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import dao.CustomerDAO;
import vo.Customer;

public class CustomerAction extends ActionSupport implements SessionAware {

	private Map<String, Object> session;
	private Customer customer;
	private String sido;
	private String gugun;
	private String dong;
	private int cust_target_price;
	
	public String join(){
		CustomerDAO dao = new CustomerDAO();
		customer.setCust_address(sido+" "+gugun+" "+dong);
		customer.setCust_birthday(customer.getCust_birthday().substring(0, 10));
		dao.join(customer);
		return SUCCESS;
	}
	
	public String login(){
		CustomerDAO dao = new CustomerDAO();
		Customer c = dao.login(customer);
		if(c==null){
			return INPUT;
		}
		else{
			session.put("loginId", c.getCust_email());
			session.put("nickname", c.getCust_nickname());
			return SUCCESS;
		}
	}
	
	public String loadProfile(){
		CustomerDAO dao = new CustomerDAO();
		customer = dao.loadProfile((String) session.get("loginId"));
		return SUCCESS;
	}
	public String setBudget(){
		System.out.println("target price : " +cust_target_price);
		
		HashMap<String, Object> map = new HashMap<>();
		map.put("email", (String) session.get("loginId"));
		map.put("target_price", cust_target_price);
		CustomerDAO dao = new CustomerDAO();
		dao.setBudget(map);
		return SUCCESS;
	}
	
	public String logout(){
		session.clear();
		return SUCCESS;
	}
	
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getSido() {
		return sido;
	}

	public void setSido(String sido) {
		this.sido = sido;
	}

	public String getGugun() {
		return gugun;
	}

	public void setGugun(String gugun) {
		this.gugun = gugun;
	}

	public String getDong() {
		return dong;
	}

	public void setDong(String dong) {
		this.dong = dong;
	}
	
	public int getCust_target_price() {
		return cust_target_price;
	}

	public void setCust_target_price(int cust_target_price) {
		this.cust_target_price = cust_target_price;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
	
}
