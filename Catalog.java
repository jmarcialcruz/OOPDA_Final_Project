import java.util.Set;
import java.util.List;
import java.util.LinkedHashSet;
import java.util.ArrayList;

/**
 * Abstract class for  managing collections of courses.
 *
 * This class provides static helper methods to search for courses within a
 * provided set (the catalog) based on string identifiers (e.g., "CS 04114").
 *
 * @author  Jordi Marcial Cruz
 */

public abstract class Catalog {

    // Checks if a specific course string exists within a given set of courses.
    protected static boolean isCatalogCourse(String courseName, String courseSubject, Set<Course> coursework) {
        int length = courseSubject.length();

        if (courseName.length() < length) {
            return false;
        }

        // Check if courseName starts with the Subject and has enough length for an ID
        if (!courseName.substring(0, length).equals(courseSubject) && courseName.length() < length + 5) {
            return false;
        }

        // Iterate through the set to find a matching Subject + ID combo
        for (Course course : coursework) {
            if (courseName.equals(course.getSubject() + " " + course.getId())) {
                return true;
            }
        }
        return false;
    }

    // Retrieves a specific Course object from a set based on its string identifier.
    protected static Course getCatalogCourse(String elective, Set<Course> coursework) {
        // Iterate through the coursework to find the matching course object
        for (Course course : coursework) {
            String courseNumber = course.getSubject() + " " + course.getId();

            // Compare the constructed string against the requested elective string
            if (elective.equals(courseNumber)) {
                return course;
            }
        }
        return null;
    }
}
