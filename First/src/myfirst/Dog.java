package myfirst;

public class Dog {
	public static final int FEMALE = 1;
	public static final int MALE = 2;
	
	public String type;
	public String name;
	private int gender = MALE;
	
	//public Dog(){}
	public Dog(String name, String type){
		this.name = name;
		this.type = type;
	}
	
	public int getGender() {
		return gender;
	}
	public void setGender(int gender) {
		this.gender = gender;
	}
	
	
}
