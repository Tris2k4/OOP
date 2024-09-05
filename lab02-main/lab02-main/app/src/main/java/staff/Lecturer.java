package staff;

import java.time.LocalDate;

public class Lecturer extends StaffMember {
    private String school;
    private String academicStatus;

    /**
    * Constructs a new Lecturer with the specified details.
    *
    * @param name     the name of the staff member
    * @param salary   the salary of the staff member
    * @param hireDate the hire date of the staff member
    * @param endDate  the end date of the staff member
    * @param school   the school name
    * @param academicStatus    the academic status
    */

    public Lecturer(String name, double salary, LocalDate hireDate, LocalDate endDate, String school,
            String academicStatus) {
        super(name, salary, hireDate, endDate);
        this.academicStatus = academicStatus;
        this.school = school;
    }

    /**
     * Returns the name of the school.
     *
     * @return the name of the school
     */
    public String getSchool() {
        return school;
    }

    /**
     * Sets the name of the school.
     *
     * @param school the new name of the school
     */
    public void setSchool(String school) {
        this.school = school;
    }

    /**
     * Returns the academic status of the lectuer.
     *
     * @return the academic status of the lectuer
     */
    public String getAcademicStatus() {
        return academicStatus;
    }

    /**
     * Sets the academic status.
     *
     * @param academicStatus the new academic status
     */
    public void setAcademicStatus(String academicStatus) {
        this.academicStatus = academicStatus;
    }

    @Override
    public String toString() {
        return super.toString().replace("]", ", school: " + this.school + ", status: " + this.academicStatus + "]");
    }

    @Override
    public boolean equals(Object obj) {
        if (!(super.equals(obj))) {
            return false;
        }
        Lecturer l = (Lecturer) obj;
        return (super.equals(obj) && school.equals(l.getSchool()) && academicStatus == l.getAcademicStatus());
    }
}
