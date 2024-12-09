package U2A3_LeeCook;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class ItemData {

    /**
     * 
     * @param name
     * @param cat
     * @param am
     * @param minimumAm
     * @param vendor
     * @param markup
     * @param discount
     * @return
     */
    public static boolean checkData(String name, String cat, String am, String minimumAm, String vendor, String markup, String discount){
        if (!(name.isBlank() || cat.isBlank() || am.isBlank() || minimumAm.isBlank() || vendor.isBlank() || markup.isBlank() || discount.isBlank())){
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "All fields must be filled out.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    /**
     * 
     * @param name
     * @param cat
     * @param am
     * @param minimumAm
     * @param vendor
     * @param markup
     * @param discount
     */
    public static void write(String name, String cat, String am, String minimumAm, String vendor, String markup, String discount){
        String id = cat.substring(0, 2);
        String data = cat + "," + name + "," + am + "," + minimumAm + "," + vendor + "," + markup + "," + discount;
    }

    /**
     * 
     * @param removeMe
     */
    public static void remove(String removeMe) {
        Path filePath = Paths.get("U2A3_LeeCook/inventory.txt"); // path to the inventory file.
        try {
            List<String> lines = Files.readAllLines(filePath); // read all lines from the file
            List<String> newLines = new ArrayList<>(); // create a new list to store remaining lines
            for (String line : lines) {
                if (!line.startsWith(removeMe)) { // if the line does not start with, add it to the new list
                    newLines.add(line);
                }
            }
            Files.write(filePath, newLines); // overwrite the file with the new list of lines
            return;
        } catch (IOException e) {
            e.printStackTrace(); // handle exception if theres an issue with file operations
            return;
        }
    }

    /**
     * "search" method to search items by id, catagory, or name.
     * always searches in this order:
     * <ul>
     * <li><b>id</b>
     * <li><b>name</b>
     * <li><b>catagory</b>
     * </ul>
     * 
     * <ul>
     * <li>if user only knows id:
     * <ul><li>look for line that starts with id
     * <li>returns empty string
     * </ul>
     * <li>if user only knows name:
     * <ul><li>splits by comma and compares index 1
     * <li>returns empty string
     * </ul>
     * <li>if user only knows catagory:
     * <ul><li>splits all lines by comma and looks for each line with the same catagory
     * <li>returns a string with all those lines for the user to pick from.
     * </ul>
     * </ul>
     * @param input represents id, catagory, or name
     * @return String
     */
    public static String search(String input){
        // display a dropdown?
        // new window?
        // make sure to update javadoc
        return new String();
    }
    
}