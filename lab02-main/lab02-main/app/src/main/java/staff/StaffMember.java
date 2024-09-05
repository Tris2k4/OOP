package staff;

import java.time.LocalDate;

/**
 * A staff member
 * @author Robert Clifton-Everest
 *
 */
public class StaffMember {
    // add your code here
    private String name;
    private double salary;
    private LocalDate hireDate;
    private LocalDate endDate;

    /**
     * Constructs a new StaffMember with the specified details.
     *
     * @param name     the name of the staff member
     * @param salary   the salary of the staff member
     * @param hireDate the hire date of the staff member
     * @param endDate  the end date of the staff member
     */

    public StaffMember(String name, double salary, LocalDate hireDate, LocalDate endDate) {
        this.name = name;
        this.salary = salary;
        this.hireDate = hireDate;
        this.endDate = endDate;
    }

    /**
     * Returns the name of the staff member.
     *
     * @return the name of the staff member
     */

    public String getName() {
        return name;
    }

    /**
     * Returns the salary of the staff member.
     *
     * @return the salary of the staff member
     */

    public double getSalary() {
        return salary;
    }

    /**
     * Returns the hire date of the staff member.
     *
     * @return the hire date of the staff member
     */

    public LocalDate getHireDate() {
        return hireDate;
    }

    /**
     * Returns the end date of the staff member.
     *
     * @return the end date of the staff member
     */

    public LocalDate getEndDate() {
        return endDate;
    }

    /**
     * Sets the name of the staff member.
     *
     * @param name the new name of the staff member
     */

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the salary of the staff member.
     *
     * @param salary the new salary of the staff member
     */

    public void setSalary(double salary) {
        this.salary = salary;
    }

    /**
     * Sets the hire date of the staff member.
     *
     * @param hireDate the new hire date of the staff member
     */

    public void setHireDate(LocalDate hireDate) {
        this.hireDate = hireDate;
    }

    /**
     * Sets the end date of the staff member.
     *
     * @param endDate the new end date of the staff member
     */

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "StaffMember [name=" + name + ", salary=" + salary + ", hireDate=" + hireDate + ", endDate=" + endDate
                + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }
        StaffMember s = (StaffMember) obj;
        if ((s.getHireDate() == null && this.getHireDate() == null)
                || (s.getEndDate() == null && this.getEndDate() == null)) {
            return true;
        }
        return (s.name == this.name && s.salary == this.salary && hireDate.equals(s.getHireDate())
                && endDate.equals(s.getEndDate()));
    }
}
