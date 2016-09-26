package common.basic.interfaces;

import java.math.BigDecimal;

public interface ICallbackBigDecimal<T> {
    BigDecimal getValue(T t);
}
