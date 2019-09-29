package vo;

public class TextPoint {
	
	public TextPoint() {
		// TODO Auto-generated constructor stub
	}

	public TextPoint(int num, int firstX, int firstY, int secondX, int secondY, int thirdX, int thirdY, int quadX,
			int quadY, String text) {

		super();

		this.num = num;
		this.firstX = firstX;
		this.firstY = firstY;
		this.secondX = secondX;
		this.secondY = secondY;
		this.thirdX = thirdX;
		this.thirdY = thirdY;
		this.quadX = quadX;
		this.quadY = quadY;
		this.text = text;
	}

	int num;

	int firstX;
	int firstY;

	int secondX;
	int secondY;

	int thirdX;
	int thirdY;

	int quadX;
	int quadY;

	String text;

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public int getFirstX() {
		return firstX;
	}

	public void setFirstX(int firstX) {
		this.firstX = firstX;
	}

	public int getFirstY() {
		return firstY;
	}

	public void setFirstY(int firstY) {
		this.firstY = firstY;
	}

	public int getSecondX() {
		return secondX;
	}

	public void setSecondX(int secondX) {
		this.secondX = secondX;
	}

	public int getSecondY() {
		return secondY;
	}

	public void setSecondY(int secondY) {
		this.secondY = secondY;
	}

	public int getThirdX() {
		return thirdX;
	}

	public void setThirdX(int thirdX) {
		this.thirdX = thirdX;
	}

	public int getThirdY() {
		return thirdY;
	}

	public void setThirdY(int thirdY) {
		this.thirdY = thirdY;
	}

	public int getQuadX() {
		return quadX;
	}

	public void setQuadX(int quadX) {
		this.quadX = quadX;
	}

	public int getQuadY() {
		return quadY;
	}

	public void setQuadY(int quadY) {
		this.quadY = quadY;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Override
	public String toString() {
		return "TextPoint [num=" + num + ", firstX=" + firstX + ", firstY=" + firstY + ", secondX=" + secondX
				+ ", secondY=" + secondY + ", thirdX=" + thirdX + ", thirdY=" + thirdY + ", quadX=" + quadX + ", quadY="
				+ quadY + ", text=" + text + "]\n";
	}

}
