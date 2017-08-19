package DS_Class;

import java.util.Scanner;

//Matrix_Control这个类在整个程序是用来显示单个矩阵矩阵和两个矩阵之间的操作界面
public class Matrix_Control {
	private final int Matrix_count=2;
	//矩阵具体运行过程
	Matrix_operator operator;
	//矩阵数组，容纳count个矩阵
	Matrix[] matrix;
	Scanner input;
	int row_length;
	int col_length;
	int flag;
	public Matrix_Control() {
		input=new Scanner(System.in);
		matrix=new Matrix[Matrix_count];
		//声明矩阵数组里的每个Matrix
		for(int out=0;out<Matrix_count;++out){
			matrix[out]=null;
		}
		operator=new Matrix_operator();
		row_length=0;
		col_length=0;
	}
	
	//单个矩阵的操作
	public void One_Matrix_Control(int number){
		Initial_Matrix(number);
		while(true){
			System.out.println("--------------第"+number+"个矩阵的操作--------------");
			System.out.println("1、插入数据");
			System.out.println("2、删除数据");
			System.out.println("3、修改数据");
			System.out.println("4、显示矩阵");
			System.out.println("5、矩阵的数乘");
			System.out.println("6、显示右边与下边的数");
			System.out.println("7、重新设置矩阵的行列数");
			System.out.println("8、返回上一级");
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
	
	//两个矩阵之间的操作
	public void Double_Matrix_Control(){
		while(true){
			System.out.println("1、矩阵相加");
			System.out.println("2、矩阵相乘");
			System.out.println("3、显示两个矩阵");
			System.out.println("4、返回上一级");
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
				  System.out.println("第一个矩阵");
				  operator.Display(matrix[0]); 
				  System.out.println("-------------美丽的分割线---------------");
				  System.out.println("第二个矩阵");
				  operator.Display(matrix[1]); 
			     }
			  break;
			  case 4:{
				  return;
			  }
			}
		}
	}
	
	//设置矩阵的行数和列数
	public void Initial_Matrix(int number){
		System.out.println("请输入矩阵的行数");
		row_length=input.nextInt();
		System.out.println("请输入矩阵的列数");
		col_length=input.nextInt();
		matrix[number-1]=new Matrix(row_length,col_length);
		System.out.println("-------------设置成功！-----------------");
	}
	
	
}
