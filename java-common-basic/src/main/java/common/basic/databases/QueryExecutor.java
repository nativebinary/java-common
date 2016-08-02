package common.basic.databases;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QueryExecutor {

    public static PreparedStatement createPreparedStatement(String query, Object... arrayParam) throws SQLException {
        final Connection connection = DatabaseManager.getConnection();
        final PreparedStatement preparedStatement = connection.prepareStatement(query);

        int index = 0;
        for (Object param : arrayParam)
            preparedStatement.setObject(++index, param);
        return preparedStatement;
    }

    public static ResultSet executeQuery(String query, Object... arrayParam) throws SQLException {
        final PreparedStatement preparedStatement = createPreparedStatement(query, arrayParam);
        return preparedStatement.executeQuery();
    }

    public static int executeUpdate(String query, Object... arrayParam) throws SQLException {
        final PreparedStatement preparedStatement = createPreparedStatement(query, arrayParam);
        return preparedStatement.executeUpdate();
    }

}
