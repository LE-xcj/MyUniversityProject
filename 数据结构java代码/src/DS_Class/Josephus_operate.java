package DS_Class;

public class Josephus_operate {     //ʵ��Լɪ������
	   int length;     //���������ݵĸ��� ���ж��ٸ���̫�ˣ�
	   int count;     //������������¼��ɾ���ĸ�����������������
	   
	   public Josephus_operate(int length) {
		   this.length =length;
		   count=0;
	   }
	   
	   //-------------Լɪ������ĺ����㷨----------\\
	   public int Which_number(int number){
		   //����һ��ѭ������
		   Circle_list list;
		   int out,in;
		   //��1��ʼ���𣬲��ҡ�������һ���������ܹ���Լɪ����Ϊ���һ����ɱ����
		   for(out=1;;++out){
			   System.out.println("����"+out);
			   count=0;
			   list=new Circle_list();
			   //��ѭ���������20��������Ŵ�1��ʼֱ��20������20���ˣ�
			   for(in=length;in>=1;--in){
				   list.Insert(in);
			   }
			   //������ʼ�������������������һ��Link��current����ɱ�ͷ��һ��Link
			   //��һ��Link��dataΪ1�����ǣ����Ϊ1��
			   list.Initial();
			   //��ʼ��֤��ǰ������ܷ���Լɪ��������
			   while(true){
				   //��ʼ��������������2����Stepһ�Σ�����3����Step2��
					   for(int mid=1;mid<out;++mid){
						   list.Step();
					   }
					   //�ֵ�Լɪ�򱻡�ɱ����
					   if(list.current.data==number){
						   //���ǻ�Ҫ��֤�ǲ������һ��
						   //�����ж�count�Ƿ����length-1����ΪԼɪ��û��ɱ
						   if(count==length-1){
							   //�����������Լɪ������������
							   return out;
						   }
						   else{
							   //������ֹwhileѭ��
							   System.out.println("ɱ����"+number+","+out+"����������Ա���");
							   break;
						   }
					   }
					   //��ɱ����Щ�С�out������
					   else{
						  int temp=list.current.data;
						  list.Delete_current(); 
						  list.Josephus_Display();						  
						  System.out.print("----"+"ɱ��"+temp);
						  System.out.println();
						  ++count;
					   }
			   }
		   }
		//return 0;
	   }
}
