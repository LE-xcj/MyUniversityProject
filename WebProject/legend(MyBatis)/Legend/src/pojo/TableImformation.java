package pojo;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TableImformation {
	private String sIP;
	private String sName;
	private String onTime;
	private String roadType;
	private String detail;
	private String qq;
	private String gameAddress;
	private static SimpleDateFormat df=new SimpleDateFormat("yyyy��MM��dd��");
	
	public TableImformation(){
		 	
	}
	
	public TableImformation(String sIP,String sName,Date onTime,
							String roadType,String detail,String qq,
							String gameAddress){
		this.sIP=sIP;
		this.sName=sName;
		this.onTime=df.format(onTime);
		this.roadType=roadType;
		this.detail=detail;
		this.qq=qq;
		this.gameAddress=gameAddress;
	}
	
	public TableImformation(String sIP, String sName, String roadType, String detail, String qq,
			String gameAddress) {
		this.sIP = sIP;
		this.sName = sName;
		this.roadType = roadType;
		this.detail = detail;
		this.qq = qq;
		this.gameAddress = gameAddress;
	}

	public String getsIP() {
		return sIP;
	}

	public void setsIP(String sIP) {
		this.sIP = sIP;
	}

	public String getsName() {
		return sName;
	}

	public void setsName(String sName) {
		this.sName = sName;
	}

	public String getOnTime() {
		return onTime;
	}

	public void setOnTime(String onTime) {
		this.onTime = onTime;
	}

	public String getRoadType() {
		return roadType;
	}

	public void setRoadType(String roadType) {
		this.roadType = roadType;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getGameAddress() {
		return gameAddress;
	}

	public void setGameAddress(String gameAddress) {
		this.gameAddress = gameAddress;
	}
	
	
}
