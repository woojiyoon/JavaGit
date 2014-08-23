package homework5;

public class Student implements Comparable<Student>{
	private String studentName;
	private String studentNumber; // key for map
	Score score;
	
	public Student(String studentName, String studentNumber) {
		this.studentName = studentName;
		this.studentNumber = studentNumber;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getstudentNumber() {
		return studentNumber;
	}

	public Score getScore() {
		return score;
	}

	public void setScore(Score score) {
		this.score = score;
	}

	public void setstudentNumber(String studentNumber) {
		this.studentNumber = studentNumber;
	}

	// 같은 학번은 저장이 안되므로 equals 필요 없다???
//	@Override
//	public boolean equals(Object obj) {
//		Student student = (Student) obj;
//		if(this.getstudentNumber().equals(student.getstudentNumber()))
//			return true;
//		else
//			return false;
//	}

	@Override
	public int compareTo(Student anotherStudent) {
		int thisTotalScore = this.getScore().getTotalScore();
		int anotherTotalScore = anotherStudent.getScore().getTotalScore();
		
		return (thisTotalScore > anotherTotalScore ? -1 : (thisTotalScore == anotherTotalScore ? 0 : 1));
	}
	
}
