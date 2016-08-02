package common.basic.databases;

import common.basic.utils.ArrayUtil;
import common.basic.utils.Pair;
import common.basic.utils.ReflectionUtil;
import common.basic.utils.ResultSetUtil;
import common.basic.utils.StringUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Query {
    List<Pair<String, Object>> listPair = new ArrayList<Pair<String, Object>>();
    List<Filter> listFilter;
    String orderByClause;
    int offset;
    int limit;

    public Query() {
        this.listFilter = new ArrayList<Filter>();
        this.orderByClause = StringUtil.empty;
        this.offset = -1;
        this.limit = -1;
    }




    //<editor-fold desc="public Query SET clauses {...}">
    public Query set(String fieldName, Object value) {
        listPair.add(new Pair<String, Object>(fieldName, value));
        return this;
    }

    public <T> Query setAll(T t) {
        final Map<String, Object> map = ReflectionUtil.toMap(t);
        setAll(map);
        return this;
    }

    public Query setAll(Map<String, Object> map) {
        this.listPair.clear();

        for (String key : map.keySet()) {
            this.listPair.add(new Pair<String, Object>(key, map.get(key)));
        }

        return this;
    }
    //</editor-fold>

    //<editor-fold desc="public Query WHERE clause {...}">
    public Query eq(String fieldName, Object value) {
        listFilter.add(new Filter(fieldName, Operator.Equals, value));
        return this;
    }

    public Query ne(String fieldName, Object value) {
        listFilter.add(new Filter(fieldName, Operator.NotEquals, value));
        return this;
    }

    public Query lt(String fieldName, Object value) {
        listFilter.add(new Filter(fieldName, Operator.LessThan, value));
        return this;
    }

    public Query gt(String fieldName, Object value) {
        listFilter.add(new Filter(fieldName, Operator.GreaterThan, value));
        return this;
    }

    public Query le(String fieldName, Object value) {
        listFilter.add(new Filter(fieldName, Operator.LessThanOrEquals, value));
        return this;
    }

    public Query ge(String fieldName, Object value) {
        listFilter.add(new Filter(fieldName, Operator.GreaterThanOrEquals, value));
        return this;
    }

    public Query like(String fieldName, Object value) {
        listFilter.add(new Filter(fieldName, Operator.Like, value));
        return this;
    }
    //</editor-fold>

    public Query orderBy(String orderByClause) {
        this.orderByClause = orderByClause;
        return this;
    }

    public Query limit(int offset, int count) {
        this.offset = offset;
        this.limit = count;
        return this;
    }

    private void appendWhere(StringBuilder sb) {
        if (listFilter.size() != 0) {
            List<String> listFilterString = new ArrayList<String>();

            for (Filter filter : listFilter) {
                listFilterString.add(filter.toWhere());
            }

            sb.append(" WHERE ");
            sb.append(StringUtil.join(" AND ", listFilterString));
        }
    }



    private List<String> getKeySet() {
        List<String> listKey = new ArrayList<String>();
        for (Pair<String, Object> pair : listPair) {
            listKey.add(pair.getKey());
        }
        return listKey;
    }

    //<editor-fold desc="String makeQueryString...() {...}">
    <T> String makeQueryStringInsert(Class<T> clazz) {
        List<String> listKey = getKeySet();


        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO ");
        sb.append(clazz.getSimpleName());
        sb.append(" (");
        sb.append(StringUtil.join(",", listKey));
        sb.append(") VALUES (");
        final int size = listPair.size();
        for (int i = 0; i < size; ++i) {
            sb.append("?,");
        }

        sb.deleteCharAt(sb.length() - 1);
        sb.append(")");

        return sb.toString();
    }

    <T> String makeQueryStringUpdate(Class<T> clazz) {

        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE ");
        sb.append(clazz.getSimpleName());
        sb.append(" SET ");

        List<String> listKey = getKeySet();
        for (String fieldName : listKey) {
            sb.append(fieldName);
            sb.append("=?,");
        }
        sb.deleteCharAt(sb.length() - 1);
        appendWhere(sb);

        return sb.toString();
    }

    <T> String makeQueryStringSelect(Class<T> clazz, String... arrayField) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        if(ArrayUtil.isNullOrEmpty(arrayField))
        {
            sb.append("*");
        }
        else
        {
            sb.append(StringUtil.join(",", arrayField));
        }
        sb.append(" FROM ");
        sb.append(clazz.getSimpleName());
        appendWhere(sb);

        if (!StringUtil.isNullOrEmpty(orderByClause))
            sb.append(" ORDER BY ").append(orderByClause);

        if (offset != -1 && limit != -1) {
            sb.append(" LIMIT ?, ?");
        }

        return sb.toString();
    }

    <T> String makeQueryStringDelete(Class<T> clazz) {
        StringBuilder sb = new StringBuilder();
        sb.append("DELETE FROM ");
        sb.append(clazz.getSimpleName());

        appendWhere(sb);

        return sb.toString();
    }
    //</editor-fold>

    public int bindParameter(PreparedStatement preparedStatement) throws SQLException {
        int index = 1;

        for (Pair<String, Object> pair : listPair) {
            preparedStatement.setObject(index++, pair.getValue());
        }

        for (Filter filter : listFilter) {
            preparedStatement.setObject(index++, filter.getValue());
        }

        return index;
    }

    public <T> long insert(Class<T> clazz) throws SQLException {

        if (listPair.size() == 0) {
            return -1;
        }

        String queryString = makeQueryStringInsert(clazz);

        final Connection connection = DatabaseManager.getConnection();
        final PreparedStatement preparedStatement = connection.prepareStatement(queryString, Statement.RETURN_GENERATED_KEYS);

        bindParameter(preparedStatement);

        final int i = preparedStatement.executeUpdate();

        if (i == 0) {
            throw new SQLException("Insert Failed : " + queryString);
        }

        ResultSet rs = preparedStatement.getGeneratedKeys();
        if (rs != null && rs.next()) {
            return rs.getLong(1);
        }

        return -1;
    }

    public <T> int update(Class<T> clazz) throws SQLException {
        if (listPair.size() == 0) {
            return -1;
        }

        String query = makeQueryStringUpdate(clazz);

        final Connection connection = DatabaseManager.getConnection();
        final PreparedStatement preparedStatement = connection.prepareStatement(query);

        bindParameter(preparedStatement);

        return preparedStatement.executeUpdate();
    }

    private <T> ResultSet select(Class<T> clazz, String... arrayField) throws SQLException {
        final String queryString = makeQueryStringSelect(clazz, arrayField);

        final Connection connection = DatabaseManager.getConnection();
        final PreparedStatement preparedStatement = connection.prepareStatement(queryString);
        int index = bindParameter(preparedStatement);

        if (offset != -1 && limit != -1) {
            preparedStatement.setObject(index++, offset);
            preparedStatement.setObject(index++, limit);
        }

        return preparedStatement.executeQuery();
    }

    public <T> int delete(Class<T> clazz) throws SQLException {

        if (listPair.size() == 0) {
            return -1;
        }

        String query = makeQueryStringDelete(clazz);

        final Connection connection = DatabaseManager.getConnection();
        final PreparedStatement preparedStatement = connection.prepareStatement(query);

        bindParameter(preparedStatement);

        return preparedStatement.executeUpdate();
    }




    public <T> T selectOne(Class<T> clazz) throws SQLException {

        limit(0, 1);

        final List<T> list = selectList(clazz);
        if (list.size() == 0) {
            return null;
        }
        return list.get(0);
    }

    public <T> List<T> selectList(Class<T> clazz, String... arrayField) throws SQLException {
        final ResultSet resultSet = select(clazz, arrayField);
        final List<Map<String, Object>> listMap = ResultSetUtil.getListMap(resultSet);
        return ReflectionUtil.fromListMap(clazz, listMap, DatabaseManager.getKeyAnnotation());
    }
}
