package com.denghj.jdk_8.lambda.基本语法;

/**
 * 获取薪水大于3000的员工信息
 */
public class GetEmpBySalary implements IEmpStrategy<Employee> {
    @Override
    public boolean compare(Employee employee) {
        return employee.getSalary()>3000;
    }
}
