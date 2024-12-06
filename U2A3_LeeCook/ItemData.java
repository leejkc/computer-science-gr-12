package U2A3_LeeCook;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;

import javax.swing.JOptionPane;

public class ItemData {
    public static void insertData(String name, String cat, String am, String minimumAm, String vendor, String markup, String discount){
        Path filePath = Paths.get("U2A3_LeeCook/inventory.txt"); // path to the inventory file.
        HashMap<String, String> dictionary = new HashMap<>();

        if (!(name.isBlank() || cat.isBlank() || am.isBlank() || minimumAm.isBlank() || vendor.isBlank() || markup.isBlank() || discount.isBlank())){
            // if every variable prevuously listed is not blank
            String data = name + ";" + am + ";" + minimumAm + ";" + vendor + ";" + markup + ";" + discount;
            dictionary.put(cat, data);
            try {
                Files.writeString(filePath, dictionary, StandardOpenOption.APPEND);
            } catch (IOException e) {
                e.printStackTrace(); // handle e -> print trace
            }
            //make so cat is in file and reads all lines for each cat.
        } else {
            JOptionPane.showMessageDialog(null, "All fields must be filled out.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
    }
}