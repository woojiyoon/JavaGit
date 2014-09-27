package ticket;

public class TicketStrategyRoundRobin implements ITicketStrategy {

	private int timeNow = 0;				// ���� �ð�(1�ʾ� ���)
	private boolean getOffWork = false;		// for while statement
	
	public int iTempR1;
	public int iTempR2;
	public int iTempR3;
	
	@Override
	public void startToEnd() {
		iTempR1 = QueueStruct.CustomerOriginalDataQueue.size();	// �������� �����Ϳ� �ִ� ���� �� ��	
		iTempR2 = QueueStruct.TicketProcessingQueue.length;		// Ƽ�� ó�� �ν��� �� ���� (3�ν�)		
		int timeFinal = QueueStruct.CustomerOriginalDataQueue.get(iTempR1 - 1).timeOfCustomerArrivalAtStation
				+ QueueStruct.CustomerOriginalDataQueue.get(iTempR1 - 1).timeOfCustomerTicketing;		// while ���ȿ� timeNow ������ �ð��� ����
		
		while(!getOffWork) {
			
			sendOriginalCustomerToTicketReadyQueue();
			sendTicketReadyCustomerToTicketProcessingQueue();
			sendCustomerAtPlatformToGettingOnTrain();
			
			if(timeNow == timeFinal + 5)	// �������� �����Ͱ� �� �������, Ƽ���� ó���� ������ + 5 ���� �ð� �������� ������ ���̳� �����ͱ��� ���⿡ ����ϹǷ�...
				break;
			
			timeNow++;	// ���� �ð� 1�� ���ϱ�
		}
		// QueueStruct.AllDisplayOfQueueInfo(QueueStruct.CustomerReadyforTrainQueue);
		try {
			QueueStruct.AllWriteOfQueueInfo(QueueStruct.CustomerFinalDataQueue,"RoundRobinfinalData.csv");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void sendOriginalCustomerToTicketReadyQueue() {
		for(int i = 0; i < iTempR1; i++) {	
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
				//System.out.println("�������� ť�� ������ ����. timeNow :" + timeNow);
				return;
			}
		}	
		
	}

	@Override
	public void sendTicketReadyCustomerToTicketProcessingQueue() {		// Round-Robin �˰���
		for(int i = 0; i < iTempR2; i++){
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
						
						if(!QueueStruct.CustomerTicketReadyQueue.isEmpty()) {									// Ƽ�� ���� ť(��)�� ������ ����� �ִٸ�
							QueueStruct.TicketProcessingQueue[i] = QueueStruct.CustomerTicketReadyQueue.get(0);	// Ƽ�� ó�� �ν��� �� �� ���� ������
							QueueStruct.CustomerTicketReadyQueue.remove(0);										// �� ���� Ƽ�� ���� ť������ �����.	
						}	
					}
					else {		// Round-Robin�� �ð��Ҵ緮(3��) ���ؿ� ���� �� ť�� Ƽ���� ó�� ���θ� �����ϴ� �κ�
						if(timeNow != 0 && (timeNow % 3) == 0) {// 3�ʰ� Time Quantum(�ð� �Ҵ緮)�� �ȴ�. = ���� ť(TrainAtPlatformQueue)�� ������ ����ϴ� �� 3�ʸ����� �ð�	
							if(QueueStruct.TicketProcessingQueue[i] != null && QueueStruct.TicketProcessingQueue[i].tempTimeOfCustomerTicketing != 0){
								QueueStruct.CustomerTicketReadyQueue.add(QueueStruct.TicketProcessingQueue[i]);	// Ƽ���� �ν����� Ƽ��ó�� �ϴ� ���� �ٽ� Ƽ�� ��� ť�� ������(��ȯ ť�� ������) 
								QueueStruct.TicketProcessingQueue[i] = null;
								
								if(!QueueStruct.CustomerTicketReadyQueue.isEmpty()) {									// Ƽ�� ���� ť(��)�� ������ ����� �ִٸ�
									QueueStruct.TicketProcessingQueue[i] = QueueStruct.CustomerTicketReadyQueue.get(0);	// Ƽ�� ó�� �ν��� �� �� ���� ������
									QueueStruct.CustomerTicketReadyQueue.remove(0);										// �� ���� Ƽ�� ���� ť������ �����.	
								}
							}
						}
					}
				}
			
			}
		}
		
		for(int j = 0; j < QueueStruct.CustomerTicketReadyQueue.size(); j++) {
			QueueStruct.CustomerTicketReadyQueue.get(j).timeOnStandbyOfTicket += 1;		// ���� Ƽ�� ��� �ð� 1 ���ϱ�
		}
		
		
		for(int k = 0; k < iTempR2; k++){
			if(QueueStruct.TicketProcessingQueue[k] != null) {
				System.out.println(QueueStruct.TicketProcessingQueue[k].getIdOfCustomer() + " : " 
						+ QueueStruct.TicketProcessingQueue[k].tempTimeOfCustomerTicketing);
				
			}
		}
		
	}

	
	// ���⼭ ���� �ڵ�� FCFS�� ����.
	@Override
	public void sendCustomerAtPlatformToGettingOnTrain() {	// ReadyforTrainQueue�� �ִ� �� -> TrainAtPlatformQueue�� ������ ���� �¿��(�� 3�ʸ���)
		
		if(!QueueStruct.CustomerReadyforTrainQueue.isEmpty()) {			// �÷������� ������ ��ٸ��� ���� �ִٸ�
			iTempR3 = QueueStruct.CustomerReadyforTrainQueue.size();
			for(int i = 0; i < iTempR3; i++) {						// ������� ��� ���� ������ �¿��
				QueueStruct.TrainAtPlatformQueue.add(QueueStruct.CustomerReadyforTrainQueue.get(0));
				QueueStruct.CustomerReadyforTrainQueue.remove(0);
			}
		}
		
		if(timeNow != 0 && (timeNow % 3) == 0) {	// ���� ť(TrainAtPlatformQueue)�� 3�ʸ��� ��߽�Ű��
			iTempR3 = QueueStruct.TrainAtPlatformQueue.size();
			for(int i = 0; i < iTempR3; i++) {
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

}	// end of public class TicketStrategyRoundRobin
