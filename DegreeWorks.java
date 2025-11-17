import java.util.ArrayList;

public class DegreeWorks {
    public static void main(String[] args) {
        CompSciCatalog csCatalog = new CompSciCatalog();
        MathCatalog mathCatalog = new MathCatalog();
        CompSciDegreePlan csDegree = new CompSciDegreePlan();

        csDegree.addRequiredCoursework(csCatalog.addMajorRequiredCourses());
        csDegree.addRequiredCoursework(csCatalog.addElectiveCourses());
        csDegree.addRequiredCoursework(mathCatalog.getCatalogCourse("MATH 01131"));
        csDegree.addRequiredCoursework(mathCatalog.getCatalogCourse("MATH 03150"));
        csDegree.displayInfo();
    }
}
