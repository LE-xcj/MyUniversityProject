package DS_Class;

import java.util.Scanner;

import DS_Interface.Matrix_function;

public class Matrix_operator implements Matrix_function {
	int row,col,value;
	Scanner input;
	public Matrix_operator(){
		input=new Scanner(System.in);
	}
	
	@Override
	//���ݴ���ľ��������ĸ������������
	public void Insert(Matrix matrix){
		System.out.println("��������Ҫ����ڼ��У��ڼ��У�");
		row=input.nextInt();
		col=input.nextInt();
		//��������������֮ǰ���ж���������λ���ھ������Ƿ���Ч
		if(matrix.Can_Insert(row, col)){
			System.out.println("���������");
			value=input.nextInt();
			matrix.Insert(row, col, value);
		}
		else{
			System.out.println("��������л����Ѿ������þ���ķ�Χ�ˣ�");
		}
	}

	@Override
	//ɾ������
	public void Delete(Matrix matrix) {
		System.out.println("��������Ҫɾ���ڼ��У��ڼ��е�����");	
		row=input.nextInt();
		col=input.nextInt();
		int temp=matrix.Delete(row, col);
		if(temp!=0){
			System.out.println("ɾ����"+temp);
		}
		else{
			System.out.println("����������ھ�����");
		}
	}
	

	@Override
	//�޸ľ����ĳһλ�õ���ֵ
	public void ChangeValue(Matrix matrix) {
		System.out.println("��������Ҫ�޸ĵڼ��У��ڼ��е�����");	
		row=input.nextInt();
		col=input.nextInt();
		System.out.println("�������޸ĵ���ֵ");	
		value=input.nextInt();
		matrix.Change_Value(row, col, value);
	}

	@Override
	//��ʾ����
	public void Display(Matrix matrix) {
			matrix.Display();	
	}
	
	

	@Override
	//��ʾ����ĳһλ�õ��ұ������±�����ֻ��Ϊ����֤�������Ĵ���ṹ
	public void Display_R_D(Matrix matrix) {
		System.out.println("��������Ҫ�鿴�ڼ��У��ڼ��е��ұߺ��±ߵ���");
		row=input.nextInt();
		col=input.nextInt();
		matrix.Display_Right_Down(row, col);		
	}
	
	@Override
	//���������
	public void Multiply(Matrix matrix) {
		int time;
		System.out.println("��������Ҫ�˵ı�����");
		time=input.nextInt();
		matrix.Multiply(time);
		System.out.println("����"+time+"�Ľ����");
		matrix.Display();
	}

	@Override
	//�����������
	public void Plus_Matrix(Matrix first,Matrix second) {
		//�����֮ǰ�����ж������������Ƿ������������������������к����Ƿ���ͬ��
		if(Matrix.Same_size(first, second)){
			System.out.println("���֮��Ľ����");
			first.Matrix_Plus(first, second).Display();
			System.out.println("--------------------------");
		}
		else{
			System.out.println("��������Ĵ�С��һ�����޷����мӷ����㣡");
		}
	}

	@Override
	//�����������
	public void Multiply_Matrix(Matrix first, Matrix second) {
		//���ж������������Ƿ����������˵���������һ������������Ƿ���ڵڶ������������
		if(Matrix.Can_Matrix_multiply(first, second)){
			System.out.println("�����������֮��Ľ����");
			first.Matrix_Multiply(first, second).Display();
		}
		else{
			System.out.println("�������������û�ж�Ӧ���޷���ˣ�");
		}
	}
	
}

