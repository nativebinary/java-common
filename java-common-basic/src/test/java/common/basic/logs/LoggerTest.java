package common.basic.logs;

import org.junit.Assert;
import org.junit.Test;

public class LoggerTest extends Assert {
    @Test(expected = InstantiationException.class)
    public void testConstructor() throws Exception {
        new Logger();
    }

    @Test
    public void testLogging() throws Exception {
        System.out.println("----------------------------------------------------------------------------------------------------");
        final boolean debug = Logger.isDebug();
        Logger.v();
        Logger.d();
        Logger.i();
        Logger.w();
        Logger.e();
        Logger.setDebug(!debug);
        Logger.v();
        Logger.d();
        Logger.i();
        Logger.w();
        Logger.e();
        Logger.setDebug(debug);
        System.out.println("----------------------------------------------------------------------------------------------------");
    }
}
