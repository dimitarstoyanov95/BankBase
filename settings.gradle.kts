rootProject.name = "explore"

include(":explore-frontend")
include(":explore-backend")
project(":explore-frontend").projectDir = file("frontend")
project(":explore-backend").projectDir = file("backend")
