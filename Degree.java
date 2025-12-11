import java.util.Set;
import java.util.LinkedHashSet;

public abstract class Degree {
    private String fieldOfStudy;
    private Set<Course> coursework;
    private int progressBitmap;
    private int completedCredits;
    private int freeElectiveCredits;
    private int restrictedElectiveCredits;
    private String advisor;
    private String advisorEmail;

    Degree(String fieldOfStudy) {
        this.fieldOfStudy = fieldOfStudy;
        this.coursework = new LinkedHashSet<>();
        this.completedCredits = 0;
        this.progressBitmap = 0;
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

    public void setProgressBit(int index) {
        int statusBit = 1 << index;
        progressBitmap |= statusBit;
    }

    public void unsetProgressBit(int index) {
        int statusBit = 1 << index;
        progressBitmap &= ~statusBit;
    }

    public int getProgressBit(int index) {
        int statusBit = 1 & (progressBitmap >> index);
        return statusBit;
    }

    public int getProgressBitmap() {
        return this.progressBitmap;
    }

    public int getCompletedCredits() {
        return this.completedCredits;
    }

    public int getFreeElectiveCredits() {
        return this.freeElectiveCredits;
    }

    public void setFreeElectiveCredits(int credits) {
        this.freeElectiveCredits = credits;
    }

    public int getRestrictedElectiveCredits() {
        return this.restrictedElectiveCredits;
    }

    public void setRestrictedElectiveCredits(int credits) {
        this.restrictedElectiveCredits = credits;
    }

    public String getAdvisor() {
        return this.advisor;
    }

    protected void setAdvisor(String advisor) {
        this.advisor = advisor;
    }

    public String getAdvisorEmail() {
        return this.advisorEmail;
    }

    protected void setAdvisorEmail(String advisorEmail) {
        this.advisorEmail = advisorEmail;
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
