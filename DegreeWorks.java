import java.util.ArrayList;
import java.util.Scanner;
import java.lang.System;

public class DegreeWorks {
    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);

        // TODO: prompt user for degree plan then generate worksheet
        CompSciCatalog csCatalog = new CompSciCatalog();
        MathCatalog mathCatalog = new MathCatalog();
        CompSciDegreePlan csDegree = new CompSciDegreePlan();
        Worksheet worksheet = new Worksheet(csDegree);

        // csDegree.addRequiredCoursework(csCatalog.getCatalogCourse("CS 00100"));
        
        csDegree.addRequiredCoursework(csCatalog.addMajorRequiredCourses());

        csDegree.addRequiredCoursework(mathCatalog.addMinorRequiredCourses());

        while(true) {
            worksheet.displayInfo();
            System.out.println();
            
            System.out.print("Enter Course ID: ");
            String course = userInput.nextLine();

            System.out.print("Enter letter grade: ");
            String grade = userInput.nextLine();

            if (course.substring(0,2).equals("CS")) {
                csDegree.addToCompletedCoursework(csCatalog.getCatalogCourse(course), grade);
                csDegree.removeRequiredCoursework(csCatalog.getCatalogCourse(course));
            }
            else if (course.substring(0,4).equals("MATH")) {
                csDegree.addToCompletedCoursework(mathCatalog.getCatalogCourse(course), grade);
                csDegree.removeRequiredCoursework(mathCatalog.getCatalogCourse(course));
            }

            System.out.println();
        }

    }
}
