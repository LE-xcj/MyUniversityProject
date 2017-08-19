package service;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CodeServlet
 */
@WebServlet("/CodeServlet")
public class CodeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static final int WIDTH=110;
    private static final int HEIGHT=25;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CodeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	// TODO Auto-generated method stub
    	response.setDateHeader("Expires", -1);
    	response.setHeader("Cache-Control","no-cache" );
    	response.setHeader("Pragma", "no-cache");
    	
    	response.setHeader("Content-Type", "image/jpeg");
    	
    	BufferedImage img=new BufferedImage(110,25,BufferedImage.TYPE_INT_BGR);
    	
    	Graphics g=img.getGraphics();
    	
    	g.setColor(Color.white);
    	g.fillRect(0, 0, WIDTH, HEIGHT);
    	
    	String checkCode="";
    	
    	for(int i=0;i<5;++i){
    		g.setColor(generateColor());
    		g.setFont(generateFont());
    		String str=generateStr();
    		checkCode+=str;
    		g.drawString(str, 20*i, 25);
    	}
    	
    	for(int i=0;i<100;++i){
    		Random rand=new Random();
    		int x=rand.nextInt(WIDTH);
    		int y=rand.nextInt(HEIGHT);
    		g.setColor(generateColor());
    		g.fillOval(x, y, 2, 2);
    	}
    	
    	for(int i=0;i<5;++i){
    		Random rand=new Random();
    		int x1=rand.nextInt(WIDTH);
    		int y1=rand.nextInt(HEIGHT);
    		int X2=rand.nextInt(WIDTH);
    		int y2=rand.nextInt(HEIGHT);
    		g.setColor(generateColor());
    		g.drawLine(x1, y1, X2, y2);
    	}
    	
    	request.getSession().setAttribute("checkCode", checkCode);
    	ImageIO.write(img, "jpg", response.getOutputStream());
    }

	private String generateStr() {
		// TODO Auto-generated method stub
		String[] nums=new String[62];
		
		for(int i=0;i<10;++i){
			nums[i]=String.valueOf(i);
		}
		
		for(int i=65;i<91;++i){
			nums[i-55]=Character.toString((char)i);
		}
		
		for(int i=97;i<123;++i){
			nums[i-61]=Character.toString((char)i);
		}
		
		Random rand=new Random();
		int index=rand.nextInt(62);
		return nums[index];
	}

	private Font generateFont() {
		// TODO Auto-generated method stub
		String[] fontNames = new String[] { "Broadway", "��Բ", "Footlight MT Light", "Sitka Text", "��������", "����Ҧ��",
		"Colonna MT" };
		int[] fontStyles=new int[]{
			 Font.BOLD, Font.ITALIC, Font.BOLD | Font.ITALIC 
		};
		Random rand=new Random();
		int nameIndex=rand.nextInt(fontNames.length);
		int styleIndex = rand.nextInt(fontStyles.length);
		return new Font(fontNames[nameIndex],fontStyles[styleIndex],28);
	}

	private Color generateColor() {
		// TODO Auto-generated method stub
		Random rand = new Random();
		return new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));
	}

}
