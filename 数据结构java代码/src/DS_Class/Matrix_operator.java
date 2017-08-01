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
	//根据传入的矩阵，来向哪个矩阵插入数据
	public void Insert(Matrix matrix){
		System.out.println("请输入你要插入第几行，第几列：");
		row=input.nextInt();
		col=input.nextInt();
		//在向矩阵插入数据之前先判断这个插入的位置在矩阵中是否有效
		if(matrix.Can_Insert(row, col)){
			System.out.println("插入的数：");
			value=input.nextInt();
			matrix.Insert(row, col, value);
		}
		else{
			System.out.println("你输入的行或列已经超出该矩阵的范围了！");
		}
	}

	@Override
	//删除数据
	public void Delete(Matrix matrix) {
		System.out.println("请输入你要删除第几行，第几列的数：");	
		row=input.nextInt();
		col=input.nextInt();
		int temp=matrix.Delete(row, col);
		if(temp!=0){
			System.out.println("删除了"+temp);
		}
		else{
			System.out.println("这个数不存在矩阵中");
		}
	}
	

	@Override
	//修改矩阵的某一位置的数值
	public void ChangeValue(Matrix matrix) {
		System.out.println("请输入你要修改第几行，第几列的数：");	
		row=input.nextInt();
		col=input.nextInt();
		System.out.println("请输入修改的数值");	
		value=input.nextInt();
		matrix.Change_Value(row, col, value);
	}

	@Override
	//显示矩阵
	public void Display(Matrix matrix) {
			matrix.Display();	
	}
	
	

	@Override
	//显示矩阵某一位置的右边数与下边数，只是为了验证这个矩阵的储存结构
	public void Display_R_D(Matrix matrix) {
		System.out.println("请输入你要查看第几行，第几列的右边和下边的数");
		row=input.nextInt();
		col=input.nextInt();
		matrix.Display_Right_Down(row, col);		
	}
	
	@Override
	//矩阵的数乘
	public void Multiply(Matrix matrix) {
		int time;
		System.out.println("请输入你要乘的倍数：");
		time=input.nextInt();
		matrix.Multiply(time);
		System.out.println("数乘"+time+"的结果：");
		matrix.Display();
	}

	@Override
	//两个矩阵相加
	public void Plus_Matrix(Matrix first,Matrix second) {
		//在相加之前，先判断这两个矩阵是否满足相加条件（两个矩阵的行和列是否相同）
		if(Matrix.Same_size(first, second)){
			System.out.println("相加之后的结果：");
			first.Matrix_Plus(first, second).Display();
			System.out.println("--------------------------");
		}
		else{
			System.out.println("两个矩阵的大小不一样，无法进行加法运算！");
		}
	}

	@Override
	//两个矩阵相乘
	public void Multiply_Matrix(Matrix first, Matrix second) {
		//先判断这两个矩阵是否满足矩阵相乘的条件（第一个矩阵的列数是否等于第二个矩阵的行数
		if(Matrix.Can_Matrix_multiply(first, second)){
			System.out.println("两个矩阵相乘之后的结果：");
			first.Matrix_Multiply(first, second).Display();
		}
		else{
			System.out.println("两个矩阵的行列没有对应，无法相乘！");
		}
	}
	
}

