package com.company;

import static org.junit.Assert.*;
import org.junit.Test;

public class CalculatorTest {

    public void main(String[] args) {
	    testSub();
    }

    @Test
    public void testSub() {
        Calculator calc = new Calculator();
        double result = calc.subtract(40,25);
        assertEquals( 15, result,  0);
    }
}
