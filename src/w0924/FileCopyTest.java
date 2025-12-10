package w0924;

import java.io.*;

public class FileCopyTest {

    public static void main(String[] args) {
        FileReader fr = null;
        BufferedReader br = null;
        FileWriter fw = null;
        try {
            fr = new FileReader("D:/filetest/mydata.txt");
            br = new BufferedReader(fr);
            fw = new FileWriter("D:/filetest/copyfile1.txt");
            String line;

            while ((line = br.readLine())!=null){
                fw.write(line + "\n");
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("I/O Error");
        }

        try {
            br.close();
            fw.close();
            System.out.println("File copied");
        } catch (IOException e) {
            System.out.println("Error closing file");
        }

    }
}

