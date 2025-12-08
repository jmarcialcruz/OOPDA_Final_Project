import java.util.Set;
import java.util.List;
import java.util.LinkedHashSet;
import java.util.ArrayList;

public class PhysicsCatalog extends Catalog {
    private static List<Set<Course>> catalogSectionList = new ArrayList<>();
    private static final int numberOfSections = 4;

    public static int getNumberOfSections() {
        return numberOfSections;
    }

    public static List<Set<Course>> getCatalogSectionList() {
//        catalogSectionList.add(addRowanExpCourses());
//        catalogSectionList.add(addRowanCoreCourses());
//        catalogSectionList.add(addNonProgramCourses());
        catalogSectionList.add(getMajorRequiredCourses());
        return catalogSectionList;
    }

    public static Set<Course> getAllCourses() {
        Set<Course> allCourses = new LinkedHashSet<>();
        allCourses.addAll(getMajorRequiredCourses());
        allCourses.addAll(getElectiveCourses());
        return allCourses;
    }

    public static Set<Course> getMajorRequiredCourses() {
        Set<Course> majorRequiredCourses = new LinkedHashSet<>();
        majorRequiredCourses.add(new Course("PHYS", "00130", "Building Momentum as a Physics Student", 1, "R"));       
        majorRequiredCourses.add(new Course("PHYS", "00200", "Intro to Mechanics", 4, "R"));       
        majorRequiredCourses.add(new Course("PHYS", "00221", "Intro to Thermodynamics, Fluids, Waves, & Optics", 4, "R"));     
        majorRequiredCourses.add(new Course("PHYS", "00222", "Intro to Electricity & Magnetism", 4, "R"));     
        majorRequiredCourses.add(new Course("PHYS", "00300", "Modern Physics", 4, "R"));     
        majorRequiredCourses.add(new Course("PHYS", "00310", "Analytical Mechanics", 4, "R"));       
        majorRequiredCourses.add(new Course("PHYS", "00320", "Electricity & Magnetism I", 4, "R"));       
        majorRequiredCourses.add(new Course("PHYS", "00351", "Physics Research Methods I", 2, "R"));       
        majorRequiredCourses.add(new Course("PHYS", "00352", "Physics Research Methods II", 2, "R"));       
        majorRequiredCourses.add(new Course("PHYS", "00410", "Quantum Mechanics I", 4, "R"));       
        majorRequiredCourses.add(new Course("PHYS", "00430", "Statistical Physics", 3, "R"));       

        return majorRequiredCourses;
    }

    public static Set<Course> getElectiveCourses() {
        Set<Course> electiveCourses = new LinkedHashSet<>();
        electiveCourses.add(new Course("PHYS", "00000", "Place Holder", 4, "R"));     
        return electiveCourses;
    }

//    public static Set<Course> addNonProgramCourses();
//    public static Set<Course> addRowanExpCourses();
//    public static Set<Course> addRowanCoreCourses();

    public static boolean isCatalogCourse(String courseName) {
        return isCatalogCourse(courseName, "PHYS ", getAllCourses());
    }

    public static Course getCatalogCourse(String elective) {
        return getCatalogCourse(elective, getAllCourses());
    }
}
