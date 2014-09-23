package ticket;

/****************************************************************

<�����ٸ� �˰���> : First-Come, First-Served (FCFS) Scheduling

=> ���� ������ �����ٸ� �˰����� ���� ��ó��(FCFS) �����ٸ� �˰��� �̴�. �� ���������
���Լ��� ť�� ���� ���� ������� ���μ��� �۾��� �Ҵ��Ѵ�. 

****************************************************************/

import java.util.ArrayList;

public class TicketStrategyFCFS implements ITicketStrategy{
	
	private int timeNow = 0;	// �ð� (1�ʾ� ����Ͽ� ������ ���� ť�� ���� ������....)
	private boolean getOffWork = false;	// ������ ������ ����� �ð��� �Ǹ� getOffWork = true �� �ȴ�.(��� ���� ����Ÿ�� ������ �Ǹ� ��ٽð���.)
	public boolean isTrainOkToDepart = false;		// �� 3�и��� ������ ����ϰ�, isTrainOkToDepart = true �� �ȴ�. ������ ������ ����� ���� false ��.
	public int timeOfTrainDeparture = 0;	// ���� ��� �ð�
	public int numberOfAllCustomers = 0;
	
	// ���� ť �ֻ��� �ε����� �ִ� ���� �Ѹ� Ƽ�� �ν�ť�� �ű�� �޼���
	public void startQueuetoEndTime(QueueStruct qs) {
		
		numberOfAllCustomers = qs.CustomerOriginalDataQueue.size();
		
		while(!getOffWork) {	// ������ ������ ����� �ð��� �ƴϸ� ���� ��� �ִ� �Ŵϱ� while�� ��� ����(�� ����ϱ�)
			
			if( this.isGetOffWork() == true) {	// �������� �����Ϳ� ���� �� �̻� ����. ���� �� ����(getOffWork == true)
				break;						  	// get out of while statement
			}
			else {
				
				if(qs.CustomerOriginalDataQueue.size() != 0) {
					sendOneCustomerToTicketReady(qs);
				
					sendOneCustomerToTicketBooth(qs);
					
					customerGetOnTrain(qs);
				}
				else {
					sendOneCustomerToTicketBooth(qs);
					
					customerGetOnTrain(qs);
				}
					
				/* ���� (�ؽ�Ʈ ť �۾�)
				
				// (�̺κ��� �ʿ� ��������...) sendOneCustomerToPlatform(qs);
				customerGetOnTrain(qs);
				*/
			}
			
			this.setTimeNow(this.getTimeNow() + 1);		// ���� ���� �ð� + 1 (=> �� 3�� ���� ������ ����Ѵ�.)
		}
		System.out.println("breakpoint");
		qs.AllDisplayOfQueueInfo(qs.TrainAtPlatformQueue); // (3)
		// qs.AllDisplayOfQueueInfo(qs.CustomerReadyforTrainQueue);	// (2)	
		// qs.AllDisplayOfQueueInfo(qs.CustomerTicketReadyQueue);	// (1)
		// ����(���̳� ������) qs.AllDisplayOfQueueInfo(qs.TrainAtPlatformQueue);
	}
	
	

	public void sendOneCustomerToTicketReady(QueueStruct qs) {
		int iSize = qs.CustomerOriginalDataQueue.size();
		
		if(iSize == 0) {	// �������� ������ ť�� ���� �� �̻� ������,
			System.out.println("�������� ������ ť�� �� �̻� ���� �����ϴ�.");
			return;
			//this.setGetOffWork(true);
		}
		
		for(int i = 0; i < iSize; i++) {
			if(qs.CustomerOriginalDataQueue.isEmpty() != true && qs.CustomerOriginalDataQueue.get(0) != null ) { // �������� ������ ť�� ���� ������ �����ִٸ�
				if(qs.CustomerOriginalDataQueue.get(0).getTimeOfCustomerArrivalAtStation() == this.timeNow) { // ���� �ð��� ���� �� ���������ð��� ������ ���� ���� ������ ���̹Ƿ�
					qs.CustomerTicketReadyQueue.add(qs.CustomerOriginalDataQueue.get(0));	// �������� ������ ť�� �ִ� ���� Ƽ�� ����ť�� �Ѹ� ������
					qs.CustomerOriginalDataQueue.remove(0);	// ���� �ڿ�, �������� ������ ť������ �� ���� �����(�ֻ��� �ε����� ���� ���� �����ϴ� ������ ����)
				}
				else {
					break;
				}
			}
		}
	}
		
	
	public void sendOneCustomerToTicketBooth(QueueStruct qs) {	// TicketBooth = TicketProcessingQueue
		int iLength1 = qs.TicketProcessingQueue.length;	// Ƽ�� ��� ť ��ü ���� (Ƽ�� �ν��� �� 3��, iLength1 = 3)
		
		
		for(int i = 0; i < iLength1; i++) {	
			if(qs.TicketProcessingQueue[i] == null) {
				qs.TicketProcessingQueue[i] = qs.CustomerTicketReadyQueue.get(0);	// ����ִ� Ƽ�� �ν��� ���� �ø��� 
				qs.CustomerTicketReadyQueue.remove(0);								// Ƽ�� ����ť�� �ִ� �ֻ��� ���� �����.
			}
			
			else if(qs.TicketProcessingQueue[i] != null && qs.TicketProcessingQueue[i].tempTimeOfCustomerTicketing == 0) {	// Ƽ�� �ν� i��° �ν��� �ִ� ���� Ƽ���� �ҿ�ð��� 0�̶��(Ƽ������ �� �����ٸ�)
				qs.CustomerReadyforTrainQueue.add(qs.TicketProcessingQueue[i]);	// Ƽ�� �ν��� �ִ� ���� ������ ��ٸ��� �÷��� ť�� ������.
				qs.TicketProcessingQueue[i] = null;	// Ƽ�� �ν��� �������
				
				if(qs.TicketProcessingQueue[i] == null) {	// Ƽ�� �ν� 3�� �� �� ���̶� ����ٸ�
					// qs.TicketProcessingQueue[i] = new CustomerInfo();
					qs.TicketProcessingQueue[i] = qs.CustomerTicketReadyQueue.get(0);	// CustomerInfo(��) ��ü�� ticketProcessingQueue[i]���� ���� ť���� �Ѿ�� �� ��ü�� ����
																						// ������ ���ٱ� ���ٴ�, �ϳ��� ��ü�� ����Ű�� ���� ������ 2���� �Ǵ� ����. ���� ���� ���������� ticketProcessingQueue[i]��
					qs.CustomerTicketReadyQueue.remove(0);								// ���� ť�� �ִ� ���� Ƽ�� �ν��� ���� ��, ���� ť�� �ִ� �� �����
				}
			}
			
			else {	// Ƽ�� �ν� i��° �ν��� �ִ� ���� Ƽ���� �ҿ�ð��� ���� ���Ҵٸ� 
				if(qs.TicketProcessingQueue[i].tempTimeOfCustomerTicketing < 0) {
					System.out.println("Ƽ���� �ҿ�ð��� ������ �Ǿ����ϴ�. �˰��� ������ ����.");
				}
				else {
					qs.TicketProcessingQueue[i].tempTimeOfCustomerTicketing -= 1;		// Ƽ�� �ν��ȿ� �ִ� ���� Ƽ�� ó�� �ð� -1 (ó���Ǵ� ���̶�� ��, �̰� 0 �̵Ǹ� �� Ƽ�� �ν��� ����)
				}
			
			}
		}
		
		if(qs.CustomerTicketReadyQueue.size() == 0) {
			System.out.println("Ƽ���� ��� ť�� ���� �� �̻� �����ϴ�.");
//			this.setGetOffWork(true);
			return;
		}
		
		
		// Ƽ�� �ν��� �� ����, �� �� ����, TicketReadyQueue�� ���� ����� Ƽ�� ���ð� + 1 �� ��
		int iLength2 = qs.CustomerTicketReadyQueue.size();	// remove�� ���ļ� ���� �� Ƽ�� ��� ť(remove�� ���� Ƽ�� �ν� 3���� �Ѱ����� ������ ���� �� �۾���.)
		int iTemp = 0;
		for(int j = 0; j < iLength2; j ++) {
			iTemp = qs.CustomerTicketReadyQueue.get(j).getTimeOnStandbyOfTicket();
			qs.CustomerTicketReadyQueue.get(j).setTimeOnStandbyOfTicket(iTemp + 1);	// �� Ƽ�� ��� ť�� �ִ� Ƽ���� ���ð� + 1 
		}
	}
	

	
//	public void sendOneCustomerToPlatform(QueueStruct qs) {
//		int iLength = qs.TicketProcessingQueue.length;
//		
//		for(int i = 0; i < iLength; i++) {
//			// Ƽ��ó�� �ν� ť�� �ִ� ���� Ƽ���� ó���� ���ؼ� Ƽ���� �ҿ�ð��� 0�� �Ǹ�
//			if(qs.TicketProcessingQueue[i] != null && qs.TicketProcessingQueue[i].getTimeOfCustomerTicketing() == 0) { 
//				qs.CustomerReadyforTrainQueue.add(qs.TicketProcessingQueue[i]);	// �� ���� �÷��� ť�� ������ ������ ��ٸ��� �ϱ� 
//			}
//		}
//	}
	
	
	public void customerGetOnTrain(QueueStruct qs) {
		
		if(this.timeNow != 0 && (this.timeNow/3) == 0) {	// �� 3���� �Ǹ� ������ ���԰� ����� �����ϴ�
			this.timeOfTrainDeparture = this.timeNow;		// ���� ��� �ð�(�� 3��)
			this.isTrainOkToDepart = true;					// ���� ��� ���� (OK)
		}
		else {	// ���� ��� �ð��� �ƴϸ�
			int iSize = qs.TrainAtPlatformQueue.size();
			if(iSize != 0) {
				int iTemp1 = 0;
				
				for(int i = 0; i < iSize; i++) {
					iTemp1 = qs.TrainAtPlatformQueue.get(i).getTimeOnStandbyOfTrain();		// ������ ����ϱ⸦ ��ٸ��� �� ���� �� ���� ���ð� ������ �����ͼ�
					qs.TrainAtPlatformQueue.get(i).setTimeOnStandbyOfTrain(iTemp1 + 1);	// ���� ����ž���� ���� ����� �ð� +1 �ϱ�.
				}
				
				this.isTrainOkToDepart = false;
			}
		}
		
		if(this.timeNow != 0 && this.isTrainOkToDepart == true) {	// ������ �÷����� ���԰�, ����� �����ϴٸ� (�� 3�и��� ����)
			int iTemp2 = 0;
			int iSize = qs.CustomerReadyforTrainQueue.size();
			
			for(int i = 0; i < iSize; i++) {	// (�÷�������) ������ ��ٸ��� ��� ���� ������ ž�½�Ű��
				if(qs.CustomerReadyforTrainQueue.get(0) != null) {			// ��ٸ��� ���� �ִٸ�
					
					qs.CustomerReadyforTrainQueue.get(0).setTimeOnDepartureOfTrain(this.timeNow);	// ������ ��߽ð��� ���� ���� ��������� ����ϰ�,
					
					iTemp2 = qs.CustomerReadyforTrainQueue.get(0).getTimeDurationOfTrain();			// ���� �ּҰ�� �ð��� �����ͼ�
					qs.CustomerReadyforTrainQueue.get(0).setTimeOnArrivalOfTrain(this.timeNow + iTemp2);	// ������� �ð� + ���� �ּҰ�� �ð� = ���� ���� ���� �ð�
					
					qs.TrainAtPlatformQueue.add(qs.CustomerReadyforTrainQueue.get(0));	// ������ ž�½�Ű��
					qs.CustomerReadyforTrainQueue.remove(0);	// ������ ��ٸ��� �� ť���� �����(���� �����ϱ�)
					
				}
				else {
					System.out.println("���� �÷������� ������ ��ٸ��� ���� �����ϴ�.");
				}
			}
		}
		
		
		// qs.TrainAtPlatformQueue �� ���� ���� �����Ͱ� �ȴ�.(�������� ������ ������ ������ ���� ����Ʈ ������)
		
	}
	
	/*******************************************************************************
	 
	  								getters, setters..
	 
	 *******************************************************************************/
	public int getTimeNow() {
		return timeNow;
	}

	public void setTimeNow(int timeNow) {
		this.timeNow = timeNow;
	}

	public boolean isGetOffWork() {
		return getOffWork;
	}

	public void setGetOffWork(boolean getOffWork) {
		this.getOffWork = getOffWork;
	}


}
