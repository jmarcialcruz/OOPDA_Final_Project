import java.util.Set;
import java.util.LinkedHashSet;

public class PhysicsCatalog extends Catalog {
    PhysicsCatalog() {
        super();
        addAllCourses();
    }

    public LinkedHashSet<Course> addMajorRequiredCourses() {
        LinkedHashSet<Course> majorRequiredCourses = new LinkedHashSet<>();
        majorRequiredCourses.addAll(addMinorRequiredCourses());     

        return majorRequiredCourses;
    }

    public LinkedHashSet<Course> addMinorRequiredCourses() {
        LinkedHashSet<Course> minorRequiredCourses = new LinkedHashSet<>();
        minorRequiredCourses.add(new Course("PHYS", "00200", "Intro to Mechanics", 4));       
        minorRequiredCourses.add(new Course("PHYS", "00222", "Intro to Electricity and Magnetism", 4));     
        minorRequiredCourses.add(new Course("PHYS", "00221", "Intro to Thermodynamics", 4));     
        minorRequiredCourses.add(new Course("PHYS", "00300", "Modern Physics", 4));     
        return minorRequiredCourses;
    }

    public LinkedHashSet<Course> addElectiveCourses() {
        LinkedHashSet<Course> electiveCourses = new LinkedHashSet<>();
        electiveCourses.add(new Course("PHYS", "00000", "Place Holder", 4));     
        return electiveCourses;
    }


}
