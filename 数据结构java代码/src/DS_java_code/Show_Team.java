package DS_java_code;

import java.util.Scanner;

import DS_Class.Form_Team;

public class Show_Team {
    public static void main(String args[]){
    	@SuppressWarnings("resource")
		Scanner input=new Scanner(System.in);
    	Form_Team text;
    	int amount,number_team;
    	while(true){
    		System.out.println("��������������");
    		amount=input.nextInt();
    		System.out.println("����������������");
    		number_team=input.nextInt();
    		text=new Form_Team(amount, number_team);
    		text.ShowTeam(0, "", 0);
    	}
    }
}
