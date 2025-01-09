import javax.swing.JOptionPane;

public class Echo {
    public static void main(String[] args) {
        String input = JOptionPane.showInputDialog(null, "Write something...", "Echo", JOptionPane.ERROR_MESSAGE);
        JOptionPane.showMessageDialog(null, input, "Echo", JOptionPane.WARNING_MESSAGE);
    }
}