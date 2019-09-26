package control;

import java.util.LinkedHashMap;

import service.CheckMemberData;

public class DataCheckControl
{
    public static boolean memberCheck(String tableName, LinkedHashMap<String, String> data)
    {
        CheckMemberData check = new CheckMemberData(tableName, data);

        boolean success = check.checkNull();

        return success;

    }
}
