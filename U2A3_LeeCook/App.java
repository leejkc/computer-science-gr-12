package U2A3_LeeCook;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class App extends JFrame{
    final java.awt.Font font = new java.awt.Font("Consolas", java.awt.Font.BOLD, 15); // font settings
    final java.awt.Color textBackgroudColor = new java.awt.Color(213, 234, 245); // background color for text fields

    final JTextField id, name, cat, am, minimumAm, vendor, markup, discount; // cat means category, am means amount
    final JTextArea output;
    final JComboBox<String> combobox;

    App(){
        setSize(700,600); // set window size
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
        name = createLabelAndTextField("Name Of Item: ", 30, 60);
        cat = createLabelAndTextField("Category: ", 30, 90);
        am = createLabelAndTextField("Amount In Stock: ", 30, 120);
        minimumAm = createLabelAndTextField("Minimum Amount: ", 30, 150);
        vendor = createLabelAndTextField("Vendor Price: ", 30, 180);
        markup = createLabelAndTextField("Markup %: ", 30, 210);
        discount = createLabelAndTextField("Discounted %: ", 30, 240);
        
        // JTextArea for output results.
        output = new JTextArea();
        output.setEditable(false);
        output.setBounds(400, 60, 250, 300);
        output.setFont(font);
        output.setBackground(textBackgroudColor);
        add(output);

        JScrollPane scrollPane = new JScrollPane(output, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBounds(400, 60, 250, 300); // set position of the scroll pane
        add(scrollPane); // add scroll pane to the frame

        // JComboBox for options once submitted text.
        String[] items = {"Search in file", "Write to file", "Remove from file"};
        combobox = new JComboBox<>(items);
        combobox.setBounds(30, 300, 250, 20);
        combobox.addItemListener(null);
        add(combobox);
        combobox.setVisible(false);

        final JLabel idLabel = new JLabel("ID Of Product"); // create label
        idLabel.setBounds(30, 360, 200, 20); 
        idLabel.setFont(font);
        add(idLabel);
        idLabel.setVisible(false);
        
        id = new JTextField(); // create text field
        id.setBounds(230, 360, 150, 20);
        id.setFont(font);
        id.setBackground(textBackgroudColor); 
        add(id); 
        id.setVisible(false);

        // button to submit textfields
        final JButton idButton = new JButton("Search by ID");
        idButton.setBounds(400, 360, 250, 20);
        idButton.setFont(font);
        idButton.setBackground(textBackgroudColor);
        add(idButton);
        idButton.addActionListener(unused->{
            if (idButton.getText() == "Search by ID"){
                idLabel.setVisible(true);
                id.setVisible(true);
                idButton.setText("Close ID search");
                revalidate();
                repaint();
                return;
            }
            if (idButton.getText() == "Close ID search"){
                idLabel.setVisible(false);
                id.setVisible(false);
                idButton.setText("Search by ID");
                revalidate();
                repaint();
                return;
            }
        });

        // button to select option from dropdown
        final JButton selectOption = new JButton("Select this option");
        selectOption.setBounds(30, 330, 350, 20);
        selectOption.setFont(font);
        selectOption.setBackground(textBackgroudColor);
        add(selectOption);
        selectOption.setVisible(false);
        selectOption.addActionListener(unused->{
            int i = combobox.getSelectedIndex();
            if (i == -1){
                JOptionPane.showMessageDialog(null, "No option selected!\nPlease select a option from the dropdown.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (i == 0){
                if (!cat.getText().isBlank()){
                    output.setText(ItemData.search(cat.getText().toUpperCase()));
                } else if (!id.getText().isBlank()){
                    output.setText(ItemData.search(id.getText()));
                } else {
                    output.setText(ItemData.search(name.getText()));
                }
            }
            if (i == 1){
                ItemData.write(name.getText(), cat.getText().toUpperCase(), am.getText(), minimumAm.getText(), vendor.getText(), markup.getText(), discount.getText());
            }
            if (i == 2){
                ItemData.remove(name.getText());
            }
        });

        // button to submit textfields
        final JButton submit = new JButton("Submit Fields");
        submit.setBounds(30, 270, 350, 20);
        submit.setFont(font);
        submit.setBackground(textBackgroudColor);
        add(submit);
        submit.addActionListener(unused->{
            combobox.setVisible(ItemData.checkBlank(name.getText(), cat.getText().toUpperCase(), id.getText()));
            selectOption.setVisible(ItemData.checkBlank(name.getText(), cat.getText().toUpperCase(), id.getText()));
            // makes dropdown and button visible if all textfields are not empty
            revalidate();
            repaint();

            return;
        });

        final JButton helpButton = new JButton("Help!");
        helpButton.setBounds(550, 30, 100, 20);
        helpButton.setFont(font);
        helpButton.setBackground(textBackgroudColor);
        add(helpButton);
        helpButton.addActionListener(unused->{
            Help.newWindow();
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
        label.setBounds(x, y, 200, 20); 
        label.setFont(font);
        add(label);
        
        final JTextField textField = new JTextField(); // create text field
        textField.setBounds(x + 200, y, 150, 20);
        textField.setFont(font);
        textField.setBackground(textBackgroudColor); 
        add(textField); 
        
        return textField; // return the created text field
    }

    public static void main(String[] args) {
        new App();
    }
}