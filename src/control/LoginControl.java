package control;

import service.LoginCheck;

public class LoginControl
{
    public static String login(String account, String password)
    {
        LoginCheck obj = new LoginCheck(); // 執行登入確認的類別
        String check = "";
        try
        {
            check = obj.login(account, password);
        } catch (Exception e)
        {
            e.printStackTrace();
            return "-2";

        }

        return check;
    }
}
