# Project BankBase

## Running the Application on your local machine

When running the microservices its recommended running locally ony the ones you need.
Every microservice is using it's own database container so when running a given microservice
you would need to run a Docker container for the database and afterwards you can execute a
service specific command to run the given service.

Be sure to run the frontend and gateway-service in order to checkout full functionality of
the application and then you can optionally run other services and se how they inpact the 
application with their feature addition.

## Run using this flow of commands:

1. Run postgre container for the database.
2. Execute bootRun for this specific service.

### Run Gateway-Service

```bash
docker run -e POSTGRES_USER=user -e POSTGRES_PASSWORD=password -e POSTGRES_DB=gateway_db -p 5443:5432 -d postgres
```

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




