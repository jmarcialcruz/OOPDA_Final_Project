import java.util.Set;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.lang.System;

public class DegreeWorks {
    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);

        // TODO: prompt user for degree plan then generate worksheet
        CompSciCatalog csCatalog = new CompSciCatalog();
        MathCatalog mathCatalog = new MathCatalog();
        DegreePlan csDegree = new DegreePlan("Computer Sciencee (BS)");
        Worksheet worksheet = new Worksheet(csDegree);

        // worksheet.getDegreePlan().addRequiredCoursework(csCatalog.getCatalogCourse("CS 00100"));
        
        worksheet.getDegreePlan().addRequiredCoursework(csCatalog.addMajorRequiredCourses());

        worksheet.getDegreePlan().addRequiredCoursework(mathCatalog.addMinorRequiredCourses());

        while(true) {
            worksheet.displayWorksheetInfo();
            
            System.out.print("Enter Course ID: ");
            String course = userInput.nextLine();

            System.out.print("Enter letter grade: ");
            String grade = userInput.nextLine();

            if (course.substring(0,2).equals("CS")) {
                worksheet.getDegreePlan().addToCompletedCoursework(csCatalog.getCatalogCourse(course), grade);
                worksheet.getDegreePlan().removeRequiredCoursework(csCatalog.getCatalogCourse(course));
            }
            else if (course.substring(0,4).equals("MATH")) {
                worksheet.getDegreePlan().addToCompletedCoursework(mathCatalog.getCatalogCourse(course), grade);
                worksheet.getDegreePlan().removeRequiredCoursework(mathCatalog.getCatalogCourse(course));
            }

            System.out.println();

        }

    }
}
