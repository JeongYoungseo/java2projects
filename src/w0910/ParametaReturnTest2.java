package w0910;

import java.util.Scanner;

public class ParametaReturnTest2 {
    public static void main(String[] args) {
//        Calc1 calc1 = new Calc1();
//        int result = calc1.plus(100,200,300);
//        System.out.println("세개 정수의 합계: "+result);
        Calc1 calc1 = new Calc1();
        Scanner sc = new Scanner(System.in);
        int[] arr = new int[3];
        System.out.print("* 정수1 입력: ");
        arr[0] = sc.nextInt();
        System.out.print("* 정수2 입력: ");
        arr[1] = sc.nextInt();
        System.out.print("* 정수3 입력: ");
        arr[2] = sc.nextInt();
        int result = calc1.plus(arr[0],arr[1],arr[2]);
        System.out.println(result);
    }
}
