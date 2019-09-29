package vo;

public class Item {

	int itemNo;
	int billNo;
	String customerId;
	String name;
	String category;
	int unitPrice;
	int price;

	public Item() {
		// TODO Auto-generated constructor stub
	}

	public Item(String name, int price) {
		super();
		this.name = name;
		this.price = price;
	}
	

	public Item(String name, int unitPrice, int price) {
		super();
		this.name = name;
		this.unitPrice = unitPrice;
		this.price = price;
	}

	public Item(int itemNo, int billNo, String customerId, String name, String category, int price) {
		super();
		this.itemNo = itemNo;
		this.billNo = billNo;
		this.customerId = customerId;
		this.name = name;
		this.category = category;
		this.price = price;
	}

	public int getItemNo() {
		return itemNo;
	}

	public void setItemNo(int itemNo) {
		this.itemNo = itemNo;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(int unitPrice) {
		this.unitPrice = unitPrice;
	}

	@Override
	public String toString() {
		return "Item [itemNo=" + itemNo + ", billNo=" + billNo + ", customerId=" + customerId + ", name=" + name
				+ ", category=" + category + ", unitPrice=" + unitPrice + ", price=" + price + "]";
	}

}
