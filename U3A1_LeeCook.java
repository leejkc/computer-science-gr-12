import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class U3A1_LeeCook extends JFrame{
    final JTextField t1Field, t2Field, nthField;
    final JTextArea output;

    int count = 0;
    U3A1_LeeCook(){
        setSize(330,300);
        setTitle("Inventory Manager");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // exit code when close button is pressed
        getContentPane().setBackground(new java.awt.Color(200, 200, 200));
        setLayout(new BorderLayout());

        JPanel cp = new JPanel(); // cp -> contentPane
        cp.setLayout(new BoxLayout(cp, BoxLayout.Y_AXIS));
        t1Field = createLabelAndTextField(cp, "TERM ONE:", 30, 40);
        t2Field = createLabelAndTextField(cp, "TERM TWO:", 30, 80);
        nthField = createLabelAndTextField(cp, "nTH TERM:", 30, 120);
        add(cp, BorderLayout.NORTH);

        // JTextArea + JScrollPane for output results.
        output = new JTextArea();
        output.setEditable(false);
        add(output, BorderLayout.CENTER);
        JScrollPane scrollPane = new JScrollPane(output, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        add(scrollPane, BorderLayout.CENTER);
        // set center since the other is NORTH, CENTER will fill the rest under it.

        JButton btn = new JButton("ENTER TERMS");
        btn.setBackground(new java.awt.Color(200, 200, 200));
        add(btn, BorderLayout.SOUTH);
        btn.addActionListener(_->{
            count = 0;
            modifiedFibonacci(Integer.parseInt(nthField.getText()));
        });


        setVisible(true);
    }

    public int modifiedFibonacci(int nth){
        // t1, t2, nth represent terms 1 & 2 and the nth term
        int t1 = 0;
        int t2 = 0;
        try {
            // try parsing to see if inputs are numbers
            t1 = Integer.parseInt(t1Field.getText());
            t2 = Integer.parseInt(t2Field.getText());

            Integer.parseInt(nthField.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Please enter a number!", "Error", JOptionPane.ERROR_MESSAGE);
            return 0;
        }

        if (nth == 0){
            // output.append(t1 + "\n");
            return t1;
        }
        if (nth == 1){
            // output.append(t2 + "\n");
            return t2;
        }
        if (nth == 2){
            // output.append(t1+t2 + "\n");
            return t1+t2;
        }

        int result = modifiedFibonacci(nth-1)+modifiedFibonacci(nth-2)+modifiedFibonacci(nth-3);
        if (count < Integer.parseInt(nthField.getText())){
            output.append(result + "\n");
            count++;
        }

        return result;
    }
    /**
     * this is a small method that creates a JLabel and a JTextField.
     * this uses the inputted x and y coords to make a label and textfield for the user to see.
     * this can help speed up coding and running process because this can make both objects and only return the JTextfield
     * instead of having both.
     * this can also help make your code clearer because it removes extra elements with the same name if you have your label as a similar name as the textfeild.
     * 
     * @param cp the JPanel to add to
     * @param t the label text for the JLabel
     * @param x the x position for the JLabel
     * @param y the y position for the JLabel
     * @return textField -> JTextField
     * @see JLabel
     * @see JTextField
     * @see JPanel
     */
    private JTextField createLabelAndTextField(JPanel cp, String t, int x, int y) {
        final JPanel p = new JPanel(); // panel to fit both elements
        p.setLayout(new java.awt.FlowLayout()); // left to right layout

        final JLabel l = new JLabel(t); // create label
        p.add(l);
        
        final JTextField tf = new JTextField(); // create text field
        tf.setPreferredSize(new Dimension(100,20));
        p.add(tf); 

        cp.add(p); // add new pane to contentpane
        
        return tf; // return the created text field
    }


    public static void main(String[] args){
        new U3A1_LeeCook();
    }
}
