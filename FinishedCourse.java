public class FinishedCourse extends Course {
    private String grade;

    FinishedCourse (String subject, String id, String title, int credits, String grade) {
        super(subject, id, title, credits);
        this.grade = grade;
    }

    @Override
    public void displayInfo() {
        System.out.println("Course:  " + getSubject() + " " + getId()); 
        System.out.println("Title:   " + getTitle());
        System.out.println("Credits: " + getCredits());
        System.out.println("Grade:   " + getGrade());
    }

    public String getGrade() {
        return this.grade;
    }

    public void setGrade(String grade) {
       this.grade = grade; 
    }
}

