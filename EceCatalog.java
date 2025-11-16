import java.util.ArrayList;

public class EceCatalog extends Catalog {
    EceCatalog() {
        courseCatalog = new ArrayList<>();
        addAllCourses();
    }

    protected void addAllCourses() {
        courseCatalog.addAll(addMajorRequiredCourses());
   }

    protected ArrayList<Course> addMajorRequiredCourses() {
        ArrayList<Course> majorRequiredCourses = new ArrayList<>();
        majorRequiredCourses.add(new Course("ECE", "09000", "Intro to Electrical and Computer Engineering", 2));     
        majorRequiredCourses.add(new Course("ECE", "09000", "Intro to Digital Systems", 2));     
        majorRequiredCourses.add(new Course("ECE", "09000", "Computer Architecture", 4));     
        majorRequiredCourses.add(new Course("ECE", "09000", "Intro to Embedded Systems", 4));     
        majorRequiredCourses.add(new Course("ECE", "09000", "Principles of Circuit Analysis", 4));     
        majorRequiredCourses.add(new Course("ECE", "09000", "Engineering Electromagnetics", 4));     

        return majorRequiredCourses;
    }

}
