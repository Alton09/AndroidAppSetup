// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
    alias(libs.plugins.hilt.plugin) apply false
    alias(libs.plugins.spotless) apply true
}

configure<com.diffplug.gradle.spotless.SpotlessExtension> {
    kotlin {
        target("**/*.kt")
        ktlint().editorConfigOverride(
            mutableMapOf(
                "ktlint_function_naming_ignore_when_annotated_with" to "Composable",
            ) as Map<String, Any>?,
        )
    }
    kotlinGradle {
        target("*.gradle.kts")
        ktlint()
    }
}
