

/*
 * Teacher Class
 * Inherits Person Class
 */

public class Teacher extends Person {

    private static final long serialVersionUID = 1L;

    private String subject;
    private String qualification;
    private double salary;
    private double attendance;

    // Constructor

    public Teacher(int id,
                   String name,
                   String phone,
                   String email,
                   String subject,
                   String qualification,
                   double salary) {

        super(id, name, phone, email);

        this.subject = subject;
        this.qualification = qualification;
        this.salary = salary;
        this.attendance = 0;
    }

    // Getters

    public String getSubject() {
        return subject;
    }

    public String getQualification() {
        return qualification;
    }

    public double getSalary() {
        return salary;
    }

    public double getAttendance() {
        return attendance;
    }

    // Setters

    public void setSubject(String subject) {

        if (subject != null && !subject.trim().isEmpty()) {
            this.subject = subject;
        }
    }

    public void setQualification(String qualification) {

        if (qualification != null &&
                !qualification.trim().isEmpty()) {

            this.qualification = qualification;
        }
    }

    public void setSalary(double salary) {

        if (salary > 0) {
            this.salary = salary;
        }
    }

    public void setAttendance(double attendance) {

        if (attendance >= 0 && attendance <= 100) {
            this.attendance = attendance;
        }
    }

    // Salary Operations

    public double calculateMonthlySalary() {
        return salary;
    }

    public double calculateYearlySalary() {
        return salary * 12;
    }

    // Display

    @Override
    public void display() {

        System.out.println("\n================================");
        System.out.println("        TEACHER DETAILS");
        System.out.println("================================");

        displayBasicInfo();

        System.out.println("Subject        : " + subject);
        System.out.println("Qualification  : " + qualification);
        System.out.println("Salary         : " + salary);
        System.out.println("Attendance     : " + attendance + "%");

        System.out.println("================================");
    }

    // Short Display

    public void displayShortInfo() {

        System.out.printf(
                "%-5d %-20s %-15s %-15s %-10.2f%n",
                id,
                name,
                subject,
                qualification,
                salary
        );
    }

    @Override
    public String toString() {

        return "Teacher{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", subject='" + subject + '\'' +
                ", qualification='" + qualification + '\'' +
                ", salary=" + salary +
                ", attendance=" + attendance +
                '}';
    }
}