package U2A2_LeeCook;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class App extends JFrame {
    final java.awt.Font font = new java.awt.Font("Consolas", java.awt.Font.BOLD, 15); // font settings
    final java.awt.Color textBackgroudColor = new java.awt.Color(213, 234, 245); // background color for text fields

    // final JTextField

    App(){
        setSize(700,600); // set window size
        setTitle("Employee Records App"); // set window title
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // close operation for the frame
        setResizable(false); // make window non-resizable
        getContentPane().setBackground(new java.awt.Color(125, 180, 209)); // background color of the content pane
        setLayout(null); // absolute positioning layout
        
        // TODO insert gui elements and action listeners here

        setVisible(true);
    }
    
    /**
     * Creates a JLabel and a JTextField, arranges them on the screen, and returns the JTextField.
     * <p>
     * This method creates a label with the specified text and a corresponding text field positioned next to it.
     * It sets the label's text and the text field's font and background color, which are predefined globally.
     * The method also ensures that both components are added to the frame.
     * </p>
     *
     * @param labelText the text to display on the JLabel.
     * @param x the x position of the label.
     * @param y the y position of the label.
     * @return the created {@code JTextField}, which is positioned next to the label.
     * @see JLabel
     * @see JTextField
     */
    private JTextField createElements(String labelText, int x, int y) {
        JLabel label = new JLabel(labelText); // create label
        label.setBounds(x, y, 130, 20); // set position of label
        label.setFont(font); // set font for the label
        label.setBackground(textBackgroudColor); // set background color for the label
        add(label); // add label to the frame
        
        JTextField textField = new JTextField(); // create text field
        textField.setBounds(x + 130, y - 2, 150, 20); // set position of text field
        textField.setFont(font); // set font for the text field
        textField.setBackground(textBackgroudColor); // set background color for the text field
        add(textField); // add text field to the frame
        
        return textField; // return the created text field
    }

    public static void main(String[] args){
        new App(); // create and display a new instance of the app class
    }
}