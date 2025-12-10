package w0827;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class SortArrayTest {
    public static void main(String[] args) {
        int[] arr = {13,24,31,14,5,56,27,18,49};
        String[] strArr = {"인공지능", "소프트웨어", "한국", "대학", "폴리텍", "1학년", "하이테크", "2학년"};

    // 오름차순 정렬
        Arrays.sort(arr);

        for(int num: arr){
            System.out.print(num + "\t");
        }
        System.out.println();
    // 내림차순 정렬

        Arrays.sort(strArr, Collections.reverseOrder());
        for(String str: strArr){
            System.out.print(str + "\t");
        }
    }
}
