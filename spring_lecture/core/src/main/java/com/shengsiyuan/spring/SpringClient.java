package com.shengsiyuan.spring;

import com.shengsiyuan.spring.bean.Student;

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
    }
}
