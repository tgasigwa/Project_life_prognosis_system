import java.io.IOException;

/**
 * Admin class extends User and adds admin-specific methods.
 */
public class Admin extends User {
    public Admin(String firstName, String lastName, String inputEmail, String inputPassword, Profile profile) {
        super(firstName, lastName, inputEmail, inputPassword, profile);
    }

    /**
     * Method to download reports.
     */
    public void downloadReports() throws IOException {
        // Implement report download logic here
    }

    /**
     * Method to download list of users.
     */
    public void downloadListOfUsers() throws IOException {
        // Implement list of users download logic here
    }

    @Override
    public void logout() {
        // Implement logout logic for Admin
    }
}
