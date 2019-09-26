package service;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class Maintain
{
    Statement aStatement;
    ResultSetMetaData rMeta;
    ResultSet result;
    String table;
    String column;
    String value;
    String[] colName;
    String[] colTypeName;
    int[] colType;
    int colCount;

    public Maintain(Statement aStatement)
    {
        this.aStatement = aStatement;
    }

    public void add(String table, String column, String value)
    {
        try
        {
            int count = aStatement
                    .executeUpdate("insert into " + table + " (" + column + ") values " + "( " + value + " )");
            System.out.println("success 影響了 " + count + " 筆資料");
        } catch (SQLException e)
        {
            System.out.println("新增失敗，確認資料表與欄位名稱");
            e.printStackTrace();
        }

    }

    public void query(String table, String column, String where)
    {
        try
        {
            result = aStatement.executeQuery("select " + column + " from " + table + " where " + where);
            rMeta = result.getMetaData();

            for (int i = 1; i <= rMeta.getColumnCount(); i++)
            {
                System.out.println(rMeta.getColumnLabel(i) + "\t" + rMeta.getColumnTypeName(i) + " "
                        + rMeta.getColumnType(i) + " " + rMeta.getSchemaName(i) + " " + rMeta.getTableName(i));
            }

            System.out.println("欄位數有: " + rMeta.getColumnCount());
            System.out.println("列數: ");
            while (result.next())
            {
                int num = result.getInt("num");
                String name = result.getString("name");
                System.out.println(num + "\t" + name);
            }

        } catch (SQLException e)
        {
            System.out.println("查詢失敗，確認資料表與攔位名稱");
            e.printStackTrace();
        }

    }

    public void getTableType(String tableName)
    {
        try
        {
            ResultSet rs = aStatement.executeQuery("desc " + tableName);
            ResultSetMetaData rsMetaData = rs.getMetaData();

            Vector<String> typeList = new Vector<>();

            while (rs.next())
            {
                String type = rs.getString(2);
                String[] split = type.split("(");

            }

        } catch (SQLException e)
        {
            System.out.println("錯誤 ! 獲取table型態欄位資料");
            e.printStackTrace();
        }

    }
}
