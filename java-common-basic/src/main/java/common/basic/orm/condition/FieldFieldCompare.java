package common.basic.orm.condition;

public class FieldFieldCompare {

    public String leftField;
    public String rightField;
    public CompareOperator compareOperator;

    public FieldFieldCompare(String leftField, CompareOperator compareOperator, String rightField) {
        this.leftField = leftField;
        this.compareOperator = compareOperator;
        this.rightField = rightField;
    }

    public String toString() {
        return compareOperator.toString(leftField, rightField);
    }

}
