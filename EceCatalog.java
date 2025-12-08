import java.util.Set;
import java.util.List;
import java.util.LinkedHashSet;
import java.util.ArrayList;

public class EceCatalog extends Catalog {
    private static List<Set<Course>> catalogSectionList = new ArrayList<>();
    private static final int numberOfSections = 4;

    public static int getNumberOfSections() {
        return numberOfSections;
    }

    public static List<Set<Course>> getCatalogSectionList() {
//        catalogSectionList.add(getRowanExpCourses());
//        catalogSectionList.add(getRowanCoreCourses());
//        catalogSectionList.add(getNonProgramCourses());
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
        majorRequiredCourses.add(new Course("ECE", "09101", "Intro to Electrical and Computer Engineering", 2, "R"));     
        majorRequiredCourses.add(new Course("ECE", "09241", "Intro to Digital Systems", 2, "R")); 
        majorRequiredCourses.add(new Course("ECE", "09203", "Principles of Circuit Analysis", 4, "R")); 
        majorRequiredCourses.add(new Course("ECE", "09243", "Computer Architecture", 3, "R")); 
        majorRequiredCourses.add(new Course("ECE", "09311", "Electronics I", 3, "R")); 
        majorRequiredCourses.add(new Course("ECE", "09342", "Intro to Embedded Systems", 3, "R")); 
        majorRequiredCourses.add(new Course("ECE", "09303", "Engineering Electromagnetics", 3, "R"));     
        majorRequiredCourses.add(new Course("ECE", "09341", "Signals and Systems", 2, "R"));     
        majorRequiredCourses.add(new Course("ECE", "09321", "Systems and Control I", 3, "R"));     
        majorRequiredCourses.add(new Course("ECE", "09351", "Digital Signal Processing", 3, "R"));     
        majorRequiredCourses.add(new Course("ECE", "09433", "Electrical Communication Systems", 3, "R"));     
        majorRequiredCourses.add(new Course("ECE", "09414", "VLSI Design", 3, "R"));     

        majorRequiredCourses.add(MathCatalog.getCatalogCourse("MATH 01130"));
        majorRequiredCourses.add(MathCatalog.getCatalogCourse("MATH 01131"));
        majorRequiredCourses.add(MathCatalog.getCatalogCourse("MATH 01230"));
        majorRequiredCourses.add(PhysicsCatalog.getCatalogCourse("PHYS 00200"));
        majorRequiredCourses.add(MiscCatalog.getCatalogCourse("STAT 02286"));
        return majorRequiredCourses;
    }

    public static Set<Course> getElectiveCourses() {
        Set<Course> electiveCourses = new LinkedHashSet<>();
        electiveCourses.add(new Course("ECE", "09456", "Embedded Software Design", 3, "R"));     
        return electiveCourses;
    }

    public static boolean isCatalogCourse(String courseName) {
        return isCatalogCourse(courseName, "ECE ", getAllCourses());
    }

    public static Course getCatalogCourse(String elective) {
        return getCatalogCourse(elective, getAllCourses());
    }
}
