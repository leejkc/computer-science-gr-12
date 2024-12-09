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
     * <p>
     * stringContains checks if a subsequence is inside a string. 
     * this function uses CharSequence as its main backend.
     * i didnt like {@code .contains()} so i made my own for fun.
     * </p>
     * <ul>
     * <li>if the subsequence that is being searched for has no length / doesnt exist
     * <ul><li>returns true</ul>
     * <li>if the subsequence's length is greater than the strings length
     * <ul><li>returns false</ul>
     * </ul>
     * 
     * <h4>Example Usage:</h3>
     * <pre>
     * public class Example {
     *     public static void main(String[] args) {
     *         String mainString = "Hello, world!";
     *         String searchString = "world";
     *         boolean result = stringContains(mainString, searchString);
     *         System.out.println("Is '" + searchString + "' found in '" + mainString + "'? " + result);
     *         // is 'world' found in 'Hello, world!'? 
     *         // output: returns true
     * </pre>
     *
     * @param sw the main CharSequence to search within. (string usually)
     * @param sf the CharSequence to search for. (subsequence, usually a character)
     * @return true -> if the sf is found within the sequence
     * @return false otherwise
     * @throws NullPointerException if either sw or sf are null.
     * @see CharSequence
     */
    public static boolean stringContains(CharSequence sw, CharSequence sf) {
        if (sw == null || sf == null) {
            throw new NullPointerException("Sequence or search sequence cannot be null");
        }
        
        int sequenceLength = sw.length();
        int searchLength = sf.length();
        
        if (searchLength == 0) {
            return true;
        }
        
        if (searchLength > sequenceLength) {
            return false;
        }
        
        char firstChar = sf.charAt(0);
        
        for (int i = 0; i <= sequenceLength - searchLength; i++) {
            if (sw.charAt(i) == firstChar) {
                boolean foundItem = true;
                for (int j = 1; j < searchLength; j++) {
                    if (sw.charAt(i + j) != sf.charAt(j)) {
                        foundItem = false;
                        break;
                    }
                }
                if (foundItem) {
                    return true;
                }
            }
        }
        return false;
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
     * @return
     */
    public static boolean checkBlank(String name, String cat, String id){
        if (!(name.isBlank() && cat.isBlank())){
            return true;
        } else {
            if (!(id.isBlank())){
                return true;
            }
            JOptionPane.showMessageDialog(null, "Missing important fields.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    /**
     * makes id, current price, and regular price.
     * writes a line containing info for each item
     * @param name
     * @param cat
     * @param am
     * @param minimumAm
     * @param vendor
     * @param markup
     * @param discount
     */
    public static void write(String name, String cat, String am, String minimumAm, String vendor, String markup, String discount){
        Path filePath = Paths.get("U2A3_LeeCook/inventory.txt"); // path to the text file
        // search to find if name has no input
        if (!(search(name).isBlank())){
            JOptionPane.showMessageDialog(null, "Please enter the name of the item you want to write!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // search to find same name items
        if (!(search(name) == "No matching items found.")){
            JOptionPane.showMessageDialog(null, "Item with the same name already exists.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // writing process
        if (stringContains(name, ",")){
            JOptionPane.showMessageDialog(null, "Cannot have a comma in the name!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int count = 0; // counts how many of the same category the file has
        try {
            List<String> lines = Files.readAllLines(filePath); // read all lines from the file
            for (String line : lines) {
                if (line.startsWith(cat.substring(0, 3))) { // if the line does not start with, add it to the new list
                    count++;
                }
            }
            count++;
        } catch (IOException e){
            e.printStackTrace();
            return;
        }
        String id = cat.substring(0, 3) + "-" + String.format("%04d", count);
        int intMarkup = 0;
        if (stringContains(markup, "%")) {
            markup = markup.replace("%", "");
            intMarkup = Integer.parseInt(markup);
        }   
        int intDiscount = 0;
        if (stringContains(discount, "%")) {
            discount = discount.replace("%", "");
            intDiscount = Integer.parseInt(discount);
        }
        if (Integer.parseInt(am) < 0 || Integer.parseInt(minimumAm) < 0 || Integer.parseInt(vendor) < 0 || intMarkup < 0 || intDiscount < 0 ){
            JOptionPane.showMessageDialog(null, "One or more fields have a number lower than zero.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int intVendor = Integer.parseInt(vendor);
        float regularPrice = (float)Math.round((intVendor + (intVendor * intMarkup / 100)) * 100) / 100; // price without discount
        float currentPrice = (float)Math.round((regularPrice - (regularPrice * intDiscount / 100)) * 100) / 100;
        String data = name + "," + cat + "," + am + "," + minimumAm + "," + vendor + "," + markup + "," + regularPrice + "," + discount + "," + currentPrice; 
        try { // try to write new line to file
            // append the new employee data to the file
            if (!(name.isBlank() || cat.isBlank() || am.isBlank() || minimumAm.isBlank() || vendor.isBlank() || markup.isBlank() || discount.isBlank())){
                Files.writeString(filePath, "\n" + id +","+ data, StandardOpenOption.APPEND);
                return;
            } else {
                JOptionPane.showMessageDialog(null, "All fields must be filled out.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } catch (IOException e){
            e.printStackTrace(); // handle exception if there's an issue writing to the file
            return;
        }
    }

    /**
     * function to remove a item from the file using name
     * @param removeMe
     */
    public static void remove(String removeMe) {
        Path filePath = Paths.get("U2A3_LeeCook/inventory.txt");
        if (removeMe.length() == 0){
            JOptionPane.showMessageDialog(null, "Please enter the name of the item you want to remove!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            byte count = 0;
            List<String> lines = Files.readAllLines(filePath);
            List<String> newLines = new ArrayList<>();
            for (String line : lines) {
                String name = line.split(",")[0];
                if (stringContains(name, removeMe)) {
                    count++;
                    if (count > 1){
                        JOptionPane.showMessageDialog(null, "Too many items match your input!", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }
                if (!stringContains(name, removeMe)) {
                    newLines.add(line);
                }
            }
            if (count == 0){
                JOptionPane.showMessageDialog(null, "No items match your input!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            Files.write(filePath, newLines);
            JOptionPane.showMessageDialog(null, "Item removed!", "Message", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * "search" method to search items by catagory or name.
     * always searches in this order:
     * <ul>
     * <li><b>catagory</b>
     * <li><b>id</b>
     * <li><b>name</b>
     * </ul>
     * 
     * <ul>
     * </ul>
     * <li>if user only knows id:
     * <ul><li>checks for id inside txt file
     * <li>appends lines found
     * <li>returns empty string
     * </ul>
     * <li>if user only knows name:
     * <ul><li>checks for name inside txt file
     * <li>appends lines found
     * <li>returns empty string
     * </ul>
     * <li>if user only knows catagory:
     * <ul><li>checks for catagory inside txt file
     * <li>appends lines found
     * <li>returns a string with all those lines for the user to pick from.
     * </ul>
     * </ul>
     * @param input represents id, catagory, or name
     * @return String
     */
    public static String search(String input){
        Path filePath = Paths.get("U2A3_LeeCook/inventory.txt");
        StringBuilder result = new StringBuilder();
        try {
            List<String> lines = Files.readAllLines(filePath);
            for (String line : lines) {
                if (stringContains(line, input)) {
                    result.append(line).append("\n");
                }
            }
            if (result.length() == 0) {
                return "No matching items found.";
            } else {
                return result.toString();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "Error reading the file.";
        }
    }
    
}