plugins {
    alias(libs.plugins.jetbrains.kotlin.jvm)
}

dependencies {
    implementation(group = "javax.inject", name = "javax.inject", version = "1")
    implementation(libs.kotlinx.coroutines.core)
}