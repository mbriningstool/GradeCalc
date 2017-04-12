import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
public class GradeTrackerDriver {	
	static ArrayList<Course> registeredCourses = new ArrayList<>();
	public static void main(String[] args) {
		boolean exitMainMenu = false;
		boolean exitSubMenu = false;
		int courseElement = -1;
		int policyElement = -1;
		File inputFile = null;
		File outputFile = null;
				
		while( !exitMainMenu ){
			
			/*Start of main menu*/
			switch( displayMainMenu() ){
				case 1:
					exitSubMenu = false;
				/*Start of Course menu*/
				while( !exitSubMenu ){
					
					switch( displayCourseMenu() ){
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
							exitSubMenu = true;
							break;
						default:
							break;
					}
				}
				break;
				/*End of Course menu*/
				case 2:
					exitSubMenu = false;
					/*Start of Grade Policy menu*/
					while( !exitSubMenu ){
						
						switch( displayGradePolicyMenu() ){
							case 1:
								if( courseElement == -1 ){
									addGradeType();
									break;
								}
								addGradeType(courseElement);									
								break;
							case 2:
								if( courseElement == -1 ){
									editGradePolicy();
									break;
								}
								editGradePolicy(courseElement);
								break;
							case 3:
								if( courseElement == -1 ){
									displayGradePolicy();
									break;
								}
								displayGradePolicy(courseElement);
								break;
							case 4:
								exitSubMenu = true;
								break;
							default:
								break;
						}
					}
					break;
					/*End of Grade Policy menu*/
				case 3:
					exitSubMenu = false;
					/*Start of Grade Menu*/
					while( !exitSubMenu ){
						
						switch( displayGradeMenu() ){
							case 1:
								if( courseElement == -1 && policyElement == -1){
									addGrade();
									break;
								}
								else if ( policyElement == -1){
									addGrade( courseElement );
									break;
								}
								addGrade( courseElement , policyElement );
								break;
							case 2:
								if( courseElement == -1 && policyElement == -1){
									editGrade();
									break;
								}
								else if ( policyElement == -1 ){
									editGrade( courseElement );
									break;
								}
								editGrade( courseElement , policyElement );
								break;
							case 3:
								if( courseElement == -1 && policyElement == -1){
									displayGradesOfAGradeType();
									break;
								}
								else if ( policyElement == -1 ){
									displayGradesOfAGradeType( courseElement );
									break;
								}
								displayGradesOfAGradeType( courseElement , policyElement );
								break;
							case 4:
								exitSubMenu = true;
								break;
							default:
								break;
						}
						
					}
					break;
					/*End of Grade Menu*/
				case 4:
					exitSubMenu = false;
					/*Start of Course Grade Menu*/
					while( !exitSubMenu ){
						
						switch( displayCourseGradeMenu() ){
							case 1:
								if ( courseElement == -1){
									registeredCourses.
										get( selectCourse() ).displayCourseGrade();
									break;
								}
								registeredCourses.
									get( courseElement ).displayCourseGrade();
								break;
							case 2:
								if( courseElement == -1 ){
									displayCourseGrades();
									break;
								}
								displayCourseGrades( courseElement );
								break;
							case 3:
								
								for(int i = 0 ; i < registeredCourses.size() ; i ++ ){
									registeredCourses.get( i ).displayCourseGrade();
								}
								break;
							case 4:
								exitSubMenu = true;
								break;
							default:
								break;
						}
					}
					/*End of Course Grade Menu*/
					break;
				case 5:
					courseElement = selectCourse();
					if ( courseElement == -1 ){
						System.out.println( "You must add a course before"
								+ " you can select a default course. " );
					}
					break;
				case 6:
					if ( courseElement == -1 ){
						System.out.println( "You must set a default course "
								+ "before you can set a default grade type. " );
						break;
					}
					policyElement = registeredCourses.
							get(courseElement).selectGradeType();
					if ( policyElement == -1 ){
						System.out.println( "You must add a grade type before"
								+ " you can select a default grade type." );
					}
					break;
				case 7:
					courseElement = -1;
					policyElement = -1;
					break;
				case 8:
					//case 5 allows the user to save course data they have input
					//into a file for later use
					outputFile = Utility.getFileSaveLocation( ".dat" );
					try(
						ObjectOutputStream output = new 
								ObjectOutputStream( new 
										FileOutputStream( outputFile , true ) ) ;
					){
						output.writeObject( registeredCourses );
					}catch(IOException e){
						System.out.println( "The file was unable to be saved. " );
					}
					break;
				case 9:
					//case 6 allows the user to load course data that has been saved.
					inputFile = Utility.getFileLoadLocation();
					try(ObjectInputStream input = new 
							ObjectInputStream( new FileInputStream( inputFile ) ) ;
					){
						registeredCourses = ( ArrayList<Course> )( input.readObject() );
					}catch(IOException e ){
						System.out.println( "Your file did not load." );
					}catch(ClassNotFoundException e){
						System.out.println( "The class was not found" );
					}

					
					break;
				case 10:
					exitMainMenu = true;
					break;
				default:
					break;
					/*End of main menu*/
			}
		}
	}
public static int displayMainMenu(){
	System.out.println();
	System.out.println( "1. Course Menu" );
	System.out.println( "2. Grading policy menu" );
	System.out.println( "3. Grade menu" );
	System.out.println( "4. Display course grades menu" );
	System.out.println( "5. Set default course" );
	System.out.println( "6. Set default grade type" );
	System.out.println( "7. Reset default course and grade type" );
	System.out.println( "8. Save" );
	System.out.println( "9. Load" );
	System.out.println( "10. Exit" );
	return Utility.getInt( "Please make a selection: ", 1 , 10 );
}
public static int displayGradePolicyMenu(){
	System.out.println();
	System.out.println( "1. Add grading policy" );
	System.out.println( "2. Edit grading policy" );
	System.out.println( "3. Display grading policy" );
	System.out.println( "4. Return to main menu" );
	return Utility.getInt( "Please make a selection: ", 1 , 4 );
	}
public static int displayGradeMenu(){
	System.out.println();
	System.out.println( "1. Add grade" );
	System.out.println( "2. Edit grade" );
	System.out.println( "3. Display grades" );
	System.out.println( "4. Return to main menu" );
	return Utility.getInt( "Please make a selection: ", 1 , 4 );
}
public static int displayCourseMenu(){
	System.out.println();
	System.out.println( "1. Add course" );
	System.out.println( "2. Edit course" );
	System.out.println( "3. List courses" );
	System.out.println( "4. Return to main menu" );
	return Utility.getInt( "Please make a selection: ", 1 , 4 );
}
public static int displayCourseGradeMenu(){
	System.out.println();
	System.out.println( "1. Display grade of a course" );
	System.out.println( "2. Display all grades for a course" );
	System.out.println( "3. Display grades of all courses" );
	System.out.println( "4. Return to main menu" );
	return Utility.getInt( "Please make a selection: ", 1 , 4 );
}
public static void displayCourses(){
	System.out.println();
	System.out.println( "These are the current courses that have been added" );
	for ( int i = 0 ; i < registeredCourses.size() ; i ++ ){
		System.out.print( ( i + 1 ) + ". " + registeredCourses.get( i ).toString() );
		System.out.println();
	}
	System.out.println();
}
public static void displayCourseGrades(){
	int courseElement = selectCourse();
	int policySize = registeredCourses.get( courseElement ).getGradingPolicy().size();
	int gradeSize = 0 ;
	
	for (int i = 0 ; i < policySize ; i ++){
		gradeSize = registeredCourses.get( courseElement ).getGradingPolicy().
				get(i).getIndividualGrades().size();
		for(int x = 0 ; x < gradeSize ; x++ ){
			
			System.out.println(registeredCourses.get( courseElement ).getGradingPolicy().
					get( i ).getIndividualGrades().get( x ) );
		}
	}
}
public static void displayGradePolicy(){
	int courseElement = selectCourse();
	int policySize = registeredCourses.get( courseElement ).getGradingPolicy().size();
	System.out.println();
	System.out.println( "These are the current courses that have been added" );
	for(int i = 0 ; i < policySize ; i ++ ){
		System.out.println( ( i + 1 ) + ". " + registeredCourses.get( courseElement ).
				getGradingPolicy().get( i ).toString() );
		System.out.println();
	}
}
public static void displayGradePolicy( int courseElement){
	int policySize = registeredCourses.get( courseElement ).getGradingPolicy().size();
	System.out.println();
	System.out.println( "These are the current courses that have been added" );
	for(int i = 0 ; i < policySize ; i ++ ){
		System.out.println( ( i + 1 ) + ". " + registeredCourses.get( courseElement ).
				getGradingPolicy().get( i ).toString() );
		System.out.println();
	}
}
public static void displayGradesOfAGradeType(){
	int courseElement = selectCourse();
	int policyElement = registeredCourses.get( courseElement ).selectGradeType();
	registeredCourses.get( courseElement ).getGradingPolicy().
		get( policyElement ).displayGrades();
}
public static void displayGradesOfAGradeType( int courseElement ){
	int policyElement = registeredCourses.get( courseElement ).selectGradeType();
	registeredCourses.get( courseElement ).getGradingPolicy().
		get( policyElement ).displayGrades();
}
public static void displayGradesOfAGradeType( int courseElement , int policyElement ){
	registeredCourses.get( courseElement ).getGradingPolicy().
		get( policyElement ).displayGrades();
}
public static void displayCourseGrades( int courseElement ){
	int policySize = registeredCourses.get( courseElement ).getGradingPolicy().size();
	int gradeSize = 0 ;
	
	for (int i = 0 ; i < policySize ; i ++){
		gradeSize = registeredCourses.get( courseElement ).getGradingPolicy().
				get( i ).getIndividualGrades().size();
		for(int x = 0 ; x < gradeSize ; x++ ){
			
			System.out.println(registeredCourses.get( courseElement ).getGradingPolicy().
					get( i ).getIndividualGrades().get( x ));
		}
	}
}
public static int selectCourse(){
	//this method displays a list to the user of all the added courses
	//and allows the user to select the element
	int courseElement = registeredCourses.size();
	if( courseElement == 0 ){
		return -1;
	}
	displayCourses();
	courseElement = Utility.
			getInt( "Please Type the number of the course you would like to select. " );
	
	if ( courseElement < 1 || courseElement > registeredCourses.size() ){
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
		if( (Utility.
			getInt( "Does the course use a weighted grading system? 1 (yes) 2 (no)" ) )
			== 2){
			placeHolderCourse.setGradedByWeight( false );
		}
		
	registeredCourses.add( placeHolderCourse );
}
public static void addGradeType(){
	int courseElement = selectCourse();
		
	String typeName = Utility.getString( "What type of grade is this? " );
	float gradeWeight = 1;
	
	//a gradeWeight is only collected if the course is graded by gradeWeight
	if( registeredCourses.get( courseElement ).getIsGradedByWeight() == true ){
		gradeWeight = Utility.getFloat( "What is the weight of the grade? " );
	}
		
	GradeType placeHolderGradeType = new GradeType( typeName , gradeWeight );
	registeredCourses.get( courseElement ).getGradingPolicy().
		add( placeHolderGradeType );
}
public static void addGradeType(int courseElement){
	String typeName = Utility.getString( "What type of grade is this? " );
	float gradeWeight = 1;
	
	//a gradeWeight is only collected if the course is graded by gradeWeight
	if( registeredCourses.get( courseElement ).getIsGradedByWeight() == true ){
		gradeWeight = Utility.getFloat( "What is the weight of the grade? " );
	}
		
	GradeType placeHolderGradeType = new GradeType( typeName , gradeWeight );
	registeredCourses.get( courseElement ).getGradingPolicy().
		add( placeHolderGradeType );
}
public static void addGrade(){
	int courseElement = selectCourse();
	int policyElement = registeredCourses.get( courseElement ).selectGradeType();
	
	String typeName = registeredCourses.get( courseElement ).getGradingPolicy().
			get( policyElement ).getTypeName();
	int individualGradeSize = registeredCourses.
			get( courseElement ).getGradingPolicy().get( policyElement ).
			getIndividualGrades().size();
	
	String name = typeName + " " + ( individualGradeSize + 1 );
	float total = Utility.
			getFloat( "What is the maximum number of points for the grade? " );
	float received = Utility.
			getFloat( "How many points did you receive for the grade? " );
	
	Grade tempGrade = new Grade( name ,total ,received );
	registeredCourses.get( courseElement ).getGradingPolicy().
		get(policyElement).getIndividualGrades().add( tempGrade );
}
public static void addGrade(int courseElement){
	int policyElement = registeredCourses.get( courseElement ).selectGradeType();
	
	String typeName = registeredCourses.get( courseElement ).getGradingPolicy().
			get( policyElement ).getTypeName();
	int individualGradeSize = registeredCourses.
			get( courseElement ).getGradingPolicy().get( policyElement ).
			getIndividualGrades().size();
	
	String name = typeName + " " + ( individualGradeSize + 1 );
	float total = Utility.
			getFloat( "What is the maximum number of points for the grade? " );
	float received = Utility.
			getFloat( "How many points did you receive for the grade? " );
	
	Grade tempGrade = new Grade( name ,total ,received );
	registeredCourses.get( courseElement ).getGradingPolicy().
		get(policyElement).getIndividualGrades().add( tempGrade );
}
public static void addGrade(int courseElement, int policyElement){
	String typeName = registeredCourses.get( courseElement ).getGradingPolicy().
			get( policyElement ).getTypeName();
	int individualGradeSize = registeredCourses.
			get( courseElement ).getGradingPolicy().get( policyElement ).
			getIndividualGrades().size();
	
	String name = typeName + " " + ( individualGradeSize + 1 );
	float total = Utility.
			getFloat( "What is the maximum number of points for the grade? " );
	float received = Utility.
			getFloat( "How many points did you receive for the grade? " );
	
	Grade tempGrade = new Grade( name , total , received );
	registeredCourses.get( courseElement ).getGradingPolicy().
		get( policyElement ).getIndividualGrades().add( tempGrade );
}
public static void editCourse(){
	int courseElement = selectCourse();
	if ( courseElement == - 1 ){
		System.out.println( "Before you can edit a course you must add a course." );
		return;
	}
	
	String courseName = Utility.getString( "What would you like to name the course?" );
	String courseDepartment = Utility.getString( "What department is the course in?" );
	String courseNumber = Utility.getString( "What is the course number?" );
	
	//below the attributes of the selected course are changed to the user supplied
	//attributes
	registeredCourses.get( courseElement ).setCourseName( courseName );
	registeredCourses.get( courseElement ).setCourseDepartment( courseDepartment );
	registeredCourses.get( courseElement ).setCourseNumber( courseNumber );
}
public static void editGradePolicy(){
	int courseElement = selectCourse();
	// -1 return from selectCourse() indicates the list is empty
		if ( courseElement == - 1 ){
			System.out.println(
					"Before you can edit a grade policy you must add a course." );
			return;
		}
	int policyElement = registeredCourses.get( courseElement ).selectGradeType();
	// -1 return from selectGradeType() indicates the list is empty
		if ( policyElement == - 1 ){
			System.out.println(
					"Before you can edit a grade policy you must add a grade policy." );
			return;
		}
	
	String typeName = Utility.getString( "What type of grade is this? " );
	float gradeWeight = Utility.getFloat( "What is the weight of the grade? " );
	
	//below the attributes of the selected gradeType are changed to the user supplied
	//attributes
	registeredCourses.get( courseElement ).getGradingPolicy().
		get( policyElement ).setTypeName( typeName );
	registeredCourses.get( courseElement ).getGradingPolicy().
		get( policyElement ).setGradeWeight( gradeWeight );
	
	
}
public static void editGradePolicy( int courseElement ){
		if ( courseElement == - 1 ){
			System.out.println(
					"Before you can edit a grade policy you must add a course." );
			return;
		}
	int policyElement = registeredCourses.get( courseElement ).selectGradeType();
	// -1 return from selectGradeType() indicates the list is empty
		if ( policyElement == - 1){
			System.out.println(
					"Before you can edit a grade policy you must add a grade policy." );
			return;
		}
	
	String typeName = Utility.getString( "What type of grade is this? " );
	float gradeWeight = Utility.getFloat( "What is the weight of the grade? " );
	
	//below the attributes of the selected gradeType are changed to the user supplied
	//attributes
	registeredCourses.get( courseElement ).getGradingPolicy().
		get( policyElement ).setTypeName( typeName );
	registeredCourses.get( courseElement ).getGradingPolicy().
		get( policyElement ).setGradeWeight( gradeWeight );
	
	
}
public static void editGrade(){
	int courseElement = selectCourse();
	// -1 return from selectCourse() indicates the list is empty
		if ( courseElement == - 1){
			System.out.println( "Before you can edit a grade you must add a course." );
			return;
		}
	int policyElement = registeredCourses.get( courseElement ).selectGradeType();
	// -1 return from selectGradeType() indicates the list is empty
		if ( policyElement == - 1){
			System.out.println( 
					"Before you can edit a grade you must add a grade policy." );
			return;
		}
	int gradeElement = registeredCourses.get( courseElement ).
			getGradingPolicy().get( policyElement ).selectGrade();
	// -1 return from selectGrade() indicates the list is empty
		if ( gradeElement == - 1){
			System.out.println( "Before you can edit a grade you must add a grade." );
			return;
		}
	
	String name = Utility.getString( "What is the name of this grade?" );
	float total = Utility.
			getFloat( "What is the maximum number of points for the grade? " );
	float received = Utility.
			getFloat( "How many points did you receive for the grade? " );
	
	//below the attributes of the selected grade are changed to the user supplied
	//attributes
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
public static void editGrade(int courseElement){
	int policyElement = registeredCourses.get( courseElement ).selectGradeType();
	// -1 return from selectGradeType() indicates the list is empty
		if ( policyElement == - 1){
			System.out.println( 
					"Before you can edit a grade you must add a grade policy." );
			return;
		}
	int gradeElement = registeredCourses.get( courseElement ).
			getGradingPolicy().get( policyElement ).selectGrade();
	// -1 return from selectGrade() indicates the list is empty
		if ( gradeElement == - 1){
			System.out.println( "Before you can edit a grade you must add a grade." );
			return;
		}
	
	String name = Utility.getString( "What is the name of this grade?" );
	float total = Utility.
			getFloat( "What is the maximum number of points for the grade? " );
	float received = Utility.
			getFloat( "How many points did you receive for the grade? " );
	
	//below the attributes of the selected grade are changed to the user supplied
	//attributes
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
public static void editGrade( int courseElement, int policyElement){
	
	int gradeElement = registeredCourses.get( courseElement ).
			getGradingPolicy().get( policyElement ).selectGrade();
	// -1 return from selectGrade() indicates the list is empty
		if ( gradeElement == - 1){
			System.out.println( "Before you can edit a grade you must add a grade." );
			return;
		}
	
	String name = Utility.getString( "What is the name of this grade?" );
	float total = Utility.
			getFloat( "What is the maximum number of points for the grade? " );
	float received = Utility.
			getFloat( "How many points did you receive for the grade? " );
	
	//below the attributes of the selected grade are changed to the user supplied
	//attributes
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
}
