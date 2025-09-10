package w0910.MultiReturn;

import java.util.Scanner;
// 3개의 정수값을 입력 받는 클래스
public class MultiInput {

    public int[] inputScores(){
        int[] scores = new int[3];
        Scanner sc = new Scanner(System.in);

        for (int i = 0; i < 3; i++) {
            System.out.print("정수" + (i+1) + "입력: ");
            scores[i] = sc.nextInt();
        }
        sc.close();

        return scores;
    }
}
