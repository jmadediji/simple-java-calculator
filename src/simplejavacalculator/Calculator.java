/**
 * @name        Simple Java Calculator
 * @package     ph.calculator
 * @file        Main.java
 * @author      SORIA Pierre-Henry
 * @email       pierrehs@hotmail.com
 * @link        http://github.com/pH-7
 * @copyright   Copyright Pierre-Henry SORIA, All Rights Reserved.
 * @license     Apache (http://www.apache.org/licenses/LICENSE-2.0)
 */

package simplejavacalculator;

import java.util.ArrayList;
import java.math.BigInteger;
import java.util.Stack;
import javax.swing.JTextField;

public class Calculator {

    /**
     * Handles operator input. Appends the operator to the current number and operation list.
     * @param currentNumAndOp - the current number and operation list
     * @param text - the text field to display the result
     * @param history - the text field to display the history of operations
     * @param beforeText - the text before the current operation
     * @param historyText - the history before the current operation
     * @param name - the name of the operator
     */
    public void handleOperator(ArrayList<String> currentNumAndOp, JTextField history, JTextField text, String beforeText, String historyText, String name) {
        if (currentNumAndOp.size()==0) {
            currentNumAndOp.add(beforeText);
            text.setText(name);
            history.setText(historyText+name);
        }
        else {
            currentNumAndOp.add(beforeText);
            if (currentNumAndOp.get(0).equals(currentNumAndOp.get(1))) {currentNumAndOp.remove(0);}
            text.setText(name);
            history.setText(historyText+name);
        }
    }

    /**
     * Handles the square root of a number.
     * 
     * @param currentNumAndOp - the current number and operation list
     * @param num - the number to take the square root of
     * @param history - the text field to display the history of operations
     * @param text - the text field to display the result
     * @param before - the history before the current operation
     * @param name - the name of the operation
     */
    public void handleSquareRoot(ArrayList<String> currentNumAndOp, double num, JTextField history, JTextField text, String before, String name) {
        String sqrt = Double.toString(Math.pow(num, 0.5));
        // Edge case when size = 1, must replace saved value with new value //
        if (currentNumAndOp.size() == 1) {
        currentNumAndOp.remove(0);
        currentNumAndOp.add(sqrt);
        }
        text.setText(sqrt);
        history.setText(before + sqrt);
    }

    /**
     * Negates a number.
     * 
     * @param currentNumAndOp - the current number and operation list
     * @param num - the number to negate
     * @param history - the text field to display the history of operations
     * @param text - the text field to display the result
     */
    public void handleNegateNumber(ArrayList<String> currentNumAndOp, double num, JTextField history, JTextField text){
        String complement = Double.toString(num *= -1);
        // Edge case when size = 1, must replace saved value with new value //
        if (currentNumAndOp.size() == 1) {
        currentNumAndOp.remove(0);
        currentNumAndOp.add(complement);
        }
        text.setText(complement);
        String historyText = history.getText();
        int indx = historyText.lastIndexOf(num + "");
        String before = historyText.substring(0, indx);
        if (num > 0) {
        history.setText(before.substring(0, before.length() - 1) + complement);
        } else if (num < 0) {
        if (historyText.lastIndexOf("(") > indx) {
        before = historyText.substring(0, historyText.lastIndexOf("(") + 1);
        }
        history.setText(before + complement);
        }
    }

    /**
     * Handles the sine of a number.
     * @param currentNumAndOp - the current number and operation list
     * @param radians - true if the number is in radians, false if it is in degrees
     * @param num - the number to take the sine of
     * @param history - the text field to display the history of operations
     * @param text - the text field to display the result
     * @param before - the history before the current operation
     */
    public void handleSin(ArrayList<String> currentNumAndOp, boolean radians, double num, JTextField history, JTextField text, String before) {
        String sin;
        // Radian Case //
        if (radians) {
        sin = Double.toString(Math.sin(num));
        }
        // Degree Case //
        else {
        sin = Double.toString(Math.sin(Math.toRadians(num)));
        }
        // Edge case when size = 1, must replace saved value with new value //
        if (currentNumAndOp.size() == 1) {
        currentNumAndOp.remove(0);
        currentNumAndOp.add(sin);
        }
        text.setText(sin);
        history.setText(before + sin);
    }

    /**
     * Handles the cosine of a number.
     * 
     * @param currentNumAndOp - the current number and operation list
     * @param radians - true if the number is in radians, false if it is in degrees
     * @param num - the number to take the cosine of
     * @param history - the text field to display the history of operations
     * @param text - the text field to display the result
     * @param before - the history before the current operation
     */
    public void handleCos(ArrayList<String> currentNumAndOp, boolean radians, double num, JTextField history, JTextField text, String before) {
        String cos;
        // Radian Case //
        if (radians) {
            cos = Double.toString(Math.cos(num));
        }
        // Degree Case //
        else {
            cos = Double.toString(Math.cos(Math.toRadians(num)));
        }
        // Edge case when size = 1, must replace saved value with new value //
        if (currentNumAndOp.size()==1) {
            currentNumAndOp.remove(0);
            currentNumAndOp.add(cos);
        }
        text.setText(cos);
        history.setText(before+cos);
    }

    /**
     * Handles the tangent of a number.
     * 
     * @param currentNumAndOp - the current number and operation list
     * @param radians - true if the number is in radians, false if it is in degrees
     * @param num - the number to take the tangent of
     * @param history - the text field to display the history of operations
     * @param text - the text field to display the result
     * @param before - the history before the current operation
     */
    public void handleTan(ArrayList<String> currentNumAndOp, boolean radians, double num, JTextField history, JTextField text, String before) {
        String tan;
        // Radian Case //
        if (radians) {
            tan = Double.toString(Math.tan(num));
        }
        // Degree Case //
        else {
            tan = Double.toString(Math.tan(Math.toRadians(num)));
        }
        // Edge case when size = 1, must replace saved value with new value //
        if (currentNumAndOp.size()==1) {
            currentNumAndOp.remove(0);
            currentNumAndOp.add(tan);
        }
        text.setText(tan);
        history.setText(before+tan);
    }

    /**
     * Handles the inverse sine of a number.
     * 
     * @param currentNumAndOp - the current number and operation list
     * @param radians - true if the number is in radians, false if it is in degrees
     * @param num - the number to take the inverse sine of
     * @param history - the text field to display the history of operations
     * @param text - the text field to display the result
     * @param before - the history before the current operation
     */
    public void handleOneDividedBy(ArrayList<String> currentNumAndOp, double num, JTextField history, JTextField text, String before) {
        String oneDividedBy = Double.toString(1/num);
        // Edge case when size = 1, must replace saved value with new value //
        if (currentNumAndOp.size()==1) {
            currentNumAndOp.remove(0);
            currentNumAndOp.add(oneDividedBy);
        }
        text.setText(oneDividedBy);
        history.setText(before+oneDividedBy);
    }

    /**
     * Handles the base 10 logarithm of a number.
     * @param currentNumAndOp - the current number and operation list
     * @param num - the number to take the base 10 logarithm of
     * @param history - the text field to display the history of operations
     * @param text - the text field to display the result
     * @param listStack - the stack of lists
     * @param before - the history before the current operation
     */
    public void handleLog10(ArrayList<String> currentNumAndOp, double num, JTextField history, JTextField text, Stack<ArrayList<String>> listStack, String before) {

        // Edge case when num = 0
        if (num == 0) {
            currentNumAndOp = new ArrayList<String>();
            listStack.clear();
            listStack.push(currentNumAndOp);
            history.setText("");
            text.setText("ERROR");
        } else {
            String logTenX = Double.toString(Math.log10(num));

            // Edge case when size = 1, must replace saved value with new value
            if (currentNumAndOp.size() == 1) {
                currentNumAndOp.remove(0);
                currentNumAndOp.add(logTenX);
            }

            text.setText(logTenX);
            history.setText(before + logTenX);
        }
    }

    /**
     * Handles the arc sine of a number.
     * @param currentNumAndOp - the current number and operation list
     * @param radians - true if the number is in radians, false if it is in degrees
     * @param num - the number to take the arc sine of
     * @param history - the text field to display the history of operations
     * @param text - the text field to display the result
     * @param before - the history before the current operation
     */
    public void handleArcsin(ArrayList<String> currentNumAndOp, boolean radians, double num, JTextField history, JTextField text, String before) {

        String asin;

        // Radian Case
        if (radians) {
            asin = Double.toString(Math.asin(num));
        }

        // Degree Case
        else {
            asin = Double.toString(Math.toDegrees(Math.asin(num)));
        }

        // Edge case when size = 1, must replace saved value with new value
        if (currentNumAndOp.size() == 1) {
            currentNumAndOp.remove(0);
            currentNumAndOp.add(asin);
        }

        text.setText(asin);
        history.setText(before + asin);
    }

    /**
     * Handles the arc cosine of a number.
     * @param currentNumAndOp - the current number and operation list
     * @param radians - true if the number is in radians, false if it is in degrees
     * @param num - the number to take the arc cosine of
     * @param history - the text field to display the history of operations
     * @param text - the text field to display the result
     * @param before - the history before the current operation
     */
    public void handleArccos(ArrayList<String> currentNumAndOp, boolean radians, double num, JTextField history, JTextField text, String before) {
        String acos;
        // Radian Case //
        if (radians) {
            acos = Double.toString(Math.acos(num));
        }
        // Degree Case //
        else {
            acos = Double.toString(Math.toDegrees(Math.acos(num)));
        }
        // Edge case when size = 1, must replace saved value with new value //
        if (currentNumAndOp.size()==1) {
            currentNumAndOp.remove(0);
            currentNumAndOp.add(acos);
        }
        text.setText(acos);
        history.setText(before+acos);
    }

    /**
     * Handles the arctangent of a number.
     * 
     * @param currentNumAndOp - the current number and operation list
     * @param num - the number to take the arctangent of
     * @param history - the text field to display the history of operations
     * @param text - the text field to display the result
     * @param before - the history before the current operation
     */
    public void handleArctan(ArrayList<String> currentNumAndOp, boolean radians, double num, JTextField history, JTextField text, String before) {
        String atan;
        // Radian Case //
        if (radians) {
            atan = Double.toString(Math.atan(num));
        }
        // Degree Case //
        else {
            atan = Double.toString(Math.toDegrees(Math.atan(num)));
        }
        // Edge case when size = 1, must replace saved value with new value //
        if (currentNumAndOp.size()==1) {
            currentNumAndOp.remove(0);
            currentNumAndOp.add(atan);
        }
        text.setText(atan);
        history.setText(before+atan);
    }

    
    /**
     * Handles the factorial of a number.
     * @param currentNumAndOp - the current number and operation list
     * @param num - the number to take the factorial of
     * @param text - the text field to display the result
     * @param history - the text field to display the history of operations
     * @param listStack - the stack of lists
     * @param name - the name of the operation
     * @param historyText - the history before the current operation
     */
    public void handleFactorial( ArrayList<String> currentNumAndOp, double num, JTextField text, JTextField history, Stack<ArrayList<String>> listStack, String name, String historyText) {
        // Solves problem where 171! and above return "infinity" //
        if (num > 170) {
            currentNumAndOp.removeAll(currentNumAndOp);
            text.setText("LIMIT EXCEEDED");
        }
        // Disallows non-integer inputs //
        else if ((num == Math.floor(num)) && !Double.isInfinite(num)) {
            String fac = Double.toString(factorial(num));
            // Edge case when size = 1, must replace saved value with new value //
            if (currentNumAndOp.size() == 1) {
                currentNumAndOp.remove(0);
                currentNumAndOp.add(fac);
            }
            text.setText(fac);
            history.setText(historyText + "!");
        }
        // Scenario when input is not an integer //
        else {
            currentNumAndOp = new ArrayList<String>();
            listStack.clear();
            listStack.push(currentNumAndOp);
            text.setText("ERROR: MUST BE INTEGER");
            history.setText("");
        }
    }

    /**
     * Handles the percent of a number.
     * @param currentNumAndOp - the current number and operation list
     * @param num - the number to take the percent of
     * @param text - the text field to display the result
     * @param history - the text field to display the history of operations
     * @param before - the history before the current operation
     * @param name - the name of the operation
     */
    public void handlePercent(ArrayList<String> currentNumAndOp, double num,  JTextField text, JTextField history, String before, String name) {
        String percent = Double.toString(num / 100);
        // Edge case when size = 1, must replace saved value with new value //
        if (currentNumAndOp.size() == 1) {
            currentNumAndOp.remove(0);
            currentNumAndOp.add(percent);
        }
        text.setText(percent);
        history.setText(before + percent);
    }

    /**
     * Clears the calculator.
     * @param currentNumAndOp - the current number and operation list
     * @param history - the text field to display the history of operations
     * @param text - the text field to display the result
     * @param listStack - the stack of lists for undo/redo functionality
     */
    
    public void handleClear(ArrayList<String> currentNumAndOp, JTextField history, JTextField text, Stack<ArrayList<String>> listStack) {
        currentNumAndOp.clear();
        listStack.clear();
        listStack.push(currentNumAndOp);
        text.setText("0");
        history.setText("");
    }

    /**
     * Handles the e button.
     * @param currentNumAndOp - the current number and operation list
     * @param history - the text field to display the history of operations
     * @param text - the text field to display the result
     * @param listStack - the stack of lists for undo/redo functionality
     * @param before - the history before the current operation
     */
    public void handleE(ArrayList<String> currentNumAndOp, JTextField history, JTextField text, Stack<ArrayList<String>> listStack, String before) {
        String eString = Double.toString(Math.E);
        // Edge case when size = 1, must replace saved value with new value //
        if (currentNumAndOp.size() == 1) {
            currentNumAndOp.remove(0);
            currentNumAndOp.add(eString);
        }
        text.setText(eString);
        history.setText(before + eString);
    }

    // Plus Button //

    /**
     * Handles the e^x button.
     * @param currentNumAndOp - the current number and operation list
     * @param num - the number to take the e^x of
     * @param history - the text field to display the history of operations
     * @param text - the text field to display the result
     * @param before - the history before the current operation
     */
    public void handleEToX(ArrayList<String> currentNumAndOp, double num, JTextField history, JTextField text,  String before) {
        String eToX = Double.toString(Math.pow(Math.E, num));
        // Edge case when size = 1, must replace saved value with new value //
        if (currentNumAndOp.size()==1) {
            currentNumAndOp.remove(0);
            currentNumAndOp.add(eToX);
        }
        text.setText(eToX);
        history.setText(before+eToX);
    }

    // 7 Button //
    // 8 Button //
    // 9 Button //
    // Minus Button //

    /**
     * Handles the square button.
     * @param currentNumAndOp - the current number and operation list
     * @param num - the number to take the square of
     * @param history - the text field to display the history of operations
     * @param text - the text field to display the result
     * @param before - the history before the current operation
     * @param name - the name of the operation
     */
    public void handleSquare(ArrayList<String> currentNumAndOp, double num, JTextField history, JTextField text, String before, String name) {
        String squared = Double.toString(Math.pow(num, 2));
        // Edge case when size = 1, must replace saved value with new value //
        if (currentNumAndOp.size()==1) {
            currentNumAndOp.remove(0);
            currentNumAndOp.add(squared);
        }
        text.setText(squared);
        history.setText(before+squared);
    }

    // 4 Button //
    // 5 Button //
    // 6 Button //
    // Multiply Button //

    /**
     * Handles the ln button.
     * @param currentNumAndOp - the current number and operation list
     * @param num - the number to take the ln of
     * @param history - the text field to display the history of operations
     * @param text - the text field to display the result
     * @param listStack - the stack of lists for undo/redo functionality
     * @param before - the history before the current operation
     */
    public void handleLn(ArrayList<String> currentNumAndOp, double num, JTextField history, JTextField text, Stack<ArrayList<String>> listStack, String before) {
        // Edge case when num = 0 //
        if (num == 0) {
            currentNumAndOp = new ArrayList<String>();
            listStack.clear();
            listStack.push(currentNumAndOp);
            history.setText("");
            text.setText("ERROR");
        }
        else {
            String lnx = Double.toString(Math.log(num));
            // Edge case when size = 1, must replace saved value with new value //
            if (currentNumAndOp.size()==1) {
                currentNumAndOp.remove(0);
                currentNumAndOp.add(lnx);
            }
            text.setText(lnx);
            history.setText(before+lnx);
        }
    }

    // 1 Button //
    // 2 Button //
    // 3 Button //
    // Divide Button //

    /**
     * Handles the open parenthesis button. Can only be used when there is a number before it(implied multiplication).
     * @param currentNumAndOp - the current number and operation list
     * @param history - the text field to display the history of operations
     * @param text - the text field to display the result
     * @param listStack - the stack of lists for undo/redo functionality
     * @param beforeText - the text before the current operation
     * @param historyText - the history before the current operation
     * @param name - the name of the operation
     */
    public void handleOpenParenthesis(ArrayList<String> currentNumAndOp, JTextField history, JTextField text, Stack<ArrayList<String>> listStack, String beforeText, String historyText, String name) {
        if (currentNumAndOp.size() > 0 || Double.parseDouble(beforeText) != 0.0) {
            if (Double.parseDouble(beforeText) == 0.0 && currentNumAndOp.size() == 1) {
                history.setText("");
                currentNumAndOp.remove(0);
                currentNumAndOp.add("1");
            }
            currentNumAndOp.add(beforeText);
            currentNumAndOp.add("x");
        } else {
            currentNumAndOp.add("1");
            currentNumAndOp.add("x");
        }
        ArrayList<String> newNumAndOp = new ArrayList<String>();
        listStack.push(newNumAndOp);
        currentNumAndOp = newNumAndOp;
        text.setText("0");
        historyText = history.getText();
        history.setText(historyText + name);
    }

    /**
     * Handles the closing parenthesis button. Can only be used when there is a number before it(implied multiplication).
     * @param currentNumAndOp - the current number and operation list
     * @param history - the text field to display the history of operations
     * @param text - the text field to display the result
     * @param listStack - the stack of lists for undo/redo functionality
     * @param beforeText - the text before the current operation
     * @param historyText - the history before the current operation
     * @param name - the name of the operation
     */
    public void handleClosingParenthesis(ArrayList<String> currentNumAndOp, JTextField history, JTextField text, Stack<ArrayList<String>> listStack, String beforeText, String historyText, String name){
        currentNumAndOp.add(beforeText);
        double result = calc(currentNumAndOp);
        text.setText(Double.toString(result));
        // Remove ArrayList from stack & move pointer   //
        currentNumAndOp.removeAll(currentNumAndOp);
        listStack.pop();
        currentNumAndOp = listStack.peek();
        history.setText(historyText + name);
    }

    // 0 Button //
    // Decimal Button //

    /**
     * Handles the equals button. Calculates the result of the current operation.
     * @param currentNumAndOp - the current number and operation list
     * @param text - the text field to display the result
     * @param history - the text field to display the history of operations
     * @param beforeText - the text before the current operation
     * @param name - the name of the operation
     */
    public void handleEquals(ArrayList<String> currentNumAndOp, JTextField text, JTextField history, String beforeText, String name) {
        currentNumAndOp.add(beforeText);
        double result = calc(currentNumAndOp);
        text.setText(Double.toString(result));
        history.setText(Double.toString(result));
        currentNumAndOp.removeAll(currentNumAndOp);
        currentNumAndOp.add(Double.toString(result));
    }

    /**
     * Handle the decimal to fraction button. Converts the decimal to a fraction.
     * @param currentNumAndOp - the current number and operation list
     * @param num - the number to convert to a fraction
     * @param history - the text field to display the history of operations
     * @param text - the text field to display the result
     * @param before - the history before the current operation
     * @param name - the name of the operation
     */
    public void handleDectoFraction(ArrayList<String> currentNumAndOp, double num, JTextField history, JTextField text, String before, String name) {
        
        final double EPSILON = 1.0E-10;

        // Check if the number is an integer
        if (Math.abs(num - Math.round(num)) < EPSILON) {
            String integer = Integer.toString((int) num);

            // Edge case when size = 1, must replace saved value with new value //
            if (currentNumAndOp.size()==1) {
                currentNumAndOp.remove(0);
                currentNumAndOp.add(integer);
            }
            text.setText(integer);
            history.setText(before + integer);
            return;
        }

        // Initialize numerator and denominator
        int numerator = 1;
        int denominator = 1;

        // Convert the decimal to a fraction
        while (Math.abs(num - (double) numerator / denominator) >= EPSILON) {
            if (num - (double) numerator / denominator > 0) {
                numerator++;
            } else {
                denominator++;
            }
        }

        // Simplify the fraction by dividing both the numerator and denominator by their gcd
        int gcd = BigInteger.valueOf(numerator).gcd(BigInteger.valueOf(denominator)).intValue();
        numerator /= gcd;
        denominator /= gcd;

        // Build the fraction string
        String fraction = numerator + "/" + denominator;

        // Edge case when size = 1, must replace saved value with new value //
        if (currentNumAndOp.size()==1) {
            currentNumAndOp.remove(0);
            currentNumAndOp.add(fraction);
        }
        text.setText(fraction);
        history.setText(before + fraction);
    }

    // Radian/Degree Button //

    /**
     * Handles the ten to the x button. Calculates 10^x.
     * @param currentNumAndOp - the current number and operation list
     * @param num - the number to raise 10 to
     * @param history - the text field to display the history of operations
     * @param text - the text field to display the result
     * @param before - the history before the current operation
     */
    public void handleTenToX(ArrayList<String> currentNumAndOp, double num, JTextField history, JTextField text, String before) {
        String tenToX = Double.toString(Math.pow(10, num));
        // Edge case when size = 1, must replace saved value with new value //
        if (currentNumAndOp.size()==1) {
            currentNumAndOp.remove(0);
            currentNumAndOp.add(tenToX);
        }
        text.setText(tenToX);
        history.setText(before+tenToX);
    }

    /**
     * Handles the pi button. Displays pi.
     * @param currentNumAndOp - the current number and operation list
     * @param history - the text field to display the history of operations
     * @param text - the text field to display the result
     * @param before - the history before the current operation
     */
    public void handlePi(ArrayList<String> currentNumAndOp, JTextField history, JTextField text, String before) {
        String piString = Double.toString(Math.PI);
        // Edge case when size = 1, must replace saved value with new value //
        if (currentNumAndOp.size()==1) {
            currentNumAndOp.remove(0);
            currentNumAndOp.add(piString);
        }
        text.setText(piString);
        history.setText(before+piString);
    }

    /**
     * Handles digit input. Appends the digit to the current number.
     * @param currentNumAndOp - the current number and operation list
     * @param history - the text field to display the history of operations
     * @param text - the text field to display the result
     * @param name - the name of the digit
     */
    public void handleDigitInput(ArrayList<String> currentNumAndOp, JTextField history, JTextField text, String name){
        
        String beforeText = text.getText();
        String historyText = history.getText();
        // Disallow appending to π/e approximations & calc function results //
        if (!(beforeText.equals(Double.toString(Math.PI)) || beforeText.equals(Double.toString(Math.E))) 
                && !(currentNumAndOp.size()==1)) {
            String aftertext = beforeText;
            if (name.equals(".")) {
                // Disallow multiple decimal points //
                if (!beforeText.contains(".")) {
                    aftertext += name;
                }
            }
            else {aftertext += name;}
            text.setText(aftertext);
            history.setText(historyText+name);
        }
    }

    /**
     * Calculates the result of the current number and operation list.
     * @param al - the current number and operation list 
     * @return - the result of the calculation
     */
    public double calc(ArrayList<String> al) {
        int size = al.size();
        // Edge cases //
        if (al.size() == 0) {return 0.0;}
        if (al.size() == 1) {return Double.parseDouble(al.get(0));}
        else {
            // Handle multiplication and division first, moving left-to-right //
            for (int i = 1; i <= size - 2; i+=2) {
                switch (al.get(i)) {
                    case "x":
                        double mult = Double.parseDouble(al.get(i-1))*Double.parseDouble(al.get(i+1));
                        al.set(i-1, Double.toString(mult));
                        al.remove(i);
                        al.remove(i);
                        // Set to -1, as loop will add 2 //
                        i=-1;
                        size = al.size();
                        break;
                    case "\u00F7":
                        double div = Double.parseDouble(al.get(i-1))/Double.parseDouble(al.get(i+1));
                        al.set(i-1, Double.toString(div));
                        al.remove(i);
                        al.remove(i);
                        // Set to -1, as loop will add 2 //
                        i=-1;
                        size = al.size();
                        break;
                }
            }
            // Now handle addition and subtraction, again left-to-right //
            double result = Double.parseDouble(al.get(0));
            // Iterates over oper-double pairs //
            for (int j = 1; j <= size - 2; j+=2) {
                String oper = al.get(j);
                double nxt = Double.parseDouble(al.get(j+1));
                // Checks which operation to use //
                switch (oper) {
                    case "+":
                        result += nxt;
                        break;
                    case "-":
                        result -= nxt;
                        break;
                }
            }

            
            return result;
        }
    }

    /**
     * Calculates the factorial of the current number for the factorial button.
     * @param d - the current number
     * @return - the factorial of the current number
     */
    public double factorial(double d) {
        // Zero case //
        if (d==0) {return 1.0;}
        else {
            double result = 1.0;
            while (d > 1.0) {
                result*=d;
                d-=1;
            }
            return result;
        }
    }

}