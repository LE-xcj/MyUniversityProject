package User;

public class Friends {
	private final int Max_amountOfFriends=4;    //每个用户的好友人数上限
	private int Element;           //有多少个好友
	public Personl[] friends;     //朋友列表（以数组的形式储存）
	
	public Friends(){
		friends=new Personl[Max_amountOfFriends];
		Element=0;
	}
	
	//建立两个用户的朋友关系，注意：需要改动两个用户的朋友列表
	public void Build_Conectivity(Personl first,Personl second){
		first.myFriends.addFriends(second);
		second.myFriends.addFriends(first);
	}
	
	//斩断两个人的好友关系，同理
	public void Cut_Conectivity(Personl first,Personl second,int s_index){
		int f_index=getFriends_Index(first, second);
		first.myFriends.DeleteFriends(s_index);
		second.myFriends.DeleteFriends(f_index);
		
	}
	
	//删除朋友
	private void DeleteFriends(int index){
		friends[index]=null;
		--Element;
	}
	
	//添加朋友
	private void addFriends(Personl newfriend){
		for(int out=0;out<Max_amountOfFriends;++out){
			if(friends[out]==null){
				friends[out]=newfriend;
				++Element;
				return;
			}
		}
	}
	
	
	//返回朋友的人数
	public int AmountOf_friends(){
		return Element;
	}
	
	//判断朋友列表是否已经满
	public boolean IsFull(){
		return Element==Max_amountOfFriends;
	}
	
	//返回朋友在数组的下标索引
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
	
	//判断这个人是不是老朋友
	public boolean Is_oldFriend(String name){
		for(int out=0;out<Element;++out){
			if(friends[out]!=null)
				if(friends[out].getName().equals(name))
					return true;
		}
		return false;
	}
	
}
