import java.util.ArrayList;

public class PhysicsCatalog extends Catalog {
    PhysicsCatalog() {
        super();
        addAllCourses();
    }

    public ArrayList<Course> addMajorRequiredCourses() {
        ArrayList<Course> majorRequiredCourses = new ArrayList<>();
        majorRequiredCourses.addAll(addMinorRequiredCourses());     

        return majorRequiredCourses;
    }

    public ArrayList<Course> addMinorRequiredCourses() {
        ArrayList<Course> minorRequiredCourses = new ArrayList<>();
        minorRequiredCourses.add(new Course("PHYS", "00200", "Intro to Mechanics", 4));       
        minorRequiredCourses.add(new Course("PHYS", "00222", "Intro to Electricity and Magnetism", 4));     
        minorRequiredCourses.add(new Course("PHYS", "00221", "Intro to Thermodynamics", 4));     
        minorRequiredCourses.add(new Course("PHYS", "00300", "Modern Physics", 4));     
        return minorRequiredCourses;
    }

    public ArrayList<Course> addElectiveCourses() {
        ArrayList<Course> electiveCourses = new ArrayList<>();
        electiveCourses.add(new Course("PHYS", "00000", "Place Holder", 4));     
        return electiveCourses;
    }


}
