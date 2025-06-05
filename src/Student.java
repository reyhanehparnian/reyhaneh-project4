/**
 * Represents a student with directory information including name, address, contact details,
 * and Smith Union box number. Provides methods to access student data and perform
 * vowel counting operations on names.
 *
 * @author rparnian
 */
public class Student {

    /**
     * Student's first name
     */
    private String firstName;

    /**
     * Student's last name
     */
    private String lastName;

    /**
     * Current campus housing location
     */
    private String address;

    /**
     * Campus phone number
     */
    private String phone;

    /**
     * Official college email address
     */
    private String email;

    /**
     * Smith Union campus mailbox number
     */
    private int suBox;

    /**
     * Constructs a Student object by parsing a formatted input line.
     *
     * @param line a string containing all student information separated by pipes
     */
    public Student(String line) {
        String[] parts = line.split("\\|");
        if (parts.length != 5) {
            throw new IllegalArgumentException("Invalid student data format");
        }

        String[] nameParts = parts[0].trim().split(" ");
        this.firstName = nameParts[0];
        this.lastName = nameParts[nameParts.length - 1];

        this.address = parts[1].trim();
        this.phone = parts[2].trim();
        this.email = parts[3].trim();
        this.suBox = Integer.parseInt(parts[4].trim());
    }

    /**
     * @return the student's first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @return the student's last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @return the student's address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @return the student's phone number
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @return the student's email address
     */
    public String getEmail() {
        return email;
    }

    /**
     * @return the student's Smith Union box number
     */
    public int getSuBox() {
        return suBox;
    }

    /**
     * Returns a string representation of the student
     *
     * @return formatted string containing all student information
     */
    @Override
    public String toString() {
        return firstName + " " + lastName + " | " + address + " | " + phone + " | " + email + " | " + suBox;
    }
}