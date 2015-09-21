package common.basic.orm;

import common.basic.utils.StringUtil;

import java.util.ArrayList;
import java.util.List;

public class QueryUpdate {

    public static class FieldUpdate {
        public String field;
        public Object value;

        public FieldUpdate(String field, String value) {
            this.field = field;
            this.value = value;
        }

        @Override
        public String toString() {
            return String.format("%s=?", field);
        }

        public void bindValueAddTo(ArrayList<Object> listValue) {
            listValue.add(value);
        }
    }

    private final String table;

    private final List<FieldUpdate> listField = new ArrayList<FieldUpdate>();

    WhereBase<QueryUpdate> whereBase;

    public QueryUpdate(String table) {
        this.table = table;
        this.whereBase = new WhereBase<QueryUpdate>(this);
    }

    public List<Object> listValueForBind() {
        ArrayList<Object> listValue = new ArrayList<Object>();

        for (FieldUpdate fieldUpdate : listField)
            fieldUpdate.bindValueAddTo(listValue);

        whereBase.bindingValueAddTo(listValue);

        return listValue;
    }

    public boolean hasWhere() {
        return whereBase.hasValue();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("UPDATE ");
        sb.append(table);

        sb.append(" SET ");
        sb.append(StringUtil.joinWithToString(",", listField));

        if (hasWhere()) {
            sb.append(" WHERE ");
            sb.append(whereBase.toString());
        }

        return sb.toString();
    }

    public WhereBase<QueryUpdate> where() {
        return whereBase;
    }

    public QueryUpdate set(String field, String value) {
        listField.add(new FieldUpdate(field, value));
        return this;
    }

    public QueryUpdate traceBindValues() {
        List<Object> listValue = listValueForBind();
        System.out.println("[" + StringUtil.joinWithToString("], [", listValue) + "]");
        return this;
    }
}
