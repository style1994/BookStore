package service;

import java.util.Vector;

public class OrderTable
{
    final public static String tableName = "book_order_view";
    final public static Vector<String> column = new Vector<>(); // 資料庫上實際的欄位名
    final public static Vector<String> columnName = new Vector<>(); // jtable上要顯示的中文欄位名
    final public static Vector<String> pattern = new Vector<>();// 各欄位的檢查條件
    final public static Vector<String> type = new Vector<>(); // 各欄位型態
    final public static Vector<Boolean> isNull = new Vector<>(); // 是否可為null
    final public static Vector<Boolean> primaryKey = new Vector<>(); // 是否為primaryKey

    static
    {
        SQLService service = new SQLService(tableName);

        Vector<Vector<Object>> desc = service.getTableDesc();

        for (Vector<Object> vector : desc)
        {

            column.add((String) vector.get(0));
            type.add((String) vector.get(1));
            isNull.add((Boolean) vector.get(2));
            // primaryKey.add((Boolean) vector.get(3));

        }

        primaryKey.add(true);
        primaryKey.add(false);
        primaryKey.add(false);
        primaryKey.add(false);
        primaryKey.add(false);
        primaryKey.add(false);

        columnName.add("訂單編號");
        columnName.add("訂單日期");
        columnName.add("會員編號");
        columnName.add("會員名稱");
        columnName.add("電話");
        columnName.add("地址");

    }

}
