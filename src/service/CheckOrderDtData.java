package service;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.Vector;

public class CheckOrderDtData implements CheckData
{
    String tableName;
    LinkedHashMap<String, String> data;

    public CheckOrderDtData(String tableName, LinkedHashMap<String, String> data)
    {
        this.tableName = tableName;
        this.data = data;
    }

    @Override
    public boolean checkNull()
    {
        Vector<Boolean> isNull = OrderDtTable.isNull;

        int index = 0;

        while ((index = isNull.indexOf(false, index)) != -1) // 找到not null的欄位
        {
            String column = OrderDtTable.column.get(index);
            if (data.get(column).isEmpty()) // 如果not null欄位值是空
            {
                return false;
            }

            index++;
        }

        return true;
    }

    @Override
    public String checkFormat()
    { // 傳回1通過 負數失敗

        for (Entry<String, String> entry : data.entrySet())
        {
            String key = entry.getKey();
            String value = entry.getValue();

            switch (key)
            {
                case "od_price":

                    LocalDate date = LocalDate.now();
                    String str = date.toString().replaceAll("-", "");
                    if (!value.matches("\\d+") && Integer.parseInt(value) < 0)
                    {// 價格接受>0整數數字
                        System.out.println("o_price");
                        return "價格接受>0數字";
                    }
                    break;
                case "od_qty":
                    if (!value.matches("\\d+") && Integer.parseInt(value) < 0)
                    { // 數量接受>0整數數字
                        System.out.println("od_qty");
                        return "數量接受>0整數數字";
                    }
                    break;

            }

        }

        return "成功"; // 如果再迴圈內都沒做return代表通過所有檢查

    }

}
