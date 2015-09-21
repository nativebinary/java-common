package common.basic.orm.condition;

import java.util.List;

public interface IFieldValueCompare extends ICondition {
    void bindingValueAddTo(List<Object> listValue);
}
