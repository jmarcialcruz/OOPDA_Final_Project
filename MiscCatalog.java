import java.util.Set;
import java.util.LinkedHashSet;

/**
 * Static catalog definition for General Education and Support courses.
 *
 * This class serves as a miscellaneous database for courses that do not belong
 * to the specific major catalogs (CS, Math, Physics). It includes Statistics,
 * Chemistry, Economics, Philosophy, Composition, and other university-wide
 * electives or requirements.
 *
 * @author  Jordi Marcial Cruz
 */

public class MiscCatalog extends Catalog {

    public static Set<Course> getStandardRowanCoreCourses() {
        Set<Course> rowanCoreCourses = new LinkedHashSet<>();
        
        // Add standard University Core requirements (Composition, Public Speaking, etc.)
        rowanCoreCourses.add(getCatalogCourse("COMP 01111"));
        rowanCoreCourses.add(getCatalogCourse("COMP 01112"));
        rowanCoreCourses.add(getCatalogCourse("CMS 04205"));
        rowanCoreCourses.add(getCatalogCourse("GEOG 16100"));
        rowanCoreCourses.add(getCatalogCourse("GEOG 16160"));
        
        // Add cross-listed core courses from other catalogs
        rowanCoreCourses.add(MathCatalog.getCatalogCourse("MATH 01130"));
        rowanCoreCourses.add(PhysicsCatalog.getCatalogCourse("PHYS 00200"));

        return rowanCoreCourses;
    }

    public static Set<Course> getMiscCourses() {
        Set<Course> miscCourses = new LinkedHashSet<>();
        
        // --- Statistics ---
        miscCourses.add(new Course("STAT", "02320", "Concepts in Statistical Data Analysis", 3, "R"));
        miscCourses.add(new Course("STAT", "02360", "Probability and Random Variables", 3, "R"));
        miscCourses.add(new Course("STAT", "02361", "Mathematical Statistics", 3, "R"));
        miscCourses.add(new Course("STAT", "02340", "Elements of Statistical Learning", 3, "R"));
        
        miscCourses.add(new Course("STAT", "02371", "Design of Experiments: Analysis of Variance", 3, "R"));
        miscCourses.add(new Course("STAT", "02286", "Probability and Statistics for ECE", 3, "R"));
        miscCourses.add(new Course("STAT", "02290", "Probability and Statistics Inference for Computing Systems", 3, "R"));

        // --- Humanities & Social Sciences ---
        miscCourses.add(new Course("ECON", "04101", "Intro to Economics: Macroeconomic Perspective", 3, "R"));
        miscCourses.add(new Course("PHIL", "09369", "Philosophy of Science", 3, "R"));
        miscCourses.add(new Course("PHIL", "09261", "Philosophical Perspective on Science", 3, "R"));

        // --- Composition ---
        miscCourses.add(new Course("COMP", "01111", "College Composition I", 3, "R"));
        miscCourses.add(new Course("COMP", "01112", "College Composition II", 3, "R"));

        // --- Foreign Language ---
        miscCourses.add(new Course("GERM", "03100", "Masterpieces in German Literature", 3, "R"));

        // --- Chemistry ---
        miscCourses.add(new Course("CHEM", "06100", "Chemistry I", 4, "R"));
        miscCourses.add(new Course("CHEM", "06101", "Chemistry II", 4, "R"));

        // --- Geography ---
        miscCourses.add(new Course("GEOG", "16100", "Earth, People, and Environment", 3, "R"));
        miscCourses.add(new Course("GEOG", "16160", "Digital Earth", 3, "R"));

        // --- Interdisciplinary ---
        miscCourses.add(new Course("INTR", "01266", "Computer Science and Society", 3, "R"));

        // --- Communication ---
        miscCourses.add(new Course("CMS", "04205", "Public Speaking", 3, "R"));

        // --- Writing ---
        miscCourses.add(new Course("WA", "01302", "Technical Writing", 3, "R"));

        return miscCourses;
    }

    public static boolean isCatalogCourse(String courseName) {
        boolean isInCatalog = false;
        // List of subject codes managed by this miscellaneous catalog
        String[] catalogSubjectNames = {"STAT","PHIL", "ECON", "COMP", "GERM", "INTR", "CMS", "WA", "CHEM", "GEOG"};

        // Iterate through subjects to see if the requested course belongs here
        for (String subjectName : catalogSubjectNames) {
            if (courseName.contains(subjectName)) {
                // Delegate to the parent helper if the subject matches
                isInCatalog = isCatalogCourse(courseName, subjectName, getMiscCourses());

                if (isInCatalog == true) {
                    return true;
                }
            }
        }

        return false;
    }

    public static void displayAllElectiveCourses(Set<Course> requiredCoursework, Set<Course> completedCoursework) {
        Set<Course> electives = new LinkedHashSet<>();
        
        // Aggregate courses from ALL catalogs to form a master list
        electives.addAll(CompSciCatalog.getAllCourses());
        electives.addAll(MathCatalog.getAllCourses());
        electives.addAll(PhysicsCatalog.getAllCourses());
        electives.addAll(MiscCatalog.getMiscCourses());
        
        // Remove courses already taken or strictly required by the major
        electives.removeAll(requiredCoursework);
        electives.removeAll(completedCoursework);

        // Display the remaining available courses as potential electives
        for (Course course : electives) {
            course.displayQuickInfo();
        }
        System.out.println();
    }

    public static Course getCatalogCourse(String elective) {
        return getCatalogCourse(elective, getMiscCourses());
    }
}
