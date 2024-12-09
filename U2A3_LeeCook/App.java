package U2A3_LeeCook;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class App extends JFrame{
    final java.awt.Font font = new java.awt.Font("Consolas", java.awt.Font.BOLD, 15); // font settings
    final java.awt.Color textBackgroudColor = new java.awt.Color(213, 234, 245); // background color for text fields

    final JTextField name, cat, am, minimumAm, vendor, markup, discount; // cat means catagory, am means amount
    final JTextArea output;
    final JComboBox optionDropDown;

    App(){
        setSize(600,600); // set window size
        setTitle("Inventory Manager"); // set window title
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // close operation for the frame
        setResizable(false); // make window non-resizable
        getContentPane().setBackground(new java.awt.Color(125, 180, 209)); // background color of the content pane
        setLayout(null); // absolute positioning layout

        final JLabel header = new JLabel("Inventory Manager");
        header.setBounds(30, 10, 300, 40);
        header.setFont(new java.awt.Font("Consolas", java.awt.Font.BOLD, 30));
        add(header);

        // creates label and textfields
        name = createLabelAndTextField("Name: ", 30, 60);
        cat = createLabelAndTextField("Catagory: ", 30, 90);
        am = createLabelAndTextField("Amount: ", 30, 120);
        minimumAm = createLabelAndTextField("Minimum: ", 30, 150);
        vendor = createLabelAndTextField("Vendor: ", 30, 180);
        markup = createLabelAndTextField("Markup: ", 30, 210);
        discount = createLabelAndTextField("Discount: ", 30, 240);
        
        // JTextArea for output results.
        output = new JTextArea();
        output.setEditable(false);
        output.setBounds(300, 60, 250, 300);
        output.setFont(font);
        output.setBackground(textBackgroudColor);
        add(output);

        // JComboBox for options once submitted text.
        optionDropDown = new JComboBox<>();
        optionDropDown.setBounds(30, 300, 250, 20);
        optionDropDown.addItemListener(null);
        add(optionDropDown);
        optionDropDown.setVisible(false);

        // button to select option from dropdown
        final JButton selectOption = new JButton();
        selectOption.setBounds(30, 330, 250, 20);
        selectOption.setFont(font);
        selectOption.setBackground(textBackgroudColor);
        add(selectOption);
        selectOption.setVisible(false);
        selectOption.addActionListener(_->{
            int i = optionDropDown.getSelectedIndex();
        });

        // button to submit textfields
        final JButton submit = new JButton("Submit Fields");
        submit.setBounds(30, 270, 250, 20);
        submit.setFont(font);
        submit.setBackground(textBackgroudColor);
        add(submit);
        submit.addActionListener(_->{
            optionDropDown.setVisible(ItemData.checkData(name.getText(), cat.getText().toUpperCase(), am.getText(), minimumAm.getText(), vendor.getText(), markup.getText(), discount.getText()));
            selectOption.setVisible(ItemData.checkData(name.getText(), cat.getText().toUpperCase(), am.getText(), minimumAm.getText(), vendor.getText(), markup.getText(), discount.getText()));
            // makes dropdown and button visible if all textfields are not empty
            revalidate();
            repaint();
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
        final JLabel label = new JLabel(labelText); // create label
        label.setBounds(x, y, 130, 20); 
        label.setFont(font);
        add(label);
        
        final JTextField textField = new JTextField(); // create text field
        textField.setBounds(x + 100, y, 150, 20);
        textField.setFont(font);
        textField.setBackground(textBackgroudColor); 
        add(textField); 
        
        return textField; // return the created text field
    }

    public static void main(String[] args) {
        new App();
    }
}