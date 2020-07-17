package com.shengsiyuan.spring;

import com.shengsiyuan.spring.bean.Student;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionReader;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

/**
 * Created by liuqing.yang
 * 2020/5/9.
 * Email: 1239604859@qq.com
 * <p>
 * 各种信息抽象化 --> Resources
 * 比如，类路径下的资源，特定文件系统上的资源，指定一个 xml 的资源等等
 * <p>
 * IOC
 * Inverse of Control 控制反转
 * 工厂，存放资源，用户只需要通过它就可以获取任何资源
 * <p>
 * DI
 * Dependency Injection 依赖注入
 * A 包含属性 B，也就是 A 依赖 B
 * A 创建的时候，B 也要创建好，并且注入到 A
 * <p>
 * 总结:
 * 1. bean信息定义在 spring 的配置文件中
 * 2. spring 抽象出来的 resource 来指定对应的配置文件
 * 3. 声明一个 spring 工厂，用来掌握配置文件中声明的各种 bean，以及 bean 之间的依赖关系与注入关系
 * 4. 定义一个配置信息读取器，该读取器用来读取之前所定义的 bean 配置文件信息
 * 5. 读取器的作用是读取我们所声明的配置文件信息，并且将读取后的信息装配到之前所声明的工厂中
 * 6. 读取器、工厂、资源对象进行相应的关联处理
 * 7. 工厂所管理的全部对象装配完毕，可以供客户端直接调用，获取客户端要使用的各种 bean 对象
 * <p>
 * spring 对应 bean 管理的核心组件:
 * 资源抽象、工厂、配置信息读取器
 * <p>
 * {@link BeanFactory}是 spring bean 工厂的最顶层的抽象
 */
public class SpringClient {
    public static void main(String[] args) {
        Resource resource = new ClassPathResource("applicationContext.xml");

        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        BeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        reader.loadBeanDefinitions(resource);

        Student student = (Student) factory.getBean("student");
        Student student2 = factory.getBean("student", Student.class);

        System.out.println(student);
        System.out.println(student2);
        System.out.println(student == student2);
    }
}
