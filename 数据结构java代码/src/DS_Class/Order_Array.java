package DS_Class;

public class Order_Array {                //有序数组
	private int[] Number_Array;           
	private int number_count;             //数组含有的元素个数
	private int array_length;            //数组的长度
    public Order_Array(int length) {          //初始化数组
		Number_Array=new int[length];
		array_length=length;
		number_count=0;
	}
    public void Insert(int value){            //插入元素
		  if(array_length==number_count){        //判断数组是否满
			  //自动扩容
			  AutoExpend();
			  System.out.println("错误！容器空间不足！自动为你扩容，请重新输入数据！");
		  }
		  else{
    	  int temp=number_count;
    	  for(int i=0;i<number_count;++i){
    		  if(value<Number_Array[i]){        
    			  //找到第一个大于插入数的元素的下标
    				  temp=i;             //记录下标
    				  for(int j=number_count;j>i;--j){ 
    					  //往后移，给插入数腾出位置
    					  Number_Array[j]=Number_Array[j-1];
    				  }
    				  break;    //结束
    		  }
    	  }
		  Number_Array[temp]=value;    //如果数组里没有元素大于插入数，就直接插入最后
		  ++number_count;        //元素总数加一
		  }
      }
      public void AutoExpend(){     //自动扩容
    	  int Temp_Array[]=new int[array_length];  //临时数组
    	  array_length+=10;   //数组容量加十
    	  for(int i=0;i<number_count;++i){   //先将元素拷贝给临时数组
    		  Temp_Array[i]=Number_Array[i];
    	  }
    	  Number_Array=new int[array_length];   
    	  //再讲临时数组里的元素拷贝给扩容的Number_Array数组
    	  for(int i=0;i<number_count;++i){
    		  Number_Array[i]=Temp_Array[i];
    	  }
      }
      public int find(int value){   //查找元素
       	  int lower=0;
    	  int upper=number_count;
    	  int mid;
    	  while(lower<=upper){    //二分查找
    		  mid=(lower+upper)/2;
    		  if(value==Number_Array[mid]){
    			  return mid;
    		  }
    		  else{
    			  if(value>Number_Array[mid]){
    				  lower=mid+1;
    			  }
    			  else{
    				  upper=mid-1;
    			  }
    		  }
    	  }
		return -1;
      }
      public void Delete(int value){       //删除元素
    	  int index=find(value);      //先找到要找的元素
    	  if(index!=-1){           //如果元素存在
    		  for(int i=index;i<number_count-1;++i){
    			  Number_Array[i]=Number_Array[i+1];
    		  }
    		  --number_count;
    	  }
    	  else{      //元素不存在
    		  System.out.println("没有"+value+"这个数，无法删除！");
    	  }
      }
      public void Display(){   //显示数组里的每一个元素
    	  for(int i=0;i<number_count;++i){
    		  System.out.print(Number_Array[i]+" ");
    	  }
    	  System.out.println();
      }
      public void Display_Reserve(){    //数组逆序   	  
    	  int temp;   	  
    	  for(int i=0;i<number_count/2;++i){
    		  temp=Number_Array[i];
    		  Number_Array[i]=Number_Array[number_count-i-1];
    		  Number_Array[number_count-i-1]=temp;   		  
    	  }
    	  
      }
}
