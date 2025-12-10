import java.util.Set;
import java.util.LinkedHashSet;

public class MiscCatalog extends Catalog {
    public static Set<Course> getStandardRowanCoreCourses() {
        Set<Course> rowanCoreCourses = new LinkedHashSet<>();
        rowanCoreCourses.add(getCatalogCourse("COMP 01111"));
        rowanCoreCourses.add(getCatalogCourse("COMP 01112"));
        rowanCoreCourses.add(getCatalogCourse("CMS 04205"));
        rowanCoreCourses.add(getCatalogCourse("GEOG 16100"));
        rowanCoreCourses.add(getCatalogCourse("GEOG 16160"));
        rowanCoreCourses.add(MathCatalog.getCatalogCourse("MATH 01130"));
        rowanCoreCourses.add(PhysicsCatalog.getCatalogCourse("PHYS 00200"));

        return rowanCoreCourses;
    }

    public static Set<Course> getMiscCourses() {
        Set<Course> miscCourses = new LinkedHashSet<>();
        miscCourses.add(new Course("STAT", "02320", "Concepts in Statistical Data Analysis", 3, "R"));
        miscCourses.add(new Course("STAT", "02360", "Probability and Random Variables", 3, "R"));
        miscCourses.add(new Course("STAT", "02361", "Mathematical Statistics", 3, "R"));
        miscCourses.add(new Course("STAT", "02340", "Elements of Statistical Learning", 3, "R"));
        miscCourses.add(new Course("STAT", "02360", "Probability and Random Variables", 3, "R"));
        miscCourses.add(new Course("STAT", "02371", "Design of Experiments: Analysis of Variance", 3, "R"));
        miscCourses.add(new Course("STAT", "02286", "Probability and Statistics for ECE", 3, "R"));
        miscCourses.add(new Course("STAT", "02290", "Probability and Statistics Inference for Computing Systems", 3, "R"));

        miscCourses.add(new Course("ECON", "04101", "Intro to Economics: Macroeconomic Perspective", 3, "R"));
        miscCourses.add(new Course("PHIL", "09369", "Philosophy of Science", 3, "R"));
        miscCourses.add(new Course("PHIL", "09261", "Philosophical Perspective on Science", 3, "R"));

        miscCourses.add(new Course("COMP", "01111", "College Composition I", 3, "R"));
        miscCourses.add(new Course("COMP", "01112", "College Composition II", 3, "R"));

        miscCourses.add(new Course("GERM", "03100", "Masterpieces in German Literature", 3, "R"));

        miscCourses.add(new Course("CHEM", "06100", "Chemistry I", 4, "R"));
        miscCourses.add(new Course("CHEM", "06101", "Chemistry II", 4, "R"));

        miscCourses.add(new Course("GEOG", "16100", "Earth, People, and Environment", 3, "R"));
        miscCourses.add(new Course("GEOG", "16160", "Digital Earth", 3, "R"));

        miscCourses.add(new Course("INTR", "01266", "Computer Science and Society", 3, "R"));

        miscCourses.add(new Course("CMS", "04205", "Public Speaking", 3, "R"));

        miscCourses.add(new Course("WA", "01302", "Technical Writing", 3, "R"));

        return miscCourses;
    }

    public static boolean isCatalogCourse(String courseName) {
        boolean isInCatalog = false;
        String[] catalogSubjectNames = {"STAT","PHIL", "ECON", "COMP", "GERM", "INTR", "CMS", "WA", "CHEM", "GEOG"};

        for (String subjectName : catalogSubjectNames) {
            if (courseName.contains(subjectName)) {
                isInCatalog = isCatalogCourse(courseName, subjectName, getMiscCourses());

                if (isInCatalog == true) {
                    return true;
                }
            }
        }

        return false;
    }

    public static Course getCatalogCourse(String elective) {
        return getCatalogCourse(elective, getMiscCourses());
    }
}
