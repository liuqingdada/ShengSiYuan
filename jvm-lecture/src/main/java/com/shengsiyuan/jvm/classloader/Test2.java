package com.shengsiyuan.jvm.classloader;

/**
 * Created by andy
 * 2020/3/26.
 * Email: 1239604859@qq.com
 * <p>
 * 常量在编译阶段会存入到调用这个常量方法所在类的常量池中,
 * 本质上, 调用类并没有直接引用到定义常量的类, 因此并不会触发定义常量的类的初始化
 * <p>
 * 注意: 这里指的是将常量存放到了{@link Test2}的常量池中, 之后{@link Test2}与{@link Parent}就没有任何关系了
 * 甚至, 我们可以将{@link Parent}的class文件删除
 * 验证: 删除文件后, cd目录到包的根目录, 执行 java com.shengsiyuan.jvm.classloader.Test2
 * 另外, 通过设置 -XX:+TraceClassLoading 参数, 我们可以看到, {@link Parent}没有被加载
 * javap -c
 * <p>
 * <p>
 * <p>
 * 助记符:
 * ldc: 表示将int, float或是String类型的常量值从常量池中推送至栈顶
 * {@link com.sun.org.apache.bcel.internal.generic.LDC}
 * <p>
 * bipush: 表示将单字节 (-128 ~ 127) 的常量值推送至栈顶
 * {@link com.sun.org.apache.bcel.internal.generic.BIPUSH}
 * <p>
 * sipush: 表示将一个短整形常量值 (-32768 ~ 32767) 推送至栈顶
 * {@link com.sun.org.apache.bcel.internal.generic.SIPUSH}
 * <p>
 * iconst_i: 表示将 int 类型 1 推动至栈顶 (iconst_m1 ~ iconst_5)
 * {@link com.sun.org.apache.bcel.internal.generic.ICONST}
 */

public class Test2 {
    public static void main(String[] args) {
        System.out.println(Parent.m);
    }
}
