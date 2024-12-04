package U2A3_LeeCook;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class App extends JFrame{
    final java.awt.Font font = new java.awt.Font("Consolas", java.awt.Font.BOLD, 15); // font settings
    final java.awt.Color textBackgroudColor = new java.awt.Color(213, 234, 245); // background color for text fields

    final JTextField name, cat, am, minimumAM, vendor, markup, discount; // cat means catagory, am means amount

    Path filePath = Paths.get("U2A3_LeeCook/inventory.txt"); // path to the inventory file.
    App(){
        setSize(600,600); // set window size
        setTitle("Inventory Manager"); // set window title
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // close operation for the frame
        setResizable(false); // make window non-resizable
        getContentPane().setBackground(new java.awt.Color(125, 180, 209)); // background color of the content pane
        setLayout(null); // absolute positioning layout

        name = createLabelAndTextField("Name: ", 30, 60);
        cat = createLabelAndTextField("Catagory: ", 30, 90);
        am = createLabelAndTextField("Amount: ", 30, 120);
        minimumAM = createLabelAndTextField("Minimum: ", 30, 150);
        vendor = createLabelAndTextField("Vendor: ", 30, 180);
        markup = createLabelAndTextField("Markup: ", 30, 210);
        discount = createLabelAndTextField("Discount: ", 30, 240);

        setVisible(true);
    }

    /**
     * @param labelText the label text for the JLabel
     * @param x the x position for the JLabel
     * @param y the y position for the JLabel
     * @return textField -> JTextField
     * @see JLabel
     * @see JTextField
     */
    private JTextField createLabelAndTextField(String labelText, int x, int y) {
        JLabel label = new JLabel(labelText); // create label
        label.setBounds(x, y, 130, 20); 
        label.setFont(font);
        label.setBackground(textBackgroudColor); 
        add(label);
        
        JTextField textField = new JTextField(); // create text field
        textField.setBounds(x + 100, y - 2, 150, 20);
        textField.setFont(font);
        textField.setBackground(textBackgroudColor); 
        add(textField); 
        
        return textField; // return the created text field
    }

    public static void main(String[] args) {
        new App();
    }
}