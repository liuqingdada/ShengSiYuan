### 网上书店

+ http://www.china-pub.com/

### JVM 工具认识

+ jconsole
+ jvisualvm

###一、类加载

在Java代码中, 类型的加载,连接与初始化过程都是在程序运行期间完成的

#### 加载

查找并加载类的二进制数据

####连接

- 验证：确保被加载的类的正确性
- 准备：为类的静态变量分配内存，并将其初始化为默认值
- 解析：把类中的符号引用转换为直接引用

#### 初始化

为类的静态变量赋予正确的初始值

#### 使用

#### 卸载

---

Java程序对类的使用方式可以分为两种

- 主动使用
- 被动使用

所有的Java虚拟机实现必须在每个类或接口被Java程序 “首次主动使用” 时才初始化他们

#### 主动使用

助记符：javap命令反编译class文件，可以得到很多助记符。例如getstatic、putstatic、invokestatic

- 创建类的实例
- 访问某个类或接口的静态变量，或者对该静态变量赋值
- 调用类的静态方法
- 反射（如Class.forName("com.test.Test")）
- 初始化一个类的子类
- Java虚拟机启动时被标记为启动类的类（java Test）
- JDK1.7开始提供的动态语言支持：java.lang.invoke.MethodHandle实例的解析结果REF_getStatic，REF_putStatic，REF_invokeStatic句柄对应的类没有初始化，则初始化

#### 被动使用

除了主动使用的情况，其它使用Java类的方式都被看作是对类的被动使用，都不会导致类的初始化

---

类的加载指的是将类的.class文件中的二进制数据读入到内存中，将其放在运行时数据区的方法区内，然后在内存中创建一个java.lang.Class对象（规范中并未说明Class对象位于哪里，HotSpot虚拟机将其放在了方法区中）用来封装类在方法区内的数据结构

#### 加载.class文件的方式

- 从本地系统中直接加载
- 通过网络下载.class文件
- 从zip，jar等归档文件中加载.class文件
- 从转悠数据库中提取.class文件
- 将java源文件动态编译为.class文件（动态代理，JSP-->java）


