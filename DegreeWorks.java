import java.util.Set;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.lang.System;

public class DegreeWorks {
    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);

        // TODO: prompt user for degree plan then generate worksheet
        DegreePlan csDegree = new DegreePlan("Computer Sciencee (BS)");
        Worksheet worksheet = new Worksheet(csDegree, 120);
        
        worksheet.getDegreePlan().addRequiredCoursework(CompSciCatalog.addMajorRequiredCourses());
        worksheet.getDegreePlan().addRequiredCoursework(MathCatalog.addMinorRequiredCourses());

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

            if (course.substring(0,3).equals("CS ")) {
                if (CompSciCatalog.checkCatalogForCourse(course)) {
                    worksheet.getDegreePlan().addToCompletedCoursework(CompSciCatalog.getCatalogCourse(course), grade);
                    worksheet.getDegreePlan().removeFromRequiredCoursework(CompSciCatalog.getCatalogCourse(course));
                }
                else {
                    System.out.println();
                    System.out.println("COURSE NOT FOUND!");
                    System.out.println();
                }
            }
            else if (course.substring(0,5).equals("MATH ")) {
                if (MathCatalog.checkCatalogForCourse(course)) {
                    worksheet.getDegreePlan().addToCompletedCoursework(MathCatalog.getCatalogCourse(course), grade);
                    worksheet.getDegreePlan().removeFromRequiredCoursework(MathCatalog.getCatalogCourse(course));
                }
                else {
                    System.out.println();
                    System.out.println("COURSE NOT FOUND!");
                    System.out.println();
                }
            }

            System.out.println();
        }
    }
}
