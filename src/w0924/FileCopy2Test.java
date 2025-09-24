package w0924;

import java.io.*;

public class FileCopy2Test {

    public static void copyFile() throws FileNotFoundException, IOException {
        FileReader fr = null;
        BufferedReader br = null;
        FileWriter fw = null;
        fr = new FileReader("D:/filetest/mydata.txt");
        br = new BufferedReader(fr);
        fw = new FileWriter("D:/filetest/copyfile1.txt");
        String line;

        while ((line = br.readLine())!=null){
            fw.write(line + "\n");
        }

        br.close();
        fw.close();
        System.out.println("File copied");
    }

    public static void main(String[] args) {
        try {
            copyFile();
        }catch (FileNotFoundException e) {
            System.out.println("file not found");
        }catch (IOException e) {
            System.out.println("file write error");
        }
    }
}

