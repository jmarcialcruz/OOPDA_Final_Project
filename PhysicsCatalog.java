import java.util.Set;
import java.util.LinkedHashSet;

public abstract class PhysicsCatalog implements Catalog {

    public static Set<Course> addMajorRequiredCourses() {
        Set<Course> majorRequiredCourses = new LinkedHashSet<>();
        majorRequiredCourses.add(new Course("PHYS", "00130", "Building Momentum as a Physics Student", 1, "R"));       
        majorRequiredCourses.addAll(addMinorRequiredCourses()); 
        majorRequiredCourses.add(new Course("PHYS", "00351", "Physics Research Methods I", 2, "R"));       
        majorRequiredCourses.add(new Course("PHYS", "00352", "Physics Research Methods II", 2, "R"));       
        majorRequiredCourses.add(new Course("PHYS", "00310", "Analytical Mechanics", 4, "R"));       
        majorRequiredCourses.add(new Course("PHYS", "00320", "Electricity & Magnetism I", 4, "R"));       
        majorRequiredCourses.add(new Course("PHYS", "00410", "Quantum Mechanics I", 4, "R"));       
        majorRequiredCourses.add(new Course("PHYS", "00430", "Statistical Physics", 3, "R"));       

        return majorRequiredCourses;
    }

    public static Set<Course> addMinorRequiredCourses() {
        Set<Course> minorRequiredCourses = new LinkedHashSet<>();
        minorRequiredCourses.add(new Course("PHYS", "00200", "Intro to Mechanics", 4, "R"));       
        minorRequiredCourses.add(new Course("PHYS", "00221", "Intro to Thermodynamics, Fluids, Waves, & Optics", 4, "R"));     
        minorRequiredCourses.add(new Course("PHYS", "00222", "Intro to Electricity & Magnetism", 4, "R"));     
        minorRequiredCourses.add(new Course("PHYS", "00300", "Modern Physics", 4, "R"));     
        return minorRequiredCourses;
    }

    public static Set<Course> addElectiveCourses() {
        Set<Course> electiveCourses = new LinkedHashSet<>();
        electiveCourses.add(new Course("PHYS", "00000", "Place Holder", 4, "R"));     
        return electiveCourses;
    }

    public static boolean checkCatalogForCourse(String courseName) {
        // Base case for checking string contents
        if (!courseName.substring(0,5).equals("PHYS ") && courseName.length() < 10) {
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
            String courseNumber = course.getSubject() + " " + course.getId();
            
            if (elective.equals(courseNumber)) {
                return course;
            }
        }

        return null;
    }

}
