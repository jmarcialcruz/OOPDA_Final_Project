import java.util.ArrayList;

public abstract class Degree implements UserFeedback{
    private String fieldOfStudy;
    private ArrayList<FinishedCourse> coursework;

    Degree(String fieldOfStudy) {
        this.fieldOfStudy = fieldOfStudy;
        this.coursework = new ArrayList<>();
    }

    public String getFieldOfStudy() {
        return this.fieldOfStudy;
    }

    public ArrayList<FinishedCourse> getCompletedCoursework() {
        return this.coursework;
    }

    public void setFieldOfStudy(String fieldOfStudy) {
        this.fieldOfStudy = fieldOfStudy;
    }

    public void addToCompletedCoursework(FinishedCourse course) {
        coursework.add(course);
    }
    
    public void addToCompletedCoursework(Course course, String grade) {
        FinishedCourse completedCourse = new FinishedCourse(course, grade);
        coursework.add(completedCourse);
    }

    // Add more than one course to coursework
    public void addToCompletedCoursework(ArrayList<FinishedCourse> coursework) {
        for (FinishedCourse course : coursework) {
            coursework.add(course);
        }
    }

    public void removeFromCompletedCoursework(FinishedCourse course) {
        boolean removed = coursework.remove(course);
        System.out.println("FinishedCourse " + course.getSubject() + " " + course.getId() + " removed: " + removed);
    }

    public void displayInfo() {
        System.out.println("=== Coursework Completed Info ===");

        if (coursework.isEmpty()) {
            System.out.println("No coursework was completed");
        }
        else {
            for (FinishedCourse course : coursework) {
                course.displayInfo();
                System.out.println();
            } 
        }
    }
}
