# GP Medical Records Application

## Overview

The GP Medical Records Application is a Java-based application designed to manage patient medical records efficiently. It allows users to log in as either a Patient or a General Practitioner (GP). The application retrieves and displays medical records stored in a MySQL database.

## Features

- **User Authentication:** 
  - Two types of users can log in: Patients and GPs.
  
- **GP Dashboard:**
  - GPs can view all medical records from the database.
  
- **Medical Record Management:**
  - GPs can access detailed medical records, including:
    - Diagnosis
    - Allergies
    - Immunization History
    - Notes
    - Prescriptions
    - Patient Details (Mother's and Father's information)

## Technologies Used

- **Frontend:** Java Swing for the GUI
- **Backend:** MySQL database
- **Development Environment:** NetBeans IDE 18
- **Database Management:** XAMPP

## Database Structure

The application uses a MySQL database named `db_mrms`. The key table is `medicalrecords` with the following structure:

| Column Name                        | Type          | Null | Default   |
|------------------------------------|---------------|------|-----------|
| RecordNo Primary                   | int(11)      | No   | None      |
| Diagnosis                          | varchar(255) | Yes  | NULL      |
| Allergies                          | varchar(255) | Yes  | NULL      |
| ImmunizationHistory                | varchar(255) | Yes  | NULL      |
| Notes                              | varchar(255) | Yes  | NULL      |
| Prescriptions                      | varchar(255) | Yes  | NULL      |
| PatientID Index                    | int(11)      | No   | None      |
| MotherName                         | varchar(255) | No   | None      |
| MotherSurname                      | varchar(255) | No   | None      |
| MotherDOB                          | varchar(10)  | No   | None      |
| MotherBloodType                    | varchar(3)   | Yes  | NULL      |
| MaternalHereditaryDisease          | varchar(255) | Yes  | NULL      |
| FatherName                         | varchar(255) | No   | None      |
| FatherSurname                      | varchar(255) | No   | None      |
| FatherDOB                          | varchar(10)  | No   | None      |
| FatherBloodType                    | varchar(3)   | Yes  | NULL      |
| PaternalHereditaryDisease          | varchar(255) | Yes  | NULL      |
| PatientBloodType                   | varchar(3)   | Yes  | NULL      |

### Important Notes:

- **RecordNo** is an auto-increment primary key.
- Patient identification is managed through the **PatientID** index.

## Getting Started

### Prerequisites

- **Java Development Kit (JDK):** Make sure you have JDK installed on your machine.
- **NetBeans IDE:** Install the latest version of NetBeans IDE for development.
- **XAMPP:** Install XAMPP for local MySQL database management.

### Installation Steps

1. **Clone the Repository:**
   ```bash
   git clone https://github.com/nmihaylov96/Medical-Record-Management-System.git
   cd Medical-Record-Management-System

2. **Set Up the Database:**

Open XAMPP and start the Apache and MySQL modules.
Access phpMyAdmin and create a database named db_mrms.
Import the SQL script (if provided) to create the medicalrecords table.

3. **Configure Database Connection:**

Update the database connection settings in your application.
Example of a typical connection string:
java
Copy code
String url = "jdbc:mysql://127.0.0.1/db_mrms";
String user = "root";
String password = ""; // your database password

4.**Run the Application:**

Open the project in NetBeans IDE.
Run the GPDashboard.java class to start the application.

**Usage**
Launch the application and log in as a GP.
Click on the designated panel (jPanel6) in the GP Dashboard to view all medical records.
Medical records will be displayed in jTable1 for review.

**Contributing**
Contributions to the GP Medical Records Application are welcome. Please fork the repository and create a pull request for any enhancements or bug fixes.

**License**
This project is licensed under the MIT License. See the LICENSE file for details.

**Acknowledgments**
Thank you to all contributors and open-source libraries that made this project possible.
