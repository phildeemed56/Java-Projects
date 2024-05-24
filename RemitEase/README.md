
# RemitEase

RemitEase is a sophisticated Java-based application designed to facilitate seamless international money transfers from the US to 25 countries worldwide. This project showcases proficiency in object-oriented design, Java programming, and robust database architecture.

## Table of Contents

- [Project Structure](#project-structure)
- [Features](#features)
- [Technologies Used](#technologies-used)
- [Setup Instructions](#setup-instructions)
- [Usage](#usage)
- [Contributing](#contributing)
- [Contact](#contact)

## Project Structure

The project is meticulously structured into six distinct packages, each serving a critical role in ensuring the system’s functionality and scalability:

1. **projectfiles.model**
   - Houses essential model classes such as `User`, `Customer`, `Recipient`, `Remittance`, `Partner`, and `ExchangeRate`.
   - Encapsulates core entities and their relationships for efficient data management.

2. **projectfiles.view**
   - Manages the front-end aspects using FXML files.
   - Ensures a user-friendly interface that facilitates intuitive interactions.

3. **projectfiles.DAO**
   - Manages Data Access Objects (DAOs) and their implementations.
   - Provides a robust interface for interacting with the MySQL database hosted on AWS RDS.

4. **projectfiles.DAOTest**
   - Dedicated to unit testing the DAOs.
   - Implements comprehensive test cases to validate data access methods.

5. **projectfiles.controller**
   - Orchestrates the control logic of RemitEase.
   - Facilitates smooth interactions between the user interface and underlying business logic.

6. **projectfiles.app**
   - Integrates all application components and manages database connectivity.
   - Ensures seamless initialization and operation of the application.

## Features

- **Create**: Allows users to initiate transactions and create instances of customers, recipients, partners, and remittances.
- **Read**: Facilitates real-time access to transaction details and historical records.
- **Update**: Supports dynamic updates of recipient details and transaction parameters.
- **Delete**: Implements stringent data deletion protocols for secure account and data removal.

## Technologies Used

- **Programming Language**: Java
- **Database**: MySQL hosted on AWS RDS
- **Front-end**: FXML for user interface
- **Testing**: JUnit for unit tests
- **Development Tools**: IntelliJ IDEA, Git

## Setup the Database:

Create a MySQL database instance on AWS RDS.
Configure the database connection parameters in projectfiles.app.Application.


## Build the Project:

Open the project in IntelliJ IDEA.
Ensure that all dependencies are resolved.

## Run the Application:

Execute the main class in projectfiles.app.


## Usage

**Creating Users and Transactions:**

Use the user interface to create new customers, recipients, partners, and remittances.
Ensure all mandatory fields are filled out correctly.

**Viewing Transactions:**

Access transaction history and details through the provided interface.

**Updating Information:**

Update recipient information through the recipient history feature.

**Deleting Accounts:**

Securely delete user accounts and associated data via the account settings.

**Contributing**

Contributions are welcome! Please fork the repository and create a pull request with your changes. Ensure that your code adheres to the project’s coding standards and includes appropriate tests.


**Contact**

Philip Mensah

Email: philmens100@gmail.com
