import java.util.Set;
import java.util.LinkedHashSet;

public final class EceDegreePlan extends DegreePlan implements DegreeInfo {
    EceDegreePlan() {
        super("Electrical and Computer Engineering (BS)");
        setAdvisor("Tang,Gina");
        setAdvisorEmail("tang@rowan.edu");
        addAllDegreeRequirements();
        setDegreeCreditsReq(128);
    }

    public void addAllDegreeRequirements() {
        addRequiredCoursework(EceCatalog.addMajorRequiredCourses());
    }

        public void displayMajorRequirementsInfo(){
        DegreeInfo.displaySectionHeader("Electrical & Computer Engineering (0919)");
        displayEachCourse(EceCatalog.addMajorRequiredCourses());
    }

    public void displayNonProgramRequirementsInfo() {
        DegreeInfo.displaySectionHeader("NON-PROGRAM REQUIREMENTS");
        System.out.println();
    }

    public void displayPlanInfo() {
        displayNonProgramRequirementsInfo();
        displayMajorRequirementsInfo();
    }
}

