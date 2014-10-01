package ticket;

/****************************************************************

<�����ٸ� �˰���> : First-Come, First-Served (FCFS) Scheduling

=> ���� ������ �����ٸ� �˰����� ���� ��ó��(FCFS) �����ٸ� �˰��� �̴�. �� ���������
���Լ��� ť�� ���� ���� ������� ���μ��� �۾��� �Ҵ��Ѵ�. 

****************************************************************/

import java.util.ArrayList;

public class TicketStrategyFCFS implements ITicketStrategy{
	
	private int timeNow = 0;	// ���� �ð� (1�ʾ� ����Ͽ� ������ ���� ť�� ���� ������....)
	private boolean getOffWork = false;	// ������ ������ ����� �ð��� �Ǹ� getOffWork = true �� �ȴ�.(��� ���� ����Ÿ�� ������ �Ǹ� ��ٽð���.)
	public boolean isTrainOkToDepart = false;		// �� 3�и��� ������ ����ϰ�, isTrainOkToDepart = true �� �ȴ�. ������ ������ ����� ���� false ��.
	public int timeOfTrainDeparture = 0;	// ���� ��� �ð�
	public int numberOfAllCustomers = 0;
	public int iTempSize1;
	public int iTempSize2;
	public int iTempSize3;
	
	public void startToEnd() {
		
		iTempSize1 = QueueStruct.CustomerOriginalDataQueue.size();	// �������� �����Ϳ� �ִ� ���� �� ��
		iTempSize2 = QueueStruct.TicketProcessingQueue.length;		// Ƽ�� ó�� �ν��� �� ���� (3�ν�)
		int timeFinal = QueueStruct.CustomerOriginalDataQueue.get(iTempSize1 - 1).timeOfCustomerArrivalAtStation
				+ QueueStruct.CustomerOriginalDataQueue.get(iTempSize1 - 1).timeOfCustomerTicketing;		// while ���ȿ� timeNow ������ �ð��� ����
		
		while(!getOffWork) {
			// �������� ť�� ���� �۾�
			sendOriginalCustomerToTicketReadyQueue();
			sendTicketReadyCustomerToTicketProcessingQueue();
			sendCustomerAtPlatformToGettingOnTrain();
			
			if(timeNow == timeFinal + 5)	// �������� �����Ͱ� �� �������, Ƽ���� ó���� ������ + 5 ���� �ð� �������� ������ ���̳� �����ͱ��� ���⿡ ����ϹǷ�...
				break;
			// 1�ʰ� ������
			timeNow++;
		}
		
		FileProcess.getInstance().CSVFileWrite("FCFSfinalData.csv");	// csv ���Ϸ� ����
	}
	
	public void sendOriginalCustomerToTicketReadyQueue() {
		for(int i = 0; i < iTempSize1; i++) {	
			if(!QueueStruct.CustomerOriginalDataQueue.isEmpty()) {	// �������� �� ������ ť�� �����Ͱ� �ִٸ�
				if(timeNow == QueueStruct.CustomerOriginalDataQueue.get(0).getTimeOfCustomerArrivalAtStation()){	// ���� �ð��� ������ �ð��� ������
					QueueStruct.CustomerTicketReadyQueue.add(QueueStruct.CustomerOriginalDataQueue.get(0));			// �� ���� Ƽ�� ���� ť�� ������
					QueueStruct.CustomerOriginalDataQueue.remove(0);
				}
				else {	// �ٸ��� ��.
					return;
				}
			}
			else {	// �������� ������ ť�� �� �����͸� �� ���°�, ���� �����Ͱ� ���ٸ�		// �� �Լ��� ���� ��
				return;
			}
		}	
	}
	
	
	public void sendTicketReadyCustomerToTicketProcessingQueue() {	// FCFS(���Լ�ó�� �˰���)
		
		for(int i = 0; i < iTempSize2; i++){
			if(QueueStruct.TicketProcessingQueue[i] == null) {				// Ƽ�� ó�� �ν��� �� ĭ�� ������
				if(!QueueStruct.CustomerTicketReadyQueue.isEmpty()) {		// Ƽ�� ���� ť(��)�� ������ ����� �ִٸ�
					QueueStruct.TicketProcessingQueue[i] = QueueStruct.CustomerTicketReadyQueue.get(0);		// Ƽ�� ó�� �ν��� �� �� ���� ������
					QueueStruct.CustomerTicketReadyQueue.remove(0);									// �� ���� Ƽ�� ���� ť������ �����.	
				}	
				else
					continue;
			}
			else {				// Ƽ��ó�� �ð� 1�ʾ� ����
				if(QueueStruct.TicketProcessingQueue[i].tempTimeOfCustomerTicketing == 0){ 				// Ƽ�� ó�� �ð��� 0�ʵǸ� �ν����� ������
					QueueStruct.CustomerReadyforTrainQueue.add(QueueStruct.TicketProcessingQueue[i]);	// ���� ��� ť�� ������
					QueueStruct.TicketProcessingQueue[i] = null;										// ���� �ν� �ڸ��� ���ڸ���
				}
				else {
					QueueStruct.TicketProcessingQueue[i].tempTimeOfCustomerTicketing -= 1;					// Ƽ�� �ҿ�ð� 1����
					if(QueueStruct.TicketProcessingQueue[i].tempTimeOfCustomerTicketing == 0){ 				// Ƽ�� ó�� �ð��� 0�ʵǸ� �ν����� ������
						QueueStruct.CustomerReadyforTrainQueue.add(QueueStruct.TicketProcessingQueue[i]);	// ���� ��� ť�� ������
						QueueStruct.TicketProcessingQueue[i] = null;										// ���� �ڸ��� ���ڸ���
						
						if(!QueueStruct.CustomerTicketReadyQueue.isEmpty()) {								// Ƽ�� ���� ť(��)�� ������ ����� �ִٸ�
							QueueStruct.TicketProcessingQueue[i] = QueueStruct.CustomerTicketReadyQueue.get(0);		// Ƽ�� ó�� �ν��� �� �� ���� ������
							QueueStruct.CustomerTicketReadyQueue.remove(0);									// �� ���� Ƽ�� ���� ť������ �����.	
						}	
					}
				}
			}
			
		}
		
		for(int j = 0; j < QueueStruct.CustomerTicketReadyQueue.size(); j++) {
			QueueStruct.CustomerTicketReadyQueue.get(j).timeOnStandbyOfTicket += 1;		// ���� Ƽ�� ��� �ð� 1 ���ϱ�
		}
		
	}
	
	
	
	public void sendCustomerAtPlatformToGettingOnTrain () {				// ReadyforTrainQueue�� �ִ� �� -> TrainAtPlatformQueue�� ������ ���� �¿��(�� 3�ʸ���)
		if(!QueueStruct.CustomerReadyforTrainQueue.isEmpty()) {			// �÷������� ������ ��ٸ��� ���� �ִٸ�
			iTempSize3 = QueueStruct.CustomerReadyforTrainQueue.size();
			for(int i = 0; i < iTempSize3; i++) {						// ������� ��� ���� ������ �¿��
				QueueStruct.TrainAtPlatformQueue.add(QueueStruct.CustomerReadyforTrainQueue.get(0));
				QueueStruct.CustomerReadyforTrainQueue.remove(0);
			}
		}
		
		if(timeNow != 0 && (timeNow % 3) == 0) {	// ���� ť(TrainAtPlatformQueue)�� 3�ʸ��� ��߽�Ű��
			iTempSize3 = QueueStruct.TrainAtPlatformQueue.size();
			for(int i = 0; i < iTempSize3; i++) {
				QueueStruct.TrainAtPlatformQueue.get(0).timeOnDepartureOfTrain += timeNow;		// ���� ��߽ð� == timeNow
				QueueStruct.TrainAtPlatformQueue.get(0).timeOnArrivalOfTrain = QueueStruct.TrainAtPlatformQueue.get(0).timeOnDepartureOfTrain + QueueStruct.TrainAtPlatformQueue.get(0).timeDurationOfTrain;	// ������ �����ð� = ��߽ð� + �ּҰ�νð�
				
				// ����� ���� �������� ������ ����
				
				QueueStruct.CustomerFinalDataQueue.add(QueueStruct.TrainAtPlatformQueue.get(0));	// �� ���̳� �����͸������ ������
				QueueStruct.TrainAtPlatformQueue.remove(0);											// �������� ������ ���� �����
				
			}
		}
		else {		// ���� ��� �ð��� �ƴϸ�, (���� ���� ��� �ð� 1 ���ϱ�)
			if(!QueueStruct.TrainAtPlatformQueue.isEmpty()) {	// �÷������� ������ ����ϴ� ������ �ִٸ�
				for(int j = 0; j < QueueStruct.TrainAtPlatformQueue.size(); j ++) {
					QueueStruct.TrainAtPlatformQueue.get(j).timeOnStandbyOfTrain += 1;	// ���� ���� ��� �ð� 1 ���ϱ�
				}
			}
		}
	}
	
}	// end of public class TicketStrategyFCFS
