package pojo;

import java.util.Date;

public class Bill {
	private String billNum;
	private String roomID;
	private String cid;
	private String cname;
	private String cStatus;
	private String bStatus;
	private String aname;
	private String phoneNum;
	private String status;
	private Date cDate;
	private Date lDate;
	private Date createDate;
	private double deposit;
	private double totalFee;
	private double realPay;
	
	public Bill(String billNum,String cid,String cname,String cStatus,
				String phoneNum,Date cDate,Date lDate,double deposit,
				double totalFee,Date createDate,String aname){
		this.billNum=billNum;
		this.cid=cid;
		this.cname=cname;
		this.cStatus=cStatus;
		this.phoneNum=phoneNum;
		this.cDate=cDate;
		this.lDate=lDate;
		this.deposit=deposit;
		this.totalFee=totalFee;
		this.createDate=createDate;
		this.aname=aname;
	}
	
	public Bill(String billNum,String cID,String cname,String aname,
			String roomID,Date cDate,Date lDate,String status,
			double deposit,double totalFee,Date createDate,double realPay){
		this.billNum=billNum;
		this.cid=cID;
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
	
	public Bill(String billNum,String clientID,String cname,
			Date cDate,String roomID,String phoneNum){
		this.billNum=billNum;
		this.cid=clientID;
		this.cname=cname;
		this.cDate=cDate;
		this.roomID=roomID;
		this.phoneNum=phoneNum;
	}

	public String getBillNum() {
		return billNum;
	}

	public void setBillNum(String billNum) {
		this.billNum = billNum;
	}

	public String getRoomID() {
		return roomID;
	}

	public void setRoomID(String roomID) {
		this.roomID = roomID;
	}

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getcStatus() {
		return cStatus;
	}

	public void setcStatus(String cStatus) {
		this.cStatus = cStatus;
	}

	public String getbStatus() {
		return bStatus;
	}

	public void setbStatus(String bStatus) {
		this.bStatus = bStatus;
	}

	public String getAname() {
		return aname;
	}

	public void setAname(String aname) {
		this.aname = aname;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
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


	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public double getDeposit() {
		return deposit;
	}

	public void setDeposit(double deposit) {
		this.deposit = deposit;
	}

	public double getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(double totalFee) {
		this.totalFee = totalFee;
	}

	public double getRealPay() {
		return realPay;
	}

	public void setRealPay(double realPay) {
		this.realPay = realPay;
	}
	
	
}
