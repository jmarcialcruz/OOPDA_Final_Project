import java.util.Set;
import java.util.LinkedHashSet;

public abstract class Degree {
    private String fieldOfStudy;
    private Set<Course> coursework;

    Degree(String fieldOfStudy) {
        this.fieldOfStudy = fieldOfStudy;
        this.coursework = new LinkedHashSet<>();
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

    public void addToCompletedCoursework(Course course) {
        for (Course completedCourse : coursework) {

        }
        coursework.add(course);
    }
    
    public void addToCompletedCoursework(Course course, String grade) {
        course.setGrade(grade);
        coursework.add(course);
    }

    // Add more than one course to coursework
    public void addToCompletedCoursework(Set<Course> coursework) {
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
