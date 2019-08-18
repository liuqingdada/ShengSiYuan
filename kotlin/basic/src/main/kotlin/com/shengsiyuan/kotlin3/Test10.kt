package com.shengsiyuan.kotlin3

/**
 * Created by suhen
 * 18-12-9.
 * Email: 1239604859@qq.com
 */

enum class Season {
    SPRING,
    SUMMER,
    AUTUMN,
    WINTER
}

enum class Season2(val temperature: Int) {
    SPRING(10),
    SUMMER(30),
    AUTUMM(10),
    WINTER(-30)
}

enum class Season3 {
    SPRING {
        override fun spring(): Season3 = SPRING
    },

    SUMMER {
        override fun spring(): Season3 = SUMMER
    },

    AUTUMM {
        override fun spring(): Season3 = AUTUMM
    },

    WINTER {
        override fun spring(): Season3 = WINTER
    };

    abstract fun spring(): Season3
}

fun main() {
    val seasons = Season.values()
    seasons.forEach {
        println(it)
        println(it.declaringClass)
        println(it.name)
        println(it.ordinal)
        println("--------------------")
    }

    val season = Season.valueOf("SPRING")
    println(season.ordinal)
    println(season.name)
}
