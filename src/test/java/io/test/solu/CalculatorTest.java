package io.test.solu;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    @Test
    void parseArgList() {
        String eqt = "50+2*20";//"5+2*3";
        Calculator c = new Calculator();
        Calculator.MyArgList argList= c.convertArgList(eqt);

        assertEquals(11,eqt);
    }

    @Test
    void testCalcualte(){
        String eqt = "50+2";
        Calculator c = new Calculator();
        Calculator.MyArgList argList= c.convertArgList(eqt);
        double r = c.calculateArgList(argList);
        assertEquals(52,r);
    }


}