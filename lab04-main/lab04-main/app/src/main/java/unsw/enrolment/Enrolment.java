package unsw.enrolment;

public class Enrolment {
    private CourseOffering offering;
    private Grade grade;
    private Student student;

    public Enrolment(CourseOffering offering, Student student) {
        this.offering = offering;
        this.student = student;
    }

    public Student getStudent() {
        return student;
    }

    public CourseOffering getOffering() {
        return offering;
    }

    public boolean hasPassedCourse() {
        if (grade == null) {
            return false;
        }

        return grade.isPass() && !grade.isFail();
    }

    public Course getCourse() {
        return offering.getCourse();
    }

    public String getTerm() {
        return offering.getTerm();
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    public boolean enrolCheck(Course preqCourse) {
        if (getCourse().equals(preqCourse) && getGrade() != null) {
            if (hasPassedCourse()) {
                return true;
            }
        }
        return false;
    }
}
