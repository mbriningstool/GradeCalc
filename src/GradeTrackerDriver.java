import java.util.ArrayList;
import java.util.Objects;
public class GradeTrackerDriver {	
	static ArrayList<Course> registeredCourses = new ArrayList<>();
	public static void main(String[] args) {
		int mainMenuSelection = 0;
		int subMenuSelection = 0;
		int element = 0;
		while( mainMenuSelection != 5 ){
			displayMainMenu();
			subMenuSelection = 0;
			mainMenuSelection = Utility.getInt( "Please make a selection: " );
			/*Start of main menu*/
			switch( mainMenuSelection ){
				case 1:
				/*Start of Course menu*/
				while( subMenuSelection != 4 ){
					displayCourseMenu();
					subMenuSelection = Utility.getInt( "Please make a selection: " );
					switch( subMenuSelection ){
						case 1:
							addCourse();
							break;
						case 2:
							editCourse();							
							break;
						case 3:
							displayCourses();
							break;
						case 4:
							break;
						default:
							break;
					}
				}
				break;
				/*End of Course menu*/
				case 2:
					/*Start of Grade Policy menu*/
					while( subMenuSelection != 4 ){
						displayGradePolicyMenu();
						subMenuSelection = Utility.getInt( 
								"Please make a selection: " );
						switch(subMenuSelection){
							case 1:
								element = selectCourse();
								while( subMenuSelection != 2 ){
									registeredCourses.get( element ).
									addGradingPolicy( Utility.getString( "What is the type of grade you want to add to "
										+ "the grading policy e.g. Quiz ?" ) , Utility.getFloat(
										"What is the weighted percentage of the grade type? " ) );
									
									subMenuSelection = Utility.
											getInt( "Do you want to add another"
											+ " grade type? Type 1 (yes) or 2 (no)" );
								}
								break;
							case 2:
								editGradePolicy();
								break;
							case 3:
								element = selectCourse();;
								registeredCourses.get( element ).displayGradingPolicy();
								break;
							case 4:
								break;
							default:
								break;
						}
					}
					break;
					/*End of Grade Policy menu*/
				case 3:
					/*Start of Grade Menu*/
					while( subMenuSelection != 4 ){
						displayGradeMenu();
						subMenuSelection = Utility.getInt(
								"Please make a selection: " );
						switch( subMenuSelection ){
							case 1:
								element = selectCourse();
								registeredCourses.get( element ).
									addGradeToGradeingPolicy(registeredCourses
										.get( element ).selectGradeType() );
								break;
							case 2:
								editGrade();
								break;
							case 3:
								element = selectCourse();
								registeredCourses.get( element).
									displayGradesOfAGradeType( registeredCourses.
											get( element ).selectGradeType() );
								break;
							case 4:
								break;
							default:
								break;
						}
						
					}
					break;
					/*End of Grade Menu*/
				case 4:
					/*Start of Course Grade Menu*/
					while( subMenuSelection != 4 ){
						displayCourseGradeMenu();
						subMenuSelection = Utility.getInt("Please make a selection: ");
						switch( subMenuSelection ){
							case 1:
								element = selectCourse();
								registeredCourses.get( element ).displayCourseGrade();
								break;
							case 2:
								break;
							case 3:
								for(int i = 0 ; i < registeredCourses.size() ; i ++ ){
									registeredCourses.get( i ).displayCourseGrade();
								}
								break;
							case 4:
								break;
							default:
								break;
						}
					}
					break;
				default:
					break;
					/*End of main menu*/
			}
		}
	}
public static void displayMainMenu(){
	System.out.println( "1. Course Menu" );
	System.out.println( "2. Grading policy menu" );
	System.out.println( "3. Grade menu" );
	System.out.println( "4. Display course grades menu" );
	System.out.println( "5. Exit" );
}
public static void displayGradePolicyMenu(){
	System.out.println( "1. Add grading policy" );
	System.out.println( "2. Edit grading policy" );
	System.out.println( "3. Display grading policy" );
	System.out.println( "4. Return to main menu" );
	}
public static void displayGradeMenu(){
	System.out.println( "1. Add grade" );
	System.out.println( "2. Edit grade" );
	System.out.println( "3. Display grades" );
	System.out.println( "4. Return to main menu" );
}
public static void displayCourseMenu(){
	System.out.println( "1. Add course" );
	System.out.println( "2. Edit course" );
	System.out.println( "3. List courses" );
	System.out.println( "4. Return to main menu" );
}
public static void displayCourseGradeMenu(){
	System.out.println( "1. Display grade of a course" );
	System.out.println( "2. Display all grades for a course" );
	System.out.println( "3. Display grades of all courses" );
	System.out.println( "4. Return to main menu" );
}
public static void displayCourses(){
	System.out.println();
	System.out.println( "These are the current courses that have been added" );
	for ( int i =0; i < registeredCourses.size(); i++ ){
		System.out.print( ( i + 1 ) + ". " + registeredCourses.get( i ).toString() );
		System.out.println();
	}
	System.out.println();
}
public static int selectCourse(){
	int courseElement = registeredCourses.size();
	displayCourses();
	courseElement = Utility.
			getInt( "Please Type the number of the course you would like to select. " );
	
	if (courseElement < 1 || courseElement > registeredCourses.size() ){
		System.out.println( "You have made an incorrect selection please try again" );
		courseElement = selectCourse();
	}
	return courseElement - 1;
}
public static void addCourse(){
	String courseName = Utility.getString( "What would you like to name the course?" );
	String courseDepartment = Utility.getString( "What department is the course in?" );
	String courseNumber = Utility.getString( "What is the course number?" );
	
	Course placeHolderCourse = new Course( courseName , courseDepartment , courseNumber );
	registeredCourses.add( placeHolderCourse );
}
public static void editCourse(){
	int element = selectCourse();
	
	String courseName = Utility.getString( "What would you like to name the course?" );
	String courseDepartment = Utility.getString( "What department is the course in?" );
	String courseNumber = Utility.getString( "What is the course number?" );
	
	registeredCourses.get(element).setCourseName( courseName );
	registeredCourses.get(element).setCourseDepartment( courseDepartment );
	registeredCourses.get(element).setCourseNumber( courseNumber );
}
public static void editGradePolicy(){
	int courseElement = selectCourse();
	int policyElement = registeredCourses.get( courseElement ).selectGradeType();
	
	String typeName = Utility.getString( "What type of grade is this? " );
	float gradeWeight = Utility.getFloat( "What is the weight of the grade? " );
	
	registeredCourses.get( courseElement ).getGradingPolicy().
		get( policyElement ).setTypeName( typeName );
	registeredCourses.get( courseElement ).getGradingPolicy().
		get( policyElement ).setGradeWeight( gradeWeight );
	
	
}
public static void editGrade(){
	int courseElement = selectCourse();
	int policyElement = registeredCourses.get( courseElement ).selectGradeType();
	int gradeElement = registeredCourses.get( courseElement ).
			getGradingPolicy().get(policyElement).selectGrade();
	
	String name = Utility.getString( "What is the name of this grade?" );
	float total = Utility.
			getFloat( "What is the maximum number of points for the grade? " );
	float received = Utility.
			getFloat( "How many points did you receive for the grade? " );
	
	registeredCourses.get( courseElement ).getGradingPolicy().
		get( policyElement ).getIndividualGrades().get( gradeElement ).
			setGradeName( name );
	registeredCourses.get( courseElement ).getGradingPolicy().
		get( policyElement ).getIndividualGrades().get( gradeElement ).
			setTotalPoints( total );
	registeredCourses.get( courseElement ).getGradingPolicy().
		get( policyElement ).getIndividualGrades().get( gradeElement ).
			setReceivedPoints( received );
}
public static int getElement(String courseName){
	int element = registeredCourses.size();
	for (int i = 0 ; i < registeredCourses.size() ; i++ ){
		if(Objects.equals(courseName , registeredCourses.get( i ).getCourseName() ) ){
			element = i ;
		}
	}
	if (element == registeredCourses.size() ){
		element = -1;
	}
	return element;
}
}
