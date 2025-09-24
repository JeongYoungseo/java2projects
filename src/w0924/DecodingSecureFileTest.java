package w0924;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class DecodingSecureFileTest {
    public static void main(String[] args) {
        FileReader fr = null;
        BufferedReader br = null;

        try {
            fr = new FileReader("D:/filetest/secure1.txt");
            br = new BufferedReader(fr);
        } catch (FileNotFoundException e) {
            System.out.println("File Read Error");
        }
        try {
            fr.close();
        } catch (IOException e) {
            System.out.println("Close Error");
        }
    }

}
