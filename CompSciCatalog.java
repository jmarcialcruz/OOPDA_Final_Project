import java.util.Set;
import java.util.List;
import java.util.LinkedHashSet;
import java.util.ArrayList;

public class CompSciCatalog extends Catalog {
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
        return allCourses;
    }

    public static Set<Course> getMajorRequiredCourses() {
        Set<Course> majorRequiredCourses = new LinkedHashSet<>();
        majorRequiredCourses.add(new Course("CS", "00100", "Computer Science Learning Community", 1, "R"));       
        majorRequiredCourses.add(MathCatalog.getCatalogCourse("MATH 03150"));
        majorRequiredCourses.add(MathCatalog.getCatalogCourse("MATH 01131"));
        majorRequiredCourses.add(MathCatalog.getCatalogCourse("MATH 01210"));
        majorRequiredCourses.add(MiscCatalog.getCatalogCourse("STAT 02290"));
        majorRequiredCourses.add(new Course("CS", "04113", "Intro to Object Oriented Programming", 3, "R"));       
        majorRequiredCourses.add(new Course("CS", "04114", "Object Oriented Programming and Data Abstraction", 4, "R"));     
        majorRequiredCourses.add(new Course("CS", "04215", "Computer Lab Techniques", 3, "R"));     
        majorRequiredCourses.add(new Course("CS", "04222", "Data Structures and Algorithms ", 4, "R"));     
        majorRequiredCourses.add(new Course("CS", "06205", "Computer Organization", 3, "R"));     
        majorRequiredCourses.add(new Course("CS", "07210", "Foundation of Computer Science ", 3, "R"));     
        majorRequiredCourses.add(new Course("CS", "04315", "Programming Languages", 3, "R"));     
        majorRequiredCourses.add(new Course("CS", "06395", "Operating Systems", 3, "R"));     
        majorRequiredCourses.add(new Course("CS", "03351", "Cyber Security: Fundamentals", 3, "R"));     
        majorRequiredCourses.add(new Course("CS", "04321", "Software Engineering I", 4, "R"));     
        majorRequiredCourses.add(new Course("CS", "07340", "Design and Analysis of Algorithms ", 3, "R"));     
        majorRequiredCourses.add(new Course("CS", "04400", "Senior Project", 3, "R"));     

        return majorRequiredCourses;
    }

    public static Set<Course> getNonProgramCourses(){
        Set<Course> nonProgramCourses = new LinkedHashSet<>();
        nonProgramCourses.add(MiscCatalog.getCatalogCourse("WA 01302"));
        nonProgramCourses.add(MiscCatalog.getCatalogCourse("INTR 01266"));
        nonProgramCourses.add(MathCatalog.getCatalogCourse("MATH 01130"));
        nonProgramCourses.add(PhysicsCatalog.getCatalogCourse("PHYS 00200"));
        nonProgramCourses.add(PhysicsCatalog.getCatalogCourse("PHYS 00221"));
        nonProgramCourses.add(PhysicsCatalog.getCatalogCourse("PHYS 00222"));

        return nonProgramCourses;
    }

    public static Set<Course> getRowanExpCourses() {
        Set<Course> rowanExpCourses = new LinkedHashSet<>();
        rowanExpCourses.add(MiscCatalog.getCatalogCourse("GERM 03100"));
        rowanExpCourses.add(CompSciCatalog.getCatalogCourse("CS 00100"));
        rowanExpCourses.add(MiscCatalog.getCatalogCourse("WA 01302"));

        return rowanExpCourses;
    }

    public static Set<Course> getRowanCoreCourses() {
        Set<Course> rowanCoreCourses = new LinkedHashSet<>();
        rowanCoreCourses.add(MiscCatalog.getCatalogCourse("INTR 01266"));
        rowanCoreCourses.addAll(MiscCatalog.getStandardRowanCoreCourses());
        return rowanCoreCourses;
    }

    public static Set<Course> getElectiveCourses() {
        Set<Course> electiveCourses = new LinkedHashSet<>();
        electiveCourses.add(new Course("CS", "01395", "Topics in Computer Science", 3, "R")); 
        electiveCourses.add(new Course("CS", "04380", "Object Oriented", 3, "R")); 
        electiveCourses.add(new Course("CS", "04391", "Parallel and Concurrent Programming", 3, "R")); 
        electiveCourses.add(new Course("CS", "04394", "Distributed Systems", 3, "R")); 
        electiveCourses.add(new Course("CS", "04401", "Compiler Design", 3, "R")); 
        electiveCourses.add(new Course("CS", "04430", "Database Systems", 3, "R")); 
        electiveCourses.add(new Course("CS", "04440", "Data Warehousing", 3, "R")); 
        electiveCourses.add(new Course("CS", "06410", "Data Communications and Networking", 3, "R")); 
        electiveCourses.add(new Course("CS", "07322", "Software Engineering II", 3, "R")); 
        electiveCourses.add(new Course("CS", "07350", "Computer Cryptography", 3, "R")); 
        electiveCourses.add(new Course("CS", "07422", "Theory of Computing", 3, "R")); 
        electiveCourses.add(new Course("CS", "07450", "Artificial Intelligence", 3, "R")); 
        electiveCourses.add(new Course("CS", "07460", "Computer Vision", 3, "R")); 
        electiveCourses.add(new Course("CS", "07470", "Machine Learning", 3, "R")); 

        return electiveCourses;
    }

    public static boolean isCatalogCourse(String courseName) {
        return isCatalogCourse(courseName, "CS ", getAllCourses());
    }

    public static Course getCatalogCourse(String elective) {
        return getCatalogCourse(elective, getAllCourses());
    }
}
