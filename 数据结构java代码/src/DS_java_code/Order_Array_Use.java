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
    		System.out.println("1、插入数据");
    		System.out.println("2、查找数据");
    		System.out.println("3、删除数据");
    		System.out.println("4、显示数据");
    		System.out.println("5、数组反转");
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
    			System.out.println("找到了！位置："+array.find(value));
    			else
    				System.out.println("没有找到"+value+"这个数");
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
