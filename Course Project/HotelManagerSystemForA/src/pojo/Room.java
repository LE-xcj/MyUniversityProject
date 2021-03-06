package pojo;

public class Room {
	private String roomID;
	private String status;
	private String type;
	private String billNum;
	private String clientName;
	private double fee;
	private int floor;
	private int beds;
	public Room(){
	}
	public Room(String roomID , String type ,String status ,double fee ,int floor,int beds,String billNum,String clientName){
		this.roomID=roomID;
		this.type=type;
		this.status=status;
		this.fee=fee;
		this.floor=floor;
		this.beds=beds;
		this.billNum=billNum;
		this.clientName=clientName;
	}	
	
	public String getClientName() {
		return clientName;
	}
	
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	
	public String getBillNum() {
		return billNum;
	}
	
	public void setBillNum(String billNum) {
		this.billNum = billNum;
	}
	
	public int getBeds() {
		return beds;
	}
	public void setBeds(int beds) {
		this.beds = beds;
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public double getFee() {
		return fee;
	}
	public void setFee(double fee) {
		this.fee = fee;
	}
	public int getFloor() {
		return floor;
	}
	public void setFloor(int floor) {
		this.floor = floor;
	}
	
}
