package com.android.plugin.cwebp.task

import com.android.build.gradle.api.BaseVariant
import com.android.build.gradle.internal.api.ApplicationVariantImpl
import com.android.build.gradle.internal.api.LibraryVariantImpl
import com.android.plugin.cwebp.spi.VariantProcessor
import com.google.auto.service.AutoService
import org.gradle.api.Project

@AutoService(VariantProcessor::class)
class Convert2WebpVariantProcessor : VariantProcessor {
    override fun process(project: Project, variant: BaseVariant) {
        val tasks = project.tasks
        val convert2WebpTask = tasks.findByName("convert2Webp") ?: tasks.create(
            "convert2Webp",
            Convert2WebpTask::class.java
        )
        val mergeResourcesTask = variant.mergeResourcesProvider.get()
        mergeResourcesTask.dependsOn(convert2WebpTask)
    }
}