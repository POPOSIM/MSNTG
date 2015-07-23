package Infocom06;

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
    
	private int CommunityCounter;
	private int Maxnode;
	private double[] CoummunityNode;
	private double[][] HumanEdges;
	private double[][] HumanEdgesSetuse;
	private double[][] BernoulliEdges;
	private double[][] MeetingplaceC;
	private double[][] Period;
	

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
		CoummunityNode = new double[Maxnode];
		for (int i = 0; i < Maxnode; i++) {
			CoummunityNode[i] = 0;
		}
		CommunityCounter = 0;
		FileReader f0 = new FileReader(a);
		BufferedReader b0 = new BufferedReader(f0);

		StringBuffer s0 = new StringBuffer();
		String strNum0 = "";
		while ((strNum0 = b0.readLine()) != null) {

			CommunityCounter++;
			s0.append(strNum0);
			String[] AfterSplit = strNum0.split(b);
			for (int i = 0; i < AfterSplit.length; i++) {
				CoummunityNode[Integer.parseInt(AfterSplit[i])] = CommunityCounter;
				for (int j = 0; j < AfterSplit.length; j++) {
					if (i != j)
						HumanEdges[Integer.parseInt(AfterSplit[i])][Integer.parseInt(AfterSplit[j])] = 1;

				}
			}

		}
		b0.close();
	}
    /*以最大群為中心 只挑出周圍邊*/
	/*public void AddSoicalToCommunity(String a, String b, int MaxCoummunity)throws NumberFormatException, IOException {
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
			if (HumanEdges[Integer.parseInt(AfterSplit[0])][Integer.parseInt(AfterSplit[1])] == 0) {
				TempEdges[Integer.parseInt(AfterSplit[0])][Integer.parseInt(AfterSplit[1])] = 1;
				TempEdges[Integer.parseInt(AfterSplit[1])][Integer.parseInt(AfterSplit[0])] = 1;
			}
			// System.out.println(Integer.parseInt(AfterSplit[1]));
		}
		br.close();

		for (int i = 0; i < Maxnode; i++) {
			for (int j = i; j < Maxnode; j++) {
				if (TempEdges[i][j] == 1) {
					// System.out.println(i+" "+j);
					if (CoummunityNode[i] == MaxCoummunity|| CoummunityNode[j] == MaxCoummunity) {
						HumanEdges[i][j] = 1;
						HumanEdges[j][i] = 1;
						// System.out.println(i+" "+j);
					} else {
						TempEdges[i][j] = 0;
						TempEdges[j][i] = 0;
					}
				}
			}
		}
		
	}

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
    
	
	public void MeetingplaceiWrite() {
		int A1 = 0, A2 = 0, A3 = 0, A4 = 0, A5 = 0, A6 = 0, A7 = 0, A8 = 0, A9 = 0, A10 = 0;
		for (int i = 0; i < Maxnode; i++) {
			for (int j = i; j < Maxnode; j++) {
				if (HumanEdges[i][j] == 1) {
					if (MeetingplaceC[i][j] == 1)
						A1++;
					if (MeetingplaceC[i][j] == 2)
						A2++;
					if (MeetingplaceC[i][j] == 3)
						A3++;
					if (MeetingplaceC[i][j] == 4)
						A4++;
					if (MeetingplaceC[i][j] == 5)
						A5++;
					if (MeetingplaceC[i][j] == 6)
						A6++;
					if (MeetingplaceC[i][j] == 7)
						A7++;
					if (MeetingplaceC[i][j] == 8)
						A8++;
					if (MeetingplaceC[i][j] == 9)
						A9++;
					if (MeetingplaceC[i][j] == 10)
						A10++;

				}
			}
		}

		System.out.println(A1 + " " + A2 + " " + A3 + " " + A4 + " " + A5 + " "+ A6 + " " + A7 + " " + A8 + " " + A9 + " " + A10);
	}
	
	
	public void nodetomeetingplace(int a, int b) {
		int timeT = a; // 單位長度
		int timelong = 1440 * b; // 總長度
		int times = timelong / timeT; // 次數

		for (int i = 0; i < Maxnode; i++) {

			int Temp = 0;
			double TempArrayMathE[];
			for (int j = 0; j < Maxnode; j++) {
				if (HumanEdges[i][j] == 1) {
					Temp = Temp + (int) MeetingplaceC[i][j];

				}
			}
			// System.out.println(Temp);

			TempArrayMathE = new double[Temp];
			for (int k = 0; k < Temp; k++) {
				TempArrayMathE[k] = mathE();
			}

			for (int l = 0; l < times; l++) {
				int GotoTEMP = 0;
				for (int m = 0; m < Temp; m++) {
					int CompareNumber = (int) (TempArrayMathE[m] * 10000000);
					int random = (int) (Math.random() * 10000000 + 1);
					if (random <= CompareNumber) {
						GotoTEMP++;
					}

				}
				System.out.print(GotoTEMP - 1 + " ");

			}
			System.out.println("node" + i);

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
	}*/

	
	public void BernoulliSetProcess(int a){
		BernoulliEdges = new double[Maxnode][Maxnode];
		HumanEdgesSetuse = new double[Maxnode][Maxnode];
		for (int i = 0; i < Maxnode; i++) {
			for (int j = 0; j < Maxnode; j++) {
				HumanEdgesSetuse[i][j]=HumanEdges[i][j];
				BernoulliEdges[i][j] = 0;
			}
		}
		ArrayList<Set<Integer>> list =new ArrayList<Set<Integer>>();
		int Control=-1;
		while (Control != 0) {
			Control=0;
			for (int i = 0; i < Maxnode; i++) {
				for (int j = 0; j < Maxnode; j++) {
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
			for (int j = 0; j < Maxnode; j++) {
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
	
	private boolean DetermineNode(HashSet<Integer> temp1,int temp2){
		if(temp1.contains(temp2))
		  return false;
		for(int i : temp1){
			if(HumanEdgesSetuse[i][temp2]!=1)  
				return false;
		}
		return true;
		
	}


    
	public void BernoulliWrit( int a,int b, String c)throws IOException {
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
					int sleep=(int) (Gaussian()*2700);// 隨機入睡
					while (Totaltime < timelong) {
						
						double CompareNumber =  BernoulliEdges[i][j];
						double random =  (Math.random());
						int randomDownTime = (int) (Powerlaw());
						int randomUpTime;
						if(BernoulliEdges[i][j]<0.5)
							randomUpTime= (int) (Exponential() * 150);
						else
						    randomUpTime= (int) (Exponential() * 50);
						//System.out.println(randomUpTime);

						
	                    if(28800+86400*0-sleep<=Totaltime && Totaltime<=64800+86400*0+sleep &&(9>=Period[i][0] || 9>=Period[j][0])){
	                    	 if (random <= CompareNumber) 
								Totaltime = (int) (Totaltime + randomUpTime);
							 else
								Totaltime = Totaltime + randomDownTime;
							                   	
	                    }   
	                    else if(28800+86400*1+sleep<=Totaltime && Totaltime<=64800+86400*1+sleep&&(9>=Period[i][1] || 9>=Period[j][1])){
	                    	 if (random <= CompareNumber) 
								Totaltime = (int) (Totaltime + randomUpTime);
							 else
								Totaltime = Totaltime + randomDownTime;
							       	
	                    }   
	                    else if(28800+86400*2+sleep<=Totaltime && Totaltime<=64800+86400*2+sleep&&(9>=Period[i][2] || 9>=Period[j][2])){
	                    	 if (random <= CompareNumber) 
								Totaltime = (int) (Totaltime + randomUpTime);
							 else
								Totaltime = Totaltime + randomDownTime;
							                 	
	                    }   
	                    else if(28800+86400*3-sleep<=Totaltime && Totaltime<=64800+86400*3+sleep&&(9>=Period[i][3] || 9>=Period[j][3])){
	                    	 if (random <= CompareNumber) 
								Totaltime = (int) (Totaltime + randomUpTime);
							 else
								Totaltime = Totaltime + randomDownTime;
	                    }
	                    else if(86400*0<=Totaltime && Totaltime<=86400*1&&(3>=Period[i][0] || 3>=Period[j][0])){
	                    	 if (random <= CompareNumber) 
								Totaltime = (int) (Totaltime + randomUpTime);
							 else
								Totaltime = Totaltime + randomDownTime;
	                    }
	                    else if(86400*1<=Totaltime && Totaltime<=86400*2&&(3>=Period[i][1] || 3>=Period[j][1])){
	                    	 if (random <= CompareNumber) 
								Totaltime = (int) (Totaltime + randomUpTime);
							 else
								Totaltime = Totaltime + randomDownTime;
	                    }    
	                    else if(86400*2<=Totaltime && Totaltime<=86400*3&&(5>=Period[i][2] || 5>=Period[j][2])){
	                    	 if (random <= CompareNumber) 
								Totaltime = (int) (Totaltime + randomUpTime);
							 else
								Totaltime = Totaltime + randomDownTime;
	                    }    
	                    else if(86400*3<=Totaltime && Totaltime<=86400*4&&(6>=Period[i][3] || 6>=Period[j][3])){
	                    	 if (random <= CompareNumber) 
								Totaltime = (int) (Totaltime + randomUpTime);
							 else
								Totaltime = Totaltime + randomDownTime;
	                    }    
						else{
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
				if(HumanEdges[i][j] == 0 && i!=j  && (int)(Math.random()*10+1)>0){
					  HumanEdges[i][j]=1;
					  HumanEdges[j][i]=1;
					  double tmep=a*Gaussian();
					  BernoulliEdges[i][j]=tmep;
					  BernoulliEdges[j][i]=tmep;
				}
			}
		}
		
		
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
		P = r.nextGaussian() *3.5;
		Prate = Math.exp((-1) * (P * P) / 2);

		// System.out.printf("%.8f\n",Prate);
		return Prate;
	}
	
	
	
	private static double Powerlaw() { // SLOP上升 INTER CONTACT 下降
		double slope = 1.375;
		int upperBound = 32400;
		int lowerBound = 240;
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
