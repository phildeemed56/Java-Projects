import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

//Author: Philip Mensah
//Purpose: The program is used to calculates Grade Point Average and generates a transcript outlining the Course Names, Course Credits, Letter Grades, and the GPA.

public class GradePointAverage {

	public static void main(String[] args) throws FileNotFoundException {
		//Declare Variables
		String courseNumber;
		int creditHour;
		String letterGrade;
		double pointScale;
		
		// Create a Scanner objects for keyboard input.
	    Scanner keyboard = new Scanner(System.in);
	    
	    //Creating an ArrayList For CourseNumber
	    ArrayList<String> courseNumbers = new ArrayList<>();
	    
	    //Create an ArrayList For CreditHours
        ArrayList<Integer> creditHours = new ArrayList<>();
        
        //Create an ArrayList For LetterGrade
        ArrayList<String> letterGrades = new ArrayList<>();
        
        //Create an ArrayList for PointGrade
        ArrayList<Double> pointScales = new ArrayList<>();
        
        
        char moreCourses = 'Y';
        
        
        System.out.println("The program prints the transcript and calculates the GPA, based on the courses entered. "
        		+ "Please enter the courses below.");
        
        // Initialize the course counter to 1
        int courseCounter = 1; 
        
        while (moreCourses == 'Y' || moreCourses == 'y') {
            System.out.print("Number of course " + courseCounter + ": "  );
            courseNumber = keyboard.nextLine();
            courseNumbers.add(courseNumber);

            System.out.print("Credit Hours of Course " + courseCounter + ":" );
            creditHour = keyboard.nextInt();
            keyboard.nextLine(); // Consume newline left-over
            creditHours.add(creditHour);
            
            System.out.print("Letter Grade for Course " + courseCounter + ":");
            letterGrade = keyboard.nextLine();
            letterGrades.add(letterGrade);
            
            // Call the method to convert letter grade to point scale
            pointScale = convertToPointScale(letterGrade);
            
            // Add the calculated point scale to the ArrayList
            pointScales.add(pointScale);
            
            // Ask if there are more courses to enter
            System.out.print("Do you have more items to enter? (Y/N) ");
            moreCourses = keyboard.nextLine().charAt(0);
            
            // Incrementing the course counter for the next iteration
            courseCounter++;
            
        }
            // Call the method to calculate GPA
            double gpa = calculateGPA(pointScales, creditHours);
            
            // Format GPA to two decimal places
            String formattedGPA = String.format("%.2f", gpa);
            
            // Print the calculated GPA
            System.out.println("The GPA is: " + formattedGPA);
            
            
            // Ask if user wants to save the result to a file
            System.out.print("Would you like to save the result to a file? (Y/N): ");
            char saveToFile = keyboard.nextLine().charAt(0);
            
            if (saveToFile == 'Y' || saveToFile == 'y') {
                System.out.print("Please enter the file name for the transcript: ");
                String fileName = keyboard.nextLine();
            
            // Write to the File
            PrintWriter outputFile = new PrintWriter(fileName);
            
            // Write header
            outputFile.println("COURSE\t\tCREDIT\tGRADE\tGRADE POINT");

            // Write course details      
            for (int i = 0; i < courseNumbers.size(); i++) {
                outputFile.println(courseNumbers.get(i) + "\t\t" + creditHours.get(i) + "\t" + letterGrades.get(i) + "\t" + pointScales.get(i));
            }
            
            // Write separator
            outputFile.println("------------------------------------------------------------");
            
            
            // Write GPA
            outputFile.println("GPA: " + formattedGPA);
            
            //Inform the user
            outputFile.close();
            System.out.println("The data is saved to the file "+ fileName);

            
         // Close the File
            outputFile.close();
     
        }
        
	}

	private static double convertToPointScale(String letterGrades) {
		double pointScale;
		
		
		
		// Convert letter grade to point scale using if-else statements
        if (letterGrades.equals("A+") || letterGrades.equals("A")) {
            pointScale = 4.0;
        } 
        else if (letterGrades.equals("A-")) {
            pointScale = 3.7;
        }
        else if (letterGrades.equals("B+")) {
            pointScale = 3.3;
        }
        else if (letterGrades.equals("B")) {
            pointScale = 3.0;
        }
        else if (letterGrades.equals("C+")) {
            pointScale = 2.3;
        }
        else if (letterGrades.equals("C")) {
            pointScale = 2.0;
        }
        else if (letterGrades.equals("C-")) {
            pointScale = 1.7;
        }
        else if (letterGrades.equals("D+")) {
            pointScale = 3.3;
        }
        else if (letterGrades.equals("D")) {
            pointScale = 1.0;
        }
        else if (letterGrades.equals("D-") || letterGrades.equals("D") || letterGrades.equals("F")) {
            pointScale = 0;
        }
        else {
            throw new IllegalArgumentException("Invalid letter grade: " + letterGrades);
        }   
		return pointScale;
	}
	

	private static double calculateGPA(ArrayList<Double> pointScales, ArrayList<Integer> creditHours) {
		if (pointScales.size() != creditHours.size()) {
            throw new IllegalArgumentException("Size of pointScales and creditHours must be the same");
        }
		
		double sumOfGradePoints = 0.0;
        int sumOfCreditHours = 0;
        
        // Calculate sum of (point scale * credit hours)
        for (int i = 0; i < pointScales.size(); i++) {
            sumOfGradePoints += pointScales.get(i) * creditHours.get(i);
            sumOfCreditHours += creditHours.get(i);
        }
        
        // Calculate GPA
        double gpa = sumOfGradePoints / sumOfCreditHours;
        return gpa;


	}

}



