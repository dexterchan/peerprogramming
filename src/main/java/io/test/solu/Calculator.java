package io.test.solu;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

//time : O(N) space: O(N)
public class Calculator {

    public double calculate(String eqt){
        InterimResult interimResult = this.calculateHelper(eqt);
        return interimResult.result;
    }

    private class InterimResult{
        double result;
        int jump;
    }

    private boolean isHigher(Character c1, Character c){
        if (c1 == '*' || c1 == '/'){
            return false;
        }
        if (c != null && (c == '*' || c =='/')){
            return true;
        }else
            return false;
    }

    private InterimResult calculateHelper(String eqt){
        int l = eqt.length();

        int ptr = 0;
        InterimResult interimResult = new InterimResult();

        double res = Double.parseDouble(String.valueOf(eqt.charAt(0)));
        while (ptr < l-2){
            char operator = eqt.charAt(ptr + 1);
            double operand = Double.parseDouble(String.valueOf(eqt.charAt(ptr + 2)));
            Character nextOperator = ptr<l-4 ? eqt.charAt(ptr + 3) : null;

            InterimResult interimResultOperand = null;
            if (this.isHigher(operator, nextOperator) ){
                String subEqt = eqt.substring(ptr + 2);
                if (operator=='-'){
                    subEqt = subEqt.replaceAll("\\+","_");
                    subEqt = subEqt.replaceAll("-", "+");
                    subEqt = subEqt.replaceAll("_", "-");
                }
                interimResultOperand = this.calculateHelper(subEqt);
            }else{
                interimResultOperand = new InterimResult();
                interimResultOperand.result = operand;
                interimResultOperand.jump = 2;
            }

            switch (operator){
                case '+':
                    res = this.add(res, interimResultOperand.result);
                    ptr += interimResultOperand.jump;
                    break;
                case '-':
                    res = this.minus(res, interimResultOperand.result);
                    ptr += interimResultOperand.jump;
                    break;
                case '*':
                    res = this.multiply(res, interimResultOperand.result);
                    ptr += interimResultOperand.jump;
                    break;
                case '/':
                    res = this.divide(res, interimResultOperand.result);
                    ptr += interimResultOperand.jump;
                    break;
            }

        }
        interimResult.result = res;
        interimResult.jump = ptr + 2;
        return interimResult;
    }

    private double add(double x, double y){
        return x + y;
    }
    private double minus (double x, double y){
        return x - y;
    }
    private double multiply(double x, double y){
        return x * y;
    }
    private double divide (double x, double y){
        return x/y;
    }



    public static void main (String[] args ){

        String eqt = "5+2*3-7/2*4+8*2";
        //eqt = "5*2-4+3*2";
        Calculator calculator = new Calculator();
        System.out.println(calculator.calculate(eqt));
    }

}
