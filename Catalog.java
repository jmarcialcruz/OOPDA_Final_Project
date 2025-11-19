import java.util.Set;
import java.util.LinkedHashSet;

public abstract class Catalog {
    private LinkedHashSet<Course> courseCatalog;

    Catalog() {
        courseCatalog = new LinkedHashSet<>();
    }

    protected void addAllCourses() {
        getCourseCatalog().addAll(addMajorRequiredCourses());
        getCourseCatalog().addAll(addElectiveCourses());
    }

    public abstract LinkedHashSet<Course> addMajorRequiredCourses();
    public abstract LinkedHashSet<Course> addMinorRequiredCourses();
    public abstract LinkedHashSet<Course> addElectiveCourses();
        
    public final void displayAllCourses() {
        System.out.print("Course:  "); 
        System.out.print("\tCredits: ");
        System.out.println("\tTitle:   ");

        for (Course course : courseCatalog) {
            course.displaySelectionInfo();
            System.out.println();
        }

        System.out.println();
    };

    public LinkedHashSet<Course> getCourseCatalog() {
        return this.courseCatalog;
    }

    public Course getCatalogCourse(String elective) {
        for (Course course : getCourseCatalog()) {
            if (elective.equals(course.getSubject() + " " + course.getId())) {
                return course;
            }
        }
        System.out.println("Course not found");
        return null;
    }
}
