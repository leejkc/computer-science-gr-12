import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class U1A1_LeeCook {
    public static void main(String[] args) {
        JFrame window = new JFrame("Pizza Price Calculator");
        window.setSize(300,250);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLayout(new GridLayout(4, 0));
        window.setResizable(false);

        JLabel sizeLabel = new JLabel("What is the size of your pizza?");
        JTextField sizeTextField = new JTextField();
        JButton calculateButton = new JButton("Calculate");
        JTextArea answerTextArea = new JTextArea();
        answerTextArea.setEditable(false);

        window.add(sizeLabel);
        window.add(sizeTextField);
        window.add(calculateButton);
        window.add(answerTextArea);

        calculateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                try {
                    double size = Double.parseDouble(sizeTextField.getText());
                    double result = (1 + 1.5) + (0.05 * size); // adds the 2 fixed cost and adds the material cost per inch
                    answerTextArea.setText(String.format("%.2f", result)); // displays output with 2 decimals
                } catch (NumberFormatException ex) {
                    answerTextArea.setText("Please enter a valid number."); // invalid inpt handling
                }
            }
        });
        window.setVisible(true);
    }
}
