plugins {
    `kotlin-dsl`
}

group = "io.github.tenantmgt.buildlogic"

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {
    compileOnly(libs.gradle.plugin.android)
    compileOnly(libs.gradle.plugin.kotlin)
}

gradlePlugin {
    plugins {
        register("coroutines") {
            id = "TenantManagement.coroutines"
            implementationClass = "CoroutinesConventionPlugin"
        }

        register("kotlinFeature") {
            id = "TenantManagement.kotlin.feature"
            implementationClass = "KotlinFeatureConventionPlugin"
        }
    }
}
