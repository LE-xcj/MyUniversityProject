package DS_java_code;

import DS_Class.Four_Sort;
import java.util.Scanner;

public class Sort_time {
	 static long B_average=0;
	 static long S_average=0;
	 static long I_average=0;
	 static long D_B_average=0;
	 static long D_S_average=0;
	 static long D_I_average=0;
	 static long O_E_average=0;
     public static void main(String args[]){   	 
		 Four_Sort sort_text=new Four_Sort();
    	 long start,end,result;
    	 int amount;       //���ԵĴ���
    	 int flag;        
    	 @SuppressWarnings("resource")
		 Scanner input=new Scanner(System.in);
    	 System.out.println("��������Ҫ���Ե�������");
    	 amount=input.nextInt();
    	 flag=amount;
    	 while(amount>0){
    		 sort_text.Initialize();     //ÿ�β���ʱ����������ݾͻ��ٴθ���
    		 //�����ð������
        	 start=System.currentTimeMillis();
        	 sort_text.Bubble_sort();
        	 end=System.currentTimeMillis();
        	 result=end-start;
        	 B_average+=result;
        	 //System.out.println("ð����������Ҫ��ʱ�䣺"+result+"����");
        	 
        	 //�����ѡ������
        	 start=System.currentTimeMillis();
        	 sort_text.Select_sort();
        	 end=System.currentTimeMillis();
        	 result=end-start;
        	 S_average+=result;
        	 //System.out.println("ѡ����������Ҫ��ʱ�䣺"+result+"����");
        	 
        	 //����Ĳ�������
        	 start=System.currentTimeMillis();
        	 sort_text.Insert_sort();
        	 end=System.currentTimeMillis();
        	 result=end-start;
        	 I_average+=result;
        	 //System.out.println("������������Ҫ��ʱ�䣺"+result+"����");
        	 
        	 //�����ð������
        	 start=System.currentTimeMillis();
        	 sort_text.Desc_Bubble_sort();
        	 end=System.currentTimeMillis();
        	 result=end-start;
        	 D_B_average+=result;
        	 //System.out.println("ð����������Ҫ��ʱ�䣺"+result+"����");
        	 
        	 //�����ѡ������
        	 start=System.currentTimeMillis();
        	 sort_text.Desc_Select_sort();
        	 end=System.currentTimeMillis();
        	 result=end-start;
        	 D_S_average+=result;
        	 //System.out.println("ѡ����������Ҫ��ʱ�䣺"+result+"����");
        	 
        	 //����Ĳ�������
        	 start=System.currentTimeMillis();
        	 sort_text.Desc_Insert_sort();
        	 end=System.currentTimeMillis();
        	 result=end-start;
        	 D_I_average+=result;
        	 //System.out.println("������������Ҫ��ʱ�䣺"+result+"����");
        	 
        	 //�������ż����
        	 start=System.currentTimeMillis();
        	 sort_text.oddEvent_sort();
        	 end=System.currentTimeMillis();
        	 result=end-start;
        	 O_E_average+=result;
        	 //System.out.println("��ż��������Ҫ��ʱ�䣺"+result+"����");
        	 
        	 --amount;
    	 }
    	 ThreeSort_averageTime(flag);  //����ÿ�������㷨��ƽ������ʱ��
     }
     public static void ThreeSort_averageTime(int amount){
    	 System.out.println("ð������(����)"+amount+"�Σ�ƽ������Ҫ��ʱ�䣺"+B_average/amount+"����");
    	 System.out.println("ѡ������(����)"+amount+"�Σ�ƽ������Ҫ��ʱ�䣺"+S_average/amount+"����");
    	 System.out.println("��������(����)"+amount+"�Σ�ƽ������Ҫ��ʱ�䣺"+I_average/amount+"����");
    	 System.out.println("ð������(����)"+amount+"�Σ�ƽ������Ҫ��ʱ�䣺"+D_B_average/amount+"����");
    	 System.out.println("ѡ������(����)"+amount+"�Σ�ƽ������Ҫ��ʱ�䣺"+D_S_average/amount+"����");
    	 System.out.println("��������(����)"+amount+"�Σ�ƽ������Ҫ��ʱ�䣺"+D_I_average/amount+"����");
    	 System.out.println("��ż����(����)"+amount+"�Σ�ƽ������Ҫ��ʱ�䣺"+O_E_average/amount+"����");
     }
}
