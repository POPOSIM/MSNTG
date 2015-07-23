package Cambridge;
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
		
		BernoulliProcess LIN = new BernoulliProcess(36);
		LIN.ReadCommunity("D:/Community/Cambridge.txt"," ");
		//LIN.WriteHumanedge("D:/football.txt","	");
		//LIN.ReadSocial("D:/HumanInput.txt","	");
		//LIN.BernoulliProcess(5);// «Y¼Æ p
		LIN.BernoulliSetProcess(5);// «Y¼Æ p random<5
		LIN.RandomContactrate(0.2);
		LIN.BernoulliWrite(86400*12,12,"D:/HumanOutput.txt"); //485
		//LIN.NodetoMeetingplace();
	    System.out.print("end");
	    
		
		
	}

}
