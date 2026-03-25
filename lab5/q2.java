package lab5;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
// Design and implement a Java application that reads textual data from an
// existing text file using FileReader and writes the same content into another
// text file using FileWriter
public class q2 {
    public static void main(String[] args) {

        try (
            FileReader fr = new FileReader("C:\\Users\\Abhijeet ojha\\Downloads\\input.txt");
            FileWriter fw = new FileWriter("output.txt");
        ) {
            int ch;

            // read character by character
            while ((ch = fr.read()) != -1) {
                fw.write(ch);
            }

            System.out.println("File copied successfully using character streams.");

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}