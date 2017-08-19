package DS_Class;

public class Circle_list {    //循环链表
    Link current;
    Link previous;
    Link temp;
    int Item;                //链表中link的个数
    public Circle_list(){
    	current=null;
    	previous=null;
    	temp=null;
    	Item=0;
    }
    
    //----------------------CircleList通用操作-----------------------\\
    //判断链表是否为空
    public boolean Is_Empty(){     
    	return current==null;
    }
    
    //返回链表的长度
    public int getSize(){
    	return Item;
    }
    
    //插入数据，只用一个Link变量current，实现循环链表
    //类中我已经默认第一个插入的数（current）为链表的尾部；是一个不变量（除非被删除）
    //否则每当表头要插入数据，current又会重新指向新的插入Link
    public void Insert(int data){
    	Link newlink=new Link(data);
    	//当链表为空的时候
		if(current==null){
			current=newlink;
			newlink.next=current;
		}
		else{
			newlink.next=current.next;
			current.next=newlink;      //维持current指向表头Link
		}
    	++Item;
    }
    
    //删除表头插入的元素（Delete first）
    public Link Delete(){
    	//先判断是否为空
    	if(Is_Empty()){
    		System.out.println("链表没有数据，所以无法删除！");
    		return null;
    	}
    	else{
        	temp=current.next;
        	//让current指向原来表头第二个Link
        	current.next=current.next.next;
        	--Item;
        	return temp;
    	}
    }
    
    //删除特定值的Link
    public Link Delete_key(int key){
    	//先判断是否为空
    	if(Is_Empty()){
    		System.out.println("链表中没有数据，所以无法删除！");
    		return null;
    	}
    	else{
    		//这里的c_current与c_previous都是临时变量
    		//目的就是不改变current与previous的值
        	Link c_current=current.next;
    		Link c_previous=c_current;
    		//开始遍历链表
        	for(int out=0;out<Item;++out){
        		//如果找到指定值的Link
        		if(c_current.data==key){
        			temp=c_current;
        			//如果指定值的Link在表头，就改变current的指向的值；
        			//毕竟这个类的current（在表尾）总是指向链表中的第一个个Link
        			if(out==0){
        				current.next=c_current.next;
        			}      			       			
        			else{
        				//如果删除的是最后一个Link，就改变current
        				//让原来链表的表尾的第二个Link作为current，并使之变为current
        				if(out==Item-1){
        					c_previous.next=current.next;
        					current=c_previous;
        				}
        				//否则就删除当前的current
        				else{
                			c_previous.next=c_current.next;
        				}
        			}
        			--Item;
        			return temp;
        		}
        		//寻找下一个Link，并记录上一个Link
        		c_previous=c_current;
        		c_current=c_current.next;
        	}
        	System.out.println("链表中没有你要删除的数！");
        	return null;
    	}
    }
    
    //显示链表中的数据，从链表表头的第一个元素开始（这里我是定义current为表尾）
    public void Display_List(){
    	temp=current;
    	for(int out=0;out<Item;++out){
    		temp.next.Display();
    		temp=temp.next;
    	}
    	System.out.println();
    }
    
    //Link移动，从当前的LinK跳到下一个Link
    public void Step(){
        	previous=current;
        	current=current.next;
    }
    
    //寻找特定值的Link，并返回
    public Link Find(int key){
    	//先判断是否为空
    	if(current==null){
    		System.out.println("链表没有数据，所以没有你要找的数！");
    		return null;
    	}
    	else{
    		//用一个临时Link变量存current地址，并代替current遍历这个链表
    		//这样就不用改变current的值
    		temp=current;     
    		for(int out=0;out<Item;++out){
    			if(temp.data==key){
    				return temp;
    			}
    			temp=temp.next;    //寻找下一个Link
    		}
    		System.out.println("链表中没有你要找的数！");
    		return null;
    	}
    }
    
    //显示传入参数temp的data值，与上面Find（）函数一起用
    public void Peek(Link link){
    	link.Display();
    	System.out.println();
    }
    
   
    //------------------约瑟夫问题的操作------------------------\\
    public void Josephus_Display()	{
    	temp=current;
    	for(int out=1;out<=Item;++out){
    		temp.Display();
    		temp=temp.next;
    	}   	
    }
    
    //删除当前的Link
    public void Delete_current(){
    	//如果链表只剩下一个Link
    	if(Item==1){
    		previous=null;
    		current=null;
    		return;
    	}
    	
    	//删除当前Link（其实就是改变上一个Link指向的LInk）
    	previous.next=current.next;
    	
    	//跳到当前Link的下一个Link，并作为current的新值
    	current=current.next;
    	--Item;
    }
    
    //“初始化”，就是将current（表尾）的Link变成第一个Link（表头）
    public void Initial(){     
    	current=current.next;
    	previous=current;
    }
}
