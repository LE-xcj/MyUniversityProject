package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import pojo.Admin;
import pojo.TableImformation;
import pojo.User;
import utils.DataBase;

public class OperateOnDB {
	private static ResultSet result=null;
	public static List<TableImformation> getTableList(){
		List<TableImformation> list=new ArrayList<TableImformation>();
		String sql="select * from t_sifu where flag<>1";
		result=DataBase.query(sql);
		try {
			while(result.next()){
				list.add(new TableImformation(result.getString("sIP"),result.getString("sName"),
											 new Date(),result.getString("roadType"),
											 result.getString("detail"),result.getString("qq"),
											 result.getString("gameAddress")
						));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	public static Admin getAdmin(String aName){
		Admin admin=null;;
		String sql="select * from t_admin where aName=?";
		result=DataBase.query(sql,aName);
		try {
			while(result.next()){
				admin=new Admin(result.getString("aName"),result.getString("apwd"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("getAdmin方法有异常");
		}
		return admin;
	}
	
	public static User getUser(String uName){
		User user=null;
		String sql="select * from t_user where userName=?";
		result=DataBase.query(sql, uName);
		try {
			while(result.next()){
				user=new User(result.getString("userName"),
							  result.getString("pwd"),
							  result.getInt("status")
						);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("getUser方法有异常");
		}
		return user;
	}
	
	public static void addUser(String uName,String upwd){
		String sql="insert into t_user(userName,pwd,status) values(?,?,?)";
		DataBase.update(sql, uName,upwd,1);
	}
	
	public static void saveSiFu(String sIP,String uName){
		int flag=getSaveSiFuFlag(sIP, uName);
		if(-1==flag){		//表中没有该私服
			String sql="insert into t_usersifu(userName,sIP,flag) values(?,?,1)";
			DataBase.update(sql, uName,sIP);		
		}else if(0==flag){		//表中有该私服，只是逻辑删除
			String sql="update t_usersifu set flag=1 where userName=? and sIP=? ";
			DataBase.update(sql, uName,sIP);
		}
	}
	
	private static int getSaveSiFuFlag(String sIP,String uName){
		String sql="select * from t_usersifu where userName=? and sIP=?";
		result=DataBase.query(sql, uName, sIP);
		int  flag=-1;
		try {
			while(result.next()){
				flag=result.getInt("flag");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}
	
	public static boolean hasSave(String sIP,String uName){
		String sql="select * from t_usersifu where userName=? and sIP=?";
		result=DataBase.query(sql, uName,sIP);
		boolean flag=false;
		try {
			while(result.next()){
				if(1==result.getInt("flag")){
					flag=true;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}
	
	public static List<TableImformation> getSaveSiFu(String uName){
		String sql="select * from t_sifu where sIP in(select sIP from t_usersifu where userName=? and flag=1)";
		result=DataBase.query(sql, uName);
		List<TableImformation> list=new ArrayList<TableImformation>();
		try {
			while(result.next()){
				list.add(new TableImformation(result.getString("sIP"),result.getString("sName"),
						 new Date(),result.getString("roadType"),
						 result.getString("detail"),result.getString("qq"),
						 result.getString("gameAddress")
						));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	public static void cancelSave(String uName,String sIP){
		String sql="update t_usersifu set flag=0 where userName=? and sIP=?";
		DataBase.update(sql, uName,sIP);
	}
	
	public static boolean canUpdateU(String newName){
		String sql="select * from t_user where userName=?";
		result=DataBase.query(sql, newName);
		boolean flag=true;
		try {
			if(result.next())
				flag=false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}
	
	public static void updateU(String oldName, String newName, String newpwd){
		String sql="update t_user set userName=?, pwd=? where userName=?";
		System.out.println(sql);
		DataBase.update(sql, newName, newpwd, oldName);
	}
	
	public static void updateU(String uName, String newpwd){
		String sql="update t_user set pwd=? where userName=?";
		System.out.println(sql);
		DataBase.update(sql, newpwd, uName);
	}
	
	public static void updateU(String uName, String newName, String newpwd, int newStatus){
		String sql="update t_user set userName=?, pwd=?, status=? where userName=?";
		DataBase.update(sql, newName, newpwd, newStatus, uName);
	}
	
	public static List<User> getUserList(){
		String sql="select * from t_user where flag<>1";
		result=DataBase.query(sql);
		List<User> list=new ArrayList<User>();
		try {
			while(result.next()){
				list.add(new User(result.getString("userName"),
								  result.getString("pwd"),
								  result.getInt("status")
						));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	public static void deleteUser(String key){
		String sql="update t_user set flag=1 where userName=?";
		DataBase.update(sql,key);
	}
	
	public static void deleteSiFu(String key){
		String sql="update t_sifu set flag=1 where sIP=?";
		DataBase.update(sql, key);
		cancelSave(key);
	}
	
	private static void cancelSave(String key){
		String sql="update t_usersifu set flag=0 where sIP=?";
		DataBase.update(sql, key);
	}
	
	public static void updateSiFu(String sIP, String newName, String newRoad, String newDetail){
		String sql="update t_sifu set sName=?, roadType=?, detail=? where sIP=?";
		DataBase.update(sql, newName, newRoad, newDetail, sIP);
	}
	
	public static boolean hasExit(String oldName, String newName){
		if(oldName.equals(newName))
			return false;
		String sql="select sName from t_sifu where sName=?";
		result=DataBase.query(sql, newName);
		boolean flag=false;
		try {
			if(result.next()){
				flag=true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("database hasexit");
		}
		return flag;
	}
	
	public static void addUser(String uName, String pwd, String status){
		String sql="insert into t_user(userName, pwd, status, flag) values(?,?,?,0)";
		DataBase.update(sql, uName,pwd,Integer.parseInt(status));
	}
	
}
