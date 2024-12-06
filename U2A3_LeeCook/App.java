package U2A3_LeeCook;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class App extends JFrame{
    final java.awt.Font font = new java.awt.Font("Consolas", java.awt.Font.BOLD, 15); // font settings
    final java.awt.Color textBackgroudColor = new java.awt.Color(213, 234, 245); // background color for text fields

    final JTextField name, cat, am, minimumAm, vendor, markup, discount; // cat means catagory, am means amount

    App(){
        setSize(600,600); // set window size
        setTitle("Inventory Manager"); // set window title
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // close operation for the frame
        setResizable(false); // make window non-resizable
        getContentPane().setBackground(new java.awt.Color(125, 180, 209)); // background color of the content pane
        setLayout(null); // absolute positioning layout

        JLabel header = new JLabel("Inventory Manager");
        header.setBounds(30, 10, 300, 40);
        header.setFont(new java.awt.Font("Consolas", java.awt.Font.BOLD, 30));
        add(header);

        name = createLabelAndTextField("Name: ", 30, 60);
        cat = createLabelAndTextField("Catagory: ", 30, 90);
        am = createLabelAndTextField("Amount: ", 30, 120);
        minimumAm = createLabelAndTextField("Minimum: ", 30, 150);
        vendor = createLabelAndTextField("Vendor: ", 30, 180);
        markup = createLabelAndTextField("Markup: ", 30, 210);
        discount = createLabelAndTextField("Discount: ", 30, 240);
        
        JButton submit = new JButton("Submit Fields");
        submit.setBounds(30, 270, 250, 20);
        submit.setFont(font);
        submit.setBackground(textBackgroudColor);
        add(submit);
        submit.addActionListener(_->{
            ItemData.insertData(name.getText(), cat.getText(), am.getText(), minimumAm.getText(), vendor.getText(), markup.getText(), discount.getText());
        });

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