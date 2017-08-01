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
      
      //��ʼ������
	  private void Initial(){
		int value;
		for(int i=0;i<length;++i){
			value=(int)(Math.random()*random);
			Array[i]=value;
			View_Array[i]=value;
		 }
	  }
	  
	  //���ַ������
	  public void Partition_Access(){
		  //����ֻ��һ��Ԫ�أ�ֱ�ӷ���
		  if(1==length){
			  return;
		  }
		  else{
			  partition();
		  }
		  Display();
	  }
	  
	  //���֣�����һ��
	  private void partition(){
		  //���������ұߵ�����Ϊ��Ŧ
		  int pivot=Array[length-1];
		  
		  //�����ƶ���ָ��
		  int Left_point=-1;
		  
		  int upper=length-1;
		  
		  //�����ƶ���ָ��
		  int Right_point=length-1;
		  
		  //�������ֻ�����������ͽ��бȽ�
		  if(2==length){
			  ++Left_point;
			  //������С����Ŧ����ô��ֱ�ӷ���
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
		  //���������ϵĻ���
		  else{
			  while(true){
				  //�����Ѱ�ұ���Ŧ�����
				  while(Left_point<length&&Array[++Left_point]<pivot);
				  //���ұ�Ѱ�ұ���ŦС����
				  while(Right_point>0&&Array[--Right_point]>pivot);
				  
				  //����ָ����������ֹͣ
				  if(Left_point>=Right_point){
					  break;
				  }
				  
				  else{
					  //����
					  Swap(Left_point,Right_point);
				  }
			  }
		  }	
		  //����Ŧ���ұ������һ��������λ��
		  Swap(Left_point,upper);		  
	  }
	  
	  //Ѱ������ֵ
	  public void Find_Middle_Value(){
		  //���ֻ��1����2��������ֱ�����
		  if(1==length||2==length){
			  System.out.println("��ֵ�ǣ�");
			  for(int i=0;i<length;++i){
				  System.out.print(Array[i]+" ");
			  }
			  System.out.println();
		  }
		  //ͨ�����֣�Ѱ������ֵ��������������Ϊ����һ����û�о����ڶ��λ��ֵģ�
		  else{
			  int center;
			  center=length/2;
			  //�����������˫���ģ��ͷ�����������ֵ
			  if(length%2==0){
				  int mid1=partition(0,length-1,center-1);
				  int mid2=partition(0,length-1,center);
				  System.out.println("��һ������ֵ��"+Array[mid1]);
				  System.out.println("�ڶ�������ֵ��"+Array[mid2]);
			  }
			  //����ͷ���һ������ֵ
			  else{
				  int mid_value=partition(0,length-1,center);
				  System.out.println("����ֵ��"+Array[mid_value]);
			  }
		  }
	  }
	  
	  //Ѱ������ֵ�Ļ���
	  private int partition(int lower,int upper,int center){
		  //�������ָ���ʼλ��
		  int Left_point=lower-1;
		  //�����ұ�ָ���ʼλ��
		  int Right_point=upper;
		  //ѡ�������������ߵ��Ǹ�����Ϊ��Ŧ
		  int pivot=Array[upper];
		  
			  while(true){
				  
				  while(Left_point<upper&&Array[++Left_point]<pivot);
				  
				  while(Right_point>lower&&Array[--Right_point]>pivot);
				  
				  if(Left_point>=Right_point){
					  break;
				  }				  
				  Swap(Left_point,Right_point);
			  }
			  //ע�⣺һ��Ҫ���ұ�����ĵ�һ��������Ŧ����λ�ã������м����������Ŧ
			  Swap(Left_point,upper);
			  
			  //����ұ������һ������λ�þ������������м�λ�ã���ô��ֱ�ӷ������λ�õ�����Ҳ����֮ǰ�Ѿ����н����˵���Ŧֵ��
			  //����ݹ鷽���Ļ�Ԫֵ
			  if(Left_point==center){
				  return Left_point;
			  }
			  //����͸����ұ������һ����λ�����ж�ѡȡ��һ�ߵ����������һ�λ���
			  else{
				  //��������������λ�õ��ұߣ���ô�Ͷ����λ�õ����������л��֣�Ҫ��ȥ��һ�λ��ֵ���Ŧ��
				  if(Left_point>center){
					  return partition(lower,Left_point-1,center);
				  }
				  //ͬ����������ھͶ��ұ�������л��֣�Ҳ��Ҫ��ȥ��һ�λ��ֵ���Ŧֵ��
				  else{
					  return partition(Left_point+1, upper, center);
				  }
			  }
	  }
	  
	  //����
	  private void Swap(int left, int right) {
		     int temp=Array[left];
		     Array[left]=Array[right];
		     Array[right]=temp;		     
	  }

	  //��ʾ�������һ������ֵ
	  public void Peek(){
		  System.out.print("���ұߵ�һ�����ǣ�");
		  System.out.println(Array[length-1]);
	  }
	  
	  //��ʾ���������
	  public void Display(){
		  for(int i=0;i<length;++i){
			  System.out.print(Array[i]+" ");
		  }
		  System.out.println();
	  }
	  
	  //��ʾ���������飬��Ϊ�˷�����֤��Ѱ������ֵ���ķ����Ƿ�ɹ�
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
