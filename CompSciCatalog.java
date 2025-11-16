import java.util.ArrayList;

public class CompSciCatalog extends Catalog {
    CompSciCatalog() {
        courseCatalog = new ArrayList<>();
        addAllCourses();
    }

    protected void addAllCourses() {
        courseCatalog.addAll(addMajorRequiredCourses());
    }

    protected ArrayList<Course> addMajorRequiredCourses() {
        ArrayList<Course> majorRequiredCourses = new ArrayList<>();
        majorRequiredCourses.add(new Course("CS", "00100", "Computer Science Learning Community", 1));       
        majorRequiredCourses.add(new Course("CS", "04113", "Intro to Object Oriented Programming", 4));       
        majorRequiredCourses.add(new Course("CS", "04114", "Object Oriented Programming and Data Abstraction", 3));     
        majorRequiredCourses.add(new Course("CS", "04215", "Computer Lab Techniques", 3));     
        majorRequiredCourses.add(new Course("CS", "04222", "Data Structures and Algorithms ", 4));     
        majorRequiredCourses.add(new Course("CS", "06205", "Computer Organization", 3));     
        majorRequiredCourses.add(new Course("CS", "07210", "Foundation of Computer Science ", 3));     
        majorRequiredCourses.add(new Course("CS", "04315", "Programming Languages", 3));     
        majorRequiredCourses.add(new Course("CS", "06395", "Operating Systems", 3));     
        majorRequiredCourses.add(new Course("CS", "03351", "Cyber Security: Fundamentals", 3));     
        majorRequiredCourses.add(new Course("CS", "04321", "Software Engineering I", 4));     
        majorRequiredCourses.add(new Course("CS", "07340", "Design and Analysis of Algorithms ", 3));     
        majorRequiredCourses.add(new Course("CS", "04400", "Senior Project", 3));     

        return majorRequiredCourses;
    }
}
