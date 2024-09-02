package com.yj.employee_service.utils;

public class Calculator {
    public Integer multiply(int n1, int n2){
        return n1 * n2;
    }
    public Integer divide(int n1, int n2){
        if(n2==0)
            throw new ArithmeticException("Cannot divide by zero");
        return n1 / n2;
    }
}
