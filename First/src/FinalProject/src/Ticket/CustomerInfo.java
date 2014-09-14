package Ticket;

public class CustomerInfo {
	
	/************************************************************************** 
		
		������(CustomerInfo)Ŭ������ ��� ������ 
		
	**************************************************************************/
	private int idOfCustomer;	// �� ���̵�
	private String nameOfCustomer;	// ���̸�
	private String nameOfDepartureCity;	// ��� ���� �̸�
	private String nameOfArrivalCity;	// ���� ���� �̸�
	private int timeOfCustomerArrivalAtStation;	// ���� ���� ������ �ð�
	private int timeOfCustomerTicketing;	// ���� Ƽ���� �ϴ� �� �ҿ�� �ð�
	private int timeOnStandbyOfTicket;	// ���� ���� Ƽ���� ���ð�(Ƽ���������� ����)
	private int timeOnStandbyOfTrain;	// ���� ���� ���ð�(������ Ÿ�� ���� ��߽ð����� ����� �ð�)
	private int timeOnDepartureOfTrain;	// ���� ��߽ð�(�� 3�� ���� �����)
	private int timeOnArrivalOfTrain;	// ���� ���� ���� �ð�(�����~������)
	
	
	
	/************************************************************************
	  
	 	�⺻ ������
	
	*************************************************************************/
	public CustomerInfo() { }


	
	
	
	@Override
	public String toString() {
		return this.getIdOfCustomer() + ":" + this.getNameOfCustomer()
				+ ":" + this.getTimeOfCustomerArrivalAtStation()
				+ ":" + this.getTimeOfCustomerTicketing()
				+ ":" + this.getNameOfDepartureCity()
				+ ":" + this.getNameOfArrivalCity();
	}





	/************************************************************************** 
	
	������(CustomerInfo)Ŭ������ ��� �����鿡 ���� getters, setters �޼���

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
	
}
