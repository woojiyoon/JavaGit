package ticket;

/****************************************************************

<스케줄링 알고리즘> : First-Come, First-Served (FCFS) Scheduling

=> 가장 간단한 스케줄링 알고리즘인 선입 선처리(FCFS) 스케줄링 알고리즘 이다. 이 방법에서는
선입선출 큐에 먼저 들어온 순서대로 프로세스 작업을 할당한다. 

****************************************************************/

import java.util.ArrayList;

public class TicketStrategyFCFS implements ITicketStrategy{
	
	private int timeNow = 0;	// 현재 시간 (1초씩 상승하여 마지막 고객을 큐에 넣을 때까지....)
	private boolean getOffWork = false;	// 기차역 직원이 퇴근할 시간이 되면 getOffWork = true 가 된다.(모든 고객이 기차타고 떠나게 되면 퇴근시간임.)
	public boolean isTrainOkToDepart = false;		// 매 3분마다 기차는 출발하고, isTrainOkToDepart = true 가 된다. 기차가 역에서 대기할 때는 false 임.
	public int timeOfTrainDeparture = 0;	// 기차 출발 시간
	public int numberOfAllCustomers = 0;
	public int iTempSize1;
	public int iTempSize2;
	public int iTempSize3;
	
	public void FCFSstartToEnd() throws InterruptedException {
		
		iTempSize1 = QueueStruct.CustomerOriginalDataQueue.size();	// 오리지날 데이터에 있는 고객의 총 수
		iTempSize2 = QueueStruct.TicketProcessingQueue.length;		// 티켓 처리 부스의 총 갯수 (3부스)
		
		while(!getOffWork) {
			System.out.println("timeNow : " + timeNow);
			// 여러가지 큐에 대한 작업
			sendOriginalCustomerToTicketReadyQueue();
			sendTicketReadyCustomerToTicketProcessingQueue();
			sendCustomerAtPlatformToGettingOnTrain();
			
//			try {
//				Thread.sleep(3000);
//			} catch (InterruptedException e) {
//				
//				e.printStackTrace();
//			}
			
			if(timeNow == 48)
				break;
			// 1초가 지나고
			timeNow++;
		}
		
		//QueueStruct.AllDisplayOfQueueInfo(QueueStruct.CustomerReadyforTrainQueue);
		QueueStruct.AllDisplayOfQueueInfo(QueueStruct.CustomerFinalDataQueue);
	}
	
	public void sendOriginalCustomerToTicketReadyQueue() {
		for(int i = 0; i < iTempSize1; i++) {	
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
				System.out.println("오리지널 큐에 데이터 없음. timeNow :" + timeNow);
				return;
			}
		}	
	}
	
	
	public void sendTicketReadyCustomerToTicketProcessingQueue() throws InterruptedException {
		
		for(int i = 0; i < iTempSize2; i++){
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
						
						if(!QueueStruct.CustomerTicketReadyQueue.isEmpty()) {								// 티켓 래디 큐(역)에 도착한 사람이 있다면
							QueueStruct.TicketProcessingQueue[i] = QueueStruct.CustomerTicketReadyQueue.get(0);		// 티켓 처리 부스로 고객 한 명을 보내고
							QueueStruct.CustomerTicketReadyQueue.remove(0);									// 그 고객을 티켓 래디 큐에서는 지운다.	
						}	
					}
				}
			}
			
		}
		
		for(int j = 0; j < QueueStruct.CustomerTicketReadyQueue.size(); j++) {
			QueueStruct.CustomerTicketReadyQueue.get(j).timeOnStandbyOfTicket += 1;		// 고객의 티켓 대기 시간 1 더하기
		}
		
		for(int k = 0; k < iTempSize2; k++){
			if(QueueStruct.TicketProcessingQueue[k] != null) {
				System.out.println(QueueStruct.TicketProcessingQueue[k].getIdOfCustomer() + " : " 
						+ QueueStruct.TicketProcessingQueue[k].tempTimeOfCustomerTicketing);
				
			}
		}
			//Thread.sleep(2000);
			System.out.println("=======");
		
	}
	
	
	
	public void sendCustomerAtPlatformToGettingOnTrain () {				// ReadyforTrainQueue에 있는 고객 -> TrainAtPlatformQueue로 보내어 기차 태우기(매 3초마다)
		if(!QueueStruct.CustomerReadyforTrainQueue.isEmpty()) {			// 플랫폼에서 열차를 기다리는 고객이 있다면
			iTempSize3 = QueueStruct.CustomerReadyforTrainQueue.size();
			for(int i = 0; i < iTempSize3; i++) {						// 대기중인 모든 고객을 열차에 태우기
				QueueStruct.TrainAtPlatformQueue.add(QueueStruct.CustomerReadyforTrainQueue.get(0));
				QueueStruct.CustomerReadyforTrainQueue.remove(0);
			}
		}
		
		if(timeNow != 0 && (timeNow % 3) == 0) {	// 열차 큐(TrainAtPlatformQueue)를 3초마다 출발시키기
			iTempSize3 = QueueStruct.TrainAtPlatformQueue.size();
			for(int i = 0; i < iTempSize3; i++) {
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
	
	
	
}	// end of public class TicketStrategyFCFS
