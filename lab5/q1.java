package lab5;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
// 1. Design and implement a Java application that copies the contents of one file
// to another using byte streams. The program must use FileInputStream to
// read data from a source file and FileOutputStream to write the same data to
// a destination file.


public class q1 {
    public static void main(String[] args) {

        try (
            FileInputStream fis = new FileInputStream("C:\\Users\\Abhijeet ojha\\Downloads\\input.txt");
            FileOutputStream fos = new FileOutputStream("output.txt");
        ) {
            int data;

            // read byte by byte
            while ((data = fis.read()) != -1) {
                fos.write(data);
            }

            System.out.println("File copied successfully using byte streams.");

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}