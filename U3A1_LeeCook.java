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

public class U3A1_LeeCook extends JFrame {
    final JTextField t1Field, t2Field, nthField;
    final JTextArea output;

    U3A1_LeeCook() {
        setSize(330, 300);
        setTitle("Modified Fibonacci");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // exit code when close button is pressed
        getContentPane().setBackground(new java.awt.Color(200, 200, 200));
        setLocationRelativeTo(null); // opens in center of screen
        setLayout(new BorderLayout());

        JPanel cp = new JPanel(); // cp -> contentPane
        cp.setLayout(new BoxLayout(cp, BoxLayout.Y_AXIS));
        t1Field = createLabelAndTextField(cp, "TERM ONE:");
        t2Field = createLabelAndTextField(cp, "TERM TWO:");
        nthField = createLabelAndTextField(cp, "nTH TERM:");
        add(cp, BorderLayout.NORTH);

        // jtextarea + jscrollpane for output results.
        output = new JTextArea();
        output.setEditable(false);
        add(output, BorderLayout.CENTER);
        JScrollPane scrollPane = new JScrollPane(output, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        add(scrollPane, BorderLayout.CENTER);

        JButton btn = new JButton("ENTER TERMS");
        btn.setBackground(new java.awt.Color(200, 200, 200));
        add(btn, BorderLayout.SOUTH);
        btn.addActionListener(_ -> {
            try {
                output.setText(""); // clear previous output
                int n = Integer.parseInt(nthField.getText());
                int t1 = Integer.parseInt(t1Field.getText());
                int t2 = Integer.parseInt(t2Field.getText());

                // validate that first term is smaller than second term
                if (t1 >= t2) {
                    JOptionPane.showMessageDialog(null, "First term should be smaller than second term.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // call the recursive function to display the sequence
                modifiedFibonacci(t1, t2, n);

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Please fill out all fields or make sure fields are numbers.");
            }
        });

        setVisible(true);
    }

    /**
     * recursive method to calculate and display the modified fibonacci sequence up to the nth term.
     *
     * @param t1     the first term
     * @param t2     the second term
     * @param nth    the index of the term to calculate
     */
    public void modifiedFibonacci(int t1, int t2, int nth) {
        output.setText(""); // clear previous output
        // start the recursion from term 0
        for (int current = 0; current <= nth; current++) {
            int term = calculateTerm(t1, t2, current);
            output.append(term + (current == nth ? "" : "\n"));
        }
    }

    /**
     * helper method to calculate each term recursively based on the modified fibonacci logic.
     * 
     *
     * @param t1     the first term
     * @param t2     the second term
     * @param current the current term index in the recursion
     * @return the value of the term at index `current`
     */
    public int calculateTerm(int t1, int t2, int current) {
        // base cases
        if (current == 0) {
            return t1;
        }
        if (current == 1) {
            return t2;
        }
        if (current == 2) {
            return t1 + t2;
        }

        // recursive case: sum of last three terms
        return calculateTerm(t1, t2, current - 1) + calculateTerm(t1, t2, current - 2) + calculateTerm(t1, t2, current - 3);
    }

    /**
     * this is a small method that creates a jlabel and a jtextfield.
     *
     * @param cp the jpanel to add to
     * @param t  the label text for the jlabel
     * @return the created jtextfield
     */
    private JTextField createLabelAndTextField(JPanel cp, String t) {
        final JPanel p = new JPanel(); // panel to fit both elements
        p.setLayout(new java.awt.FlowLayout()); // left to right layout

        final JLabel l = new JLabel(t); 
        p.add(l);

        final JTextField tf = new JTextField(); 
        tf.setPreferredSize(new Dimension(100, 20));
        p.add(tf);

        cp.add(p); 

        return tf;
    }

    public static void main(String[] args) {
        new U3A1_LeeCook();
    }
}
