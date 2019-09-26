package service;

import java.util.LinkedHashMap;
import java.util.Vector;

public class CheckMemberData
{
    String tableName;
    LinkedHashMap<String, String> data;

    public CheckMemberData(String tableName, LinkedHashMap<String, String> data)
    {
        this.tableName = tableName;
        this.data = data;
    }

    public boolean checkNull()
    {
        Vector<Boolean> isNull = MemberTable.isNull;

        int index = 0;

        while ((index = isNull.indexOf(false, index)) != -1) // 找到not null的欄位
        {
            String column = MemberTable.column.get(index);
            if (data.get(column).isEmpty()) // 如果not null欄位值是空
            {
                return false;
            }

            index++;
        }

        return true;
    }

}
