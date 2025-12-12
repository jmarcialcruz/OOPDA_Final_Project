import java.util.Objects;

/**
 * Represents an academic course within the catalog or a student's record.
 *
 * This class stores core details such as the subject, ID, name, grade,
 * and credit value. It provides methods for formatting and displaying
 * course information in various styles.
 *
 * @author  Jordi Marcial Cruz
 */

public class Course {

    private String subject;
    private String id;
    private String name;
    private String grade;
    private int credits;

    Course (String subject, String id, String name, int credits, String grade) {
        this.subject = subject;
        this.id = id;
        this.name = name;
        this.grade = grade;
        this.credits = credits;
    }

    Course (Course catalogCourse) {
        // Copy constructor: creates a new object using data from an existing course
        this.subject = catalogCourse.subject;
        this.id = catalogCourse.id;
        this.name = catalogCourse.name;
        this.credits = catalogCourse.credits;
        this.grade = catalogCourse.grade;
    }

    private void drawDashedLines(int length1, int length2) {
        String dashedLine = " ";
        // Calculate the gap size needed for alignment
        int dashedLineLength = length1 - length2;

        // Append hyphens to fill the gap
        for (int i = 0; i < dashedLineLength; i++) {
            dashedLine = dashedLine.concat("-");
        }

        dashedLine = dashedLine.concat(" ");
        System.out.print(dashedLine);
    }

    public void displaySelectionInfo() {
        // Print Subject + ID (e.g., CS 04114)
        System.out.print(getSubject() + " " + getId());
        
        // Dynamically pad with dashes based on subject length
        drawDashedLines(13, getSubject().length());
        
        // Print Grade and pad
        System.out.print(getGrade());
        drawDashedLines(13, getGrade().length());
        
        // Print credits and Name
        System.out.print("(" + getCredits());
        System.out.print(") ------- " + getName());
    }

    public void displayFinishedInfo() {
        // Print vertical list for final transcript view
        System.out.println("Course:  " + getSubject() + " " + getId());
        System.out.println("Name:    " + getName());
        System.out.println("Credits: " + getCredits());
        System.out.println("Grade:   " + getGrade());
    }

    public void displayQuickInfo() {
        String str = getSubject() + " " + getId();
        
        // Calculate padding to align text to 10 characters
        int length = 10 - str.length();
        for (int i = 0; i < length; i++) {
            str = str.concat(" ");
        }
        
        // Append credits and name, then print in Cyan
        str = str.concat(" (" + getCredits() + ") " + getName());
        ColoredOutput.colorCyan(str + "\n");
    }

    public String getSubject() {
        return this.subject;
    }

    public String getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getGrade() {
        return this.grade;
    }

    public int getCredits() {
        return this.credits;
    }

    protected void setSubject(String subject) {
        this.subject = subject;
    }

    protected void setId(String id) {
        this.id = id;
    }

    protected void setName(String name) {
        this.name = name;
    }

    protected void setGrade(String grade) {
        this.grade = grade;
    }

    protected void setCredits(int credits) {
        this.credits = credits;
    }

    @Override
    public boolean equals(Object obj) {
        // Return true if referring to the exact same memory location
        if (this == obj) return true;

        // Return false if object is null or a different class type
        if (obj == null || obj.getClass() != this.getClass()) return false;

        // Cast to Course to compare specific data fields
        Course course = (Course) obj;

        // Objects are equal if Subject, ID, Name, and Credits all match
        return (course.subject.equals(this.subject) && 
                course.id.equals(this.id) && 
                course.name.equals(this.name) && 
                course.credits == this.credits);
    }

    @Override
    public int hashCode() {
        // Generate hash based on core fields to support HashMaps/Sets
        return Objects.hash(subject, id, name, credits);
    }
}
