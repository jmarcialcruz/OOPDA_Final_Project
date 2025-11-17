import java.util.ArrayList;

public class MathCatalog extends Catalog {
    MathCatalog() {
        super();
        addAllCourses();
    }

    public ArrayList<Course> addMajorRequiredCourses() {
        ArrayList<Course> majorRequiredCourses = new ArrayList<>();
        majorRequiredCourses.add(new Course("MATH", "01130", "Calculus I", 4));       
        majorRequiredCourses.add(new Course("MATH", "01131", "Calculus II ", 4));     
        majorRequiredCourses.add(new Course("MATH", "01230", "Calculus III ", 4));     
        majorRequiredCourses.add(new Course("MATH", "03150", "Discrete Mathematics", 4));     
        majorRequiredCourses.add(new Course("MATH", "01210", "Linear Algebra", 3));     

        return majorRequiredCourses;
    }

    public ArrayList<Course> addElectiveCourses() {
        ArrayList<Course> electiveCourses = new ArrayList<>();
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
