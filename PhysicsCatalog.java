import java.util.ArrayList;

public class PhysicsCatalog extends Catalog {
    PhysicsCatalog() {
        courseCatalog = new ArrayList<>();
        addAllCourses();
    }

    protected void addAllCourses() {
        courseCatalog.addAll(addMajorRequiredCourses());
    }

    protected ArrayList<Course> addMajorRequiredCourses() {
        ArrayList<Course> majorRequiredCourses = new ArrayList<>();
        majorRequiredCourses.add(new Course("PHYS", "00200", "Intro to Mechanics", 4));       
        majorRequiredCourses.add(new Course("PHYS", "00222", "Intro to Electricity and Magnetism", 4));     
        majorRequiredCourses.add(new Course("PHYS", "00000", " ", 4));     

        return majorRequiredCourses;
    }

}
