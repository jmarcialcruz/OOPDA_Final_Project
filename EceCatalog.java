import java.util.Set;
import java.util.LinkedHashSet;

public class EceCatalog extends Catalog {
    EceCatalog() {
        super();
        addAllCourses();
    }

    public LinkedHashSet<Course> addMajorRequiredCourses() {
        LinkedHashSet<Course> majorRequiredCourses = new LinkedHashSet<>();
        majorRequiredCourses.addAll(addMinorRequiredCourses());     
        majorRequiredCourses.add(new Course("ECE", "09243", "Computer Architecture", 3));     
        majorRequiredCourses.add(new Course("ECE", "09342", "Intro to Embedded Systems", 3));     
        majorRequiredCourses.add(new Course("ECE", "09303", "Engineering Electromagnetics", 3));     
        majorRequiredCourses.add(new Course("ECE", "09341", "Signals and Systems", 2));     
        majorRequiredCourses.add(new Course("ECE", "09321", "Systems and Control I", 3));     
        majorRequiredCourses.add(new Course("ECE", "09351", "Digital Signal Processing", 3));     
        majorRequiredCourses.add(new Course("ECE", "09433", "Electrical Communication Systems", 3));     
        majorRequiredCourses.add(new Course("ECE", "09414", "VLSI Design", 3));     

        return majorRequiredCourses;
    }

    public LinkedHashSet<Course> addMinorRequiredCourses() {
        LinkedHashSet<Course> minorRequiredCourses = new LinkedHashSet<>();
        minorRequiredCourses.add(new Course("ECE", "09101", "Intro to Electrical and Computer Engineering", 2));     
        minorRequiredCourses.add(new Course("ECE", "09241", "Intro to Digital Systems", 2));     
        minorRequiredCourses.add(new Course("ECE", "09203", "Principles of Circuit Analysis", 4));     
        minorRequiredCourses.add(new Course("ECE", "09311", "Electronics I", 3));     

        return minorRequiredCourses;
    }

    public LinkedHashSet<Course> addElectiveCourses() {
        LinkedHashSet<Course> electiveCourses = new LinkedHashSet<>();
        electiveCourses.add(new Course("ECE", "09456", "Embedded Software Design", 3));     
        return electiveCourses;
    }

}
