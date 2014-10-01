package ticket;

/****************************************************************

<�����ٸ� �˰���> : �ִ� �۾� �켱(Shortest-Job-First) Scheduling

=> SJF �˰����� �켱���� �����ٸ� �˰��� ���Ѵ�. �� �߿����� SJF�� Ư���� �켱���� �˰������ν�, ���� cpu ����Ʈ�� ���̸� �����ؼ� �����ٸ���
�켱������ ���Ѵ�. �츮�� �ڹ� ���������� Ƽ���� ó�� �ð��� cpu ����Ʈ ���̰� �ǰ�, �� �ð� ���� ��� �����������Ƿ�(�̷��� �˼� �ִ�) ��Ȯ�� SJF�˰���
�� ������ �� �ִ�.(������ ���ǿ����� �������� �̿���.)

=> �߰����� : SJF�˰����� ������(preemptive), ������(non-preemptive) �� ������ �ִµ�, ���⿡���� �������� �����ߴ�.
(������ : ���ο� ���μ����� ���� ����ǰ� �ִ� ���μ����� ���� �ð����� �� ª�� cpu����Ʈ�� ���� ��, ���μ��� �Ҵ��� ��ü�ϴ� ��.
 ������ : ��ü���� �ʰ� ���� �������� ���μ����� �������� ����ϴ� ��.)

****************************************************************/

public class TicketStrategySJF implements ITicketStrategy{
	private int timeNow = 0;	// ���� �ð� (1�ʾ� ����Ͽ� ������ ���� ť�� ���� ������....)
	private boolean getOffWork = false;	// ������ ������ ����� �ð��� �Ǹ� getOffWork = true �� �ȴ�.(��� ���� ����Ÿ�� ������ �Ǹ� ��ٽð���.)
	public int iTempS1;
	public int iTempS2;
	public int iTempS3;
	
	@Override
	public void startToEnd() {
		iTempS1 = QueueStruct.CustomerOriginalDataQueue.size();	// �������� �����Ϳ� �ִ� ���� �� ��
		iTempS2 = QueueStruct.TicketProcessingQueue.length;		// Ƽ�� ó�� �ν��� �� ���� (3�ν�)

		while(!getOffWork) {
		
			// �������� ť�� ���� �۾�
			sendOriginalCustomerToTicketReadyQueue();
			sendTicketReadyCustomerToTicketProcessingQueue();
			sendCustomerAtPlatformToGettingOnTrain();
			
			//if(timeNow == timeFinal + 5)	// �������� �����Ͱ� �� �������, Ƽ���� ó���� ������ + 5 ���� �ð� �������� ������ ���̳� �����ͱ��� ���⿡ ����ϹǷ�...
			if(timeNow == 59)	
				break;
			
			// 1�ʰ� ������
			timeNow++;
					
		}

		FileProcess.getInstance().CSVFileWrite("SJFfinalData.csv");	// csv ���Ϸ� ����
	}
	

	@Override
	public void sendOriginalCustomerToTicketReadyQueue() {
		for(int i = 0; i < iTempS1; i++) {	
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

	
	public CustomerInfo selectShortestJobCustomer() {
		
		CustomerInfo minTicketingTimeCustomer = new CustomerInfo();
		minTicketingTimeCustomer = QueueStruct.CustomerTicketReadyQueue.get(0);	// �ּ� Ƽ���� ó�� �ð� ���� ��� ��ü
		
		for(int i = 1; i < QueueStruct.CustomerTicketReadyQueue.size(); i++) {	// Ƽ�� ���� ť�� �� �� �ּ� Ƽ���� �ð��� �ҿ��ϴ� �� ����
			if(minTicketingTimeCustomer.tempTimeOfCustomerTicketing > QueueStruct.CustomerTicketReadyQueue.get(i).tempTimeOfCustomerTicketing) {
				minTicketingTimeCustomer = QueueStruct.CustomerTicketReadyQueue.get(i);
			}
			else {
				continue;
			}
		}
		
		QueueStruct.CustomerTicketReadyQueue.remove(QueueStruct.CustomerTicketReadyQueue.indexOf(minTicketingTimeCustomer));	// �ּ� Ƽ���� ���� �����ڸ����� �����
		return minTicketingTimeCustomer;	// �� ����(Ƽ���� ��� ť�� �� �߿���, Ƽ���� ó�� �ð��� ���� ���� ��)
	}
	
	
	@Override
	public void sendTicketReadyCustomerToTicketProcessingQueue() {	// SJF(���� ª�� �۾��ð� �켱���� �˰���)
		
		for(int i = 0; i < iTempS2; i++){
			if(QueueStruct.TicketProcessingQueue[i] == null) {				// Ƽ�� ó�� �ν��� �� ĭ�� ������
				if(!QueueStruct.CustomerTicketReadyQueue.isEmpty()) {		// Ƽ�� ���� ť(��)�� ������ ����� �ִٸ�
					QueueStruct.TicketProcessingQueue[i] = selectShortestJobCustomer();		// Ƽ�� ó�� �ν��� �ּ��� Ƽ���� �� �� ���� ������	
				}	
			}
			else {				
				// Ƽ��ó�� 0�� ��� ��������, �� �ڸ��� �ּ� Ƽ�� �ð� �� �ֱ�.
				if(QueueStruct.TicketProcessingQueue[i].tempTimeOfCustomerTicketing == 0){ 				// Ƽ�� ó�� �ð��� 0�ʵǸ� �ν����� ������
					QueueStruct.CustomerReadyforTrainQueue.add(QueueStruct.TicketProcessingQueue[i]);	// ���� ��� ť�� ������
					QueueStruct.TicketProcessingQueue[i] = null;										// ���� �ν� �ڸ��� ���ڸ���
					if(!QueueStruct.CustomerTicketReadyQueue.isEmpty()) {						// Ƽ�� ���� ť(��)�� ������ ����� �ִٸ�
						QueueStruct.TicketProcessingQueue[i] = selectShortestJobCustomer();		// Ƽ�� ó�� �ν��� �ּ��� Ƽ���� �� �� ���� ������
					}
				}
				
				else {
					if(!QueueStruct.CustomerTicketReadyQueue.isEmpty()) {		// Ƽ�� ���� ť(��)�� ������ ����� �ִٸ�
						for(int j = 0; j < QueueStruct.CustomerTicketReadyQueue.size(); j ++) {	// Ƽ�� �ν��� ���� Ƽ���� ��� ť�� ó���ð� ���� ũ�� �� ��ȯ�ϱ�
							if(QueueStruct.TicketProcessingQueue[i].tempTimeOfCustomerTicketing > QueueStruct.CustomerTicketReadyQueue.get(j).tempTimeOfCustomerTicketing) {
								QueueStruct.CustomerTicketReadyQueue.add(QueueStruct.TicketProcessingQueue[i]);
								QueueStruct.TicketProcessingQueue[i] = QueueStruct.CustomerTicketReadyQueue.get(j);
								QueueStruct.CustomerTicketReadyQueue.remove(j);
							}
						}
					}
				}
			}	
		} // end of for i	
		
		
		for(int k = 0; k < iTempS2; k ++) {
			if(QueueStruct.TicketProcessingQueue[k] != null){
				QueueStruct.TicketProcessingQueue[k].tempTimeOfCustomerTicketing -= 1;					// Ƽ�� �ҿ�ð� 1����
				if(QueueStruct.TicketProcessingQueue[k].tempTimeOfCustomerTicketing == 0){ 				// Ƽ�� ó�� �ð��� 0�ʵǸ� �ν����� ������
					QueueStruct.CustomerReadyforTrainQueue.add(QueueStruct.TicketProcessingQueue[k]);	// ���� ��� ť�� ������
					QueueStruct.TicketProcessingQueue[k] = null;										// ���� �ڸ��� ���ڸ���
					
					if(!QueueStruct.CustomerTicketReadyQueue.isEmpty()) {								// Ƽ�� ���� ť(��)�� ������ ����� �ִٸ�
						QueueStruct.TicketProcessingQueue[k] = selectShortestJobCustomer();				// Ƽ�� ó�� �ν��� �ּ��� Ƽ���� �� �� ���� ������	
					}	
				}	
			}	
		}
		
		for(int j = 0; j < QueueStruct.CustomerTicketReadyQueue.size(); j++) {
			QueueStruct.CustomerTicketReadyQueue.get(j).timeOnStandbyOfTicket += 1;		// ���� Ƽ�� ��� �ð� 1 ���ϱ�
		}
		
	} // end of method

	
	
	@Override
	public void sendCustomerAtPlatformToGettingOnTrain() {				// ReadyforTrainQueue�� �ִ� �� -> TrainAtPlatformQueue�� ������ ���� �¿��(�� 3�ʸ���)
		if(!QueueStruct.CustomerReadyforTrainQueue.isEmpty()) {			// �÷������� ������ ��ٸ��� ���� �ִٸ�
			iTempS3 = QueueStruct.CustomerReadyforTrainQueue.size();
			for(int i = 0; i < iTempS3; i++) {						// ������� ��� ���� ������ �¿��
				QueueStruct.TrainAtPlatformQueue.add(QueueStruct.CustomerReadyforTrainQueue.get(0));
				QueueStruct.CustomerReadyforTrainQueue.remove(0);
			}
		}
		
		if(timeNow != 0 && (timeNow % 3) == 0) {	// ���� ť(TrainAtPlatformQueue)�� 3�ʸ��� ��߽�Ű��
			iTempS3 = QueueStruct.TrainAtPlatformQueue.size();
			for(int i = 0; i < iTempS3; i++) {
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
		
}
   

