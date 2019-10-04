package control;

import java.util.LinkedHashMap;

import service.CheckMemberData;
import service.CheckOrderData;
import service.CheckOrderDtData;
import service.CheckProductData;

public class DataCheckControl
{
    public static String memberCheck(String tableName, LinkedHashMap<String, String> data)
    {
    	
        CheckMemberData check = new CheckMemberData(tableName, data);

       if(!check.checkNull()) {
    	   return "*號欄位不得為空";
       }
       
       return check.checkFormat();
          

    }
    
    public static String productCheck(String tableName, LinkedHashMap<String, String> data) {
    	
    	CheckProductData check = new CheckProductData(tableName, data);
    	if(!check.checkNull()) {
     	   return "*號欄位不得為空";
        }
    	
		return check.checkFormat();
    	
    }
    
    public static String orderCheck(String tableName, LinkedHashMap<String, String> data) {
    	CheckOrderData check = new CheckOrderData(tableName, data);
    	if(!check.checkNull()) {
     	   return "*號欄位不得為空";
        }
    	
		return check.checkFormat();
    }
    
    public static String orderDtCheck(String tableName, LinkedHashMap<String, String> data) {
    	
    	CheckOrderDtData check = new CheckOrderDtData(tableName, data);
    	if(!check.checkNull()) {
     	   return "*號欄位不得為空";
        }
    	
		return check.checkFormat();
    }
}
