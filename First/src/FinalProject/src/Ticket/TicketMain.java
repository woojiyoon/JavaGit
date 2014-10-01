package ticket;

import java.io.*;

public class TicketMain {

	public static void main(String[] args) throws Exception {
			
		int input = 0;
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		System.out.println("티켓팅 알고리즘을 선택하세요.");
		System.out.println("1 : FCFS(선입선출)");
		System.out.println("2 : Round-Robin");
		System.out.println("3 : SJF(최단시간 우선순위)");
		System.out.println("-1 : 프로그램 종료");
		System.out.print("=> ");
		while((input = Integer.parseInt(in.readLine()))!=-1) {
			switch(input) {
				case 1 :	// FCFS 알고리즘을 적용하여 티켓팅 프로세스 돌리기
							readOriginalData();
							TicketStrategyFCFS fcfs = new TicketStrategyFCFS();
							fcfs.startToEnd();
							System.out.println("프로젝트 폴더에 FCFSfinalData.csv 파일이 생성되었습니다.");
							break;
				case 2 : 	// Round-Robin 알고리즘을 적용하여 티켓팅 프로세스 돌리기
							readOriginalData();
							TicketStrategyRoundRobin rRobin = new TicketStrategyRoundRobin();
							rRobin.startToEnd();
							System.out.println("프로젝트 폴더에 RoundRobinfinalData.csv 파일이 생성되었습니다.");
							break;
				case 3 : 	// Round-Robin 알고리즘을 적용하여 티켓팅 프로세스 돌리기
							readOriginalData();
							TicketStrategySJF sjf = new TicketStrategySJF();
							sjf.startToEnd();
							System.out.println("프로젝트 폴더에 SJFfinalData.csv 파일이 생성되었습니다.");
							break;
				default : 	break;
			}
			System.out.print("=> ");
		}	
		System.out.println("프로그램을 종료합니다.");
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
