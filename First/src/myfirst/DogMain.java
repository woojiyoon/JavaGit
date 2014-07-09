package myfirst;

public class DogMain {

	public static void main(String[] args) {
		//int i = 100;
		//Integer i2 = new Integer(100); // Â÷ÀÌÁ¡?
		
		//Dog dog = new Dog("poppi","a");
		//dog.name = "¶Ë°³";
		//dog.type = "Çªµé";
		Person James = new Person("James",40);
		//James.age = 40;
		James.marriage = true;
		James.numberOfchildren = 4;
		James.marriage = true;
		//James.setName("James");
		
		System.out.println(James.name);
		System.out.println(James.age);
		System.out.println(James.marriage);
		System.out.println(James.numberOfchildren);

	}

}
