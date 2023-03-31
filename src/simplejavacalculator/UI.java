package simplejavacalculator;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.BorderLayout;
import java.awt.GridLayout;

import java.util.ArrayList;
import java.util.Stack;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class UI extends JFrame implements ActionListener {

    private JButton[] buttons;                                 // Array that stores buttons for easy access
    private final JFrame frame;                                // Frame for calculator
    private final JPanel panel;                                // Panel for buttons
    private final JTextField history;                          // History of what has been entered
    private final JTextField field;         
    private ImageIcon image;
    private BufferedImageCustom imageReturn;                   // Displayed text area on top of calculator
    private ArrayList<String> currentNumAndOp;                 // Pointer for current ArrayList, stores numbers & operators
    private Stack<ArrayList<String>> listStack;                // Stack for multi-level calculations and grouping with parentheses
    private boolean radians;                                   // Radians mode if true, degrees mode if false

    public void init() throws IOException {
        new UI();
    }

    public UI() throws IOException {

        radians = true;
        currentNumAndOp = new ArrayList<>();
        listStack = new Stack<ArrayList<String>>();
        listStack.push(currentNumAndOp);

        frame = new JFrame("Calculator PH"); // Creates empty window
        imageReturn = new BufferedImageCustom();
        image = new ImageIcon(imageReturn.imageReturn()); // Creates image icon 
        
        // Create button grid, order proportional to layout //
        String[] names = {
            "√", "+/-", "sin(x)", "cos(x)", "tan(x)",
            "1/x", "log10(x)", "arcsin(x)", "arccos(x)", "arctan(x)",
            "x!", "%", "AC", "e", "+",
            "e^x", "7", "8", "9", "-",
            "x^2", "4", "5", "6", "x",
            "ln(x)", "1", "2", "3", "\u00F7",
            "(", ")", "0", ".", "=",
            "d->f", "rad", "10^x", "\u03C0", 
        };
        panel = new JPanel();                       // Creates JPanel for the 36 buttons
        panel.setLayout(new GridLayout(9, 5)); 
        buttons = new JButton[45];                              // 38 buttons and counting
        for (int i = 0; i < 39; i++) {
            buttons[i] = new JButton(names[i]);
            buttons[i].addActionListener(this);
            panel.add(buttons[i]); // Adds ActionListener so buttons trigger an event
        }
                    // Sets up 4x9 layout

        
        // Create container for everything //
        JPanel overall = new JPanel();
        overall.setLayout(new GridLayout(4, 1));
        // History field //
        history = new JTextField("");
        // Copy/Paste //
        // Editable text field //
        field = new JTextField("0");
        // Add components //
        overall.add(history, BorderLayout.NORTH);
        overall.add(field, BorderLayout.CENTER);
        overall.add(panel, BorderLayout.SOUTH);
        // Add overall to frame //
        frame.add(overall, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setIconImage(image.getImage());
        frame.pack();
        frame.setVisible(true);
    }


    Calculator calc = new Calculator();
    @Override
    public void actionPerformed(ActionEvent e) {
        
        String beforeText = field.getText();
        String name = ((JButton) e.getSource()).getActionCommand();
        String historyText = history.getText();

        // CLEAR CASE //
        if (name.equals("AC")) {
            calc.handleClear(currentNumAndOp, history, field, listStack);
        }

        // rad/deg switch //
        else if (name.equals("rad")) {
            buttons[2].setText("deg");
            radians = false;
        }
        else if (name.equals("deg")) {
            buttons[2].setText("rad");
            radians = true;
        }
        
        // CASE 1: displayed text is not an operator //
        else if (!(beforeText.equals("\u00F7") || beforeText.equals("x") || beforeText.equals("-") || beforeText.equals("+"))) {
            double num = Double.parseDouble(beforeText);
            int divIndx = historyText.lastIndexOf("\u00F7");
            int multIndx = historyText.lastIndexOf("x");
            int subIndx = historyText.lastIndexOf("-");
            int addIndx = historyText.lastIndexOf("+");
            int indx = Math.max(Math.max(divIndx, multIndx), Math.max(subIndx, addIndx));
            String before = historyText.substring(0, indx+1);

            // If operator, add displayed number to ArrayList and display operator //
            if (name.equals("\u00F7") || name.equals("x") || name.equals("-") || name.equals("+")) {
                calc.handleOperator(currentNumAndOp, history, field, beforeText, historyText, name);
            }

            // If square-root, perform sqrt on displayed number //
            else if (name.equals("√")) {
                calc.handleSquareRoot(currentNumAndOp, num, history, field, before, name);
            }

            // +/- button, alter numeric sign //
            else if (name.equals("+/-")) {
                calc.handleNegateNumber(currentNumAndOp, num, history, field);
            }

            // If sin(x), perform sin(x) on displayed number //
            else if (name.equals("sin(x)")) {
                calc.handleSin(currentNumAndOp, radians, num, history, field, before);
            }
            // If cos(x), perform cos(x) on displayed number //
            else if (name.equals("cos(x)")) {
                calc.handleCos(currentNumAndOp, radians, num, history, field, before);
            }
            // If tan(x), perform tan(x) on displayed number //
            else if (name.equals("tan(x)")) {
                calc.handleTan(currentNumAndOp, radians, num, history, field, before);
            }

            // If 1/x, divide 1 by displayed number//
            else if (name.equals("1/x")) {
                calc.handleOneDividedBy(currentNumAndOp, num, history, field, before);
            }

            // If log10(x), perform log base 10 on displayed number //
            else if (name.equals("log10(x)")) {
                calc.handleLog10(currentNumAndOp, num, history, field, listStack, before);
            }
            
            // If arcsin(x), perform arcsin(x) on displayed number //
            else if (name.equals("arcsin(x)")) {
                calc.handleArcsin(currentNumAndOp, radians, num, history, field, before);
            }
            // If arccos(x), perform arccos(x) on displayed number //
            else if (name.equals("arccos(x)")) {
                calc.handleArccos(currentNumAndOp, radians, num, history, field, before);
            }
            // If arctan(x), perform arctan(x) on displayed number //
            else if (name.equals("arctan(x)")) {
                calc.handleArctan(currentNumAndOp, radians, num, history, field, before);
            }

            // If !, perform factorial on displayed number //
            else if (name.equals("x!")) {
                calc.handleFactorial(currentNumAndOp, num, history, field, listStack, name, historyText);
            }

            // If %, convert displayed number to decimal-equiv percentage //
            else if (name.equals("%")) {
                calc.handlePercent(currentNumAndOp, num, history, field, before, name);
            }

            // AC button already implemented //

            // If e, replace diplayed number with e //
            else if (name.equals("e")) {
                calc.handleE(currentNumAndOp, history, field, listStack, before);
            }

            // Plus Button already implemented //

            // If e^x, perform e^x on displayed number //
            else if (name.equals("e^x")) {
                calc.handleEToX(currentNumAndOp, num, history, field, before);
            }

            // 7 Button already implemented //
            // 8 Button already implemented //
            // 9 Button already implemented //
            // Minus Button already implemented //

            // If x-squared, square displayed number //
            else if (name.equals("x^2")) {
                calc.handleSquare(currentNumAndOp, num, history, field, before, name);
            }

            // 4 Button already implemented //
            // 5 Button already implemented //
            // 6 Button already implemented //
            // Multiply Button already implemented //

            // If ln(x), perform natural log on displayed number //
            else if (name.equals("ln(x)")) {
                calc.handleLn(currentNumAndOp, num, history, field, listStack, before);
            }

            // 1 Button already implemented //
            // 2 Button already implemented //
            // 3 Button already implemented //
            // Divide Button already implemented //

            // Open Parenthesis //
            else if (name.equals("(")) {
                calc.handleOpenParenthesis(currentNumAndOp, history, field, listStack, beforeText, historyText, name);
            }
            // Close Parenthesis //
            else if (name.equals(")")) {
                calc.handleClosingParenthesis(currentNumAndOp, history, field, listStack, beforeText, historyText, name);
            }

            // 0 Button already implemented //
            // Decimal Button already implemented //

            // If equals, add the displayed number to ArrayList and calculate result //
            else if (name.equals("=")) {
                calc.handleEquals(currentNumAndOp, history, field, beforeText, name);
            }

            else if (name.equals("d->f")) {
                calc.handleDectoFraction(currentNumAndOp, num, history, field, before, name);
            }

            // Radian/Degree Button Already Implemented //

            // If 10^x, perform 10^x on displayed number //
            else if (name.equals("10^x")) {
                calc.handleTenToX(currentNumAndOp, num, history, field, before);

            }

            // If π, replace diplayed number with π //
            else if (name.equals("\u03C0")) {
                calc.handlePi(currentNumAndOp, history, field, before);
            }

            // Else, add another digit to number //
            else {
                calc.handleDigitInput(currentNumAndOp, history, field, name);
            }
        }

        // CASE 2: displayed text is an operator //
        else {
            // If new operator, replace old one //
            if (name.equals("\u00F7") || name.equals("x") || name.equals("-") || name.equals("+")) {
                field.setText(name);
                history.setText(historyText.substring(0,historyText.length()-1)+name);
            }
            // If equals, disregard displayed operator and calculate result //
            else if (name.equals("=")) {
                double result = calc(currentNumAndOp);
                field.setText(Double.toString(result));
                history.setText(Double.toString(result));
                currentNumAndOp.removeAll(currentNumAndOp);
                currentNumAndOp.add(Double.toString(result));
            }
            // If function, display "ERROR" (you can't enter an operator and then square/SQRT/factorial it) //
            else if (name.equals("x^2") || name.equals("√") || name.equals("x!") ||
                    name.equals("sin(x)") || name.equals("cos(x)") || name.equals("tan(x)") || 
                    name.equals("arcsin(x)") || name.equals("arccos(x)") || name.equals("arctan(x)") || 
                    name.equals("e^x") || name.equals("ln(x)") || name.equals("+/-") ||
                    name.equals("log10(x)") || name.equals("10^x")) {

                currentNumAndOp = new ArrayList<String>();
                listStack.clear();
                listStack.push(currentNumAndOp);
                history.setText("");
                field.setText("ERROR");
            }
            // π Case //
            else if (name.equals("\u03C0")) {
                currentNumAndOp.add(beforeText);
                String aftertext = Double.toString(Math.PI);
                field.setText(aftertext);
                history.setText(historyText+aftertext);
            }
            // e Case //
            else if (name.equals("e")) {
                currentNumAndOp.add(beforeText);
                String aftertext = Double.toString(Math.E);
                field.setText(aftertext);
                history.setText(historyText+aftertext);
            }
            // Open Parenthesis //
            else if (name.equals("(")) {
                currentNumAndOp.add(beforeText);
                // Add new ArrayList to stack & move pointer    //
                ArrayList<String> newNumAndOp = new ArrayList<String>();
                listStack.push(newNumAndOp);
                currentNumAndOp = newNumAndOp;
                field.setText("0");
                history.setText(historyText+name);
            }
            // Close Parenthesis //
            else if (name.equals(")")) {
                double result = calc(currentNumAndOp);
                field.setText(Double.toString(result));
                // Remove ArrayList from stack & move pointer   //
                currentNumAndOp.removeAll(currentNumAndOp);
                listStack.pop();
                currentNumAndOp = listStack.peek();
                history.setText(historyText+name);
            }
            // Else, add the operator to the ArrayList and display new number //
            else {
                currentNumAndOp.add(beforeText);
                field.setText(name);
                history.setText(historyText+name);
            }
        }
        
    }
    /**
     * A function that takes an input ArrayList<String> of doubles and operators and returns the reduced result
     * @param al
     * @return
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
     * Returns d!
     * @param d
     * @return
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
