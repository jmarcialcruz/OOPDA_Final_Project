import java.util.Set;
import java.util.List;
import java.util.LinkedHashSet;
import java.util.ArrayList;

public class DegreePlan extends Degree {
    private int progressBitmap;
    private int degreeProgress;
    private int degreeCreditsReq;
    private String advisor;
    private String advisorEmail;
    private Set<Course> requiredCoursework;
    private List<Set<Course>> degreeSectionList;

    DegreePlan(String fieldOfStudy) {
        super(fieldOfStudy);
        this.degreeProgress = 0;
        this.requiredCoursework = new LinkedHashSet<>();
        this.degreeSectionList = new ArrayList<>();
        this.progressBitmap = 0;
    }

    protected void createDegreeSections(int index) {
        for (int i = 0; i < index; i++) {
            degreeSectionList.add(new LinkedHashSet<>());
        }
    }

    public List<Set<Course>> getDegreeSectionList() {
        return this.degreeSectionList;
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

    public int getDegreeProgress() {
        return this.degreeProgress;
    }

    public int getDegreeCreditsReq() {
        return this.degreeCreditsReq;
    }

    public void setDegreeCreditsReq(int degreeCreditsReq) {
        this.degreeCreditsReq = degreeCreditsReq;
    }

    public Set<Course> getRequiredCoursework() {
        return this.requiredCoursework;
    }

    public void addRequiredCoursework(Course course) {
        requiredCoursework.add(course);
    }

    public void addRequiredCoursework(Set<Course> coursework) {
        requiredCoursework.addAll(coursework);
    }

    protected void displayEachCourse(Set<Course> selectedCoursework) {
        for (Course completedCourse : getRequiredCoursework()) {
            for (Course selectedCourse : selectedCoursework) {
                if (completedCourse.equals(selectedCourse)) {
                    if (completedCourse.getGrade().equals("R")){
                        System.out.print(ColoredOutput.RED);
                    }
                    else if (completedCourse.getGrade().equals("F")){
                        System.out.print(ColoredOutput.BRIGHT_YELLOW);
                    }
                    else {
                        System.out.print(ColoredOutput.GREEN);
                    }

                    completedCourse.displaySelectionInfo();
                    System.out.println();
                }
            }
        }

        System.out.println(ColoredOutput.RESET);
    }

    // Only updates required coursework grade once
    public void updateRequiredCoursework(Course course, String grade) {
        for (Course reqCourse : requiredCoursework) {
            if (reqCourse.equals(course) && reqCourse.getGrade().equals("R")) {
                reqCourse.setGrade(grade);
            }
        }
    }

    // TODO: Find a solution to this issue for the subclass methods
    public void displayPlanInfo() {
    }

    public void getDegreeSectionProgress() {
    }
}

