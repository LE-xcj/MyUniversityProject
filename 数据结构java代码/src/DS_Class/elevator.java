package DS_Class;

public class elevator {
//private int tottal_floor=10;
public	int current_floor=1;
   public void Up_floor(int floor){
    	if(floor>current_floor)
    	{
    		current_floor=floor;
    		open_Door(floor);
    	}
    	else{
    		open_Door(current_floor);
    	}
     }
   public void Down_floor(int floor){
    	if(floor<current_floor)
    	{
    		current_floor=floor;
    		open_Door(floor);
    	}
    	else
    	{
    		open_Door(current_floor);
    	}
    }
   public void open_Door(int floor){
	   System.out.print("���ţ���!");
	   System.out.println("������"+floor+"¥");
   }
   public void close_Door(int floor){
	   System.out.print("���ţ���!");
	   System.out.println("������"+floor+"¥");
   }
}
