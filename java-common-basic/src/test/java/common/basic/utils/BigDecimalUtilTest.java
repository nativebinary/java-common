package common.basic.utils;

import org.junit.Test;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BigDecimalUtilTest {
    @Test
    public void testGeneral() throws Exception {

        BigDecimal value = new BigDecimal(1234.5678);


        assertEquals("1235", value.setScale(0, BigDecimal.ROUND_UP).toString());
        assertEquals("1234", value.setScale(0, BigDecimal.ROUND_DOWN).toString());
        assertEquals("1235", value.setScale(0, BigDecimal.ROUND_CEILING).toString());
        assertEquals("1234", value.setScale(0, BigDecimal.ROUND_FLOOR).toString());
        assertEquals("1235", value.setScale(0, BigDecimal.ROUND_HALF_UP).toString());
        assertEquals("1235", value.setScale(0, BigDecimal.ROUND_HALF_DOWN).toString());
        assertEquals("1235", value.setScale(0, BigDecimal.ROUND_HALF_EVEN).toString());


        BigDecimal value2 = new BigDecimal(1234.1111);

        assertEquals("1234.12", value2.setScale(2, BigDecimal.ROUND_UP).toString());
        assertEquals("1234.11", value2.setScale(2, BigDecimal.ROUND_DOWN).toString());
        assertEquals("1234.12", value2.setScale(2, BigDecimal.ROUND_CEILING).toString());
        assertEquals("1234.11", value2.setScale(2, BigDecimal.ROUND_FLOOR).toString());
        assertEquals("1234.11", value2.setScale(2, BigDecimal.ROUND_HALF_UP).toString());
        assertEquals("1234.11", value2.setScale(2, BigDecimal.ROUND_HALF_DOWN).toString());
        assertEquals("1234.11", value2.setScale(2, BigDecimal.ROUND_HALF_EVEN).toString());

        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        assertEquals("1230.00", decimalFormat.format(value.setScale(-1, BigDecimal.ROUND_HALF_EVEN)));

        assertTrue(BigDecimalUtil.isPlus(new BigDecimal(1)));
        assertTrue(BigDecimalUtil.isMinus(new BigDecimal(-1)));
        assertTrue(BigDecimalUtil.lt(new BigDecimal(1), new BigDecimal(2)));
        assertTrue(BigDecimalUtil.gt(new BigDecimal(2), new BigDecimal(1)));
        assertTrue(BigDecimalUtil.gt(new BigDecimal(-1), new BigDecimal(-2)));
    }

    @Test(expected=ArithmeticException.class)
    public void testGeneralException() throws Exception
    {
        BigDecimal value = new BigDecimal(1234.5678);
        assertEquals("1234", value.setScale(0, BigDecimal.ROUND_UNNECESSARY).toString());
    }
}
