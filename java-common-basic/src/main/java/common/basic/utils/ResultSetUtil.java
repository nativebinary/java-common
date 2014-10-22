package common.basic.utils;

import common.basic.logs.Logger;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResultSetUtil {

    public ResultSetUtil() throws InstantiationException {
        throw new InstantiationException();
    }

    public static Map<Object, Object> getMap(ResultSet resultSet) {

        Map<Object, Object> map = new HashMap<Object, Object>();
        try {
            while(resultSet.next())
            {
                Object key = resultSet.getObject(1);
                Object value = resultSet.getObject(2);

                map.put(key, value);
            }
        } catch (SQLException e) {
            Logger.e(e);
        }
        return map;
    }


    public static List<Map<String, Object>> getListMap(ResultSet resultSet) {
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		try {
			while(resultSet.next())
			{
				HashMap<String, Object> hashMap = new HashMap<String, Object>();
				
				ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
				int iColumnCount = resultSetMetaData.getColumnCount();
				
				for (int i = 1; i <= iColumnCount; i++) {
					String columnName = resultSetMetaData.getColumnLabel(i);
					Object value = resultSet.getObject(i);
					hashMap.put(columnName, value);
				}
				
				listMap.add(hashMap);
			}
		} catch (SQLException e) {
			Logger.e(e);
		}
		return listMap;
	}

    public static Map<Object, Map<String, Object>> getMapMap(ResultSet resultSet, String keyFieldName) {
        Map<Object, Map<String, Object>> mapMap = new HashMap<Object, Map<String, Object>>();
        try {
            while(resultSet.next())
            {
                HashMap<String, Object> hashMap = new HashMap<String, Object>();

                ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
                int iColumnCount = resultSetMetaData.getColumnCount();

                for (int i = 1; i <= iColumnCount; i++) {
                    String columnName = resultSetMetaData.getColumnLabel(i);
                    Object value = resultSet.getObject(i);
                    hashMap.put(columnName, value);
                }

                Object idValue = hashMap.get(keyFieldName);

                mapMap.put(idValue, hashMap);
            }
        } catch (SQLException e) {
            Logger.e(e);
        }
        return mapMap;
    }


    public static List<List<Object>> getListList(ResultSet resultSet) {
		List<List<Object>> listList = new ArrayList<List<Object>>();
		try {
			while(resultSet.next())
			{
				List<Object> list = new ArrayList<Object>();
				
				ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
				int iColumnCount = resultSetMetaData.getColumnCount();
				
				for (int i = 1; i <= iColumnCount; i++) {
					String columnName = resultSetMetaData.getColumnName(i);
					Object value = resultSet.getObject(i);
					list.add(value);
				}
				
				listList.add(list);
			}
		} catch (SQLException e) {
			Logger.e(e);
		}
		return listList;
	}

    public static List<Object> getListFirstColumn(ResultSet resultSet) {
        List<Object> list= new ArrayList<Object>();
        try {
            while(resultSet.next())
            {
                list.add(resultSet.getObject(1));
            }
        } catch (SQLException e) {
            Logger.e(e);
        }
        return list;
    }

    public static List<String> getListFirstStringColumn(ResultSet resultSet) {
        List<String> list= new ArrayList<String>();
        try {
            while(resultSet.next())
            {
                list.add(resultSet.getString(1));
            }
        } catch (SQLException e) {
            Logger.e(e);
        }
        return list;
    }

	public static int getScalarInt(ResultSet resultSet) {
		try {
			if(resultSet.next())
				return resultSet.getInt(1);
		} catch (SQLException e) {
			Logger.e(e);  //To change body of catch statement use File | Settings | File Templates.
		}
		return 0;
	}

    public static long getScalarDate(ResultSet resultSet) {
        try {
            if(resultSet.next())
                return resultSet.getTimestamp(1).getTime();
        } catch (SQLException e) {
            Logger.e(e);  //To change body of catch statement use File | Settings | File Templates.
        }
        return 0;
    }

	public static long getScalarLong(ResultSet resultSet) {
		try {
			if(resultSet.next())
				return resultSet.getLong(1);
		} catch (SQLException e) {
			Logger.e(e);  //To change body of catch statement use File | Settings | File Templates.
		}
		return 0;

	}

    public static List<Long> getListFirstLongColumn(ResultSet resultSet) {
        return null;  //To change body of created methods use File | Settings | File Templates.
    }

    public static Date getDate(ResultSet resultSet, String fieldName) throws SQLException {
        Timestamp timestamp = resultSet.getTimestamp(fieldName);
        if(null == timestamp)
            return null;

        return new Date(timestamp.getTime());
    }
}
	