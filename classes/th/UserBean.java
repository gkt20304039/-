package th;

import java.io.Serializable;

public class UserBean implements Serializable {
	private String resNum;
	private String resName;	
	private String resDate;
	private String resText;
	
	public UserBean(){}
	
	public String getNum() {
		return resNum;
	}
	public void setNum(String resNum) {
		this.resNum = resNum;
	}

	public String getName() {
		return resName;
	}
	public void setName(String resName) {
		this.resName = resName;
	}

	public String getDate() {
		return resDate;
	}
	public void setDate(String resDate) {
		this.resDate = resDate;
	}

	public String getText() {
		return resText;
	}
	public void setText(String resText) {
		this.resText = resText;
	}

	public void setAllContents(String resNum, String resName, String resDate, String resText){
		setNum(resNum);
		setName(resName);
		setDate(resDate);
		setText(resText);
	}
}