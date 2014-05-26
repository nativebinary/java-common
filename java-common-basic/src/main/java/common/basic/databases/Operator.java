package common.basic.databases;

enum Operator {
    Equals {
        @Override
        public String toWhere(String fieldName, Object value) {
            return fieldName + "=?";
        }
    },
    NotEquals {
        @Override
        public String toWhere(String fieldName, Object value) {
            return fieldName + "<>?";
        }
    },
    LessThan {
        @Override
        public String toWhere(String fieldName, Object value) {
            return fieldName + "<?";
        }
    },
    GreaterThan {
        @Override
        public String toWhere(String fieldName, Object value) {
            return "?<" + fieldName;
        }
    },
    LessThanOrEquals {
        @Override
        public String toWhere(String fieldName, Object value) {
            return fieldName + "<=?";
        }
    },
    GreaterThanOrEquals {
        @Override
        public String toWhere(String fieldName, Object value) {
            return "?<=" + fieldName;
        }
    },
    Like {
        @Override
        public String toWhere(String fieldName, Object value) {
            return fieldName + " LIKE ?";
        }
    },
    ;

    public abstract String toWhere(String fieldName, Object value);
}
