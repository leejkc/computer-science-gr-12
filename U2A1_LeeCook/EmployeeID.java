package U2A1_LeeCook;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class EmployeeID {
    // this class accesses my txt doc and returns it to my app

    // method to write a new employees data to the file
    public static void txtWriter(String id, String firstName, String lastName, String salary, String startDate) {
        Path filePath = Paths.get("U2A1_LeeCook/file.txt"); // path to the text file
        try {
            // append the new employee data to the file
            Files.writeString(filePath, id +","+ firstName +","+ lastName +","+ salary +","+ startDate + "\n", StandardOpenOption.APPEND);
        } catch (IOException e){
            e.printStackTrace(); // handle exception if there's an issue writing to the file
        }
    }

    // method to read all employee data from the file and return it as an array of strings
    public static String[] txtReader(){
        Path filePath = Paths.get("U2A1_LeeCook/file.txt"); // path to the text file
        try {
            List<String> lines = Files.readAllLines(filePath); // read all lines from the file
            // convert the list of lines into an array and return it
            return lines.toArray(new String[0]);
        } catch (IOException e) {
            e.printStackTrace(); // handle exception if theres an issue reading the file
            return new String[0]; // return an empty array in case of error
        }
    }

    // method to remove a specific line from the file based on the provided ID
    public static void removeLineFromFile(String removeMe) {
        Path filePath = Paths.get("U2A1_LeeCook/file.txt"); // path to the text file
        try {
            List<String> lines = Files.readAllLines(filePath); // read all lines from the file
            List<String> newLines = new ArrayList<>(); // create a new list to store remaining lines
            for (String line : lines) {
                if (!line.startsWith(removeMe)) { // if the line does not start with the provided ID, add it to the new list
                    newLines.add(line);
                }
            }
            Files.write(filePath, newLines); // overwrite the file with the new list of lines
        } catch (IOException e) {
            e.printStackTrace(); // handle exception if theres an issue with file operations
        }
    }
}
