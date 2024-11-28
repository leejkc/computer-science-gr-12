package U2A2_LeeCook;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class App extends JFrame {
    final java.awt.Font font = new java.awt.Font("Consolas", java.awt.Font.BOLD, 15); // font settings
    final java.awt.Color textBackgroudColor = new java.awt.Color(213, 234, 245); // background color for text fields

    // final JCheckBox rectangle, square, triangle, circle, doughnut, hexagon, parallelogram;

    App(){
        setSize(700,600); // set window size
        setTitle("Employee Records App"); // set window title
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // close operation for the frame
        setResizable(false); // make window non-resizable
        getContentPane().setBackground(new java.awt.Color(125, 180, 209)); // background color of the content pane
        setLayout(null); // absolute positioning layout
        
        JLabel header = new JLabel("Tile Price Calculator"); // create a header label
        header.setFont(new java.awt.Font("Consolas", java.awt.Font.BOLD, 30)); // set font for header
        header.setBounds(30, 40, 450, 50); // set position of the header label
        add(header); // add header to the frame

        setVisible(true);
    }
    
    /**
     * Creates a JLabel and a JCheckBox, arranges them on the screen, and returns the JCheckBox.
     * <p>
     * This method creates a check box and a label together and displays them on screen.
     * This method also sets font and background color.
     * </p>
     *
     * @param labelText the text to display on the JLabel.
     * @param x the x position of the label.
     * @param y the y position of the label.
     * @return the created {@code JCheckBox}, which is positioned next to the label.
     * @see JLabel
     * @see JCheckBox
     */
    private JCheckBox createElements(String labelText, int x, int y) {
        JCheckBox checkBox = new JCheckBox(); // create check box
        checkBox.setBounds(x, y, 150, 20); // set position of check box
        checkBox.setFont(font); // set font for the check box
        checkBox.setBackground(textBackgroudColor); // set background color for the check box
        add(checkBox); // add check box to the frame

        JLabel label = new JLabel(labelText); // create label
        label.setBounds(x + 40, y, 130, 20); // set position of label
        label.setFont(font); // set font for the label
        label.setBackground(textBackgroudColor); // set background color for the label
        add(label); // add label to the frame

        return checkBox; // return the created text field
    }

    public static void main(String[] args){
        new App(); // create and display a new instance of the app class
    }
}