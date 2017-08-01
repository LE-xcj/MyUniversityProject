package DS_Class;

import java.util.Scanner;

public class Superior_Sort_Operator {
	private Superior_Sort superior;
	private int length;
	Scanner input;
    public Superior_Sort_Operator(){
    	input=new Scanner(System.in);
    	System.out.println("������Ҫ��������ݹ�ģ��");
    	length=input.nextInt();
    	superior=new Superior_Sort(length);
    }
    
    public void Display_Shell_Sort(){
    	superior.Shell_Sort();
    }
    
    //�Ƚ�ϣ���������������֮���Ч�ʣ��ǻ��ڶ���ͬ���ݽ�������
    public void Compare_TwoSort_Eefficency(){
    	int count;
    	long Shell_time=0;
    	long Quick_time=0;
    	long begin,end,result;
    	System.out.println("������Ҫ���ԵĴ�����");
    	count=input.nextInt();
    	for(int i=0;i<count;++i){
    		superior.Initial();
    		//��¼ϣ����������Ҫ��ʱ��
    		begin=System.currentTimeMillis();
    		superior.Shell_Sort();
    		end=System.currentTimeMillis();
    		result=end-begin;
    		Shell_time+=result;
    		
    		//��¼������������Ҫ��ʱ��
    		begin=System.currentTimeMillis();
    		superior.Quick_Sort_Entry();
    		end=System.currentTimeMillis();
    		result=end-begin;
    		Quick_time+=result;
    	}
    	System.out.println("���ݹ�ģ��"+length);
    	System.out.println("ϣ������--����"+count+"��ƽ������Ҫ��ʱ�䣺"+Shell_time/count+"����");
    	System.out.println("��������--����"+count+"��ƽ������Ҫ��ʱ�䣺"+Quick_time/count+"����");
    }
    
    //��ʾ���������������
    public void Display_Quick(){
    	System.out.println("����ǰ�����ݷֲ���");
    	superior.Display_Quick();
    	System.out.println("�������������ݷֲ���");
    	superior.Quick_Sort_Entry();
    	superior.Display_Quick();
    }
    
    //�޸����ݹ�ģ
    public void Change_Length(){
    	System.out.println("�������µ����ݹ�ģ��");
    	length=input.nextInt();
    	superior=new Superior_Sort(length);
    	System.out.println("���óɹ���");
    }
}
