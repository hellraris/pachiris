package vo;

import java.util.List;

public class MainPage {
	private String mostVisitStoreName;
	private String mostVisitStoreNumber;
	private String mostSpendDayDate;
	private String mostSpendDayCount;
	private String mostSpendDayMoney;
	private String mostSpendPrice;
	private String mostSpendStore;
	private List<Bill> latestBills;

	public MainPage() {
		// TODO Auto-generated constructor stub
	}

	
	//getters & setters
	public String getMostVisitStoreName() {
		return mostVisitStoreName;
	}

	public void setMostVisitStoreName(String mostVisitStoreName) {
		this.mostVisitStoreName = mostVisitStoreName;
	}

	public String getMostVisitStoreNumber() {
		return mostVisitStoreNumber;
	}

	public void setMostVisitStoreNumber(String mostVisitStoreNumber) {
		this.mostVisitStoreNumber = mostVisitStoreNumber;
	}
	
	public String getMostSpendDayDate() {
		return mostSpendDayDate;
	}

	public void setMostSpendDayDate(String mostSpendDayDate) {
		this.mostSpendDayDate = mostSpendDayDate;
	}

	public String getMostSpendDayCount() {
		return mostSpendDayCount;
	}

	public void setMostSpendDayCount(String mostSpendDayCount) {
		this.mostSpendDayCount = mostSpendDayCount;
	}

	public String getMostSpendDayMoney() {
		return mostSpendDayMoney;
	}

	public void setMostSpendDayMoney(String mostSpendDayMoney) {
		this.mostSpendDayMoney = mostSpendDayMoney;
	}


	public String getMostSpendPrice() {
		return mostSpendPrice;
	}

	public void setMostSpendPrice(String mostSpendPrice) {
		this.mostSpendPrice = mostSpendPrice;
	}

	public String getMostSpendStore() {
		return mostSpendStore;
	}

	public void setMostSpendStore(String mostSpendStore) {
		this.mostSpendStore = mostSpendStore;
	}

	public List<Bill> getLatestBills() {
		return latestBills;
	}

	public void setLatestBills(List<Bill> list) {
		this.latestBills = list;
	}
	
	
}
