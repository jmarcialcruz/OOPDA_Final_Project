import java.util.Set;
import java.util.LinkedHashSet;

public abstract class StatsCatalog implements Catalog {

    public static Set<Course> addMinorRequiredCourses() {
        Set<Course> minorRequiredCourses = new LinkedHashSet<>();
        minorRequiredCourses.add(new Course("STAT", "02360", "Probability and Random Variables", 3, "R"));     
        minorRequiredCourses.add(new Course("STAT", "02361", "Mathematical Statistics", 3, "R"));     
        minorRequiredCourses.add(new Course("STAT", "02340", "Elements of Statistical Learning", 3, "R"));     
        minorRequiredCourses.add(new Course("STAT", "02371", "Design of Experiments: Analysis of Variance", 3, "R"));     
        minorRequiredCourses.add(new Course("STAT", "02286", "Probability and Statistics for ECE", 3, "R"));     
        minorRequiredCourses.add(new Course("STAT", "02290", "Probability and Statistics Inference for Computing Systems", 3, "R"));     

        return minorRequiredCourses;
    }
    
    public static boolean checkCatalogForCourse(String courseName) {
        for (Course course : addMinorRequiredCourses()) {
            if (courseName.equals(course.getSubject() + " " + course.getId())) {
                return true;
            }
        }
        return false;
    }

    public static Course getCatalogCourse(String elective) {
        Set<Course> lookupCourse = new LinkedHashSet<>();
        lookupCourse.addAll(addMinorRequiredCourses());

        for (Course course : lookupCourse) {
            String courseTitle = course.getSubject() + " " + course.getId();
            
            if (elective.equals(courseTitle)) {
                return course;
            }
        }

        return null;
    }
}
