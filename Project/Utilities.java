import java.io.*;
import java.util.ArrayList;

/* =========================================
   BOOK CLASS
========================================= */

class Book implements Serializable {

    private int bookId;
    private String title;
    private String author;
    private boolean issued;

    public Book(int bookId,
                String title,
                String author) {

        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.issued = false;
    }

    public int getBookId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }

    public boolean isIssued() {
        return issued;
    }

    public void issueBook() {
        issued = true;
    }

    public void returnBook() {
        issued = false;
    }

    public void display() {

        System.out.println(
                bookId + " | " +
                title + " | " +
                author + " | " +
                (issued ? "Issued" : "Available")
        );
    }
}

/* =========================================
   SUBJECT CLASS
========================================= */

class Subject implements Serializable {

    private int subjectId;
    private String subjectName;

    public Subject(int subjectId,
                   String subjectName) {

        this.subjectId = subjectId;
        this.subjectName = subjectName;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void display() {

        System.out.println(
                "ID : " + subjectId +
                " | Subject : " +
                subjectName
        );
    }
}

/* =========================================
   NOTICE CLASS
========================================= */

class Notice implements Serializable {

    private String message;

    public Notice(String message) {
        this.message = message;
    }

    public void display() {

        System.out.println(
                "NOTICE : " + message
        );
    }
}

/* =========================================
   RESULT CLASS
========================================= */

class Result implements Serializable {

    private int studentId;
    private String studentName;
    private double marks;
    private String grade;

    public Result(int studentId,
                  String studentName,
                  double marks,
                  String grade) {

        this.studentId = studentId;
        this.studentName = studentName;
        this.marks = marks;
        this.grade = grade;
    }

    public void display() {

        System.out.println(
                studentId +
                " | " +
                studentName +
                " | Marks : " +
                marks +
                " | Grade : " +
                grade
        );
    }
}

/* =========================================
   LOGIN MANAGER
========================================= */

class LoginManager {

    private final String USERNAME = "Pankaj";
    private final String PASSWORD = "Pankaj123";

    public boolean login(
            String username,
            String password) {

        return USERNAME.equals(username)
                &&
                PASSWORD.equals(password);
    }
}

/* =========================================
   FILE MANAGER
========================================= */

class FileManager {

    public static void saveStudents(
            ArrayList<Student> students) {

        try {

            ObjectOutputStream out =
                    new ObjectOutputStream(
                            new FileOutputStream(
                                    "students.dat"));

            out.writeObject(students);

            out.close();

        } catch (Exception e) {

            System.out.println(
                    "Student Save Error"
            );
        }
    }

    @SuppressWarnings("unchecked")
    public static ArrayList<Student>
    loadStudents() {

        try {

            ObjectInputStream in =
                    new ObjectInputStream(
                            new FileInputStream(
                                    "students.dat"));

            ArrayList<Student> students =
                    (ArrayList<Student>)
                            in.readObject();

            in.close();

            return students;

        } catch (Exception e) {

            return new ArrayList<>();
        }
    }

    public static void saveTeachers(
            ArrayList<Teacher> teachers) {

        try {

            ObjectOutputStream out =
                    new ObjectOutputStream(
                            new FileOutputStream(
                                    "teachers.dat"));

            out.writeObject(teachers);

            out.close();

        } catch (Exception e) {

            System.out.println(
                    "Teacher Save Error"
            );
        }
    }

    @SuppressWarnings("unchecked")
    public static ArrayList<Teacher>
    loadTeachers() {

        try {

            ObjectInputStream in =
                    new ObjectInputStream(
                            new FileInputStream(
                                    "teachers.dat"));

            ArrayList<Teacher> teachers =
                    (ArrayList<Teacher>)
                            in.readObject();

            in.close();

            return teachers;

        } catch (Exception e) {

            return new ArrayList<>();
        }
    }
}

/* =========================================
   REPORTS
========================================= */

class Reports {

    public static void generateReport(

            ArrayList<Student> students,

            ArrayList<Teacher> teachers,

            ArrayList<Book> books) {

        System.out.println(
                "\n========== REPORT =========="
        );

        System.out.println(
                "Total Students : "
                        + students.size()
        );

        System.out.println(
                "Total Teachers : "
                        + teachers.size()
        );

        System.out.println(
                "Total Books    : "
                        + books.size()
        );

        if (!students.isEmpty()) {

            Student topper = students.get(0);

            for (Student s : students) {

                if (s.getMarks() >
                        topper.getMarks()) {

                    topper = s;
                }
            }

            System.out.println(
                    "Topper Student : "
                            + topper.getName()
            );

            System.out.println(
                    "Marks          : "
                            + topper.getMarks()
            );
        }

        System.out.println(
                "============================"
        );
    }
}

/* =========================================
   AUTO SAVE THREAD
========================================= */

class AutoSaveThread extends Thread {

    private ArrayList<Student> students;
    private ArrayList<Teacher> teachers;

    public AutoSaveThread(

            ArrayList<Student> students,

            ArrayList<Teacher> teachers) {

        this.students = students;
        this.teachers = teachers;
    }

    @Override
    public void run() {

        while (true) {

            try {

                FileManager.saveStudents(
                        students);

                FileManager.saveTeachers(
                        teachers);

                System.out.println(
                        "\n[Auto Save Completed]"
                );

                Thread.sleep(
                        120000
                );

            } catch (Exception e) {

                System.out.println(
                        "Auto Save Error"
                );
            }
        }
    }
}
 
