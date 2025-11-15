import java.util.ArrayList;

public abstract class Catalog {
    protected ArrayList<Course> courseCatalog;
    protected abstract void addAllCourses();
    public abstract void displayAllCourses();
}
