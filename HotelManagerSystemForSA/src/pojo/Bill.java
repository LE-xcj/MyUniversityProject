package pojo;

import java.util.Date;

public class Bill {
	private String billNum;
	private String cID;
	private String cname;
	private String aname;
	private String roomID;
	private Date cDate;
	private Date lDate;
	private Date createDate;
	private String status;
	private double deposit; 
	private double totalFee;
	private double realPay;

	public Bill(){
		
	}
	
	public Bill(String billNum,String cID,String cname,String aname,
				String roomID,Date cDate,Date lDate,String status,
				double deposit,double totalFee,Date createDate,double realPay){
		this.billNum=billNum;
		this.cID=cID;
		this.cname=cname;
		this.aname=aname;
		this.roomID=roomID;
		this.cDate=cDate;
		this.lDate=lDate;
		this.status=status;
		this.deposit=deposit;
		this.createDate=createDate;
		this.totalFee=totalFee;
		this.realPay=realPay;
	}
	
	
	
	public String getcID() {
		return cID;
	}
	
	public void setcID(String cID) {
		this.cID = cID;
	}
	
	public String getAname() {
		return aname;
	}
	
	public void setAname(String aname) {
		this.aname = aname;
	}
	
	public String getRoomID() {
		return roomID;
	}
	
	public void setRoomID(String roomID) {
		this.roomID = roomID;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public double getDeposit() {
		return deposit;
	}
	
	public void setDeposit(double deposit) {
		this.deposit = deposit;
	}
	
	public Date getcDate() {
		return cDate;
	}

	public void setcDate(Date cDate) {
		this.cDate = cDate;
	}

	public Date getlDate() {
		return lDate;
	}

	public void setlDate(Date lDate) {
		this.lDate = lDate;
	}
	
	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public double getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(double totalFee) {
		this.totalFee = totalFee;
	}
	
	public String getBillNum() {
		return billNum;
	}

	public void setBillNum(String billNum) {
		this.billNum = billNum;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	public double getRealPay() {
		return realPay;
	}

	public void setRealPay(double realPay) {
		this.realPay = realPay;
	}
		
}
