import java.util.Set;
import java.util.Scanner;
import java.lang.System;
import java.util.InputMismatchException;

public class DegreeWorks {
    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);

        DegreePlan degree; 
        Worksheet worksheet;
        
        while(true) {
            try {
                System.out.println("1. Computer Science");
                System.out.print("Choose a degree plan: ");
                int selection = userInput.nextInt();

                if (selection == 1) {
                    degree = new DegreePlan("Computer Sciencee (BS)");
                    worksheet = new Worksheet(degree, 120);
                    worksheet.getDegreePlan().addRequiredCoursework(CompSciCatalog.addMajorRequiredCourses());
                    worksheet.getDegreePlan().addRequiredCoursework(MathCatalog.addMinorRequiredCourses());
                    System.out.println();
                    userInput.nextLine();
                    break;
                }
                else {
                    System.out.println("Incorrect Selection!");
                    System.out.println();
                    continue;
                }
            }
            catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a whole number.");
                    System.out.println();
                    userInput.next();
                    continue;
            }
        }

        while(true) {
            worksheet.displayWorksheetInfo();
            
            System.out.print("Enter Course ID: ");
            String course = userInput.nextLine();
            
            // Checks for minimum amount characters for course id i.e. CS 00100 has 8 characters 
            if (course.length() < 8) {
                System.out.println();
                System.out.println("ENTER A VALID COURSE ID!");
                System.out.println();
                continue;
            } 

            System.out.print("Enter letter grade: ");
            String grade = userInput.nextLine();

            if (!worksheet.isValidNumericGrade(grade)) {
                System.out.println();
                System.out.println("ENTER A VALID GRADE!");
                System.out.println();
                continue;
            }
            
            // Accepts lower and uppercase
            course = course.toUpperCase();

            if (course.substring(0,3).equals("CS ")) {
                if (CompSciCatalog.checkCatalogForCourse(course)) {
                    worksheet.getDegreePlan().addToCompletedCoursework(CompSciCatalog.getCatalogCourse(course), grade);
                    worksheet.getDegreePlan().removeFromRequiredCoursework(CompSciCatalog.getCatalogCourse(course));
                }
                else {
                    printCourseNotFound();
                }
            }
            else if (course.substring(0,5).equals("MATH ")) {
                if (MathCatalog.checkCatalogForCourse(course)) {
                    worksheet.getDegreePlan().addToCompletedCoursework(MathCatalog.getCatalogCourse(course), grade);
                    worksheet.getDegreePlan().removeFromRequiredCoursework(MathCatalog.getCatalogCourse(course));
                }
                else {
                    printCourseNotFound();
                }
            }
            else {
                System.out.println();
                System.out.println("ENTER A VALID COURSE ID!");
            }

            System.out.println();
        }
    }

    public static void printCourseNotFound(){
        System.out.println();
        System.out.println("COURSE NOT FOUND!");
        System.out.println();

    }
}
