package w0827;

import java.util.Arrays;
import java.util.Scanner;

public class ArrayTest3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] arr = new int[5];
        int sum = 0;
        double avg = 0;

        System.out.println("피겨스케이팅 연기를 끝났습니다.");
        System.out.println("심사위원들은 점수를 입력해 주세요.");

        for (int i = 0; i <arr.length; i++) {
            System.out.println("심사위원 " + (i+1) + "입력: ");
            arr[i] = sc.nextInt();
            sum += arr[i];
        }
            avg = sum/arr.length;

            System.out.println("심사위원의 개별 점수" + Arrays.toString(arr));
            System.out.printf("심사위원의 평균 점수: %.2f \n", avg);
            sc.close();
    }
}
