package DS_Class;

public class Circle_list {    //ѭ������
    Link current;
    Link previous;
    Link temp;
    int Item;                //������link�ĸ���
    public Circle_list(){
    	current=null;
    	previous=null;
    	temp=null;
    	Item=0;
    }
    
    //----------------------CircleListͨ�ò���-----------------------\\
    //�ж������Ƿ�Ϊ��
    public boolean Is_Empty(){     
    	return current==null;
    }
    
    //��������ĳ���
    public int getSize(){
    	return Item;
    }
    
    //�������ݣ�ֻ��һ��Link����current��ʵ��ѭ������
    //�������Ѿ�Ĭ�ϵ�һ�����������current��Ϊ�����β������һ�������������Ǳ�ɾ����
    //����ÿ����ͷҪ�������ݣ�current�ֻ�����ָ���µĲ���Link
    public void Insert(int data){
    	Link newlink=new Link(data);
    	//������Ϊ�յ�ʱ��
		if(current==null){
			current=newlink;
			newlink.next=current;
		}
		else{
			newlink.next=current.next;
			current.next=newlink;      //ά��currentָ���ͷLink
		}
    	++Item;
    }
    
    //ɾ����ͷ�����Ԫ�أ�Delete first��
    public Link Delete(){
    	//���ж��Ƿ�Ϊ��
    	if(Is_Empty()){
    		System.out.println("����û�����ݣ������޷�ɾ����");
    		return null;
    	}
    	else{
        	temp=current.next;
        	//��currentָ��ԭ����ͷ�ڶ���Link
        	current.next=current.next.next;
        	--Item;
        	return temp;
    	}
    }
    
    //ɾ���ض�ֵ��Link
    public Link Delete_key(int key){
    	//���ж��Ƿ�Ϊ��
    	if(Is_Empty()){
    		System.out.println("������û�����ݣ������޷�ɾ����");
    		return null;
    	}
    	else{
    		//�����c_current��c_previous������ʱ����
    		//Ŀ�ľ��ǲ��ı�current��previous��ֵ
        	Link c_current=current.next;
    		Link c_previous=c_current;
    		//��ʼ��������
        	for(int out=0;out<Item;++out){
        		//����ҵ�ָ��ֵ��Link
        		if(c_current.data==key){
        			temp=c_current;
        			//���ָ��ֵ��Link�ڱ�ͷ���͸ı�current��ָ���ֵ��
        			//�Ͼ�������current���ڱ�β������ָ�������еĵ�һ����Link
        			if(out==0){
        				current.next=c_current.next;
        			}      			       			
        			else{
        				//���ɾ���������һ��Link���͸ı�current
        				//��ԭ������ı�β�ĵڶ���Link��Ϊcurrent����ʹ֮��Ϊcurrent
        				if(out==Item-1){
        					c_previous.next=current.next;
        					current=c_previous;
        				}
        				//�����ɾ����ǰ��current
        				else{
                			c_previous.next=c_current.next;
        				}
        			}
        			--Item;
        			return temp;
        		}
        		//Ѱ����һ��Link������¼��һ��Link
        		c_previous=c_current;
        		c_current=c_current.next;
        	}
        	System.out.println("������û����Ҫɾ��������");
        	return null;
    	}
    }
    
    //��ʾ�����е����ݣ��������ͷ�ĵ�һ��Ԫ�ؿ�ʼ���������Ƕ���currentΪ��β��
    public void Display_List(){
    	temp=current;
    	for(int out=0;out<Item;++out){
    		temp.next.Display();
    		temp=temp.next;
    	}
    	System.out.println();
    }
    
    //Link�ƶ����ӵ�ǰ��LinK������һ��Link
    public void Step(){
        	previous=current;
        	current=current.next;
    }
    
    //Ѱ���ض�ֵ��Link��������
    public Link Find(int key){
    	//���ж��Ƿ�Ϊ��
    	if(current==null){
    		System.out.println("����û�����ݣ�����û����Ҫ�ҵ�����");
    		return null;
    	}
    	else{
    		//��һ����ʱLink������current��ַ��������current�����������
    		//�����Ͳ��øı�current��ֵ
    		temp=current;     
    		for(int out=0;out<Item;++out){
    			if(temp.data==key){
    				return temp;
    			}
    			temp=temp.next;    //Ѱ����һ��Link
    		}
    		System.out.println("������û����Ҫ�ҵ�����");
    		return null;
    	}
    }
    
    //��ʾ�������temp��dataֵ��������Find��������һ����
    public void Peek(Link link){
    	link.Display();
    	System.out.println();
    }
    
   
    //------------------Լɪ������Ĳ���------------------------\\
    public void Josephus_Display()	{
    	temp=current;
    	for(int out=1;out<=Item;++out){
    		temp.Display();
    		temp=temp.next;
    	}   	
    }
    
    //ɾ����ǰ��Link
    public void Delete_current(){
    	//�������ֻʣ��һ��Link
    	if(Item==1){
    		previous=null;
    		current=null;
    		return;
    	}
    	
    	//ɾ����ǰLink����ʵ���Ǹı���һ��Linkָ���LInk��
    	previous.next=current.next;
    	
    	//������ǰLink����һ��Link������Ϊcurrent����ֵ
    	current=current.next;
    	--Item;
    }
    
    //����ʼ���������ǽ�current����β����Link��ɵ�һ��Link����ͷ��
    public void Initial(){     
    	current=current.next;
    	previous=current;
    }
}
