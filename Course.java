public class Course implements UserFeedback {
    protected String subject;
    protected String id;
    protected String title;
    protected int credits;

    Course (String subject, String id, String title, int credits) {
        this.subject = subject;
        this.id = id;
        this.title = title;
        this.credits = credits;
    }

    public void displayInfo() {
       System.out.print(getSubject() + " " + getId()); 
       System.out.print(" -------- (" + getCredits());
       System.out.print(") --------- " + getTitle());
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

    public void setCredits() {
        this.credits = credits;
    }

}

