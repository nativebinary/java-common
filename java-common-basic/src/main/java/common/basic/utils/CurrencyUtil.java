package common.basic.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class CurrencyUtil {
    public CurrencyUtil() throws InstantiationException {
        throw new InstantiationException();
    }

    static NumberFormat formatterMoneyWithoutComma = new DecimalFormat("0");
    static NumberFormat formatterUSDwithoutComma = new DecimalFormat("0.00");
    public static synchronized String format(BigDecimal value) {
        if ((double)value.longValue() == value.doubleValue())
        {
            return formatterMoneyWithoutComma.format(value);
        }
        return formatterUSDwithoutComma.format(value.setScale(2, BigDecimal.ROUND_UP));
    }

    static NumberFormat formatterMoneyWithComma = new DecimalFormat("#,##0");
    static NumberFormat formatterUSDwithComma = new DecimalFormat("#,##0.00");
    public static synchronized String formatWithComma(BigDecimal value) {
        if ((double)value.longValue() == value.doubleValue())
        {
            return formatterMoneyWithComma.format(value);
        }
        return formatterUSDwithComma.format(value.setScale(2, BigDecimal.ROUND_UP));
    }

    public static BigDecimal ceil(final String currency, final BigDecimal price) {
        if (currency.equals("TWD")) {
            return price.setScale(0, BigDecimal.ROUND_UP);
        }
        else if (currency.equals("USD")) {
            return price.setScale(2, BigDecimal.ROUND_UP);
        }
        else if (currency.equals("HKD")) {
            return price.setScale(0, BigDecimal.ROUND_UP);
        }
        else if (currency.equals("MOP")) {
            return price.setScale(0, BigDecimal.ROUND_UP);
        }
        else if (currency.equals("CNY")) {
            return price.setScale(-1, BigDecimal.ROUND_UP);
        }
        else {
            return price.setScale(-2, BigDecimal.ROUND_UP);
        }
    }
}
