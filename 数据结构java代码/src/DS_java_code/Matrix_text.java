package DS_java_code;

import java.util.Scanner;

import DS_Class.Matrix_Control;

public class Matrix_text {
	public static void main(String args[]){
   	  int flag;
   	  @SuppressWarnings("resource")
	  Scanner input=new Scanner(System.in);
   	  Matrix_Control control=new Matrix_Control();
   	  while(true){
   		  System.out.println("1����һ������Ĳ���");
   		  System.out.println("2���ڶ�������Ĳ���");
   		  System.out.println("3����������Ĳ���");
   		  flag=input.nextInt();
   		  switch(flag){
		  case 1:{
			   control.One_Matrix_Control(flag);
		     }
		  break;
		  case 2:{
			   control.One_Matrix_Control(flag);
		     }
		  break;
		  case 3:{
				control.Double_Matrix_Control();
		     }
   		  }
   	   }
    }
}
