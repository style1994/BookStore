package service;

import java.util.Vector;

//紀錄OrderView表格的相關訊息
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

        }

        // VIEW無法直接透過DESC查到欄位是否為空，這裡選擇直接加
        isNull.add(false);
        isNull.add(false);
        isNull.add(false);
        isNull.add(false);
        isNull.add(false);
        isNull.add(false);

        // VIEW無法直接透過DESC查到欄位是否為PK，這裡選擇直接加
        primaryKey.add(true);
        primaryKey.add(false);
        primaryKey.add(false);
        primaryKey.add(false);
        primaryKey.add(false);
        primaryKey.add(false);

        // 要顯示給使用者看到的攔位名稱
        columnName.add("訂單編號");
        columnName.add("訂單日期");
        columnName.add("會員編號");
        columnName.add("會員名稱");
        columnName.add("電話");
        columnName.add("地址");

    }

}
