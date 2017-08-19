package dao.myinterface;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import pojo.Admin;
import pojo.TableImformation;
import pojo.User;

public interface AdminInterface {
	
	public Admin getAdmin(String aName);
	
	public void updateU(@Param("uName")String uName,@Param("newName")String newName,@Param("newpwd")String newpwd,@Param("newStatus")int newStatus);
	
	public List<User> getUserList();
	
	public void deleteUser(String key);
	
	public void deleteSiFu(String key);	
	
	public void deleteSave(String key);
	
	public void updateSiFu(@Param("sIP")String sIP,@Param("newName")String newName,@Param("newRoad")String newRoad,@Param("newDetail")String newDetail);
	
	public int hasExit(@Param("oldName")String oldName,@Param("newName")String newName);
	
	public void addUser(@Param("uName")String uName, @Param("pwd")String pwd,@Param("status")String status);
	
	public int exit(String sIP);
	
	public void addSiFu(TableImformation tb);
}
