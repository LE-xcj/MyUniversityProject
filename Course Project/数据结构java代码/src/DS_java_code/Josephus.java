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
		   System.out.println("Լɪ�����ڵĶ��е�������");
		   length=input.nextInt();
		   Josephus_operate josephus=new Josephus_operate(length);		   
		   System.out.println("Լɪ���ڶ����е�λ�ã�");
		   do{
			   index=input.nextInt();
			   if(index<=0||index>length){
				   System.out.println("�������������������������Ч��λ�ã�");
			   }
		   }while(index<=0||index>length);
		   int number=josephus.Which_number(index);
		   System.out.println("������"+number+"������Լɪ����������");
	   }
   }
}
