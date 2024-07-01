# Project BankBase

## Overview

BankBase is a microservice-based banking system designed to provide essential banking functionalities
without adhering to specific country regulations. Each microservice runs independently with its own 
database container, providing modular and scalable architecture.

## Services and Dependencies

1. Gateway-Service: Entry point for the application. It routes requests to appropriate services.
2. Account-Service: Manages user accounts.
3. Backup-Service: Handles data backups.
4. Deposit-Service: Manages deposits.
5. Investment-Service: Manages investments.
6. Loan-Service: Manages loans.
7. Notification-Service: Handles notifications.
8. Payment-Service: Manages payments.
9. Profile-Service: Manages user profiles.
10. Support-Service: Provides support functionalities.
11. Transaction-Service: Manages transactions.

## Running the Application

### Prerequisites

- Docker
- Gradle

### Run Microservices

1. Run postgre container for the database.
2. Execute bootRun for this specific service.


### Run Frontend

```bash
./gradlew startFrontend
```

### Run Gateway-Service

```bash
./gradlew runGatewayService
```


### Run Account-Service

```bash
docker run -e POSTGRES_USER=user -e POSTGRES_PASSWORD=password -e POSTGRES_DB=account_db -p 5440:5432 -d postgres
```

```bash
./gradlew runAccountService
```

### Run Backup-Service


```bash
docker run -e POSTGRES_USER=user -e POSTGRES_PASSWORD=password -e POSTGRES_DB=backup_db -p 5441:5432 -d postgres
```

```bash
./gradlew runAccountService
```

### Run Deposit-Service


```bash
docker run -e POSTGRES_USER=user -e POSTGRES_PASSWORD=password -e POSTGRES_DB=deposit_db -p 5442:5432 -d postgres
```

```bash
./gradlew runDepositService
```

### Run Investment-Service


```bash
docker run -e POSTGRES_USER=user -e POSTGRES_PASSWORD=password -e POSTGRES_DB=investment_db -p 5444:5432 -d postgres
```

```bash
./gradlew runInvestmentService
```

### Run Loan-Service


```bash
docker run -e POSTGRES_USER=user -e POSTGRES_PASSWORD=password -e POSTGRES_DB=loan_db -p 5445:5432 -d postgres
```

```bash
./gradlew runLoanService
```

### Run Notification-Service


```bash
docker run -e POSTGRES_USER=user -e POSTGRES_PASSWORD=password -e POSTGRES_DB=notification_db -p 5446:5432 -d postgres
```

```bash
./gradlew runNotificationService
```

### Run Payment-Service


```bash
docker run -e POSTGRES_USER=user -e POSTGRES_PASSWORD=password -e POSTGRES_DB=payment_db -p 5447:5432 -d postgres
```

```bash
./gradlew runPaymentService
```

### Run Profile-Service


```bash
docker run -e POSTGRES_USER=user -e POSTGRES_PASSWORD=password -e POSTGRES_DB=profile_db -p 5448:5432 -d postgres
```

```bash
./gradlew runProfileService
```

### Run Support-Service


```bash
docker run -e POSTGRES_USER=user -e POSTGRES_PASSWORD=password -e POSTGRES_DB=support_db -p 5449:5432 -d postgres
```

```bash
./gradlew runSupportService
```

### Run Transaction-Service


```bash
docker run -e POSTGRES_USER=user -e POSTGRES_PASSWORD=password -e POSTGRES_DB=transaction_db -p 5450:5432 -d postgres
```

```bash
./gradlew runTransactionService
```

## Conclusion

This refactored README provides a clearer structure, making it easier to understand the relationships between
microservices and the steps required to run each service. This structure improves readability and usability for
developers working with the application.

For further details, please refer to the project documentation or contact the development team.
