import java.util.Objects;

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
        this.subject = catalogCourse.subject;
        this.id = catalogCourse.id;
        this.name = catalogCourse.name;
        this.credits = catalogCourse.credits;
        this.grade = catalogCourse.grade;
    }

    public void displaySelectionInfo() {
       System.out.print(getSubject() + " " + getId()); 
       System.out.print(" -------- (" + getCredits());
       System.out.print(") --------- " + getName());
    }

    public void displayFinishedInfo() {
        System.out.println("Course:  " + getSubject() + " " + getId()); 
        System.out.println("Name:    " + getName());
        System.out.println("Credits: " + getCredits());
        System.out.println("Grade:   " + getGrade());
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

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGrade(String grade) {
       this.grade = grade; 
    }

    public void setCredits() {
        this.credits = credits;
    }

    @Override 
    public boolean equals(Object obj) {
        // Referring to same object then return true
        if (this == obj) return true;

        // Checking if object is null or the object is not an instance of this class
        if (obj == null || obj.getClass() != this.getClass()) return false;

        Course course = (Course) obj;

        return (course.subject.equals(this.subject) && course.id.equals(this.id) && course.name.equals(this.name) && course.credits == this.credits);
    }

    @Override
    public int hashCode() {
        return Objects.hash(subject, id, name, credits);
    }
}

