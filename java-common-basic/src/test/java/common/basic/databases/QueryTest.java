package common.basic.databases;

import common.basic.utils.ReflectionUtil;
import org.junit.Assert;
import org.junit.Test;

import java.util.Map;

public class QueryTest extends Assert{

    @Test
    public void testMakeQueryInsert() throws Exception {

        TestMock testMock = new TestMock();
        testMock.i = 1;
        testMock.s = "s";

        final Query query = new Query();
        final Map<String,Object> map = ReflectionUtil.toMap(testMock);
        query.setAll(map);

        assertEquals("INSERT INTO TestMock (s,i) VALUES (?,?)", query.makeQueryStringInsert(TestMock.class));
    }

    @Test
    public void testMakeQueryUpdate() throws Exception {

        TestMock testMock = new TestMock();
        testMock.i = 1;
        testMock.s = "s";

        final Query query = new Query();
        query.setAll(testMock).eq("s", "s");

        assertEquals("UPDATE TestMock SET s=?,i=? WHERE s=?", query.makeQueryStringUpdate(TestMock.class));
    }

    @Test
    public void testMakeQueryStringSelect1() throws Exception {
        final Query query = new Query();
        assertEquals("SELECT * FROM TestMock", query.makeQueryStringSelect(TestMock.class));
    }

    @Test
    public void testMakeQueryStringSelect2() throws Exception {
        final Query query = new Query()
                .eq("i", 10)
                .ne("i", 20)
                .lt("i", 30)
                .gt("i", 40)
                .le("i", 50)
                .ge("i", 60)
                .like("i", 70)
                .orderBy("s")
                .limit(80, 90)
                ;
        assertEquals("SELECT s,i FROM TestMock WHERE i=? AND i<>? AND i<? AND ?<i AND i<=? AND ?<=i AND i LIKE ? ORDER BY s LIMIT ?, ?", query.makeQueryStringSelect(TestMock.class, "s", "i"));


        final String s = query.makeQueryStringDelete(TestMock.class);

//        TestMock testMock = query.selectOne(TestMock.class);
//        query.update(TestMock.class);
//        query.delete(TestMock.class);
//
//        final Connection connection = ConnectionPool.get();
//        final PreparedStatement preparedStatement = connection.prepareStatement(s);
//        query.bindParameter(preparedStatement);
//        preparedStatement.execute();
    }

    @Test
    public void testMakeQueryDelete() throws Exception {

        final Query query = new Query();
        query.eq("s", "s");

        assertEquals("DELETE FROM TestMock WHERE s=?", query.makeQueryStringDelete(TestMock.class));
    }

}
