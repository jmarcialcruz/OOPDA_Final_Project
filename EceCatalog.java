import java.util.ArrayList;

public class EceCatalog extends Catalog {
    EceCatalog() {
        super();
        addAllCourses();
    }

    public ArrayList<Course> addMajorRequiredCourses() {
        ArrayList<Course> majorRequiredCourses = new ArrayList<>();
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

    public ArrayList<Course> addMinorRequiredCourses() {
        ArrayList<Course> minorRequiredCourses = new ArrayList<>();
        minorRequiredCourses.add(new Course("ECE", "09101", "Intro to Electrical and Computer Engineering", 2));     
        minorRequiredCourses.add(new Course("ECE", "09241", "Intro to Digital Systems", 2));     
        minorRequiredCourses.add(new Course("ECE", "09203", "Principles of Circuit Analysis", 4));     
        minorRequiredCourses.add(new Course("ECE", "09311", "Electronics I", 3));     

        return minorRequiredCourses;
    }

    public ArrayList<Course> addElectiveCourses() {
        ArrayList<Course> electiveCourses = new ArrayList<>();
        electiveCourses.add(new Course("ECE", "09456", "Embedded Software Design", 3));     
        return electiveCourses;
    }

}
