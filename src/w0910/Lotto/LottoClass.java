package w0910.Lotto;

import java.util.Random;

public class LottoClass {
    public int randomNum(int max){
        int num = 0;
        Random random = new Random();
        num = random.nextInt(max) + 1; //1~45까지의 숫자

        return num;
    }

}
