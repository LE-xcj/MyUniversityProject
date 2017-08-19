package DS_java_code;

import java.util.Scanner;

import DS_Class.Josephus_operate;

public class Josephus {
   public static void main(String args[]){
	   int length;
	   int index;
	   while(true){
		   @SuppressWarnings("resource")
		   Scanner input=new Scanner(System.in);
		   System.out.println("约瑟夫所在的队列的人数：");
		   length=input.nextInt();
		   Josephus_operate josephus=new Josephus_operate(length);		   
		   System.out.println("约瑟夫在队列中的位置：");
		   do{
			   index=input.nextInt();
			   if(index<=0||index>length){
				   System.out.println("输入的数据有误，请重新输入有效的位置！");
			   }
		   }while(index<=0||index>length);
		   int number=josephus.Which_number(index);
		   System.out.println("报数："+number+"可以让约瑟夫最后才死！");
	   }
   }
}
