package DS_java_code;


import java.util.Scanner;

import DS_Class.elevator;
public class elevator_User {
public static void main(String args[]){
	elevator e1=new elevator();
	@SuppressWarnings("resource")
	Scanner input=new Scanner(System.in);
	int input_floor;
	String up_Down;
	while(true){
	  	System.out.print("● or ");
	  	System.out.println("◎");
	  	up_Down=input.next();
	  	System.out.println("1、2、3、4、5、6、7、8、9、10");
	  	input_floor=input.nextInt();
	  	if(up_Down.equals("●")){
	  		e1.Up_floor(input_floor);
	  	}
	  	else{
	  		e1.Down_floor(input_floor);
	  	}
	 }
   }
}
