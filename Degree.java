import java.util.Set;
import java.util.LinkedHashSet;

/**
 * An abstract base class representing an academic degree program.
 *
 * This class manages the student's progress, including their field of study,
 * completed coursework, accumulated credits, and advisor details. It uses a
 * bitmask (integer) to track specific progress milestones or flags efficiently.
 *
 * @author  Jordi Marcial Cruz
 */

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

    protected void setFieldOfStudy(String fieldOfStudy) {
        this.fieldOfStudy = fieldOfStudy;
    }

    public void setProgressBit(int index) {
        // Create a mask with a 1 at the specified index
        int statusBit = 1 << index;
        // Use bitwise OR to set that specific bit to 1 (leaving others unchanged)
        progressBitmap |= statusBit;
    }

    public void unsetProgressBit(int index) {
        // Create a mask with a 1 at the specified index
        int statusBit = 1 << index;
        // Invert the mask (e.g., 1110111) and AND it to clear the bit
        progressBitmap &= ~statusBit;
    }

    public int getProgressBit(int index) {
        // Shift the target bit to the 0th position and mask with 1 to isolate it
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
        // Update the course object with the grade earned
        course.setGrade(grade);
        
        // Add to the set and update total credits
        coursework.add(course);
        completedCredits += course.getCredits();
    }

    public void removeFromCompletedCoursework(Course course) {
        // Attempt to remove the course from the set
        boolean removed = coursework.remove(course);
        System.out.println("Course " + course.getSubject() + " " + course.getId() + " removed: " + removed);
        
        if (removed) {
            completedCredits -= course.getCredits();
        }
    }

    public void displayDegreeInfo() {
        System.out.println("=== Coursework Completed Info ===");

        if (coursework.isEmpty()) {
            System.out.println("No coursework was completed");
            System.out.println();
        }
        else {
            // Iterate through the set and print detailed info for each course
            for (Course course : coursework) {
                course.displayFinishedInfo();
                System.out.println();
            } 
        }
    }
}
