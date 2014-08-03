package grades;
// This implement is for non-major grades
public class NonMajor implements IGrades {

	@Override
	public String gradeType(int score) {
		if(score >= 90)
			return "A";
		else if(score >= 80)
			return "B";
		else if(score >= 70)
			return "C";
		else if(score >= 55)
			return "D";
		else
			return "F";
	}
	
}
