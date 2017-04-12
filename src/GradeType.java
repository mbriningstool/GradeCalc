import java.util.ArrayList;
import java.io.Serializable;

public class GradeType implements Serializable{
	public GradeType(String typeName, float gradeWeight){
		this.typeName = typeName;
		while(gradeWeight > 100 || gradeWeight <= 0){
			System.out.println("The grade weight must be greater than 0 and less than 100. ");
			gradeWeight = Utility.getInt("Please try again: ");
		}
		this.gradeWeight = gradeWeight /100;
	}
	
	private static final long serialVersionUID = 1L;
	private String typeName;
	private float gradeWeight;
	private float sectionGrade;
	private float totalPossiblePoints;
	private float totalReceivedPoints;
	private ArrayList<Grade> individualGrades = new ArrayList<>();
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Float.floatToIntBits(gradeWeight);
		result = prime * result + Float.floatToIntBits(totalPossiblePoints);
		result = prime * result + ((typeName == null) ? 0 : typeName.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GradeType other = (GradeType) obj;
		if (Float.floatToIntBits(gradeWeight) != Float.floatToIntBits(other.gradeWeight))
			return false;
		if (Float.floatToIntBits(totalPossiblePoints) != Float.floatToIntBits(other.totalPossiblePoints))
			return false;
		if (typeName == null) {
			if (other.typeName != null)
				return false;
		} else if (!typeName.equals(other.typeName))
			return false;
		return true;
	}
	public float getTotalPossiblePoints() {
		return totalPossiblePoints;
	}
	public float getTotalReceivedPoints() {
		return totalReceivedPoints;
	}
	public ArrayList<Grade> getIndividualGrades() {
		return individualGrades;
	}
	public void setIndividualGrades(ArrayList<Grade> individualGrades) {
		this.individualGrades = individualGrades;
	}
	
	public String getTypeName() {
		return typeName;
	}
	public  void calculateSectionGrade(){
		calculateSectionPoints();
		float grade = 0;
		grade = this.totalReceivedPoints / totalPossiblePoints;
		this.sectionGrade = this.gradeWeight * grade; 
	}
	public void calculateSectionPoints(){
		float received = 0;
		float total = 0;
		for(int i = 0 ; i < individualGrades.size() ; i ++){
			received += individualGrades.get(i).getReceivedPoints();
			total += individualGrades.get(i).getTotalPoints();
		}
		this.totalPossiblePoints = total;
		this.totalReceivedPoints = received;
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