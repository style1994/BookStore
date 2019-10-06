package control;

import java.util.LinkedHashMap;

import service.CheckData;
import service.CheckMemberData;
import service.CheckOrderData;
import service.CheckOrderDtData;
import service.CheckProductData;

public class DataCheckControl
{

    private static String checkData(CheckData check)
    {

        if (!check.checkNull())
        {
            return "*耗攔位不得為空";
        } else
        {
            return check.checkFormat();
        }
    }

    public static String memberCheck(String tableName, LinkedHashMap<String, String> data)
    {

        CheckMemberData check = new CheckMemberData(tableName, data);
        return checkData(check);

    }

    public static String productCheck(String tableName, LinkedHashMap<String, String> data)
    {

        CheckProductData check = new CheckProductData(tableName, data);
        return checkData(check);

    }

    public static String orderCheck(String tableName, LinkedHashMap<String, String> data)
    {
        CheckOrderData check = new CheckOrderData(tableName, data);
        return checkData(check);
    }

    public static String orderDtCheck(String tableName, LinkedHashMap<String, String> data)
    {

        CheckOrderDtData check = new CheckOrderDtData(tableName, data);
        return checkData(check);
    }
}
