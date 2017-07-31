package pojo;

public class Client {
	private String id;
	private String cname;
	private String cpwd;
	private String sex;
	private String phoneNum;
	private String status;
	public Client(){
		
	}
	public Client(String id , String cname , String cpwd , String sex ,String status ,String phoneNum){
		this.id=id;
		this.cname=cname;
		this.cpwd=cpwd;
		this.sex=sex;
		this.status =status;
		this.phoneNum=phoneNum;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getCpwd() {
		return cpwd;
	}
	public void setCpwd(String cpwd) {
		this.cpwd = cpwd;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
