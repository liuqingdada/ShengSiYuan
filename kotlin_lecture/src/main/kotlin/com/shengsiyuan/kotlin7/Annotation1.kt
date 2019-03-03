package com.shengsiyuan.kotlin7

/**
 * Created by yangliuqing
 * 2019-03-03.
 * Email: 1239604859@qq.com
 *
 * 注解 annotation
 * meta-annotation (元注解)
 */

@Target(AnnotationTarget.CLASS,
        AnnotationTarget.CONSTRUCTOR,
        AnnotationTarget.FIELD,
        AnnotationTarget.FUNCTION,
        AnnotationTarget.VALUE_PARAMETER,
        AnnotationTarget.EXPRESSION,
        AnnotationTarget.PROPERTY_GETTER,
        AnnotationTarget.PROPERTY_SETTER)
@Retention(AnnotationRetention.SOURCE)
@Repeatable
@MustBeDocumented
annotation class MyAnnotation

@MyAnnotation
class MyClass @MyAnnotation constructor(@MyAnnotation private val i: Int) {
    @MyAnnotation
    private val j: Int = 100

    var k: Int? = null
        @MyAnnotation set

    @MyAnnotation
    fun myMethod(@MyAnnotation a: Int): Int {
        return (@MyAnnotation a + i)
    }
}