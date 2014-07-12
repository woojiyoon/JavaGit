package birthdayhomework;

//import java.io.InputStreamReader;

public class BirthDayMain {

	public static void main(String[] args){

		BirthDay day = new BirthDay();
		day.setYear(2000);
		day.setMonth(2);
		System.out.println(day.setDay(29));
		//System.out.println(day.checkValidate());
		
		BirthDay day2 = new BirthDay(2014,7,10);
		System.out.println(day2.valide);
		
		BirthDay day3 = new BirthDay();
		System.out.println(day3.setDate(2100, 2, 29));
	}

}
