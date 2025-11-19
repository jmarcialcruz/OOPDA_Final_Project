import java.util.Set;
import java.util.LinkedHashSet;

public abstract class Degree {
    private String fieldOfStudy;
    private LinkedHashSet<Course> coursework;

    Degree(String fieldOfStudy) {
        this.fieldOfStudy = fieldOfStudy;
        this.coursework = new LinkedHashSet<>();
    }

    public String getFieldOfStudy() {
        return this.fieldOfStudy;
    }

    public LinkedHashSet<Course> getCompletedCoursework() {
        return this.coursework;
    }

    public void setFieldOfStudy(String fieldOfStudy) {
        this.fieldOfStudy = fieldOfStudy;
    }

    public void addToCompletedCoursework(Course course) {
        coursework.add(course);
    }
    
    public void addToCompletedCoursework(Course course, String grade) {
        Course completedCourse = new Course(course, grade);
        coursework.add(completedCourse);
    }

    // Add more than one course to coursework
    public void addToCompletedCoursework(LinkedHashSet<Course> coursework) {
        for (Course course : coursework) {
            coursework.add(course);
        }
    }

    public void removeFromCompletedCoursework(Course course) {
        boolean removed = coursework.remove(course);
        System.out.println("Course " + course.getSubject() + " " + course.getId() + " removed: " + removed);
    }

    public void displayDegreeInfo() {
        System.out.println("=== Coursework Completed Info ===");

        if (coursework.isEmpty()) {
            System.out.println("No coursework was completed");
        }
        else {
            for (Course course : coursework) {
                course.displayFinishedInfo();
                System.out.println();
            } 
        }
    }
}
