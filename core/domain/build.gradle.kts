plugins {
    id("TenantManagement.kotlin.feature")
    id("TenantManagement.coroutines")
}

dependencies {
    implementation(project(":core:commons"))
    implementation(project(":core:data"))

    implementation(libs.bundles.javax)
}
