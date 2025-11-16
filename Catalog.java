import java.util.ArrayList;

public abstract class Catalog {
    protected ArrayList<Course> courseCatalog;
    protected abstract void addAllCourses();
    protected abstract ArrayList<Course> addMajorRequiredCourses();

    public final void displayAllCourses() {
        System.out.print("Course:  "); 
        System.out.print("\tCredits: ");
        System.out.println("\tTitle:   ");

        for (Course course : courseCatalog) {
            course.displayInfo();
            System.out.println();
        }
    };
}
