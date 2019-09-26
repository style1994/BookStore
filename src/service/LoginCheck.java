package service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginCheck
{
    // 登入的方法
    public String login(String account, String password)
    {
        DBConnect connect = new DBConnect();
        connect.getConnect();
        Statement aStatement = connect.getStatement();
        ;
        try
        {
            if (account.length() > 0 && password.length() > 0)
            {
                String sql = "SELECT * FROM account WHERE userId='" + account + "' AND passwd='" + password + "'";
                ResultSet rs = aStatement.executeQuery(sql);

                // 如果rs裡面有資料 且帳號密碼都符合的話傳回登入權限
                if (rs.next() && account.equals(rs.getString(1)) && password.equals(rs.getString(2)))
                {
                    return rs.getString(3); // 帳密正確傳回帳戶的權限
                }

            }

        } catch (SQLException e)
        {
            System.out.println("登入確認發生錯誤");
            e.printStackTrace();
        } finally
        {
            connect.close(); // 用完關閉連接
        }

        return "-1"; // 帳密錯誤傳回-1

    }
}
