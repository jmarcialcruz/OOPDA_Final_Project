import java.util.Set;
import java.util.List;
import java.util.LinkedHashSet;
import java.util.ArrayList;

public abstract class Catalog {
    protected static boolean isCatalogCourse(String courseName, String courseSubject, Set<Course> coursework) {
        // Base case for checking string contents
        int length  = courseSubject.length();

        if (!courseName.substring(0, length).equals(courseSubject) && courseName.length() < length + 5) {
            return false;
        }

        for (Course course : coursework) {
            if (courseName.equals(course.getSubject() + " " + course.getId())) {
                return true;
            }
        }
        return false;
    }

    protected static Course getCatalogCourse(String elective, Set<Course> coursework) {
        for (Course course : coursework) {
            String courseNumber = course.getSubject() + " " + course.getId();

            if (elective.equals(courseNumber)) {
                return course;
            }
        }

        return null;
    }
}
