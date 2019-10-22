package io.test.solu;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Calculator {

    class MyStack{
        Stack<Double> arguments = new Stack<>();
        Stack<Character> operator = new Stack<>();
    }
    class MyArgList{
        List<Double> arguments = new ArrayList<>();
        List<Character> operator = new ArrayList<>();
    }

    protected MyArgList convertArgList (String str){


        MyArgList myList = new MyArgList();
        //read the string
        char[] myCharArray = str.toCharArray();

        double lastArgument = 0;
        for ( char c : myCharArray){

            if(c=='+' || c=='-' || c == '*' || c == '/'){
                myList.operator.add(c);
                myList.arguments.add(lastArgument);
                lastArgument=0;

            }else{
                lastArgument = Character.getNumericValue(c) + lastArgument *10;
            }
        }
        myList.arguments.add(lastArgument);


        return myList;
    }


    double calculateArgumentList(MyArgList argList){
        //iterate the operator
        double r=0;
        MyStack stack = new MyStack();
        for( Character c: argList.operator){
            highPredrence
        }

    }

    boolean highPredrence(Character c){
        if(c=='*' || c=='/'){
            return true;
        }else{
            return false;
        }
    }

/*
Character myLastOperator = myStack.operator.peek();
            if(myStack.operator.peek() == '*' || myStack.operator.peek() == '/'){

            }
 */

    public static void main (String[] args ){
        String eqt = "5+2*3";
    }

}
