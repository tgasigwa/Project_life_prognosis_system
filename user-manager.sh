#!/bin/bash  # Shebang to specify the script should be run using bash

# Function to onboard a user
onboard_user() {
    email=$1  # First argument: user's email
    uuid=$2  # Second argument: user's UUID
    echo "$email;$uuid" >> uuid.txt  # Append email and UUID to uuid.txt file, separated by a semicolon
    echo "User onboarded with UUID: $uuid"  # Print confirmation message
}

# Function to verify UUID
verify_uuid() {
    uuid=$1  # First argument: UUID to verify
    # Use grep to search for the UUID in uuid.txt and cut to extract the email part
    email=$(grep "$uuid" uuid.txt | cut -d ';' -f1)
    if [ -z "$email" ]; then  # Check if the email variable is empty (UUID not found)
        echo "Invalid UUID."  # Print error message
    else
        echo "$email"  # Print the email associated with the UUID
    fi
}

# Function to register a patient
register_patient() {
    firstName=$1  # First argument: patient's first name
    lastName=$2  # Second argument: patient's last name
    email=$3  # Third argument: patient's email
    uuid=$4  # Fourth argument: UUID
    password=$5  # Fifth argument: password
    dateBirth=$6  # Sixth argument: date of birth
    statusHiv=$7  # Seventh argument: HIV status (true/false)
    dateDiagnosis=$8  # Eighth argument: date of HIV diagnosis
    statusArt=$9  # Ninth argument: ART status (true/false)
    dateArt=${10}  # Tenth argument: date of ART initiation
    countryISO=${11}  # Eleventh argument: country ISO code
    # Append patient details to user-store.txt file, separated by semicolons
    echo "$firstName;$lastName;$email;$uuid;$password;PATIENT;$dateBirth;$statusHiv;$dateDiagnosis;$statusArt;$dateArt;$countryISO" >> user-store.txt
    echo "Patient registered successfully."  # Print confirmation message
}

# Function to login a user
login_user() {
    email=$1  # First argument: user's email
    password=$2  # Second argument: user's password
    # Simulate user authentication by checking if the email and password pair exists in user-store.txt
    if grep "$email;$password" user-store.txt > /dev/null; then
        echo "success"  # Print success message if authentication is successful
    else
        echo "failure"  # Print failure message if authentication fails
    fi
}

# Function to generate empty CSV files
generate_csv() {
    # Create or overwrite users.csv with header fields
    echo "firstName;lastName;inputEmail;uuid;inputPassword;profile;dateBirth;statusHiv;dateDiagnosis;statusArt;dateArt;countryISO" > users.csv
    # Create or overwrite analytics.csv with placeholder data
    echo "reportData" > analytics.csv
}

# Case statement to handle different script commands
case $1 in
    onboard)
        onboard_user $2 $3  # Call onboard_user function with provided arguments
        ;;
    verify-uuid)
        verify_uuid $2  # Call verify_uuid function with provided UUID
        ;;
    register)
        register_patient $2 $3 $4 $5 $6 $7 $8 $9 ${10} ${11} ${12}  # Call register_patient function with provided arguments
        ;;
    login)
        login_user $2 $3  # Call login_user function with provided email and password
        ;;
    generate-csv)
        generate_csv  # Call generate_csv function to create CSV files
        ;;
    *)
        echo "Invalid option."  # Print error message for invalid commands
        ;;
esac

