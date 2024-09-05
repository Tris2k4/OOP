package staff;

import java.time.LocalDate;

public class StaffTest {
    // Add your tests here
    public void printStaffDetails(StaffMember staff) {
        System.out.println(staff.toString());
    }

    public static void main(String[] args) {
        StaffMember staff = new StaffMember("Jack", 5000, LocalDate.of(2018, 12, 3), LocalDate.of(2019, 12, 3));
        Lecturer lecturer = new Lecturer("Thomas", 1000000000, LocalDate.of(2019, 12, 3), LocalDate.of(2020, 12, 3),
                "CSE", "A");
        StaffTest newStaff = new StaffTest();

        newStaff.printStaffDetails(staff);
        newStaff.printStaffDetails(lecturer);
        System.out.println(staff.equals(lecturer));

        Lecturer lecturer2 = new Lecturer("Thomas", 1000000000, LocalDate.of(2019, 12, 3), LocalDate.of(2020, 12, 3),
                "CSE", "A");

        System.out.println(lecturer.equals(lecturer2));

        lecturer2.setAcademicStatus("B");
        System.out.println(lecturer.equals(lecturer2));

        Lecturer lecturer3 = new Lecturer("Thomas", 1000000000, LocalDate.of(2019, 12, 3), null, "CSE", "A");
        Lecturer lecturer4 = new Lecturer("Thomas", 1000000000, LocalDate.of(2019, 12, 3), null, "CSE", "A");
        System.out.println(lecturer3.equals(lecturer4));

        Lecturer lecturer5 = new Lecturer("Thomas", 1000000000, null, null, "CSE", "A");
        Lecturer lecturer6 = new Lecturer("Thomas", 1000000000, null, null, "CSE", "A");
        System.out.println(lecturer5.equals(lecturer6));
    }
}
