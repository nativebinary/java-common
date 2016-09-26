package common.basic.utils;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class CurrencyUtilTest {
    @Test
    public void testFormat() throws Exception {
        assertEquals("1", CurrencyUtil.format(new BigDecimal("1")));
        assertEquals("1", CurrencyUtil.format(new BigDecimal(1)));
        assertEquals("1.10", CurrencyUtil.format(new BigDecimal("1.1")));
        assertEquals("0.11", CurrencyUtil.format(new BigDecimal(".101")));
        assertEquals("0.11", CurrencyUtil.format(new BigDecimal(0.101)));

        assertEquals("1000.11", CurrencyUtil.format(new BigDecimal(1000.101)));

    }
    @Test
    public void testFormatWithComma() throws Exception {
        assertEquals("1", CurrencyUtil.formatWithComma(new BigDecimal("1")));
        assertEquals("1", CurrencyUtil.formatWithComma(new BigDecimal(1)));
        assertEquals("1.10", CurrencyUtil.formatWithComma(new BigDecimal("1.1")));
        assertEquals("0.11", CurrencyUtil.formatWithComma(new BigDecimal(".101")));
        assertEquals("0.11", CurrencyUtil.formatWithComma(new BigDecimal(0.101)));

        assertEquals("1,000.11", CurrencyUtil.formatWithComma(new BigDecimal(1000.101)));

    }


    @Test
    public void testCompare() throws Exception {
        assertEquals(true, CurrencyUtil.format(new BigDecimal("1")));
        assertEquals("1", CurrencyUtil.format(new BigDecimal(1)));
        assertEquals("1.10", CurrencyUtil.format(new BigDecimal("1.1")));
        assertEquals("0.11", CurrencyUtil.format(new BigDecimal(".101")));
        assertEquals("0.11", CurrencyUtil.format(new BigDecimal(0.101)));
    }
}
