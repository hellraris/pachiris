package vo;

/*
 * 예산 금액, 사용액, 잔액, 사용금액, 
 * 전체 일 평균 지출액, 이번달 일평균 지출액, 
 * 남은 금액 퍼센티지 데이터 처리를 위한 VO
 */
public class ProgressData {

	private int CUST_TARGET_PRICE;
	private int spendMonth;
	private int leftMonth;
	private int canMonth;
	private int averMonth;
	private String precentMonth;
	private String mostSpendItem;
	private int mostSpendItemPrice;
	private String mostSpendDay;
	private int mostSpendDayPrice;

	public ProgressData() {
	}

	public ProgressData(int cUST_TARGET_PRICE, int spendMonth, int leftMonth, int canMonth, int averMonth,
			String precentMonth, String mostSpendItem, int mostSpendItemPrice, String mostSpendDay,
			int mostSpendDayPrice) {
		super();
		CUST_TARGET_PRICE = cUST_TARGET_PRICE;
		this.spendMonth = spendMonth;
		this.leftMonth = leftMonth;
		this.canMonth = canMonth;
		this.averMonth = averMonth;
		this.precentMonth = precentMonth;
		this.mostSpendItem = mostSpendItem;
		this.mostSpendItemPrice = mostSpendItemPrice;
		this.mostSpendDay = mostSpendDay;
		this.mostSpendDayPrice = mostSpendDayPrice;
	}// constructor

	public int getCUST_TARGET_PRICE() {
		return CUST_TARGET_PRICE;
	}

	public void setCUST_TARGET_PRICE(int cUST_TARGET_PRICE) {
		CUST_TARGET_PRICE = cUST_TARGET_PRICE;
	}

	public int getSpendMonth() {
		return spendMonth;
	}

	public void setSpendMonth(int spendMonth) {
		this.spendMonth = spendMonth;
	}

	public int getLeftMonth() {
		return leftMonth;
	}

	public void setLeftMonth(int leftMonth) {
		this.leftMonth = leftMonth;
	}

	public int getCanMonth() {
		return canMonth;
	}

	public void setCanMonth(int canMonth) {
		this.canMonth = canMonth;
	}

	public int getAverMonth() {
		return averMonth;
	}

	public void setAverMonth(int averMonth) {
		this.averMonth = averMonth;
	}

	public String getPrecentMonth() {
		return precentMonth;
	}

	public void setPrecentMonth(String precentMonth) {
		this.precentMonth = precentMonth;
	}

	public String getMostSpendItem() {
		return mostSpendItem;
	}

	public void setMostSpendItem(String mostSpendItem) {
		this.mostSpendItem = mostSpendItem;
	}

	public int getMostSpendItemPrice() {
		return mostSpendItemPrice;
	}

	public void setMostSpendItemPrice(int mostSpendItemPrice) {
		this.mostSpendItemPrice = mostSpendItemPrice;
	}

	public String getMostSpendDay() {
		return mostSpendDay;
	}

	public void setMostSpendDay(String mostSpendDay) {
		this.mostSpendDay = mostSpendDay;
	}

	public int getMostSpendDayPrice() {
		return mostSpendDayPrice;
	}

	public void setMostSpendDayPrice(int mostSpendDayPrice) {
		this.mostSpendDayPrice = mostSpendDayPrice;
	}
	// getter setter

	@Override
	public String toString() {
		return "ProgressData [CUST_TARGET_PRICE=" + CUST_TARGET_PRICE + ", spendMonth=" + spendMonth + ", leftMonth="
				+ leftMonth + ", canMonth=" + canMonth + ", averMonth=" + averMonth + ", precentMonth=" + precentMonth
				+ ", mostSpendItem=" + mostSpendItem + ", mostSpendItemPrice=" + mostSpendItemPrice + ", mostSpendDay="
				+ mostSpendDay + ", mostSpendDayPrice=" + mostSpendDayPrice + "]";
	}

}// end of ProgressData
