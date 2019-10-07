package control;

import java.util.LinkedHashMap;
import java.util.Vector;

import service.MyTableModel;
import service.SQLService;

public class TableControl
{
    // 獲取資料表資料 預設全部資料表欄位
    public static Vector<Vector<Object>> getData(String tableName)
    {
        SQLService service = new SQLService(tableName);

        return service.getAllTableData();

    }

    // 獲取資料表欄位名稱
    public static Vector<String> getColumn(String tableName)
    {
        SQLService service = new SQLService(tableName);

        return service.getColumnName();
    }

    // 獲取一個有資料的TableModel
    public static MyTableModel getTableModel(String tableName)
    {

        SQLService service = new SQLService(tableName);
        Vector<Vector<Object>> data = getData(tableName);
        Vector<String> column = getColumn(tableName);
        service.close();

        return new MyTableModel(data, column);
    }

    // 新增資料
    public static boolean addTableData(String tableName, LinkedHashMap<String, String> map, MyTableModel model)
    {

        SQLService service = new SQLService(tableName);

        int isSuccess = service.add(map); // 對Table做新增

        if (isSuccess == 1)
        {
            model.addTableData(map);
            return true;
        } else
        {
            return false;
        }

    }

    // 新增資料到雙檔
    public static boolean add2TableData(String tableName, LinkedHashMap<String, String> mapForDatabase,
            LinkedHashMap<String, String> mapForModel, MyTableModel model)
    {
        SQLService service = new SQLService(tableName);

        int isSuccess = service.add(mapForDatabase); // 對Table做新增

        if (isSuccess == 1)
        {
            model.addTableData(mapForModel); // SQL新增成功的話更新TableModel資料
            return true;
        } else
        {
            return false;
        }

    }

    // 刪除資料
    public static void delTableData(String tableName, int row, MyTableModel model)
    {
        SQLService service = new SQLService(tableName);
        Vector<String> keyList = service.getPrimaryKey(); // 拿到Table PK

        Vector<String> value = model.getPrimaryKeyValue(row, keyList); // 透過Model拿到該列的PK值

        service.del(keyList, value); // 對資料庫做刪除
        model.delTableData(row); // Model資料刪除

    }

    // 獲取某列的值集合
    public static Vector<String> getRowValue(int row, MyTableModel model)
    {

        return model.getRowValues(row);
    }

    // 更新資料
    public static boolean updataTableData(String tableName, LinkedHashMap<String, String> dataMap, int row,
            MyTableModel model)
    {
        SQLService service = new SQLService(tableName);
        Vector<String> primaryKey = service.getPrimaryKey(); // 拿到Table PK
        Vector<String> keyValue = model.getPrimaryKeyValue(row, primaryKey); // 透過Model拿到該列的PK值

        int affected = service.updata(dataMap, primaryKey, keyValue); // 更新資料庫的資料

        if (affected == 1)
        {
            model.updateTableData(row, dataMap); // 更新Model資料
            return true;
        } else
        {
            return false;
        }

    }

    // 更新雙檔資料
    public static boolean updata2TableData(String tableName, LinkedHashMap<String, String> mapForDatabase,
            LinkedHashMap<String, String> mapForModel, int row, MyTableModel model)
    {

        SQLService service = new SQLService(tableName);
        Vector<String> primaryKey = service.getPrimaryKey(); // 拿到Table PK
        Vector<String> keyValue = model.getPrimaryKeyValue(row, primaryKey); // 透過Model拿到該列的PK值

        int affected = service.updata(mapForDatabase, primaryKey, keyValue); // 更新資料庫資料

        if (affected == 1)
        {
            model.updateTableData(row, mapForModel); // 更新Model資料
            return true;
        } else
        {
            return false;
        }

    }

    // 搜尋資料，只給內部使用
    private static Vector<Vector<Object>> searchTableData(String tableName, String where)
    {
        SQLService service = new SQLService(tableName);
        if (where.startsWith(" where "))
        {
            return service.getTableData(where);
        } else
        {
            return service.getAllTableData();
        }

    }

    // 重載table資料，給搜尋用
    public static void reloadTableData(String tableName, String where, MyTableModel model)
    {
        SQLService service = new SQLService(tableName);
        Vector<Vector<Object>> data = searchTableData(tableName, where);
        model.setTableData(data);
    }
}
