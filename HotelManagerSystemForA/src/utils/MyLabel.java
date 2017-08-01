package utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
/*
 * flag�Ǵ�����Ҫ����ǩ��������ʲô��ɫ
 * 1�������ɫ
 * 2��������ɫ
 * ��������ɫ
 */

@SuppressWarnings("serial")
public class MyLabel extends JLabel{
	public MyLabel(){
		
	}
	
	public MyLabel(ImageIcon img){
		super(img);
	}
	
	
	public MyLabel(Rectangle rectanle,String text,int flag,int fontSize){
		super(text);
		if(1==flag)
			this.setForeground(Color.red);
		else if(0==flag)
			this.setForeground(Color.blue);
		this.setFont(new Font("΢���ź�", Font.PLAIN, fontSize));
		this.setBounds(rectanle);
	}
	
	public MyLabel(Rectangle rectanle,int fontSize){
		super();
		this.setFont(new Font("΢���ź�", Font.PLAIN, fontSize));
		this.setBounds(rectanle);
	}
	
	public MyLabel(Rectangle rectanle,String text,int fontSize){
		super(text);
		this.setFont(new Font("΢���ź�", Font.PLAIN, fontSize));
		this.setBounds(rectanle);
	}
	
	public MyLabel(String text,Rectangle rectanle,Font font){
		super(text);
		this.setFont(font);
		this.setBounds(rectanle);
	}
	
	public MyLabel(String text){
		super(text);
	}
	
	public MyLabel(int flag){
		super();
		if(1==flag)
			this.setForeground(Color.red);
		else if(0==flag)
			this.setForeground(Color.blue);
	}
	
	public MyLabel(int flag , Rectangle rectangle){
		super();
		if(1==flag)
			this.setForeground(Color.red);
		else if(0==flag)
			this.setForeground(Color.blue);
		this.setBounds(rectangle);
	}
	
	public MyLabel(String text,Rectangle rectangle){
		super(text);
		this.setBounds(rectangle);
	}
	
	public MyLabel(String text,int flag){
		super(text);
		if(1==flag)
			this.setForeground(Color.red);
		else if(0==flag)
			this.setForeground(Color.blue);
	}
	
	public MyLabel(Rectangle rectangle,String url){
		super();
		this.setIcon(new ImageIcon(url));
		this.setBounds(rectangle);
	}
	
	public MyLabel(String url,String text,Rectangle rectangle){
		this.setBounds(rectangle);
		this.setText(text);
		this.setIcon(new ImageIcon(url));
		this.setHorizontalTextPosition(CENTER);
		this.setVerticalTextPosition(BOTTOM);
	}
	
	public MyLabel(String text , int flag ,Rectangle rectangle){
		if(1==flag)
			this.setForeground(Color.red);
		else if(0==flag)
			this.setForeground(Color.blue);
		this.setBounds(rectangle);
		this.setText(text);
	}
	
}
