package unsw.enrolment;

public class Grade {
    private int mark;
    private String grade;
    private CourseOffering offering;

    public Grade(CourseOffering offering, int mark, String grade) {
        this.offering = offering;
        this.mark = mark;
        this.grade = grade;
    }

    public int getMark() {
        return mark;
    }

    public String getGrade() {
        return grade;
    }

    public CourseOffering getOffering() {
        return offering;
    }

    boolean isPass() {
        return this.getMark() >= 50 && !isFail();
    }

    boolean isFail() {
        return this.getGrade().equals("FL") || this.getGrade().equals("UF");
    }
}
