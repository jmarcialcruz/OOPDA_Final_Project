import java.util.Set;
import java.util.List;
import java.util.LinkedHashSet;
import java.util.ArrayList;

public class MathCatalog extends Catalog {
    private static List<Set<Course>> catalogSectionList = new ArrayList<>();
    private static final int numberOfSections = 4;

    public static int getNumberOfSections() {
        return numberOfSections;
    }

    public static List<Set<Course>> getCatalogSectionList() {
        catalogSectionList.add(getRowanExpCourses());
        catalogSectionList.add(getRowanCoreCourses());
        catalogSectionList.add(getNonProgramCourses());
        catalogSectionList.add(getMajorRequiredCourses());
        return catalogSectionList;
    }

    public static Set<Course> getAllCourses() {
        Set<Course> allCourses = new LinkedHashSet<>();
        allCourses.addAll(getMajorRequiredCourses());
        allCourses.addAll(getElectiveCourses());
        allCourses.addAll(getOptionalCourses());
        return allCourses;
    }

    public static Set<Course> getMajorRequiredCourses() {
        Set<Course> majorRequiredCourses = new LinkedHashSet<>();
        majorRequiredCourses.add(new Course("MATH", "03150", "Discrete Mathematics", 3, "R"));     
        majorRequiredCourses.add(new Course("MATH", "01130", "Calculus I", 4, "R"));       
        majorRequiredCourses.add(new Course("MATH", "01131", "Calculus II ", 4, "R"));     
        majorRequiredCourses.add(new Course("MATH", "01230", "Calculus III ", 4, "R"));     
        majorRequiredCourses.add(new Course("MATH", "01210", "Linear Algebra", 3, "R"));     
        majorRequiredCourses.add(new Course("MATH", "01231", "Ordinary Differential Equations", 3, "R")); 
        majorRequiredCourses.add(new Course("MATH", "01300", "Mathematical Proof Writing", 3, "R")); 

        majorRequiredCourses.add(new Course("MATH", "01340", "Modern Algebra I", 3, "R")); 
        majorRequiredCourses.add(new Course("MATH", "01330", "Intro to Real Analysis I", 3, "R")); 
        majorRequiredCourses.add(MiscCatalog.getCatalogCourse("STAT 02360"));
        majorRequiredCourses.add(new Course("MATH", "01430", "Intro to Complex Analysis", 3, "R")); 
        majorRequiredCourses.add(new Course("MATH", "01498", "Mathematics Seminar", 3, "R")); 

        return majorRequiredCourses;
    }

    public static Set<Course> getNonProgramCourses(){
        Set<Course> nonProgramCourses = new LinkedHashSet<>();
        nonProgramCourses.add(CompSciCatalog.getCatalogCourse("CS 04103"));
        nonProgramCourses.add(CompSciCatalog.getCatalogCourse("CS 04225"));
        nonProgramCourses.add(PhysicsCatalog.getCatalogCourse("PHYS 00200"));
        nonProgramCourses.add(PhysicsCatalog.getCatalogCourse("PHYS 00221"));
        nonProgramCourses.add(MiscCatalog.getCatalogCourse("STAT 02320"));

        return nonProgramCourses;
    }

    public static Set<Course> getRowanExpCourses() {
        Set<Course> rowanExpCourses = new LinkedHashSet<>();
        rowanExpCourses.add(MiscCatalog.getCatalogCourse("GERM 03100"));
        rowanExpCourses.add(MathCatalog.getCatalogCourse("MATH 01000")); 
        rowanExpCourses.add(MathCatalog.getCatalogCourse("MATH 01498"));

        return rowanExpCourses;
    }

    public static Set<Course> getRowanCoreCourses() {
        Set<Course> rowanCoreCourses = new LinkedHashSet<>();
        rowanCoreCourses.add(MiscCatalog.getCatalogCourse("ECON 04101"));
        rowanCoreCourses.addAll(MiscCatalog.getStandardRowanCoreCourses());
        return rowanCoreCourses;
    }

    public static Set<Course> getElectiveCourses() {
        Set<Course> electiveCourses = new LinkedHashSet<>();
        electiveCourses.add(new Course("MATH", "01205", "Tech Tools for Discovering Mathematics", 2, "R"));       
        electiveCourses.add(new Course("MATH", "01310", "College Geometry", 4, "R")); 
        electiveCourses.add(new Course("MATH", "01331", "Intro to Real Analysis II", 3, "R"));       
        electiveCourses.add(new Course("MATH", "01341", "Modern Algebra II", 3, "R"));  
        electiveCourses.add(new Course("MATH", "01354", "Intro to Topology", 3, "R"));   
        electiveCourses.add(new Course("MATH", "01332", "Intro to Numerical Analysis", 3, "R"));       
        electiveCourses.add(new Course("MATH", "03400", "Applications of Mathematics", 3, "R"));       
        electiveCourses.add(new Course("MATH", "01421", "Mathematics Field Experience", 3, "R"));       
        electiveCourses.add(new Course("MATH", "01386", "Partial Differential Equations", 3, "R"));       
        electiveCourses.add(new Course("MATH", "01352", "Theory of Numbers", 3, "R"));       
        electiveCourses.add(new Course("MATH", "01410", "History of Mathematics", 3, "R"));       
        electiveCourses.add(new Course("MATH", "03411", "Determinstic Models in Operations Research", 3, "R"));       
        electiveCourses.add(new Course("MATH", "03412", "Stochastic Models in Operations Research", 3, "R"));       
        return electiveCourses;
    }

    public static Set<Course> getOptionalCourses() {
        Set<Course> optionalCourses = new LinkedHashSet<>();
        optionalCourses.add(new Course("MATH", "01235", "Mathematics for Engineering Analysis", 4, "R")); 
        optionalCourses.add(new Course("MATH", "01000", "Mathematics Learning Community", 1, "R")); 

        return optionalCourses;
    }

    public static boolean isCatalogCourse(String courseName) {
        return isCatalogCourse(courseName, "MATH ", getAllCourses());
    }

    public static Course getCatalogCourse(String elective) {
        return getCatalogCourse(elective, getAllCourses());
    }
}
