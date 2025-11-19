import java.util.Set;
import java.util.LinkedHashSet;

public class MathCatalog extends Catalog {
    MathCatalog() {
        super();
        addAllCourses();
    }

    public LinkedHashSet<Course> addMajorRequiredCourses() {
        LinkedHashSet<Course> majorRequiredCourses = new LinkedHashSet<>();
        majorRequiredCourses.addAll(addMinorRequiredCourses());     
        majorRequiredCourses.add(new Course("MATH", "03150", "Discrete Mathematics", 4));     
        majorRequiredCourses.add(new Course("MATH", "01231", "Ordinary Differential Equations", 4));     

        return majorRequiredCourses;
    }

    public LinkedHashSet<Course> addMinorRequiredCourses() {
        LinkedHashSet<Course> minorRequiredCourses = new LinkedHashSet<>();
        minorRequiredCourses.add(new Course("MATH", "01130", "Calculus I", 4));       
        minorRequiredCourses.add(new Course("MATH", "01131", "Calculus II ", 4));     
        minorRequiredCourses.add(new Course("MATH", "01230", "Calculus III ", 4));     
        minorRequiredCourses.add(new Course("MATH", "01210", "Linear Algebra", 3));     

        return minorRequiredCourses;
    }
    
    public LinkedHashSet<Course> addElectiveCourses() {
        LinkedHashSet<Course> electiveCourses = new LinkedHashSet<>();
        electiveCourses.add(new Course("MATH", "01235", "Math for Engineering Analysis", 4));       

        return electiveCourses;
    }

    public Course getCatalogCourse(String elective) {
        for (Course course : getCourseCatalog()) {
            if (elective.equals(course.getSubject() + " " + course.getId())) {
                return course;
            }
        }
        return null;
    }
}
