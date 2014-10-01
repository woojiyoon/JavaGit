package ticket;

/****************************************************************

<스케줄링 알고리즘> : 최단 작업 우선(Shortest-Job-First) Scheduling

=> SJF 알고리즘은 우선순위 스케줄링 알고리즘에 속한다. 그 중에서도 SJF는 특별한 우선순위 알고리즘으로써, 다음 cpu 버스트의 길이를 예측해서 스케줄링의
우선순위를 정한다. 우리의 자바 과제에서는 티켓팅 처리 시간이 cpu 버스트 길이가 되고, 그 시간 값이 모두 정해져있으므로(미래를 알수 있다) 정확한 SJF알고리즘
을 구현할 수 있다.(하지만 현실에서는 예측값을 이용함.)

=> 추가사항 : SJF알고리즘은 선점형(preemptive), 비선점형(non-preemptive) 두 종류가 있는데, 여기에서는 선점형을 적용했다.
(선점형 : 새로운 프로세스가 현재 실행되고 있는 프로세스의 남은 시간보다 더 짧은 cpu버스트를 가질 때, 프로세스 할당을 교체하는 것.
 비선점형 : 교체하지 않고 현재 실행중인 프로세스를 끝내도록 허용하는 것.)

****************************************************************/

public class TicketStrategySJF implements ITicketStrategy{
	private int timeNow = 0;	// 현재 시간 (1초씩 상승하여 마지막 고객을 큐에 넣을 때까지....)
	private boolean getOffWork = false;	// 기차역 직원이 퇴근할 시간이 되면 getOffWork = true 가 된다.(모든 고객이 기차타고 떠나게 되면 퇴근시간임.)
	public int iTempS1;
	public int iTempS2;
	public int iTempS3;
	
	@Override
	public void startToEnd() {
		iTempS1 = QueueStruct.CustomerOriginalDataQueue.size();	// 오리지날 데이터에 있는 고객의 총 수
		iTempS2 = QueueStruct.TicketProcessingQueue.length;		// 티켓 처리 부스의 총 갯수 (3부스)

		while(!getOffWork) {
		
			// 여러가지 큐에 대한 작업
			sendOriginalCustomerToTicketReadyQueue();
			sendTicketReadyCustomerToTicketProcessingQueue();
			sendCustomerAtPlatformToGettingOnTrain();
			
			//if(timeNow == timeFinal + 5)	// 오리지날 데이터가 다 비워지고, 티켓팅 처리도 끝난뒤 + 5 정도 시간 지나가면 마지막 파이날 데이터까지 가기에 충분하므로...
			if(timeNow == 59)	
				break;
			
			// 1초가 지나고
			timeNow++;
					
		}

		FileProcess.getInstance().CSVFileWrite("SJFfinalData.csv");	// csv 파일로 쓰기
	}
	

	@Override
	public void sendOriginalCustomerToTicketReadyQueue() {
		for(int i = 0; i < iTempS1; i++) {	
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

				return;
			}
		}	
		
	}

	
	public CustomerInfo selectShortestJobCustomer() {
		
		CustomerInfo minTicketingTimeCustomer = new CustomerInfo();
		minTicketingTimeCustomer = QueueStruct.CustomerTicketReadyQueue.get(0);	// 최소 티켓팅 처리 시간 고객을 담는 객체
		
		for(int i = 1; i < QueueStruct.CustomerTicketReadyQueue.size(); i++) {	// 티켓 래디 큐의 고객 중 최소 티켓팅 시간을 소요하는 고객 고르기
			if(minTicketingTimeCustomer.tempTimeOfCustomerTicketing > QueueStruct.CustomerTicketReadyQueue.get(i).tempTimeOfCustomerTicketing) {
				minTicketingTimeCustomer = QueueStruct.CustomerTicketReadyQueue.get(i);
			}
			else {
				continue;
			}
		}
		
		QueueStruct.CustomerTicketReadyQueue.remove(QueueStruct.CustomerTicketReadyQueue.indexOf(minTicketingTimeCustomer));	// 최소 티켓팅 고객은 원래자리에서 지우고
		return minTicketingTimeCustomer;	// 고객 리턴(티켓팅 대기 큐의 고객 중에서, 티켓팅 처리 시간이 가장 작은 고객)
	}
	
	
	@Override
	public void sendTicketReadyCustomerToTicketProcessingQueue() {	// SJF(가장 짧은 작업시간 우선순위 알고리즘)
		
		for(int i = 0; i < iTempS2; i++){
			if(QueueStruct.TicketProcessingQueue[i] == null) {				// 티켓 처리 부스에 빈 칸이 있으면
				if(!QueueStruct.CustomerTicketReadyQueue.isEmpty()) {		// 티켓 래디 큐(역)에 도착한 사람이 있다면
					QueueStruct.TicketProcessingQueue[i] = selectShortestJobCustomer();		// 티켓 처리 부스로 최소의 티켓팅 고객 한 명을 보내고	
				}	
			}
			else {				
				// 티켓처리 0인 사람 내보내고, 그 자리에 최소 티켓 시간 고객 넣기.
				if(QueueStruct.TicketProcessingQueue[i].tempTimeOfCustomerTicketing == 0){ 				// 티켓 처리 시간이 0초되면 부스에서 나가기
					QueueStruct.CustomerReadyforTrainQueue.add(QueueStruct.TicketProcessingQueue[i]);	// 열차 대기 큐로 보내기
					QueueStruct.TicketProcessingQueue[i] = null;										// 나간 부스 자리는 빈자리로
					if(!QueueStruct.CustomerTicketReadyQueue.isEmpty()) {						// 티켓 래디 큐(역)에 도착한 사람이 있다면
						QueueStruct.TicketProcessingQueue[i] = selectShortestJobCustomer();		// 티켓 처리 부스로 최소의 티켓팅 고객 한 명을 보내고
					}
				}
				
				else {
					if(!QueueStruct.CustomerTicketReadyQueue.isEmpty()) {		// 티켓 래디 큐(역)에 도착한 사람이 있다면
						for(int j = 0; j < QueueStruct.CustomerTicketReadyQueue.size(); j ++) {	// 티켓 부스의 고객이 티켓팅 대기 큐의 처리시간 보다 크면 고객 교환하기
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
				QueueStruct.TicketProcessingQueue[k].tempTimeOfCustomerTicketing -= 1;					// 티켓 소요시간 1빼기
				if(QueueStruct.TicketProcessingQueue[k].tempTimeOfCustomerTicketing == 0){ 				// 티켓 처리 시간이 0초되면 부스에서 나가기
					QueueStruct.CustomerReadyforTrainQueue.add(QueueStruct.TicketProcessingQueue[k]);	// 열차 대기 큐로 보내기
					QueueStruct.TicketProcessingQueue[k] = null;										// 나간 자리는 빈자리로
					
					if(!QueueStruct.CustomerTicketReadyQueue.isEmpty()) {								// 티켓 래디 큐(역)에 도착한 사람이 있다면
						QueueStruct.TicketProcessingQueue[k] = selectShortestJobCustomer();				// 티켓 처리 부스로 최소의 티켓팅 고객 한 명을 보내고	
					}	
				}	
			}	
		}
		
		for(int j = 0; j < QueueStruct.CustomerTicketReadyQueue.size(); j++) {
			QueueStruct.CustomerTicketReadyQueue.get(j).timeOnStandbyOfTicket += 1;		// 고객의 티켓 대기 시간 1 더하기
		}
		
	} // end of method

	
	
	@Override
	public void sendCustomerAtPlatformToGettingOnTrain() {				// ReadyforTrainQueue에 있는 고객 -> TrainAtPlatformQueue로 보내어 기차 태우기(매 3초마다)
		if(!QueueStruct.CustomerReadyforTrainQueue.isEmpty()) {			// 플랫폼에서 열차를 기다리는 고객이 있다면
			iTempS3 = QueueStruct.CustomerReadyforTrainQueue.size();
			for(int i = 0; i < iTempS3; i++) {						// 대기중인 모든 고객을 열차에 태우기
				QueueStruct.TrainAtPlatformQueue.add(QueueStruct.CustomerReadyforTrainQueue.get(0));
				QueueStruct.CustomerReadyforTrainQueue.remove(0);
			}
		}
		
		if(timeNow != 0 && (timeNow % 3) == 0) {	// 열차 큐(TrainAtPlatformQueue)를 3초마다 출발시키기
			iTempS3 = QueueStruct.TrainAtPlatformQueue.size();
			for(int i = 0; i < iTempS3; i++) {
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
		
}
   

