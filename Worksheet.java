public class Worksheet implements UserFeedback{
    private String advisor;
    private String academicStanding;
    private String classStanding;
    private Degree degree;
    private double degreeProgress;
    private boolean degreeCompletion;

    Worksheet(Degree degree) {
        this.degree = degree;
        this.degreeProgress = 0.0;
        this.degreeCompletion = false;
    }

    public String getAdvisor() {
        return this.advisor;
    }

    public String getAcademicStanding() {
        if (calculateGPA() > 2.0) {
            academicStanding = "Good Standing";
        }
        else {
            academicStanding = "Notice";
        }

        return this.academicStanding;
    }

    public String getClassStanding() {
        int totalCredits = 0;
        for (FinishedCourse course : degree.getCoursework()) {
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

    public Degree getDegree() {
        return this.degree;
    }

    public boolean getDegreeCompletion() {
        return this.degreeCompletion;
    }

    public double calculateGPA() {
        double totalQualityPoints = 0; 
        double totalCredits = 0;

        for(FinishedCourse course : degree.getCoursework()) {
            totalQualityPoints += course.getCredits() * getNumericGrade(course.getGrade());
            totalCredits += course.getCredits();
        }

        return totalQualityPoints / totalCredits;
    }

    public void displayInfo() {
        System.out.println("=== Worksheet Info ===");
        System.out.println("Degree:            " + degree.getFieldOfStudy());
        System.out.println("Cumulative GPA:    " + String.format("%.3f", calculateGPA()));
        System.out.println("Academic Standing: " + getAcademicStanding());
        System.out.println("Class Standing:    " + getClassStanding());
        System.out.println("Degree Finished:   " + getDegreeCompletion());
        System.out.println();
        
        getDegree().displayInfo();
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
