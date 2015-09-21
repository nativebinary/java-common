package common.basic.orm;

import common.basic.orm.condition.IChainCondition;
import common.basic.orm.condition.LogicalOperator;

class ChainCondition {

    public static IChainCondition And = new IChainCondition() {
        @Override
        public LogicalOperator logicalOperator() {
            return LogicalOperator.And;
        }

        @Override
        public String toString() {
            return logicalOperator().toString();
        }
    };

    public static IChainCondition Or = new IChainCondition() {
        @Override
        public LogicalOperator logicalOperator() {
            return LogicalOperator.Or;
        }

        @Override
        public String toString() {
            return logicalOperator().toString();
        }
    };

}
