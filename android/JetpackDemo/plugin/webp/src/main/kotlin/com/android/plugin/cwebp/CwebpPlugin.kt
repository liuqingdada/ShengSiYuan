package com.android.plugin.cwebp

import com.android.build.gradle.AppExtension
import com.android.build.gradle.LibraryExtension
import com.android.plugin.cwebp.spi.VariantProcessor
import org.gradle.api.Plugin
import org.gradle.api.Project
import java.util.*

class CwebpPlugin : Plugin<Project> {
    companion object {
        const val PLUGIN_NAME = "com.android.plugin.cwebp"
    }

    override fun apply(project: Project) {
        println("apply plugin: '$PLUGIN_NAME'")

        // Extension
        project.extensions.create("convert2WebpConfig", Convert2WebpExtension::class.java)

        when {
            project.plugins.hasPlugin("com.android.application") -> project.extensions.getByType(
                AppExtension::class.java
            ).let { android ->
                project.afterEvaluate {
                    ServiceLoader.load(VariantProcessor::class.java, javaClass.classLoader)
                        .toList().let { processes ->
                            android.applicationVariants.forEach { variant ->
                                processes.forEach {
                                    it.process(project, variant)
                                }
                            }
                        }
                }
            }

            project.plugins.hasPlugin("com.android.library") -> project.extensions.getByType(
                LibraryExtension::class.java
            ).let { android ->
                project.afterEvaluate {
                    ServiceLoader.load(VariantProcessor::class.java, javaClass.classLoader)
                        .toList().let { processes ->
                            android.libraryVariants.forEach { variant ->
                                processes.forEach {
                                    it.process(project, variant)
                                }
                            }
                        }
                }
            }
        }
    }
}