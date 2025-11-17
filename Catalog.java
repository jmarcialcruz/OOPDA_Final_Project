import java.util.ArrayList;

public abstract class Catalog {
    private ArrayList<Course> courseCatalog;

    Catalog() {
        courseCatalog = new ArrayList<>();
    }

    protected void addAllCourses() {
        getCourseCatalog().addAll(addMajorRequiredCourses());
        getCourseCatalog().addAll(addElectiveCourses());
    }

    public abstract ArrayList<Course> addMajorRequiredCourses();
    public abstract ArrayList<Course> addElectiveCourses();
        
    public final void displayAllCourses() {
        System.out.print("Course:  "); 
        System.out.print("\tCredits: ");
        System.out.println("\tTitle:   ");

        for (Course course : courseCatalog) {
            course.displayInfo();
            System.out.println();
        }

        System.out.println();
    };

    public ArrayList<Course> getCourseCatalog() {
        return this.courseCatalog;
    }
}
