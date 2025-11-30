import java.util.Set;
import java.util.LinkedHashSet;

public final class EceDegreePlan extends DegreePlan {
    EceDegreePlan() {
        super("Electrical and Computer Engineering (BS)");
        setAdvisor("Tang,Gina");
        setAdvisorEmail("tang@rowan.edu");
        addAllDegreeRequirements();
        setDegreeCreditsReq(128);
    }

    public void addAllDegreeRequirements() {
        addRequiredCoursework(EceCatalog.addMajorRequiredCourses());
        addRequiredCoursework(MathCatalog.getCatalogCourse("MATH 01130"));
        addRequiredCoursework(MathCatalog.getCatalogCourse("MATH 01131"));
        addRequiredCoursework(MathCatalog.getCatalogCourse("MATH 01230"));
        addRequiredCoursework(PhysicsCatalog.getCatalogCourse("PHYS 00200"));
        addRequiredCoursework(StatsCatalog.getCatalogCourse("STAT 02286"));
    }
}

