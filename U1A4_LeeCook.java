import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class U1A4_LeeCook {
    public static void main(String[] args) {
        JFrame window = new JFrame("Hurricane Scale");
        window.setSize(450,350);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setLayout(null); //set null layout for custom placement

        JLabel title = new JLabel("Hurricane Scale");
        title.setFont(new Font("Arial", Font.BOLD, 42));
        title.setBounds(30, 20, 350, 50);
        window.add(title);

        JLabel instructions = new JLabel("Please enter a hurricane category (1-5) :");
        instructions.setBounds(30, 100, 400, 30);
        window.add(instructions);

        JTextField textInput = new JTextField();
        textInput.setBounds(260, 100, 30, 30);
        window.add(textInput);

        JButton button = new JButton("Confirm");
        button.setBounds(300, 99, 90, 30);
        window.add(button);

        JTextArea result = new JTextArea("");
        result.setEditable(false);
        result.setBounds(30, 140, 400, 300);
        window.add(result);

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                switch (Integer.parseInt(textInput.getText())) {
                    case 1:
                        result.setText("Windspeeds for Category 1 are:\n\n74-95 mph or 64-82 kt or 119-153 km/hr");
                        break;
                    case 2:
                        result.setText("Windspeeds for Category 2 are:\n\n96-110 mph or 83-95 kt or 154-177 km/hr");
                        break;
                    case 3:
                        result.setText("Windspeeds for Category 3 are:\n\n111-130 mph or 96-113 kt or 178-209 km/hr");
                        break;
                    case 4:
                        result.setText("Windspeeds for Category 4 are:\n\n131-155 mph or 114-135 kt or 210-249 km/hr");
                        break;
                    case 5:
                        result.setText("Windspeeds for Category 5 are:\n\ngreater than 155 mph or 135 kt or 249 km/hr");
                        break;
                    
                    default:
                        JOptionPane.showMessageDialog(window, "Please enter a valid number!");;
                }
            }
        });

        window.setVisible(true);
    }
}