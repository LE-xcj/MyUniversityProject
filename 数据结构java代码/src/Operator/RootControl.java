package Operator;

import java.sql.Connection;

import User.Personl;
import User.UserList;

public class RootControl {
	private UserList list;     //储存所有用户的链表，（以链表的形式储存）
	public Personl current;    //记录登录的用户
	public Personl previous;
	
	public RootControl(Connection connection){
		list=new UserList(connection);	
		current=null;
		previous=list.First_User_Address();
	}
	
	//判断是否能够成功登录
	public boolean Login(String name,String password){
		if(list.Login(name, password)!=null){
			current=list.Login(name, password);
			return true;
		}
		else{
			return false;
		}
	}
	
	//注册
	public boolean Registor(String name,String password){
		if(list.SameName(name)){
			return false;
		}
		list.Registor(name, password);
		return true;
	}
	
	//返回用户链表的地址
	public UserList listAddress(){
		return list;
	}
	
	//当有人登录了，那么此时的current就是登录用户，所以建立朋友关系时把current的值传进去
	public boolean Add_People(Personl newFriend){
		//在添加朋友之前，先判断“此时登录用户”与“新朋友”的好友人数是否已经达到上限了
		if(current.myFriends.IsFull()&&newFriend.myFriends.IsFull())
			return false;
		current.myFriends.Build_Conectivity(current, newFriend);
		return true;
	}
	
	//删除朋友
	public boolean Delete_Friends(int index){
		Personl second=current.myFriends.friends[index];
		current.myFriends.Cut_Conectivity(current, second, index);
		return true;
	}
	
	//根据朋友在朋友列表中的下标索引，找到该朋友的“地址”
	public Personl getFriend(int index){
		return current.myFriends.friends[index];
	}
	
	//搜索朋友，根据三个筛选条件进行筛选
	//也可以不筛选
	public Personl Search_NewFriends(boolean box1,boolean box2,boolean box3){
		//这个变量previous是用来记录上一次搜索到（符合标准）的陌生人的下一个人
		Personl temp=previous;
		
		//记录登录用户的兴趣、位置与学历
		String current_Interest=current.getInterest();
		String current_Location=current.getLocation();
		String current_Degree=current.getDegree();
		
		//记录陌生人的兴趣、位置与学历
		String temp_Interest;
		String temp_Location;
		String temp_Degree;
		
		String temp_name=temp.getName();
		//从用户链表遍历，如果当前这个用户“不是登录用户”且又不是“登录用户的朋友”就停止遍历
		//寻找陌生人
		while((temp!=null)&&(current.getName().equals(temp_name)||current.myFriends.Is_oldFriend(temp_name))){			
			temp=temp.nextPersonl;     //下一个用户
			if(temp!=null)
				//更新被搜索用户的用户名
				temp_name=temp.getName();
		}
		
		//找到陌生人
		//更新陌生人的信息，以便用于下面的筛选
		if(temp!=null){
			temp_Interest=temp.getInterest();
			temp_Location=temp.getLocation();
			temp_Degree=temp.getDegree();
			//更新previous，并指向当前陌生人的下一个用户
			previous=temp.nextPersonl;
		}
		//没有找到陌生人
		else{
			//令previous等于null是为了让UI层次能够根据这一条件结束搜索
			previous=null;
			return null;
		}
		
		//有陌生人就会执行到这
		//选中的，进行筛选，没有就不用；如果不符合筛选条件，就直接返回null，符合，就进行下一步的筛选，直到符合最后一个筛选条件
		//UI界面，选中第一个筛选条件；
		if(box1){
			if(current_Interest.equals(temp_Interest)==false)
				return null;
			if(box2){
				if(current_Location.equals(temp_Location)==false)
					return null;
			}
			if(box3){
				if(current_Degree.equals(temp_Degree)==false)
					return null;
			}
			return temp;
		}
		
		else{
			if(box2){
				if(current_Location.equals(temp_Location)==false)
					return null;
			}
			if(box3){
				if(current_Degree.equals(temp_Degree)==false)
					return null;
			}
			return temp;
		}
		
	}
	
	//重置previous的值，并指向用户链表中的第一个用户
	public void Reset_Previous(){
		previous=list.First_User_Address();
	}
	
}
