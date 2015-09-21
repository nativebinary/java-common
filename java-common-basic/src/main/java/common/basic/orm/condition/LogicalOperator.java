package common.basic.orm.condition;

public enum LogicalOperator {
    And {
        @Override
        public String toString() {
            return " AND";
        }
    },
    Or {
        @Override
        public String toString() {
            return " OR";
        }
    },;

}
