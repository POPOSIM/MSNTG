package Pmtr;

import java.awt.List;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

public class BernoulliProcess {
    
	
	private int Maxnode;
	private double[][] HumanEdges;
	private double[][] HumanEdgesSetuse;
	private double[][] BernoulliEdges;
	private double[][] MeetingPlaceNumber;
	private double[][] Period;
	ArrayList<Set<Integer>> list; //Meetingplaceset

	public BernoulliProcess(int x) {
		this.Maxnode = x;
		this.HumanEdges = new double[Maxnode][Maxnode];
		for (int i = 0; i < Maxnode; i++) {
			for (int j = 0; j < Maxnode; j++) {
				HumanEdges[i][j] = 0;
				
			}
		}
	}

	public void ReadCommunity(String a, String b) throws NumberFormatException,IOException {
        //跟最大群加入而外邊有關 CoummunityNode CommunityCounter
		FileReader f0 = new FileReader(a);
		BufferedReader b0 = new BufferedReader(f0);

		StringBuffer s0 = new StringBuffer();
		String strNum0 = "";
		while ((strNum0 = b0.readLine()) != null) {
			s0.append(strNum0);
			String[] AfterSplit = strNum0.split(b);
			for (int i = 0; i < AfterSplit.length; i++) {
				for (int j = 0; j < AfterSplit.length; j++) {
					if (i != j)
						HumanEdges[Integer.parseInt(AfterSplit[i])][Integer.parseInt(AfterSplit[j])] = 1;

				}
			}

		}
		b0.close();
	}
    /*以最大群為中心 只挑出周圍邊*/
	

	public void ReadSocial(String a, String b) throws NumberFormatException,IOException {
		FileReader fr = new FileReader(a);
		BufferedReader br = new BufferedReader(fr);

		StringBuffer sb = new StringBuffer();
		String strNum = "";
		while ((strNum = br.readLine()) != null) {
			sb.append(strNum);
			String[] AfterSplit = strNum.split(b);
			HumanEdges[Integer.parseInt(AfterSplit[0])][Integer.parseInt(AfterSplit[1])] = 1;
			HumanEdges[Integer.parseInt(AfterSplit[1])][Integer.parseInt(AfterSplit[0])] = 1;
			// System.out.println(Integer.parseInt(AfterSplit[1]));
		}
		br.close();
	}
    
	
	
	
	public void NodetoMeetingplace() {
		MeetingPlaceNumber = new double[Maxnode][Maxnode];
		for (int i = 0; i < Maxnode; i++) {
			for (int j = 0; j < Maxnode; j++) {
				MeetingPlaceNumber[i][j]=0;
			}
		}
		
		
		for (int i = 0; i < Maxnode; i++) {
			for (int j = i; j < Maxnode; j++) {
				for( Set<Integer> temp : list){
					if(temp.contains(i)&&temp.contains(j))
						MeetingPlaceNumber[i][j]++;
					    MeetingPlaceNumber[j][i]=MeetingPlaceNumber[i][j];
						
				}
			}
		}

	}
	
	public void WriteHumanedge(String a, String b) throws IOException {
		double[][] TempEdges = new double[Maxnode][Maxnode];
		for (int i = 0; i < Maxnode; i++) {
			for (int j = 0; j < Maxnode; j++) {
				TempEdges[i][j] = 0;
			}
		}
		FileReader fr = new FileReader(a);
		BufferedReader br = new BufferedReader(fr);

		StringBuffer sb = new StringBuffer();
		String strNum = "";
		while ((strNum = br.readLine()) != null) {
			sb.append(strNum);
			String[] AfterSplit = strNum.split(b);
			
			if(HumanEdges[Integer.parseInt(AfterSplit[0])][Integer.parseInt(AfterSplit[1])] ==0){
				TempEdges[Integer.parseInt(AfterSplit[0])][Integer.parseInt(AfterSplit[1])]=1;
			    TempEdges[Integer.parseInt(AfterSplit[1])][Integer.parseInt(AfterSplit[0])]=1;
			}
		}
		br.close();
		
		for (int i = 0; i < Maxnode; i++) {
			for (int j = i; j < Maxnode; j++) {
				if (TempEdges[i][j] == 1) {
					// System.out.println(i+" "+j);
					if(HumanEdges[i][j] ==0 && TempEdges[i][j]==1){
						System.out.println(i+" "+j);
					}
				}
			}
		}
	}

	 
	public void BernoulliSetProcess(int a){
		BernoulliEdges = new double[Maxnode][Maxnode];
		HumanEdgesSetuse = new double[Maxnode][Maxnode];
		for (int i = 0; i < Maxnode; i++) {
			for (int j = 0; j < Maxnode; j++) {
				HumanEdgesSetuse[i][j]=HumanEdges[i][j];
				BernoulliEdges[i][j] = 0;
			}
		}
		list =new ArrayList<Set<Integer>>();
		int Control=-1;
		while (Control != 0) {
			Control=0;
			for (int i = 0; i < Maxnode; i++) {
				for (int j = i; j < Maxnode; j++) {
					if (HumanEdgesSetuse[i][j] == 1) {
						HashSet<Integer> temp = new HashSet<Integer>();
						temp.add(i);
						temp.add(j);

						for (int node = 0; node < Maxnode; node++) {
							if (DetermineNode(temp, node)) {
								temp.add(node);
							}
						}
						if (!list.contains(temp))
							list.add(temp);

					}
				}
			}
			for (int i = 0; i < Maxnode; i++) {
				for (int j = i; j < Maxnode; j++) {
					Control+=HumanEdgesSetuse[i][j];
					int random = (int) (Math.random() * 10 + 1);
					if (random <= a){
						HumanEdgesSetuse[i][j] = 0;
						HumanEdgesSetuse[j][i] = 0;
					}
				}
			}
			
		}
		//System.out.println(list);
		for (int i = 0; i < Maxnode; i++) {
			for (int j = i; j < Maxnode; j++) {
				if (HumanEdges[i][j] == 1 && BernoulliEdges[i][j] == 0) {
					int counter = 0;
					for( Set<Integer> temp : list){
						if(temp.contains(i)&&temp.contains(j))
							counter++;
						
					}
					// 第一次
					double rA1 = mathE();
					double rB1 = mathE();
					BernoulliEdges[i][j] = 1 - rA1 * rB1;

					for (int i1 = 0; i1 < counter - 1; i1++) {

						double rA2 = mathE();
						double rB2 = mathE();

						BernoulliEdges[i][j] = BernoulliEdges[i][j]
								* (1 - rA2 * rB2);

					}
					BernoulliEdges[i][j] = 1 - BernoulliEdges[i][j];
					BernoulliEdges[j][i] = BernoulliEdges[i][j];
					System.out.println(i+" "+j+" "+BernoulliEdges[i][j]);
				}
			}
		}
	}
	
    
	public void BernoulliWrite( int a , int b, String c)throws IOException {
		Period = new double[Maxnode][b];
        for(int i=0;i<Maxnode;i++){
        	for(int j=0;j<b;j++){
        		Period[i][j]=(Math.random() * 10 + 1);
        	}
        }
    
		int timelong = a; // 總長度
		FileWriter dataFile = new FileWriter(c);
		BufferedWriter input = new BufferedWriter(dataFile);

		for (int i = 0; i < Maxnode; i++) {
			for (int j = i; j < Maxnode; j++) {
				if (HumanEdges[i][j] == 1) {
					int Totaltime = 0;
					int sleep=(int) (Gaussian()*900);// 隨機入睡
					while (Totaltime < timelong) {
						
						double CompareNumber =  BernoulliEdges[i][j];
						double random =  (Math.random());
						int randomUpTime;
						int randomDownTime = (int) (Powerlaw());
						
						if(BernoulliEdges[i][j]<0.9)
							randomUpTime= (int) (Exponential() * 60);
						else
							randomUpTime= (int) (Exponential() * 6);
						
						//System.out.println(randomUpTime);
						boolean WakeUp=true;
                        for(int i1=0;i1<b;i1++){
                        	 if(28800+86400*i1-sleep<=Totaltime && Totaltime<=72000+86400*i1+sleep &&(10>=Period[i][i1] || 10>=Period[j][i1])){
                        		 if (random <= CompareNumber){
      								Totaltime = (int) (Totaltime + randomUpTime);
      							 }
      							 else{
      								Totaltime = Totaltime + randomDownTime;
      							 }
                         		 WakeUp=false;
                         		 break;
                        	 }
                        	 else if((86400*1.5<Totaltime && Totaltime<86400*3.5) || (86400*8.5<Totaltime && Totaltime<86400*10.5) || (86400*15.5<Totaltime && Totaltime<86400*17.5)){
                        		 if (random <= CompareNumber){
       								Totaltime = (int) (Totaltime + randomUpTime);
       							 }
       							 else{
       								Totaltime = Totaltime + randomDownTime;
       							 }
                          		 WakeUp=false;
                          		 break;
                        	 }
                        	 else if(86400*i1<=Totaltime && Totaltime<=86400*(i1+1) &&(5>=Period[i][i1] || 5>=Period[j][i1])){
                        		 if (random <= CompareNumber){
        								Totaltime = (int) (Totaltime + randomUpTime);
        							 }
        							 else{
        								Totaltime = Totaltime + randomDownTime;
        							 }
                        		 WakeUp=false;
                          		 break;
                        	 }
                        }

						if(WakeUp) {
							 if (random <= CompareNumber) {
								    if(randomUpTime!=0){
									  input.write(i + " " + j + " " + Totaltime + " "+ (int) (Totaltime + randomUpTime) + "\n");
									  Totaltime = (int) (Totaltime + randomUpTime);
								    }
							 }
							 else{
									Totaltime = Totaltime + randomDownTime;
							 }
						}
                        
	                    
						
					}

				}
			}
		}
		input.close();
	}
	
	public void RandomContactrate(double a) { //高斯參數上升朋友率下降
		/*int Temp[]=new int[Maxnode];
		for (int i = 0; i < Maxnode; i++) {
			Temp[i]=(int) (Math.random()*10+1);
		}
		for (int i = 0; i < Maxnode; i++) {
			 for (int j = i; j < Maxnode; j++) {
				if(HumanEdges[i][j] == 1){
					Temp[i]=10;
					Temp[j]=10;			  
			    }
	    	}
		}
		Temp[i]>2 && Temp[j]>2  [3 4 5 6 7 8 9 10可以]
		*/ 
		
		for (int i = 0; i < Maxnode; i++) {
			for (int j = i; j < Maxnode; j++) {
				if(HumanEdges[i][j] == 0 && i!=j && (int)(Math.random()*10+1)>0){
					if(i!=4 && i!=7 && i!=14 && i!=29 && i!=34&&
					   j!=4 && j!=7 && j!=14 && j!=29 && j!=34){
					  HumanEdges[i][j]=1;
					  HumanEdges[j][i]=1;
					  double tmep=a*Gaussian();
					  BernoulliEdges[i][j]=tmep;
					  BernoulliEdges[j][i]=tmep;
					}
				}
			}
		}
		
		
	}
	
	private boolean DetermineNode(HashSet<Integer> temp1,int temp2){
		if(temp1.contains(temp2))
		  return false;
		for(int i : temp1){
			if(HumanEdgesSetuse[i][temp2]!=1)  
				return false;
		}
		return true;
		
	}
	
	private static double Gaussian() { //高斯參數上升朋友率下降
		double P = 0;

		Random r = new Random();
		// P=(Math.random()*700+1)/100; // P is standard normal random variable
		P = r.nextGaussian();
		
        if(P<0)
		  return -P;
        else
          return P;
		
	}
	

	private static double mathE() { //高斯參數上升朋友率下降
		double P = 0, Prate = 0;

		Random r = new Random();
		// P=(Math.random()*700+1)/100; // P is standard normal random variable
		P = r.nextGaussian() *3;
		Prate = Math.exp((-1) * (P * P) / 2);

		// System.out.printf("%.8f\n",Prate);
		return Prate;
	}
	
	
	
	private static double Powerlaw() { // SLOP上升 INTER CONTACT 下降
		double slope = 1.25;
		int upperBound = 43200;
		int lowerBound = 1960;
		double P=Math.random();
	

		// convert to a random number in [1,+inf) with power law distribution

		double exponent = 1.0 / ( -slope + 1 );
		double time = Math.pow( ( double )( 1.0 - P ), ( double )exponent );
     
		// apply an upper bound
		
		if( time > upperBound )
		{
			time = upperBound;
		}
		if( lowerBound>time  )
		{
			Random r = new Random();
			time = r.nextGaussian() *lowerBound;
			if(time<0) time=-time;
		}
		return time;
	}
	
	
	private static double Exponential() { //SLOP 變高 CONTACT DRAUTION 下降
		double Temp = Math.random();
		double randomTime = Math.log(1 / Temp) ;
		return randomTime;
	}
	
}
