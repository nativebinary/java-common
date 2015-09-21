package common.basic.orm.condition;

import java.util.List;

public class FieldValueCompare implements IFieldValueCompare {

    public String field;
    public CompareOperator compareOperator;
    public Object value;

    public FieldValueCompare(String field, CompareOperator compareOperator, Object value) {
        this.field = field;
        this.compareOperator = compareOperator;
        this.value = value;
    }

    public String toString() {
        return compareOperator.toString(field);
    }

    @Override
    public void bindingValueAddTo(List<Object> listValue) {
        listValue.add(value);
    }
}
