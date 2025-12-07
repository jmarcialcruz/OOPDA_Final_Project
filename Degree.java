import java.util.Set;
import java.util.LinkedHashSet;

public abstract class Degree {
    private String fieldOfStudy;
    private Set<Course> coursework;
    private int completedCredits;

    Degree(String fieldOfStudy) {
        this.fieldOfStudy = fieldOfStudy;
        this.coursework = new LinkedHashSet<>();
        this.completedCredits = 0;
    }

    public String getFieldOfStudy() {
        return this.fieldOfStudy;
    }

    public Set<Course> getCompletedCoursework() {
        return this.coursework;
    }

    public void setFieldOfStudy(String fieldOfStudy) {
        this.fieldOfStudy = fieldOfStudy;
    }

    public int getCompletedCredits() {
        return this.completedCredits;
    }

    public void addToCompletedCoursework(Course course, String grade) {
        course.setGrade(grade);
        coursework.add(course);
        completedCredits += course.getCredits();
    }

    public void removeFromCompletedCoursework(Course course) {
        boolean removed = coursework.remove(course);
        System.out.println("Course " + course.getSubject() + " " + course.getId() + " removed: " + removed);
        completedCredits -= course.getCredits();
    }

    public void displayDegreeInfo() {
        System.out.println("=== Coursework Completed Info ===");

        if (coursework.isEmpty()) {
            System.out.println("No coursework was completed");
            System.out.println();
        }
        else {
            for (Course course : coursework) {
                course.displayFinishedInfo();
                System.out.println();
            } 
        }
    }
}
