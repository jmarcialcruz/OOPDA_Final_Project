import java.util.Set;
import java.util.LinkedHashSet;

public final class PhysicsDegreePlan extends DegreePlan implements DegreeInfo {
    PhysicsDegreePlan() {
        super("Physics (BS)");
        setAdvisor("Mason,Cristine");
        setAdvisorEmail("masonc@rowan.edu");
        addAllDegreeRequirements();
        setDegreeCreditsReq(120);
    }

    public void addAllDegreeRequirements() {
        addRequiredCoursework(PhysicsCatalog.addMajorRequiredCourses());
    }

    public void displayMajorRequirementsInfo(){
        DegreeInfo.displaySectionHeader("PHYSICS (1902)");
        displayEachCourse(PhysicsCatalog.addMajorRequiredCourses());
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

