package com.yj.employee_service;

import com.yj.employee_service.utils.Calculator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CalculatorTest {
    Calculator calculator;
    @BeforeEach
    public void setUp(){
        //arrange
        calculator=new Calculator();
    }

    @Test
    public void multiplyTest_success(){

        //Act
        Integer res=calculator.multiply(4,5);
        //Assert
        assertEquals(20, res);
    }

    @Test
    public void divideTest_success(){
        //Act
        ArithmeticException exception = assertThrows(ArithmeticException.class, () -> {
            calculator.divide(20,0);
        });
        //Assert
        assertEquals("Cannot divide by zero", exception.getMessage());
    }
}
