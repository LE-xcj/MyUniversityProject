package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import java.text.SimpleDateFormat;

import pojo.Admin;
import pojo.Bill;
import pojo.BriefBill;
import pojo.Client;
import pojo.Room;
import utils.ContainerForPojo;

/*
 * dao层
 * 只进行对数据 的增删查改，直接与数据库打交道
 * 
 */

public class OperaPojo {
	private static ResultSet result;
	private final static int adminBF=0;
	private final static int clientBF=1;
	private final static int roomBF=2;
	private final static int maxNum=16;		//一页最多有16个，也就是4行
	private final static int MAXBILLNum=20;
	private final static int COLUMN=12;
	private final static int HCOLUMN=4;
	private final static String smaxNum="16";
	private final static String all="全部显示";
	private final static String valid="可用";
	private final static String normal="普通";
	private final static String TODAY="今天";
	private final static String WEEK="近一周";
	private final static String BILLNUM="订单号";
	private final static String ADMIN="记账人";
	private final static String CLIENTID="客户";
	private final static SimpleDateFormat df =new SimpleDateFormat("yyyy-MM-dd");
	
	
	public static boolean superAdminLogin(String saname,String sapwd){
		boolean flag=false;
		String sql="select * from sadmin where saname='"+saname+"' and sapwd='"+sapwd+"'";
		result=DataBase.select(sql);
		try {
			flag= result.next();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}
	
	public static void addAdmin(String aname, String apwd, String state){
		String sql="insert into admin (aname, apwd,state) "
				+ "values('"+aname+"' , '"+apwd+"' , '"+state+"')";
		DataBase.other(sql);
	}
		
	public static void updateAdmin(String pk,String aname, String apwd, String state){
		if(!pk.equals(aname)){
			addAdmin(aname, apwd, state);
			updateBillFK(aname, pk, adminBF);
		}else{
			String sql="update admin set aname='"+aname+
					"' , apwd='"+apwd+
					"' , state='"+state+
					"' where aname='"+pk+"'";
			DataBase.other(sql);
		}
		if(!pk.equals(aname))
			delete(pk, adminBF);
	}
	
	public static void addClient(String id,String cname,String cpwd,String phoneNum,String sex,String status){
		String sql="insert into client (id,cname,cpwd,phoneNum,sex,status) values('"+id+"' , '"
																					+cname+"' , '"
																					+cpwd+"' , '"
																					+phoneNum+"' , '"
																					+sex+"','"
																					+status+"')";
		DataBase.other(sql);
	}
	
	public static void updateClient(String pk,String cid,String cname,String cpwd,String sex,String status,String phoneNum){
		if(!pk.equals(cid)){
			addClient(cid, cname, cpwd, phoneNum, sex, status);		
			updateBillFK(cid, pk, clientBF);
		}else{
			String sql="update client set cname='"+cname+
					"' , cpwd='"+cpwd+
					"' , sex='"+sex+
					"' , status='"+status+
					"' , phoneNum='"+phoneNum+
					"' where id='"+pk+"'";
			DataBase.other(sql);
		}
		if(!pk.equals(cid))
			delete(pk, clientBF);
		
	}
	
	public static void addRoom(String roomID,String type, String floor, String fee,String beds,String status){
		String sql="insert into room (roomID,type,floor,fee,beds,status) values('"+roomID+
																				"', '"+type+
																				"', "+floor+
																				" , "+fee+
																				" , "+beds+
																				" , '"+status
																				+"')";
		DataBase.other(sql);
	}
	
	public static void updateRoom(String pk,String roomID,String type, String floor, String fee,String beds,String status){
		if(!pk.equals(roomID)){
			addRoom(roomID, type, floor, fee, beds, status);
			updateBillFK(roomID,pk,roomBF);
		}
		else{
			String sql="update room set roomID= '"+roomID+
						"', type='"+type+
						"', floor="+floor+
						" , fee="+fee+
						" , beds="+beds+
						" , status='"+status+
						"' where roomID='"+pk+"'";

			DataBase.other(sql);
			//updateRoom(roomID);
		}
		
		if(!pk.equals(roomID))
			delete(pk, roomBF);
	}
	
	private static void updateBillFK(String newFk,String oldFk,int flag){
		String sql=null;
		switch(flag){
			case adminBF:{				
				sql="update bill set aname='"+newFk+"' where aname='"+oldFk+"'";
			}
			break;
			case clientBF:{
				sql="update bill set cid='"+newFk+"' where cid='"+oldFk+"'";
			}
			break;
			case roomBF:{
				sql="update bill set roomID='"+newFk+"' where roomID='"+oldFk+"'";
			}
			break;
		}
		DataBase.other(sql);
	}
		
	//防止出现超级管理员设置房间的状态位可用时（但是该房间还有预定的订单，即状态应该是预定），变成可用
	public static void updateRoom(String roomID){
		String sql="update room set status='预定' "
				+ "where (select count(billNum) from bill where roomID='"+roomID+"' and status ='未结账')<>0 and "
						+ "roomID='"+roomID+"'";
		DataBase.other(sql);
	}
	
	
	public static void initAdminList(String condition,int currentPage){
		String sql=null;
		int temp=(currentPage-1)*maxNum;
		String begin=Integer.toString(temp);
		if(all.equals(condition))
			sql="select * from admin where aname in ("
					+ "select top "+smaxNum+" aname from admin where aname not in("
					+"select top "+begin+" aname from admin)"
					+")";
		else if(valid.equals(condition))
			sql="select * from admin where aname in ("
					+ "select top "+smaxNum+" aname from admin where state = '"+valid+"' and aname not in("
					+"select top "+begin+" aname from admin where state ='"+valid+"')"
					+")";
		else
			sql="select * from admin where aname in ("
					+ "select top "+smaxNum+" aname from admin where state <> '"+valid+"' and aname not in("
					+"select top "+begin+" aname from admin where state <>'"+valid+"')"
					+")";
		result=DataBase.select(sql);
		try {
			while(result.next()){
				ContainerForPojo.list_admin.add(new Admin(result.getString("aname"),
														  result.getString("apwd"),
														  result.getString("state")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void initClientList(String condition,int currentPage){
		String sql=null;
		int temp=(currentPage-1)*maxNum;
		String begin=Integer.toString(temp);
		if(all.equals(condition))
			sql="select * from client where id in ("
					+ "select top "+smaxNum+" id from client where id not in("
					+"select top "+begin+" id from client)"
					+")";
		else if(normal.equals(condition))
			sql="select * from client where id in ("
					+ "select top "+smaxNum+" id from client where status = '"+normal+"' and id not in("
					+"select top "+begin+" id from client where status ='"+normal+"')"
					+")";
		else
			sql="select * from client where id in ("
					+ "select top "+smaxNum+" id from client where status <> '"+normal+"' and id not in("
					+"select top "+begin+" id from client where status <>'"+normal+"')"
					+")";
		result=DataBase.select(sql);
		try {
			while(result.next()){
				ContainerForPojo.list_clent.add(new Client(result.getString("id"),
															result.getString("cname"),
															result.getString("cpwd"),
															result.getString("sex"),
															result.getString("status"),
															result.getString("phoneNum")
															));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void initRoomList(String condition,int currentPage){
		String sql=null;
		int temp=(currentPage-1)*maxNum;
		String begin=Integer.toString(temp);
		if(all.equals(condition))
			sql="select * from room where roomID in ("
					+ "select top "+smaxNum+" roomID from room where roomID not in("
					+"select top "+begin+" roomID from room)"
					+")";
		else if(valid.equals(condition))
			sql="select * from room where roomID in ("
					+ "select top "+smaxNum+" roomID from room where status = '"+valid+"' and roomID not in("
					+"select top "+begin+" roomID from room where status ='"+valid+"')"
					+")";
		else
			sql="select * from room where roomID in ("
					+ "select top "+smaxNum+" roomID from room where status <> '"+valid+"' and roomID not in("
					+"select top "+begin+" roomID from room where status <>'"+valid+"')"
					+")";
		result=DataBase.select(sql);
		try {
			while(result.next()){
				ContainerForPojo.list_room.add(new Room(result.getString("roomid"),
														result.getString("type"),
														result.getString("status"),
														result.getDouble("fee"),
														result.getInt("floor"),
														result.getInt("beds")
														));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void initBillVector(String time , int currentPage){
		String sql=null;
		switch(time){
			case all:{
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
				ContainerForPojo.vector_bill.add(new Bill(result.getString("billNum"),
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
			case BILLNUM:{
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
			case CLIENTID:{
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
				ContainerForPojo.vector_bill.add(new Bill(result.getString("billNum"),
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
	
	public static void initBriefBill(String roomID){
		String sql="select billNum, clientID,aname,cDate from billView where status ='未结账' and roomID='"
				+roomID +"' order by cDate";
		result=DataBase.select(sql);
		try {
			while(result.next()){
				ContainerForPojo.list_bill.add(new BriefBill(result.getString("billNum"),
															result.getString("clientID"),
															result.getString("aname"),
															result.getDate("cDate")													
												));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public static void delete(String pk, int flag){	
		String sql=null;		
		switch(flag){
			case adminBF:{
				sql="delete from admin where aname='"+pk+"'";
			}
			break;
			case clientBF:{
				sql="delete from client where id='"+pk+"'";
			}
			break;
			case roomBF:{
				sql="delete from room where roomid='"+pk+"'";
			}
			break;
		}
		DataBase.other(sql);
	}
	
	public static void deleteBill(String condition,int flag){
		String sql=null;
		switch(flag){
			case adminBF:
				sql="delete from bill where aname ='"+condition+"'";
			break;
			case clientBF:
				sql="delete from bill where cid ='"+condition+"'";
			break;
			case roomBF:
				sql="delete from bill where roomID ='"+condition+"'";
			break;
		}
		DataBase.other(sql);
	}
	
	public static void deleteBill(String pk){
		String sql="delete from bill where billNum='"+pk+"'";
		DataBase.other(sql);
	}
	
	public static int countTotal(int flag){
		String sql=null;
		int count=0;
		switch(flag){
			case adminBF:
				sql="select count(*) total from admin";
			break;
			case clientBF:
				sql="select count(*) total from client";
			break;
			case roomBF:
				sql="select count(*) total from room";
		}
		result=DataBase.select(sql);
		try {
			result.next();
			count=result.getInt("total");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}
	
	public static int validRow(int flag,String condition){
		int count=0;
		String sql=null;
		switch(flag){
			case adminBF:
				sql="select count(*) valid from admin where state ='"+condition+"'";
			break;
			case clientBF:
				sql="select count(*) valid from client where status='"+condition+"'";
			break;
			case roomBF:
				sql="select count(*) valid from room where status='"+condition+"'";
			break;
		}
		result=DataBase.select(sql);
		try {
			result.next();			//因为result返回的是第一条记录的前一个
			count=result.getInt("valid");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}
	
	public static int countType(String sql){
		int length=0;
		result=DataBase.select(sql);
		try {
			result.next();
			length=result.getInt("count");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return length;
	}
	
	public static String[] roomType(){
		String[] type=null;
		int length=0;
		int count=0;
		String sql="select type from room group by type";	
		length=countType("select count(*) count from (select type from room group by type) t");
		result=DataBase.select(sql);
		try {
			type=new String[length];
			while(result.next()){
				type[count++]=new String(result.getString("type"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return type;
	}
	
	public static String[] imforPojo(String pk,int flag){
		String[] imfor=null;
		String sql=null;
		String temp=null;
		int count=0;
		int length=0;
		switch(flag){
			case adminBF:{
				length=3;
				sql="select * from admin where aname='"+pk+"'";	
				imfor=new String[length];
			}
			break;
			case clientBF:{
				length=6;
				sql="select * from client where id='"+pk+"'";
				imfor=new String[length];
			}
			break;
			case roomBF:{
				length=3;
				sql="select * from room where roomID='"+pk+"'";
				imfor=new String[6];
			}
			break;
		}
		result=DataBase.select(sql);
		try {
			while(result.next()){
				for(count=0;count<length;++count){	
					temp=result.getString(count+1);
					if(temp!=null)
						imfor[count]=new String(temp);		//第一列是从下标1开始
					
				}
				if(flag==roomBF){
					imfor[count++]=Integer.toString(result.getInt("floor"));
					imfor[count++]=Double.toString(result.getDouble("fee"));
					imfor[count]=Integer.toString(result.getInt("beds"));
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return imfor;
	}
	
	public static int pageNum(int flag){
		String sql=null;
		int count=0;
		switch(flag){
			case adminBF:{
				sql="select count(*) count from admin";
			}
			break;
			case clientBF:{
				sql="select count(*) count from client";
			}
			break;
			case roomBF:{
				sql="select count(*) count from room";
			}
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
		if(count%maxNum==0)
			count=count/maxNum;
		else
			count=count/maxNum+1;
		return count;
	}
	
	public static int pageNum(int flag,String condition){
		String sql=null;
		int count=0;
		switch(flag){
			case adminBF:{
				if(all.equals(condition))
					sql="select count(*) count from admin ";
				else if(valid.equals(condition))
					sql="select count(*) count from admin where state = '"+valid+"'";
				else
					sql="select count(*) count from admin where state <> '"+valid+"'";
					
			}
			break;
			case clientBF:{
				if(all.equals(condition))
					sql="select count(*) count from client ";
				else if(normal.equals(condition))
					sql="select count(*) count from client where status = '"+normal+"'";
				else 
					sql="select count(*) count from client where status <> '"+normal+"'";
			}
			break;
			case roomBF:{
				if(all.equals(condition))
					sql="select count(*) count from room ";
				else if(valid.equals(condition))
					sql="select count(*) count from room where status = '"+valid+"'";
				else
					sql="select count(*) count from room where status <> '"+valid+"'";
			}
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
		if(count%maxNum==0)
			count=count/maxNum;
		else
			count=count/maxNum+1;
		return count;
	}
	
	public static void setFee(String type,String fee){
		String sql="update room set fee="+fee+"where type='"+type+"' and status ='可用'";
		DataBase.other(sql);
	}
	
	public static String[][] tableData(){
		String[][] rowData=null;
		Date temp;
		int length=ContainerForPojo.vector_bill.size();
		int j=0;
		rowData=new String[length][COLUMN];
		for(int i=0;i<length;++i){
			j=0;
			rowData[i][j++]=ContainerForPojo.vector_bill.get(i).getBillNum();
			rowData[i][j++]=ContainerForPojo.vector_bill.get(i).getCname();
			rowData[i][j++]=ContainerForPojo.vector_bill.get(i).getcID();
			rowData[i][j++]=ContainerForPojo.vector_bill.get(i).getRoomID();
			rowData[i][j++]=df.format(ContainerForPojo.vector_bill.get(i).getcDate());
			temp=ContainerForPojo.vector_bill.get(i).getlDate();
			if(temp!=null)
				rowData[i][j++]=df.format(temp);
			else
				rowData[i][j++]="";
			rowData[i][j++]=Double.toString(ContainerForPojo.vector_bill.get(i).getDeposit());
			rowData[i][j++]=Double.toString(ContainerForPojo.vector_bill.get(i).getTotalFee());
			rowData[i][j++]=Double.toString(ContainerForPojo.vector_bill.get(i).getRealPay());
			rowData[i][j++]=ContainerForPojo.vector_bill.get(i).getStatus();
			rowData[i][j++]=df.format(ContainerForPojo.vector_bill.get(i).getCreateDate());			
			rowData[i][j++]=ContainerForPojo.vector_bill.get(i).getAname();
		}
		return rowData;
	}
	
	public static String[][] hotelTableDate(){
		String[][] rowData=null;
		int length=ContainerForPojo.list_bill.size();
		rowData=new String[length][HCOLUMN];
		int j;
		for(int i=0;i<length;++i){
			j=0;
			rowData[i][j++]=ContainerForPojo.list_bill.get(i).getBillNum();
			rowData[i][j++]=ContainerForPojo.list_bill.get(i).getCid();
			rowData[i][j++]=ContainerForPojo.list_bill.get(i).getAname();
			rowData[i][j++]=df.format(ContainerForPojo.list_bill.get(i).getcDate());
		}
		return rowData;
	}
	
	
	public static int pageOfBill(String condition){
		String sql=null;
		int count=0;
		switch(condition){
			case all:
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
		if(BILLNUM.equals(condition))
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
	
	public static void updateBillStatus(String pk,String newStatus){
		String sql="update bill set status ='"+newStatus+"' where billNum='"+pk+"'";
		DataBase.other(sql);
		
		sql="update room set billNum=NULL where billNum='"+pk+"'";
		DataBase.other(sql);
	}
	
	public static void updateBillStatus(String roomID){
		String sql="update bill set status ='取消' where roomID='"+roomID+"' and status ='未结账'";
		DataBase.other(sql);
		
		sql="update room set billNum=NULL where roomID='"+roomID+"'";
		DataBase.other(sql);
	}
	
	public static void updateBill(String pk,String roomID,String cDate,String lDate,String deposit){
		String sql="update bill set roomID='"+roomID+"' , cDate='"+cDate+"' , lDate='"+lDate+"' , deposit="+deposit+
					"where billNum='"+pk+"'";
		DataBase.other(sql);
	}
	
	public static void updateBill(String pk,String totalFee,String status){
		String sql="update bill set status='"+status+"', realPay="+totalFee+"where billNum='"+pk+"'";
		DataBase.other(sql);
		
		sql="update room set billNum=NULL where billNum='"+pk+"'";
		DataBase.other(sql);
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
		String sql="select roomID from room where status <> '停用'";
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
	
	public static String calculateProfit(String from,String to){
		String profit=null;
		
		String sql="declare @profit decimal(10,1) \n"+
					"set @profit =dbo.calculateProfit('"+from+"' , '"+to+"')"+
					"select @profit profit";
		result=DataBase.select(sql);
		try {
			result.next();
			profit=result.getString("profit");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return profit;
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

	
}
