package vo;

import java.util.ArrayList;
import java.util.List;

public class Bill {

	int billNo;
	String customerId;
	String storeName;
	String address;
	String billDate;
	List<Item> itemList;
	String payment;
	int totalPrice;
	String img;

	public Bill() {
	}

	public Bill(String storeName, String address, String billDate, ArrayList<Item> itemList, String payment) {
		super();
		this.storeName = storeName;
		this.address = address;
		this.billDate = billDate;
		this.itemList = itemList;
		this.payment = payment;
	}

	public Bill(int billNo, String customerId, String storeName, String address, String billDate, ArrayList<Item> itemList,
			String payment, int totalPrice, String img) {
		super();
		this.billNo = billNo;
		this.customerId = customerId;
		this.storeName = storeName;
		this.address = address;
		this.billDate = billDate;
		this.itemList = itemList;
		this.payment = payment;
		this.totalPrice = totalPrice;
		this.img = img;
	}

	public int getBillNo() {
		return billNo;
	}

	public void setBillNo(int billNo) {
		this.billNo = billNo;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getBillDate() {
		return billDate;
	}

	public void setBillDate(String billDate) {
		this.billDate = billDate;
	}

	public List<Item> getItemList() {
		return itemList;
	}

	public void setItemList(List<Item> itemList) {
		this.itemList = itemList;
	}

	public String getPayment() {
		return payment;
	}

	public void setPayment(String payment) {
		this.payment = payment;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	@Override
	public String toString() {
		return "Bill [billNo=" + billNo + ", customerId=" + customerId + ", storeName=" + storeName + ", address="
				+ address + ", billDate=" + billDate + ", itemList=" + itemList + ", payment=" + payment + ", totalPrice="
				+ totalPrice + ", img=" + img + "]";
	}

}
