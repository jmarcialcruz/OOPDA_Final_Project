import java.util.Set;
import java.util.LinkedHashSet;

public final class PhysicsDegreePlan extends DegreePlan {
    PhysicsDegreePlan() {
        super("Physics (BS)");
        setAdvisor("Mason,Cristine");
        setAdvisorEmail("masonc@rowan.edu");
        addAllDegreeRequirements();
        setDegreeCreditsReq(120);
    }

    public void addAllDegreeRequirements() {
        addRequiredCoursework(PhysicsCatalog.addMajorRequiredCourses());
        addRequiredCoursework(MathCatalog.getCatalogCourse("MATH 01130"));
        addRequiredCoursework(MathCatalog.getCatalogCourse("MATH 01131"));
        addRequiredCoursework(MathCatalog.getCatalogCourse("MATH 01230"));
    }
}

