package DS_Class;

public class Order_Array {                //��������
	private int[] Number_Array;           
	private int number_count;             //���麬�е�Ԫ�ظ���
	private int array_length;            //����ĳ���
    public Order_Array(int length) {          //��ʼ������
		Number_Array=new int[length];
		array_length=length;
		number_count=0;
	}
    public void Insert(int value){            //����Ԫ��
		  if(array_length==number_count){        //�ж������Ƿ���
			  //�Զ�����
			  AutoExpend();
			  System.out.println("���������ռ䲻�㣡�Զ�Ϊ�����ݣ��������������ݣ�");
		  }
		  else{
    	  int temp=number_count;
    	  for(int i=0;i<number_count;++i){
    		  if(value<Number_Array[i]){        
    			  //�ҵ���һ�����ڲ�������Ԫ�ص��±�
    				  temp=i;             //��¼�±�
    				  for(int j=number_count;j>i;--j){ 
    					  //�����ƣ����������ڳ�λ��
    					  Number_Array[j]=Number_Array[j-1];
    				  }
    				  break;    //����
    		  }
    	  }
		  Number_Array[temp]=value;    //���������û��Ԫ�ش��ڲ���������ֱ�Ӳ������
		  ++number_count;        //Ԫ��������һ
		  }
      }
      public void AutoExpend(){     //�Զ�����
    	  int Temp_Array[]=new int[array_length];  //��ʱ����
    	  array_length+=10;   //����������ʮ
    	  for(int i=0;i<number_count;++i){   //�Ƚ�Ԫ�ؿ�������ʱ����
    		  Temp_Array[i]=Number_Array[i];
    	  }
    	  Number_Array=new int[array_length];   
    	  //�ٽ���ʱ�������Ԫ�ؿ��������ݵ�Number_Array����
    	  for(int i=0;i<number_count;++i){
    		  Number_Array[i]=Temp_Array[i];
    	  }
      }
      public int find(int value){   //����Ԫ��
       	  int lower=0;
    	  int upper=number_count;
    	  int mid;
    	  while(lower<=upper){    //���ֲ���
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
      public void Delete(int value){       //ɾ��Ԫ��
    	  int index=find(value);      //���ҵ�Ҫ�ҵ�Ԫ��
    	  if(index!=-1){           //���Ԫ�ش���
    		  for(int i=index;i<number_count-1;++i){
    			  Number_Array[i]=Number_Array[i+1];
    		  }
    		  --number_count;
    	  }
    	  else{      //Ԫ�ز�����
    		  System.out.println("û��"+value+"��������޷�ɾ����");
    	  }
      }
      public void Display(){   //��ʾ�������ÿһ��Ԫ��
    	  for(int i=0;i<number_count;++i){
    		  System.out.print(Number_Array[i]+" ");
    	  }
    	  System.out.println();
      }
      public void Display_Reserve(){    //��������   	  
    	  int temp;   	  
    	  for(int i=0;i<number_count/2;++i){
    		  temp=Number_Array[i];
    		  Number_Array[i]=Number_Array[number_count-i-1];
    		  Number_Array[number_count-i-1]=temp;   		  
    	  }
    	  
      }
}
