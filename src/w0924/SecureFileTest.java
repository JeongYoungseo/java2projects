package w0924;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

// 콘솔(키보드) 입력받은 메세지를 암호화시켜 파일로 저장
public class SecureFileTest {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        FileWriter fw = null;
        String line = "";
        String secureOutStr = "";
        try {
            fw = new FileWriter("D:/filetest/secure1.txt");
            while (true){
                System.out.print("Enter Message: ");
                line = sc.nextLine();
                if(line.equals(""))
                    break;

                 for (int i = 0; i < line.length(); i++){
                     int code = (int)line.charAt(i);
                     code += 100;
                     secureOutStr += (char)code;
                 }
                 fw.write(secureOutStr + "\n");
            }

        } catch (IOException e) {
            System.out.println("File Write Error");;
        }
        try {
            fw.close();
            System.out.println("Generated Secure File");
            sc.close();
        } catch (IOException e) {
            System.out.println("Close Error");;
        }
    }
}
