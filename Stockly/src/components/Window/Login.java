package Stockly.src.components.Window;

import javax.crypto.SecretKey;
import javax.swing.*;
import java.nio.file.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.Base64;

import Stockly.src.components.AESUtils;
import Stockly.src.App;

public class Login extends JFrame {
    Color green = new Color(169, 236, 168);

    public static Boolean signedIn = false;

    private static Login currentLoginWindow = null;
    private JTextField usernameField;
    private JPasswordField passwordField, keyField;
    private JLabel errorMessageLabel;

    Login() {
        setSize(200, 350);
        setTitle("Stockly Login");
        setLocationRelativeTo(null); // opens in center of screen
        setLayout(new BorderLayout());

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBackground(green);

        // username label and text field
        JPanel usernamePanel = new JPanel();
        usernamePanel.setBackground(green);
        JLabel usernameLabel = new JLabel("Username:");
        usernameField = new JTextField(15);
        usernamePanel.add(usernameLabel);
        usernamePanel.add(usernameField);
        contentPanel.add(usernamePanel);

        // password label and password field
        JPanel passwordPanel = new JPanel();
        passwordPanel.setBackground(green);
        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField(15);
        passwordPanel.add(passwordLabel);
        passwordPanel.add(passwordField);
        contentPanel.add(passwordPanel);

        // key label and key field
        JPanel keyPanel = new JPanel();
        keyPanel.setBackground(green);
        JLabel keyLabel = new JLabel("Key:");
        keyField = new JPasswordField(15);
        keyPanel.add(keyLabel);
        keyPanel.add(keyField);
        contentPanel.add(keyPanel);

        // error message label
        errorMessageLabel = new JLabel("");
        errorMessageLabel.setForeground(Color.RED);
        contentPanel.add(errorMessageLabel);

        // login button
        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(_ -> handleLogin());
        contentPanel.add(loginButton);

        add(contentPanel, BorderLayout.CENTER);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                // reset window reference when closed
                currentLoginWindow = null;
                dispose(); // dispose of the closed window
            }
        });

        setVisible(true);
    }

    public static void newWindow() {
        if (currentLoginWindow == null) {
            currentLoginWindow = new Login();
        } else {
            currentLoginWindow.toFront();
        }
    }

    // handle login button click
    private void handleLogin() {
        String username = usernameField.getText();
        char[] password = passwordField.getPassword();
        char[] key = keyField.getPassword();

        try {
            // convert entered key to SecretKey
            String keyString = new String(key);
            // decode the Base64-encoded key to byte array
            byte[] keyBytes = Base64.getDecoder().decode(keyString);
            SecretKey secretKey = AESUtils.convertToSecretKey(keyBytes);

            // read the encrypted credentials from the file
            File userFile = new File("Stockly/src/accounts/" + username + ".txt");

            if (userFile.exists()) {
                // read encrypted data from file
                String encryptedData = new String(Files.readAllBytes(userFile.toPath()));
                String[] credentials = encryptedData.split("\n");
                String encryptedUsername = credentials[0].trim();
                String encryptedPassword = credentials[1].trim();

                // decrypt the username and password using the key
                String decryptedUsername = AESUtils.decrypt(encryptedUsername, secretKey);
                String decryptedPassword = AESUtils.decrypt(encryptedPassword, secretKey);

                // compare entered and decrypted credentials
                if (username.equals(decryptedUsername) && new String(password).equals(decryptedPassword)) {
                    // login success
                    JOptionPane.showMessageDialog(this, "Login successful!");
                    signedIn = true;
                    App.signedIn = signedIn;
                    App.signedInCheckLabel.setText("Signed in as: " + username);
                    App.signedInLabel.setText("You will be signed out when you close the app.");
                    App.createAccount();
                    // close the login window after successful login
                    dispose();
                } else {
                    // login failed
                    errorMessageLabel.setText("Invalid username or password!");
                }
            } else {
                // username file doesn't exist
                errorMessageLabel.setText("No account found for this username!");
            }
        } catch (Exception e) {
            // handle any exception (file read, decryption, etc.)
            errorMessageLabel.setText("An error occurred during login!");
            e.printStackTrace();
        }
    }
}
