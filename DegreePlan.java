public class DegreePlan extends Degree {
    private boolean degreeCompletion;
    private double degreeProgress;

    DegreePlan(String fieldOfStudy) {
        super(fieldOfStudy);
        this.degreeCompletion = false;
        this.degreeProgress = 0.0;
    }

    public void addDegreeRequirements() {

    }
}

