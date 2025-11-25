import java.util.Objects;

public class Course {
    private String subject;
    private String id;
    private String title;
    private String grade;
    private int credits;
    
    Course (String subject, String id, String title, int credits, String grade) {
        this.subject = subject;
        this.id = id;
        this.title = title;
        this.grade = grade;
        this.credits = credits;
    }

    Course (Course catalogCourse) {
        this.subject = catalogCourse.subject;
        this.id = catalogCourse.id;
        this.title = catalogCourse.title;
        this.credits = catalogCourse.credits;
        this.grade = catalogCourse.grade;
    }

    public void displaySelectionInfo() {
       System.out.print(getSubject() + " " + getId()); 
       System.out.print(" -------- (" + getCredits());
       System.out.print(") --------- " + getTitle());
    }

    public void displayFinishedInfo() {
        System.out.println("Course:  " + getSubject() + " " + getId()); 
        System.out.println("Title:   " + getTitle());
        System.out.println("Credits: " + getCredits());
        System.out.println("Grade:   " + getGrade());
    }

    public String getSubject() {
        return this.subject;
    }

    public String getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
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

    public void setTitle(String title) {
        this.title = title;
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

        return (course.subject.equals(this.subject) && course.id.equals(this.id) && course.title.equals(this.title) && course.credits == this.credits);
    }

    @Override
    public int hashCode() {
        return Objects.hash(subject, id, title, credits);
    }
}

