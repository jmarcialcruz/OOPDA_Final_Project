import java.util.Set;
import java.util.LinkedHashSet;

public abstract class PhysicsCatalog implements Catalog {

    public static LinkedHashSet<Course> addMajorRequiredCourses() {
        LinkedHashSet<Course> majorRequiredCourses = new LinkedHashSet<>();
        majorRequiredCourses.addAll(addMinorRequiredCourses()); 

        return majorRequiredCourses;
    }

    public static LinkedHashSet<Course> addMinorRequiredCourses() {
        LinkedHashSet<Course> minorRequiredCourses = new LinkedHashSet<>();
        minorRequiredCourses.add(new Course("PHYS", "00200", "Intro to Mechanics", 4, "R"));       
        minorRequiredCourses.add(new Course("PHYS", "00222", "Intro to Electricity and Magnetism", 4, "R"));     
        minorRequiredCourses.add(new Course("PHYS", "00221", "Intro to Thermodynamics", 4, "R"));     
        minorRequiredCourses.add(new Course("PHYS", "00300", "Modern Physics", 4, "R"));     
        return minorRequiredCourses;
    }

    public static LinkedHashSet<Course> addElectiveCourses() {
        LinkedHashSet<Course> electiveCourses = new LinkedHashSet<>();
        electiveCourses.add(new Course("PHYS", "00000", "Place Holder", 4, "R"));     
        return electiveCourses;
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
