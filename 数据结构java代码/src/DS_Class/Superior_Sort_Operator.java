package DS_Class;

import java.util.Scanner;

public class Superior_Sort_Operator {
	private Superior_Sort superior;
	private int length;
	Scanner input;
    public Superior_Sort_Operator(){
    	input=new Scanner(System.in);
    	System.out.println("请输入要排序的数据规模：");
    	length=input.nextInt();
    	superior=new Superior_Sort(length);
    }
    
    public void Display_Shell_Sort(){
    	superior.Shell_Sort();
    }
    
    //比较希尔排序与快速排序之间的效率（是基于对相同数据进行排序）
    public void Compare_TwoSort_Eefficency(){
    	int count;
    	long Shell_time=0;
    	long Quick_time=0;
    	long begin,end,result;
    	System.out.println("请输入要测试的次数：");
    	count=input.nextInt();
    	for(int i=0;i<count;++i){
    		superior.Initial();
    		//记录希尔排序所需要的时间
    		begin=System.currentTimeMillis();
    		superior.Shell_Sort();
    		end=System.currentTimeMillis();
    		result=end-begin;
    		Shell_time+=result;
    		
    		//记录快速排序所需要的时间
    		begin=System.currentTimeMillis();
    		superior.Quick_Sort_Entry();
    		end=System.currentTimeMillis();
    		result=end-begin;
    		Quick_time+=result;
    	}
    	System.out.println("数据规模："+length);
    	System.out.println("希尔排序--排序"+count+"次平均所需要的时间："+Shell_time/count+"毫秒");
    	System.out.println("快速排序--排序"+count+"次平均所需要的时间："+Quick_time/count+"毫秒");
    }
    
    //显示快速排序的排序结果
    public void Display_Quick(){
    	System.out.println("排序前的数据分布：");
    	superior.Display_Quick();
    	System.out.println("快速排序后的数据分布：");
    	superior.Quick_Sort_Entry();
    	superior.Display_Quick();
    }
    
    //修改数据规模
    public void Change_Length(){
    	System.out.println("请输入新的数据规模：");
    	length=input.nextInt();
    	superior=new Superior_Sort(length);
    	System.out.println("设置成功！");
    }
}
