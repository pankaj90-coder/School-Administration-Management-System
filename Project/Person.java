import java.io.Serializable;

/*
 * Abstract Parent Class
 * Used by Student and Teacher classes
 */

public abstract class Person implements Serializable {

    private static final long serialVersionUID = 1L;

    protected int id;
    protected String name;
    protected String phone;
    protected String email;

    // Constructor
    public Person(int id, String name, String phone, String email) {

        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    // Getters

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    // Setters

    public void setName(String name) {

        if (name != null && !name.trim().isEmpty()) {
            this.name = name;
        }
    }

    public void setPhone(String phone) {

        if (phone != null && !phone.trim().isEmpty()) {
            this.phone = phone;
        }
    }

    public void setEmail(String email) {

        if (email != null && !email.trim().isEmpty()) {
            this.email = email;
        }
    }

    // Common Information

    public void displayBasicInfo() {

        System.out.println("ID      : " + id);
        System.out.println("Name    : " + name);
        System.out.println("Phone   : " + phone);
        System.out.println("Email   : " + email);
    }

    // Abstract Method
    // Must be implemented by child classes

    public abstract void display();

    @Override
    public String toString() {

        return "ID=" + id +
                ", Name='" + name + '\'' +
                ", Phone='" + phone + '\'' +
                ", Email='" + email + '\'';
    }
}