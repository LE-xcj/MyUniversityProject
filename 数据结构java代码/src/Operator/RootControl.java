package Operator;

import java.sql.Connection;

import User.Personl;
import User.UserList;

public class RootControl {
	private UserList list;     //���������û������������������ʽ���棩
	public Personl current;    //��¼��¼���û�
	public Personl previous;
	
	public RootControl(Connection connection){
		list=new UserList(connection);	
		current=null;
		previous=list.First_User_Address();
	}
	
	//�ж��Ƿ��ܹ��ɹ���¼
	public boolean Login(String name,String password){
		if(list.Login(name, password)!=null){
			current=list.Login(name, password);
			return true;
		}
		else{
			return false;
		}
	}
	
	//ע��
	public boolean Registor(String name,String password){
		if(list.SameName(name)){
			return false;
		}
		list.Registor(name, password);
		return true;
	}
	
	//�����û�����ĵ�ַ
	public UserList listAddress(){
		return list;
	}
	
	//�����˵�¼�ˣ���ô��ʱ��current���ǵ�¼�û������Խ������ѹ�ϵʱ��current��ֵ����ȥ
	public boolean Add_People(Personl newFriend){
		//���������֮ǰ�����жϡ���ʱ��¼�û����롰�����ѡ��ĺ��������Ƿ��Ѿ��ﵽ������
		if(current.myFriends.IsFull()&&newFriend.myFriends.IsFull())
			return false;
		current.myFriends.Build_Conectivity(current, newFriend);
		return true;
	}
	
	//ɾ������
	public boolean Delete_Friends(int index){
		Personl second=current.myFriends.friends[index];
		current.myFriends.Cut_Conectivity(current, second, index);
		return true;
	}
	
	//���������������б��е��±��������ҵ������ѵġ���ַ��
	public Personl getFriend(int index){
		return current.myFriends.friends[index];
	}
	
	//�������ѣ���������ɸѡ��������ɸѡ
	//Ҳ���Բ�ɸѡ
	public Personl Search_NewFriends(boolean box1,boolean box2,boolean box3){
		//�������previous��������¼��һ�������������ϱ�׼����İ���˵���һ����
		Personl temp=previous;
		
		//��¼��¼�û�����Ȥ��λ����ѧ��
		String current_Interest=current.getInterest();
		String current_Location=current.getLocation();
		String current_Degree=current.getDegree();
		
		//��¼İ���˵���Ȥ��λ����ѧ��
		String temp_Interest;
		String temp_Location;
		String temp_Degree;
		
		String temp_name=temp.getName();
		//���û���������������ǰ����û������ǵ�¼�û������ֲ��ǡ���¼�û������ѡ���ֹͣ����
		//Ѱ��İ����
		while((temp!=null)&&(current.getName().equals(temp_name)||current.myFriends.Is_oldFriend(temp_name))){			
			temp=temp.nextPersonl;     //��һ���û�
			if(temp!=null)
				//���±������û����û���
				temp_name=temp.getName();
		}
		
		//�ҵ�İ����
		//����İ���˵���Ϣ���Ա����������ɸѡ
		if(temp!=null){
			temp_Interest=temp.getInterest();
			temp_Location=temp.getLocation();
			temp_Degree=temp.getDegree();
			//����previous����ָ��ǰİ���˵���һ���û�
			previous=temp.nextPersonl;
		}
		//û���ҵ�İ����
		else{
			//��previous����null��Ϊ����UI����ܹ�������һ������������
			previous=null;
			return null;
		}
		
		//��İ���˾ͻ�ִ�е���
		//ѡ�еģ�����ɸѡ��û�оͲ��ã����������ɸѡ��������ֱ�ӷ���null�����ϣ��ͽ�����һ����ɸѡ��ֱ���������һ��ɸѡ����
		//UI���棬ѡ�е�һ��ɸѡ������
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
	
	//����previous��ֵ����ָ���û������еĵ�һ���û�
	public void Reset_Previous(){
		previous=list.First_User_Address();
	}
	
}
