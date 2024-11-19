import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class U1A8_LeeCook {
    public static void main(String[] args) {
        // making a new window
        JFrame window = new JFrame("secret message");
        window.setSize(450, 400); // setting the size of the window
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // closing the app when the window is closed
        window.setResizable(false); // making sure the window can’t be resized
        window.setLayout(null); // using no layout for custom placement
        window.getContentPane().setBackground(new java.awt.Color(188, 204, 122)); // making the background green

        // title label
        JLabel title = new JLabel("Secret Message!");
        title.setFont(new Font("Arial", Font.BOLD, 26)); // setting font and size
        title.setBounds(30, 20, 500, 40); // positioning
        window.add(title);

        // instructions label
        JLabel instructions = new JLabel("please enter a string: (max 15 characters)");
        instructions.setBounds(30, 70, 300, 30); // positioning
        window.add(instructions);

        // text field for user input
        JTextField textInput = new JTextField();
        textInput.setBounds(30, 100, 380, 30); // positioning
        window.add(textInput);

        // button to submit the input
        JButton convertButton = new JButton("convert");
        convertButton.setBounds(170, 140, 100, 30); // positioning
        window.add(convertButton);

        // adding labels for ascii, binary, octal, and hex values
        JLabel asciiLabel = new JLabel("ascii:");
        asciiLabel.setBounds(30, 180, 300, 30); // positioning
        window.add(asciiLabel);
        JLabel binaryLabel = new JLabel("binary:");
        binaryLabel.setBounds(30, 220, 300, 30); // positioning
        window.add(binaryLabel);
        JLabel octalLabel = new JLabel("octal:");
        octalLabel.setBounds(30, 260, 300, 30); // positioning
        window.add(octalLabel);
        JLabel hexLabel = new JLabel("hexadecimal:");
        hexLabel.setBounds(30, 300, 300, 30); // positioning
        window.add(hexLabel);

        // making non-editable text fields for showing results
        JTextField asciiJTextField = new JTextField();
        asciiJTextField.setBounds(130, 180, 280, 30); // positioning
        asciiJTextField.setEditable(false);
        window.add(asciiJTextField);
        JTextField binaryJTextField = new JTextField();
        binaryJTextField.setBounds(130, 220, 280, 30); // positioning
        binaryJTextField.setEditable(false);
        window.add(binaryJTextField);
        JTextField octalJTextField = new JTextField();
        octalJTextField.setBounds(130, 260, 280, 30); // positioning
        octalJTextField.setEditable(false);
        window.add(octalJTextField);
        JTextField hexJTextField = new JTextField();
        hexJTextField.setBounds(130, 300, 280, 30); // positioning
        hexJTextField.setEditable(false);
        window.add(hexJTextField);

        // action listener for the convert button
        convertButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String inputText = textInput.getText();
                
                // checking if input length is okay
                if (inputText.length() > 15) {
                    JOptionPane.showMessageDialog(window, "message exceeds 15 characters. please try again.");
                    return;
                }

                // using regular strings instead of stringbuilder
                String asciiValues = "";
                String binaryValues = "";
                String octalValues = "";
                String hexValues = "";

                // going through each character
                for (char c : inputText.toCharArray()) {
                    int ascii = (int) c;
                    asciiValues += ascii + " "; // adding ascii value
                    binaryValues += decimalToBinary(ascii) + " "; // adding binary value
                    octalValues += decimalToOctal(ascii) + " "; // adding octal value
                    hexValues += decimalToHex(ascii) + " "; // adding hexadecimal value
                }

                // showing the results
                asciiJTextField.setText(asciiValues.trim());
                binaryJTextField.setText(binaryValues.trim());
                octalJTextField.setText(octalValues.trim());
                hexJTextField.setText(hexValues.trim());
                // the .trim() method i use here cuts off any spaces at the beginning or end of a string
            }
        });

        // making the window visible
        window.setVisible(true);
    }

    // method to convert decimal to binary
    public static String decimalToBinary(int num) {
        String binary = "";
        while (num > 0) {
            binary = (num % 2) + binary; // putting the binary digit at the front
            num /= 2;
        }
        return binary;
    }

    // method to convert decimal to octal
    public static String decimalToOctal(int num) {
        String octal = "";
        while (num > 0) {
            octal = (num % 8) + octal; // putting the octal digit at the front
            num /= 8;
        }
        return octal;
    }

    // method to convert decimal to hexadecimal
    public static String decimalToHex(int num) {
        String hex = "";
        char[] hexChars = "0123456789ABCDEF".toCharArray();
        while (num > 0) {
            hex = hexChars[num % 16] + hex; // putting the hex digit at the front
            num /= 16;
        }
        return hex;
    }
}