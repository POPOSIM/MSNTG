package Pmtr;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;


public class Main {
   
	public static void main(String[] args) throws IOException{
		
		BernoulliProcess LIN = new BernoulliProcess(49);
		LIN.ReadCommunity("D:/Community/Pmtr.txt"," ");
		//LIN.WriteHumanedge("D:/football.txt","	");
		//LIN.ReadSocial("D:/HumanInput.txt","	");
		//LIN.BernoulliProcess(5);// �Y�� p
		LIN.BernoulliSetProcess(5);// �Y�� p random<5
		LIN.RandomContactrate(0.2);
		LIN.BernoulliWrite(86400*19,19,"D:/HumanOutput.txt"); //485
		//LIN.NodetoMeetingplace();
	    System.out.print("end");
	    
		
		
	}

}
