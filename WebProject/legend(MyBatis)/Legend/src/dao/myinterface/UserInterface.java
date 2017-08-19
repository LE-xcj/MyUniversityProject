package dao.myinterface;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import pojo.TableImformation;
import pojo.User;

public interface UserInterface {
	
	public List<TableImformation> getTableList();
	
	public User getUser(String uName);
	
	public void registor(User u);
	
	public void saveSiFu(@Param("sIP")String sIP,@Param("uName")String uName);
	
	public int hasSave(@Param("sIP")String sIP,@Param("uName")String uName);
	
	public List<TableImformation> getSaveSiFu(String uName);
	
	public void cancelSave(@Param("uName")String uName , @Param("sIP")String sIP);
	
	public int canUpdateU(String newName);
	
	public void updateU(@Param("oldName")String oldName,@Param("newName")String newName,@Param("newpwd")String newpwd);
	
}
