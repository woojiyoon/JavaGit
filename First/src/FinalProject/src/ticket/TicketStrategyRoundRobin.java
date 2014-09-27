package ticket;

public class TicketStrategyRoundRobin implements ITicketStrategy {

	private int timeNow = 0;				// 현재 시간(1초씩 상승)
	private boolean getOffWork = false;		// for while statement
	
	public int iTempR1;
	public int iTempR2;
	public int iTempR3;
	
	@Override
	public void startToEnd() {
		iTempR1 = QueueStruct.CustomerOriginalDataQueue.size();	// 오리지날 데이터에 있는 고객의 총 수	
		iTempR2 = QueueStruct.TicketProcessingQueue.length;		// 티켓 처리 부스의 총 갯수 (3부스)		
		int timeFinal = QueueStruct.CustomerOriginalDataQueue.get(iTempR1 - 1).timeOfCustomerArrivalAtStation
				+ QueueStruct.CustomerOriginalDataQueue.get(iTempR1 - 1).timeOfCustomerTicketing;		// while 문안에 timeNow 마지막 시간을 결정
		
		while(!getOffWork) {
			
			sendOriginalCustomerToTicketReadyQueue();
			sendTicketReadyCustomerToTicketProcessingQueue();
			sendCustomerAtPlatformToGettingOnTrain();
			
			if(timeNow == timeFinal + 5)	// 오리지날 데이터가 다 비워지고, 티켓팅 처리도 끝난뒤 + 5 정도 시간 지나가면 마지막 파이날 데이터까지 가기에 충분하므로...
				break;
			
			timeNow++;	// 현실 시간 1초 더하기
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
			if(!QueueStruct.CustomerOriginalDataQueue.isEmpty()) {	// 오리지날 고객 데이터 큐에 데이터가 있다면
				if(timeNow == QueueStruct.CustomerOriginalDataQueue.get(0).getTimeOfCustomerArrivalAtStation()){	// 현재 시간과 도착한 시간이 같으면
					QueueStruct.CustomerTicketReadyQueue.add(QueueStruct.CustomerOriginalDataQueue.get(0));			// 그 고객을 티켓 래디 큐로 보내기
					QueueStruct.CustomerOriginalDataQueue.remove(0);
				}
				else {	// 다르면 끝.
					return;
				}
			}
			else {	// 오리지널 데이터 큐에 고객 데이터를 다 꺼냈고, 이제 데이터가 없다면		// 이 함수는 완전 끝
				//System.out.println("오리지널 큐에 데이터 없음. timeNow :" + timeNow);
				return;
			}
		}	
		
	}

	@Override
	public void sendTicketReadyCustomerToTicketProcessingQueue() {		// Round-Robin 알고리즘
		for(int i = 0; i < iTempR2; i++){
			if(QueueStruct.TicketProcessingQueue[i] == null) {				// 티켓 처리 부스에 빈 칸이 있으면
				if(!QueueStruct.CustomerTicketReadyQueue.isEmpty()) {		// 티켓 래디 큐(역)에 도착한 사람이 있다면
					QueueStruct.TicketProcessingQueue[i] = QueueStruct.CustomerTicketReadyQueue.get(0);		// 티켓 처리 부스로 고객 한 명을 보내고
					QueueStruct.CustomerTicketReadyQueue.remove(0);									// 그 고객을 티켓 래디 큐에서는 지운다.	
				}	
				else
					continue;
			}
			else {				// 티켓처리 시간 1초씩 빼기
				if(QueueStruct.TicketProcessingQueue[i].tempTimeOfCustomerTicketing == 0){ 				// 티켓 처리 시간이 0초되면 부스에서 나가기
					QueueStruct.CustomerReadyforTrainQueue.add(QueueStruct.TicketProcessingQueue[i]);	// 열차 대기 큐로 보내기
					QueueStruct.TicketProcessingQueue[i] = null;										// 나간 부스 자리는 빈자리로
				}
				else {
					QueueStruct.TicketProcessingQueue[i].tempTimeOfCustomerTicketing -= 1;					// 티켓 소요시간 1빼기
					if(QueueStruct.TicketProcessingQueue[i].tempTimeOfCustomerTicketing == 0){ 				// 티켓 처리 시간이 0초되면 부스에서 나가기
						QueueStruct.CustomerReadyforTrainQueue.add(QueueStruct.TicketProcessingQueue[i]);	// 열차 대기 큐로 보내기
						QueueStruct.TicketProcessingQueue[i] = null;										// 나간 자리는 빈자리로
						
						if(!QueueStruct.CustomerTicketReadyQueue.isEmpty()) {									// 티켓 래디 큐(역)에 도착한 사람이 있다면
							QueueStruct.TicketProcessingQueue[i] = QueueStruct.CustomerTicketReadyQueue.get(0);	// 티켓 처리 부스로 고객 한 명을 보내고
							QueueStruct.CustomerTicketReadyQueue.remove(0);										// 그 고객을 티켓 래디 큐에서는 지운다.	
						}	
					}
					else {		// Round-Robin의 시간할당량(3초) 기준에 따른 고객 큐의 티케팅 처리 여부를 적용하는 부분
						if(timeNow != 0 && (timeNow % 3) == 0) {// 3초가 Time Quantum(시간 할당량)이 된다. = 열차 큐(TrainAtPlatformQueue)에 열차가 출발하는 매 3초마다의 시간	
							if(QueueStruct.TicketProcessingQueue[i] != null && QueueStruct.TicketProcessingQueue[i].tempTimeOfCustomerTicketing != 0){
								QueueStruct.CustomerTicketReadyQueue.add(QueueStruct.TicketProcessingQueue[i]);	// 티켓팅 부스에서 티켓처리 하던 고객을 다시 티켓 대기 큐로 보내기(순환 큐의 꼬리로) 
								QueueStruct.TicketProcessingQueue[i] = null;
								
								if(!QueueStruct.CustomerTicketReadyQueue.isEmpty()) {									// 티켓 래디 큐(역)에 도착한 사람이 있다면
									QueueStruct.TicketProcessingQueue[i] = QueueStruct.CustomerTicketReadyQueue.get(0);	// 티켓 처리 부스로 고객 한 명을 보내고
									QueueStruct.CustomerTicketReadyQueue.remove(0);										// 그 고객을 티켓 래디 큐에서는 지운다.	
								}
							}
						}
					}
				}
			
			}
		}
		
		for(int j = 0; j < QueueStruct.CustomerTicketReadyQueue.size(); j++) {
			QueueStruct.CustomerTicketReadyQueue.get(j).timeOnStandbyOfTicket += 1;		// 고객의 티켓 대기 시간 1 더하기
		}
		
		
		for(int k = 0; k < iTempR2; k++){
			if(QueueStruct.TicketProcessingQueue[k] != null) {
				System.out.println(QueueStruct.TicketProcessingQueue[k].getIdOfCustomer() + " : " 
						+ QueueStruct.TicketProcessingQueue[k].tempTimeOfCustomerTicketing);
				
			}
		}
		
	}

	
	// 여기서 부터 코드는 FCFS랑 같음.
	@Override
	public void sendCustomerAtPlatformToGettingOnTrain() {	// ReadyforTrainQueue에 있는 고객 -> TrainAtPlatformQueue로 보내어 기차 태우기(매 3초마다)
		
		if(!QueueStruct.CustomerReadyforTrainQueue.isEmpty()) {			// 플랫폼에서 열차를 기다리는 고객이 있다면
			iTempR3 = QueueStruct.CustomerReadyforTrainQueue.size();
			for(int i = 0; i < iTempR3; i++) {						// 대기중인 모든 고객을 열차에 태우기
				QueueStruct.TrainAtPlatformQueue.add(QueueStruct.CustomerReadyforTrainQueue.get(0));
				QueueStruct.CustomerReadyforTrainQueue.remove(0);
			}
		}
		
		if(timeNow != 0 && (timeNow % 3) == 0) {	// 열차 큐(TrainAtPlatformQueue)를 3초마다 출발시키기
			iTempR3 = QueueStruct.TrainAtPlatformQueue.size();
			for(int i = 0; i < iTempR3; i++) {
				QueueStruct.TrainAtPlatformQueue.get(0).timeOnDepartureOfTrain += timeNow;		// 열차 출발시간 == timeNow
				QueueStruct.TrainAtPlatformQueue.get(0).timeOnArrivalOfTrain = QueueStruct.TrainAtPlatformQueue.get(0).timeOnDepartureOfTrain + QueueStruct.TrainAtPlatformQueue.get(0).timeDurationOfTrain;	// 목적지 도착시간 = 출발시간 + 최소경로시간
				
				// 여기는 고객이 목적지에 도착한 시점
				
				QueueStruct.CustomerFinalDataQueue.add(QueueStruct.TrainAtPlatformQueue.get(0));	// 고객 파이날 데이터목록으로 보내고
				QueueStruct.TrainAtPlatformQueue.remove(0);											// 목적지에 도착한 고객은 지우기
				
			}
		}
		else {		// 열차 출발 시간이 아니면, (고객의 열차 대기 시간 1 더하기)
			if(!QueueStruct.TrainAtPlatformQueue.isEmpty()) {	// 플랫폼에서 열차를 대기하는 고객들이 있다면
				for(int j = 0; j < QueueStruct.TrainAtPlatformQueue.size(); j ++) {
					QueueStruct.TrainAtPlatformQueue.get(j).timeOnStandbyOfTrain += 1;	// 고객의 열차 대기 시간 1 더하기
				}
			}
	
		}
	}   

}	// end of public class TicketStrategyRoundRobin
