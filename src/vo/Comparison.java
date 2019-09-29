package vo;

public class Comparison {

	private String id;
	private String gender;
	private String age;
	private String address;
	private String time;
	private int count;

	public Comparison() {

	}

	public Comparison(String id, String gender, String age, String address, String time, int count) {
		super();
		this.id = id;
		this.gender = gender;
		this.age = age;
		this.address = address;
		this.time = time;
		this.count = count;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "Comparison [id=" + id + ", gender=" + gender + ", age=" + age + ", address=" + address + ", time="
				+ time + ", count=" + count + "]";
	}
	
	

}
