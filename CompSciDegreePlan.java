import java.util.Set;
import java.util.LinkedHashSet;

public final class CompSciDegreePlan extends DegreePlan {
    CompSciDegreePlan() {
        super("Computer Science (BS)");
        setAdvisor("Graves,Jaclyn");
        setAdvisorEmail("gravesj@rowan.edu");
        addAllDegreeRequirements();
        setDegreeCreditsReq(120);
    }

    public void addAllDegreeRequirements() {
        addRequiredCoursework(CompSciCatalog.addMajorRequiredCourses());
        addRequiredCoursework(MathCatalog.getCatalogCourse("MATH 01130"));
        addRequiredCoursework(MathCatalog.getCatalogCourse("MATH 01131"));
        addRequiredCoursework(MathCatalog.getCatalogCourse("MATH 01210"));
        addRequiredCoursework(PhysicsCatalog.getCatalogCourse("PHYS 00200"));
        addRequiredCoursework(StatsCatalog.getCatalogCourse("STAT 02290"));
    }
}

