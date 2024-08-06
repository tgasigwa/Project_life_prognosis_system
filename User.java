/**
 * Abstract User class to define common attributes and methods for all users.
 */
public abstract class User {
    private String firstName;
    private String lastName;
    private String inputEmail;
    private String inputPassword;
    private Profile profile;

    /**
     * Constructor to initialize a User object.
     */
    public User(String firstName, String lastName, String inputEmail, String inputPassword, Profile profile) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.inputEmail = inputEmail;
        this.inputPassword = inputPassword;
        this.profile = profile;
    }

    // Getter and Setter methods

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getInputEmail() {
        return inputEmail;
    }

    public void setInputEmail(String inputEmail) {
        this.inputEmail = inputEmail;
    }

    public String getInputPassword() {
        return inputPassword;
    }

    public void setInputPassword(String inputPassword) {
        this.inputPassword = inputPassword;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    /**
     * Abstract method to be implemented by subclasses for logout functionality.
     */
    public abstract void logout();
}
