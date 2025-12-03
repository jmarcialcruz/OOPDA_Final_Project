public class Worksheet {
    private String academicStanding;
    private String classStanding;
    private DegreePlan degreePlan;
    private double degreeProgress;

    // TODO: Possibly all degree plans to this worksheet and create on worksheet in Degree Works
    // TODO: Include what if feature for individual worksheet
    // TODO: Semester of when course was taken
    // TODO: Override equals and hashCode methods

    Worksheet(DegreePlan degreePlan) {
        this.degreePlan = degreePlan;
        this.degreeProgress = 0.0;
    }

    public String getAcademicStanding() {
        if (calculateGPA() > 2.0) {
            academicStanding = "GOOD STANDING";
        }
        else if (getCredits() == 0) {
            academicStanding = "N/A";
        }
        else {
            academicStanding = "NOTICE";
        }

        return this.academicStanding;
    }

    public int getTotalCredits() {
        int totalCredits = 0;
        for (Course course : degreePlan.getCompletedCoursework()) {
            totalCredits += course.getCredits();
        }

        return totalCredits;
    }

    public String getClassStanding() {
        int totalCredits = getTotalCredits();

        if (totalCredits > 90) {
            classStanding = "Senior";
        } 
        else if (totalCredits > 60) {
            classStanding = "Junior";
        }
        else if (totalCredits > 30) {
            classStanding = "Sophomore";
        }
        else {
            classStanding = "Freshman";
        }

        return classStanding;
    }

    public void updateDegreePlanCoursework(Course course, String grade) {
        this.degreePlan.addToCompletedCoursework(course, grade);
        this.degreePlan.removeFromRequiredCoursework(course);
    }

    public DegreePlan getDegreePlan() {
        return this.degreePlan;
    }

    public boolean getDegreeCompletion() {
        int totalCredits = getTotalCredits();
        if (totalCredits < degreePlan.getDegreeCreditsReq()){
            return false;
        }
        else {
            return true;
        }
    }

    public double calculateGPA() {
        double totalQualityPoints = 0; 
        double totalCredits = 0;

        for(Course course : degreePlan.getCompletedCoursework()) {
            totalQualityPoints += course.getCredits() * getNumericGrade(course.getGrade());
            totalCredits += course.getCredits();
        }

        return totalQualityPoints / totalCredits;
    }

    public int getCredits() {
        int totalCredits = 0;

        for(Course course : degreePlan.getCompletedCoursework()) {
            totalCredits += course.getCredits();
        }

        return totalCredits;
    }

    public void dispalyWorksheetHeader() {
        System.out.print(ColoredOutput.BRIGHT_CYAN + "Class Standing:    " + getClassStanding() + "\t\t\t");
        System.out.println("Degree: " + degreePlan.getFieldOfStudy());
        System.out.print("Cumulative GPA:    " + String.format("%.3f", calculateGPA()) + "  \t\t\t");
        System.out.println("Academic Standing: " + getAcademicStanding());
        System.out.print("Degree Completed:  " + getDegreeCompletion() + " \t\t\t");
        System.out.println("Total Credits: " + getTotalCredits());
        System.out.println("Advisor:\t   " + degreePlan.getAdvisor() + " - " + degreePlan.getAdvisorEmail());
        System.out.println();
    }

    public void displayWorksheetSections() {
        displayDegreeProgressSection();
        displayRowanExperienceSection();
    }

    public void displayRowanExperienceSection() {
        System.out.print(ColoredOutput.BRIGHT_BLUE);
        System.out.println("________________________________ROWAN EXPERIENCE________________________________");
        System.out.println(ColoredOutput.RESET);
    }

    public void displayRowanCoreSection() {
        System.out.print(ColoredOutput.BRIGHT_BLUE);
        System.out.println("________________________________ROWAN CORE________________________________");
        System.out.println(ColoredOutput.RESET);
    }

    public void displayNonProgramReqSection() {
        // TODO: Fill in this section
        // TODO: Create custom for each degree plan
    }

    public void displayProgramReqSection() {
        // TODO: Fill in this section
        // TODO: Create custom for each degree plan
    }

    public void displayDegreeProgressSection() {
        System.out.println(ColoredOutput.BRIGHT_BLUE + "________________________________DEGREE PROGRESS________________________________" + ColoredOutput.RESET);
        System.out.println(ColoredOutput.RED + "[N]" + ColoredOutput.BRIGHT_CYAN + " 120 credits are required for this degree for graduation");
        System.out.println(ColoredOutput.BRIGHT_GREEN + "[Y]" + ColoredOutput.BRIGHT_CYAN + " Minimum 30 credits Taken in Residence");
        System.out.println(ColoredOutput.BRIGHT_GREEN + "[Y]" + ColoredOutput.BRIGHT_CYAN + " Minimum 2.0 GPA Requirement");
        System.out.println(ColoredOutput.RED + "[N]" + ColoredOutput.BRIGHT_CYAN + " Rowan Experience Requirements");
        System.out.println(ColoredOutput.BRIGHT_GREEN + "[Y]" + ColoredOutput.BRIGHT_CYAN + " Rowan Core Course");
        System.out.println(ColoredOutput.BRIGHT_GREEN + "[Y]" + ColoredOutput.BRIGHT_CYAN + " Non Program Electives");
        System.out.println(ColoredOutput.RED + "[N]" + ColoredOutput.BRIGHT_CYAN + " Major Requirements");
        System.out.println(ColoredOutput.BRIGHT_GREEN + "[Y]" + ColoredOutput.BRIGHT_CYAN + " Free Elective Requirement\n" + ColoredOutput.RESET);
        System.out.print(ColoredOutput.BRIGHT_GREEN_BACKGROUND + ColoredOutput.BLACK + "Progress: [" + degreePlan.getDegreeProgress() + "%]");
        System.out.println(ColoredOutput.RESET + ColoredOutput.BRIGHT_GREEN +  " [" + "##############" + ".............." + "]\n");
    }

    public void displayWorksheetInfo() {
        System.out.println(ColoredOutput.BRIGHT_BLUE + "________________________________WORKSHEET INFO________________________________" + ColoredOutput.RESET);
        dispalyWorksheetHeader();
        displayWorksheetSections();
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
