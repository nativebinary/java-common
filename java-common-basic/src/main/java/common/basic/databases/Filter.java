package common.basic.databases;

public class Filter {
    final String fieldName;
    final Operator operator;
    final Object value;

    Filter(String fieldName, Operator operator, Object value) {
        this.fieldName = fieldName;
        this.operator = operator;
        this.value = value;
    }

    public String toWhere() {
        return operator.toWhere(fieldName, value);
    }

    public Object getValue() {
        return value;
    }
}
