package common.basic.utils;

import java.util.Random;

public class RandomUtil {
    static Random random;

    static {
        random = new Random();
    }

    RandomUtil() throws InstantiationException {
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

        sb.delete(length, sb.length() - 1);
        return sb.toString();
    }

    public static int next() {
        return random.nextInt();
    }

    public static int next(int start, int until) {
        return start + random.nextInt(until - start);
    }

    public static float nextFloat() {
        return random.nextFloat();
    }
}
