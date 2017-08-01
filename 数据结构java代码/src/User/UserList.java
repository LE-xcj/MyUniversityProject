package User;

import java.sql.*;



public class UserList {
		private Personl first;     //ָ�������һ���û�
		public Personl last;      //ָ���������һ���û�
		private Personl current;
		private boolean Exit_This_User;    //�����ж���û�и��û�
		
		private Connection connection;    //�����ݿ��������
		private ResultSet rs;        //���ڽ��ա���������ɸѡ��ѯ���Ĵ����ݿⷵ�صļ�����
		private Statement statement;    //ִ��SQL���
		
		private int amount;         //������¼ÿ���û�����������
		private int User_amount;      //��¼�û��������û�������
		
		//�û���ID������ǳ���Ҫ����ΪҪ�����û���ID�������ݿ���ÿ���û������ݽ��и��£����ID��Ψһ�Ҳ���Ϊ��
		private int ID;           
		private final int Max_amountOfFriends=4;     //������������
		
		private String name;
		private String password;
		private String Interest;
		private String Degree;
		private String Location;
		private String Introduction;
		private String sql;
		
		//��ʼ���������ݳ�Ա
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
		
		
		//��ʼ���û��������б�
		private void Initial_Personl_Friends() {
			//�ӵ�һ��
			Personl temp=first;
			Personl Friend;
			String Friend_Name;
			String index;
			
			try {
				rs=statement.executeQuery("select * from user_list order by ID");	
				//��Ϊ����û������Ǹ������ݿ����û��������ģ������û���˳����һ�µ�
				while(rs.next()){
					//�����ݿ�����ȡ���û�����������������������û���ʼ�������б��У��Ϳ��Բ���äĿ�ı����ˡ������ֶΡ�
					amount=rs.getInt("Amount");
					for(int out=1;out<=amount;++out){
						
						//���ݿ⡰�ֶΡ�����
						Integer integer = new Integer(out);
						index="friend"+integer.toString();
						
						//��ȡ�á��ֶΡ��е���������
						Friend_Name=rs.getString(index);
						//���û����������������ظ����ѵĵ�ַ
						Friend=Find_Friend(Friend_Name);
						
						//Ҫ�жϸ��ֶ��Ƿ񴢴�����
						//�����ѡ����ڣ��ͽ������������һ���˵����ѹ�ϵ
						if(Friend!=null){
							//�����֮ǰ�����ж���������Ƿ��Ѿ����Լ����û��б������
							//��Ȼ�ͻ���������ˣ���Ϊ�����ݿⷵ�صļ������б�����
							if(!temp.myFriends.Is_oldFriend(Friend_Name)){
								//ע�⣺���Build_Conectivity�ķ����ǽ��������˵����ѹ�ϵ
								temp.myFriends.Build_Conectivity(temp, Friend);
							}
						}
					}	
					//�ֵ���һ���û�
					temp=temp.nextPersonl;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			
		}

		//�����ݿ���ȡÿ���û��ĸ�����Ϣ������ÿ���û���ʼ��������Ϣ��Ȼ�󴢴浽�û�������
		private void Initial_Personl_Imformation() {
			try {
				//�����ݿ��з��������û��ļ�����
				rs=statement.executeQuery("select * from user_list order by ID");
				Personl user;
				
				//�����ݿ��е�һ���û���ʼ������ֱ�����һ����¼Ϊֹ
				while(rs.next()){
					//�����ݿ�����ȡ�����û��ĸ�����Ϣ
					name=rs.getString("Name");
					password=rs.getString("Password");
					Interest=rs.getString("Interest");
					Degree=rs.getString("Degree");
					Location=rs.getString("Location");
					Introduction=rs.getString("Introduction");
					ID=rs.getInt("ID");
					
					//���ڴ��н����û�����
					user=new Personl(name,password);
					//Ҫע���������ṹ�У�����һ��ָ���һ���û��ı���first��ָ�����һ���û��ı���last��һ��Ҫ��ʼ��
					if(first==null){
						first=user;
						last=first;
					}else{
						last.nextPersonl=user;
						last=user;
					}
					//��������û���������ÿ���û�����Ϣ
					user.setInterest(Interest);
					user.setDegree(Degree);
					user.setLocation(Location);
					user.setSelf_Introduction(Introduction);
					user.setID(ID);
					
					//�����û�������
					++User_amount;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		//ע���û������µ��û�����������β��
		//�������Ҫ�����ݿ��в����µļ�¼���µ��û���
		public void Registor(String name,String password){
			Personl newPersonl=new Personl(name,password);
			last.nextPersonl=newPersonl;
			last=newPersonl;
			
			try {
				//�����û���������Ϊһ��ID��ֵ��
				++User_amount;
				
				Integer interger=new Integer(User_amount);
				String temp=interger.toString();
				
				//�����ݿ��в����µļ�¼���û���ID���û���������
				sql="insert into user_list(Name,Password,ID)values('"+name+"','"+password+"',"+temp+")";
				statement.executeUpdate(sql);
				last.setID(User_amount);
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
		//��¼
		public Personl Login(String name,String password){
			Exit_This_User=false;
			current=first;
			
			while(current!=null){
				if(current.getName().equals(name)){
					//1��������û���������ɹ�ƥ��
					if(current.getPassword().equals(password)){
						return current;
					}
					//2�����ڸ��û����������벻ƥ��
					else{
						//�ͱ�־�û��������и��û�
						Exit_This_User=true;
					}
										
				}
				current=current.nextPersonl;
			}
			//����null���޷����������1���и��û��������벻ƥ�䣻2�����ڸ��û�
			return null;
		}
		
		//������û�и��û�
		public boolean Exit(){
			return Exit_This_User;
		}
		
		//�ж�ע����û��Ƿ����Ѿ�ע���û����û�����ͬ
		public boolean SameName(String name){
			current =first;
			while(current!=null){
				if(current.getName().equals(name))
					return true;
				current=current.nextPersonl;
			}
			return false;
		}
		
		//�����û������е�һ���û��ĵ�ַ
		public Personl First_User_Address(){
			return first;
		}
		
		//Ѱ�����ѵĵ�ַ�����ڳ�ʼ���û��������б�
		private Personl Find_Friend(String Name){
			Personl temp=first;
			while(temp!=null){
				if(temp.getName().equals(Name))
					return temp;
				temp=temp.nextPersonl;
			}
			return null;
		}
		
		//�ڹرճ����ע���˺ź󣬸������ݿ�ĸ�����Ϣ
		//�����ʵʱ�������ݿ���Ϣ����ô�Ǻ�������Դ��
		public void Update_User_list(){	
			Personl temp=first;
			int start=1;
			String ID;
			
			Integer integer;
			//�ӵ�һ���û���ʼ����
			while(temp!=null){
				//ID�ֶ�
				integer=new Integer(start);
				ID=integer.toString();
				
				//���ڴ�����ȡ���µ��û���Ϣ
				name=temp.getName();
				Interest=temp.getInterest();
				Degree=temp.getDegree();
				Location=temp.getLocation();
				Introduction=temp.getSelf_Introduction();
				
				//�����û����û�������Ȥ��ѧ����λ�������Ҽ��
				//���ﶨҪ��һ���ַ�������������Щ�����Ӵ������һ������ս������Ҫֱ��д��executeUpdate�������棬�ᱨ��
				
				//�������û���ID������������Ҫ�����ã���Ϊ���ˡ�ID���ǲ���ģ�������Ϣ���ǿ��Ը��ĵģ�
				//�����ڸ������ݿ���ÿ����¼����Ϣ����Ҫͨ�����ID�ҵ�Ӧ�ø��µļ�¼
				sql="update user_list set Name='"+name+"',Interest='"+
				Interest+"',Degree='"+Degree+"', Location='"+Location+
				"',Introduction='"+Introduction+"' where ID="+ID;
				
				try {
					//�������ݿ���ÿ����¼������
					statement.executeUpdate(sql);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				//���¸��û������ݿ���С����ѡ��ֶ��е���Ϣ��temp���Ǹ��û�
				Update_Friend_Name(temp);
				++start;		//��Ϊ�û���IDֵ������ID
				temp=temp.nextPersonl;			//��һ�û�
			}
		}
		
		//����ÿһ���û��ĺ�����Ϣ�������û���ID���и��£�ֻ�ܸ����û���ID��
		private void Update_Friend_Name(Personl self){
			int ID=self.getID();      
			amount=self.myFriends.AmountOf_friends();    //��������
			
			String culunm;    //���ݿ���Ӷ�����
			String db_ID;     //�û���ID
			String db_amount;    //�����û��ĺ�������
			String Friend_name;   //�û���������
			
			Integer interger2;
			Integer integer;
			
			for(int index=1;index<=Max_amountOfFriends;++index){
				//�����ֶ�
				integer=new Integer(index);
				culunm="friend"+integer.toString();
				
				//String���͵�ID������ƴ��SQL���
				interger2=new Integer(ID);
				db_ID=interger2.toString();
				
				try{
					//���жϸ��û����û��б�����λ����û������
					
					//�У���ȡ�����ѵ��û���������
				if(self.myFriends.friends[index-1]!=null){	
					Friend_name=self.myFriends.friends[index-1].getName();
					sql="update user_list set "+culunm+"= '"+Friend_name+"' where ID="+db_ID;
					statement.executeUpdate(sql);
				}
				
					//û�У��ͽ����ֶε�ֵ��ֵnull
				else{
					sql="update user_list set "+culunm+"= 'null' where ID="+db_ID;
					statement.executeUpdate(sql);
				}
					//����Amount�ֶΣ���¼���û���������������ֵ
					interger2=new Integer(amount);
					db_amount=interger2.toString();
					
					sql="update user_list set Amount = "+db_amount+" where ID="+db_ID;
					statement.executeUpdate(sql);
					
				}catch (SQLException e) {
					e.printStackTrace();
				}
				
			}
		}
		
		//��ע��������ݿ������ע���û��ļ�¼
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
