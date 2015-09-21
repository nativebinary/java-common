package common.basic.orm;

import common.basic.orm.condition.CompareOperator;
import common.basic.orm.condition.FieldFieldCompare;
import common.basic.utils.StringUtil;

import java.util.ArrayList;
import java.util.List;

public class QuerySelect {

    public static class Join extends JoinBase {

        private final QuerySelect querySelect;

        public Join(QuerySelect querySelect, String table) {
            super(table);
            this.querySelect = querySelect;
        }

        public QuerySelect endJoin() {
            return querySelect;
        }

        public Join join(String table) {
            return this.querySelect.join(table);
        }


        public Join on(String leftField, String rightField) {
            fieldFieldCompare = new FieldFieldCompare(leftField, CompareOperator.Equal, rightField);
            return this;
        }
    }


    String table;
    List<JoinBase> listJoin = new ArrayList<JoinBase>();
    WhereBase<QuerySelect> where;
    List<Order> listOrder = new ArrayList<Order>();
    long offset = 0;
    long limit = -1;

    public QuerySelect(String table) {
        this.table = table;
        this.where = new WhereBase<QuerySelect>(this);
    }

    public Join join(String table) {
        Join join = new Join(this, table);
        join.joinType = JoinBase.JoinType.InnerJoin;
        listJoin.add(join);
        return join;
    }

    public WhereBase<QuerySelect> where() {
        return where;
    }

    public QuerySelect order(String field, boolean asc) {
        Order order = new Order();
        order.field = field;
        order.ascending = asc;

        listOrder.add(order);

        return this;
    }


    public List<Object> listValueForBind() {
        ArrayList<Object> listValue = new ArrayList<Object>();
        where.bindingValueAddTo(listValue);
        return listValue;
    }

    public static class Order {
        public String field;
        public boolean ascending;

        @Override
        public String toString() {
            return String.format(" %s %s", field, ascending ? "ASC" : "DESC");
        }
    }

    public boolean hasJoin() {
        return 0 < listJoin.size();
    }

    public boolean hasWhere() {
        return where.hasValue();
    }

    public boolean hasOrder() {
        return 0 < listOrder.size();
    }


    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("SELECT * FROM ");
        sb.append(table);

        if (hasJoin()) {
            for (JoinBase join : listJoin) {
                sb.append(join.joinType.toQuery());
                sb.append(join.toString());
            }
        }

        if (hasWhere()) {
            sb.append(" WHERE");
            sb.append(where.toString());
        }

        if (hasOrder()) {
            sb.append(" ORDER BY");
            sb.append(StringUtil.joinWithToString(",", listOrder));
        }

        if (0 < offset && 0 <= limit) {
            sb.append(" LIMIT ");
            sb.append(offset);
            sb.append(",");
            sb.append(limit);
        } else if (0 <= limit) {
            sb.append(" LIMIT ");
            sb.append(limit);
        }

        return sb.toString();
    }


    public QuerySelect limit(int offset, int limit) {
        this.offset = offset;
        this.limit = limit;

        return this;
    }

    public QuerySelect limit(int limit) {
        return limit(0, limit);
    }

    public QuerySelect traceBindValues() {
        List<Object> listValue = listValueForBind();
        System.out.println("[" + StringUtil.joinWithToString("], [", listValue) + "]");
        return this;
    }
}
