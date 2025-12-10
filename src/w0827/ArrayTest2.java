package w0827;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Random;

public class ArrayTest2 {
    public static void main(String[] args) {
        String[] strArr = {"작은 습관이 큰 변화를 만든다.– 제임스 클리어",
                "성공은 열정을 잃지 않고 실패에서 실패로 걸어가는 것이다.– 윈스턴 처칠",
                "당신이 할 수 있다고 믿든, 할 수 없다고 믿든, 믿는 대로 될 것이다.– 헨리 포드",
                "위대한 일을 하는 유일한 방법은 당신이 하는 일을 사랑하는 것이다.– 스티브 잡스",
                "노력은 배신하지 않는다.– 일본 속담", "나 자신을 이기는 것이 가장 큰 승리다.– 플라톤",
                "기회는 준비된 자에게 찾아온다.– 루이스 파스퇴르", "오늘 걷지 않으면 내일은 뛰어야 한다– 독일 속담",
                "절망 속에서도 희망을 찾는 자가 결국 승리한다– 톨스토이",
                "천천히 가도 멈추지 않으면 결국 도착한다.– 공자"};

        Random rand = new Random();

        int todayIndex = rand.nextInt(strArr.length);
        Calendar cal = Calendar.getInstance();
        int year  = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH)+1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        System.out.printf("%d년 %d월 %d일 오늘의 명언: %s \n",year,month,day,strArr[todayIndex]);
    }
}
