package action;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import dao.BillDAO;
import util.BillDetection;
import util.FileService;
import util.LocalSearch;
import util.ShoppingSearch;
import vo.Bill;
import vo.Item;

public class BillAction extends ActionSupport implements SessionAware {

	private Bill bill;
	private Item item;
	private List<Bill> billList;
	private List<String> itemName;
	private List<Integer> itemPrice;

	private Map<String, Object> session;

	private File billImg;
	private String billImgFileName;
	private String billImgContentType;
	private String imagePath;

	/////////////////////////////////////////////////////////////////////

	public String uploadBill() throws Exception {
		
		
		System.out.println("업로드 빌 들어오나요?");

		HttpServletRequestWrapper request = new HttpServletRequestWrapper(ServletActionContext.getRequest());

		String basePath = request.getRealPath("/") + "temp";
		String savedfile = null;

		if (billImg != null) {
			FileService fs = new FileService();
			// basePath = getText("user.uploadpath"); // user.properties에 지정된 파일
			// 저장
			// 경로
			savedfile = fs.saveFile(billImg, basePath, billImgFileName);

		}

		imagePath = "temp/" + savedfile;
		String totalPath = basePath + "/" + savedfile;
		BillDetection bd = new BillDetection();
		bill = bd.assembleForm(totalPath);

		bill.setImg(imagePath);

		System.out.println(bill);

		return SUCCESS;
	}

	public String deleteBill() {

		HttpServletRequestWrapper request = new HttpServletRequestWrapper(ServletActionContext.getRequest());
		String deletePath = request.getRealPath("/") + imagePath;

		File bill_temp = new File(deletePath);

		bill_temp.delete();

		return SUCCESS;
	}

	////////////////////////////

	public String inputBill() {
		String customerId = (String) session.get("loginId");

		List<Item> itemList = new ArrayList();
		LocalSearch local = new LocalSearch();
		ShoppingSearch shopping = null;
		String category = "";
		boolean restaurantflag = false;
		BillDAO dao = new BillDAO();
		
		category = local.searchLocalCategory(bill.getStoreName());
		if(!category.equals("")){
			category = dao.searchRestaurant(category);
			restaurantflag = true;
		}else{
			shopping = new ShoppingSearch();
		}
		
		bill.setCustomerId(customerId);
		int billNo = dao.insertBill(bill);

		for (int i = 0; i < itemName.size(); i++) {
			Item item = new Item(itemName.get(i), itemPrice.get(i));
			item.setCustomerId(customerId);
			item.setBillNo(billNo);
			if(restaurantflag){ //업체카테고리로 일괄 등록
				item.setCategory(category);
			}else{
				category = shopping.searchShoppingCategory(item.getName());
				category = dao.searchItem(category);
				if(category == null){
					item.setCategory("L99M99");
				}else{
					item.setCategory(category);
				}
			}
			itemList.add(item);
		}
		
		
		dao.insertItem(itemList);
		bill.setItemList(itemList);

		System.out.println("input: " + bill);

		return SUCCESS;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;

	}

	public List<String> getItemName() {
		return itemName;
	}

	public void setItemName(List<String> itemName) {
		this.itemName = itemName;
	}

	public List<Integer> getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(List<Integer> itemPrice) {
		this.itemPrice = itemPrice;
	}

	public Bill getBill() {
		return bill;
	}

	public void setBill(Bill bill) {
		this.bill = bill;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public List<Bill> getBillList() {
		return billList;
	}

	public void setBillList(List<Bill> billList) {
		this.billList = billList;
	}

	//////////////////////////////////////////////////

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public File getBillImg() {
		return billImg;
	}

	public void setBillImg(File billImg) {
		this.billImg = billImg;
	}

	public String getBillImgFileName() {
		return billImgFileName;
	}

	public void setBillImgFileName(String billImgFileName) {
		this.billImgFileName = billImgFileName;
	}

	public String getBillImgContentType() {
		return billImgContentType;
	}

	public void setBillImgContentType(String billImgContentType) {
		this.billImgContentType = billImgContentType;
	}

}
