package w0924;

import java.io.*;

public class ImageFileCopyTest {
    public static void main(String[] args) throws IOException {
        FileInputStream fis = null;
        FileOutputStream fos = null;
// 이미지 파일은 1바이트씩 입력받아서 1바이트씩 출력해야한다.
        try {
            fis = new FileInputStream("C:/Users/AI-510-167/Pictures/images.jpg");
            fos = new FileOutputStream("D:/filetest/CopyImageFile1.jpg");
            int ch;
            while ((ch = fis.read()) != -1) {
                fos.write(ch);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found");
            ;
        } catch (IOException e) {
            System.out.println("Reading Character Error");
        }
        try {
            fis.close();
            fos.close();
            System.out.println("File copied successfully");
        } catch (IOException e) {
            System.out.println("closing file IOStream Error");
        }


    }
}
