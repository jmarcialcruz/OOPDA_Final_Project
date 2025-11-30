import java.util.Set;
import java.util.LinkedHashSet;

public final class MathDegreePlan extends DegreePlan {
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
}

