package com.shengsiyuan.jdk8;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class OptionalTest2 {

    public static void main(String[] args) {
        ///
        Employee employee = new Employee();
        employee.setNeme("zhangsan");
        Employee employee2 = new Employee();
        employee.setNeme("lisi");

        Company company = new Company();
        company.setName("shengsiyuan");

        List<Employee> employees = Arrays.asList(employee, employee2);
        //company.setEmployees(employees);

        ///
        Optional<Company> optional = Optional.ofNullable(company);

        List<Employee> list = optional.map(com -> com.getEmployees())
                                      .orElse(Collections.emptyList());
        System.out.println(list);
    }

    private void test(Optional optional) { // Optional 不要用作方法参数和成员变量, 最好只用作返回值

    }
}
