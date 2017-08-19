package User;


public class Personl {
       private String UserName;
       private String Password;
       private String Interest;
       private String Degree;
       private String Location;
       private String Self_Introduction; 
       private int ID;
       public Friends myFriends;      //朋友
       public Personl nextPersonl;   //用户链表的基本构成元素
       public Personl(String name,String password){
    	   UserName=name;
    	   Password=password;
    	   Interest=null;
    	   Degree=null;
    	   Location=null;
    	   Self_Introduction=null;
    	   myFriends=new Friends();
    	   nextPersonl=null;
    	   ID=0;
       }
       
       public void setName(String name){
    	   UserName=name;
       }
       
       public String getName(){
    	   return UserName;
       }
       
       public void setInterest(String interest){
    	   Interest=interest;
       }
       
       public String getInterest(){
    	   return Interest;
       }
       
       public void setDegree(String degree){
    	   Degree=degree;
       }
       
       public String getDegree(){
    	   return Degree;
       }
       
       public void setLocation(String location){
    	   Location=location;  	   
       }     
       
       public String getLocation(){
    	   return Location;	   
       }
       
       public void setSelf_Introduction(String introduction){
    	   Self_Introduction=introduction;
       }
       
       public String getSelf_Introduction(){
    	   return Self_Introduction;
       }
       
       public String getPassword(){
    	   return Password;
       }
       
       public void setID(int id){
    	   ID=id;
       }
       
       public int getID(){
    	   return ID;
       }
            
}
