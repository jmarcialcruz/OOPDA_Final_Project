import java.util.Set;

public interface Catalog {
    public void displayAllCourses();

    public default void dispalyHeader() {
        System.out.print("Course:  "); 
        System.out.print("\tCredits: ");
        System.out.println("\tTitle:   ");
    }

    // public static Set<Course> addAllCourses();
}
