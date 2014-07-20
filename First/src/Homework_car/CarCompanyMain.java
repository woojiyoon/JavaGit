package Homework_car;

public class CarCompanyMain {

	public static void main(String[] args) {
		
		CarCompany headquater = CarCompany.getInstance("Hyundai");
		
		CarCompany[] CarList = new CarCompany[3];
		CarList[0] = new Sonata("Sonata 2", 3, 3000);
		CarList[1] = new Grandeur("Grandeur XG", 3, 4000);
		CarList[2] = new Genesis("Genesis 2012", 3, 5000);
		
		System.out.println("자동차 가격의 합 : " + Calculating.Cal(CarList));
		
	}

}
