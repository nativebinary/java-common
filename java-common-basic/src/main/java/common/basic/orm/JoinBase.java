package common.basic.orm;

import common.basic.orm.condition.FieldFieldCompare;

public class JoinBase {
    public enum JoinType {
        InnerJoin {
            @Override
            public String toQuery() {
                return " INNER JOIN";
            }
        },
        LeftJoin {
            @Override
            public String toQuery() {
                return " LEFT JOIN";
            }
        },
        RightJoin {
            @Override
            public String toQuery() {
                return " RIGHT JOIN";
            }
        },
        FullOuterJoin {
            @Override
            public String toQuery() {
                return " OUTER JOIN";
            }
        },;

        public abstract String toQuery();
    }

    public JoinType joinType;
    public String table;
    public FieldFieldCompare fieldFieldCompare;

    public JoinBase(String table) {
        this.table = table;
    }

    public String toString() {
        return " " + table + " ON" + fieldFieldCompare.toString();
    }


}
