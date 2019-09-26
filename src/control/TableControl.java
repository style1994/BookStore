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

    // 獲取一個有資料的tablemodel
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

        int isSuccess = service.add(map);

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

        int isSuccess = service.add(mapForDatabase); // 把真正要給database新增的資料丟給SQLService

        if (isSuccess == 1)
        {
            model.addTableData(mapForModel); // 把要顯示給使用者看的資料丟給自訂tablemodel
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
        Vector<String> keyList = service.getPrimaryKey(); // 拿到所屬資料表的主key 所屬類別是sqlservice

        Vector<String> value = model.getPrimaryKeyValue(row, keyList); // 拿到對應key的資料

        service.del(keyList, value);
        model.delTableData(row);

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
        Vector<String> primaryKey = service.getPrimaryKey();
        Vector<String> keyValue = model.getPrimaryKeyValue(row, primaryKey);

        model.updateTableData(row, dataMap); // 更新表格模型裡存在的資料
        int affected = service.updata(dataMap, primaryKey, keyValue); // 更新實際資料庫存在的資料

        boolean isSuccess = (affected == 1) ? true : false;

        return isSuccess;

    }

    // 更新雙檔資料
    public static boolean updata2TableData(String tableName, LinkedHashMap<String, String> mapForDatabase,
            LinkedHashMap<String, String> mapForModel, int row, MyTableModel model)
    {

        SQLService service = new SQLService(tableName);
        Vector<String> primaryKey = service.getPrimaryKey();
        Vector<String> keyValue = model.getPrimaryKeyValue(row, primaryKey);

        model.updateTableData(row, mapForModel); // 更新表格模型裡存在的資料
        int affected = service.updata(mapForDatabase, primaryKey, keyValue); // 更新實際資料庫存在的資料

        boolean isSuccess = (affected == 1) ? true : false;

        return isSuccess;

    }

    // 搜尋資料，只給內部使用
    private static Vector<Vector<Object>> searchTableData(String tableName, String where)
    {
        SQLService service = new SQLService(tableName);

        return service.getTableData(where);
    }

    // 重載table資料，給搜尋用
    public static void reloadTableData(String tableName, String where, MyTableModel model)
    {
        SQLService service = new SQLService(tableName);
        Vector<Vector<Object>> data = searchTableData(tableName, where);
        model.setTableData(data);
    }
}
