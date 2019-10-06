package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginView extends JFrame
{

    private LoginView loginView = this;

    public LoginView()
    {
        super("二手書商店 - 進銷存系統");

        setSize(350, 600);
        setLocationRelativeTo(null); // 做視窗開啟就在中間
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Image icon = new ImageIcon("./image/book.png").getImage(); // 設定圖標
        setIconImage(icon);

        JPanel contentJPane = new JPanel()
        {
            // 放背景圖
            @Override
            public void paintComponent(Graphics g)
            {
                Image image = new ImageIcon("./image/bg_login.jpg").getImage();
                g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);
            }
        };
        setContentPane(contentJPane);
        contentJPane.setLayout(null);

        JLabel lblTitle = new JLabel();
        lblTitle.setText("二 手 書 管 理 系 統");
        lblTitle.setFont(new Font("微軟正黑體", Font.BOLD, 20));
        lblTitle.setSize(180, 30);
        lblTitle.setLocation((350 - 180) / 2, 50);

        JLabel lblAcc = new JLabel();
        lblAcc.setText("帳號: ");
        lblAcc.setFont(new Font("微軟正黑體", Font.PLAIN, 16));
        lblAcc.setSize(60, 30);
        lblAcc.setLocation(55, 120);

        JLabel lblPwd = new JLabel();
        lblPwd.setText("密碼: ");
        lblPwd.setFont(new Font("微軟正黑體", Font.PLAIN, 16));
        lblPwd.setSize(60, 30);
        lblPwd.setLocation(55, 155);

        JTextField fieldAcc = new JTextField(12);
        fieldAcc.setFont(new Font("微軟正黑體", Font.BOLD, 12));
        fieldAcc.setSize(150, 30);
        fieldAcc.setLocation(100, 120);

        JPasswordField fieldPwd = new JPasswordField(12);
        fieldPwd.setFont(new Font("微軟正黑體", Font.BOLD, 12));
        fieldPwd.setSize(150, 30);
        fieldPwd.setLocation(100, 155);

        JButton btnOk = new JButton("登入");
        btnOk.setFont(new Font("微軟正黑體", Font.PLAIN, 12));
        btnOk.setSize(210, 30);
        btnOk.setLocation(55, 200);

        JButton btnCancel = new JButton("清除");
        btnCancel.setFont(new Font("微軟正黑體", Font.PLAIN, 12));
        btnCancel.setSize(210, 30);
        btnCancel.setLocation(55, 240);

        JLabel lblResult = new JLabel("");
        lblResult.setForeground(Color.red);
        lblResult.setFont(new Font("微軟正黑體", Font.BOLD, 14));
        lblResult.setSize(210, 30);
        lblResult.setLocation(100, 280);

        JMenuBar menuBar = new JMenuBar();
        JMenu other = new JMenu("其他");
        other.setFont(new Font("微軟正黑體", Font.BOLD, 14));
        JMenuItem setItem = new JMenuItem("設定");
        setItem.setFont(new Font("微軟正黑體", Font.BOLD, 14));
        menuBar.add(other);
        other.add(setItem);

        setJMenuBar(menuBar);
        contentJPane.add(lblResult);
        contentJPane.add(btnOk);
        contentJPane.add(btnCancel);
        contentJPane.add(fieldAcc);
        contentJPane.add(fieldPwd);
        contentJPane.add(lblTitle);
        contentJPane.add(lblAcc);
        contentJPane.add(lblPwd);
        setVisible(true);

        // 確認按鈕事件
        btnOk.addActionListener((ActionEvent e) ->
        {

            if (fieldAcc.getText().length() > 0 && fieldAcc.getText().length() > 0)
            {

                String account = fieldAcc.getText(); // 取得帳號
                String password = new String(fieldPwd.getPassword()).toString(); // 取得密碼

                String check = control.LoginControl.login(account, password); // 呼叫控制類別的靜態方法

                if (Integer.parseInt(check) > 0) // 傳回>0的數字代表帳號的權限
                {

                    new MainFrame();
                    loginView.dispose();

                } else if (Integer.parseInt(check) == -1)
                {

                    lblResult.setText("帳號密碼錯誤");

                } else if (Integer.parseInt(check) == -2)
                {

                    lblResult.setText("資料庫連接失敗，請檢查設定");

                }

            } else
            {

                lblResult.setText("請填寫帳號密碼");

            }

        });

        // 清除按鈕事件
        btnCancel.addActionListener((ActionEvent e) ->
        {
            fieldAcc.setText("");
            fieldPwd.setText("");
            lblResult.setText("");
        });

        // 設定
        setItem.addActionListener((ActionEvent e) ->
        {

            JDialog dialog = new JDialog(loginView, "設定");
            SetView setView = new SetView(dialog);
            dialog.add(setView, BorderLayout.CENTER);
            dialog.pack();
            dialog.setLocationRelativeTo(null);
            dialog.setVisible(true);

        });

    }

    // 程式進入點
    public static void main(String[] args)
    {
        new LoginView();
    }
}
