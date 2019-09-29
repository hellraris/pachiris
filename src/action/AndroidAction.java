package action;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import dao.CustomerDAO;
import util.BillDetection;
import util.FileService;
import vo.Bill;
import vo.Customer;

public class AndroidAction extends ActionSupport implements SessionAware {
	private Map<String, Object> session;
	private boolean loginCheck;
	private String cust_email;
	private String cust_password;
	private File billImg;
	private Bill bill;
	private String imagePath;
	private String billImgFileName;
	private ArrayList<Bill> billList;

	public String execute() {
		System.out.println(cust_email + " / " + cust_password);

		CustomerDAO dao = new CustomerDAO();
		Customer customer = new Customer();
		customer.setCust_email(cust_email);
		customer.setCust_password(cust_password);
		Customer c = dao.login(customer);
		System.out.println("cust : " + customer + " / c : " + c);
		if (c == null) {
			loginCheck = false;
		} else {
			// session.put("loginId", c.getCust_email());
			// session.put("nickname", c.getCust_nickname());
			loginCheck = true;
		}
		return SUCCESS;
	}

	public String uploadBill() throws Exception {

		System.out.println("업로드 빌 들어오나요?");

		HttpServletRequestWrapper request = new HttpServletRequestWrapper(ServletActionContext.getRequest());

		String basePath = request.getRealPath("/") + "temp";
		String savedfile = null;

		if (billImg != null) {
			
			BufferedImage origin = ImageIO.read(billImg);
			BufferedImage update = rotate(origin, 270);
			ImageIO.write(update, "jpg" , billImg);
			
			
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

		System.out.println("안드로이드 출력" + bill);

		bill.setImg(imagePath);
		
		billList = new ArrayList<>();

		billList.add(bill);

		return SUCCESS;
	}
	
	
	public BufferedImage rotate(BufferedImage bi, int degree) {
	    int width = bi.getWidth();
	    int height = bi.getHeight();

	    BufferedImage biFlip;
	    if (degree == 90 || degree == 270)
	        biFlip = new BufferedImage(height, width, bi.getType());
	    else if (degree == 180)
	        biFlip = new BufferedImage(width, height, bi.getType());
	    else 
	        return bi;

	    if (degree == 90) {
	        for (int i = 0; i < width; i++)
	            for (int j = 0; j < height; j++)
	                biFlip.setRGB(height- j - 1, i, bi.getRGB(i, j));
	   }

	if (degree == 180) {
	    for (int i = 0; i < width; i++)
	        for (int j = 0; j < height; j++)
	            biFlip.setRGB(width - i - 1, height - j - 1, bi.getRGB(i, j));
	}

	if (degree == 270) {
	    for (int i = 0; i < width; i++)
	        for (int j = 0; j < height; j++)
	            biFlip.setRGB(j, width - i - 1, bi.getRGB(i, j));
	}

	bi.flush();
	bi = null;

	return biFlip;
	}

	public boolean isLoginCheck() {
		return loginCheck;
	}

	public void setLoginCheck(boolean loginCheck) {
		this.loginCheck = loginCheck;
	}

	public String getCust_email() {
		return cust_email;
	}

	public void setCust_email(String cust_email) {
		this.cust_email = cust_email;
	}

	public String getCust_password() {
		return cust_password;
	}

	public void setCust_password(String cust_password) {
		this.cust_password = cust_password;
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

	public Bill getBill() {
		return bill;
	}

	public void setBill(Bill bill) {
		this.bill = bill;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	
	

	public ArrayList<Bill> getBillList() {
		return billList;
	}

	public void setBillList(ArrayList<Bill> billList) {
		this.billList = billList;
	}

	@Override
	public void setSession(Map<String, Object> arg0) {
		// TODO Auto-generated method stub
		this.session = session;
	}

	public Map<String, Object> getSession() {
		return session;
	}

}
