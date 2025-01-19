 # Coupon System Backend

## Overview

The Coupon System Backend is a Java-based application designed to facilitate the management of coupons for companies and customers. It allows companies to create and manage coupons, while customers can view and redeem them. Administrators have oversight capabilities to manage users and monitor system activities.

## Features

### Company Management
- Register and manage company profiles.
- Create, update, and delete coupons.
- Set expiration dates and discount amounts for coupons.

### Customer Access
- View available coupons.
- Redeem coupons.

### Administrator Controls
- Oversee all companies and their associated coupons.
- Manage user roles and permissions.

## Technologies Used

- **Java**: Core programming language.
- **Spring Boot**: Framework for building the application.
- **MySQL**: Database management system.
- **Maven**: Build automation tool.
- **Lombok**: Java library to reduce boilerplate code.

## Getting Started

### Prerequisites

- **JDK 11 or higher**: Ensure Java Development Kit is installed.
- **Maven**: For building and managing the project.
- **MySQL Server**: Set up the database server.

### Installation

1. **Clone the repository**:

   ```bash
   git clone https://github.com/Mentzer1/Coupon-system-backend.git
   ```

2. **Navigate to the project directory**:

   ```bash
   cd Coupon-system-backend
   ```

3. **Configure the database**:
   - Update the `application.properties` file with your MySQL credentials and database URL.

4. **Build the project**:

   ```bash
   mvn clean install
   ```

5. **Run the application**:

   ```bash
   mvn spring-boot:run
   ```

## Usage

- Access the application at `http://localhost:8080`.
- Use the provided endpoints to interact with the system.

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

## Acknowledgments

This project is inspired by similar coupon management systems and aims to provide a robust backend solution for managing coupons effectively.
