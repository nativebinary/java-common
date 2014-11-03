package common.basic.utils;

import java.util.Random;

public class RandomUtil {
    static Random random;

    static {
        random = new Random();
    }

    public RandomUtil() throws InstantiationException {
        throw new InstantiationException();
    }

    public static String nextLeadingZeroInt(int i) {
        final int value = random.nextInt(i);
        int length = ("" + i).length();
        return String.format("%0" + length + "d", value);
    }

    public static String nextString(int length) {
        StringBuilder sb = new StringBuilder();
        while(sb.length() < length)
        {
            sb.append(UuidUtil.generateWithoutDash());
        }

        sb.delete(length, sb.length());
        return sb.toString();
    }

    public static int nextInt() {
        return random.nextInt();
    }

    public static int nextInt(int i) {
        return random.nextInt(i);
    }

    public static int nextInt(int start, int until) {
        return start + nextInt(until - start);
    }

    public static boolean nextBoolean(){
        return nextInt() % 2 == 0;
    }

    public static boolean nextBoolean(float ratioPositive){
        return nextFloat() < ratioPositive;
    }

    public static float nextFloat() {
        return random.nextFloat();
    }

    public static long nextLong() {
        return random.nextLong();
    }

    public static String nextNumberString(int length)
    {
        StringBuilder sb = new StringBuilder();
        sb.append(Math.abs(nextInt(10)));
        while(sb.length() < length)
        {
            sb.append(Math.abs(nextLong()));
        }

        sb.delete(length, sb.length());
        return sb.toString();

    }

}
