import java.util.Scanner;

public class Utility {
	private static Scanner input = new Scanner(System.in);
	
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
				System.out.println( "Your input was not a number please input a number: ");
			}finally{
				input.nextLine();
			}
		}
		return number;
	}
	public static short getShort(String question){
		short number = 0;
		System.out.print( question );
		boolean inputError = true;
			/////////////////////////////////////////////
			//Try catch is used here to prevent program crashes
			//if the user inputs values other than integers in for
			//the requested number.
			/////////////////////////////////////////////
		while( inputError ){
			try{
				number = input.nextShort();
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
	public static long getLong(String question){
		long number = 0;
		System.out.print( question );
		boolean inputError = true;
			/////////////////////////////////////////////
			//Try catch is used here to prevent program crashes
			//if the user inputs values other than integers in for
			//the requested number.
			/////////////////////////////////////////////
		while( inputError ){
			try{
				number = input.nextLong();
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
	public static String getString(String question){
		System.out.println( question );
		String inputString = input.nextLine();
		return inputString;
	}
	
}
