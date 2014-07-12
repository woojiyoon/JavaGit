// checkvalidate()메소드를 따로 빼서 사용하지 않고, setYear, setMonth, setDay 메소드에 값을 넣을 때
// 값이 유효한지 판단하고, 윤년인지 판단하게 끔 코드를 바꾸어 봤습니다.
package birthdayhomework;

public class BirthDay {
	public int year;
	public int month;
	public int day;
	public boolean leapyear = false; // true(윤년o), false(윤년x)
	public boolean valide = false;
	
	public BirthDay(){}
	public BirthDay(int year, int month, int day){
		setYear(year);
		setMonth(month);
		valide = setDay(day);
	}
	public void setYear(int year){
		if(year < 0){
			System.out.println("잘못된 입력입니다.(0년 이상만 가능)");
			return;
		}
		if((year % 4) == 0 && (year % 100) != 0 || (year % 400) == 0) // 윤년이면
			leapyear = true;
		else
			leapyear = false;
		this.year = year;
	}
	public void setMonth(int month){
		if(month < 1 || month > 12){ // 1~12월 사이값이 아니면 잘못된 입력이므로 
			System.out.println("잘못된 입력입니다(1월~12월만 가능).");
			return;
		}
		this.month = month;
	}
	public boolean setDay(int day){
		if(day < 1 || day > 31){
			System.out.println("잘못된 입력입니다(1일~31일만 가능).");
			return false;
		}
		if(month == 1 || month == 3 || month == 5 || month == 7 || month == 8 
				|| month == 10 || month == 12){ // 31일까지 있는 달이면
			if(day <= 31)
				return true;
			else 
				return false;
		}
		else if(month == 2){ // 2월이면
			if(leapyear){ // 윤년이면
				if(day <= 29)
					return true;
				else 
					return false;
			}
			else{	// 윤년이 아니면
				if(day <= 28)
					return true;
				else
					return false;
			}
		}
		else{  // 30일 까지 있는 달이면
			if(day <= 30)
				return true;
			else
				return false;
		}
	}
	public boolean setDate(int year, int month, int day){
		setYear(year);
		setMonth(month);
		return setDay(day);
	}
	// checkValidate()를 사용하지 않는다면
	/*
	public boolean checkValidate(){
		int y = this.year;
		int m = this.month;
		int d = this.day;
		
	
		if(m == 1 || m == 3 || m == 5 || m == 7 || m == 8 || m == 10 || m == 12){ // 31일까지 있는 달이면
			if(d >= 1 && d <= 31)
				return true;
			else
				return false;
		}
		else if(m == 4 || m == 6 || m == 9 || m == 11){ // 30일까지 있는 달이면
			if(d >= 1 && d <= 30)
				return true;
			else
				return false;
		}
		else if(m == 2){ // 2월이면
			if((y % 4) == 0 && (y % 100) != 0 || (y % 400) == 0){ // 윤년이면
				if(d >= 1 && d <= 29) // 29일까지 가능
					return true;
				else
					return false;
			}
			else{ // 윤년이 아니면
				if(d >= 1 && d <= 28)
					return true;
				else
					return false;
			}	
		}
		else // 달이 1~12월이 아니면
			return false;	
	}
	*/
}
