import java.util.ArrayList;

public class GradeType {
	
	private String typeName;
	private float gradeWeight;
	private float sectionGrade;
	private ArrayList<Grade> individualGrades = new ArrayList<>();
	
	public GradeType(String typeName, float gradeWeight){
		this.typeName = typeName;
		while(gradeWeight > 100 || gradeWeight <= 0){
			System.out.println("The grade weight must be a number greater than 0 and less than 100. ");
			gradeWeight = Utility.getInt("Please try again: ");
		}
		this.gradeWeight = gradeWeight /100;
	}
	public String getTypeName() {
		return typeName;
	}
	public  void totalOfGrades(){
		float grade = 0;
		float received = 0;
		float total = 0;
		for(int i = 0 ; i < individualGrades.size() ; i ++){
			received += individualGrades.get(i).getReceivedPoints();
			total += individualGrades.get(i).getTotalPoints();
		}
		grade = received / total;
		this.sectionGrade = this.gradeWeight * grade; 
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public float getGradeWeight() {
		return gradeWeight;
	}
	public void setGradeWeight(float gradeWeight) {
		this.gradeWeight = gradeWeight;
	}
	public float getSectionGrade() {
		return sectionGrade;
	}
	public void setSectionGrade(float sectionGrade) {
		this.sectionGrade = sectionGrade;
	}
	public ArrayList<Grade> getIndividualGrades() {
		return individualGrades;
	}
	public void addGrade() {
		String name =  new String(typeName + " " + (individualGrades.size()+1) );
		float total = Utility.getFloat("What is the maximum number of points for the grade? ");
		float received = Utility.getFloat("How many points did you receive for the grade? ");
		Grade tempGrade = new Grade(name ,total ,received );
		individualGrades.add(tempGrade);
	}
	
	public void displayGrades(){
		System.out.println();
		System.out.println("Below are your current " + typeName + " grades.");
		for (int i =0; i < individualGrades.size(); i ++){
			System.out.println(individualGrades.get(i));
		}
		System.out.println();
	}
	public String toString(){
		return this.getTypeName() + " " + this.getGradeWeight() *100 +"%";
	}
		
}
