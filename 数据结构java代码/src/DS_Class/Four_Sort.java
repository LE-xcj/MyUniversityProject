package DS_Class;
public class Four_Sort {
	private final int length=55000;   //数组长度
	private int[] B_array;          //存冒泡的数组
	private int[] S_array;         //存选择的数组
	private int[] I_array;        //存插入的数组
	private int[] D_B_array;          
	private int[] D_S_array;         
	private int[] D_I_array;  
	private int[] array;
	public Four_Sort(){       //给数组分配内存空间
		B_array=new int[length];
		S_array=new int[length];
		I_array=new int[length];
		D_B_array=new int[length];          
		D_S_array=new int[length];         
		D_I_array=new int[length]; 
		array=new int[length];
	}
	public void Initialize(){         //初始化数组
		for(int i=0;i<length;++i){
			B_array[i]=(int)(Math.random()*(length)); //产生随机数
			S_array[i]=B_array[i];
			I_array[i]=B_array[i];
			D_B_array[i]=B_array[i];
			D_S_array[i]=B_array[i];
			D_I_array[i]=B_array[i];
			array[i]=B_array[i];
		}
	}
	public void Bubble_sort(){      //（升序）冒泡排序
		int temp;
		for(int out=length-1;out>0;--out){
			for(int in=0;in<out;++in){
				if(B_array[in+1]<B_array[in]){
					temp=B_array[in];
					B_array[in]=B_array[in+1];
					B_array[in+1]=temp;
				}
			}	
		}
	}
	public void Select_sort(){   //（升序）选择排序
		int temp;
		int min,in,out;
		for(out=0;out<length-1;++out){
			min=out;
			for(in=out+1;in<length;++in){
				if(S_array[in]<S_array[min])
					min=in;
			}
			temp=S_array[out];
			S_array[out]=S_array[min];
			S_array[min]=temp;
		}
	}
	public void Insert_sort(){    //（升序）插入排序
		int out,in;
		int temp;
		for(out=1;out<length;++out){
			temp=I_array[out];
			in=out;
			while(in>0&&I_array[in-1]>temp){
				I_array[in]=I_array[in-1];
				--in;
			}
			I_array[in]=temp;
		}
	}
	
	public void oddEvent_sort(){      //（升序）奇偶排序
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
	
	public void Desc_Bubble_sort(){     //（降序）冒泡排序
		int temp,out,in;
		for(out=length-1;out>0;--out){
			for(in=0;in<out;++in){
				if(D_B_array[in+1]>D_B_array[in]){
					temp=D_B_array[in+1];
					D_B_array[in+1]=D_B_array[in];
					D_B_array[in]=temp;
				}
			}
		}
	}
	public void Desc_Select_sort(){    //（降序）选择排序
		int temp,out,in;
		int max;
		for(out=0;out<length-1;++out){
			max=out;
			for(in=out+1;in<length;++in){
				if(D_S_array[in]>D_S_array[max]){
					max=in;
				}
			}
			temp=D_S_array[out];
			D_S_array[out]=D_S_array[max];
			D_S_array[max]=temp;
		}
	}
	public void Desc_Insert_sort(){      //（降序）插入排序
		int temp,out,in;
		for(out=1;out<length;++out){
			temp=D_I_array[out];
			in=out;
			while(in>0&&D_I_array[in-1]<temp){
				D_I_array[in]=D_I_array[in-1];
				--in;
			}
			D_I_array[in]=temp;
		}
	}
}
