package isort.sort;

import isort.ISort;

public class SortMethod {
	public static ISort getParser(String type) {
		if(type.equals("Q"))
			return new QuickSort(); // ISort s = new QuickSort(); // return s;
		else if(type.equals("B")) 
			return new BubbleSort();
		else if(type.equals("I")) 
			return new InsertionSort();
		else
			return new NullClass(); // return warning message.
		
	}
}

class QuickSort implements ISort {

	@Override
	public void sort() {
		System.out.println("Quick sorting is operated.");
	}
	
}

class BubbleSort implements ISort {

	@Override
	public void sort() {
		System.out.println("Bubble sorting is operated.");
	}
	
}

class InsertionSort implements ISort {

	@Override
	public void sort() {
		System.out.println("Insertion sorting is operated.");	
	}
	
}

class NullClass implements ISort { // 입력이 "Q", "B", "I"가 아닐때 return 할 클래스
	public void sort() { // print "warning message" only
		System.out.println("You inputed wrong value. Please try again among (Q,B,I).");
	}
}
