package ticket;

public class CustomerInfo {
	
	/************************************************************************** 
		
		고객정보(CustomerInfo)클래스의 멤버 변수들 
		
	**************************************************************************/
	private int idOfCustomer;					// 고객 아이디
	private String nameOfCustomer;				// 고객이름
	private String nameOfDepartureCity;			// 출발 도시 이름
	private String nameOfArrivalCity;			// 도착 도시 이름
	private int timeOfCustomerArrivalAtStation;	// 고객이 역에 도착한 시간
	private int timeOfCustomerTicketing;		// 고객이 티케팅 하는 데 소요할 시간(실제 값)
	public int tempTimeOfCustomerTicketing;		// 고객이 티케팅 하는 데 소요할 시간(임시 값)
	private int timeOnStandbyOfTicket;			// 고객의 실제 티켓팅 대기시간(티케팅전략에 따라, 래디 큐에 머문시간)
	private int timeOnStandbyOfTrain;			// 고객의 열차 대기시간(열차를 타고 열차 출발시간까지 대기한 시간)
	private int timeOnDepartureOfTrain;			// 열차 출발시간(매 3분 마다 출발함)
	private int timeOnArrivalOfTrain;			// 고객의 열차 도착 시간(출발지~도착지)
	private int timeDurationOfTrain;			// 기차가 달리기 시작해서 도착할 때까지 걸린 순수 이동시간(대기시간 제외, 최소경로로 달린 시간)
	
	
	/************************************************************************
	  
	 	기본 생성자
	
	*************************************************************************/
	public CustomerInfo() { }


	
	
	
	@Override
	public String toString() {
		return this.getIdOfCustomer() + ":" + this.getNameOfCustomer()	// id, 이름
				+ ":" + this.getTimeOfCustomerArrivalAtStation()		// 역 도착시간 (출발지)
				+ ":" + this.getTimeOfCustomerTicketing()				// 티켓팅 소요할 시간
				+ ":" + this.getNameOfDepartureCity()					// 출발지 이름
				+ ":" + this.getNameOfArrivalCity()						// 도착지 이름
				+ ":(최소경로=>)" + this.getTimeDurationOfTrain()			// 최소 경로 시간
				+ ">" + this.getTimeOnStandbyOfTicket()					// 티켓팅 대기 시간(프로세스 알고리즘에 의해 대기한 시간)
				+ ">" + this.getTimeOnStandbyOfTrain()					// 열차 대기 시간
				+ ">" + this.getTimeOnDepartureOfTrain()				// 열차 출발 시간 (매 3분마다)
				+ ">" + this.getTimeOnArrivalOfTrain();					// 열차 도착 시간 (열차 출발시간 + 최소경로 시간)	
	}





	/************************************************************************** 
	
	고객정보(CustomerInfo)클래스의 멤버 변수들에 대한 getters, setters 메서드

 **************************************************************************/
	public int getIdOfCustomer() {
		return idOfCustomer;
	}

	
	public void setIdOfCustomer(int idOfCustomer) {
		this.idOfCustomer = idOfCustomer;
	}


	public String getNameOfCustomer() {
		return nameOfCustomer;
	}


	public void setNameOfCustomer(String nameOfCustomer) {
		this.nameOfCustomer = nameOfCustomer;
	}


	public String getNameOfDepartureCity() {
		return nameOfDepartureCity;
	}


	public void setNameOfDepartureCity(String nameOfDepartureCity) {
		this.nameOfDepartureCity = nameOfDepartureCity;
	}


	public String getNameOfArrivalCity() {
		return nameOfArrivalCity;
	}


	public void setNameOfArrivalCity(String nameOfArrivalCity) {
		this.nameOfArrivalCity = nameOfArrivalCity;
	}


	public int getTimeOfCustomerArrivalAtStation() {
		return timeOfCustomerArrivalAtStation;
	}


	public void setTimeOfCustomerArrivalAtStation(int timeOfCustomerArrivalAtStation) {
		this.timeOfCustomerArrivalAtStation = timeOfCustomerArrivalAtStation;
	}


	public int getTimeOfCustomerTicketing() {
		return timeOfCustomerTicketing;
	}

	
	public void setTimeOfCustomerTicketing(int timeOfCustomerTicketing) {
		this.timeOfCustomerTicketing = timeOfCustomerTicketing;
		this.tempTimeOfCustomerTicketing = timeOfCustomerTicketing;
	}


	public int getTimeOnStandbyOfTicket() {
		return timeOnStandbyOfTicket;
	}


	public void setTimeOnStandbyOfTicket(int timeOnStandbyOfTicket) {
		this.timeOnStandbyOfTicket = timeOnStandbyOfTicket;
	}


	public int getTimeOnStandbyOfTrain() {
		return timeOnStandbyOfTrain;
	}


	public void setTimeOnStandbyOfTrain(int timeOnStandbyOfTrain) {
		this.timeOnStandbyOfTrain = timeOnStandbyOfTrain;
	}


	public int getTimeOnDepartureOfTrain() {
		return timeOnDepartureOfTrain;
	}


	public void setTimeOnDepartureOfTrain(int timeOnDepartureOfTrain) {
		this.timeOnDepartureOfTrain = timeOnDepartureOfTrain;
	}


	public int getTimeOnArrivalOfTrain() {
		return timeOnArrivalOfTrain;
	}


	public void setTimeOnArrivalOfTrain(int timeOnArrivalOfTrain) {
		this.timeOnArrivalOfTrain = timeOnArrivalOfTrain;
	}
	
	
	public int getTimeDurationOfTrain() {
		return timeDurationOfTrain;
	}

	
	public void setTimeDurationOfTrain(int timeDurationOfTrain) {
		this.timeDurationOfTrain = timeDurationOfTrain;
	}
	
}
