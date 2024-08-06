import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.UUID;

/**
 * Main class to handle user interactions and process flow.
 */
public class Main {

    // Method to run the bash script with specified arguments and return its output
    private static String runBashScript(String scriptName, String... args) throws IOException, InterruptedException {
        // Construct command to run the bash script with arguments
        StringBuilder command = new StringBuilder("bash ").append(scriptName);
        for (String arg : args) {
            command.append(" ").append(arg);
        }

        // Execute the command
        Process process = Runtime.getRuntime().exec(command.toString());
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

        // Read and return the output from the script
        StringBuilder output = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            output.append(line).append("\n");
        }

        // Wait for the process to complete
        process.waitFor();
        return output.toString().trim(); // Return output as String
    }

    public static void main(String[] args) {
        try {
            while (true) {
                // Display initial menu
                System.out.println("Are you an Admin or a Patient? Enter 1 for Admin, 2 for Patient, 3 to Exit:");
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                int choice = Integer.parseInt(reader.readLine());

                switch (choice) {
                    case 1: // Admin
                        handleAdminMenu(reader);
                        break;
                    case 2: // Patient
                        handlePatientMenu(reader);
                        break;
                    case 3: // Exit
                        System.out.println("Exiting...");
                        return;
                    default:
                        System.out.println("Invalid choice, please try again.");
                        break;
                }
            }
        } catch (IOException | InterruptedException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    private static void handleAdminMenu(BufferedReader reader) throws IOException, InterruptedException {
        while (true) {
            // Display Admin menu
            System.out.println("Admin Menu:");
            System.out.println("1. Onboard User");
            System.out.println("2. Login");
            System.out.println("3. Generate CSV Files");
            System.out.println("4. Exit to Main Menu");
            int choice = Integer.parseInt(reader.readLine());

            switch (choice) {
                case 1: // Onboard User
                    System.out.println("Enter the email of the user to onboard:");
                    String email = reader.readLine();
                    String uuid = UUID.randomUUID().toString();
                    runBashScript("user-manager.sh", "onboard", email, uuid);
                    System.out.println("User onboarded with UUID: " + uuid);
                    break;
                case 2: // Login
                    System.out.println("Enter your email:");
                    String adminEmail = reader.readLine();
                    System.out.println("Enter your password:");
                    String adminPassword = reader.readLine();
                    String loginResult = runBashScript("user-manager.sh", "login", adminEmail, adminPassword);
                    if ("success".equals(loginResult)) {
                        adminActions(reader);
                    } else {
                        System.out.println("Invalid login credentials.");
                    }
                    break;
                case 3: // Generate CSV Files
                    runBashScript("user-manager.sh", "generate-csv");
                    System.out.println("CSV files generated.");
                    break;
                case 4: // Exit to Main Menu
                    return;
                default:
                    System.out.println("Invalid choice, please try again.");
                    break;
            }
        }
    }

    private static void adminActions(BufferedReader reader) throws IOException, InterruptedException {
        while (true) {
            System.out.println("Admin Actions Menu:");
            System.out.println("1. Download Reports");
            System.out.println("2. Download List of Users");
            System.out.println("3. Logout");
            int choice = Integer.parseInt(reader.readLine());

            switch (choice) {
                case 1: // Download Reports
                    runBashScript("user-manager.sh", "download-reports");
                    System.out.println("Reports downloaded.");
                    break;
                case 2: // Download List of Users
                    runBashScript("user-manager.sh", "download-users");
                    System.out.println("User list downloaded.");
                    break;
                case 3: // Logout
                    return;
                default:
                    System.out.println("Invalid choice, please try again.");
                    break;
            }
        }
    }

    private static void handlePatientMenu(BufferedReader reader) throws IOException, InterruptedException {
        while (true) {
            // Display Patient menu
            System.out.println("Patient Menu:");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            int choice = Integer.parseInt(reader.readLine());

            switch (choice) {
                case 1: // Register
                    System.out.println("Enter the UUID of the user:");
                    String uuid = reader.readLine();
                    String email = runBashScript("user-manager.sh", "verify-uuid", uuid);

                    if (email.isEmpty()) {
                        System.out.println("Invalid UUID.");
                    } else {
                        // Proceed with patient registration
                        System.out.println("Enter First Name:");
                        String firstName = reader.readLine();
                        System.out.println("Enter Last Name:");
                        String lastName = reader.readLine();
                        System.out.println("Enter Date of Birth (YYYY-MM-DD):");
                        String dateBirth = reader.readLine();
                        System.out.println("HIV Status (true/false):");
                        String statusHiv = reader.readLine();
                        System.out.println("Date of Diagnosis (YYYY-MM-DD):");
                        String dateDiagnosis = reader.readLine();
                        System.out.println("ART Status (true/false):");
                        String statusArt = reader.readLine();
                        System.out.println("Date of ART (YYYY-MM-DD):");
                        String dateArt = reader.readLine();
                        System.out.println("Country ISO Code:");
                        String countryISO = reader.readLine();
                        System.out.println("Enter Password:");
                        String password = reader.readLine();

                        runBashScript("user-manager.sh", "register", firstName, lastName, email, uuid, password, dateBirth, statusHiv, dateDiagnosis, statusArt, dateArt, countryISO);
                        System.out.println("Patient registered successfully.");
                    }
                    break;
                case 2: // Login
                    System.out.println("Enter your email:");
                    String patientEmail = reader.readLine();
                    System.out.println("Enter your password:");
                    String patientPassword = reader.readLine();
                    String loginResult = runBashScript("user-manager.sh", "login", patientEmail, patientPassword);
                    if ("success".equals(loginResult)) {
                        patientActions(reader);
                    } else {
                        System.out.println("Invalid login credentials.");
                    }
                    break;
                case 3: // Exit
                    return;
                default:
                    System.out.println("Invalid choice, please try again.");
                    break;
            }
        }
    }

    private static void patientActions(BufferedReader reader) throws IOException, InterruptedException {
        while (true) {
            System.out.println("Patient Actions Menu:");
            System.out.println("1. Update Profile");
            System.out.println("2. View Profile");
            System.out.println("3. Compute Lifespan (to be done later)");
            System.out.println("4. Download iCalendar (to be done later)");
            System.out.println("5. Logout");
            int choice = Integer.parseInt(reader.readLine());

            switch (choice) {
                case 1: // Update Profile
                    System.out.println("Update profile functionality (to be done later).");
                    break;
                case 2: // View Profile
                    System.out.println("View profile functionality (to be done later).");
                    break;
                case 3: // Compute Lifespan
                    System.out.println("Compute lifespan functionality (to be done later).");
                    break;
                case 4: // Download iCalendar
                    System.out.println("Download iCalendar functionality (to be done later).");
                    break;
                case 5: // Logout
                    return;
                default:
                    System.out.println("Invalid choice, please try again.");
                    break;
            }
        }
    }
}
