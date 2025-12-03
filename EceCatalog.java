import java.util.Set;
import java.util.LinkedHashSet;

public abstract class EceCatalog implements Catalog {
    public static Set<Course> addMajorRequiredCourses() {
        Set<Course> majorRequiredCourses = new LinkedHashSet<>();
        majorRequiredCourses.addAll(addMinorRequiredCourses()); 
        majorRequiredCourses.add(new Course("ECE", "09243", "Computer Architecture", 3, "R"));     
        majorRequiredCourses.add(new Course("ECE", "09342", "Intro to Embedded Systems", 3, "R"));     
        majorRequiredCourses.add(new Course("ECE", "09303", "Engineering Electromagnetics", 3, "R"));     
        majorRequiredCourses.add(new Course("ECE", "09341", "Signals and Systems", 2, "R"));     
        majorRequiredCourses.add(new Course("ECE", "09321", "Systems and Control I", 3, "R"));     
        majorRequiredCourses.add(new Course("ECE", "09351", "Digital Signal Processing", 3, "R"));     
        majorRequiredCourses.add(new Course("ECE", "09433", "Electrical Communication Systems", 3, "R"));     
        majorRequiredCourses.add(new Course("ECE", "09414", "VLSI Design", 3, "R"));     

        return majorRequiredCourses;
    }

    public static Set<Course> addMinorRequiredCourses() {
        Set<Course> minorRequiredCourses = new LinkedHashSet<>();
        minorRequiredCourses.add(new Course("ECE", "09101", "Intro to Electrical and Computer Engineering", 2, "R"));     
        minorRequiredCourses.add(new Course("ECE", "09241", "Intro to Digital Systems", 2, "R"));     
        minorRequiredCourses.add(new Course("ECE", "09203", "Principles of Circuit Analysis", 4, "R"));     
        minorRequiredCourses.add(new Course("ECE", "09311", "Electronics I", 3, "R"));     

        return minorRequiredCourses;
    }

    public static Set<Course> addElectiveCourses() {
        Set<Course> electiveCourses = new LinkedHashSet<>();
        electiveCourses.add(new Course("ECE", "09456", "Embedded Software Design", 3, "R"));     
        return electiveCourses;
    }

    public static boolean checkCatalogForCourse(String courseName) {
        // Base case for checking string contents
        if (!courseName.substring(0,4).equals("ECE ") && courseName.length() < 9) {
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
        Set<Course> lookupCourse = new LinkedHashSet<>();
        lookupCourse.addAll(addMajorRequiredCourses());

        for (Course course : lookupCourse) {
            String courseTitle = course.getSubject() + " " + course.getId();
            
            if (elective.equals(courseTitle)) {
                return course;
            }
        }

        return null;
    }
}
