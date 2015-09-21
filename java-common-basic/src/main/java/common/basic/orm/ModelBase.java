package common.basic.orm;

public class ModelBase {
    public QuerySelect querySelect() {
        return new QuerySelect(this.getClass().getSimpleName());
    }

    public QueryUpdate queryUpdate() {
        return new QueryUpdate(this.getClass().getSimpleName());
    }
}
