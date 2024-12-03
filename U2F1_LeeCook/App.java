package U2F1_LeeCook;
import java.io.*;
import java.nio.file.*;
import java.util.*;

public class App {
    public static void main(String[] args) {
        Path inputFilePath = Paths.get("U2F1_LeeCook/StudentP3.txt");
        Path outputFilePath = Paths.get("U2F1_LeeCook/SortedStudentP3.txt");

        try {
            // Step 1: Read all lines from the input file
            List<String> names = Files.readAllLines(inputFilePath);

            // Step 2: Sort the names alphabetically
            Collections.sort(names);

            // Step 3: Write sorted names to the output file
            Files.write(outputFilePath, names);

            // Step 4: Print sorted names to the console
            System.out.println("Sorted Names:");
            for (String name : names) {
                System.out.println(name);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
