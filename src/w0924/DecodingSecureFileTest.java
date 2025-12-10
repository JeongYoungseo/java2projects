package w0924;

import java.io.*;

public class DecodingSecureFileTest {
    public static void main(String[] args) {
        FileReader fr = null;
        BufferedReader br = null;
        FileWriter fw = null;

        try {
            fr = new FileReader("D:/filetest/secure1.txt");
            br = new BufferedReader(fr);
            fw = new FileWriter("D:/filetest/decode1.txt");
            String line;
            while ((line = br.readLine()) != null) {
                String decodeStr = "";
                for (int i = 0; i < line.length(); i++) {
                    int code = (int)line.charAt(i);
                    code -= 100;
                    decodeStr += (char)code;
                }
                System.out.println(decodeStr);
                fw.write(decodeStr+"\n");
            }
        } catch (FileNotFoundException e) {
            System.out.println("File Found Error");
        } catch (IOException e) {
            System.out.println("File Read Error");
        } finally {
            try {
                if(br != null) br.close();
                if(fr != null) fr.close();
                if(fw != null) fw.close();
            } catch (IOException e) {
                System.out.println("Close Error");
            }

        }
    }

}
