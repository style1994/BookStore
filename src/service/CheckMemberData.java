package service;

import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.Vector;

public class CheckMemberData implements CheckData
{
    String tableName;
    LinkedHashMap<String, String> data;

    public CheckMemberData(String tableName, LinkedHashMap<String, String> data)
    {
        this.tableName = tableName;
        this.data = data;
    }

    @Override
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

    @Override
    public String checkFormat()
    { // 傳回1通過 負數失敗

        for (Entry<String, String> entry : data.entrySet())
        {
            String key = entry.getKey();
            String value = entry.getValue();

            switch (key)
            {
                case "m_no":
                    if (!value.matches("M\\d{5}"))
                    {// 編號格式限定M00001
                        System.out.println("m_no");
                        return "會員標號格式錯誤，範例:M00000";
                    }
                    break;

                case "m_tel":
                    if (!value.matches("\\d{2}-\\d{7}") && !value.matches("\\d{10}"))
                    {
                        System.out.println("m_tel");
                        return "電話標號格式錯誤，範例:02-1234567或0900111222";
                    }
                    break;

                case "m_bir":
                    if (!value.matches("\\d{4}-\\d{2}-\\d{2}") && !value.matches(""))
                    { // 日期限定0000-00-00
                        System.out.println("m_bir");
                        return "生日格式錯誤，範例:2019-01-07";
                    }
                    break;

                case "m_email":
                    if (!value.matches(".+@.+(.).+") && !value.matches(""))
                    {// email
                        System.out.println("m_email");
                        return "email格式錯誤，範例:abc@gmail.com";
                    }
                    break;

            }

        }

        return "成功"; // 如果再迴圈內都沒做return代表通過所有檢查

    }

}
