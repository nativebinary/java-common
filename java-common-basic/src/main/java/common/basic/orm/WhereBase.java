package common.basic.orm;

import common.basic.orm.condition.CompareOperator;
import common.basic.orm.condition.FieldValueCompare;
import common.basic.orm.condition.ICondition;
import common.basic.orm.condition.IFieldValueCompare;
import common.basic.utils.StringUtil;

import java.util.ArrayList;
import java.util.List;

public class WhereBase<T> {

    public List<ICondition> listCondition = new ArrayList<ICondition>();

    @Override
    public String toString() {

        if (null == listCondition || 0 == listCondition.size())
            return "";

        return " (" + StringUtil.joinWithToString("", listCondition) + ")";
    }

    public void bindingValueAddTo(List<Object> listValue) {
        for (ICondition condition : listCondition) {
            if (condition instanceof IFieldValueCompare)
                ((IFieldValueCompare)condition).bindingValueAddTo(listValue);
        }
    }

    public boolean hasValue() {
        return null != listCondition && 0 < listCondition.size();
    }

    private final T queryBase;

    public WhereBase(T queryBase) {
        this.queryBase = queryBase;
    }

    public T endWhere() {
        return queryBase;
    }

    private WhereBase<T> and(IFieldValueCompare fieldValueCompare) {

        if (0 < listCondition.size())
            listCondition.add(ChainCondition.And);

        listCondition.add(fieldValueCompare);
        return this;
    }

    public WhereBase<T> eq(String field, Object value) {
        return and(new FieldValueCompare(field, CompareOperator.Equal, value));
    }

    public WhereBase<T> ne(String field, String value) {
        return and(new FieldValueCompare(field, CompareOperator.NotEqual, value ));
    }

    public WhereBase<T> lt(String field, String value) {
        return and(new FieldValueCompare(field, CompareOperator.LessThen, value ));
    }

    public WhereBase<T> le(String field, String value) {
        return and(new FieldValueCompare(field, CompareOperator.LessEqual, value ));
    }

    public WhereBase<T> gt(String field, String value) {
        return and(new FieldValueCompare(field, CompareOperator.GraterThen, value ));
    }

    public WhereBase<T> ge(String field, String value) {
        return and(new FieldValueCompare(field, CompareOperator.GraterEqual, value ));
    }

    public WhereBase<T> like(String field, String value) {
        return and(new FieldValueCompare(field, CompareOperator.Like, "%" + value + "%"));
    }

}
