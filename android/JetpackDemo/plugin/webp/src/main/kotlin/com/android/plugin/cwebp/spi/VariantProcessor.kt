package com.android.plugin.cwebp.spi

import com.android.build.gradle.api.BaseVariant
import org.gradle.api.Project

interface VariantProcessor {
    fun process(project: Project, variant: BaseVariant)
}