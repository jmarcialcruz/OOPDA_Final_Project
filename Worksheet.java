public class Worksheet {
    private String academicStanding;
    private String classStanding;
    private DegreePlan degreePlan;

    Worksheet(DegreePlan degreePlan) {
        this.degreePlan = degreePlan;
    }

    public String getAcademicStanding() {
        if (calculateGPA() > 2.0) {
            academicStanding = "GOOD STANDING";
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

        if (totalCredits >= 90) {
            classStanding = "Senior";
        }
        else if (totalCredits >= 60) {
            classStanding = "Junior";
        }
        else if (totalCredits >= 30) {
            classStanding = "Sophomore";
            getDegreePlan().setProgressBit(1);
        }
        else {
            classStanding = "Freshman";
            getDegreePlan().unsetProgressBit(1);
        }

        return classStanding;
    }

    public void updateDegreePlanCoursework(Course course, String grade) {
        if (degreePlan.getCompletedCoursework().contains(course)) {
            System.out.println("\nERROR: Duplicate course enter!");
            return;
        }

        this.degreePlan.addToCompletedCoursework(course, grade);
        this.degreePlan.updateRequiredCoursework(course, grade);
    }

    public void updateDegreePlanCoursework(Course course) {
        if (!degreePlan.getCompletedCoursework().contains(course)) {
            System.out.println("\nERROR: Course cannot be removed!");
            return;
        }

        this.degreePlan.removeFromCompletedCoursework(course);
        this.degreePlan.updateRequiredCoursework(course);
    }

    public DegreePlan getDegreePlan() {
        return this.degreePlan;
    }

    // Check if all bits are set in bitmap
    public boolean getDegreeCompletion() {
        if (degreePlan.getProgressBitmap() != 0xFF) {
            return false;
        }

        return true;
    }

    public double calculateGPA() {
        double totalQualityPoints = 0;
        double totalCredits = 0;

        for(Course course : degreePlan.getCompletedCoursework()) {
            totalQualityPoints += course.getCredits() * getNumericGrade(course.getGrade());
            totalCredits += course.getCredits();
        }

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

        double creditsNotApplied = freeElectiveCredits - freeElectiveCreditsReq;

        if (creditsNotApplied > 0) {
            completedCredits -= creditsNotApplied;
        }

        int progressPercentage = (int) ((completedCredits/creditReq) * 100);

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
        int percentBarStatus = (int) (((double) percentBarLength * (double) degreeProgressPercent) / 100);
        String percentBarStr = "";

        ColoredOutput.colorBlack(ColoredOutput.BRIGHT_GREEN_BACKGROUND + "Progress: [" + degreeProgressPercent + "%]");

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

        switch (progressStatus) {
            case 1 ->  ColoredOutput.colorBrightGreen("[Y]");
            default -> ColoredOutput.colorRed("[N]");
        };

        ColoredOutput.colorBrightCyan(str);
    }

    private void displayDegreeProgressSection() {
        ColoredOutput.colorBrightBlue("________________________________DEGREE PROGRESS________________________________\n");
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
        return switch (letterGrade) {
            case "A", "A-", "B+", "B", "B-", "C+", "C", "C-", "D+", "D", "F" -> true;
                default -> false;
        };
    }

    public double getNumericGrade(String letterGrade) {
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

