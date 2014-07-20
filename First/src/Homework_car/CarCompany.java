package Homework_car;

class CarCompany {
	
	static String nameOfCompany; // 회사이름 , 모든 객체가 동일(현대자동차)
	String nameOfCar;	// 자동차의 이름
	int numberOfCar;  // 자동차 갯수
	int priceOfCar;	// 자동차 가격
	
	private static CarCompany Hyundai = new CarCompany(nameOfCompany); // singleton
	
	//public CarCompany(){}
	private CarCompany(String nameOfCompany) {
		this.nameOfCompany = nameOfCompany;
		System.out.println("Hyundai 인스턴스가 생성되었습니다.");
		
	}
	public static CarCompany getInstance(String nameOfCompany) {
		if(Hyundai == null)
			Hyundai = new CarCompany(nameOfCompany);
		return Hyundai;
	}
	
	public CarCompany(){}
	public CarCompany(String nameOfCar, int numberOfCar, int priceOfCar) {
		this.nameOfCar = nameOfCar;
		this.numberOfCar = numberOfCar;
		this.priceOfCar = priceOfCar;
	}
	
}

class Sonata extends CarCompany{
	
	Sonata(){
		this("이름없음",0,0);
	}
	public Sonata(String nameOfCar, int numberOfCar, int priceOfCar){
		this.nameOfCar = nameOfCar;
		this.numberOfCar = numberOfCar;
		this.priceOfCar = priceOfCar;
		System.out.println("소나타 인스턴스가 생성되었습니다.");
	}
	
}

class Grandeur extends CarCompany{
	
	public Grandeur(String nameOfCar, int numberOfCar, int priceOfCar){
		this.nameOfCar = nameOfCar;
		this.numberOfCar = numberOfCar;
		this.priceOfCar = priceOfCar;
		System.out.println("그렌저 인스턴스가 생성되었습니다.");
	}
}

class Genesis extends CarCompany{
	
	public Genesis(String nameOfCar, int numberOfCar, int priceOfCar){
		this.nameOfCar = nameOfCar;
		this.numberOfCar = numberOfCar;
		this.priceOfCar = priceOfCar;
		System.out.println("제네시스 인스턴스가 생성되었습니다.");
	}
}

class Calculating {  // 자동차의 값을 계산하는 부분
	public static int Cal(CarCompany[] Ltd){
		int result = 0;
		
		for(int i = 0; i < Ltd.length; i++){
			result += ( Ltd[i].priceOfCar * Ltd[i].numberOfCar);  // 자동차 가격만 합산한다.
			//System.out.println(Ltd[i].priceOfCar);
		}
		return result;
	}
}

