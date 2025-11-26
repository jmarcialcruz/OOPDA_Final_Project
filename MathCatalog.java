import java.util.Set;
import java.util.LinkedHashSet;

public abstract class MathCatalog implements Catalog {

    public static Set<Course> addMajorRequiredCourses() {
        Set<Course> majorRequiredCourses = new LinkedHashSet<>();
        majorRequiredCourses.addAll(addMinorRequiredCourses());     
        majorRequiredCourses.add(new Course("MATH", "03150", "Discrete Mathematics", 4, "R"));     
        majorRequiredCourses.add(new Course("MATH", "01231", "Ordinary Differential Equations", 4, "R"));     

        return majorRequiredCourses;
    }

    public static Set<Course> addMinorRequiredCourses() {
        Set<Course> minorRequiredCourses = new LinkedHashSet<>();
        minorRequiredCourses.add(new Course("MATH", "01130", "Calculus I", 4, "R"));       
        minorRequiredCourses.add(new Course("MATH", "01131", "Calculus II ", 4, "R"));     
        minorRequiredCourses.add(new Course("MATH", "01230", "Calculus III ", 4, "R"));     
        minorRequiredCourses.add(new Course("MATH", "01210", "Linear Algebra", 3, "R"));     

        return minorRequiredCourses;
    }
    
    public static Set<Course> addElectiveCourses() {
        Set<Course> electiveCourses = new LinkedHashSet<>();
        electiveCourses.add(new Course("MATH", "01235", "Math for Engineering Analysis", 4, "R"));       

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
        Set<Course> lookupCourse = new LinkedHashSet<>();
        lookupCourse.addAll(addMajorRequiredCourses());

        for (Course course : lookupCourse) {
            String courseTitle = course.getSubject() + " " + course.getId();
            
            if (elective.equals(courseTitle)) {
                return course;
            }
        }

        return null;
    }
}
