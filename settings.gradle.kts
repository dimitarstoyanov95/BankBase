rootProject.name = "explore"

include(":explore-frontend")
include(":explore-gateway")
include(":explore-customer-service")
include(":explore-payment-service")

project(":explore-frontend").projectDir = file("frontend")
project(":explore-gateway").projectDir = file("gateway")
project(":explore-customer-service").projectDir = file("customer-service")
project(":explore-payment-service").projectDir = file("payment-service")
