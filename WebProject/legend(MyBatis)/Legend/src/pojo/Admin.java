package pojo;

public class Admin {
	private String aName;
	private String apwd;
	
	public Admin(){}
	
	public Admin(String aName,String apwd){
		this.aName=aName;
		this.apwd=apwd;
	}
	
	public String getaName() {
		return aName;
	}
	public void setaName(String aName) {
		this.aName = aName;
	}
	public String getApwd() {
		return apwd;
	}
	public void setApwd(String apwd) {
		this.apwd = apwd;
	}
	
	
}
