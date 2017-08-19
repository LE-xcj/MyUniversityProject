package pojo;

public class User {
	private String uName;
	private String upwd;
	private int status;
	
	public User(){}
	
	public User(String uName,String upwd,int status){
		this.uName=uName;
		this.upwd=upwd;
		this.status=status;
	}

	public String getuName() {
		return uName;
	}

	public void setuName(String uName) {
		this.uName = uName;
	}

	public String getUpwd() {
		return upwd;
	}

	public void setUpwd(String upwd) {
		this.upwd = upwd;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
}
