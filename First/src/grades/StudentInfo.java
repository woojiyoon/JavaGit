package grades;

public class StudentInfo {
	static int count = 999; // variable for counting the student of instances
	public String studentName;	// student's name
	private String studentMajor;	// Major or Non-Major
	private String mathGrade;	// S, A, B, C, D, F in Math
	private String engGrade;	// S, A, B, C, D, F in English
	private int studentNumber; // 1000, 1001, 1002,.....
	private int mathScore;	// 0 ~ 100 score
	private int engScore;	// 0 ~ 100 score
	
	// block for initializing members of instance
	{
		++count; // ++count when every instances of StudentInfo class made
		studentNumber = count;
	}
	
	// make the instance of StudentInfo class only with name, major and scores at first
	public StudentInfo(String studentName, String studentMajor, int mathScore, int engScore) {
		this.studentName = studentName;
		this.studentMajor = studentMajor;
		this.mathScore = mathScore;
		this.engScore = engScore;
		
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public int getStudentNumber() {
		return studentNumber;
	}
	
	public void setStudentNumber(int studentNumber) {
		this.studentNumber = studentNumber;
	}
	public String getStudnetMajor() {
		return studentMajor;
	}

	public void setStudentMajor(String studentMajor) {
		this.studentMajor = studentMajor;
	}

	public String getMathGrade() {
		return mathGrade;
	}

	public void setMathGrade(int mathScore) { // Math part
		IGrades temp; // we don't know student is on major or not.
		if(this.studentMajor == "Mathematics"){
			System.out.println("You are on Major");
			temp  = new Major(); // You are on major
		}
		else {
			System.out.println("You are on Non-Major");
			temp = new NonMajor(); // You are not on major
		}

		this.mathGrade = temp.gradeType(mathScore); // decide grade
	}

	public String getEngGrade() {
		return engGrade;
	}

	public void setEngGrade(int engScore) { // English part
		IGrades temp;	// we don't know student is on major or not.
		if(this.studentMajor == "English"){
			System.out.println("You are on Major");
			temp  = new Major(); // you are on major
		}
		else {
			System.out.println("You are on Non-Major");
			temp = new NonMajor(); // you are not on major
		}
			
		this.engGrade = temp.gradeType(engScore); // decide grade
	}

	public int getMathScore() {
		return mathScore;
	}

	public void setMathScore(int mathScore) {
		this.mathScore = mathScore;
	}

	public int getEngScore() {
		return engScore;
	}

	public void setEngScore(int engScore) {
		this.engScore = engScore;
	}

}
