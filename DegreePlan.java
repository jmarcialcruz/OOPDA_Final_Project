import java.util.Set;
import java.util.LinkedHashSet;

public class DegreePlan extends Degree {
    private String advisor;
    private String advisorEmail;
    private boolean degreeCompletion;
    private double degreeProgress;
    private LinkedHashSet<Course> requiredCoursework;

    DegreePlan(String fieldOfStudy) {
        super(fieldOfStudy);
        this.degreeProgress = 0.0;
        this.degreeCompletion = false;
        this.requiredCoursework = new LinkedHashSet<>();
        setAdvisor("Graves,Jaclyn");
        setAdvisorEmail("gravesj@rowan.edu");
    }

    public String getAdvisor() {
        return this.advisor;
    }

    public void setAdvisor(String advisor) {
        this.advisor = advisor;
    }

    public String getAdvisorEmail() {
        return this.advisorEmail;
    }

    public void setAdvisorEmail(String advisorEmail) {
        this.advisorEmail = advisorEmail;
    }

    public boolean getDegreeCompletion() {
        return this.degreeCompletion;
    }

    public double getDegreeProgess() {
        return this.degreeProgress;
    }

    public final LinkedHashSet<Course> getRequiredCoursework() {
        return this.requiredCoursework;
    }

    public final void addRequiredCoursework(Course course) {
        requiredCoursework.add(course);        
    }

    public final void addRequiredCoursework(LinkedHashSet<Course> coursework) {
        requiredCoursework.addAll(coursework);        
    }

    public final void removeRequiredCoursework(Course course) {
        requiredCoursework.remove(course);        
    }

    public void displayPlanInfo() {
        System.out.println("=== Coursework Required Info ===");
        System.out.print("Course:  "); 
        System.out.print("\tCredits: ");
        System.out.println("\tTitle:   ");

        for (Course course : requiredCoursework) {
            course.displaySelectionInfo();
            System.out.println();
        } 

        System.out.println();
        super.displayDegreeInfo();
    }
}

