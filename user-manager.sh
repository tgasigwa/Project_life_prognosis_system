#!/bin/bash

# Function to onboard a user
onboard_user() {
    email=$1
    uuid=$2
    echo "$email;$uuid" >> uuid.txt
    echo "User onboarded with UUID: $uuid"
}

# Function to verify UUID
verify_uuid() {
    uuid=$1
    email=$(grep "$uuid" uuid.txt | cut -d ';' -f1)
    if [ -z "$email" ]; then
        echo "Invalid UUID."
    else
        echo "$email"
    fi
}

# Function to register a patient
register_patient() {
    firstName=$1
    lastName=$2
    email=$3
    uuid=$4
    password=$5
    dateBirth=$6
    statusHiv=$7
    dateDiagnosis=$8
    statusArt=$9
    dateArt=${10}
    countryISO=${11}
    echo "$firstName;$lastName;$email;$uuid;$password;PATIENT;$dateBirth;$statusHiv;$dateDiagnosis;$statusArt;$dateArt;$countryISO" >> user-store.txt
    echo "Patient registered successfully."
}

# Function to login a user
login_user() {
    email=$1
    password=$2
    # Simulate user authentication; replace with actual logic
    if grep "$email;$password" user-store.txt > /dev/null; then
        echo "success"
    else
        echo "failure"
    fi
}

# Function to generate empty CSV files
generate_csv() {
    echo "firstName;lastName;inputEmail;uuid;inputPassword;profile;dateBirth;statusHiv;dateDiagnosis;statusArt;dateArt;countryISO" > users.csv
    echo "reportData" > analytics.csv
}

case $1 in
    onboard)
        onboard_user $2 $3
        ;;
    verify-uuid)
        verify_uuid $2
        ;;
    register)
        register_patient $2 $3 $4 $5 $6 $7 $8 $9 ${10} ${11} ${12}
        ;;
    login)
        login_user $2 $3
        ;;
    generate-csv)
        generate_csv
        ;;
    *)
        echo "Invalid option."
        ;;
esac
