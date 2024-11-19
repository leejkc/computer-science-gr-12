import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class U1A2_LeeCook{
    public static void main(String[] args){
        JFrame window = new JFrame("Change Exchange");
        window.setSize(450,250);
        window.setLayout(new GridLayout(0, 3));
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);

        JLabel amountLabel = new JLabel("Enter Currency:");
        JTextField amountTextField = new JTextField();
        JButton calculateButton = new JButton("Calculate for me");
        JLabel answerLabel = new JLabel("Change:");
        JTextArea answerTextArea = new JTextArea();
        answerTextArea.setEditable(false);
        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(_ -> System.exit(0));

        window.add(amountLabel);
        window.add(amountTextField);
        window.add(calculateButton);
        window.add(answerLabel);
        window.add(answerTextArea);
        window.add(exitButton);

        calculateButton.addActionListener(new ActionListener() {
            @Override 
            public void actionPerformed(ActionEvent e){
                try{
                    double input = Double.parseDouble(amountTextField.getText());
                    int totalCents = (int) Math.round((input) * 100);
                    
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
                    //leftover pennies
                    int pennies = totalCents;

                    String result = String.format("Toonies: %d\nLoonies: %d\nQuarters: %d\nDimes: %d\nNickels: %d\nPennies: %d",
                            toonies, loonies, quarters, dimes, nickels, pennies);
                    answerTextArea.setText(result);
                } catch (NumberFormatException ex) { //handling errors/ letters put in
                    answerTextArea.setText("Please enter\na valid number.");
                }
            }
        });
        //display window
        window.setVisible(true);
    }
}