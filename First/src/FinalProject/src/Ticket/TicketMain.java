package ticket;

import java.io.IOException;

public class TicketMain {

	public static void main(String[] args) throws Exception {
		
		FileProcess fp = new FileProcess();
		fp.CSVFileRead("OriginalTicketInfo.csv");
		
		// FCFS �˰����� �����Ͽ� Ƽ���� ���μ��� ������
		TicketStrategyFCFS fcfs = new TicketStrategyFCFS();
		fcfs.FCFSstartToEnd();

	}

}
