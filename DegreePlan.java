import java.util.ArrayList;

public class DegreePlan extends Degree {
    private boolean degreeCompletion;
    private double degreeProgress;
    private ArrayList<Course> requiredCoursework;

    DegreePlan(String fieldOfStudy) {
        super(fieldOfStudy);
        this.degreeProgress = 0.0;
        this.degreeCompletion = false;
        this.requiredCoursework = new ArrayList<>();
    }

    public boolean getDegreeCompletion() {
        return this.degreeCompletion;
    }

    public double getDegreeProgess() {
        return this.degreeProgress;
    }

    public final ArrayList<Course> getRequiredCoursework() {
        return this.requiredCoursework;
    }

    public final void addRequiredCoursework(Course course) {
        requiredCoursework.add(course);        
    }

    public final void addRequiredCoursework(ArrayList<Course> coursework) {
        requiredCoursework.addAll(coursework);        
    }

    @Override 
    public void displayInfo() {
        System.out.println("=== Coursework Required Info ===");

        for (Course course : requiredCoursework) {
            course.displayInfo();
            System.out.println();
        } 
    }
}

