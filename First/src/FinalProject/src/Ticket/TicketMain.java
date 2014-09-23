package ticket;

import java.io.IOException;

public class TicketMain {

	public static void main(String[] args) throws IOException {
		
		QueueStruct qs = new QueueStruct();	
		FileProcess fp = new FileProcess("OriginalTicketInfo.csv", qs);
		//qs.AllDisplayOfQueueInfo(qs.CustomerOriginalDataQueue);
		
		
		// FCFS �˰����� �����Ͽ� Ƽ���� ���μ��� ������
		TicketStrategyFCFS fcfs = new TicketStrategyFCFS();
		fcfs.startQueuetoEndTime(qs);
		//qs.AllDisplayOfQueueInfo(qs.TrainAtPlatformQueue);	// ���̳� ��� ����
	}

}
