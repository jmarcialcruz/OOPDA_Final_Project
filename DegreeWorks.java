import java.util.ArrayList;

public class DegreeWorks {
    public static void main(String[] args) {
        Degree degree = new Degree("Computer Science (BS)");
        FinishedCourse course1 = new FinishedCourse("CS", "04113", "Intro to Object Oriented Programming", 4, "A");        
        FinishedCourse course2 = new FinishedCourse("CS", "04114", "Object Oriented Programming and Data Abstraction", 3, "A");        
        FinishedCourse course3 = new FinishedCourse("MATH", "03150", "Discrete Mathematics", 3, "A-");        

        degree.addToCoursework(course1);
        degree.addToCoursework(course2);
        degree.addToCoursework(course3);

        Worksheet worksheet = new Worksheet(degree);
        worksheet.displayInfo();

        CompSciCatalog catalog = new CompSciCatalog();
        catalog.displayAllCourses();
    }
}
