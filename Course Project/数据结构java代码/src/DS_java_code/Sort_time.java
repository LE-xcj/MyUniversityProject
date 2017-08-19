package DS_java_code;

import DS_Class.Four_Sort;
import java.util.Scanner;

public class Sort_time {
	 static long B_average=0;
	 static long S_average=0;
	 static long I_average=0;
	 static long D_B_average=0;
	 static long D_S_average=0;
	 static long D_I_average=0;
	 static long O_E_average=0;
     public static void main(String args[]){   	 
		 Four_Sort sort_text=new Four_Sort();
    	 long start,end,result;
    	 int amount;       //测试的次数
    	 int flag;        
    	 @SuppressWarnings("resource")
		 Scanner input=new Scanner(System.in);
    	 System.out.println("请输入你要测试的组数：");
    	 amount=input.nextInt();
    	 flag=amount;
    	 while(amount>0){
    		 sort_text.Initialize();     //每次测试时数组里的数据就会再次更新
    		 //升序的冒泡排序
        	 start=System.currentTimeMillis();
        	 sort_text.Bubble_sort();
        	 end=System.currentTimeMillis();
        	 result=end-start;
        	 B_average+=result;
        	 //System.out.println("冒泡排序所需要的时间："+result+"毫秒");
        	 
        	 //升序的选择排序
        	 start=System.currentTimeMillis();
        	 sort_text.Select_sort();
        	 end=System.currentTimeMillis();
        	 result=end-start;
        	 S_average+=result;
        	 //System.out.println("选择排序所需要的时间："+result+"毫秒");
        	 
        	 //升序的插入排序
        	 start=System.currentTimeMillis();
        	 sort_text.Insert_sort();
        	 end=System.currentTimeMillis();
        	 result=end-start;
        	 I_average+=result;
        	 //System.out.println("插入排序所需要的时间："+result+"毫秒");
        	 
        	 //降序的冒泡排序
        	 start=System.currentTimeMillis();
        	 sort_text.Desc_Bubble_sort();
        	 end=System.currentTimeMillis();
        	 result=end-start;
        	 D_B_average+=result;
        	 //System.out.println("冒泡排序所需要的时间："+result+"毫秒");
        	 
        	 //降序的选择排序
        	 start=System.currentTimeMillis();
        	 sort_text.Desc_Select_sort();
        	 end=System.currentTimeMillis();
        	 result=end-start;
        	 D_S_average+=result;
        	 //System.out.println("选择排序所需要的时间："+result+"毫秒");
        	 
        	 //降序的插入排序
        	 start=System.currentTimeMillis();
        	 sort_text.Desc_Insert_sort();
        	 end=System.currentTimeMillis();
        	 result=end-start;
        	 D_I_average+=result;
        	 //System.out.println("插入排序所需要的时间："+result+"毫秒");
        	 
        	 //升序的奇偶排序
        	 start=System.currentTimeMillis();
        	 sort_text.oddEvent_sort();
        	 end=System.currentTimeMillis();
        	 result=end-start;
        	 O_E_average+=result;
        	 //System.out.println("奇偶排序所需要的时间："+result+"毫秒");
        	 
        	 --amount;
    	 }
    	 ThreeSort_averageTime(flag);  //计算每种排序算法的平均排序时间
     }
     public static void ThreeSort_averageTime(int amount){
    	 System.out.println("冒泡排序(升序)"+amount+"次，平均所需要的时间："+B_average/amount+"毫秒");
    	 System.out.println("选择排序(升序)"+amount+"次，平均所需要的时间："+S_average/amount+"毫秒");
    	 System.out.println("插入排序(升序)"+amount+"次，平均所需要的时间："+I_average/amount+"毫秒");
    	 System.out.println("冒泡排序(降序)"+amount+"次，平均所需要的时间："+D_B_average/amount+"毫秒");
    	 System.out.println("选择排序(降序)"+amount+"次，平均所需要的时间："+D_S_average/amount+"毫秒");
    	 System.out.println("插入排序(降序)"+amount+"次，平均所需要的时间："+D_I_average/amount+"毫秒");
    	 System.out.println("奇偶排序(升序)"+amount+"次，平均所需要的时间："+O_E_average/amount+"毫秒");
     }
}
