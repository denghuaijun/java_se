package com.denghj.jdk_8.lambda.基本语法;

/**
 * 获取年龄大于20的员工信息
 */
public class GetEmpByAge implements IEmpStrategy<Employee> {
    @Override
    public boolean compare(Employee employee) {
        return employee.getAge()>20;
    }
}
