import java.util.Set;
import java.util.List;
import java.util.LinkedHashSet;
import java.util.ArrayList;

/**
 * Static catalog definition for the Physics degree program.
 *
 * This class serves as a database of all valid Physics courses, including
 * major requirements, restricted electives, and relevant cross-discipline
 * courses (Math, CS, Chemistry). It provides methods to retrieve specific
 * subsets of courses for degree planning.
 *
 * @author  Jordi Marcial Cruz
 */

public class PhysicsCatalog extends Catalog {
    private static List<Set<Course>> catalogSectionList = new ArrayList<>();
    private static final int numberOfSections = 4;

    public static int getNumberOfSections() {
        return numberOfSections;
    }

    public static List<Set<Course>> getCatalogSectionList() {
        catalogSectionList.clear();
        catalogSectionList.add(getRowanExpCourses());
        catalogSectionList.add(getRowanCoreCourses());
        catalogSectionList.add(getNonProgramCourses());
        catalogSectionList.add(getMajorRequiredCourses());
        return catalogSectionList;
    }

    public static Set<Course> getAllCourses() {
        // specific implementation that aggregates all Physics-related courses into one set
        Set<Course> allCourses = new LinkedHashSet<>();
        allCourses.addAll(getMajorRequiredCourses());
        allCourses.addAll(getOptionalCourses());
        return allCourses;
    }

    public static Set<Course> getMajorRequiredCourses() {
        Set<Course> majorRequiredCourses = new LinkedHashSet<>();
        
        // Add Math prerequisites
        majorRequiredCourses.add(MathCatalog.getCatalogCourse("MATH 01131"));        
        majorRequiredCourses.add(MathCatalog.getCatalogCourse("MATH 01230"));        
        
        // Add Physics Core
        majorRequiredCourses.add(new Course("PHYS", "00130", "Building Momentum as a Physics Student", 1, "R"));        
        majorRequiredCourses.add(new Course("PHYS", "00200", "Intro to Mechanics", 4, "R"));        
        majorRequiredCourses.add(new Course("PHYS", "00221", "Intro to Thermodynamics, Fluids, Waves, & Optics", 4, "R"));      
        majorRequiredCourses.add(new Course("PHYS", "00222", "Intro to Electricity & Magnetism", 4, "R"));      
        
        // Add Advanced Math requirements
        majorRequiredCourses.add(MathCatalog.getCatalogCourse("MATH 01210"));        
        majorRequiredCourses.add(MathCatalog.getCatalogCourse("MATH 01231"));        
        
        // Add Advanced Physics Core
        majorRequiredCourses.add(new Course("PHYS", "00300", "Modern Physics", 4, "R"));      
        majorRequiredCourses.add(new Course("PHYS", "00351", "Physics Research Methods I", 2, "R"));        
        majorRequiredCourses.add(new Course("PHYS", "00352", "Physics Research Methods II", 2, "R"));        
        majorRequiredCourses.add(new Course("PHYS", "00310", "Analytical Mechanics", 4, "R"));        
        majorRequiredCourses.add(new Course("PHYS", "00320", "Electricity & Magnetism I", 4, "R"));        
        majorRequiredCourses.add(new Course("PHYS", "00410", "Quantum Mechanics I", 4, "R"));        
        majorRequiredCourses.add(new Course("PHYS", "00430", "Statistical Physics", 3, "R"));        

        return majorRequiredCourses;
    }

    public static Set<Course> getNonProgramCourses(){
        // Courses required for the Physics degree but provided by other departments
        Set<Course> nonProgramCourses = new LinkedHashSet<>();
        nonProgramCourses.add(CompSciCatalog.getCatalogCourse("CS 04103"));
        nonProgramCourses.add(MiscCatalog.getCatalogCourse("PHIL 09261"));
        nonProgramCourses.add(MathCatalog.getCatalogCourse("MATH 01130"));
        nonProgramCourses.add(MiscCatalog.getCatalogCourse("CHEM 06100"));
        nonProgramCourses.add(MiscCatalog.getCatalogCourse("CHEM 06101"));

        return nonProgramCourses;
    }

    public static Set<Course> getRowanExpCourses() {
        // "Rowan Experience" courses
        Set<Course> rowanExpCourses = new LinkedHashSet<>();
        rowanExpCourses.add(MiscCatalog.getCatalogCourse("GERM 03100"));
        rowanExpCourses.add(PhysicsCatalog.getCatalogCourse("PHYS 00130"));
        rowanExpCourses.add(MiscCatalog.getCatalogCourse("PHIL 09261"));

        return rowanExpCourses;
    }

    public static Set<Course> getRowanCoreCourses() {
        // Standard university core requirements
        Set<Course> rowanCoreCourses = new LinkedHashSet<>();
        rowanCoreCourses.add(MiscCatalog.getCatalogCourse("PHIL 09261"));
        rowanCoreCourses.addAll(MiscCatalog.getStandardRowanCoreCourses());
        return rowanCoreCourses;
    }

    public static Set<Course> getElectiveCourses() {
        // Physics electives often pull from Math and CS upper-level courses
        Set<Course> electiveCourses = new LinkedHashSet<>();
        electiveCourses.addAll(MathCatalog.getElectiveCourses());
        electiveCourses.addAll(CompSciCatalog.getElectiveCourses());
        return electiveCourses;
    }

    public static Set<Course> getOptionalCourses() {
        // Courses that may be taken but aren't strictly required
        Set<Course> optionalCourses = new LinkedHashSet<>();
        optionalCourses.add(new Course("PHYS", "00130", "Building Momentum as a Physics Student", 1, "R"));
        return optionalCourses;
    }

    public static boolean isCatalogCourse(String courseName) {
        // Basic filter: only check courses starting with "PHYS "
        if (!courseName.contains("PHYS ")) {
            return false;
        }
        // Delegate to parent helper using the full Physics course list
        return isCatalogCourse(courseName, "PHYS ", getAllCourses());
    }

    public static Course getCatalogCourse(String elective) {
        // Retrieve a course object from the full list by string ID
        return getCatalogCourse(elective, getAllCourses());
    }
}
