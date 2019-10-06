package service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map.Entry;

public class DBConnect
{
    private String ip;// 連接的ip
    private String port;// 連接的port
    private String dbName; // 連接的資料庫名稱
    private String user;// 連接資料庫的帳號
    private String passwd;// 連接資料庫的密碼

    private String address;
//    private DriverManager driverManger;
    private Connection con;
    private Statement aStatement;

    public DBConnect()
    {
        File file = new File("config.txt");
        HashMap<String, String> map = new HashMap<>();
        String str = "";
        if (file.isFile() && file.exists())
        {

            try (BufferedReader br = new BufferedReader(new FileReader(file)))
            {

                while (br.ready())
                {
                    str = br.readLine();

                    String[] array = str.split(":");
                    map.put(array[0], array[1]);
                }

            } catch (FileNotFoundException e)
            {
                System.out.println("config檔案不存在");
                e.printStackTrace();
            } catch (IOException e)
            {
                System.out.println("讀取config檔案發生錯誤");
                e.printStackTrace();
            }

        }

        for (Entry<String, String> entry : map.entrySet())
        {

            String key = entry.getKey().trim(); // 抓key時去前後空白
            String value = entry.getValue().trim(); // 抓值時去前後空白
            switch (key)
            {
                case "ip":
                    this.ip = value;
                    break;
                case "port":
                    this.port = value;
                    break;
                case "database-name":
                    this.dbName = value;
                    break;
                case "user":
                    this.user = value;
                    break;
                case "password":
                    this.passwd = value;
                    break;
                default:
                    System.out.println("config錯誤設定");
            }

        }

        /*
         * serverTimezone=Asia/Taipei 設定時區 ,setUnicode=true 使用unicode
         * characterEncoding=UTF-8 設定使用utf8,useSSL=false 關閉ssl
         * allowPublicKeyRetrieval=true 允許公開密鑰登入
         * 
         */
        this.address = "jdbc:mysql://" + ip + ":" + port + "/" + dbName
                + "?serverTimezone=Asia/Taipei&setUnicode=true&characterEncoding=UTF-8&useSSL=false&allowPublicKeyRetrieval = true";

        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver"); // 載入driver
        } catch (ClassNotFoundException e)
        {
            System.out.println("找不到mysql驅動");
            e.printStackTrace();
        }

    }

    public void getConnect()
    {
        try
        {

            con = DriverManager.getConnection(address, user, passwd);
            aStatement = con.createStatement();
            if (con != null && con.isClosed() != false)
            {
                System.out.println("資料庫連接成功");
            }

        } catch (SQLException e)
        {
            System.out.println("資料庫連接失敗，檢查Mysql服務是否開啟，帳號密碼是否正確");
            e.printStackTrace();
        }

    }

    public void close()
    {
        try
        {
            con.close();
        } catch (SQLException e)
        {
            System.out.println("關閉資料庫時發生錯誤");
            e.printStackTrace();
        }
    }

    public void setIp(String ip)
    {
        this.ip = ip;
    }

    public void setPort(String port)
    {
        this.port = port;
    }

    public void setAddress(String addr)
    {
        address = addr;
    }

    public void setUser(String user)
    {
        this.user = user;
    }

    public void setPasswd(String passwd)
    {
        this.passwd = passwd;
    }

    public Statement getStatement()
    {
        return aStatement;
    }

    public String getAddress()
    {
        return address;
    }

    public String getUser()
    {
        return user;
    }

    public String getpasswd()
    {
        return passwd;
    }

}
