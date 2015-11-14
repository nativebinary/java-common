package common.basic.orm;

import common.basic.orm.annotation.AutoIncrement;
import org.junit.Assert;
import org.junit.Test;

public class ModelBaseTest extends Assert {

    public static class TestModel extends ModelBase {
        @AutoIncrement
        public int i;

        public String s;
    }

    @Test
    public void testQuerySelect() {
        TestModel testModel = new TestModel();

        assertEquals("SELECT * FROM TestModel", testModel.querySelect().toString());

        assertEquals("SELECT * FROM TestModel WHERE ( Field1 = ?)"
                , testModel.querySelect().where().eq("Field1", "Value1").endWhere().toString());

        QuerySelect querySelect = testModel.querySelect()
                .join("Table2").on("Table1.Field1", "Table2.Field2")
                .join("Table3").on("Table2.field2", "Table3.Field3")
                .endJoin()
                .where()
                .eq("Field1", "Value1")
                .eq("Field2", "Value2")
                .like("Field1", "likeValue1")
                .endWhere()
                .order("Field1", true)
                .limit(5);

        querySelect.traceBindValues();

        assertEquals("SELECT * FROM TestModel INNER JOIN Table2 ON Table1.Field1 = Table2.Field2 INNER JOIN Table3 ON Table2.field2 = Table3.Field3 WHERE ( Field1 = ? AND Field2 = ? AND Field1 LIKE ?) ORDER BY Field1 ASC LIMIT 5"
                , querySelect .toString()
        );


        QueryUpdate queryUpdate = testModel.queryUpdate()
                .set("Field2", "Value2")
                .where().eq("Field1", "Value1").endWhere();

        queryUpdate.traceBindValues();

        assertEquals("UPDATE TestModel SET Field2=? WHERE  ( Field1 = ?)"
                , queryUpdate.toString()
        );

    }
}
