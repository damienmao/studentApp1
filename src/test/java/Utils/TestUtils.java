package Utils;

import java.util.Random;

public class TestUtils {
    public static String getRandomValue(){
        Random random=new Random();
        int randomInt=random.nextInt(100000);  //so what it means is every time we run this class, it will generate a random value between 1-100000
        return Integer.toString(randomInt);  //Integer.toString can convert an Int to a String
    }
}
