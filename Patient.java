import java.time.LocalDate;

/**
 * Patient class extends User and adds attributes specific to a Patient.
 */
public class Patient extends User {
    private LocalDate dateBirth;
    private boolean statusHiv;
    private LocalDate dateDiagnosis;
    private boolean statusArt;
    private LocalDate dateArt;
    private String countryISO;

    /**
     * Constructor to initialize a Patient object.
     */
    public Patient(String firstName, String lastName, String inputEmail, String inputPassword, Profile profile,
                   LocalDate dateBirth, boolean statusHiv, LocalDate dateDiagnosis, boolean statusArt, LocalDate dateArt, String countryISO) {
        super(firstName, lastName, inputEmail, inputPassword, profile);
        this.dateBirth = dateBirth;
        this.statusHiv = statusHiv;
        this.dateDiagnosis = dateDiagnosis;
        this.statusArt = statusArt;
        this.dateArt = dateArt;
        this.countryISO = countryISO;
    }

    // Getter and Setter methods

    public LocalDate getDateBirth() {
        return dateBirth;
    }

    public void setDateBirth(LocalDate dateBirth) {
        this.dateBirth = dateBirth;
    }

    public boolean isStatusHiv() {
        return statusHiv;
    }

    public void setStatusHiv(boolean statusHiv) {
        this.statusHiv = statusHiv;
    }

    public LocalDate getDateDiagnosis() {
        return dateDiagnosis;
    }

    public void setDateDiagnosis(LocalDate dateDiagnosis) {
        this.dateDiagnosis = dateDiagnosis;
    }

    public boolean isStatusArt() {
        return statusArt;
    }

    public void setStatusArt(boolean statusArt) {
        this.statusArt = statusArt;
    }

    public LocalDate getDateArt() {
        return dateArt;
    }

    public void setDateArt(LocalDate dateArt) {
        this.dateArt = dateArt;
    }

    public String getCountryISO() {
        return countryISO;
    }

    public void setCountryISO(String countryISO) {
        this.countryISO = countryISO;
    }

    @Override
    public void logout() {
        // Implement logout logic for Patient
    }
}
