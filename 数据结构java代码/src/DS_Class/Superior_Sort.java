package DS_Class;

public class Superior_Sort {
    private int[] Shell_array;
    private int[] Quick_array;
    private int length;
    public Superior_Sort(int length){
    	this.length=length;
    	Shell_array=new int[length];
    	Quick_array=new int[length];
    }
    
    //给数组赋值
    public void Initial(){
    	int value;
    	for(int i=0;i<length;++i){
    		value=(int)(Math.random()*length);
    		Shell_array[i]=value;
    		Quick_array[i]=value;
    	}
    }
    
    //希尔排序
    public void Shell_Sort(){
    	//int count=1;
    	int gap=1;
    	int out,mid,in,temp;
    	//寻找最大的间隔
    	while(gap<=length/3){
    		gap=gap*3+1;
    	}
    	//直到间隔等于0表示该数组已经排好序 
//    	System.out.print("数据未开始排序的数据分布：");
//    	Display();
    	while(gap>0){
    		//从间隔的大开始对数组进行大幅度排序
    		//开始遍历小于当前间隔的数
			//System.out.println("间隔为："+gap);
    		for(out=0;out<gap;++out){
    			//开始以gap的间隔开始在整个范围大幅度排序
    			for(mid=out;mid<length;mid+=gap){
    				temp=Shell_array[mid];
    				in=mid;
    				//一定先要判断这个in是否在有效的范围内，也就是in-gap大于等于0
    				while(in>(gap-1)&&Shell_array[in-gap]>temp){
    					Shell_array[in]=Shell_array[in-gap];
    					in-=gap;
    				}
    				Shell_array[in]=temp;
    			}
//    			System.out.print("希尔排序第"+count+"趟排序：");
//    			++count;
//    			Display();    			
    		}
    		//更新间隔的数值
			gap=(gap-1)/3;
    	}
    }
    
    //快速排序的入口
    public void Quick_Sort_Entry(){
    	Quick_Sort(0,length-1);
    }
    
    //快速排序，我这里的快速排序就是“三数据取中值”版的快速排序
    private void Quick_Sort(int lower,int upper) {
    	//这个范围有多少个数
		int size=upper-lower+1;
		//如果数据个数少于3，那么就直接进行简单的排序
		if(size<=3){
			Normal_Sort(lower,upper);
		}
		else{
			//记录中值
			int key=Middle_Value_of_Three(lower, upper);
			//记录划分之后中值的位置
			int Middle_Index=Partition(lower, upper, key);
			//开始对左边区域的
			Quick_Sort(lower, Middle_Index-1);
			Quick_Sort(Middle_Index+1,upper);
		}
	}

    //三个数取中值
    private int Middle_Value_of_Three(int lower,int upper){
    	int mid=(lower+upper)/2;
    	//先对这三个数进行局部排序
    	if(Quick_array[lower]>Quick_array[mid])
    		Swap(lower, mid);
    	if(Quick_array[lower]>Quick_array[upper])
    		Swap(lower, upper);
    	if(Quick_array[mid]>Quick_array[upper])
    		Swap(mid, upper);
    	//最后将中值与这段区域的倒数第二个数交换位置
    	Swap(mid,upper-1);
    	//返回中值
    	return Quick_array[upper-1];
    }
    
    //对划分的区域进行比较与交换
    private int Partition(int Left_point,int Right_point,int key){
    	//记录这段区域中值的位置
    	int upper=Right_point-1;  
    	//重新定义右指针的位置（中值的位置）
    	Right_point-=1;
    	while(true){
    		
    		//从左边开始寻找比中值大的数值
    		while(Quick_array[++Left_point]<key);
    		
    		//从右边开始寻找比中值小的数值
    		while(Quick_array[--Right_point]>key);
    		
    		if(Left_point>=Right_point){
    			break;
    		}
    		else{
    			Swap(Left_point,Right_point);
    		}
    	}
    	//将中值与右数组第一个数的位置进行交换
    	Swap(Left_point,upper);
    	//返回中值的位置
    	return Left_point;
    }
    
    //对数据项小于3的区域进行简单比较与排序
	private void Normal_Sort(int lower,int upper) {
		int size=upper-lower+1;
		if(size<=1){
			return;
		}
		if(size==2){
			if(Quick_array[lower]>Quick_array[upper]){
				Swap(lower,upper);
				return;
			}
		}else{
			int mid=(lower+upper)/2;
			if(Quick_array[lower]>Quick_array[mid])
				Swap(lower, mid);
			if(Quick_array[lower]>Quick_array[upper])
				Swap(lower, upper);
			if(Quick_array[mid]>Quick_array[upper])
				Swap(mid, upper);
			return;
		}
	}

	//交换数据
	private void Swap(int first, int second) {
		int temp=Quick_array[first];
		Quick_array[first]=Quick_array[second];
		Quick_array[second]=temp;		
	}

	//显示希尔排序后的数据
	public void Display(){
    	for(int i=0;i<length;++i){
    		System.out.print(Shell_array[i]+" ");
    	}
    	System.out.println();
    }  
	
	//显示快速排序后的数据
	public void Display_Quick(){
    	for(int i=0;i<length;++i){
    		System.out.print(Quick_array[i]+" ");
    	}
    	System.out.println();
	}
    
}
