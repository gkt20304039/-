package th;

import java.io.Serializable;

public abstract class UserBean implements Serializable {
	protected String bdID;
	protected String id;
	protected int num;
	protected String name;
	protected String date;
	protected String text;

    public void setBdID(String bdID) {
        this.bdID = bdID;
    }

    public String getBdID() {
        return bdID;
    }
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}

	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}

}