package view;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PurchaseView extends JPanel
{
    public PurchaseView()
    {

        JLabel noLabel = new JLabel("進貨編號");
        JTextField noTextField = new JTextField();

        JLabel dateLabel = new JLabel("進貨日期");
        JTextField dateTextField = new JTextField();

        JLabel memberNoLabel = new JLabel("會員編號");
        JTextField memberNoTextField = new JTextField();

        JLabel member_NameLabel = new JLabel("會員姓名");
        JTextField member_NameTextField = new JTextField();

        JLabel member_TeLabel = new JLabel("電話");
        JTextField member_TelTextField = new JTextField();

        JLabel member_AddrLabel = new JLabel("地址");
        JTextField member_AddrTextField = new JTextField();

        JButton addButton = new JButton(" 新增 ");
        JButton delButton = new JButton(" 刪除 ");
        JButton updataButton = new JButton(" 修改 ");
        JButton clearButton = new JButton(" Reset ");
        JButton memberButton = new JButton("選擇客戶資料");

    }
}
