package common.basic.databases;

import java.sql.Connection;

public class DatabaseManager {
    public static interface IProvider<T> {
        Connection getConnection();
        Class getKeyAnnotation();
    }

    static IProvider provider;

    public static IProvider initialize(IProvider connectionFactory) {
        IProvider providerOld = DatabaseManager.provider;
        DatabaseManager.provider = connectionFactory;
        return providerOld;
    }

    public static Connection getConnection() {
        return provider.getConnection();
    }

    public static Class getKeyAnnotation()
    {
        return provider.getKeyAnnotation();
    }

    public static boolean isReady() {
        return null != provider;
    }
}
