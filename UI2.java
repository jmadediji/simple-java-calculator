/**
 * @name        Simple Java Calculator
 * @package     ph.calculator
 * @file        UI.java
 * @author      SORIA Pierre-Henry
 * @email       pierrehs@hotmail.com
 * @link        http://github.com/pH-7
 * @copyright   Copyright Pierre-Henry SORIA, All Rights Reserved.
 * @license     Apache (http://www.apache.org/licenses/LICENSE-2.0)
 * @create      2012-03-30
 *
 * @modifiedby  Achintha Gunasekara
 * @modifiedby  Kydon Chantzaridis
 * @modweb      http://www.achinthagunasekara.com
 * @modemail    contact@achinthagunasekara.com
 * @modemail    kchantza@csd.auth.gr
 */

package simplejavacalculator;

import java.awt.Dimension;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import java.awt.Font;
import javax.swing.Box;
import javax.swing.BoxLayout;

import javax.swing.ImageIcon; 
import java.io.*;
public class UI implements ActionListener {
   
   private final JFrame frame;
   
   private final JPanel panel;
   private final JPanel panelSub1;
   private final JPanel panelSub2;
   private final JPanel panelSub3;
   private final JPanel panelSub4;
   private final JPanel panelSub5;
   private final JPanel panelSub6;
   private final JPanel panelSub7;
   private final JPanel panelSub8;
   private final JPanel panelSub9;
   
   private final JTextArea text;
   private final JButton but[], 
   butSquareRoot, butAbs, butSin, butCos, butTan,
   butOneDividedBy, butLog, butSinh, butCosh, butTanh,
   butxpowerofy, butPercent, butCancel, butBack, butAdd,
   butCubed, butMinus,
   butSquare, butMultiply,
   butFactorial, butDivide,
   butLeftPar, butRightPar, butDot, butEqual,
   butDecToFrac;
   
   private final Calculator calc;
   
   private final String[] buttonValue = {"0", "1", "2", "3", "4", "5", "6",
      "7", "8", "9"};
   
   private final Font font;
   private final Font textFont;
   private final Dimension dimension;
   private ImageIcon image;
   private BufferedImageCustom imageReturn;
   
   public UI() throws IOException {
      frame = new JFrame("Calculator PH");
      
      imageReturn = new BufferedImageCustom();
      image = new ImageIcon(imageReturn.imageReturn());      
      
      panel = new JPanel();
      panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
      panelSub1 = new JPanel(new FlowLayout());
      panelSub2 = new JPanel(new FlowLayout());
      panelSub3 = new JPanel(new FlowLayout());
      panelSub4 = new JPanel(new FlowLayout());
      panelSub5 = new JPanel(new FlowLayout());
      panelSub6 = new JPanel(new FlowLayout());
      panelSub7 = new JPanel(new FlowLayout());
      panelSub8 = new JPanel(new FlowLayout());
      panelSub9 = new JPanel(new FlowLayout());
      
      font = new Font("Consolas",Font.PLAIN, 10);
      
      text = new JTextArea(1, 30);
      
      textFont = new Font("Consolas",Font.BOLD, 24);
      
      but = new JButton[10];      

      dimension = new Dimension(60, 40);

      for (int i = 0; i < 10; i++) {
    		 but[i] = new JButton(String.valueOf(i));
          but[i].setPreferredSize(dimension);
      }  
         
      butSquareRoot = new JButton("√"); butSquareRoot.setPreferredSize(dimension);
      butAbs = new JButton("|x|"); butAbs.setPreferredSize(dimension);
      butSin = new JButton("Sin"); butSin.setPreferredSize(dimension);
      butCos = new JButton("Cos"); butCos.setPreferredSize(dimension);
      butTan = new JButton("Tan"); butTan.setPreferredSize(dimension);
      butOneDividedBy = new JButton("1/x"); butOneDividedBy.setPreferredSize(dimension);
      butLog = new JButton("Log"); butLog.setPreferredSize(dimension);
      butSinh = new JButton("Sinh"); butSinh.setPreferredSize(dimension);
      butCosh = new JButton("Cosh"); butCosh.setPreferredSize(dimension);
      butTanh = new JButton("Tanh"); butTanh.setPreferredSize(dimension);
      butxpowerofy = new JButton("x^y"); butxpowerofy.setPreferredSize(dimension);
      butPercent = new JButton("%"); butPercent.setPreferredSize(dimension);
      butCancel = new JButton("AC"); butCancel.setPreferredSize(dimension);
      butBack = new JButton("C"); butBack.setPreferredSize(dimension);
      butAdd = new JButton("+"); butAdd.setPreferredSize(dimension);
      butCubed = new JButton("x^3"); butCubed.setPreferredSize(dimension);
      butMinus = new JButton("-"); butMinus.setPreferredSize(dimension);
      butSquare = new JButton("x^2"); butSquare.setPreferredSize(dimension);
      butMultiply = new JButton("*"); butMultiply.setPreferredSize(dimension);
      butFactorial = new JButton("x!"); butFactorial.setPreferredSize(dimension);
      butDivide = new JButton("/"); butDivide.setPreferredSize(dimension);
      butLeftPar = new JButton("("); butLeftPar.setPreferredSize(dimension);
      butRightPar = new JButton(")"); butRightPar.setPreferredSize(dimension);
      butDot = new JButton("."); butDot.setPreferredSize(dimension);
      butEqual = new JButton("="); butEqual.setPreferredSize(dimension);
      butDecToFrac = new JButton("d->f"); butDecToFrac.setPreferredSize(dimension);

      calc = new Calculator();
      
   }
   
   public void init() {      
      frame.setSize(500, 450);
      frame.setLocationRelativeTo(null); 
      frame.setResizable(false);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setIconImage(image.getImage());
      
      text.setFont(textFont);
      text.setEditable(false);
      
      for (int i = 0; i < 10; i++) {
         but[i].setFont(font);
      }      

      butSquareRoot.setFont(font);
      butAbs.setFont(font);
      butSin.setFont(font);
      butCos.setFont(font);
      butTan.setFont(font);
      butOneDividedBy.setFont(font);
      butLog.setFont(font);
      butSinh.setFont(font);
      butCosh.setFont(font);
      butTanh.setFont(font);
      butxpowerofy.setFont(font);
      butPercent.setFont(font);
      butCancel.setFont(font);
      butBack.setFont(font);
      butAdd.setFont(font);
      butCubed.setFont(font);
      butMinus.setFont(font);
      butSquare.setFont(font);
      butMultiply.setFont(font);
      butFactorial.setFont(font);
      butDivide.setFont(font);
      butLeftPar.setFont(font);
      butRightPar.setFont(font);
      butDot.setFont(font);
      butEqual.setFont(font);
      butDecToFrac.setFont(font);
      
      panel.add(Box.createHorizontalStrut(100));
      panelSub1.add(text);
      panel.add(panelSub1);
      
      panelSub2.add(butSquareRoot);
      panelSub2.add(butAbs);
      panelSub2.add(butSin);
      panelSub2.add(butCos);
      panelSub2.add(butTan);
      panel.add(panelSub2);
      
      panelSub3.add(butOneDividedBy);
      panelSub3.add(butLog);
      panelSub3.add(butSinh);
      panelSub3.add(butCosh);
      panelSub3.add(butTanh);
      panel.add(panelSub3);
      
      panelSub4.add(butxpowerofy);
      panelSub4.add(butPercent);
      panelSub4.add(butCancel);
      panelSub4.add(butBack);
      panelSub4.add(butAdd);
      panel.add(panelSub4);
      
      panelSub5.add(butCubed);
      panelSub5.add(but[7]);
      panelSub5.add(but[8]);
      panelSub5.add(but[9]);
      panelSub5.add(butMinus);
      panel.add(panelSub5);
      
      panelSub6.add(butSquare);
      panelSub6.add(but[4]);
      panelSub6.add(but[5]);
      panelSub6.add(but[6]);
      panelSub6.add(butMultiply);
      panel.add(panelSub6);
      


      panelSub8.add(butLeftPar);
      panelSub8.add(butRightPar);
      panelSub8.add(but[0]);
      panelSub8.add(butDot);
      panelSub8.add(butEqual);
      
      panel.add(panelSub7);
      
      
      panelSub7.add(butFactorial);
      panelSub7.add(but[1]);
      panelSub7.add(but[2]);
      panelSub7.add(but[3]);
      panelSub7.add(butDivide);
      panel.add(panelSub8);

      panelSub9.add(butDecToFrac);
      panel.add(panelSub9);
      
      for (int i = 0; i < 10; i++) {
         but[i].addActionListener(this);
      }     

      butSquareRoot.addActionListener(this);
      butAbs.addActionListener(this);
      butSin.addActionListener(this);
      butCos.addActionListener(this);
      butTan.addActionListener(this);
      butOneDividedBy.addActionListener(this);
      butLog.addActionListener(this);
      butSinh.addActionListener(this);
      butCosh.addActionListener(this);
      butTanh.addActionListener(this);
      butxpowerofy.addActionListener(this);
      butPercent.addActionListener(this);
      butCancel.addActionListener(this);
      butBack.addActionListener(this);
      butAdd.addActionListener(this);
      butCubed.addActionListener(this);
      butMinus.addActionListener(this);
      butSquare.addActionListener(this);
      butMultiply.addActionListener(this);
      butFactorial.addActionListener(this);
      butDivide.addActionListener(this);
      butLeftPar.addActionListener(this);
      butRightPar.addActionListener(this);
      butDot.addActionListener(this);
      butEqual.addActionListener(this);
      butDecToFrac.addActionListener(this);
      
      frame.add(panel);
      frame.setVisible(true);
   }
   
   @Override
   public void actionPerformed(ActionEvent e) {
      final Object source = e.getSource();
      Double checkNum = null;

      for (int i = 0; i < 10; i++) {
         if (source == but[i]) {
            text.replaceSelection(buttonValue[i]);
            return;
         }
      }

      try {
         checkNum = Double.parseDouble(text.getText().replace("/", ".")); // important piece of code
      } catch(NumberFormatException k) {
         System.out.println(text.getText() + ": Not a number");
      }

      if (checkNum != null || source == butCancel) {
         
         if (source == butSquareRoot) {
            calc.calculateMono(Calculator.MonoOperatorModes.squareRoot, reader());
         }

         if (source == butAbs) {
            calc.calculateMono(Calculator.MonoOperatorModes.abs, reader());
         }

         if (source == butSin) {
            calc.calculateMono(Calculator.MonoOperatorModes.sin, reader());
         }

         if (source == butCos) {
            calc.calculateMono(Calculator.MonoOperatorModes.cos, reader());
         }

         if (source == butTan) {
            calc.calculateMono(Calculator.MonoOperatorModes.tan, reader());
         }

         if (source == butOneDividedBy) {
            calc.calculateMono(Calculator.MonoOperatorModes.oneDividedBy, reader());
         }

         if (source == butLog) {
            calc.calculateMono(Calculator.MonoOperatorModes.log, reader());
         }

         if (source == butSinh) {
            calc.calculateMono(Calculator.MonoOperatorModes.sinh, reader());
         }

         if (source == butCosh) {
            calc.calculateMono(Calculator.MonoOperatorModes.cosh, reader());
         }

         if (source == butTanh) {
            calc.calculateMono(Calculator.MonoOperatorModes.tanh, reader());
         }

         if (source == butxpowerofy) {
            calc.calculateBi(Calculator.BiOperatorModes.xpowerofy, reader());
         }

         if (source == butPercent) {
            calc.calculateMono(Calculator.MonoOperatorModes.percent, reader());
         }

         if (source == butCancel)
            writer(calc.reset());

         if (source == butBack) {
            //implement here

         }

         if (source == butAdd) {
            
            writer(calc.calculateBi(Calculator.BiOperatorModes.add, reader()));
            //text.replaceSelection(butAdd.getText());
         }

         if (source == butCubed) {
            calc.calculateMono(Calculator.MonoOperatorModes.cube, reader());
         }

         if (source == butMinus) {
            calc.calculateBi(Calculator.BiOperatorModes.minus, reader());
            //text.replaceSelection(butMinus.getText());
         }

         if (source == butSquare) {
            calc.calculateMono(Calculator.MonoOperatorModes.square, reader());
         }

         if (source == butMultiply) {
            //change fraction to dec by modifying 
            calc.calculateBi(Calculator.BiOperatorModes.multiply, reader());
            text.replaceSelection(butMultiply.getText());
         }

         if (source == butFactorial) {
            calc.calculateMono(Calculator.MonoOperatorModes.factorial, reader());
         }

         if (source == butDivide) {
            Double n = reader();
            System.out.println(n);
            writer(calc.calculateBi(Calculator.BiOperatorModes.divide, n));
            //text.replaceSelection(butDivide.getText());
         }

         if (source == butLeftPar) {
            text.replaceSelection("(");
         }

         if (source == butRightPar) {
            text.replaceSelection(")");
         }


         if (source == butDot) {
            text.replaceSelection(".");
            return; // how does this work?
         }

         if (source == butEqual) {
            writer(calc.calculateEqual(reader()));
         }

         if (source == butDecToFrac) {
            Double n = reader();
            String[] result = String.valueOf((calc.calculateMono(Calculator.MonoOperatorModes.decToFrac, n).toString())).split("\\.");
            //System.out.println(result);
            text.setText(result[0] + "/" + result[1]);
         }
         

      text.selectAll();
   
   
   }}
   
   public Double reader() {
      Double num;
      String str;
      str = text.getText();
      num = Double.valueOf(str);
      
      return num;
   }
   
   public void writer(final Double num) {
      if (Double.isNaN(num)) {
         text.setText("");
      } else {
         text.setText(Double.toString(num));
      }
   }
}
