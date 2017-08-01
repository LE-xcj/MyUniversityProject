package User;

public class Friends {
	private final int Max_amountOfFriends=4;    //ÿ���û��ĺ�����������
	private int Element;           //�ж��ٸ�����
	public Personl[] friends;     //�����б����������ʽ���棩
	
	public Friends(){
		friends=new Personl[Max_amountOfFriends];
		Element=0;
	}
	
	//���������û������ѹ�ϵ��ע�⣺��Ҫ�Ķ������û��������б�
	public void Build_Conectivity(Personl first,Personl second){
		first.myFriends.addFriends(second);
		second.myFriends.addFriends(first);
	}
	
	//ն�������˵ĺ��ѹ�ϵ��ͬ��
	public void Cut_Conectivity(Personl first,Personl second,int s_index){
		int f_index=getFriends_Index(first, second);
		first.myFriends.DeleteFriends(s_index);
		second.myFriends.DeleteFriends(f_index);
		
	}
	
	//ɾ������
	private void DeleteFriends(int index){
		friends[index]=null;
		--Element;
	}
	
	//�������
	private void addFriends(Personl newfriend){
		for(int out=0;out<Max_amountOfFriends;++out){
			if(friends[out]==null){
				friends[out]=newfriend;
				++Element;
				return;
			}
		}
	}
	
	
	//�������ѵ�����
	public int AmountOf_friends(){
		return Element;
	}
	
	//�ж������б��Ƿ��Ѿ���
	public boolean IsFull(){
		return Element==Max_amountOfFriends;
	}
	
	//����������������±�����
	public int getFriends_Index(Personl first,Personl second){
		
		for(int out=0;out<Max_amountOfFriends;++out){
			if(second.myFriends.friends[out]!=null){
				if(second.myFriends.friends[out].getName().equals(first.getName())){
					return out;
				}
			}
		}
		return -1;
	}
	
	//�ж�������ǲ���������
	public boolean Is_oldFriend(String name){
		for(int out=0;out<Element;++out){
			if(friends[out]!=null)
				if(friends[out].getName().equals(name))
					return true;
		}
		return false;
	}
	
}
