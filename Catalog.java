import java.util.Set;
import java.util.LinkedHashSet;

public interface Catalog {
    public void displayAllCourses();

    public default void dispalyHeader() {
        System.out.print("Course:  "); 
        System.out.print("\tCredits: ");
        System.out.println("\tTitle:   ");
    }

    // public LinkedHashSet<Course> getCourseCatalog();
}
