package pojo;

public class Client {
	private String clientID;
	private String cName;
	private String phoneNum;
	private String sex;
	private String status;
	
	public Client(String clientID,String cName,String phoneNum,String sex,String status){
		this.clientID=clientID;
		this.cName=cName;
		this.phoneNum=phoneNum;
		this.sex=sex;
		this.status=status;
	}

	public String getClientID() {
		return clientID;
	}

	public void setClientID(String clientID) {
		this.clientID = clientID;
	}

	public String getcName() {
		return cName;
	}

	public void setcName(String cName) {
		this.cName = cName;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
