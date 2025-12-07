import java.util.Set;
import java.util.LinkedHashSet;

public final class MathDegreePlan extends DegreePlan implements DegreeInfo {
    MathDegreePlan() {
        super("Math (BS)");
        setAdvisor("Mason,Cristine");
        setAdvisorEmail("masonc@rowan.edu");
        addAllDegreeRequirements();
        setDegreeCreditsReq(120);
    }

    public void addAllDegreeRequirements() {
        addRequiredCoursework(MathCatalog.addMajorRequiredCourses());
    }

    public void displayMajorRequirementsInfo(){
        DegreeInfo.displaySectionHeader("MATHEMATICS (1702)");
        displayEachCourse(MathCatalog.addMajorRequiredCourses());
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

