package w0903;

import java.util.Scanner;

public class Lab10_01 {
    public static int calc (int num1, int num2, char cal){
        int result;
        switch(cal){
          case '+': result = num1 + num2;
          break;
          case '-': result = num1 - num2;
          break;
          case '*': result = num1 * num2;
          break;
          case '/': result = num1 / num2;
          break;
          default: result =  0;
        }
        return result;
    }

    public static void main(String[] args) {
      Scanner s = new Scanner(System.in);
      int res;
      char op;
      int num1, num2;

      while(true){
          System.out.println("＊연산기호 입력 시 알파벳 e를 입력하면 프로그램은 종료됩니다.＊");
          System.out.printf("계산할 연산기호(+,-,*,/) 입력: ");
          op = s.next().charAt(0);
          if(op == 'e')
              break;
          System.out.print("정수1 입력: ");
          num1 = s.nextInt();
          System.out.print("정수2 입력: ");
          num2 = s.nextInt();

          res = calc(num1, num2, op);
          System.out.printf("%d  %c  %d = %d\n", num1, op, num2, res);
      }

        s.close();
    }
}
