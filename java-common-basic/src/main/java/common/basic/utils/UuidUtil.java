package common.basic.utils;

import java.util.UUID;

public class UuidUtil {
    public UuidUtil() throws InstantiationException {
        throw new InstantiationException();
    }

    public static String generate() {
        return UUID.randomUUID().toString();
    }

    public static String generateWithoutDash()
	{
		return generate().replace("-", "");
	}

}
