package util;
import java.util.Random;

public class Utility {

    public Integer selectRandomInteger(int totalNumbers) {
        Random random = new Random();
        int index = random.nextInt(totalNumbers);
        System.out.println("The selected Random index is " + index);
        return index;
    }


}
