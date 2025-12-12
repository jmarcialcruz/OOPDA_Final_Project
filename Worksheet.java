/**
 * Represents the active worksheet for a specific student's degree plan.
 *
 * This class acts as the primary controller for calculating GPA, academic
 * standing, and degree progress. It aggregates data from the DegreePlan class
 * to generate the final visual report/audit for the user.
 *
 * @author  Jordi Marcial Cruz
 */

public class Worksheet {
    private String academicStanding;
    private String classStanding;
    private DegreePlan degreePlan;

    Worksheet(DegreePlan degreePlan) {
        this.degreePlan = degreePlan;
    }

    public String getAcademicStanding() {
        // Evaluate GPA against the 2.0 minimum requirement
        if (calculateGPA() > 2.0) {
            academicStanding = "GOOD STANDING";
            // Set Bit 2: GPA Requirement Met
            getDegreePlan().setProgressBit(2);
        }
        else if (getDegreePlan().getCompletedCredits() == 0) {
            academicStanding = "N/A";
            getDegreePlan().unsetProgressBit(2);
        }
        else {
            academicStanding = "NOTICE";
            getDegreePlan().unsetProgressBit(2);
        }

        return this.academicStanding;
    }

    public String getClassStanding() {
        int totalCredits = getDegreePlan().getCompletedCredits();

        // Determine seniority based on total credit thresholds
        if (totalCredits >= 90) {
            classStanding = "Senior";
        }
        else if (totalCredits >= 60) {
            classStanding = "Junior";
        }
        else if (totalCredits >= 30) {
            classStanding = "Sophomore";
            // Set Bit 1: Residence Requirement (Proxy check > 30 credits)
            getDegreePlan().setProgressBit(1);
        }
        else {
            classStanding = "Freshman";
            getDegreePlan().unsetProgressBit(1);
        }

        return classStanding;
    }

    public void updateDegreePlanCoursework(Course course, String grade) {
        // Prevent adding the same course object twice
        if (degreePlan.getCompletedCoursework().contains(course)) {
            System.out.println("\nERROR: Duplicate course enter!");
            return;
        }

        // Add to completed list and update specific requirement grades
        this.degreePlan.addToCompletedCoursework(course, grade);
        this.degreePlan.updateRequiredCoursework(course, grade);
    }

    public void updateDegreePlanCoursework(Course course) {
        // Validate existence before removal
        if (!degreePlan.getCompletedCoursework().contains(course)) {
            System.out.println("\nERROR: Course cannot be removed!");
            return;
        }

        // Remove from list and reset requirement status to "Required"
        this.degreePlan.removeFromCompletedCoursework(course);
        this.degreePlan.updateRequiredCoursework(course);
    }

    public DegreePlan getDegreePlan() {
        return this.degreePlan;
    }

    // Check if all bits (0-7) are set in the progress bitmap (0xFF is 11111111)
    public boolean getDegreeCompletion() {
        if (degreePlan.getProgressBitmap() != 0xFF) {
            return false;
        }

        return true;
    }

    public double calculateGPA() {
        double totalQualityPoints = 0;
        double totalCredits = 0;

        // Iterate through all completed courses to sum quality points
        for(Course course : degreePlan.getCompletedCoursework()) {
            totalQualityPoints += course.getCredits() * getNumericGrade(course.getGrade());
            totalCredits += course.getCredits();
        }

        // Set Bit 0: Total Credit Requirement Met
        if (totalCredits >= getDegreePlan().getDegreeCreditsReq()) {
            getDegreePlan().setProgressBit(0);
        }
        else {
            getDegreePlan().unsetProgressBit(0);
        }

        return totalQualityPoints / totalCredits;
    }

    // Checks total credits 'applied' to degree plan, ignores extra credits added
    public int calculateDegreeProgress() {
        double creditReq = (double) (degreePlan.getDegreeCreditsReq() + degreePlan.getRestrictedElectiveCreditsReq());
        double completedCredits = (double) getDegreePlan().getCompletedCredits();

        double freeElectiveCredits = (double) degreePlan.getFreeElectiveCredits();
        double freeElectiveCreditsReq = (double) degreePlan.getFreeElectiveCreditsReq();

        // Calculate "wasted" credits (free electives taken beyond the requirement)
        double creditsNotApplied = freeElectiveCredits - freeElectiveCreditsReq;

        // Subtract excess credits so progress doesn't exceed 100% purely on extra electives
        if (creditsNotApplied > 0) {
            completedCredits -= creditsNotApplied;
        }

        int progressPercentage = (int) ((completedCredits/creditReq) * 100);

        // Logic to cap percentage at 99% until all specific requirements (bitmap) are met
        if (degreePlan.getProgressBitmap() == 0xFF ) {
            return 100;
        }
        else if (progressPercentage > 99) {
            return 99;
        }
        else{
            return progressPercentage;
        }
    }

    public void displayDegreeProgressBar() {
        int degreeProgressPercent = calculateDegreeProgress();
        int percentBarLength = 30;
        
        // Calculate how many '#' characters to print based on percentage
        int percentBarStatus = (int) (((double) percentBarLength * (double) degreeProgressPercent) / 100);
        String percentBarStr = "";

        ColoredOutput.colorBlack(ColoredOutput.BRIGHT_GREEN_BACKGROUND + "Progress: [" + degreeProgressPercent + "%]");

        // Build the progress bar string
        for (int i = 0; i < percentBarLength; i++) {
            if (i < percentBarStatus) {
                percentBarStr = percentBarStr.concat("#");
            }
            else {
                percentBarStr = percentBarStr.concat(".");
            }
        }

        ColoredOutput.colorBrightGreen(" [" + percentBarStr + "]\n\n");
    }

    public void displayWorksheetHeader() {
        // Recalculate section progress before displaying
        degreePlan.getDegreeSectionProgress();
        
        System.out.print(ColoredOutput.BRIGHT_CYAN + "Class Standing:    " + getClassStanding() + "\t\t\t");
        System.out.println("Degree: " + degreePlan.getFieldOfStudy());
        System.out.print("Cumulative GPA:    " + String.format("%.3f", calculateGPA()) + "  \t\t\t");
        System.out.println("Academic Standing: " + getAcademicStanding());
        System.out.print("Degree Completed:  " + getDegreeCompletion() + " \t\t\t");
        System.out.println("Total Credits: " + getDegreePlan().getCompletedCredits());
        System.out.println("Advisor:\t   " + degreePlan.getAdvisor() + " - " + degreePlan.getAdvisorEmail());
        System.out.println();
    }

    private void displaySectionHeader(String header) {
        ColoredOutput.colorBrightBlue("________________________________" + header + "________________________________\n");
    }

    private void displayProgressStatus(int index, String str) {
        int progressStatus = degreePlan.getProgressBit(index);

        // Display Green [Y] or Red [N] based on the specific bit status
        switch (progressStatus) {
            case 1 ->  ColoredOutput.colorBrightGreen("[Y]");
            default -> ColoredOutput.colorRed("[N]");
        };

        ColoredOutput.colorBrightCyan(str);
    }

    private void displayDegreeProgressSection() {
        ColoredOutput.colorBrightBlue("________________________________DEGREE PROGRESS________________________________\n");
        // Print status for all 8 tracked requirements (Bits 0-7)
        displayProgressStatus(0, " 120 credits are required for this degree for graduation\n");
        displayProgressStatus(1, " Minimum 30 credits Taken in Residence\n");
        displayProgressStatus(2, " Minimum 2.0 GPA Requirement\n");
        displayProgressStatus(3, " Rowan Experience Requirements\n");
        displayProgressStatus(4, " Rowan Core Course\n");
        displayProgressStatus(5, " Non Program Electives\n");
        displayProgressStatus(6, " Major Requirements (Includes Restricted Electives)\n");
        displayProgressStatus(7, " Free Elective Requirement\n\n");
        
        displayDegreeProgressBar();
    }

    public void displayWorksheetInfo() {
        ColoredOutput.colorBrightBlue("________________________________WORKSHEET INFO________________________________\n");
        displayWorksheetHeader();
        displayDegreeProgressSection();
        getDegreePlan().displayPlanInfo();
    }

    public boolean isValidLetterGrade(String letterGrade) {
        // Validate input against standard grade set
        return switch (letterGrade) {
            case "A", "A-", "B+", "B", "B-", "C+", "C", "C-", "D+", "D", "F" -> true;
                default -> false;
        };
    }

    public double getNumericGrade(String letterGrade) {
        // Convert letter grade to 4.0 scale quality points
        return switch(letterGrade) {
            case "A"  -> 4.0;
            case "A-" -> 3.7;
            case "B+" -> 3.3;
            case "B"  -> 3.0;
            case "B-" -> 2.7;
            case "C+" -> 2.3;
            case "C"  -> 2.0;
            case "C-" -> 1.7;
            case "D+" -> 1.3;
            case "D"  -> 1.0;
            case "F"  -> 0.0;
            default   -> -1.0;
        };
    }
}
