import java.util.Set;
import java.util.LinkedHashSet;

public class DegreePlan extends Degree {
    private String advisor;
    private String advisorEmail;
    private boolean degreeCompletion;
    private int degreeProgress;
    private int degreeCreditsReq;
    private Set<Course> requiredCoursework;

    DegreePlan(String fieldOfStudy) {
        super(fieldOfStudy);
        this.degreeProgress = 0;
        this.degreeCompletion = false;
        this.requiredCoursework = new LinkedHashSet<>();
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

    public int getDegreeProgress() {
        return this.degreeProgress;
    }

    public int getDegreeCreditsReq() {
        return this.degreeCreditsReq;
    }

    public void setDegreeCreditsReq(int degreeCreditsReq) {
        this.degreeCreditsReq = degreeCreditsReq;
    }

    public final Set<Course> getRequiredCoursework() {
        return this.requiredCoursework;
    }

    public final void addRequiredCoursework(Course course) {
        requiredCoursework.add(course);        
    }

    public final void addRequiredCoursework(Set<Course> coursework) {
        requiredCoursework.addAll(coursework);        
    }

    public final void removeFromRequiredCoursework(Course course) {
        requiredCoursework.remove(course);        
    }

    public void displayPlanInfo() {
        System.out.print(ColoredOutput.BRIGHT_RED); 
        System.out.print("__COURSE_#__"); 
        System.out.print("\t__CREDITS__");
        System.out.println("\t__COURSE_NAME__");
        System.out.print(ColoredOutput.RESET); 
        System.out.print(ColoredOutput.RED); 

        for (Course course : requiredCoursework) {
            course.displaySelectionInfo();
            System.out.println();
        } 

        System.out.print(ColoredOutput.RESET); 

        System.out.println();
        super.displayDegreeInfo();
    }
}

