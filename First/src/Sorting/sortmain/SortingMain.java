package sortmain;

import isort.ISort;
import isort.sort.SortMethod;

public class SortingMain {

	public static void main(String[] args) {
		ISort bubble = SortMethod.getParser("B");
		bubble.sort();
		
		ISort quick = SortMethod.getParser("Q");
		quick.sort();
		
		ISort insertion = SortMethod.getParser("I");
		insertion.sort();
		
		ISort wrong = SortMethod.getParser("K"); // wrong input
		wrong.sort();  // warning message, and show options available
	}

}
