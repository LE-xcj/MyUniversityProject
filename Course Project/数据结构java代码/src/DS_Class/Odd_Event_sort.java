package DS_Class;

public class Odd_Event_sort {        //奇偶排序
       private int length=20;
       private int[] array;
       public Odd_Event_sort(){     //给数组分配内存空间s
    	   array=new int[length];
       }
       public void Initialize(){     //给数组赋值
    	   for(int i=0;i<length;++i){
    		   array[i]=(int)(Math.random()*(length));
    	   }
       }
       public void oddEvent_sort(){    //奇偶排序
    	   Boolean finish;
    	   int temp;
    	   while(true){
    		   finish=true;
    		   for(int i=1;i<length-1;i+=2){     //奇数下标搜
    			   if(array[i]>array[i+1]){
    				   temp=array[i];
    				   array[i]=array[i+1];
    				   array[i+1]=temp;
    				   finish=false;
    			   }
    		   }
    		   for(int i=0;i<length-1;i+=2){       //偶数下标搜
    			   if(array[i]>array[i+1]){
    				   temp=array[i];
    				   array[i]=array[i+1];
    				   array[i+1]=temp;
    				   finish=false;
    			   }
    		   }
    		   if(finish)
    			   break;
    	   }
       }
       public void Display(){        //显示数组的所有元素
    	   for(int i=0;i<length;++i)
    		   System.out.print(array[i]+" ");
    	   System.out.println();
       }
}
