package service;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.Vector;

public class SQLService
{

    private String tableName;
    private DBConnect conn;
    private Statement aStatement;

    public SQLService(String tableName)
    {
        this.tableName = tableName;
        conn = new DBConnect();
        conn.getConnect(); // 做資料庫連接
        aStatement = conn.getStatement();

    }

    // 拿到Table內的所有data
    public Vector<Vector<Object>> getAllTableData()
    {

        String sql = "SELECT * FROM " + tableName;
        Vector<Vector<Object>> data = new Vector<>();

        try
        {
            ResultSet rs = aStatement.executeQuery(sql);
            ResultSetMetaData metaData = rs.getMetaData();

            int columnCount = metaData.getColumnCount();

            while (rs.next())
            {

                Vector<Object> row = new Vector<>();

                for (int i = 1; i <= columnCount; i++)
                {

                    Object obj = rs.getObject(i);
                    row.add(obj);

                }
                data.add(row);

            }

        } catch (SQLException e)
        {
            System.out.println("查詢全部資料時發生錯誤");
            e.printStackTrace();
        }

        return data;
    }

    // 條件搜尋
    public Vector<Vector<Object>> getTableData(String where)
    {

        String sql = "SELECT * FROM " + tableName + where;
        Vector<Vector<Object>> data = new Vector<>();

        try
        {
            ResultSet rs = aStatement.executeQuery(sql);
            ResultSetMetaData metaData = rs.getMetaData();

            int columnCount = metaData.getColumnCount();

            while (rs.next())
            {

                Vector<Object> row = new Vector<>();

                for (int i = 1; i <= columnCount; i++)
                {

                    Object obj = rs.getObject(i);
                    row.add(obj);

                }
                data.add(row);

            }

        } catch (SQLException e)
        {
            System.out.println("查詢全部資料時發生錯誤");
            e.printStackTrace();
        }

        return data;
    }

    // 獲得table欄位名稱
    public Vector<String> getColumnName()
    {

        String sql = "desc " + tableName;
        Vector<String> columnName = new Vector<>(); // 儲存欄位名稱
        try
        {
            ResultSet rs = aStatement.executeQuery(sql);

            while (rs.next())
            {
                columnName.add(rs.getString(1));
            }

        } catch (SQLException e)
        {
            System.out.println("取得欄位名稱發生錯誤");
            e.printStackTrace();
        }

        return columnName;

    }

    // 新增資料
    public int add(LinkedHashMap<String, String> map)
    {
        // 拼接SQL語法
        StringBuilder sql = new StringBuilder("INSERT INTO " + tableName + " SET ");

        for (Entry<String, String> entry : map.entrySet())
        {
            String key = entry.getKey();
            String value = entry.getValue();

            if (value.equals(""))
            {
                value = "default"; // 如果是空字串，用default
            } else
            {
                value = "'" + value + "'"; // 每個值前後都加上''
            }
            sql.append(key + " = " + value + ",");

        }

        sql.deleteCharAt(sql.length() - 1); // 刪除最後多的,號
        System.out.println(sql); // 檢查用print

        // 新增動作
        int isSuccess = 0; // 紀錄受影響列數
        try
        {
            isSuccess = aStatement.executeUpdate(sql.toString());

        } catch (SQLException e)
        {
            System.out.println("新增資料失敗，發生錯誤");
            e.printStackTrace();
        }

        return isSuccess;
    }

    // 更新
    public int updata(LinkedHashMap<String, String> dataMap, Vector<String> primarykey, Vector<String> keyValue)
    {
        // 拼接SQL語法
        StringBuilder sql = new StringBuilder("UPDATE " + tableName + " SET ");

        for (Entry<String, String> entry : dataMap.entrySet())
        {
            String key = entry.getKey();
            String value = entry.getValue();

            if (value.equals(""))
            {
                value = "default"; // 如果是空字串，用default
            } else
            {
                value = "'" + value + "'"; // 每個值前後都加上''
            }
            sql.append(key + " = " + value + ",");

        }
        sql.deleteCharAt(sql.length() - 1); // 刪掉最後多的,號

        sql.append(" WHERE ");

        for (int i = 0; i < primarykey.size(); i++)
        {
            sql.append(primarykey.get(i) + " = " + "'" + keyValue.get(i) + "'" + " AND ");
        }

        sql.delete(sql.length() - 4, sql.length() - 1); // 刪掉最後的AND

        System.out.println(sql);

        // 更新動作
        int isSuccess = 0;
        try
        {
            isSuccess = aStatement.executeUpdate(sql.toString());

        } catch (SQLException e)
        {
            System.out.println("更新資料表資料失敗");
            e.printStackTrace();
        }

        return isSuccess;
    }

    // 獲得PK
    public Vector<String> getPrimaryKey()
    {
        Vector<String> keyList = new Vector<>();
        try
        {

            ResultSet rs = aStatement.executeQuery("desc " + tableName);
            while (rs.next())
            {
                String primaryKey = rs.getString(4);
                if (primaryKey.equals("PRI"))
                {
                    keyList.add(rs.getString(1));
                }

            }

        } catch (SQLException e)
        {
            System.out.println("獲取主鍵失敗");
            e.printStackTrace();
        }

        return keyList;
    }

    // 刪除
    public int del(Vector<String> primaryKey, Vector<String> value)
    {
        // SQL拼接
        String sql = "DELETE FROM " + tableName + " WHERE ";
        String concate = "";
        for (int i = 0; i < primaryKey.size(); i++)
        {
            concate = primaryKey.get(i) + " = '" + value.get(i) + "'";
        }
        sql += concate;

        // 刪除動作
        int isSuccess = 0;
        try
        {
            isSuccess = aStatement.executeUpdate(sql);
        } catch (SQLException e)
        {
            System.out.println("刪除資料發生錯誤");
            e.printStackTrace();
        }

        return isSuccess;
    }

    // 獲得table欄位的所有資訊
    public Vector<Vector<Object>> getTableDesc()
    {
        Vector<Vector<Object>> list = new Vector<>();

        try
        {

            ResultSet rs = aStatement.executeQuery("desc " + tableName);
            while (rs.next())
            {
                Vector<Object> keyList = new Vector<>();
                String columnName = rs.getString(1);
                keyList.add(columnName); // 加入欄位名

                String type = rs.getString(2);
                if (type.matches("varchar.*") || type.matches("char.*"))
                {
                    keyList.add("java.lang.String");
                } else if (type.equals("date"))
                {
                    keyList.add("java.sql.Date");
                } else if (type.matches("int.*"))
                {
                    keyList.add("java.lang.Integer");
                }

                String isNull = rs.getString(3);
                if (isNull.equals("YES"))
                {
                    keyList.add(true);
                } else
                {
                    keyList.add(false);
                }

                String primaryKey = rs.getString(4);
                if (primaryKey.equals("PRI"))
                {
                    keyList.add(true);
                } else
                {
                    keyList.add(false);
                }

                list.add(keyList);
            }

        } catch (SQLException e)
        {
            System.out.println("獲取主鍵失敗");
            e.printStackTrace();
        }

        return list;
    }

    public void close()
    {
        conn.close();
    }

}
