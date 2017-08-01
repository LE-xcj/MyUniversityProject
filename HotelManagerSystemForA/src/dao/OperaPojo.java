package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import pojo.Bill;
import pojo.Client;
import pojo.Room;
import utils.ContainerForPojo;




public class OperaPojo {
	private static ResultSet result;
	private static final int TEXTNUM=7;
	private static final int CLIENTIMFORNUM=5;
	private static final int TABLECOLUMNNUM=11;
	private static final int WILLUSIGNTITLECOLUMN=6;
	private static final int BILLNUM=0;
	private static final int CLIENTID=1;
	private static final int CLIENTNAME=2;
	private static final int ROOMCLUMN=2;
	private final static int MAXBILLNum=20;
	private final static int COLUMN=12;
	private final static int ROWNUM=18;
	private static String enable="不可用";
	private static final String ALL="全部显示";
	private static final String	TODAY="今天";
	private static final String WEEK="近一周";
	private static final String BILLNUMF="订单号";
	private static final String ADMIN="记账人";
	private static final String CLIENTIDF="客户";
	private static final String NORMAL="普通";
	private final static SimpleDateFormat df =new SimpleDateFormat("yyyy-MM-dd");
	
	//判断是否存在
	public static boolean exit(String pk){
		String sql="select count(aname) count from admin where aname='"+pk+"'";
		result=DataBase.select(sql);
		int flag=0;
		try {
			result.next();
			flag=result.getInt("count");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return (1==flag);
	}
	
	public static boolean exitClient(String cid){
		String sql="select count(id) count from client where id='"+cid+"'";
		result=DataBase.select(sql);
		int flag=0;
		try {
			result.next();
			flag=result.getInt("count");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return (1==flag);
	}
	
	public static boolean adminEnable(String aname){
		String sql="select state from admin where aname='"+aname+"'";
		result=DataBase.select(sql);
		boolean flag=true;
		try {
			result.next();
			flag=enable.equals(result.getString("state"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}
	
	public static boolean match(String str1,String str2){
		String sql="select aname, apwd from admin where aname='"+str1+"'";
		result=DataBase.select(sql);
		boolean flag=false;
		try {
			result.next();
			String aname=result.getString("aname");
			String apwd=result.getString("apwd");
			flag=(str1.equals(aname)&&str2.equals(apwd));
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return flag;
	}
	
	public static int countRoomType(){
		int count=0;
		String sql="select count(type) count from ("+
							"select type from room group by type) t";
		result=DataBase.select(sql);
		try {
			result.next();
			count=result.getInt("count");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return count;		
	}
	
	//注意有可能为空，但是这个很少出现
	public static String[] roomType(){
		String[] type=null;
		int length=countRoomType();
		int index=0;
		type=new String[length];
		String sql="select type from room group by type";
		result=DataBase.select(sql);
		try {
			while(result.next()){
				type[index++]=result.getString("type");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return type;
	}
	
	public static void initRoomList(String type){
		String sql="select *, cname from room left join billView on room.billNum = billView.billNum "
						+ "where type='"+type+"'";
		result=DataBase.select(sql);
		try {
			while(result.next()){
				ContainerForPojo.room_list.add(new Room(result.getString("roomID"),
														result.getString("type"),
														result.getString("status"),
														result.getDouble("fee"),
														result.getInt("floor"),
														result.getInt("beds"),
														result.getString("billNum"),
														result.getString("cname")
												));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static String[] roomImfor(int index){
		Room room=ContainerForPojo.room_list.get(index);
		String[] text=new String[TEXTNUM];
		int count=0;
		text[count++]=room.getRoomID();
		text[count++]=Integer.toString(room.getFloor());
		text[count++]=Integer.toString(room.getBeds());
		text[count++]=Double.toString(room.getFee());
		text[count++]=room.getStatus();
		text[count++]=room.getBillNum();
		text[count]=room.getClientName();
		return text;
	}
	
	public static void initBill(String roomID){
		ContainerForPojo.bill_list.clear();
		String sql="select billView.* , client.status cStatus, client.phoneNum "
				+ "from billView join client on clientID=id and billView.status='未结账'"
				+"and roomID='"+roomID+"' order by cDate";
		
		result=DataBase.select(sql);
		try {
			while(result.next()){
				ContainerForPojo.bill_list.add(new Bill(result.getString("billNum"),
														result.getString("clientID"),
														result.getString("cname"),
														result.getString("cStatus"),
														result.getString("phoneNum"),
														result.getDate("cDate"),
														result.getDate("lDate"),
														result.getDouble("deposit"),
														result.getDouble("totalFee"),
														result.getDate("createDate"),
														result.getString("aname")												
						));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void initWillUsingBill(String dayGap){
		ContainerForPojo.bill_list.clear();
		String sql="select * from willUsingRoom("+dayGap+")";
		result=DataBase.select(sql);
		try {
			while(result.next()){
				ContainerForPojo.bill_list.add(new Bill(result.getString("billNum"),
														result.getString("cid"),
														result.getString("cname"),
														result.getDate("cDate"),
														result.getString("roomID"),
														result.getString("phoneNum")										
						));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static String[][] getWillUsingBillData(){
		String[][] rowData=null;
		ArrayList<Bill> bills=ContainerForPojo.bill_list;
		int row=bills.size();
		if(0==row)
			return null;
		rowData=new String[row][WILLUSIGNTITLECOLUMN];
		int col;
		for(int i=0;i<row;++i){
			col=0;
			rowData[i][col++]=bills.get(i).getBillNum();
			rowData[i][col++]=bills.get(i).getCid();
			rowData[i][col++]=bills.get(i).getCname();
			rowData[i][col++]=df.format(bills.get(i).getcDate());
			rowData[i][col++]=bills.get(i).getRoomID();
			rowData[i][col++]=bills.get(i).getPhoneNum();
		}
		return rowData;
	}
	
	public static String[][] getRowData(){
		String[][] rowData=null;
		ArrayList<Bill> bills=ContainerForPojo.bill_list;
		int row=bills.size();
		if(0==row)
			return null;
		rowData=new String[row][TABLECOLUMNNUM];
		int col;
		for(int i=0;i<row;++i){
			col=0;
			rowData[i][col++]=bills.get(i).getBillNum();
			rowData[i][col++]=bills.get(i).getCid();
			rowData[i][col++]=bills.get(i).getCname();
			rowData[i][col++]=bills.get(i).getcStatus();
			rowData[i][col++]=bills.get(i).getPhoneNum();
			rowData[i][col++]=df.format(bills.get(i).getcDate());
			rowData[i][col++]=df.format(bills.get(i).getlDate());
			rowData[i][col++]=Double.toString(bills.get(i).getDeposit());
			rowData[i][col++]=Double.toString(bills.get(i).getTotalFee());
			rowData[i][col++]=df.format(bills.get(i).getCreateDate());
			rowData[i][col++]=bills.get(i).getAname();
		}
		return rowData;
	}

	public static void updateBill(String billNum, String shouldPay, final String status,String roomID) {
		// TODO Auto-generated method stub
		String sql="update bill set status='"+status+"', realPay="+shouldPay+"where billNum='"+billNum+"'";
		DataBase.other(sql);
		setRoomBillNull(roomID);
	}
	
	private static void setRoomBillNull(String roomID){
		String sql="update room set billNum=null where roomID='"+roomID+"'";
		DataBase.other(sql);
	}
	
	public static void updateRoomStatus(String roomID,String billNum){
		String sql="update room set billNum='"+billNum+"' , status='入住' where roomID='"+roomID+"'";
		DataBase.other(sql);
	}
	
	public static String[] billImfors(int flag){
		String[] items=null;
		int length=ContainerForPojo.bill_list.size();
		items=new String[length];
		switch(flag){
			case BILLNUM:{
				for(int i=0;i<length;++i)
					items[i]=ContainerForPojo.bill_list.get(i).getBillNum();
			}
			break;
			case CLIENTID:{
				for(int i=0;i<length;++i)
					items[i]=ContainerForPojo.bill_list.get(i).getCid();
			}
			break;
			case CLIENTNAME:{
				for(int i=0;i<length;++i)
					items[i]=ContainerForPojo.bill_list.get(i).getCname();
			}
			break;
		}
		return items;
	}
	
	public static String[] clientImfor(String clientID){
		String[] imfor=new String[CLIENTIMFORNUM];
		String sql="select * from client where id='"+clientID+"'";
		result=DataBase.select(sql);
		int count=0;
		boolean flag=false;
		try {
			flag=result.next();
			if(!flag)
				return null;
			imfor[count++]=result.getString("id");
			imfor[count++]=result.getString("cname");
			imfor[count++]=result.getString("phoneNum");
			imfor[count++]=result.getString("sex");
			imfor[count]=result.getString("status");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return imfor;
	}
	
	public static int getClientBillNum(String clientID){
		String sql ="select count(billNum) count from bill where cID='"+clientID+"'";
		result=DataBase.select(sql);
		int count=0;
		try {
			result.next();
			count=result.getInt("count");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}
	
	public static boolean isOverlap(String roomID,String cDate,String lDate,String billNum){
		String sql="declare @flag int \n"+
					"set @flag=  dbo.isOverlap('"+cDate+"' , '"+lDate+"' , '"+roomID+"', '"+billNum+"') \n"+
					"select @flag flag";
		result=DataBase.select(sql);
		int flag=0;
		try {
			result.next();
			flag=result.getInt("flag");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return (1==flag);	
	}
	
	public static int pageOfBill(String condition){
		String sql=null;
		int count=0;
		switch(condition){
			case ALL:
				sql="select count(*) count from bill ";
			break;
			case TODAY:
				sql="select count(*) count from bill where datediff(DAY,createDate,getdate())=0";
			break;
			case WEEK:
				sql="select count(*) count from bill where datediff(DAY,createDate,getdate())<=7";
			break;
		}
		result=DataBase.select(sql);
		try {
			result.next();
			count=result.getInt("count");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(count==0)
			return 1;		
		else if(count%MAXBILLNum==0)
			return count/MAXBILLNum;
		else return count/MAXBILLNum+1;
		
	}
	
	public static int pageOfBill(String condition,String constrain){
		String sql=null;
		if(BILLNUMF.equals(condition))
			return 1;
		else if(ADMIN.equals(condition))
			sql="select count(*) count from bill where aname='"+constrain+"'";
		else
			sql="select count(*) count from bill where cid='"+constrain+"'";
		
		result=DataBase.select(sql);
		int count=0;
		try {
			result.next();
			count=result.getInt("count");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(0==count)
			return 1;
		else if(count%MAXBILLNum==0&&count!=0)
			return count/MAXBILLNum;
		else return count/MAXBILLNum+1;
	}
	
	public static int pageAmount(String condition){
		String sql=null;
		if(ALL.equals(condition))
			sql="select count(id) count from client ";
		else 
			sql="select count(id) count from client where status='"+condition+"'";
		result=DataBase.select(sql);
		int count=0;
		try {
			result.next();
			count=result.getInt("count");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(0==count)
			return 1;
		if(0==count%ROWNUM)
			return count/ROWNUM;		
		return count/ROWNUM+1;
	}
	
	
	public static void initBillVector(String time , int currentPage){
		String sql=null;
		switch(time){
			case ALL:{
				sql="select * from billView where billNum in("+
						"select top "+MAXBILLNum+" billNum from billView where billNum not in("+
							"select top "+MAXBILLNum*(currentPage-1)+" billNum from billView)"+
						") order by billNum";
			}
			break;
			case TODAY:{
				
				sql="select * from billView where billNum in("+
						"select top "+MAXBILLNum+" billNum from billView where datediff(DAY,createDate,getdate())=0 and billNum not in("+
							"select top "+MAXBILLNum*(currentPage-1)+" billNum from billView where datediff(DAY,createDate,getdate())=0)"+
						") order by billNum";
			}
			break;
			case WEEK:{
				sql="select * from billView where billNum in("+
						"select top "+MAXBILLNum+" billNum from billView where datediff(DAY,createDate,getdate())<=7 and billNum not in("+
							"select top "+MAXBILLNum*(currentPage-1)+" billNum from billView where datediff(DAY,createDate,getdate())<=7)"+
						") order by billNum";
			}
			break;		
			
		}
		result=DataBase.select(sql);
		try {
			while(result.next()){
				ContainerForPojo.bill_vector.add(new Bill(result.getString("billNum"),
														  result.getString("clientid"),
														  result.getString("cname"),
														  result.getString("aname"),
														  result.getString("roomID"),
														  result.getDate("cDate"),
														  result.getDate("lDate"),
														  result.getString("status"),
														  result.getDouble("deposit"),
														  result.getDouble("totalFee"),
														  result.getDate("createDate"),
														  result.getDouble("realPay")
												));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void initBillVector(String condition,String constrain,int currentPage){
		String sql=null;
		switch(condition){
			case BILLNUMF:{
				sql="select * from billView where billNum='"+constrain+"'";
			}	
			break;
			case ADMIN:{
				sql="select * from billView where billNum in("+
						"select top "+MAXBILLNum+" billNum from billView where aname='"+constrain+"' and billNum not in("+
							"select top "+MAXBILLNum*(currentPage-1)+" billNum from bill where aname='"+constrain+"')"+
						") order by billNum";
			}
			break;
			case CLIENTIDF:{
				sql="select * from billView where billNum in("+
						"select top "+MAXBILLNum+" billNum from billView where clientid='"+constrain+"' and billNum not in("+
							"select top "+(MAXBILLNum*(currentPage-1))+" billNum from bill where clientid='"+constrain+"')"+
						") order by billNum";
			}
			break;
		}
		result=DataBase.select(sql);
		try {
			while(result.next()){
				ContainerForPojo.bill_vector.add(new Bill(result.getString("billNum"),
														  result.getString("clientID"),
														  result.getString("cname"),
														  result.getString("aname"),
														  result.getString("roomID"),
														  result.getDate("cDate"),
														  result.getDate("lDate"),
														  result.getString("status"),
														  result.getDouble("deposit"),
														  result.getDouble("totalFee"),
														  result.getDate("createDate"),
														  result.getDouble("realPay")
													
												));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static String[][] tableData(){
		String[][] rowData=null;
		Date temp;
		int length=ContainerForPojo.bill_vector.size();
		int j=0;
		rowData=new String[length][COLUMN];
		for(int i=0;i<length;++i){
			j=0;
			rowData[i][j++]=ContainerForPojo.bill_vector.get(i).getBillNum();
			rowData[i][j++]=ContainerForPojo.bill_vector.get(i).getCname();
			rowData[i][j++]=ContainerForPojo.bill_vector.get(i).getCid();
			rowData[i][j++]=ContainerForPojo.bill_vector.get(i).getRoomID();
			rowData[i][j++]=df.format(ContainerForPojo.bill_vector.get(i).getcDate());
			temp=ContainerForPojo.bill_vector.get(i).getlDate();
			if(temp!=null)
				rowData[i][j++]=df.format(temp);
			else
				rowData[i][j++]="";
			rowData[i][j++]=Double.toString(ContainerForPojo.bill_vector.get(i).getDeposit());
			rowData[i][j++]=Double.toString(ContainerForPojo.bill_vector.get(i).getTotalFee());
			rowData[i][j++]=Double.toString(ContainerForPojo.bill_vector.get(i).getRealPay());
			rowData[i][j++]=ContainerForPojo.bill_vector.get(i).getStatus();
			rowData[i][j++]=df.format(ContainerForPojo.bill_vector.get(i).getCreateDate());			
			rowData[i][j++]=ContainerForPojo.bill_vector.get(i).getAname();
		}
		return rowData;
	}
	
	public static void deleteBill(String pk){
		String sql="delete from bill where billNum='"+pk+"'";
		DataBase.other(sql);
	}
	
	public static void updateBillStatus(String pk,String newStatus){
		String sql="update bill set status ='"+newStatus+"' where billNum='"+pk+"'";
		DataBase.other(sql);
		
		sql="update room set billNum=NULL where billNum='"+pk+"'";
		DataBase.other(sql);
	}
	
	public static void updateClientImfor(String clientID,String cName,String phoneNum,String status,String sex){
		String sql="update client set cname='"+cName+"' ,"
				+ " phoneNum='"+phoneNum+"' ,"
				+" sex='"+sex+"',"
				+" status='"+status+"' where id='"+clientID+"'";
		DataBase.other(sql);
	}
	
	public static String getClientStatus(String clientID){
		String sql="select status from client where id='"+clientID+"'";
		result=DataBase.select(sql);
		String status=null;
		try {
			result.next();
			status=result.getString("status");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return status;
	}
	
	public static int ableRoomCount(){
		String sql="select count(*) count from room where status <> '停用'";
		result=DataBase.select(sql);
		int count=0;
		try {
			result.next();
			count=result.getInt("count");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}
	
	public static String[] AllroomID(){
		int length=ableRoomCount();
		int count=0;
		String[] room=new String[length];
		String sql="select roomID from room where status <> '禁用'";
		result=DataBase.select(sql);
		try {
			while(result.next()){
				room[count++]=result.getString("roomID");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return room;
	}
	
	public static void updateBill(String pk,String roomID,String preRoomID,String cDate,String lDate,String deposit){
		String sql="update bill set roomID='"+roomID+"' , cDate='"+cDate+"' , lDate='"+lDate+"' , deposit="+deposit+
					"where billNum='"+pk+"'";
		DataBase.other(sql);
		//修改入住房间号，如果新的房间状态为可用，就要将该房间的状态设置为“预定”
		if(!preRoomID.equals(roomID)){
			sql="update room set status='预定' where status='可用' and roomID='"+roomID+"'";
			DataBase.other(sql);
			
			sql="update room set status ='可用' where roomID='"+preRoomID+"' and"
					+ " 0=(select count(billNum) from bill where status='未结账' and roomID='"+preRoomID+"')";
			DataBase.other(sql);
		}
	}
	
	
	public static void initClientList(String condition,int currentPage){
		ContainerForPojo.client_list.clear();
		String sql=null;
		int temp=(currentPage-1)*ROWNUM;
		String begin=Integer.toString(temp);
		if(ALL.equals(condition))
			sql="select * from client where id in ("
					+ "select top "+ROWNUM+" id from client where id not in("
					+"select top "+begin+" id from client)"
					+")";
		else if(NORMAL.equals(condition))
			sql="select * from client where id in ("
					+ "select top "+ROWNUM+" id from client where status = '"+NORMAL+"' and id not in("
					+"select top "+begin+" id from client where status ='"+NORMAL+"')"
					+")";
		else
			sql="select * from client where id in ("
					+ "select top "+ROWNUM+" id from client where status <> '"+NORMAL+"' and id not in("
					+"select top "+begin+" id from client where status <>'"+NORMAL+"')"
					+")";
		result=DataBase.select(sql);
		try {
			while(result.next()){
				ContainerForPojo.client_list.add(new Client(result.getString("id"),
															result.getString("cname"),
															result.getString("phoneNum"),
															result.getString("sex"),
															result.getString("status")
												));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static boolean canDeleteClient(String clientID){
		String sql="select count(clientID) count from billView where clientID='"+clientID+"' and status='未结账'";
		int count=0;
		result=DataBase.select(sql);
		try {
			result.next();
			count=result.getInt("count");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return (0==count);
	}
	
	public static void deleteBillBeforeDeleteClient(String clientID){
		String sql="delete bill where clientID='"+clientID+"'";
		DataBase.other(sql);
	}
	
	public static void deleteClient(String clientID){
		deleteBillBeforeDeleteClient(clientID);
		String sql="delete client where id='"+clientID+"'";
		DataBase.other(sql);
	}
	
	public static boolean canComplish(String billNum){
		String sql="select count(billNum) count from room where billNum='"+billNum+"'";
		result=DataBase.select(sql);
		int count=0;
		try {
			result.next();
			count=result.getInt("count");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return (1==count);
	}
	
	
	public static String createBill(String clientID,String roomID,String cDate,String lDate,String deposit,String aname){
		int currentCount=getClientBillNum(clientID)+1;
		String billNum=clientID+Integer.toString(currentCount);
		String createDate=df.format(new Date());
		String sql="insert into bill (billNum,cid, roomID,cDate,lDate,deposit,createDate,aname) values('"+
					billNum+"', '"+clientID+"' , '"+roomID+"' , '"+cDate+"' , '"+lDate+"', "+deposit+", '"+createDate+"', '"
					+aname+"')";
		DataBase.other(sql);
		return billNum;
	}
	
	public static void updateRoomStatus(String roomID){
		String sql="update room set status='预定' where roomID='"+roomID+"'";
		DataBase.other(sql);
	}
	
	public static void addClient(String clientID,String cname,String status,String sex,String phoneNum){
		String sql="insert into client (id,cname,status,sex,phoneNum) values('"+clientID+"' , '"
																			+cname+"' , '"
																			+status+"', '"
																			+sex+"' , '"
																			+phoneNum+"') ";
		DataBase.other(sql);
																			
																			
	}
	
	public static String[][] suitableRoom(String status,String cDate,String lDate){
		String 	sql="select count(*) count from findSuitRoom('"+cDate+"' , '"+lDate+"' , '"+status+"')";
		result=DataBase.select(sql);
		int length=0;
		try {
			result.next();
			length=result.getInt("count");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if(0==length)
			return null;
		sql="select * from findSuitRoom('"+cDate+"' , '"+lDate+"' , '"+status+"')";
		result=DataBase.select(sql);
		String[][] roomImfor=new String[ROOMCLUMN][length];
		int count=0;
		int count1=0;
		try {
			while(result.next()){
				roomImfor[0][count++]=new String(result.getString("roomID"));
				roomImfor[1][count1++]=new String(result.getString("status"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return roomImfor;
	}
	
	
	
}
