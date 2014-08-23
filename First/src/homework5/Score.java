package homework5;

public class Score {
	private String studentNumber; // studnetNumber is key for map
	private int korScore;
	private int mathScore;
	private int totalScore;
	
	public Score(String studentNumber, int korScore, int mathScore) {
		this.studentNumber = studentNumber;
		this.korScore = korScore;
		this.mathScore = mathScore;
	}

	public int getKorScore() {
		return korScore;
	}

	public void setKorScore(int korScore) {
		this.korScore = korScore;
	}

	public int getMathScore() {
		return mathScore;
	}

	public void setMathScore(int mathScore) {
		this.mathScore = mathScore;
	}

	public int getTotalScore() {
		totalScore = korScore + mathScore; 
		return totalScore;
	}

	public void setTotalScore(int totalScore) {
		this.totalScore = totalScore;
	}

	public String getstudentNumber() {
		return studentNumber;
	}

	public void setStudentNumber(String studentNumber) {
		this.studentNumber = studentNumber;
	}

}
