package User;

import java.sql.*;



public class UserList {
		private Personl first;     //指向链表第一个用户
		public Personl last;      //指向链表最后一个用户
		private Personl current;
		private boolean Exit_This_User;    //用来判断有没有该用户
		
		private Connection connection;    //与数据库的连接器
		private ResultSet rs;        //用于接收“经过条件筛选查询”的从数据库返回的集合体
		private Statement statement;    //执行SQL语句
		
		private int amount;         //用来记录每个用户的朋友人数
		private int User_amount;      //记录用户链表中用户的数量
		
		//用户的ID，这个非常重要，因为要根据用户的ID来对数据库中每个用户的数据进行更新，这个ID是唯一且不能为空
		private int ID;           
		private final int Max_amountOfFriends=4;     //好友人数上限
		
		private String name;
		private String password;
		private String Interest;
		private String Degree;
		private String Location;
		private String Introduction;
		private String sql;
		
		//初始化所有数据成员
		public UserList(Connection c) {
			ID=0;
			amount=0;
			User_amount=0;
			connection=c;
			
			try {
				statement=connection.createStatement();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			rs=null;
			first=null;
			last=null;
			
			Initial_Personl_Imformation();	
			Initial_Personl_Friends();
			
			current=null;
			Exit_This_User=false;
		}
		
		
		//初始化用户的朋友列表
		private void Initial_Personl_Friends() {
			//从第一个
			Personl temp=first;
			Personl Friend;
			String Friend_Name;
			String index;
			
			try {
				rs=statement.executeQuery("select * from user_list order by ID");	
				//因为这个用户链表是根据数据库中用户所建立的，所以用户的顺序是一致的
				while(rs.next()){
					//从数据库中提取该用户的朋友人数，这样在这个用户初始化朋友列表中，就可以不用盲目的遍历了“朋友字段”
					amount=rs.getInt("Amount");
					for(int out=1;out<=amount;++out){
						
						//数据库“字段”名字
						Integer integer = new Integer(out);
						index="friend"+integer.toString();
						
						//提取该“字段”中的朋友名字
						Friend_Name=rs.getString(index);
						//从用户链表中搜索，返回该朋友的地址
						Friend=Find_Friend(Friend_Name);
						
						//要判断该字段是否储存朋友
						//“朋友”存在，就建立这个人与另一个人的朋友关系
						if(Friend!=null){
							//在添加之前，先判断这个朋友是否已经在自己的用户列表存在了
							//不然就会添加两次了，因为从数据库返回的集合体中遍历；
							if(!temp.myFriends.Is_oldFriend(Friend_Name)){
								//注意：这个Build_Conectivity的方法是建立两个人的朋友关系
								temp.myFriends.Build_Conectivity(temp, Friend);
							}
						}
					}	
					//轮到下一个用户
					temp=temp.nextPersonl;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			
		}

		//从数据库提取每个用户的个人信息，并对每个用户初始化个人信息，然后储存到用户链表中
		private void Initial_Personl_Imformation() {
			try {
				//从数据库中返回所有用户的集合体
				rs=statement.executeQuery("select * from user_list order by ID");
				Personl user;
				
				//从数据库中第一个用户开始遍历，直到最后一条记录为止
				while(rs.next()){
					//从数据库中提取所有用户的个人信息
					name=rs.getString("Name");
					password=rs.getString("Password");
					Interest=rs.getString("Interest");
					Degree=rs.getString("Degree");
					Location=rs.getString("Location");
					Introduction=rs.getString("Introduction");
					ID=rs.getInt("ID");
					
					//在内存中建立用户链表
					user=new Personl(name,password);
					//要注意这个链表结构中，是有一个指向第一个用户的变量first和指向最后一个用户的变量last，一定要初始化
					if(first==null){
						first=user;
						last=first;
					}else{
						last.nextPersonl=user;
						last=user;
					}
					//基于这个用户链表，设置每个用户的信息
					user.setInterest(Interest);
					user.setDegree(Degree);
					user.setLocation(Location);
					user.setSelf_Introduction(Introduction);
					user.setID(ID);
					
					//更新用户的人数
					++User_amount;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		//注册用户，将新的用户插入在链表尾部
		//从这里就要向数据库中插入新的记录（新的用户）
		public void Registor(String name,String password){
			Personl newPersonl=new Personl(name,password);
			last.nextPersonl=newPersonl;
			last=newPersonl;
			
			try {
				//更新用户人数（作为一个ID的值）
				++User_amount;
				
				Integer interger=new Integer(User_amount);
				String temp=interger.toString();
				
				//向数据库中插入新的记录：用户的ID、用户名、密码
				sql="insert into user_list(Name,Password,ID)values('"+name+"','"+password+"',"+temp+")";
				statement.executeUpdate(sql);
				last.setID(User_amount);
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
		//登录
		public Personl Login(String name,String password){
			Exit_This_User=false;
			current=first;
			
			while(current!=null){
				if(current.getName().equals(name)){
					//1、输入的用户名与密码成功匹配
					if(current.getPassword().equals(password)){
						return current;
					}
					//2、存在该用户，但是密码不匹配
					else{
						//就标志用户链表中有该用户
						Exit_This_User=true;
					}
										
				}
				current=current.nextPersonl;
			}
			//返回null就无非两种情况：1、有该用户但是密码不匹配；2不存在该用户
			return null;
		}
		
		//返回有没有该用户
		public boolean Exit(){
			return Exit_This_User;
		}
		
		//判断注册的用户是否与已经注册用户的用户名相同
		public boolean SameName(String name){
			current =first;
			while(current!=null){
				if(current.getName().equals(name))
					return true;
				current=current.nextPersonl;
			}
			return false;
		}
		
		//返回用户链表中第一个用户的地址
		public Personl First_User_Address(){
			return first;
		}
		
		//寻找朋友的地址，用于初始化用户的朋友列表
		private Personl Find_Friend(String Name){
			Personl temp=first;
			while(temp!=null){
				if(temp.getName().equals(Name))
					return temp;
				temp=temp.nextPersonl;
			}
			return null;
		}
		
		//在关闭程序或注销账号后，更新数据库的个人信息
		//如果是实时更新数据库信息，那么是很消耗资源的
		public void Update_User_list(){	
			Personl temp=first;
			int start=1;
			String ID;
			
			Integer integer;
			//从第一个用户开始遍历
			while(temp!=null){
				//ID字段
				integer=new Integer(start);
				ID=integer.toString();
				
				//从内存中提取更新的用户信息
				name=temp.getName();
				Interest=temp.getInterest();
				Degree=temp.getDegree();
				Location=temp.getLocation();
				Introduction=temp.getSelf_Introduction();
				
				//更新用户的用户名、兴趣、学历、位置与自我简介
				//这里定要用一个字符串变量储存这些各种子串组合在一起的最终结果，不要直接写在executeUpdate（）里面，会报错
				
				//在这里用户的ID就起着至关重要的作用：因为除了“ID”是不变的，其余信息都是可以更改的，
				//所以在更新数据库中每条记录的信息，就要通过这个ID找到应该更新的记录
				sql="update user_list set Name='"+name+"',Interest='"+
				Interest+"',Degree='"+Degree+"', Location='"+Location+
				"',Introduction='"+Introduction+"' where ID="+ID;
				
				try {
					//更新数据库中每条记录的数据
					statement.executeUpdate(sql);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				//更新该用户在数据库库中“朋友”字段中的信息，temp就是该用户
				Update_Friend_Name(temp);
				++start;		//作为用户的ID值，更新ID
				temp=temp.nextPersonl;			//下一用户
			}
		}
		
		//更新每一个用户的好友信息（根据用户的ID进行更新，只能根据用户的ID）
		private void Update_Friend_Name(Personl self){
			int ID=self.getID();      
			amount=self.myFriends.AmountOf_friends();    //朋友人数
			
			String culunm;    //数据库的子段索引
			String db_ID;     //用户的ID
			String db_amount;    //更新用户的好友数量
			String Friend_name;   //用户的朋友名
			
			Integer interger2;
			Integer integer;
			
			for(int index=1;index<=Max_amountOfFriends;++index){
				//朋友字段
				integer=new Integer(index);
				culunm="friend"+integer.toString();
				
				//String类型的ID，用于拼接SQL语句
				interger2=new Integer(ID);
				db_ID=interger2.toString();
				
				try{
					//先判断该用户的用户列表的这个位置有没有朋友
					
					//有，提取该朋友的用户名并更新
				if(self.myFriends.friends[index-1]!=null){	
					Friend_name=self.myFriends.friends[index-1].getName();
					sql="update user_list set "+culunm+"= '"+Friend_name+"' where ID="+db_ID;
					statement.executeUpdate(sql);
				}
				
					//没有，就将该字段的值赋值null
				else{
					sql="update user_list set "+culunm+"= 'null' where ID="+db_ID;
					statement.executeUpdate(sql);
				}
					//更新Amount字段（记录该用户的朋友人数）的值
					interger2=new Integer(amount);
					db_amount=interger2.toString();
					
					sql="update user_list set Amount = "+db_amount+" where ID="+db_ID;
					statement.executeUpdate(sql);
					
				}catch (SQLException e) {
					e.printStackTrace();
				}
				
			}
		}
		
		//在注册后向数据库插入新注册用户的记录
		public void Update_personl_Imformation_After_registor(String interest,String degree,String location,String introduction){
					String ID;
					Integer integer=new Integer(User_amount);
					ID=integer.toString();
					
					sql="update user_list set Interest='"+
					interest+"',Degree='"+degree+"', Location='"+location+
					"',Introduction='"+introduction+"' where ID="+ID;
					
					try {
						
						statement.executeUpdate(sql);
					} catch (SQLException e) {
						e.printStackTrace();
					}
		}
		
		
}
