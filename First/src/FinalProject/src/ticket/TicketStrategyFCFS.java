package ticket;

/****************************************************************

<스케줄링 알고리즘> : First-Come, First-Served (FCFS) Scheduling

=> 가장 간단한 스케줄링 알고리즘인 선입 선처리(FCFS) 스케줄링 알고리즘 이다. 이 방법에서는
선입선출 큐에 먼저 들어온 순서대로 프로세스 작업을 할당한다. 

****************************************************************/

import java.util.ArrayList;

public class TicketStrategyFCFS implements ITicketStrategy{
	
	private int timeNow = 0;	// 시간 (1초씩 상승하여 마지막 고객을 큐에 넣을 때까지....)
	private boolean getOffWork = false;	// 기차역 직원이 퇴근할 시간이 되면 getOffWork = true 가 된다.(모든 고객이 기차타고 떠나게 되면 퇴근시간임.)
	public boolean isTrainOkToDepart = false;		// 매 3분마다 기차는 출발하고, isTrainOkToDepart = true 가 된다. 기차가 역에서 대기할 때는 false 임.
	public int timeOfTrainDeparture = 0;	// 기차 출발 시간
	public int numberOfAllCustomers = 0;
	
	// 래디 큐 최상의 인덱스에 있던 고객을 한명씩 티켓 부스큐로 옮기는 메서드
	public void startQueuetoEndTime(QueueStruct qs) {
		
		numberOfAllCustomers = qs.CustomerOriginalDataQueue.size();
		
		while(!getOffWork) {	// 기차역 직원이 퇴근할 시간이 아니면 고객이 계속 있는 거니까 while문 계속 돌기(일 계속하기)
			
			if( this.isGetOffWork() == true) {	// 오리지날 데이터에 고객이 더 이상 없다. 전부 다 기차(getOffWork == true)
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
					
				/* 보류 (넥스트 큐 작업)
				
				// (이부분은 필요 없을지도...) sendOneCustomerToPlatform(qs);
				customerGetOnTrain(qs);
				*/
			}
			
			this.setTimeNow(this.getTimeNow() + 1);		// 현실 세계 시간 + 1 (=> 매 3분 마다 기차가 출발한다.)
		}
		System.out.println("breakpoint");
		qs.AllDisplayOfQueueInfo(qs.TrainAtPlatformQueue); // (3)
		// qs.AllDisplayOfQueueInfo(qs.CustomerReadyforTrainQueue);	// (2)	
		// qs.AllDisplayOfQueueInfo(qs.CustomerTicketReadyQueue);	// (1)
		// 보류(파이날 데이터) qs.AllDisplayOfQueueInfo(qs.TrainAtPlatformQueue);
	}
	
	

	public void sendOneCustomerToTicketReady(QueueStruct qs) {
		int iSize = qs.CustomerOriginalDataQueue.size();
		
		if(iSize == 0) {	// 오리지날 데이터 큐에 고객이 더 이상 없으면,
			System.out.println("오리지날 데이터 큐에 더 이상 고객이 없습니다.");
			return;
			//this.setGetOffWork(true);
		}
		
		for(int i = 0; i < iSize; i++) {
			if(qs.CustomerOriginalDataQueue.isEmpty() != true && qs.CustomerOriginalDataQueue.get(0) != null ) { // 오리지날 데이터 큐에 고객이 여전히 남아있다면
				if(qs.CustomerOriginalDataQueue.get(0).getTimeOfCustomerArrivalAtStation() == this.timeNow) { // 현재 시간과 고객의 역 도착예정시간이 같으면 고객이 역에 도착한 것이므로
					qs.CustomerTicketReadyQueue.add(qs.CustomerOriginalDataQueue.get(0));	// 오리지날 데이터 큐에 있던 고객을 티켓 래디큐로 한명씩 보내기
					qs.CustomerOriginalDataQueue.remove(0);	// 보낸 뒤에, 오리지날 데이터 큐에서는 그 고객을 지우기(최상위 인덱스는 역에 빨리 도착하는 고객으로 유지)
				}
				else {
					break;
				}
			}
		}
	}
		
	
	public void sendOneCustomerToTicketBooth(QueueStruct qs) {	// TicketBooth = TicketProcessingQueue
		int iLength1 = qs.TicketProcessingQueue.length;	// 티켓 대기 큐 전체 길이 (티켓 부스는 총 3곳, iLength1 = 3)
		
		
		for(int i = 0; i < iLength1; i++) {	
			if(qs.TicketProcessingQueue[i] == null) {
				qs.TicketProcessingQueue[i] = qs.CustomerTicketReadyQueue.get(0);	// 비어있는 티켓 부스에 고객을 올리고 
				qs.CustomerTicketReadyQueue.remove(0);								// 티켓 래디큐에 있는 최상위 고객은 지우기.
			}
			
			else if(qs.TicketProcessingQueue[i] != null && qs.TicketProcessingQueue[i].tempTimeOfCustomerTicketing == 0) {	// 티켓 부스 i번째 부스에 있는 고객의 티켓팅 소요시간이 0이라면(티켓팅이 다 끝났다면)
				qs.CustomerReadyforTrainQueue.add(qs.TicketProcessingQueue[i]);	// 티켓 부스에 있던 고객을 열차를 기다리는 플랫폼 큐로 보내기.
				qs.TicketProcessingQueue[i] = null;	// 티켓 부스는 빈곳으로
				
				if(qs.TicketProcessingQueue[i] == null) {	// 티켓 부스 3곳 중 한 곳이라도 비었다면
					// qs.TicketProcessingQueue[i] = new CustomerInfo();
					qs.TicketProcessingQueue[i] = qs.CustomerTicketReadyQueue.get(0);	// CustomerInfo(고객) 객체인 ticketProcessingQueue[i]에는 래디 큐에서 넘어온 고객 객체가 담긴다
																						// 원래는 담긴다기 보다는, 하나의 객체를 가리키는 참조 변수가 2개가 되는 것임. 새로 생긴 참조변수가 ticketProcessingQueue[i]임
					qs.CustomerTicketReadyQueue.remove(0);								// 래디 큐에 있던 고객을 티켓 부스에 보낸 뒤, 래디 큐에 있던 고객 지우기
				}
			}
			
			else {	// 티켓 부스 i번째 부스에 있는 고객의 티켓팅 소요시간이 아직 남았다면 
				if(qs.TicketProcessingQueue[i].tempTimeOfCustomerTicketing < 0) {
					System.out.println("티켓팅 소요시간이 음수가 되었습니다. 알고리즘에 문제가 있음.");
				}
				else {
					qs.TicketProcessingQueue[i].tempTimeOfCustomerTicketing -= 1;		// 티켓 부스안에 있는 고객의 티켓 처리 시간 -1 (처리되는 중이라는 것, 이게 0 이되면 이 티켓 부스를 나감)
				}
			
			}
		}
		
		if(qs.CustomerTicketReadyQueue.size() == 0) {
			System.out.println("티케팅 대기 큐에 고객이 더 이상 없습니다.");
//			this.setGetOffWork(true);
			return;
		}
		
		
		// 티켓 부스가 꽉 차든, 꽉 안 차든, TicketReadyQueue에 남은 사람은 티켓 대기시간 + 1 이 됨
		int iLength2 = qs.CustomerTicketReadyQueue.size();	// remove를 거쳐서 남은 고객 티켓 대기 큐(remove는 고객을 티켓 부스 3곳중 한곳으로 보내기 위해 한 작업임.)
		int iTemp = 0;
		for(int j = 0; j < iLength2; j ++) {
			iTemp = qs.CustomerTicketReadyQueue.get(j).getTimeOnStandbyOfTicket();
			qs.CustomerTicketReadyQueue.get(j).setTimeOnStandbyOfTicket(iTemp + 1);	// 고객 티켓 대기 큐에 있는 티켓팅 대기시간 + 1 
		}
	}
	

	
//	public void sendOneCustomerToPlatform(QueueStruct qs) {
//		int iLength = qs.TicketProcessingQueue.length;
//		
//		for(int i = 0; i < iLength; i++) {
//			// 티켓처리 부스 큐에 있는 고객이 티켓팅 처리를 다해서 티켓팅 소요시간이 0이 되면
//			if(qs.TicketProcessingQueue[i] != null && qs.TicketProcessingQueue[i].getTimeOfCustomerTicketing() == 0) { 
//				qs.CustomerReadyforTrainQueue.add(qs.TicketProcessingQueue[i]);	// 한 고객을 플랫폼 큐로 보내어 기차를 기다리게 하기 
//			}
//		}
//	}
	
	
	public void customerGetOnTrain(QueueStruct qs) {
		
		if(this.timeNow != 0 && (this.timeNow/3) == 0) {	// 매 3분이 되면 기차가 들어왔고 출발이 가능하다
			this.timeOfTrainDeparture = this.timeNow;		// 기차 출발 시간(매 3분)
			this.isTrainOkToDepart = true;					// 기차 출발 가능 (OK)
		}
		else {	// 기차 출발 시간이 아니면
			int iSize = qs.TrainAtPlatformQueue.size();
			if(iSize != 0) {
				int iTemp1 = 0;
				
				for(int i = 0; i < iSize; i++) {
					iTemp1 = qs.TrainAtPlatformQueue.get(i).getTimeOnStandbyOfTrain();		// 기차가 출발하기를 기다리는 고객 정보 중 열차 대기시간 정보를 가져와서
					qs.TrainAtPlatformQueue.get(i).setTimeOnStandbyOfTrain(iTemp1 + 1);	// 고객이 열차탑승을 위해 대기한 시간 +1 하기.
				}
				
				this.isTrainOkToDepart = false;
			}
		}
		
		if(this.timeNow != 0 && this.isTrainOkToDepart == true) {	// 기차가 플랫폼에 들어왔고, 출발이 가능하다면 (매 3분마다 들어옴)
			int iTemp2 = 0;
			int iSize = qs.CustomerReadyforTrainQueue.size();
			
			for(int i = 0; i < iSize; i++) {	// (플랫폼에서) 기차를 기다리던 모든 고객을 기차에 탑승시키기
				if(qs.CustomerReadyforTrainQueue.get(0) != null) {			// 기다리던 고객이 있다면
					
					qs.CustomerReadyforTrainQueue.get(0).setTimeOnDepartureOfTrain(this.timeNow);	// 열차의 출발시간을 고객의 열차 출발정보에 기록하고,
					
					iTemp2 = qs.CustomerReadyforTrainQueue.get(0).getTimeDurationOfTrain();			// 고객의 최소경로 시간을 가져와서
					qs.CustomerReadyforTrainQueue.get(0).setTimeOnArrivalOfTrain(this.timeNow + iTemp2);	// 열차출발 시간 + 고객의 최소경로 시간 = 고객의 열차 도착 시간
					
					qs.TrainAtPlatformQueue.add(qs.CustomerReadyforTrainQueue.get(0));	// 기차에 탑승시키기
					qs.CustomerReadyforTrainQueue.remove(0);	// 기차를 기다리던 고객 큐에서 지우고(기차 탔으니까)
					
				}
				else {
					System.out.println("현재 플랫폼에서 기차를 기다리는 고객이 없습니다.");
				}
			}
		}
		
		
		// qs.TrainAtPlatformQueue 가 고객의 최종 데이터가 된다.(목적지에 도착한 고객들의 정보를 모은 리스트 데이터)
		
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
