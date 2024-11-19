import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import java.text.DecimalFormat;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

//change: what the cash register has to give back
//amount given: what the person gave to pay it off
//amount owing: how much what their buying costs them

public class U1A3_LeeCookREDO{
    public static void main(String[] args){
        //DecimalFormat format = new DecimalFormat("#0.00");

        JFrame window = new JFrame("Change Exchange");
        window.setSize(550,370);
        window.setLayout(new GridLayout(0, 5));
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);

        JLabel amountLabel = new JLabel("Amount Given:");
        JTextField amountTextField = new JTextField();
        JButton calculateButton = new JButton("Calculate for me");
        JLabel answerLabel = new JLabel("Change:");
        JTextArea answerTextArea = new JTextArea();
        answerTextArea.setEditable(false);
        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(_ -> System.exit(0));
        JLabel amountOwingLabel = new JLabel("Amount Owing:");
        JTextField amountOwingTextField = new JTextField();
        JLabel changeLabel = new JLabel("Change:");
        JTextArea changeTextArea = new JTextArea();       
        changeTextArea.setEditable(false);

        window.add(amountLabel);
        window.add(amountTextField);
        window.add(answerLabel); // this is also a change var
        window.add(answerTextArea); //this one too
        window.add(calculateButton);

        window.add(amountOwingLabel);
        window.add(amountOwingTextField);
        window.add(changeLabel);
        window.add(changeTextArea);
        window.add(exitButton);

        calculateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                try{
                    double amounttf = Double.parseDouble(amountTextField.getText());
                    double amountotf = Double.parseDouble(amountOwingTextField.getText());
                    int totalCents = (int) Math.round((amounttf-amountotf) *20) *5;
                    
                    answerTextArea.setText(String.valueOf(((double)totalCents)/100));

                    //hundreddollar
                    int hundreddollar = totalCents / 10000;
                    totalCents %= 10000;
                    //fiftydollar
                    int fiftydollar = totalCents / 5000;
                    totalCents %= 5000;
                    //twentydollar
                    int twentydollar = totalCents / 2000;
                    totalCents %= 2000;
                    //tendollar
                    int tendollar = totalCents / 1000;
                    totalCents %= 1000;
                    //fivedollar
                    int fivedollar = totalCents / 500;
                    totalCents %= 500;
                    //toonies
                    int toonies = totalCents / 200;
                    totalCents %= 200;
                    //loonies
                    int loonies = totalCents / 100;
                    totalCents %= 100;
                    //quarters
                    int quarters = totalCents / 25;
                    totalCents %= 25;
                    //dimes
                    int dimes = totalCents / 10;
                    totalCents %= 10;
                    //nickels
                    int nickels = totalCents / 5;
                    totalCents %= 5;

                    String result = String.format("One Hundred Bill: %d\nFifty Dollar Bill: %d\nTwenty Dollar Bill: %d\nTen Dollar Bill: %d\nFive Dollar Bill: %d\nToonies: %d\nLoonies: %d\nQuarters: %d\nDimes: %d\nNickels: %d",
                            hundreddollar, fiftydollar, twentydollar, tendollar, fivedollar, toonies, loonies, quarters, dimes, nickels);
                    changeTextArea.setText(result);
                } catch (NumberFormatException ex) { //handling errors/ letters put in
                    changeTextArea.setText("Please enter\na valid number.");
                }
            }
        });
        //display window
        window.setVisible(true);
    }
}