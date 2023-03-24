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

 import static java.lang.Double.NaN;
 import static java.lang.Math.log10;
 import static java.lang.Math.pow;
 
 public class Calculator {
 
     public enum BiOperatorModes {
         normal, add, minus, multiply, divide , xpowerofy 
     }
 
     public enum MonoOperatorModes {
         square, squareRoot, oneDividedBy, cos, sin, tan, log, percent, abs,
         sinh, cosh, tanh, cube, factorial, decToFrac
     }
 
     private Double num1, num2;
     private BiOperatorModes mode = BiOperatorModes.normal;
 
     private Double calculateBiImpl() {
         if (mode == BiOperatorModes.normal) {
             return num2;
         }
         if (mode == BiOperatorModes.add) {
             if (num2 != 0) {
                 return num1 + num2;
             }
 
             return num1;
         }
         if (mode == BiOperatorModes.minus) {
             return num1 - num2;
         }
         if (mode == BiOperatorModes.multiply) {
             return num1 * num2;
         }
         if (mode == BiOperatorModes.divide) {
            System.out.println(num1);
            System.out.println(num2);
             return num1 / num2;
         }
         if (mode == BiOperatorModes.xpowerofy) {
             return pow(num1,num2);
         }
 
         // never reach
         throw new Error();
     }
 
    public Double calculateBi(BiOperatorModes newMode, Double num) {
        if (mode == BiOperatorModes.normal) {
            num2 = 0.0;
            num1 = num;
            mode = newMode;
            return NaN;
        } else {
            num2 = num;
            num1 = calculateBiImpl();
            mode = newMode;
            return num1;
        }
    }
 
     public Double calculateEqual(Double num) {
        return calculateBi(BiOperatorModes.normal, num);
    }

    
 
     public Double reset() {
         num2 = 0.0;
         num1 = 0.0;
         mode = BiOperatorModes.normal;
 
         return NaN;
     }
 
     public Double calculateMono(MonoOperatorModes newMode, Double num) {
         if (newMode == MonoOperatorModes.square) {
             return num * num;
         }
         if (newMode == MonoOperatorModes.squareRoot) {
             return Math.sqrt(num);
         }
         if (newMode == MonoOperatorModes.oneDividedBy) {
             return 1 / num;
         }
         if (newMode == MonoOperatorModes.cos) {
             return Math.cos(Math.toRadians(num));
         }
         if (newMode == MonoOperatorModes.sin) {
             return Math.sin(Math.toRadians(num));
         }
         if (newMode == MonoOperatorModes.tan) {
             if (num == 0 || num % 180 == 0) {
                 return 0.0;
             }
             if (num % 90 == 0 && num % 180 != 0) {
                 return NaN;
             }
 
             return Math.tan(Math.toRadians(num));
         }
         if (newMode == MonoOperatorModes.log) {
             return log10(num);
         }
         if (newMode == MonoOperatorModes.percent) {
            return num / 100;
         }
         if (newMode == MonoOperatorModes.abs){
             return Math.abs(num);
         }

         if (newMode == MonoOperatorModes.cosh) {
            return Math.cosh(Math.toRadians(num));
        }
        if (newMode == MonoOperatorModes.sinh) {
            return Math.sinh(Math.toRadians(num));
        }
        if (newMode == MonoOperatorModes.tanh) {
            if (num == 0 || num % 180 == 0) {
                return 0.0;
            }
            if (num % 90 == 0 && num % 180 != 0) {
                return NaN;
            }

            return Math.tanh(Math.toRadians(num));
        }
        if (newMode == MonoOperatorModes.cube) {
            return num * num * num; 
        }
        if (newMode == MonoOperatorModes.factorial) {
            double n = num;
            double total = 1;
            while (n != 0) {
                total *= n;
                n--;
            }
            return total;
        }

        if (newMode == MonoOperatorModes.decToFrac) {
            return Double.parseDouble(decimalToFraction(num));
        }


        // never reach
        throw new Error();
    }

    public String decimalToFraction(double decimal) {
        // Initialize numerator and denominator to handle integer inputs
        int numerator = (int) decimal;
        int denominator = 1;
        
        // Check if input has a decimal component
        if (decimal != (double) numerator) {
            // Find the number of decimal places
            int decimalPlaces = String.valueOf(decimal).split("\\.")[1].length();
            
            // Convert the decimal to a fraction
            int p = (int) Math.pow(10, decimalPlaces);
            int q = p;
            while (decimal * q - Math.floor(decimal * q) > 0) {
                q++;
            }
            numerator = (int) (decimal * q);
            denominator = q;
            
            // Simplify the fraction by dividing numerator and denominator by their greatest common divisor
            int gcd = gcd(numerator, denominator);
            numerator /= gcd;
            denominator /= gcd;
        }
        
        // Return the fraction as a string
        return numerator + "." + denominator;
    }
    
    // Recursive function to find the greatest common divisor
    private int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }
    
    
}
 