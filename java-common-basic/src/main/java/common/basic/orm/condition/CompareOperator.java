package common.basic.orm.condition;

import common.basic.utils.StringUtil;

import java.util.List;

public enum CompareOperator {
    Equal {
        @Override
        public String toString(String field, Object value) {
            return String.format(" %s = %s", field, value);
        }
    },
    NotEqual {
        @Override
        public String toString(String field, Object value) {
            return String.format(" %s <> %s", field, value);
        }
    },
    LessThen {
        @Override
        public String toString(String field, Object value) {
            return String.format(" %s < %s", field, value);
        }
    },
    LessEqual {
        @Override
        public String toString(String field, Object value) {
            return String.format(" %s <= %s", field, value);
        }
    },
    GraterThen {
        @Override
        public String toString(String field, Object value) {
            return String.format(" %s > %s", field, value);
        }
    },
    GraterEqual {
        @Override
        public String toString(String field, Object value) {
            return String.format(" %s >= %s", field, value);
        }
    },
    Like {
        @Override
        public String toString(String field, Object value) {
            return String.format(" %s LIKE %s", field, value);
        }
    },
    LikeLeft {
        @Override
        public String toString(String field, Object value) {
            return String.format(" %s LIKE %s", field, value);
        }
    },
    LikeRight {
        @Override
        public String toString(String field, Object value) {
            return String.format(" %s LIKE %s", field, value);
        }
    },
    In {
        @Override
        public String toString(String field, Object value) {
            StringBuilder sb = new StringBuilder();
            sb.append(field);
            sb.append(" IN (");

            if (value instanceof List) {
                List list = (List) value;
                sb.append(StringUtil.repeat("?,", list.size()));
                sb.deleteCharAt(sb.length() - 1);
            } else {
                sb.append(value.toString());
            }

            sb.append(")");

            return sb.toString();
        }
    };

    public abstract String toString(String field, Object value);

    public String toString(String field) {
        return toString(field, "?");
    }
}
