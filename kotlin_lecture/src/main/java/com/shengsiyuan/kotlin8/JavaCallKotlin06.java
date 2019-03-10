package com.shengsiyuan.kotlin8;

import java.lang.reflect.Constructor;

/**
 * Created by yangliuqing
 * 2019-03-10.
 * Email: 1239604859@qq.com
 * <p>
 * 我们无法通过 new 关键子来创建 Kotlin 编译器自动生成的以 Kt 结尾的类的实例
 * 字节码中没有构造方法，这并不违背 JVM 规范
 */
class JavaCallKotlin06 {

    public static void main(String[] args) throws Exception {
        JKClass jkClass = new JKClass();

        System.out.println(Kotlin06Kt.getStr());
        Kotlin06Kt.test();

//        Kotlin06Kt kotlin06Kt = new Kotlin06Kt(); // 找不到构造器
        // 试试反射
        Class<Kotlin06Kt> ktClass = Kotlin06Kt.class;
        Constructor<?>[] declaredConstructors = ktClass.getDeclaredConstructors();
        for (Constructor<?> declaredConstructor : declaredConstructors) {
            System.out.println(declaredConstructor.toString());
        }
        // 看样也不行，或者是自己太菜了
        Constructor<Kotlin06Kt> ktConstructor = ktClass.getDeclaredConstructor();
    }
}
