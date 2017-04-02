import java.util.ArrayList;

public class Course {
	private String courseName;
	private String courseDepartment;
	private String courseNumber;
	private float courseGrade;
	private ArrayList<GradeType> gradingPolicy = new ArrayList<>();
	
	public void addGradeToGradeingPolicy(int element){
		gradingPolicy.get(element).addGrade();
		
	}
	public void displayGradesOfAGradeType(int element){
		gradingPolicy.get(element).displayGrades();
	}
	public void addGradingPolicy(String typeName, float gradeWeight){
		GradeType placeHolderGradeType = new GradeType( typeName , gradeWeight );
		gradingPolicy.add(placeHolderGradeType);
	}
	public void displayGradingPolicy(){
		System.out.println();
		System.out.println( "These are the current courses that have been added" );
		for(int i = 0 ; i < gradingPolicy.size() ; i ++ ){
			System.out.println( (i+1) +". "+ gradingPolicy.get(i).toString() );
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
		for(int i = 0 ; i < gradingPolicy.size() ; i ++){
			gradingPolicy.get(i).totalOfGrades();
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
		return this.getCourseNumber() + " " + this.getCourseDepartment() + " " + this.getCourseName() + " ";
	}
	public int selectGradeType(){
		int gradeTypeElement = gradingPolicy.size();
		displayGradingPolicy();
		gradeTypeElement = Utility.getInt( "Please Type the number of the course you would like to select. " )  ;
		if ( gradeTypeElement < 1 || gradeTypeElement > gradingPolicy.size() ){
			System.out.println( "You have made an incorrect selection please try again" );
			gradeTypeElement = selectGradeType();
		}
		return gradeTypeElement - 1;
	}
}
