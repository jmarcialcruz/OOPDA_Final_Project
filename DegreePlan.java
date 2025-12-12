import java.util.Set;
import java.util.List;
import java.util.LinkedHashSet;
import java.util.ArrayList;

public class DegreePlan extends Degree {
    private int degreeCreditsReq;
    private int freeElectiveCreditsReq;
    private int restrictedElectiveCreditsReq;
    private Set<Course> requiredCoursework;
    private List<Set<Course>> degreeSectionList;

    DegreePlan(String fieldOfStudy) {
        super(fieldOfStudy);
        this.requiredCoursework = new LinkedHashSet<>();
        this.degreeSectionList = new ArrayList<>();
    }

    protected void setFreeElectiveCreditsReq(int credits) {
        this.freeElectiveCreditsReq = credits;
    }

    public int getFreeElectiveCreditsReq() {
        return this.freeElectiveCreditsReq;
    }

    protected void setRestrictedElectiveCreditsReq(int credits) {
        this.restrictedElectiveCreditsReq = credits;
    }

    public int getRestrictedElectiveCreditsReq() {
        return this.restrictedElectiveCreditsReq;
    }

    protected void createDegreeSections(int index) {
        for (int i = 0; i < index; i++) {
            degreeSectionList.add(new LinkedHashSet<>());
        }
    }

    public void setDegreeSectionList(List<Set<Course>> sectionList) {
        this.degreeSectionList = sectionList;
    }

    public List<Set<Course>> getDegreeSectionList() {
        return this.degreeSectionList;
    }

    public int getDegreeCreditsReq() {
        return this.degreeCreditsReq;
    }

    protected void setDegreeCreditsReq(int degreeCreditsReq) {
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

    public void getDegreeSectionProgress(int numberOfSections, Set<Course> electiveCourses) {
        // Comment this later
        Set<Course> restrictedElectives = new LinkedHashSet<>(getCompletedCoursework());
        restrictedElectives.retainAll(electiveCourses);

        int electiveCredits = 0;
        for (Course course : restrictedElectives) {
            electiveCredits += course.getCredits();

            if (electiveCredits > restrictedElectiveCreditsReq) {
                break;
            }
        }

        setRestrictedElectiveCredits(electiveCredits);
        boolean hasReqElectiveCredits  = (getRestrictedElectiveCredits() > restrictedElectiveCreditsReq);

        // Comment this later
        for (int i = 0; i < numberOfSections - 1; i++) {
            if (getCompletedCoursework().containsAll(degreeSectionList.get(i))) {
                setProgressBit(i + 3);
            }
            else {
                unsetProgressBit(i + 3);
            }
        }

        // Comment this later
        if (getCompletedCoursework().containsAll(degreeSectionList.get(3)) && hasReqElectiveCredits) {
            setProgressBit(6);
        }
        else {
            unsetProgressBit(6);
        }

        // Comment this later
        int reqCreditsCompleted = 0;

        Set<Course> completedReqCoursework = new LinkedHashSet<>(getCompletedCoursework());
        completedReqCoursework.retainAll(getRequiredCoursework());

        for (Course reqCourse : completedReqCoursework) {
            reqCreditsCompleted += reqCourse.getCredits();
        }
        
        setFreeElectiveCredits(getCompletedCredits() - reqCreditsCompleted - getRestrictedElectiveCredits());

        if (getFreeElectiveCredits() >= freeElectiveCreditsReq){
            setProgressBit(7);
        }
        else {
            unsetProgressBit(7);
        }
    }

    private void displayColoredCourses (Set<Course> coursework) {
        for (Course course : coursework) {
            if (course.getGrade().equals("R")){
                System.out.print(ColoredOutput.RED);
            }
            else if (course.getGrade().equals("F")){
                System.out.print(ColoredOutput.YELLOW);
            }
            else {
                System.out.print(ColoredOutput.GREEN);
            }

            course.displaySelectionInfo();
            System.out.println();
        }
        System.out.println(ColoredOutput.RESET);
    }

    protected void displayEachCourse(Set<Course> selectedCoursework) {
        Set<Course> coursework = new LinkedHashSet<>(getRequiredCoursework());
        coursework.retainAll(selectedCoursework);

        displayColoredCourses(coursework);
    }

    // Add comment here for restricted electives
    protected void displayRestrictedElectives(Set<Course> electiveCoursework, int creditReq) {
        Set<Course> electiveCourses = new LinkedHashSet<>(getCompletedCoursework());
        electiveCourses.removeAll(getRequiredCoursework());
        electiveCourses.retainAll(electiveCoursework);

        if (electiveCourses.isEmpty()) {
            System.out.println();
            return;
        }

        int totalCredits = 0;
        Set<Course> restrictedElectiveCourses = new LinkedHashSet<>();

        for (Course course : electiveCourses) {
            totalCredits += course.getCredits();
            restrictedElectiveCourses.add(course);

            if (totalCredits > creditReq) {
                break;
            }
        }

        displayColoredCourses(restrictedElectiveCourses);
    }

    protected void displayFreeElectiveCourses(Set<Course> electiveCoursework, int creditReq) {
        // Base case for checking for free electives
        if (getRequiredCoursework().containsAll(getCompletedCoursework())) {
            System.out.println();
            return;
        }

        // Removes restricted electives allocated restricted elective section based on credit requirement
        Set<Course> restrictedElectiveCourses = new LinkedHashSet<>(getCompletedCoursework());
        restrictedElectiveCourses.removeAll(getRequiredCoursework());
        restrictedElectiveCourses.retainAll(electiveCoursework);
        int totalCredits = 0;

        Set<Course> requiredRestricedElectiveCourses = new LinkedHashSet<>();
        for (Course course : restrictedElectiveCourses) {
            totalCredits += course.getCredits();
            requiredRestricedElectiveCourses.add(course);

            if (totalCredits > creditReq) {
                break;
            }
        }

        restrictedElectiveCourses.removeAll(requiredRestricedElectiveCourses);

        // Reduced set of restricted electives makes up the free elective coursework
        Set<Course> freeElectiveCourses = new LinkedHashSet<>(getCompletedCoursework());
        freeElectiveCourses.removeAll(getRequiredCoursework());
        freeElectiveCourses.removeAll(electiveCoursework);
        freeElectiveCourses.addAll(restrictedElectiveCourses);

        displayColoredCourses(freeElectiveCourses);
    }

    // Only updates required coursework grade once
    public void updateRequiredCoursework(Course course, String grade) {
        for (Course reqCourse : requiredCoursework) {
            if (reqCourse.equals(course) && reqCourse.getGrade().equals("R")) {
                reqCourse.setGrade(grade);
            }
        }
    }

    // Add comment here
    public void updateRequiredCoursework(Course course) {
        for (Course reqCourse : requiredCoursework) {
            if (reqCourse.equals(course) && !reqCourse.getGrade().equals("R")) {
                reqCourse.setGrade("R");
            }
        }
    }

    // Methods will be overriden in subclasses
    public void displayPlanInfo() {}

    public void getDegreeSectionProgress() {}
}

