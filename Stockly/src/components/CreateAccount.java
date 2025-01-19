package Stockly.src.components;

import javax.crypto.SecretKey;
import javax.swing.*;
import java.nio.file.*;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class CreateAccount {
    private CreateAccount() {}

    public static void create() {
        try {
            // use JOptionPane to get the username and password from the user
            String username = JOptionPane.showInputDialog("Enter Username:");
            if (username == null || username.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Username cannot be empty.");
                return;
            }

            String password = JOptionPane.showInputDialog("Enter Password:");
            if (password == null || password.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Password cannot be empty.");
                return;
            }

            // generate a secret key (you will need to use the same key in the login process)
            SecretKey secretKey = AESUtils.generateSecretKey();

            // convert the secret key to a readable format (Base64 string)
            String secretKeyString = Base64.getEncoder().encodeToString(secretKey.getEncoded());

            // encrypt username and password
            String encryptedUsername = AESUtils.encrypt(username, secretKey);
            String encryptedPassword = AESUtils.encrypt(password, secretKey);

            // define the path for the account file
            Path filePath = Paths.get("Stockly/src/accounts/" + username + ".txt");

            // prepare the data to write (encrypted username and password)
            String accountData = encryptedUsername + "\n" + encryptedPassword;

            // write to the file using NIO (Files.write with StandardOpenOption)
            Files.write(filePath, accountData.getBytes(StandardCharsets.UTF_8), StandardOpenOption.CREATE, StandardOpenOption.WRITE);

            // show success message
            JOptionPane.showMessageDialog(null, "Account created successfully!");

            // create a JTextArea for the key, so it's copy-paste-able
            JTextArea textArea = new JTextArea(5, 20); // 5 lines, 20 characters wide
            textArea.setText(secretKeyString); // put the Base64 encoded key in the text area
            textArea.setEditable(false); // make the text area read-only
            textArea.setCaretPosition(0); // move the caret to the beginning of the text

            // create a JScrollPane for the JTextArea to make it scrollable
            JScrollPane scrollPane = new JScrollPane(textArea);
            
            // show the secret key in a dialog with the scrollable JTextArea
            JOptionPane.showMessageDialog(null, scrollPane, "Your Key (Don't lose it)", JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "An error occurred while creating the account.");
        }
    }
}
