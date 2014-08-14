package maze;

public class MyStack implements MyStackInterface{
	
	private int maxSize;	// size of stack array
	private int[] stackArray;	// stack array
	private int top; // top of stack array
	
	public MyStack(int size) {	// constructor
		maxSize = size;			// array size
		stackArray = new int[maxSize];	// make array
		top = -1;					// no element at first time
	}
	
	@Override
	public void push(int element) { // put element on top of stack
		if(isFull()) {
			System.out.println("stack if full.");
			return;
		}
		stackArray[++top] = element; // increment top, insert element
	}
	
	@Override
	public int pop() {	// take element from top of stack
		if(isEmpty()) {
			System.out.println("stack is empty.");
			return 0;
		}
		
		return stackArray[top--]; // access element, decrement element
}
	@Override
	public int size() {
		if(top < 0)
			return 0;	// size 0
		return top;		// size of element is top
	}
	@Override
	public int peek() { // peek at top of stack
		
		return stackArray[top]; // just return top array
	}
	
	@Override
	public boolean isEmpty() {
		
		return (top == -1); // if top == -1, then return true
	}
	
	@Override
	public boolean isFull() {
		return (top >= maxSize -1); // if top == maxSize -1, then return true
	}
	
}
