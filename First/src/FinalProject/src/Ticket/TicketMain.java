package ticket;

import java.io.IOException;

public class TicketMain {

	public static void main(String[] args) throws IOException, InterruptedException {
		
		QueueStruct qs = new QueueStruct();	
		FileProcess fp = new FileProcess("OriginalTicketInfo.csv", qs);
		//qs.AllDisplayOfQueueInfo(qs.CustomerOriginalDataQueue);
		
		
		// FCFS �˰����� �����Ͽ� Ƽ���� ���μ��� ������
		TicketStrategyFCFS fcfs = new TicketStrategyFCFS();
		fcfs.FCFSstartToEnd();
		//qs.AllDisplayOfQueueInfo(qs.TrainAtPlatformQueue);	// ���̳� ��� ����
	}

}
