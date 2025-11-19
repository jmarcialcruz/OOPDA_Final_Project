public class Worksheet {
    private String academicStanding;
    private String classStanding;
    private DegreePlan degreePlan;
    private double degreeProgress;
    private boolean degreeCompletion;

    // TODO: Possibly all degree plans to this worksheet and create on worksheet in Degree Works
    Worksheet(DegreePlan degreePlan) {
        this.degreePlan = degreePlan;
        this.degreeProgress = 0.0;
        this.degreeCompletion = false;
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

    public String getClassStanding() {
        int totalCredits = 0;
        for (Course course : degreePlan.getCompletedCoursework()) {
            totalCredits += course.getCredits();
        }

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

    public DegreePlan getDegreePlan() {
        return this.degreePlan;
    }

    public boolean getDegreeCompletion() {
        return this.degreeCompletion;
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

    public void displayWorksheetInfo() {
        System.out.println("=== Worksheet Info ===");
        System.out.print("Class Standing:    " + getClassStanding() + "\t\t\t");
        System.out.println("Degree: " + degreePlan.getFieldOfStudy());
        System.out.print("Cumulative GPA:    " + String.format("%.3f", calculateGPA()) + "  \t\t\t");
        System.out.println("Academic Standing: " + getAcademicStanding());
        System.out.println("Degree :\t   " + getDegreeCompletion());
        System.out.println("Advisor:\t   " + degreePlan.getAdvisor() + " - " + degreePlan.getAdvisorEmail());
        System.out.println();
        
        getDegreePlan().displayInfo();
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
            default     : 
                System.out.println("Invalid Letter Grade"); 
                return -1.0;
        }
    }

}
