package ticket;

import java.io.IOException;

public class TicketMain {

	public static void main(String[] args) throws Exception {
		
		FileProcess fp = new FileProcess();
		fp.CSVFileRead("OriginalTicketInfo.csv");
		
		// FCFS 알고리즘을 적용하여 티켓팅 프로세스 돌리기
		TicketStrategyFCFS fcfs = new TicketStrategyFCFS();
		fcfs.FCFSstartToEnd();

	}

}
