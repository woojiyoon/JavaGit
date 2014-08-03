package grades;

public class GradesMain {

	public static void main(String[] args) {
		StudentInfo stuBox[] = new StudentInfo[5];
		// StudentInfo("이름","전공",수학점수,영어점수);
		
		stuBox[0] = new StudentInfo("이승기","Mathematics",95,20);
		stuBox[1] = new StudentInfo("김수현","English",90,100);
		stuBox[2] = new StudentInfo("주원","Mathematics",76,60);
		stuBox[3] = new StudentInfo("김우빈","Mathematics",85,89);
		stuBox[4] = new StudentInfo("이민호","English",90,95);
				
		// automatically student number is increasing. Let's see
		System.out.println("<자동적으로 생성된 학번 보기>");
		for(int i = 0; i < stuBox.length; i ++){
			System.out.println(stuBox[i].getStudentName() + ": " + stuBox[i].getStudentNumber());
		}
		
		System.out.println(); // for blank
		
		// Let's see the grade of Mathematics
		System.out.println("<학생들의 전공에 따른 수학 등급 보기>");
		for(int i = 0; i < stuBox.length; i++){
			stuBox[i].setMathGrade(stuBox[i].getMathScore());
			System.out.println(stuBox[i].getStudentName() + ": " + stuBox[i].getMathGrade());
		}
		
		System.out.println(); // for blank
		
		// Let's see the grade of English
		System.out.println("<학생들의 전공에 따른 영어 등급 보기>");
		for(int i = 0; i < stuBox.length; i++){
			stuBox[i].setEngGrade(stuBox[i].getEngScore());
			System.out.println(stuBox[i].getStudentName() + ": " + stuBox[i].getEngGrade());
		}
	}

}
