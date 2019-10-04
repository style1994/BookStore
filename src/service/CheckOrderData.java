package service;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.Vector;

public class CheckOrderData
{
    String tableName;
    LinkedHashMap<String, String> data;

    public CheckOrderData(String tableName, LinkedHashMap<String, String> data)
    {
        this.tableName = tableName;
        this.data = data;
    }

    public boolean checkNull() 
    {
        Vector<Boolean> isNull = OrderTable.isNull;

        int index = 0;

        while ((index = isNull.indexOf(false, index)) != -1) // 找到not null的欄位
        {
            String column = OrderTable.column.get(index);
            if (data.get(column).isEmpty()) // 如果not null欄位值是空
            {
                return false;
            }

            index++;
        }

        return true;
    }
    
    public String checkFormat() { //傳回1通過 負數失敗
    	
    	for (Entry<String, String> entry : data.entrySet())
		{
    		String key = entry.getKey();
    		String value = entry.getValue();
    		
    		switch (key)
			{
			case "o_no":
				
				LocalDate date = LocalDate.now();
				String str = date.toString().replaceAll("-","");
				if(!value.matches("od"+str+"\\d{3}")) {//訂單編號格式:od+今天日期+數字
					System.out.println("o_no");
					return "訂單編號格式:od"+str+"數字3碼";
				}
				break;
			case "o_date":
				if(!value.matches("\\d{4}-\\d{2}-\\d{2}") && !value.matches("")) { //日期限定0000-00-00
					System.out.println("o_date");
					return "日期格式錯誤，範例:2019-01-07";
				}
				break;
			
			}
		
		}

		return "成功"; //如果再迴圈內都沒做return代表通過所有檢查
    	
    }
    
    
//    public static void main(String[] args)
//	{
//		String s = "od20190923001";
//		
//		System.out.println(s.matches("od"+"20190923"+"\\d{3}"));
//	}

}
