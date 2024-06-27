rootProject.name = "bank-base"

val services = listOf(
    //"account-service",
    //"backup-service",
//    "deposit-service",
//    "investment-service",
//    "loan-service",
//    "notification-service",
    "frontend",
    "gateway",
    "profile-service",
    "payment-service",
//    "support-service",
//    "transaction-service"
)

services.forEach { service ->
    include(":bank-base-$service")
    project(":bank-base-$service").projectDir = file(service)
}