package Sigcomm;
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
		
		BernoulliProcess LIN = new BernoulliProcess(77);
		LIN.ReadCommunity("D:/Community/Sigcomm.txt"," ");
		//LIN.WriteHumanedge("D:/football.txt","	");
		//LIN.ReadSocial("D:/HumanInput.txt","	");
		//LIN.BernoulliProcess(5);// «Y¼Æ p
		LIN.BernoulliSetProcess(5);// «Y¼Æ p random<5
		LIN.RandomContactrate(0.15);
		LIN.BernoulliWrit(86400*4,4,"D:/HumanOutput.txt"); //485
		//LIN.NodetoMeetingplace();
	    System.out.print("end");
	    
		
		
	}

}
