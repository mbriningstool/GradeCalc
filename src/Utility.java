import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

public class Utility {
	private static Scanner input = new Scanner(System.in);
			
	public static void writeToFile( File output, String givenString ){
		PrintWriter out = null;
		FileWriter fw = null;
		try{
			fw = new FileWriter(output,true);
			out = new PrintWriter(fw);
			out.println(givenString);
		}catch(IOException e){
			System.out.println("The file was not found");
		}finally{
			out.close();
			
		}
	}
	 public static File getFileSaveLocation(){
		 File output = null;
		 int returnVal = 0;
		 JFrame parentFrame = new JFrame();
		 parentFrame.setAlwaysOnTop(true);
		 JFileChooser chooser = new JFileChooser();
		 try{
			 returnVal = chooser.showSaveDialog(parentFrame );
			 if(returnVal == JFileChooser.APPROVE_OPTION){
				output = chooser.getSelectedFile();
			}
		 }catch(NullPointerException e){
			 System.out.println(
					 "Your File was not saved. "
					 + "Because the save dialog was closed prior to saving" );
		 }
		 	if(output == null){
		 		System.out.println( "Please try saving the file again." );
		 		getFileSaveLocation();
		 	}
		return output;
	 }
	 public static File getFileSaveLocation( String fileExtension ){
		 File output = null;
		 int returnVal = 0;
		 JFrame parentFrame = new JFrame();
		 parentFrame.setAlwaysOnTop(true);
		 JFileChooser chooser = new JFileChooser();
		 try{
			 returnVal = chooser.showSaveDialog(parentFrame );
			 if(returnVal == JFileChooser.APPROVE_OPTION){
				output = new File(chooser.getSelectedFile() + fileExtension);
			}
		 }catch(NullPointerException e){
			 System.out.println(
					 "Your File was not saved. "
					 + "Because the save dialog was closed prior to saving" );
		 }
		 	if(output == null){
		 		System.out.println( "Please try saving the file again." );
		 		getFileSaveLocation();
		 	}
		return output;
	 }
	 public static File getFileLoadLocation(){
		 File input  = null;
		 int returnVal = 0;
		 JFrame parentFrame = new JFrame();
		 parentFrame.setAlwaysOnTop(true);
		 JFileChooser chooser = new JFileChooser();
		 try{
			 returnVal = chooser.showOpenDialog(parentFrame);
			 	if(returnVal==JFileChooser.APPROVE_OPTION){
			 		input =chooser.getSelectedFile();
		 }
		 }catch(NullPointerException e){
			 System.out.println(
					 "YourFilewasnotsaved."
							 +"Becausethesavedialogwasclosedpriortosaving");
		 }
		 	if(input == null){
		 		System.out.println("Pleasetrysavingthefileagain.");
		 		getFileLoadLocation();
		 	}
		 return input ;
		 }

	public static int getInt(String question){
		int number = 0;
		System.out.print( question );
		boolean inputError = true;
			/////////////////////////////////////////////
			//Try catch is used here to prevent program crashes
			//if the user inputs values other than integers in for
			//the requested number.
			/////////////////////////////////////////////
		while( inputError ){
			try{
				number = input.nextInt();
				inputError = false;
			}catch(java.util.InputMismatchException e){
				input.nextLine();
				System.out.println( "Your input was not a number please input a number: ");
			}finally{
				input.nextLine();
			}
		}
		return number;
	}
	public static int getInt( String question ,int min ,int max ){
		int number = 0;
		System.out.print( question );
		boolean inputError = true;
			/////////////////////////////////////////////
			//Try catch is used here to prevent program crashes
			//if the user inputs values other than integers in for
			//the requested number.
			/////////////////////////////////////////////
		while( inputError ){
			try{
				number = input.nextInt();
				inputError = false;
			}catch(java.util.InputMismatchException e){
				input.nextLine();
				System.out.println( "Your input was not a number please input a number: ");
			}finally{
				input.nextLine();
			}
		}
		if (number < min || number > max){
			System.out.println("The number must be between " + min + " and " + max + 
					". Please try again");
			getInt(question,min,max);
		}
		return number;
	}
	public static double getDouble(String question){
		double number = 0.0;
		System.out.print( question );
		boolean inputError = true;
			/////////////////////////////////////////////
			//Try catch is used here to prevent program crashes
			//if the user inputs values other than integers in for
			//the requested number.
			/////////////////////////////////////////////
		while( inputError ){
			try{
				number = input.nextDouble();
				inputError = false;
			}catch(java.util.InputMismatchException e){
				input.nextLine();
				System.out.println( "Your input was not a number please input a number: ");
			}finally{
				input.nextLine();
			}
			
		}
		return number;
	}
	public static float getFloat(String question){
		float number = 0;
		System.out.print( question );
		boolean inputError = true;
			/////////////////////////////////////////////
			//Try catch is used here to prevent program crashes
			//if the user inputs values other than integers in for
			//the requested number.
			/////////////////////////////////////////////
		while( inputError ){
			try{
				number = input.nextFloat();
				inputError = false;
			}catch(java.util.InputMismatchException e){
				input.nextLine();
				System.out.println( " Your input was not a number please input a number: ");
			}finally{
				input.nextLine();
			}
		}
		return number;
	}
	public static String getString(String question){
		System.out.println( question );
		String inputString = input.nextLine();
		
		return inputString;
	}
	
}
