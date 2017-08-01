package DS_Class;

public class Partition {
      private static final int random = 99;
	  private int length;
      private int[] Array;
      private int[] View_Array;
      public Partition(int length){
    	  this.length=length;
    	  Array=new int[length];
    	  View_Array=new int[length];
    	  Initial();
      }
      
      //初始化数组
	  private void Initial(){
		int value;
		for(int i=0;i<length;++i){
			value=(int)(Math.random()*random);
			Array[i]=value;
			View_Array[i]=value;
		 }
	  }
	  
	  //划分方法入口
	  public void Partition_Access(){
		  //数组只有一个元素，直接返回
		  if(1==length){
			  return;
		  }
		  else{
			  partition();
		  }
		  Display();
	  }
	  
	  //划分，划分一次
	  private void partition(){
		  //以数组最右边的数作为枢纽
		  int pivot=Array[length-1];
		  
		  //向右移动的指针
		  int Left_point=-1;
		  
		  int upper=length-1;
		  
		  //向左移动的指针
		  int Right_point=length-1;
		  
		  //如果数组只有两个数，就进行比较
		  if(2==length){
			  ++Left_point;
			  //如果左边小于枢纽，那么就直接返回
			  if(Array[Left_point]<=pivot){
				  return;
			  }
			  else{
				  int temp=Array[Left_point];
				  Array[Left_point]=Array[upper];
				  Array[upper]=temp;
				  return;
			  }
		  }
		  //三个数以上的划分
		  else{
			  while(true){
				  //从左边寻找比枢纽大的数
				  while(Left_point<length&&Array[++Left_point]<pivot);
				  //从右边寻找比枢纽小的数
				  while(Right_point>0&&Array[--Right_point]>pivot);
				  
				  //两个指针相遇，就停止
				  if(Left_point>=Right_point){
					  break;
				  }
				  
				  else{
					  //交换
					  Swap(Left_point,Right_point);
				  }
			  }
		  }	
		  //将枢纽与右边数组第一个数交换位置
		  Swap(Left_point,upper);		  
	  }
	  
	  //寻找中心值
	  public void Find_Middle_Value(){
		  //如果只有1个或2个数，就直接输出
		  if(1==length||2==length){
			  System.out.println("中值是：");
			  for(int i=0;i<length;++i){
				  System.out.print(Array[i]+" ");
			  }
			  System.out.println();
		  }
		  //通过划分，寻找中心值，但不是排序；因为总有一半是没有经过第二次划分的；
		  else{
			  int center;
			  center=length/2;
			  //如果数据项是双数的，就返回两个中心值
			  if(length%2==0){
				  int mid1=partition(0,length-1,center-1);
				  int mid2=partition(0,length-1,center);
				  System.out.println("第一个中心值："+Array[mid1]);
				  System.out.println("第二个中心值："+Array[mid2]);
			  }
			  //否则就返回一个中心值
			  else{
				  int mid_value=partition(0,length-1,center);
				  System.out.println("中心值："+Array[mid_value]);
			  }
		  }
	  }
	  
	  //寻找中心值的划分
	  private int partition(int lower,int upper,int center){
		  //设置左边指针初始位置
		  int Left_point=lower-1;
		  //设置右边指针初始位置
		  int Right_point=upper;
		  //选择这段区域最左边的那个数作为枢纽
		  int pivot=Array[upper];
		  
			  while(true){
				  
				  while(Left_point<upper&&Array[++Left_point]<pivot);
				  
				  while(Right_point>lower&&Array[--Right_point]>pivot);
				  
				  if(Left_point>=Right_point){
					  break;
				  }				  
				  Swap(Left_point,Right_point);
			  }
			  //注意：一定要将右边数组的第一个数与枢纽交换位置，这样中间的数才是枢纽
			  Swap(Left_point,upper);
			  
			  //如果右边数组第一个数的位置就是整个数组中间位置，那么就直接返回这个位置的数（也就是之前已经进行交换了的枢纽值）
			  //这个递归方法的基元值
			  if(Left_point==center){
				  return Left_point;
			  }
			  //否则就根据右边数组第一数的位置来判断选取那一边的区域进行下一次划分
			  else{
				  //如果这个数在中心位置的右边，那么就对这个位置的左边区域进行划分（要除去上一次划分的枢纽）
				  if(Left_point>center){
					  return partition(lower,Left_point-1,center);
				  }
				  //同样，如果大于就对右边区域进行划分（也是要除去上一次划分的枢纽值）
				  else{
					  return partition(Left_point+1, upper, center);
				  }
			  }
	  }
	  
	  //交换
	  private void Swap(int left, int right) {
		     int temp=Array[left];
		     Array[left]=Array[right];
		     Array[right]=temp;		     
	  }

	  //显示数组最后一个数的值
	  public void Peek(){
		  System.out.print("最右边的一个数是：");
		  System.out.println(Array[length-1]);
	  }
	  
	  //显示数组的数据
	  public void Display(){
		  for(int i=0;i<length;++i){
			  System.out.print(Array[i]+" ");
		  }
		  System.out.println();
	  }
	  
	  //显示排序后的数组，是为了方便验证“寻找中心值”的方法是否成功
	  public void Sort_to_Show(){
		  int out,in,temp;
		  for(out=1;out<length;++out){
			  temp=View_Array[out];
			  in=out;
			  while(in>0&&View_Array[in-1]>temp){
				  View_Array[in]=View_Array[in-1];
				  --in;
			  }
			  View_Array[in]=temp;
		  }
		  for(out=0;out<length;++out){
			  System.out.print(View_Array[out]+" ");
		  }
		  System.out.println();
	  }
}
