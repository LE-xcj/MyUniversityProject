package DS_Class;

public class Odd_Event_sort {        //��ż����
       private int length=20;
       private int[] array;
       public Odd_Event_sort(){     //����������ڴ�ռ�s
    	   array=new int[length];
       }
       public void Initialize(){     //�����鸳ֵ
    	   for(int i=0;i<length;++i){
    		   array[i]=(int)(Math.random()*(length));
    	   }
       }
       public void oddEvent_sort(){    //��ż����
    	   Boolean finish;
    	   int temp;
    	   while(true){
    		   finish=true;
    		   for(int i=1;i<length-1;i+=2){     //�����±���
    			   if(array[i]>array[i+1]){
    				   temp=array[i];
    				   array[i]=array[i+1];
    				   array[i+1]=temp;
    				   finish=false;
    			   }
    		   }
    		   for(int i=0;i<length-1;i+=2){       //ż���±���
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
       public void Display(){        //��ʾ���������Ԫ��
    	   for(int i=0;i<length;++i)
    		   System.out.print(array[i]+" ");
    	   System.out.println();
       }
}
