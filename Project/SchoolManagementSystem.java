import java.util.ArrayList;
import java.util.Scanner;

public class SchoolManagementSystem {

    static Scanner sc = new Scanner(System.in);

    static ArrayList<Student> students =
            FileManager.loadStudents();

    static ArrayList<Teacher> teachers =
            FileManager.loadTeachers();

    static ArrayList<Book> books =
            new ArrayList<>();

    static ArrayList<Subject> subjects =
            new ArrayList<>();

    static ArrayList<Notice> notices =
            new ArrayList<>();

    static ArrayList<Result> results =
            new ArrayList<>();

    static LoginManager loginManager =
            new LoginManager();

    // ==========================
    // MAIN METHOD
    // ==========================

    public static void main(String[] args) {

        System.out.println(
                "==================================");
        System.out.println(
                " SCHOOL ADMINISTRATION SYSTEM");
        System.out.println(
                "==================================");

        System.out.print("Username : ");
        String username = sc.next();

        System.out.print("Password : ");
        String password = sc.next();

        if (!loginManager.login(username, password)) {

            System.out.println("Invalid Login!");
            return;
        }

        AutoSaveThread autoSave =
                new AutoSaveThread(
                        students,
                        teachers);

        autoSave.setDaemon(true);
        autoSave.start();

        menu();
    }

    // ==========================
    // MAIN MENU
    // ==========================

    public static void menu() {

        while (true) {

            System.out.println(
                    "\n=========== MENU ===========");

            System.out.println("1. Student Management");
            System.out.println("2. Teacher Management");
            System.out.println("3. Subject Management");
            System.out.println("4. Library Management");
            System.out.println("5. Attendance Management");
            System.out.println("6. Examination & Results");
            System.out.println("7. Fee Management");
            System.out.println("8. Notice Board");
            System.out.println("9. Reports");
            System.out.println("0. Exit");

            System.out.print(
                    "Enter Choice : ");

            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    studentMenu();
                    break;

                case 2:
                    teacherMenu();
                    break;

                case 3:
                    subjectMenu();
                    break;

                case 4:
                    libraryMenu();
                    break;

                case 5:
                    attendanceMenu();
                    break;

                case 6:
                    resultMenu();
                    break;

                case 7:
                    feeMenu();
                    break;

                case 8:
                    noticeMenu();
                    break;

                case 9:
                    reportMenu();
                    break;

                case 0:

                    saveAllData();

                    System.out.println(
                            "\nThank You For Using");
                    System.out.println(
                            "School Administration System");

                    return;

                default:

                    System.out.println(
                            "Invalid Choice!");
            }
        }
    }

    // ==========================
    // STUDENT MENU
    // ==========================

    public static void studentMenu() {

        while (true) {

            System.out.println(
                    "\n===== STUDENT MENU =====");

            System.out.println("1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Search Student");
            System.out.println("4. Update Student");
            System.out.println("5. Delete Student");
            System.out.println("0. Back");

            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    addStudent();
                    break;

                case 2:
                    viewStudents();
                    break;

                case 3:
                    searchStudent();
                    break;

                case 4:
                    updateStudent();
                    break;

                case 5:
                    deleteStudent();
                    break;

                case 0:
                    return;

                default:
                    System.out.println(
                            "Invalid Choice");
            }
        }
    }

    // ==========================
    // ADD STUDENT
    // ==========================
static void addStudent() {

    System.out.print("How many students do you want to add? : ");
    int count = sc.nextInt();
    sc.nextLine();

    for (int i = 1; i <= count; i++) {

        System.out.println("\n========== Student " + i + " ==========");

        System.out.print("Enter Student ID : ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Name : ");
        String name = sc.nextLine();

        System.out.print("Enter Phone : ");
        String phone = sc.nextLine();

        System.out.print("Enter Email : ");
        String email = sc.nextLine();

        System.out.print("Enter Class : ");
        String className = sc.nextLine();

        System.out.print("Enter Section : ");
        String section = sc.nextLine();

        System.out.print("Enter Roll Number : ");
        int rollNo = sc.nextInt();
        sc.nextLine();

        Student student =
                new Student(
                        id,
                        name,
                        phone,
                        email,
                        className,
                        section,
                        rollNo
                );

        students.add(student);

        System.out.println("Student Added Successfully");
    }

    System.out.println("\n================================");
    System.out.println(count + " Student(s) Added Successfully");
    System.out.println("Total Students : " + students.size());
    System.out.println("================================");
}

    // ==========================
    // VIEW STUDENTS
    // ==========================

    public static void viewStudents() {

        if (students.isEmpty()) {

            System.out.println(
                    "No Students Found.");
            return;
        }

        for (Student student : students) {

            student.display();
        }
    }

    // ==========================
    // SEARCH STUDENT
    // ==========================

    public static void searchStudent() {

        System.out.print(
                "Enter Student ID : ");

        int id = sc.nextInt();

        Student student =
                findStudent(id);

        if (student != null) {

            student.display();

        } else {

            System.out.println(
                    "Student Not Found.");
        }
    }

    // ==========================
    // UPDATE STUDENT
    // ==========================

    public static void updateStudent() {

        System.out.print(
                "Enter Student ID : ");

        int id = sc.nextInt();

        Student student =
                findStudent(id);

        if (student == null) {

            System.out.println(
                    "Student Not Found.");
            return;
        }

        sc.nextLine();

        System.out.print("New Name : ");
        student.setName(sc.nextLine());

        System.out.print("New Phone : ");
        student.setPhone(sc.nextLine());

        System.out.print("New Email : ");
        student.setEmail(sc.nextLine());

        System.out.println(
                "Student Updated.");
    }

    // ==========================
    // DELETE STUDENT
    // ==========================

    public static void deleteStudent() {

        System.out.print(
                "Enter Student ID : ");

        int id = sc.nextInt();

        Student student =
                findStudent(id);

        if (student == null) {

            System.out.println(
                    "Student Not Found.");
            return;
        }

        students.remove(student);

        System.out.println(
                "Student Deleted.");
    }

    // ==========================
    // FIND STUDENT
    // ==========================

    public static Student findStudent(int id) {

        for (Student student : students) {

            if (student.getId() == id) {

                return student;
            }
        }

        return null;
    }
        // ==========================
    // TEACHER MENU
    // ==========================

    public static void teacherMenu() {

        while (true) {

            System.out.println("\n===== TEACHER MENU =====");

            System.out.println("1. Add Teacher");
            System.out.println("2. View Teachers");
            System.out.println("3. Search Teacher");
            System.out.println("4. Update Teacher");
            System.out.println("5. Delete Teacher");
            System.out.println("0. Back");

            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    addTeacher();
                    break;

                case 2:
                    viewTeachers();
                    break;

                case 3:
                    searchTeacher();
                    break;

                case 4:
                    updateTeacher();
                    break;

                case 5:
                    deleteTeacher();
                    break;

                case 0:
                    return;
            }
        }
    }

    public static void addTeacher() {

        System.out.print("ID : ");
        int id = sc.nextInt();

        sc.nextLine();

        System.out.print("Name : ");
        String name = sc.nextLine();

        System.out.print("Phone : ");
        String phone = sc.nextLine();

        System.out.print("Email : ");
        String email = sc.nextLine();

        System.out.print("Subject : ");
        String subject = sc.nextLine();

        System.out.print("Qualification : ");
        String qualification = sc.nextLine();

        System.out.print("Salary : ");
        double salary = sc.nextDouble();

        Teacher teacher = new Teacher(
                id,
                name,
                phone,
                email,
                subject,
                qualification,
                salary
        );

        teachers.add(teacher);

        System.out.println("Teacher Added.");
    }

    public static void viewTeachers() {

        if (teachers.isEmpty()) {

            System.out.println("No Teachers Found.");
            return;
        }

        for (Teacher teacher : teachers) {

            teacher.display();
        }
    }

    public static Teacher findTeacher(int id) {

        for (Teacher teacher : teachers) {

            if (teacher.getId() == id) {

                return teacher;
            }
        }

        return null;
    }

    public static void searchTeacher() {

        System.out.print("Teacher ID : ");

        int id = sc.nextInt();

        Teacher teacher = findTeacher(id);

        if (teacher != null) {

            teacher.display();

        } else {

            System.out.println("Teacher Not Found.");
        }
    }

    public static void updateTeacher() {

        System.out.print("Teacher ID : ");

        int id = sc.nextInt();

        Teacher teacher = findTeacher(id);

        if (teacher == null) {

            System.out.println("Teacher Not Found.");
            return;
        }

        sc.nextLine();

        System.out.print("New Name : ");
        teacher.setName(sc.nextLine());

        System.out.print("New Phone : ");
        teacher.setPhone(sc.nextLine());

        System.out.print("New Email : ");
        teacher.setEmail(sc.nextLine());

        System.out.println("Teacher Updated.");
    }

    public static void deleteTeacher() {

        System.out.print("Teacher ID : ");

        int id = sc.nextInt();

        Teacher teacher = findTeacher(id);

        if (teacher != null) {

            teachers.remove(teacher);

            System.out.println("Teacher Deleted.");
        }
    }

    // ==========================
    // SUBJECT MENU
    // ==========================

    public static void subjectMenu() {

        while (true) {

            System.out.println("\n===== SUBJECT MENU =====");

            System.out.println("1. Add Subject");
            System.out.println("2. View Subjects");
            System.out.println("3. Delete Subject");
            System.out.println("0. Back");

            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    addSubject();
                    break;

                case 2:
                    viewSubjects();
                    break;

                case 3:
                    deleteSubject();
                    break;

                case 0:
                    return;
            }
        }
    }

    public static void addSubject() {

        System.out.print("Subject ID : ");

        int id = sc.nextInt();

        sc.nextLine();

        System.out.print("Subject Name : ");

        String name = sc.nextLine();

        subjects.add(
                new Subject(id, name)
        );

        System.out.println("Subject Added.");
    }

    public static void viewSubjects() {

        if (subjects.isEmpty()) {

            System.out.println("No Subjects Found.");
            return;
        }

        for (Subject subject : subjects) {

            subject.display();
        }
    }

    public static void deleteSubject() {

        System.out.print("Subject ID : ");

        int id = sc.nextInt();

        Subject found = null;

        for (Subject subject : subjects) {

            if (subject.getSubjectId() == id) {

                found = subject;
                break;
            }
        }

        if (found != null) {

            subjects.remove(found);

            System.out.println("Subject Deleted.");
        }
    }

    // ==========================
    // LIBRARY MENU
    // ==========================

    public static void libraryMenu() {

        while (true) {

            System.out.println("\n===== LIBRARY MENU =====");

            System.out.println("1. Add Book");
            System.out.println("2. View Books");
            System.out.println("3. Issue Book");
            System.out.println("4. Return Book");
            System.out.println("5. Search Book");
            System.out.println("0. Back");

            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    addBook();
                    break;

                case 2:
                    viewBooks();
                    break;

                case 3:
                    issueBook();
                    break;

                case 4:
                    returnBook();
                    break;

                case 5:
                    searchBook();
                    break;

                case 0:
                    return;
            }
        }
    }

    public static void addBook() {

        System.out.print("Book ID : ");

        int id = sc.nextInt();

        sc.nextLine();

        System.out.print("Title : ");
        String title = sc.nextLine();

        System.out.print("Author : ");
        String author = sc.nextLine();

        books.add(
                new Book(id, title, author)
        );

        System.out.println("Book Added.");
    }

    public static void viewBooks() {

        if (books.isEmpty()) {

            System.out.println("No Books Found.");
            return;
        }

        for (Book book : books) {

            book.display();
        }
    }

    public static Book findBook(int id) {

        for (Book book : books) {

            if (book.getBookId() == id) {

                return book;
            }
        }

        return null;
    }

    public static void issueBook() {

        System.out.print("Book ID : ");

        int id = sc.nextInt();

        Book book = findBook(id);

        if (book != null) {

            book.issueBook();

            System.out.println("Book Issued.");
        }
    }

    public static void returnBook() {

        System.out.print("Book ID : ");

        int id = sc.nextInt();

        Book book = findBook(id);

        if (book != null) {

            book.returnBook();

            System.out.println("Book Returned.");
        }
    }

    public static void searchBook() {

        System.out.print("Book ID : ");

        int id = sc.nextInt();

        Book book = findBook(id);

        if (book != null) {

            book.display();

        } else {

            System.out.println("Book Not Found.");
        }
    }
        // ==========================
    // ATTENDANCE MENU
    // ==========================

    public static void attendanceMenu() {

        while (true) {

            System.out.println("\n===== ATTENDANCE MENU =====");

            System.out.println("1. Mark Student Attendance");
            System.out.println("2. View Student Attendance");
            System.out.println("0. Back");

            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    markStudentAttendance();
                    break;

                case 2:
                    viewStudentAttendance();
                    break;

                case 0:
                    return;
            }
        }
    }

    public static void markStudentAttendance() {

        System.out.print("Student ID : ");
        int id = sc.nextInt();

        Student student = findStudent(id);

        if (student == null) {

            System.out.println("Student Not Found.");
            return;
        }

        System.out.print("Attendance (%) : ");
        double attendance = sc.nextDouble();

        student.setAttendance(attendance);

        System.out.println("Attendance Updated.");
    }

    public static void viewStudentAttendance() {

        for (Student student : students) {

            System.out.println(
                    student.getId() +
                    " | " +
                    student.getName() +
                    " | Attendance : " +
                    student.getAttendance() + "%"
            );
        }
    }

    // ==========================
    // RESULT MENU
    // ==========================

    public static void resultMenu() {

        while (true) {

            System.out.println("\n===== RESULT MENU =====");

            System.out.println("1. Enter Marks");
            System.out.println("2. Generate Result");
            System.out.println("3. View Results");
            System.out.println("0. Back");

            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    enterMarks();
                    break;

                case 2:
                    generateResult();
                    break;

                case 3:
                    viewResults();
                    break;

                case 0:
                    return;
            }
        }
    }

    public static void enterMarks() {

        System.out.print("Student ID : ");
        int id = sc.nextInt();

        Student student = findStudent(id);

        if (student == null) {

            System.out.println("Student Not Found.");
            return;
        }

        System.out.print("Marks : ");
        double marks = sc.nextDouble();

        student.setMarks(marks);

        System.out.println("Marks Saved.");
    }

    public static void generateResult() {

        System.out.print("Student ID : ");
        int id = sc.nextInt();

        Student student = findStudent(id);

        if (student == null) {

            System.out.println("Student Not Found.");
            return;
        }

        Result result = new Result(
                student.getId(),
                student.getName(),
                student.getMarks(),
                student.getGrade()
        );

        results.add(result);

        System.out.println("Result Generated.");
    }

    public static void viewResults() {

        if (results.isEmpty()) {

            System.out.println("No Results Available.");
            return;
        }

        for (Result result : results) {

            result.display();
        }
    }

    // ==========================
    // FEE MENU
    // ==========================

    public static void feeMenu() {

        while (true) {

            System.out.println("\n===== FEE MENU =====");

            System.out.println("1. Deposit Fee");
            System.out.println("2. Fee Status");
            System.out.println("3. Pending Fees");
            System.out.println("0. Back");

            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    depositFee();
                    break;

                case 2:
                    feeStatus();
                    break;

                case 3:
                    pendingFees();
                    break;

                case 0:
                    return;
            }
        }
    }

    public static void depositFee() {

        System.out.print("Student ID : ");
        int id = sc.nextInt();

        Student student = findStudent(id);

        if (student == null) {

            System.out.println("Student Not Found.");
            return;
        }

        System.out.print("Amount : ");
        double amount = sc.nextDouble();

        student.payFee(amount);

        System.out.println("Fee Deposited.");
    }

    public static void feeStatus() {

        System.out.print("Student ID : ");
        int id = sc.nextInt();

        Student student = findStudent(id);

        if (student != null) {

            System.out.println(
                    "Remaining Fee : "
                    + student.getRemainingFee()
            );
        }
    }

    public static void pendingFees() {

        for (Student student : students) {

            if (student.getRemainingFee() > 0) {

                System.out.println(
                        student.getId() +
                        " | " +
                        student.getName() +
                        " | Due : " +
                        student.getRemainingFee()
                );
            }
        }
    }

    // ==========================
    // NOTICE MENU
    // ==========================

    public static void noticeMenu() {

        while (true) {

            System.out.println("\n===== NOTICE MENU =====");

            System.out.println("1. Add Notice");
            System.out.println("2. View Notices");
            System.out.println("3. Delete Notice");
            System.out.println("0. Back");

            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    addNotice();
                    break;

                case 2:
                    viewNotices();
                    break;

                case 3:
                    deleteNotice();
                    break;

                case 0:
                    return;
            }
        }
    }

    public static void addNotice() {

        sc.nextLine();

        System.out.print("Notice : ");
        String text = sc.nextLine();

        notices.add(new Notice(text));

        System.out.println("Notice Added.");
    }

    public static void viewNotices() {

        for (Notice notice : notices) {

            notice.display();
        }
    }

    public static void deleteNotice() {

        if (notices.isEmpty()) {

            System.out.println("No Notices.");
            return;
        }

        System.out.print("Notice Number : ");
        int index = sc.nextInt();

        if (index > 0 && index <= notices.size()) {

            notices.remove(index - 1);

            System.out.println("Notice Deleted.");
        }
    }

    // ==========================
    // REPORT MENU
    // ==========================

    public static void reportMenu() {

        System.out.println("\n===== SCHOOL REPORT =====");

        System.out.println("Students : " + students.size());
        System.out.println("Teachers : " + teachers.size());
        System.out.println("Subjects : " + subjects.size());
        System.out.println("Books    : " + books.size());
        System.out.println("Results  : " + results.size());
    }

    // ==========================
    // SAVE DATA
    // ==========================

    public static void saveAllData() {

        FileManager.saveStudents(students);
        FileManager.saveTeachers(teachers);

        System.out.println("Data Saved Successfully.");
    }

}