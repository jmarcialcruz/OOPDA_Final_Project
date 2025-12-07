import java.util.Set;
import java.util.List;
import java.util.LinkedHashSet;
import java.util.ArrayList;

public abstract class CompSciCatalog implements Catalog {
    private static List<Set<Course>> catalogSectionList = new ArrayList<>();
    private static final int numberOfSections = 4;

    public static int getNumberOfSections() {
        return numberOfSections;
    }

    public static List<Set<Course>> getCatalogSectionList() {
        catalogSectionList.add(addRowanExpCourses());
        catalogSectionList.add(addRowanCoreCourses());
        catalogSectionList.add(addNonProgramCourses());
        catalogSectionList.add(addMajorRequiredCourses());
        return catalogSectionList;
    }

    public static Set<Course> addMajorRequiredCourses() {
        Set<Course> majorRequiredCourses = new LinkedHashSet<>();
        //majorRequiredCourses.addAll(addMinorRequiredCourses());     
        majorRequiredCourses.add(new Course("CS", "00100", "Computer Science Learning Community", 100, "R"));       
        majorRequiredCourses.add(new Course("CS", "07210", "Foundation of Computer Science ", 3, "R"));     
        majorRequiredCourses.add(new Course("CS", "04315", "Programming Languages", 30, "R"));     
        majorRequiredCourses.add(new Course("CS", "06395", "Operating Systems", 3, "R"));     
//        majorRequiredCourses.add(new Course("CS", "03351", "Cyber Security: Fundamentals", 3, "R"));     
//        majorRequiredCourses.add(new Course("CS", "04321", "Software Engineering I", 4, "R"));     
//        majorRequiredCourses.add(new Course("CS", "07340", "Design and Analysis of Algorithms ", 3, "R"));     
//        majorRequiredCourses.add(new Course("CS", "04400", "Senior Project", 3, "R"));     
//        majorRequiredCourses.add(StatsCatalog.getCatalogCourse("STAT 02290"));

        return majorRequiredCourses;
    }

    public static Set<Course> addMinorRequiredCourses() {
        Set<Course> minorRequiredCourses = new LinkedHashSet<>();
        minorRequiredCourses.add(new Course("CS", "04113", "Intro to Object Oriented Programming", 29, "R"));       
        minorRequiredCourses.add(new Course("CS", "04114", "Object Oriented Programming and Data Abstraction", 33, "R"));     
        minorRequiredCourses.add(new Course("CS", "04215", "Computer Lab Techniques", 100, "R"));     
        minorRequiredCourses.add(new Course("CS", "04222", "Data Structures and Algorithms ", 4, "R"));     
        minorRequiredCourses.add(new Course("CS", "06205", "Computer Organization", 3, "R"));     

        return minorRequiredCourses;
    }

    public static Set<Course> addElectiveCourses() {
        Set<Course> electiveCourses = new LinkedHashSet<>();
        electiveCourses.add(new Course("CS", "04391", "Parallel and Concurrent Programming", 3, "R")); 

        return electiveCourses;
    }

    public static Set<Course> addNonProgramCourses(){
        Set<Course> nonProgramCourses = new LinkedHashSet<>();
        //nonProgramCourses.add(new Course("INTR", "01265", "Computer Science and Society", 3, "R")); 
        //nonProgramCourses.add(new Course("WA", "01302", "Technical Writing", 3, "R")); 
        nonProgramCourses.add(MathCatalog.getCatalogCourse("MATH 01130"));
        nonProgramCourses.add(PhysicsCatalog.getCatalogCourse("PHYS 00200"));
        nonProgramCourses.add(PhysicsCatalog.getCatalogCourse("PHYS 00222"));
        //nonProgramCourses.add(EceCatalog.getCatalogCourse("ECE 09243"));

        return nonProgramCourses;
    }

    public static Set<Course> addRowanExpCourses() {
        Set<Course> rowanExpCourses = new LinkedHashSet<>();
        //rowanExpCourses.add(new Course("GERM", "03100", "Masterpieces in German Literature", 3, "R"));
        rowanExpCourses.add(MathCatalog.getCatalogCourse("MATH 01131"));

        return rowanExpCourses;
    }

    public static Set<Course> addRowanCoreCourses() {
        Set<Course> rowanCoreCourses = new LinkedHashSet<>();
        //rowanCoreCourses.add(new Course("COMP", "01111", "College Composition I", 3, "R"));
        //rowanCoreCourses.add(new Course("COMP", "01112", "College Composition II", 3, "R"));
        //rowanCoreCourses.add(new Course("CMS", "04205", "Public Speaking", 3, "R"));
        rowanCoreCourses.add(MathCatalog.getCatalogCourse("MATH 01130"));
        //rowanCoreCourses.add(new Course("ECON", "04101", "Intro to Economics: Macroeconomic Perspective", 3, "R"));

        return rowanCoreCourses;
    }

    public static boolean checkCatalogForCourse(String courseName) {
        // Base case for checking string contents
        if (!courseName.substring(0,3).equals("CS ") && courseName.length() < 8) {
            return false;
        }

        for (Course course : addMajorRequiredCourses()) {
            if (courseName.equals(course.getSubject() + " " + course.getId())) {
                return true;
            }
        }
        return false;
    }

    public static Course getCatalogCourse(String elective) {
        for (Course course : addMajorRequiredCourses()) {
            String courseNumber = course.getSubject() + " " + course.getId();
            
            if (elective.equals(courseNumber)) {
                return course;
            }
        }

        return null;
    }
}
