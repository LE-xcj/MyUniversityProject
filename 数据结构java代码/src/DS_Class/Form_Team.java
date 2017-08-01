package DS_Class;

public class Form_Team {
     private char[] Array;
     private int All_amount,Team_amount;
     public Form_Team(int all,int team){
    	 Array=new char[all];
    	 All_amount=all;
    	 Team_amount=team;
    	 Initial();
     }
	private void Initial() {
		char temp='A';
		for(int i=0;i<All_amount;++i){
			Array[i]=(char) (temp+i);
		}
	}
	
	public void ShowTeam(int current,String str,int count){
		if(Team_amount==count){
			System.out.println(str);
			return;
		}
		else{
			for(int i=current;i<All_amount;++i){
				ShowTeam(i+1,str+Array[i]+" ",count+1);
			}
		}
	}
	
}
