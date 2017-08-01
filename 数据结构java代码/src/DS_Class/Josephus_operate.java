package DS_Class;

public class Josephus_operate {     //实现约瑟夫问题
	   int length;     //链表中数据的个数 （有多少个犹太人）
	   int count;     //计数，用来记录被删除的个数（死掉的人数）
	   
	   public Josephus_operate(int length) {
		   this.length =length;
		   count=0;
	   }
	   
	   //-------------约瑟夫问题的核心算法----------\\
	   public int Which_number(int number){
		   //创建一个循环链表
		   Circle_list list;
		   int out,in;
		   //从1开始找起，查找“报”哪一个“数”能够让约瑟夫作为最后一个被杀的人
		   for(out=1;;++out){
			   System.out.println("报数"+out);
			   count=0;
			   list=new Circle_list();
			   //向循环链表插入20个数，编号从1开始直到20（代表20个人）
			   for(in=length;in>=1;--in){
				   list.Insert(in);
			   }
			   //链表“初始化”，就是让链表最后一个Link（current）变成表头第一个Link
			   //第一个Link的data为1，就是（编号为1）
			   list.Initial();
			   //开始验证当前这个数能否让约瑟夫最后才死
			   while(true){
				   //开始“报数”，报“2”就Step一次；报“3”就Step2次
					   for(int mid=1;mid<out;++mid){
						   list.Step();
					   }
					   //轮到约瑟夫被”杀“了
					   if(list.current.data==number){
						   //但是还要验证是不是最后一个
						   //这里判断count是否等于length-1，因为约瑟夫还没被杀
						   if(count==length-1){
							   //返回这个能让约瑟夫死到最后的数
							   return out;
						   }
						   else{
							   //否则终止while循环
							   System.out.println("杀死了"+number+","+out+"这个数不可以报！");
							   break;
						   }
					   }
					   //“杀”那些中“out“的人
					   else{
						  int temp=list.current.data;
						  list.Delete_current(); 
						  list.Josephus_Display();						  
						  System.out.print("----"+"杀了"+temp);
						  System.out.println();
						  ++count;
					   }
			   }
		   }
		//return 0;
	   }
}
