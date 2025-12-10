package w0827;

import java.util.Arrays;
import java.util.Collections;

public class ArrayReverseTest {
    public static void main(String[] args) {
        String[] strArr = {"레이", "가을", "안유진", "장원영", "이서", "리즈"};
        System.out.println("처음 배열: " + Arrays.toString(strArr));

        Collections.reverse(Arrays.asList(strArr));

        System.out.println("역순 배열: " + Arrays.toString(strArr));
    }
}
