package DS_java_code;

import java.util.Scanner;

import DS_Class.Order_Array;

public class Order_Array_Use {
    //@SuppressWarnings("unused")
	public static void main(String args[]){
    	int length;
    	int flag;
    	int value;
    	@SuppressWarnings("resource")
		Scanner input=new Scanner(System.in);
    	length=input.nextInt();
    	Order_Array array=new Order_Array(length);
    	while(true){
    		System.out.println("1����������");
    		System.out.println("2����������");
    		System.out.println("3��ɾ������");
    		System.out.println("4����ʾ����");
    		System.out.println("5�����鷴ת");
    		flag=input.nextInt();
    		switch(flag){
    		case 1:{
    			value=input.nextInt();
    			array.Insert(value);
    		}
    		break;
    		case 2:{
    			value=input.nextInt();
    			if(array.find(value)!=-1)
    			System.out.println("�ҵ��ˣ�λ�ã�"+array.find(value));
    			else
    				System.out.println("û���ҵ�"+value+"�����");
    		}
    		break;
    		case 3:{
    			value=input.nextInt();
    			array.Delete(value);
    		}
    		break;
    		case 4:{
    			array.Display();
    		}
    		break;
    		case 5:{
    			array.Display_Reserve();
    			array.Display();
    		}
    	  } 
    	}
    }
}
