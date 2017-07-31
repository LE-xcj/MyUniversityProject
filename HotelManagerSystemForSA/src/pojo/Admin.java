package pojo;

public class Admin {
	private String aname;
	private String apwd;
	private String state;
	public Admin(){
		
	}
	public Admin(String aname , String apwd , String state){
		this.aname=aname;
		this.apwd=apwd;
		this.state=state;
	}
	
	public String getAname() {
		return aname;
	}
	
	public void setAname(String asname) {
		this.aname = asname;
	}
	
	public String getApwd() {
		return apwd;
	}
	
	public void setApwd(String apwd) {
		this.apwd = apwd;
	}
	
	public String getState() {
		return state;
	}
	
	public void setState(String state) {
		this.state = state;
	}
}
