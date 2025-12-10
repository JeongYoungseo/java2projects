package w0910.Lotto;

import java.util.ArrayList;
import java.util.Arrays;

public class LottoReturn {
    public static void main(String[] args) {
        System.out.println("***로또 프로그램***");
        ArrayList<Integer> numlist = new ArrayList<>();
        int randNum = 0;
        LottoClass lotto = new LottoClass();

        reFindNum:
        while (true){
            randNum = lotto.randomNum(45);
            for(int num : numlist){
                if(randNum == num){
                    continue reFindNum;
                }
            }
            numlist.add(randNum);

            if(numlist.size()==6)
                break;
        }

       Object[] numArr = numlist.toArray();
        Arrays.sort(numlist.toArray());
        System.out.println("*** 당첨번호 ***");
        System.out.println(Arrays.toString(numArr));
    }
}
