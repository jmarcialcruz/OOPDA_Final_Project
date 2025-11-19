import java.util.Set;
import java.util.LinkedHashSet;

public class CompSciCatalog extends Catalog {
    CompSciCatalog() {
        super();
        addAllCourses();
    }

    public LinkedHashSet<Course> addMajorRequiredCourses() {
        LinkedHashSet<Course> majorRequiredCourses = new LinkedHashSet<>();
        majorRequiredCourses.addAll(addMinorRequiredCourses());     
        majorRequiredCourses.add(new Course("CS", "00100", "Computer Science Learning Community", 1));       
        majorRequiredCourses.add(new Course("CS", "07210", "Foundation of Computer Science ", 3));     
        majorRequiredCourses.add(new Course("CS", "04315", "Programming Languages", 3));     
        majorRequiredCourses.add(new Course("CS", "06395", "Operating Systems", 3));     
        majorRequiredCourses.add(new Course("CS", "03351", "Cyber Security: Fundamentals", 3));     
        majorRequiredCourses.add(new Course("CS", "04321", "Software Engineering I", 4));     
        majorRequiredCourses.add(new Course("CS", "07340", "Design and Analysis of Algorithms ", 3));     
        majorRequiredCourses.add(new Course("CS", "04400", "Senior Project", 3));     

        return majorRequiredCourses;
    }

    public LinkedHashSet<Course> addMinorRequiredCourses() {
        LinkedHashSet<Course> minorRequiredCourses = new LinkedHashSet<>();
        minorRequiredCourses.add(new Course("CS", "04113", "Intro to Object Oriented Programming", 4));       
        minorRequiredCourses.add(new Course("CS", "04114", "Object Oriented Programming and Data Abstraction", 3));     
        minorRequiredCourses.add(new Course("CS", "04215", "Computer Lab Techniques", 3));     
        minorRequiredCourses.add(new Course("CS", "04222", "Data Structures and Algorithms ", 4));     
        minorRequiredCourses.add(new Course("CS", "06205", "Computer Organization", 3));     

        return minorRequiredCourses;
    }

    public LinkedHashSet<Course> addElectiveCourses() {
        LinkedHashSet<Course> electiveCourses = new LinkedHashSet<>();
        electiveCourses.add(new Course("CS", "04391", "Parallel and Concurrent Programming", 3));       

        return electiveCourses;
    }

    public Course getCatalogCourse(String elective) {
        for (Course course : getCourseCatalog()) {
            if (elective.equals(course.getSubject() + " " + course.getId())) {
                return course;
            }
        }
        System.out.println("Course not found");
        return null;
    }
}
