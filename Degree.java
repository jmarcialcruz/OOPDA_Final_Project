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

    public ArrayList<FinishedCourse> getCoursework() {
        return this.coursework;
    }

    public void setFieldOfStudy(String fieldOfStudy) {
        this.fieldOfStudy = fieldOfStudy;
    }

    public void addToCoursework(FinishedCourse course) {
        coursework.add(course);
    }
    
    // Add more than one course to coursework
    public void addToCoursework(ArrayList<Course> coursework) {
        coursework.addAll(coursework);
    }

    public void removeFromCoursework(FinishedCourse course) {
        boolean removed = coursework.remove(course);
        System.out.println("FinishedCourse " + course.getSubject() + " " + course.getId() + " removed: " + removed);
    }

    public void displayInfo() {
        System.out.println("=== Coursework Completed Info ===");

        for (FinishedCourse course : coursework) {
            course.displayInfo();
            System.out.println();
        } 
    }
}
