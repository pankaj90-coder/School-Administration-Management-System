

public class Student extends Person {

    private static final long serialVersionUID = 1L;

    private String className;
    private String section;
    private int rollNo;

    private double attendance;
    private double marks;

    private double totalFee;
    private double paidFee;

    public Student(int id,
                   String name,
                   String phone,
                   String email,
                   String className,
                   String section,
                   int rollNo) {

        super(id, name, phone, email);

        this.className = className;
        this.section = section;
        this.rollNo = rollNo;

        this.attendance = 0.0;
        this.marks = 0.0;

        this.totalFee = 50000.0;
        this.paidFee = 0.0;
    }

    // Getters

    public String getClassName() {
        return className;
    }

    public String getSection() {
        return section;
    }

    public int getRollNo() {
        return rollNo;
    }

    public double getAttendance() {
        return attendance;
    }

    public double getMarks() {
        return marks;
    }

    public double getTotalFee() {
        return totalFee;
    }

    public double getPaidFee() {
        return paidFee;
    }

    public double getRemainingFee() {
        return Math.max(0, totalFee - paidFee);
    }

    // Setters

    public void setClassName(String className) {

        if (className != null &&
                !className.trim().isEmpty()) {

            this.className = className;
        }
    }

    public void setSection(String section) {

        if (section != null &&
                !section.trim().isEmpty()) {

            this.section = section;
        }
    }

    public void setRollNo(int rollNo) {

        if (rollNo > 0) {

            this.rollNo = rollNo;
        }
    }

    public void setAttendance(double attendance) {

        if (attendance >= 0 &&
                attendance <= 100) {

            this.attendance = attendance;
        }
    }

    public void setMarks(double marks) {

        if (marks >= 0 &&
                marks <= 100) {

            this.marks = marks;
        }
    }

    public void setTotalFee(double totalFee) {

        if (totalFee > 0) {

            this.totalFee = totalFee;
        }
    }

    // Fee Operations

    public void payFee(double amount) {

        if (amount <= 0) {
            return;
        }

        paidFee += amount;

        if (paidFee > totalFee) {
            paidFee = totalFee;
        }
    }

    // Result Operations

    public double getPercentage() {
        return marks;
    }

    public String getGrade() {

        if (marks >= 90)
            return "A+";

        if (marks >= 75)
            return "A";

        if (marks >= 60)
            return "B";

        if (marks >= 40)
            return "C";

        return "FAIL";
    }

    public boolean isPassed() {
        return marks >= 40;
    }

    // Display

    @Override
    public void display() {

        System.out.println("\n================================");
        System.out.println("        STUDENT DETAILS");
        System.out.println("================================");

        displayBasicInfo();

        System.out.println("Class          : " + className);
        System.out.println("Section        : " + section);
        System.out.println("Roll Number    : " + rollNo);
        System.out.println("Attendance     : " + attendance + "%");
        System.out.println("Marks          : " + marks);
        System.out.println("Grade          : " + getGrade());
        System.out.println("Status         : "
                + (isPassed() ? "PASS" : "FAIL"));
        System.out.println("Total Fee      : " + totalFee);
        System.out.println("Paid Fee       : " + paidFee);
        System.out.println("Remaining Fee  : "
                + getRemainingFee());

        System.out.println("================================");
    }

    public void displayShortInfo() {

        System.out.printf(
                "%-5d %-20s %-10s %-10s %-10.2f %-10s%n",
                getId(),
                getName(),
                className,
                section,
                marks,
                getGrade()
        );
    }

    @Override
    public String toString() {

        return "Student{" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                ", className='" + className + '\'' +
                ", section='" + section + '\'' +
                ", rollNo=" + rollNo +
                ", attendance=" + attendance +
                ", marks=" + marks +
                ", paidFee=" + paidFee +
                ", remainingFee=" + getRemainingFee() +
                '}';
    }
}