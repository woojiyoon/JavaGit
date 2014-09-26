package ticket;

import java.io.IOException;

public class TicketMain {

	public static void main(String[] args) throws IOException, InterruptedException {
		
		QueueStruct qs = new QueueStruct();	
		FileProcess fp = new FileProcess("OriginalTicketInfo.csv", qs);
		//qs.AllDisplayOfQueueInfo(qs.CustomerOriginalDataQueue);
		
		
		// FCFS 알고리즘을 적용하여 티켓팅 프로세스 돌리기
		TicketStrategyFCFS fcfs = new TicketStrategyFCFS();
		fcfs.FCFSstartToEnd();
		//qs.AllDisplayOfQueueInfo(qs.TrainAtPlatformQueue);	// 파이날 결과 보기
	}

}
