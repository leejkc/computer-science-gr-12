import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class U1A7_LeeCook {
    public static void main(String[] args) {
        //create a new window
        JFrame window = new JFrame("String Playground");
        window.setSize(450, 400);// set size of the window
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// close the app when the window is closed
        window.setResizable(false); // dont let the window be resized
        window.setLayout(null); // use null layout for custom placement
        window.getContentPane().setBackground(new java.awt.Color(254,226,127));//make the background office yellow
        //i made the background yellow because i thought it would be funny and creative

        // title label
        JLabel title = new JLabel("String Playground");
        title.setFont(new Font("Arial", Font.BOLD, 32)); // set font and size
        title.setBounds(30, 20, 300, 40); // position it
        window.add(title);

        // instructions label
        JLabel instructions = new JLabel("Please enter a string:");
        instructions.setBounds(30, 80, 300, 30); // position it
        window.add(instructions);

        // text field for user input
        JTextField textInput = new JTextField();
        textInput.setBounds(30, 120, 380, 30); // position it
        window.add(textInput);

        // button to submit answer
        // i think analyze makes it seem more cool and professional
        JButton analyzeButton = new JButton("Analyze");
        analyzeButton.setBounds(170, 160, 100, 30); // position it
        window.add(analyzeButton);

        // text area to show results
        JTextArea resultArea = new JTextArea();
        resultArea.setEditable(false); // make it noneditable for the user
        resultArea.setBounds(30, 200, 380, 150); // position it
        window.add(resultArea);

        // action listener for the button
        analyzeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String input = textInput.getText(); // get the text from the input field

                // keep asking for input if it's empty
                if (input.length() == 0) {
                    JOptionPane.showMessageDialog(window, "Please enter a string!"); // show a message
                } else {
                    // reverse the string
                    String reversed = new StringBuilder(input).reverse().toString();
                    int length = input.length(); // get the length of the string
                    int spaces = input.length() - input.replace(" ", "").length(); // count blank spaces
                    int[] vowelsCount = new int[5]; // array to hold counts for a, e, i, o, u

                    // loop through each character in the input
                    for (char c : input.toLowerCase().toCharArray()) {
                        switch (c) {
                            case 'a': vowelsCount[0]++; break; // count 'a'
                            case 'e': vowelsCount[1]++; break; // count 'e'
                            case 'i': vowelsCount[2]++; break; // count 'i'
                            case 'o': vowelsCount[3]++; break; // count 'o'
                            case 'u': vowelsCount[4]++; break; // count 'u'
                        }
                    }

                    // prepare the result to show
                    String result = "Reversed String: " + reversed + "\n" +
                                    "Length of String: " + length + "\n" +
                                    "Number of Blank Spaces: " + spaces + "\n" +
                                    "Vowel Counts:\n" +
                                    "A: " + vowelsCount[0] + "\n" +
                                    "E: " + vowelsCount[1] + "\n" +
                                    "I: " + vowelsCount[2] + "\n" +
                                    "O: " + vowelsCount[3] + "\n" +
                                    "U: " + vowelsCount[4];

                    // show the result in the text area
                    resultArea.setText(result);
                }
            }
        });

        // show everything
        window.setVisible(true);
    }
}