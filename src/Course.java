import java.util.ArrayList;

public class Course {
	private String courseName;
	private String courseDepartment;
	private String courseNumber;
	private float courseGrade;
	private boolean isGradedByWeight = true;
	
	public boolean getIsGradedByWeight() {
		return isGradedByWeight;
	}

	public void setGradedByWeight(boolean isGradedByWeight) {
		this.isGradedByWeight = isGradedByWeight;
	}
	private ArrayList<GradeType> gradingPolicy = new ArrayList<>();
	
	public ArrayList<GradeType> getGradingPolicy() {
		return gradingPolicy;
	}
		
	public void displayGradesOfAGradeType(int element){
		gradingPolicy.get(element).displayGrades();
	}
	
	public void displayGradingPolicy(){
		System.out.println();
		System.out.println( "These are the current courses that have been added" );
		for(int i = 0 ; i < gradingPolicy.size() ; i ++ ){
			System.out.println( (i+1) +". "+ gradingPolicy.get( i ).toString() );
			System.out.println();
		}
	}
	public void displayCourseGrade(){
		System.out.println();
		System.out.println();
		totalGradeTypes();
		System.out.println(courseName + " " + (courseGrade * 100) + "%");
		
	}
	public Course(String courseName, String courseDepartment, String courseNumber){
		this.setCourseName(courseName);
		this.setCourseDepartment(courseDepartment);
		this.setCourseNumber(courseNumber);
	}
	public void totalGradeTypes(){
		float subTotal = 0;
		float received = 0;
		float total = 0;
			//calculating points based grading
			if ( this.isGradedByWeight == false ){
				for(int i = 0 ; i < gradingPolicy.size() ; i ++){
					gradingPolicy.get(i).calculateSectionPoints();
					received += gradingPolicy.get(i).getTotalReceivedPoints();
					total += gradingPolicy.get(i).getTotalPossiblePoints();
				}
				this.courseGrade = received / total;
				return;
			}
		//calculating grade weight based grading	
		for(int i = 0 ; i < gradingPolicy.size() ; i ++){
			gradingPolicy.get(i).calculateSectionGrade();
			subTotal += gradingPolicy.get(i).getSectionGrade();
		}
		this.courseGrade = subTotal;
	}
	
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
			
	public String getCourseNumber() {
		return courseNumber;
	}
	public void setCourseNumber(String courseNumber) {
		this.courseNumber = courseNumber;
	}
	public String getCourseDepartment() {
		return courseDepartment;
	}
	public void setCourseDepartment(String courseDepartment) {
		this.courseDepartment = courseDepartment;
	}
	public String toString(){
		return this.getCourseNumber() + " " + 
				this.getCourseDepartment() + " " + 
				this.getCourseName() + " ";
	}
	public int selectGradeType(){
		int gradeTypeElement = gradingPolicy.size();
		displayGradingPolicy();
		gradeTypeElement = Utility.
				getInt( "Please Type the number of the course you would like to select. " )  ;
		if ( gradeTypeElement < 1 || gradeTypeElement > gradingPolicy.size() ){
			System.out.println( "You have made an incorrect selection please try again" );
			gradeTypeElement = selectGradeType();
		}
		return gradeTypeElement - 1;
	}
}
