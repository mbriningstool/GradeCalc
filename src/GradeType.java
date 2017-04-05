import java.util.ArrayList;

public class GradeType {
	
	private String typeName;
	private float gradeWeight;
	private float sectionGrade;
	private ArrayList<Grade> individualGrades = new ArrayList<>();
	
	public ArrayList<Grade> getIndividualGrades() {
		return individualGrades;
	}
	public void setIndividualGrades(ArrayList<Grade> individualGrades) {
		this.individualGrades = individualGrades;
	}
	public GradeType(String typeName, float gradeWeight){
		this.typeName = typeName;
		while(gradeWeight > 100 || gradeWeight <= 0){
			System.out.println("The grade weight must be greater than 0 and less than 100. ");
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
		this.gradeWeight = gradeWeight / 100;
	}
	public float getSectionGrade() {
		return sectionGrade;
	}
	public void setSectionGrade(float sectionGrade) {
		this.sectionGrade = sectionGrade;
	}
		
	public int selectGrade(){
		int element = individualGrades.size();
		displayGrades();
		element = Utility.
				getInt( "Please Type the number of the course you would like to select. " )  ;
		if ( element < 1 || element > individualGrades.size() ){
			System.out.println( "You have made an incorrect selection please try again" );
			element = selectGrade();
		}
		return element - 1;
	}
	public void displayGrades(){
		System.out.println();
		System.out.println("Below are your current " + typeName + " grades.");
		for (int i =0; i < individualGrades.size(); i ++){
			System.out.println( ( i + 1 )+". "+ individualGrades.get(i).toString() );
		}
		System.out.println();
	}
	public String toString(){
		return this.getTypeName() + " " + this.getGradeWeight() *100 +"%";
	}
		
}
