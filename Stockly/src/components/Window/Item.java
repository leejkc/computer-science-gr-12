package Stockly.src.components.Window;

import javax.swing.*;
import java.awt.*;
import java.nio.file.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Item {
    private static final Path ITEM_DIR = Paths.get("Stockly/src/items/");

    public static void readFromFile(String itemName, boolean isLoggedIn) {
        try {
            if (!Files.exists(ITEM_DIR)) {
                Files.createDirectories(ITEM_DIR);
            }

            Path itemFilePath = ITEM_DIR.resolve(itemName + ".txt");

            if (!Files.exists(itemFilePath)) {
                Files.write(itemFilePath, List.of("Item: " + itemName, "Description: ", "Price: ", "Stock: "), StandardOpenOption.CREATE);
            }

            List<String> itemDetails = Files.readAllLines(itemFilePath);

            createItemGUI(itemName, itemDetails, itemFilePath, isLoggedIn);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

        public static String[] getItemDetailsForSorting(String detailType) {
            List<String> itemDetailsList = new ArrayList<>();
            try (DirectoryStream<Path> stream = Files.newDirectoryStream(ITEM_DIR, "*.txt")) {
                for (Path entry : stream) {
                    List<String> lines = Files.readAllLines(entry);
                    String itemName = entry.getFileName().toString().replace(".txt", "");
                    String detail = lines.stream()
                            .filter(line -> line.startsWith(detailType + ": "))
                            .findFirst()
                            .orElse(detailType + ": 0");
                    String detailValue = detail.split(": ")[1];
                    itemDetailsList.add(detailValue + " (" + itemName + ")");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return itemDetailsList.toArray(new String[0]);
        }

        private static void createItemGUI(String itemName, List<String> itemDetails, Path itemFilePath, boolean isLoggedIn) {
        JFrame frame = new JFrame(itemName);
        frame.setSize(600, 300);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));

        for (String detail : itemDetails) {
            if (isLoggedIn) {
                JTextField textField = new JTextField(detail);
                contentPanel.add(textField);
            } else {
                JLabel label = new JLabel(detail);
                contentPanel.add(label);
            }
        }

        JButton saveButton = new JButton("Save");
        saveButton.setEnabled(isLoggedIn);
        saveButton.addActionListener(_ -> {
            try {
                List<String> updatedDetails = new ArrayList<>();
                for (Component component : contentPanel.getComponents()) {
                    if (component instanceof JTextField) {
                        updatedDetails.add(((JTextField) component).getText());
                    }
                }
                Files.write(itemFilePath, updatedDetails, StandardOpenOption.TRUNCATE_EXISTING);
                JOptionPane.showMessageDialog(frame, "Item details saved successfully!");
            } catch (IOException ioException) {
                ioException.printStackTrace();
                JOptionPane.showMessageDialog(frame, "Failed to save item details.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Panel for the image and import button
        JPanel imagePanel = new JPanel();
        imagePanel.setLayout(new BorderLayout());

        JLabel imageLabel = new JLabel();
        imageLabel.setHorizontalAlignment(JLabel.CENTER);
        imageLabel.setVerticalAlignment(JLabel.CENTER);
        imagePanel.add(imageLabel, BorderLayout.CENTER);

        JButton importButton = new JButton("Import Image");
        importButton.setEnabled(isLoggedIn);
        importButton.addActionListener(_ -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Image files", "png", "jpg", "jpeg"));
            int result = fileChooser.showOpenDialog(frame);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                try {
                    String extension = selectedFile.getName().substring(selectedFile.getName().lastIndexOf("."));
                    Path destinationPath = ITEM_DIR.resolve(itemName + extension);
                    Files.copy(selectedFile.toPath(), destinationPath, StandardCopyOption.REPLACE_EXISTING);
                    ImageIcon imageIcon = new ImageIcon(destinationPath.toString());
                    imageLabel.setIcon(imageIcon);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                    JOptionPane.showMessageDialog(frame, "Failed to import image.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        imagePanel.add(importButton, BorderLayout.SOUTH);

        frame.add(new JScrollPane(contentPanel), BorderLayout.CENTER);
        frame.add(imagePanel, BorderLayout.EAST);
        frame.add(saveButton, BorderLayout.SOUTH);

        frame.setVisible(true);
    }
}