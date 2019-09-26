package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

import control.TableControl;
import service.MemberTable;
import service.MyTableModel;
import service.OrderTable;
import service.ProductTable;

public class MainFrame extends JFrame
{
    ArrayList<JMenu> jMenuList;
    ArrayList<JMenuItem> jMenuItemList;

    JFrame jfrm = this;
    LoginView loginView;

    JMenuBar menuBar;
    JMenu memberJMenu;
    JMenu productJMenu;
    JMenu salesJMenu;
    JMenu purchaseJMenu;
    JMenu orderJMenu;
    JMenu otherJMenu;

    JMenuItem memberQuery;
    JMenuItem memberMaintain;
    JMenuItem productQuery;
    JMenuItem productMaintain;
    JMenuItem salesQuery;
    JMenuItem salesMaintain;
    JMenuItem purchaseQuery;
    JMenuItem purchaseMaintain;
    JMenuItem orderQuery;
    JMenuItem orderMaintain;
    JMenuItem logout;
    JMenuItem exit;

    JSplitPane contentPane;

    String useing_table; // 旗標 紀錄上半部現在是哪個維護哪個資料表

    public MainFrame(LoginView loginView)
    {

        this.loginView = loginView;

        Image icon = new ImageIcon("./image/book.png").getImage();
        setIconImage(icon);

        menuBar = new JMenuBar();
        menuBar.setBackground(Color.white);

        memberJMenu = new JMenu("會員");
        productJMenu = new JMenu("商品");
        salesJMenu = new JMenu("銷售");
        purchaseJMenu = new JMenu("進貨");
        orderJMenu = new JMenu("訂單");
        otherJMenu = new JMenu("其他");

        memberMaintain = new JMenuItem("會員資料 維護");

        productMaintain = new JMenuItem("商品資料 維護");
        salesQuery = new JMenuItem("銷售資料 查詢");
        salesMaintain = new JMenuItem("銷售資料 維護");
        purchaseQuery = new JMenuItem("進貨資料 查詢");
        purchaseMaintain = new JMenuItem("進貨資料 維護");

        orderMaintain = new JMenuItem("訂單資料 維護");
        logout = new JMenuItem("登出");
        exit = new JMenuItem("結束");

        menuBar.add(otherJMenu);
        menuBar.add(memberJMenu);
        menuBar.add(productJMenu);
        menuBar.add(purchaseJMenu);
        menuBar.add(orderJMenu);
        menuBar.add(salesJMenu);

        otherJMenu.add(logout);
        otherJMenu.add(exit);
        memberJMenu.add(memberMaintain);
        productJMenu.add(productMaintain);
        purchaseJMenu.add(purchaseQuery);
        purchaseJMenu.add(purchaseMaintain);

        orderJMenu.add(orderMaintain);
        salesJMenu.add(salesQuery);
        salesJMenu.add(salesMaintain);

        // 把menu menuItem加到list方便統一操作
        jMenuList = new ArrayList<>();
        jMenuItemList = new ArrayList<>();

        jMenuList.add(otherJMenu);
        jMenuList.add(memberJMenu);
        jMenuList.add(productJMenu);
        jMenuList.add(purchaseJMenu);
        jMenuList.add(orderJMenu);
        jMenuList.add(salesJMenu);

        jMenuItemList.add(logout);
        jMenuItemList.add(exit);

        jMenuItemList.add(memberMaintain);
        jMenuItemList.add(productMaintain);
        jMenuItemList.add(salesQuery);
        jMenuItemList.add(salesMaintain);
        jMenuItemList.add(purchaseQuery);
        jMenuItemList.add(purchaseMaintain);
        jMenuItemList.add(purchaseQuery);
        jMenuItemList.add(purchaseMaintain);

        jMenuItemList.add(orderMaintain);

        for (JMenu obj : jMenuList)
        {
            obj.setFont(new Font("微軟正黑體", Font.PLAIN, 16)); // 設定字型
        }
        for (JMenuItem obj : jMenuItemList)
        {
            obj.setFont(new Font("微軟正黑體", Font.PLAIN, 16));
        }

        contentPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);

        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setJMenuBar(menuBar);
        setTitle("二手書店管理系統");
        setContentPane(contentPane);

        setVisible(true);

        // Window監聽
        addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e)
            {
                int check = JOptionPane.showConfirmDialog(jfrm, "確認訊息", "確定要離開嗎?", JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE);

                if (check == JOptionPane.YES_OPTION)
                {
                    System.exit(0);
                }

            }
        });

        // menuItem exit 監聽
        exit.addActionListener((ActionEvent e) ->
        {

            int check = JOptionPane.showConfirmDialog(jfrm, "確認訊息", "確定要離開嗎?", JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE);

            if (check == JOptionPane.YES_OPTION)
            {
                System.exit(0);
            }

        });

        // menuItem logout 監聽
        logout.addActionListener((ActionEvent e) ->
        {

            int check = JOptionPane.showConfirmDialog(jfrm, "確認訊息", "確定要登出嗎?", JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE);

            if (check == JOptionPane.YES_OPTION)
            {
                jfrm.dispose(); // 關閉主要畫面
                new LoginView();
            }

        });
        // 會員資料表維護
        memberMaintain.addActionListener((ActionEvent e) ->
        {

            useing_table = "membership"; // 更新使用的table

            JTable table = new JTable();

            MyTableModel tableModel = TableControl.getTableModel(useing_table); // 拿到自訂的tableModle
            tableModel.setColumnName(MemberTable.columnName); // 設定欄位名稱
            tableModel.setType(MemberTable.type);
            table.setModel(tableModel);
            setTableCellColor(table, table.getColumnCount()); // 設定每一列的顏色
            setUpTable(table);// table基本設定

            TableColumnModel columnModle = table.getColumnModel();// 透過TableColumnModle取得column來設定各攔位的大小
            columnModle.getColumn(2).setPreferredWidth(150);
            columnModle.getColumn(3).setPreferredWidth(120);
            columnModle.getColumn(4).setPreferredWidth(200);
            columnModle.getColumn(5).setPreferredWidth(200);

            MemberView memberView = new MemberView(table); //
            contentPane.setLeftComponent(memberView);
            JScrollPane scrollPane = new JScrollPane(table);
            contentPane.setRightComponent(scrollPane);
            contentPane.setDividerLocation(0.5); // 設定分隔線位子 50%

        });

        // 商品資料維護
        productMaintain.addActionListener((ActionEvent e) ->
        {

            useing_table = "book";

            JTable table = new JTable();

            MyTableModel tableModel = TableControl.getTableModel(useing_table); // 拿到自訂的tableModle
            tableModel.setColumnName(ProductTable.columnName);
            tableModel.setType(ProductTable.type);

            table.setModel(tableModel);
            setTableCellColor(table, table.getColumnCount()); // 設定每一列的顏色
            setUpTable(table);// table基本設定

            ProductView productView = new ProductView(table, useing_table); // 傳入Table和使用的資料表
            contentPane.setLeftComponent(productView);
            JScrollPane scrollPane = new JScrollPane(table);
            contentPane.setRightComponent(scrollPane);
            contentPane.setDividerLocation(0.5); // 設定分隔線位子 50%
        });

        // 訂單資料表
        orderMaintain.addActionListener((ActionEvent e) ->
        {

            useing_table = "book_order_view"; // 這裡使用view

            JTable table = new JTable();

            MyTableModel tableModel = TableControl.getTableModel(useing_table);
            tableModel.setColumnName(OrderTable.columnName);
            tableModel.setType(OrderTable.type);

            table.setModel(tableModel);
            setTableCellColor(table, table.getColumnCount()); // 設定每一列的顏色
            setUpTable(table);// table基本設定

            OrderView orderView = new OrderView(table, useing_table);
            contentPane.setTopComponent(orderView);
            JScrollPane scrollPane = new JScrollPane(table);
            contentPane.setRightComponent(scrollPane);
            contentPane.setDividerLocation(0.5);

        });
    }

    // 繪製表格欄位顏色
    public static void setTableCellColor(JTable table, int columnCount)
    {

        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer()
        {

            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                    boolean hasFocus, int row, int column)
            {

                if (row % 2 == 0)
                {
                    setBackground(new Color(255, 128, 0, 80));
                } else
                {
                    setBackground(new Color(255, 0, 128, 80));
                }

                return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            }
        };

        String[] columnName = new String[columnCount];

        for (int i = 0; i < columnName.length; i++)
        {
            columnName[i] = table.getColumnName(i);
            table.getColumn(columnName[i]).setCellRenderer(cellRenderer);
        }

    }

    // Table基本設定
    public void setUpTable(JTable table)
    {

        // table 基本設定

        table.setFillsViewportHeight(true);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS); // 在調整寬度時自動調整所有欄位的寬
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // 一次只能選取一列
        table.setFont(new Font("微軟正黑體", Font.PLAIN, 16));
        table.setRowHeight(28);
        // 設定table表頭
        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("微軟正黑體", Font.PLAIN, 16));
        header.setBackground(Color.white);

    }

}
