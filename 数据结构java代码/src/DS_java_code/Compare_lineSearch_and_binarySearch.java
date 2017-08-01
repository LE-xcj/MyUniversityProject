package DS_java_code;

import java.util.Scanner;

public class Compare_lineSearch_and_binarySearch {
	 final static int length=50000000;
	 static int[] order_array=new int[length];
     public static void main(String args[]){  
    	 for(int i=0;i<length;++i){             //初始化数组
    		 order_array[i]=i;
    	 }
    	 @SuppressWarnings("resource")
		Scanner input=new Scanner(System.in);
    	 int value;
    	 long start,end,result;           //1、开始的时刻，2、结束的时刻，3、结果
    	 while(true){
    		 System.out.println("请输入你要查找的数：");
    		 value=input.nextInt();
    		 start=System.currentTimeMillis();
    		 //记录调用线性查找开始的时刻
    		 line_find(value);         //线性查找
    		 end=System.currentTimeMillis();
    		 //记录查找完的时刻
    		 result=end-start;   //查找所需的时间
    		 System.out.println("线性查找所需要的时间："+result+"毫秒");
    		 
    		 start=System.currentTimeMillis();
    		 binary_find(value);    //二分查找
    		 end=System.currentTimeMillis();
    		 result=end-start;
    		 System.out.println("二分查找所需要的时间："+result+"毫秒");
    	 }
     }
     public static int line_find(int value){    //线性查找
    	 for(int i=0;i<length;++i){
    		 if(value==order_array[i]){
    			 return i;
    		 }
    	 }
    	 return -1;
     }
     public static int binary_find(int value){   //二分查找
    	 int lower=0;
    	 int upper=length-1;
    	 int mid;
    	 while(lower<=upper){
    		 mid=(lower+upper)/2;
    		 if(order_array[mid]==value){
    			 return mid;
    		 }
    		 else{
    			 if(value>order_array[mid]){
    				 lower=mid+1;
    			 }
    			 else{
    				 upper=mid-1;
    			 }
    		 }
    	 }
    	 return -1;
     }
}
