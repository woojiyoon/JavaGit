package ticket;

import java.io.*;

public class TicketMain {

	public static void main(String[] args) throws Exception {
			
		int input = 0;
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		System.out.println("Ƽ���� �˰����� �����ϼ���.");
		System.out.println("1 : FCFS(���Լ���)");
		System.out.println("2 : Round-Robin");
		System.out.println("3 : SJF(�ִܽð� �켱����)");
		System.out.println("-1 : ���α׷� ����");
		System.out.print("=> ");
		while((input = Integer.parseInt(in.readLine()))!=-1) {
			switch(input) {
				case 1 :	// FCFS �˰����� �����Ͽ� Ƽ���� ���μ��� ������
							readOriginalData();
							TicketStrategyFCFS fcfs = new TicketStrategyFCFS();
							fcfs.startToEnd();
							System.out.println("������Ʈ ������ FCFSfinalData.csv ������ �����Ǿ����ϴ�.");
							break;
				case 2 : 	// Round-Robin �˰����� �����Ͽ� Ƽ���� ���μ��� ������
							readOriginalData();
							TicketStrategyRoundRobin rRobin = new TicketStrategyRoundRobin();
							rRobin.startToEnd();
							System.out.println("������Ʈ ������ RoundRobinfinalData.csv ������ �����Ǿ����ϴ�.");
							break;
				case 3 : 	// Round-Robin �˰����� �����Ͽ� Ƽ���� ���μ��� ������
							readOriginalData();
							TicketStrategySJF sjf = new TicketStrategySJF();
							sjf.startToEnd();
							System.out.println("������Ʈ ������ SJFfinalData.csv ������ �����Ǿ����ϴ�.");
							break;
				default : 	break;
			}
			System.out.print("=> ");
		}	
		System.out.println("���α׷��� �����մϴ�.");
	}
	
	public static void readOriginalData() {
		
		try {
			FileProcess.getInstance().CSVFileRead("OriginalTicketInfo.csv");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
