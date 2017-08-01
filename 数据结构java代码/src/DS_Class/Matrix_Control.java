package DS_Class;

import java.util.Scanner;

//Matrix_Control�����������������������ʾ��������������������֮��Ĳ�������
public class Matrix_Control {
	private final int Matrix_count=2;
	//����������й���
	Matrix_operator operator;
	//�������飬����count������
	Matrix[] matrix;
	Scanner input;
	int row_length;
	int col_length;
	int flag;
	public Matrix_Control() {
		input=new Scanner(System.in);
		matrix=new Matrix[Matrix_count];
		//���������������ÿ��Matrix
		for(int out=0;out<Matrix_count;++out){
			matrix[out]=null;
		}
		operator=new Matrix_operator();
		row_length=0;
		col_length=0;
	}
	
	//��������Ĳ���
	public void One_Matrix_Control(int number){
		Initial_Matrix(number);
		while(true){
			System.out.println("--------------��"+number+"������Ĳ���--------------");
			System.out.println("1����������");
			System.out.println("2��ɾ������");
			System.out.println("3���޸�����");
			System.out.println("4����ʾ����");
			System.out.println("5�����������");
			System.out.println("6����ʾ�ұ����±ߵ���");
			System.out.println("7���������þ����������");
			System.out.println("8��������һ��");
			flag=input.nextInt();
			switch(flag){
			  case 1:{
				    operator.Insert(matrix[number-1]);
			     }
			  break;
			  case 2:{
				    operator.Delete(matrix[number-1]);
			     }
			  break;
			  case 3:{
				    operator.ChangeValue(matrix[number-1]);
			     }
			  break;
			  case 4:{
				    operator.Display(matrix[number-1]);
			     }
			  break;
			  case 5:{
					operator.Multiply(matrix[number-1]);
			     }
			  break;
			  case 6:{
					operator.Display_R_D(matrix[number-1]);
			     }
			  break;
			  case 7:{
					Initial_Matrix(number);
			     }
			  break;
			  case 8:{
					return;
			     }
			}
		}
	}
	
	//��������֮��Ĳ���
	public void Double_Matrix_Control(){
		while(true){
			System.out.println("1���������");
			System.out.println("2���������");
			System.out.println("3����ʾ��������");
			System.out.println("4��������һ��");
			flag=input.nextInt();
			switch(flag){
			  case 1:{
				    operator.Plus_Matrix(matrix[0], matrix[1]);
			     }
			  break;
			  case 2:{
				  operator.Multiply_Matrix(matrix[0], matrix[1]);
			     }
			  break;
			  case 3:{
				  System.out.println("��һ������");
				  operator.Display(matrix[0]); 
				  System.out.println("-------------�����ķָ���---------------");
				  System.out.println("�ڶ�������");
				  operator.Display(matrix[1]); 
			     }
			  break;
			  case 4:{
				  return;
			  }
			}
		}
	}
	
	//���þ��������������
	public void Initial_Matrix(int number){
		System.out.println("��������������");
		row_length=input.nextInt();
		System.out.println("��������������");
		col_length=input.nextInt();
		matrix[number-1]=new Matrix(row_length,col_length);
		System.out.println("-------------���óɹ���-----------------");
	}
	
	
}
