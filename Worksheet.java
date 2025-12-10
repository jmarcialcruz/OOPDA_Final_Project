public class Worksheet {
    private String academicStanding;
    private String classStanding;
    private DegreePlan degreePlan;

    // TODO: Fix progress percent bar, exceeds 100 percent due to credits

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
        
        if (progressPercentage > 100) {
            return 100;
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

        ColoredOutput.colorBrightGreen(" [" + percentBarStr + "]\n");
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
        System.out.println(ColoredOutput.BRIGHT_BLUE + "________________________________" + header + "________________________________" + ColoredOutput.RESET);
    }

    private void displayProgressStatus(int index, String str) {
        int progressStatus = degreePlan.getProgressBit(index);

        if (progressStatus == 1) {
            System.out.print(ColoredOutput.BRIGHT_GREEN + "[Y]" + ColoredOutput.RESET);
        }
        else {
            System.out.print(ColoredOutput.RED + "[N]" + ColoredOutput.RESET);
        }
        
        System.out.println(ColoredOutput.BRIGHT_CYAN + str + ColoredOutput.RESET);
    }

    private void displayDegreeProgressSection() {
        System.out.println(ColoredOutput.BRIGHT_BLUE + "________________________________DEGREE PROGRESS________________________________" + ColoredOutput.RESET);
        displayProgressStatus(0, " 120 credits are required for this degree for graduation");
        displayProgressStatus(1, " Minimum 30 credits Taken in Residence");
        displayProgressStatus(2, " Minimum 2.0 GPA Requirement");
        displayProgressStatus(3, " Rowan Experience Requirements");
        displayProgressStatus(4, " Rowan Core Course");
        displayProgressStatus(5, " Non Program Electives");
        displayProgressStatus(6, " Major Requirements (Includes Restricted Electives)");
        displayProgressStatus(7, " Free Elective Requirement\n");
        displayDegreeProgressBar();
    }

    public void displayWorksheetInfo() {
        System.out.println(ColoredOutput.BRIGHT_BLUE + "________________________________WORKSHEET INFO________________________________" + ColoredOutput.RESET);
        displayWorksheetHeader();
        displayDegreeProgressSection();

        getDegreePlan().displayPlanInfo();
    }

    public boolean isValidNumericGrade(String letterGrade) {
        if (letterGrade.length() > 2) {
            return false;
        }
        else {
            switch(letterGrade) {
                case "A-"   : return true;
                case "B+"   : return true;
                case "B-"   : return true;
                case "C+"   : return true;
                case "C-"   : return true;
                case "D+"   : return true;
            }
        }

        if (letterGrade.length() > 1) {
            return false;
        }
        else {
            switch(letterGrade) {
                case "A"    : return true;
                case "B"    : return true;
                case "C"    : return true;
                case "D"    : return true;
                case "F"    : return true;
                default     : 
                    System.out.println("Invalid Letter Grade"); 
                    return false;
            }
        }
    }

    public double getNumericGrade(String letterGrade) {
        switch(letterGrade) {
            case "A"    : return 4.0;
            case "A-"   : return 3.7;
            case "B+"   : return 3.3;
            case "B"    : return 3.0;
            case "B-"   : return 2.7;
            case "C+"   : return 2.3;
            case "C"    : return 2.0;
            case "C-"   : return 1.7;
            case "D+"   : return 1.3;
            case "D"    : return 1.0;
            case "F"    : return 0.0;
            default     : return 0.0;
        }
    }
}

