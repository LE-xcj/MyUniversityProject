package pojo;

import java.util.Date;

public class BriefBill {
	private String billNum;
	private String cid;
	private String aname;
	private Date cDate;
	
	public BriefBill(){
		
	}
	
	public BriefBill(String billNum, String cid,String aname,Date cDate){
		this.billNum=billNum;
		this.cid=cid;
		this.aname=aname;
		this.cDate=cDate;
	}

	public String getBillNum() {
		return billNum;
	}

	public void setBillNum(String billNum) {
		this.billNum = billNum;
	}

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public String getAname() {
		return aname;
	}

	public void setAname(String aname) {
		this.aname = aname;
	}

	public Date getcDate() {
		return cDate;
	}

	public void setcDate(Date cDate) {
		this.cDate = cDate;
	}
	
	
}
