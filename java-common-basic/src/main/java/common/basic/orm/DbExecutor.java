package common.basic.orm;

import common.basic.databases.DatabaseManager;
import common.basic.utils.ResultSetUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class DbExecutor {

    private ResultSet executeQuery(String queryString, List<Object> listValue) {
        Connection connection = null;
        try {
            connection = DatabaseManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(queryString);

            for (int i = 0; i < listValue.size(); ++i) {
                preparedStatement.setObject(i + 1, listValue.get(i));
            }

            return preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            if (null != connection) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public List<Map<String, Object>> selectList(QuerySelect querySelect) {
        String queryString = querySelect.toString();

        List<Object> listValue = querySelect.listValueForBind();

        ResultSet resultSet = executeQuery(queryString, listValue);

        return ResultSetUtil.getListMap(resultSet);
    }

    public <T> List<T> selectListT(QuerySelect querySelect, Class<T> clazz)  {
        String queryString = querySelect.toString();

        List<Object> listValue = querySelect.listValueForBind();

        ResultSet resultSet = executeQuery(queryString, listValue);

        return ResultSetUtil.getListT(resultSet, clazz);
    }

    public <T> T selectOneT(QuerySelect querySelect, Class<T> clazz)  {
        String queryString = querySelect.toString();

        List<Object> listValue = querySelect.listValueForBind();

        ResultSet resultSet = executeQuery(queryString, listValue);

        return ResultSetUtil.getFirstRowT(resultSet, clazz);
    }




}
