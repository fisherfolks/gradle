plugins {
    id("gradlebuild.distribution.api-java")
}

description = "Implementation for interacting with HTTP build caches"

dependencies {
    api(libs.httpcore)
    api(libs.inject)
    api(libs.jsr305)

    api(project(":base-services"))
    api(project(":core-api"))
    api(project(":resources-http"))

    implementation(project(":base-annotations"))
    implementation(project(":core"))
    implementation(project(":logging"))
    implementation(project(":resources"))

    implementation(libs.commonsHttpclient)
    implementation(libs.guava)
    implementation(libs.slf4jApi)

    testImplementation(testFixtures(project(":core")))
    testImplementation(libs.servletApi)

    integTestImplementation(project(":enterprise-operations"))
    integTestImplementation(libs.jetty)

    integTestDistributionRuntimeOnly(project(":distributions-basics"))
}
