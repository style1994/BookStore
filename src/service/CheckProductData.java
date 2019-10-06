package service;

import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.Vector;

public class CheckProductData implements CheckData
{
    String tableName;
    LinkedHashMap<String, String> data;

    public CheckProductData(String tableName, LinkedHashMap<String, String> data)
    {
        this.tableName = tableName;
        this.data = data;
    }

    @Override
    public boolean checkNull()
    {
        Vector<Boolean> isNull = ProductTable.isNull;

        int index = 0;

        while ((index = isNull.indexOf(false, index)) != -1) // 找到not null的欄位
        {
            String column = ProductTable.column.get(index);
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
                case "b_no":
                    if (!value.matches("B\\d{5}"))
                    {// 編號格式限定B00001
                        System.out.println("b_no");
                        return "書本標號格式錯誤，範例:B00000";
                    }
                    break;

                case "b_isbn":
                    if (value.length() != 13)
                    { // ISBN
                        System.out.println("b_isbn");
                        return "ISBN長度須為13碼";
                    }
                    break;

                case "b_price":
                    if (!value.matches("\\d+") && Integer.parseInt(value) < 0)
                    { // 價格只能輸入數字,且不可為負
                        System.out.println("b_bir");
                        return "價格只能輸入數字,且不可為負";
                    }
                    break;

                case "b_qty":
                    if (!value.matches("\\d+") && Integer.parseInt(value) < 0)
                    { // 數量只能輸入數字,且不可為負
                        System.out.println("b_qty");
                        return "數量只能輸入數字,且不可為負";
                    }
                    break;

            }

        }

        return "成功"; // 如果再迴圈內都沒做return代表通過所有檢查

    }

}
