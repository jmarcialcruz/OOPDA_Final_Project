import java.util.Set;
import java.util.LinkedHashSet;

public abstract class CompSciCatalog implements Catalog {

    public static Set<Course> addMajorRequiredCourses() {
        Set<Course> majorRequiredCourses = new LinkedHashSet<>();
        majorRequiredCourses.addAll(addMinorRequiredCourses());     
        majorRequiredCourses.add(new Course("CS", "00100", "Computer Science Learning Community", 1, "R"));       
        majorRequiredCourses.add(new Course("CS", "07210", "Foundation of Computer Science ", 3, "R"));     
        majorRequiredCourses.add(new Course("CS", "04315", "Programming Languages", 3, "R"));     
        majorRequiredCourses.add(new Course("CS", "06395", "Operating Systems", 3, "R"));     
        majorRequiredCourses.add(new Course("CS", "03351", "Cyber Security: Fundamentals", 3, "R"));     
        majorRequiredCourses.add(new Course("CS", "04321", "Software Engineering I", 4, "R"));     
        majorRequiredCourses.add(new Course("CS", "07340", "Design and Analysis of Algorithms ", 3, "R"));     
        majorRequiredCourses.add(new Course("CS", "04400", "Senior Project", 3, "R"));     

        return majorRequiredCourses;
    }

    public static Set<Course> addMinorRequiredCourses() {
        Set<Course> minorRequiredCourses = new LinkedHashSet<>();
        minorRequiredCourses.add(new Course("CS", "04113", "Intro to Object Oriented Programming", 4, "R"));       
        minorRequiredCourses.add(new Course("CS", "04114", "Object Oriented Programming and Data Abstraction", 3, "R"));     
        minorRequiredCourses.add(new Course("CS", "04215", "Computer Lab Techniques", 3, "R"));     
        minorRequiredCourses.add(new Course("CS", "04222", "Data Structures and Algorithms ", 4, "R"));     
        minorRequiredCourses.add(new Course("CS", "06205", "Computer Organization", 3, "R"));     

        return minorRequiredCourses;
    }

    public static Set<Course> addElectiveCourses() {
        Set<Course> electiveCourses = new LinkedHashSet<>();
        electiveCourses.add(new Course("CS", "04391", "Parallel and Concurrent Programming", 3, "R")); 

        return electiveCourses;
    }

    public static boolean checkCatalogForCourse(String courseName) {
        for (Course course : addMajorRequiredCourses()) {
            if (courseName.equals(course.getSubject() + " " + course.getId())) {
                return true;
            }
        }
        return false;
    }

    public static Course getCatalogCourse(String elective) {
        for (Course course : addMajorRequiredCourses()) {
            String courseTitle = course.getSubject() + " " + course.getId();
            
            if (elective.equals(courseTitle)) {
                return course;
            }
        }

        return null;
    }
}
