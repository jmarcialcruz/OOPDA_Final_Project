import java.util.ArrayList;

public class MathCatalog extends Catalog {
    MathCatalog() {
        courseCatalog = new ArrayList<>();
        addAllCourses();
    }

    protected void addAllCourses() {
        courseCatalog.addAll(addMajorRequiredCourses());
    }

    protected ArrayList<Course> addMajorRequiredCourses() {
        ArrayList<Course> majorRequiredCourses = new ArrayList<>();
        majorRequiredCourses.add(new Course("MATH", "01130", "Calculus I", 4));       
        majorRequiredCourses.add(new Course("MATH", "01131", "Calculus II ", 4));     
        majorRequiredCourses.add(new Course("MATH", "01230", "Calculus III ", 4));     
        majorRequiredCourses.add(new Course("MATH", "03150", "Discrete Mathematics", 4));     
        majorRequiredCourses.add(new Course("MATH", "01210", "Linear Algebra", 3));     

        return majorRequiredCourses;
    }

}
