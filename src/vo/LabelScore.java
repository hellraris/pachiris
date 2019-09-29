package vo;

public class LabelScore {

	private String labelName;
	private int labelScore;

	public LabelScore() {
		// TODO Auto-generated constructor stub
	}

	public LabelScore(String labelName, int labelScore) {
		super();
		this.labelName = labelName;
		this.labelScore = labelScore;
	}

	public String getLabelName() {
		return labelName;
	}

	public void setLabelName(String labelName) {
		this.labelName = labelName;
	}

	public int getLabelScore() {
		return labelScore;
	}

	public void setLabelScore(int labelScore) {
		this.labelScore = labelScore;
	}

	@Override
	public String toString() {
		return "Label [labelName=" + labelName + ", labelScore=" + labelScore + "]";
	}

}
