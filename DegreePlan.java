import java.util.Set;
import java.util.List;
import java.util.LinkedHashSet;
import java.util.ArrayList;

/**
 * Represents a specific degree plan (e.g., Computer Science).
 * 
 * This class extends Degree class to manage the specific requirements of a major,
 * including required courses, elective credits, and degree sections (modules).
 * It calculates progress towards graduation by comparing completed coursework
 * against these requirements.
 *
 * @author  Jordi Marcial Cruz
 */

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

    // Initializes the list of degree sections.
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

    /**
     * Calculates and updates the progress flags (bits) for the degree.
     * This checks if specific sections of the degree (e.g., Core, Electives)
     * are satisfied and sets the corresponding bits in the progress bitmap.
     */
    public void getDegreeSectionProgress(int numberOfSections, Set<Course> electiveCourses) {
        // Part 1: Calculate Restricted Elective Progress 
        // Create a subset of completed courses that are also valid electives
        Set<Course> restrictedElectives = new LinkedHashSet<>(getCompletedCoursework());
        restrictedElectives.retainAll(electiveCourses);

        int electiveCredits = 0;
        // Sum credits until requirement is met
        for (Course course : restrictedElectives) {
            electiveCredits += course.getCredits();

            if (electiveCredits > restrictedElectiveCreditsReq) {
                break;
            }
        }

        setRestrictedElectiveCredits(electiveCredits);
        boolean hasReqElectiveCredits = (getRestrictedElectiveCredits() > restrictedElectiveCreditsReq);

        // Part 2: Check Standard Degree Sections 
        // Iterate through sections and check completion
        // Bits 3+ are used for section completion flags
        for (int i = 0; i < numberOfSections - 1; i++) {
            if (getCompletedCoursework().containsAll(degreeSectionList.get(i))) {
                setProgressBit(i + 3);
            }
            else {
                unsetProgressBit(i + 3);
            }
        }

        // Part 3: Check Elective Section Completion 
        // Checks if the specific elective section (index 3) AND credit counts are met
        // Uses Bit 6 for this specific milestone
        if (getCompletedCoursework().containsAll(degreeSectionList.get(3)) && hasReqElectiveCredits) {
            setProgressBit(6);
        }
        else {
            unsetProgressBit(6);
        }

        // Part 4: Calculate Free Electives 
        int reqCreditsCompleted = 0;

        // Filter completed courses to only those that are explicitly required
        Set<Course> completedReqCoursework = new LinkedHashSet<>(getCompletedCoursework());
        completedReqCoursework.retainAll(getRequiredCoursework());

        for (Course reqCourse : completedReqCoursework) {
            reqCreditsCompleted += reqCourse.getCredits();
        }
        
        // Free electives = Total Completed - (Required + Restricted Electives used)
        setFreeElectiveCredits(getCompletedCredits() - reqCreditsCompleted - getRestrictedElectiveCredits());

        // Check if free elective requirement is met (uses Bit 7)
        if (getFreeElectiveCredits() >= freeElectiveCreditsReq){
            setProgressBit(7);
        }
        else {
            unsetProgressBit(7);
        }
    }

    private void displayColoredCourses (Set<Course> coursework) {
        for (Course course : coursework) {
            // Color code output based on grade status
            if (course.getGrade().equals("R")){
                System.out.print(ColoredOutput.RED); // Required/Missing
            }
            else if (course.getGrade().equals("F")){
                System.out.print(ColoredOutput.YELLOW); // Failed
            }
            else {
                System.out.print(ColoredOutput.GREEN); // Completed/Passed
            }

            course.displaySelectionInfo();
            System.out.println();
        }
        System.out.println(ColoredOutput.RESET);
    }

    protected void displayEachCourse(Set<Course> selectedCoursework) {
        // Filter required coursework to only show the selected subset
        Set<Course> coursework = new LinkedHashSet<>(getRequiredCoursework());
        coursework.retainAll(selectedCoursework);

        displayColoredCourses(coursework);
    }

    // Filters and displays the restricted electives counting toward the degree.
    protected void displayRestrictedElectives(Set<Course> electiveCoursework, int creditReq) {
        // Filter completed courses: Remove core requirements, keep only valid electives
        Set<Course> electiveCourses = new LinkedHashSet<>(getCompletedCoursework());
        electiveCourses.removeAll(getRequiredCoursework());
        electiveCourses.retainAll(electiveCoursework);

        if (electiveCourses.isEmpty()) {
            System.out.println();
            return;
        }

        int totalCredits = 0;
        Set<Course> restrictedElectiveCourses = new LinkedHashSet<>();

        // Add courses to the list until the credit requirement is filled
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
        // Base case: If all required courses are done, just print newline
        if (getRequiredCoursework().containsAll(getCompletedCoursework())) {
            System.out.println();
            return;
        }

        // Part 1: Identifies Restricted Electives used 
        Set<Course> restrictedElectiveCourses = new LinkedHashSet<>(getCompletedCoursework());
        restrictedElectiveCourses.removeAll(getRequiredCoursework());
        restrictedElectiveCourses.retainAll(electiveCoursework);
        
        int totalCredits = 0;
        Set<Course> requiredRestricedElectiveCourses = new LinkedHashSet<>();
        
        // Calculate which courses were "consumed" by the restricted elective requirement
        for (Course course : restrictedElectiveCourses) {
            totalCredits += course.getCredits();
            requiredRestricedElectiveCourses.add(course);

            if (totalCredits > creditReq) {
                break;
            }
        }

        // Remove the consumed restricted electives from the pool
        restrictedElectiveCourses.removeAll(requiredRestricedElectiveCourses);

        // Part 2: Calculate Free Electives 
        // Free electives = (Completed - Required - Valid Electives) + (Overflow/Unused Restricted Electives)
        Set<Course> freeElectiveCourses = new LinkedHashSet<>(getCompletedCoursework());
        freeElectiveCourses.removeAll(getRequiredCoursework());
        freeElectiveCourses.removeAll(electiveCoursework);
        freeElectiveCourses.addAll(restrictedElectiveCourses);

        displayColoredCourses(freeElectiveCourses);
    }

    // Updates the grade of a required course in the plan.
    // Only updates if the current status is "R" (Required/Not Taken).
    public void updateRequiredCoursework(Course course, String grade) {
        for (Course reqCourse : requiredCoursework) {
            // Check if course matches AND is currently marked as 'Required' (uncompleted)
            if (reqCourse.equals(course) && reqCourse.getGrade().equals("R")) {
                reqCourse.setGrade(grade);
            }
        }
    }

    // Resets a required course status back to "R" (Required).
    // Used when a course was removed from completed coursework.
    public void updateRequiredCoursework(Course course) {
        for (Course reqCourse : requiredCoursework) {
            // Check if course matches AND is NOT currently 'Required' (meaning it was previously completed)
            if (reqCourse.equals(course) && !reqCourse.getGrade().equals("R")) {
                reqCourse.setGrade("R");
            }
        }
    }

    // Methods to be overridden by specific major subclasses
    public void displayPlanInfo() {}

    public void getDegreeSectionProgress() {}
}
