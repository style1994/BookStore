package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.JTableHeader;

import control.DataCheckControl;
import control.TableControl;
import service.MyTableModel;
import service.OrderDtTable;
import service.OrderTable;

public class OrderView extends JPanel
{
    JTable table;
    JTable dtTable; // 再點了名細檔維護才做設定值
    String tableName; // book_order_view
    final JPanel panel = this;
    ListSelectionModel select;

    public OrderView(JTable table, String tableName)
    {
        this.table = table;
        this.tableName = tableName;
        select = table.getSelectionModel();

        GridBagLayout layout = new GridBagLayout();

        setLayout(layout);
        setBackground(Color.pink);
        GridBagConstraints constraints; // 設定gridlayout排版的物件

        JLabel noLabel = new JLabel("*訂單編號");
        JTextField noTextField = new JTextField(10);
        constraints = MyGridBagLayout.getGridBagConstraints(0, 0, 1, 1, GridBagConstraints.EAST);
        this.add(noLabel, constraints);
        constraints = MyGridBagLayout.getGridBagConstraints(1, 0, 1, 1, GridBagConstraints.EAST);
        this.add(noTextField, constraints);

        JLabel dateLabel = new JLabel("*訂單日期");
        JTextField dateTextField = new JTextField(10);
        constraints = MyGridBagLayout.getGridBagConstraints(0, 1, 1, 1, GridBagConstraints.EAST);
        this.add(dateLabel, constraints);
        constraints = MyGridBagLayout.getGridBagConstraints(1, 1, 1, 1, GridBagConstraints.EAST);
        this.add(dateTextField, constraints);

        JLabel m_noLabel = new JLabel("*會員編號");
        JTextField m_noTextField = new JTextField(10);
        constraints = MyGridBagLayout.getGridBagConstraints(0, 2, 1, 1, GridBagConstraints.EAST);
        this.add(m_noLabel, constraints);
        constraints = MyGridBagLayout.getGridBagConstraints(1, 2, 1, 1, GridBagConstraints.EAST);
        this.add(m_noTextField, constraints);

        JLabel m_nameLabel = new JLabel("會員名稱");
        JTextField m_nameTextField = new JTextField(10);
        constraints = MyGridBagLayout.getGridBagConstraints(2, 0, 1, 1, GridBagConstraints.EAST);
        this.add(m_nameLabel, constraints);
        constraints = MyGridBagLayout.getGridBagConstraints(3, 0, 1, 1, GridBagConstraints.EAST);
        this.add(m_nameTextField, constraints);

        JLabel m_telLabel = new JLabel("電話");
        constraints = MyGridBagLayout.getGridBagConstraints(2, 1, 1, 1, GridBagConstraints.EAST);
        this.add(m_telLabel, constraints);
        JTextField m_telTextField = new JTextField(10);
        constraints = MyGridBagLayout.getGridBagConstraints(3, 1, 1, 1, GridBagConstraints.EAST);
        this.add(m_telTextField, constraints);

        JLabel m_addr_Label = new JLabel("地址");
        constraints = MyGridBagLayout.getGridBagConstraints(2, 2, 1, 1, GridBagConstraints.EAST);
        this.add(m_addr_Label, constraints);
        JTextField m_addr_TextField = new JTextField(35);
        constraints = MyGridBagLayout.getGridBagConstraints(3, 2, 4, 1, GridBagConstraints.WEST);
        this.add(m_addr_TextField, constraints);

        JLabel checkJLable = new JLabel("*號欄位不得為空"); // 顯示檢查欄位的結果的label
        constraints = MyGridBagLayout.getGridBagConstraints(0, 3, 0, 1, GridBagConstraints.WEST);
        this.add(checkJLable, constraints);
        checkJLable.setFont(new Font("微軟正黑體", Font.PLAIN, 16));
        checkJLable.setForeground(Color.red);

        // 按鈕
        JButton searchButton = new JButton("查詢"); // 讓使用者能透過選取方式找到客戶資料
        constraints = MyGridBagLayout.getGridBagConstraints(0, 4, 1, 1, GridBagConstraints.EAST);
        this.add(searchButton, constraints);
        JButton memberButton = new JButton("選擇廠商");
        constraints = MyGridBagLayout.getGridBagConstraints(1, 4, 1, 1, GridBagConstraints.EAST);
        this.add(memberButton, constraints);

        JButton detailButton = new JButton("明細檔維護");
        constraints = MyGridBagLayout.getGridBagConstraints(2, 4, 1, 1, GridBagConstraints.EAST);
        this.add(detailButton, constraints);
        JButton clearButton = new JButton("Reset");
        constraints = MyGridBagLayout.getGridBagConstraints(3, 4, 1, 1, GridBagConstraints.EAST);
        this.add(clearButton, constraints);
        JButton addButton = new JButton(" 新增 ");
        constraints = MyGridBagLayout.getGridBagConstraints(4, 4, 1, 1, GridBagConstraints.EAST);
        this.add(addButton, constraints);
        JButton updataButton = new JButton(" 修改 ");
        constraints = MyGridBagLayout.getGridBagConstraints(5, 4, 1, 1, GridBagConstraints.EAST);
        this.add(updataButton, constraints);
        JButton delButton = new JButton(" 刪除 ");
        constraints = MyGridBagLayout.getGridBagConstraints(6, 4, 1, 1, GridBagConstraints.EAST); // 這一列最後一個
        this.add(delButton, constraints);

        ArrayList<JLabel> lblList = new ArrayList<>();

        lblList.add(noLabel);
        lblList.add(dateLabel);
        lblList.add(m_noLabel);
        lblList.add(m_nameLabel);
        lblList.add(m_telLabel);
        lblList.add(m_addr_Label);

        for (int i = 0; i < lblList.size(); i++)
        {
            JLabel myLabel = lblList.get(i);
            myLabel.setFont(new Font("微軟正黑體", Font.PLAIN, 16));
        }

        ArrayList<JTextField> textList = new ArrayList<>();
        textList.add(noTextField);
        textList.add(dateTextField);
        textList.add(m_noTextField);
        textList.add(m_nameTextField);
        textList.add(m_telTextField);
        textList.add(m_addr_TextField);

        for (int i = 0; i < textList.size(); i++)
        {
            JTextField myTextField = textList.get(i);
            myTextField.setFont(new Font("微軟正黑體", Font.PLAIN, 12));
            myTextField.setName(OrderTable.column.get(i));
            if (i >= 2) // 設定部分textField不能被編輯
            {
                myTextField.setEditable(false);
            }
        }

        ArrayList<JButton> btnList = new ArrayList<JButton>();
        btnList.add(delButton);
        btnList.add(clearButton);
        btnList.add(detailButton);
        btnList.add(searchButton);
        btnList.add(updataButton);
        btnList.add(addButton);
        btnList.add(memberButton);

        for (int i = 0; i < btnList.size(); i++)
        {
            JButton myButton = btnList.get(i);
            myButton.setFont(new Font("微軟正黑體", Font.PLAIN, 14));
        }

        // 搜尋事件
        searchButton.addActionListener((ActionEvent e) ->
        {

            JFrame searchFrame = new JFrame("訂單搜尋");
            OrderSearchView orderSearchView = new OrderSearchView(table, "book_order_view");
            searchFrame.setContentPane(orderSearchView);
            searchFrame.setSize(400, 300);
            searchFrame.setLocationRelativeTo(null); // 視窗畫面置中
            searchFrame.setVisible(true);

            searchFrame.addWindowListener(new WindowAdapter()
            {
                @Override
                public void windowClosing(WindowEvent e)
                {
                    int check = JOptionPane.showConfirmDialog(searchFrame, "是否要關閉搜尋視窗", "搜尋", JOptionPane.YES_NO_OPTION,
                            JOptionPane.WARNING_MESSAGE);

                    if (check == JOptionPane.YES_OPTION)
                    {
                        searchFrame.dispose();
                    }

                }
            });

        });

        // 選擇客戶事件
        memberButton.addActionListener((ActionEvent e) ->
        {

            ArrayList<JTextField> list = new ArrayList<>();
            list.add(m_noTextField);
            list.add(m_nameTextField);
            list.add(m_telTextField);
            list.add(m_addr_TextField);

            select.clearSelection(); // 取消表格的選取
            for (JTextField textField : list) // 清空textfield上的內容
            {
                textField.setText("");
            }

            SelectMemberView selectMemberView = new SelectMemberView(list);

        });

        // 明細檔維護
        detailButton.addActionListener((ActionEvent e) ->
        {

            JFrame odtFrame = new JFrame("訂單明細檔");
            JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);

            MyTableModel model = TableControl.getTableModel("book_orderdt_view");
            model.setColumnName(OrderDtTable.columnName);
            model.setType(OrderDtTable.type);

            // table基本設定
            JTable orderDtTable = new JTable(model);
            orderDtTable.setFillsViewportHeight(true);
            orderDtTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS); // 在調整寬度時自動調整所有欄位的寬
            orderDtTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // 一次只能選取一列
            orderDtTable.setFont(new Font("微軟正黑體", Font.PLAIN, 18));// 設定table字體
            orderDtTable.setRowHeight(35);// 設定列高
            MainFrame.setTableCellColor(orderDtTable, orderDtTable.getColumnCount()); // 設定表格顏色

            // 設定table表頭
            JTableHeader header = orderDtTable.getTableHeader();
            header.setFont(new Font("微軟正黑體", Font.PLAIN, 16));// 設定表頭字體
            header.setBackground(Color.white);// 設定表頭背景

            dtTable = orderDtTable; // 保留這裡的明細檔jtable當主檔jtable點選時 要改變明細檔jtable用
            OrderDetailView detailView = new OrderDetailView(orderDtTable, "book_orderdt_view");

            splitPane.setTopComponent(detailView);
            splitPane.setBottomComponent(new JScrollPane(orderDtTable));

            odtFrame.setContentPane(splitPane);
            odtFrame.setSize(800, 600);
            odtFrame.setVisible(true);
            splitPane.setDividerLocation(0.5);

        });

        // 清除事件
        clearButton.addActionListener((ActionEvent e) ->
        {

            for (JTextField textField : textList)
            {
                textField.setText("");
            }

        });

        // 新增事件
        addButton.addActionListener((ActionEvent e) ->
        {

            

            int check = JOptionPane.showConfirmDialog(panel, "是否要新增資料?", "新增", JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE);
            boolean isSuccess = false;

            if (check == JOptionPane.YES_OPTION)
            {
            	
            	LinkedHashMap<String, String> mapForDatabase = new LinkedHashMap<>();// 給database新增資料用的
                LinkedHashMap<String, String> mapForModel = new LinkedHashMap<>(); // 給自訂model更新資料用的

                for (int i = 0; i < textList.size(); i++)
                {
                    JTextField myTextField = textList.get(i);
                    mapForModel.put(myTextField.getName(), myTextField.getText());
                    if (i < 3)
                    {
                        mapForDatabase.put(myTextField.getName(), myTextField.getText());
                    }

                }
                
                String message = DataCheckControl.orderCheck(tableName, mapForModel); //檢查
                if(message.equals("成功")) {
                	// 新增資料可以直接進去view裡面，所以在這裡直接給view名稱就可以，不必給實體資料表名稱
                    isSuccess = TableControl.add2TableData(tableName, mapForDatabase, mapForModel,
                            (MyTableModel) table.getModel());
                    if(isSuccess) JOptionPane.showMessageDialog(panel, "新增"+message);
                }else {
                	JOptionPane.showMessageDialog(panel,message);  
				}
            	

                
            }

            if (isSuccess)
            {
                checkJLable.setText("新增資料成功");

            } else
            {
                checkJLable.setText("新增資料失敗，請確認資料是否齊全");
            }

        });

        // 修改事件
        updataButton.addActionListener((ActionEvent e) ->
        {

            int row = table.getSelectedRow();
            boolean isSuccess = false;

            if (row > -1)
            { // 如果使用者有選擇
              // 做詢問是否確認修改
                int check = JOptionPane.showConfirmDialog(panel, "是否要更新資料?", "更新", JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE);

                if (check == JOptionPane.YES_OPTION) // 如果點選是
                {

                    LinkedHashMap<String, String> mapForDataBase = new LinkedHashMap<>();
                    LinkedHashMap<String, String> mapForModel = new LinkedHashMap<>();

                    for (int i = 0; i < textList.size(); i++)
                    {
                        JTextField myTextField = textList.get(i);

                        mapForModel.put(myTextField.getName(), myTextField.getText());

                        if (i < 3)
                        {
                            mapForDataBase.put(myTextField.getName(), myTextField.getText());
                        }

                    }

                    MyTableModel model = (MyTableModel) table.getModel();
                    
                    String message = DataCheckControl.orderCheck(tableName, mapForModel);
                    if(message.equals("成功")) {
                    	// 這裡不用view用實體資料表
                        isSuccess = TableControl.updata2TableData("book_order", mapForDataBase, mapForModel, row, model);
                        if(isSuccess) JOptionPane.showMessageDialog(panel, "更新"+message);
                    }else {
                    	JOptionPane.showMessageDialog(panel, message);
					}
                    

                    if (isSuccess) // 修改使用者提示label
                    {
                        checkJLable.setText("資料更新成功");
                    } else
                    {
                        checkJLable.setText("資料更新失敗");
                    }
                }

            } else
            {
                // 如果沒有選擇，提示使用者要選擇資料後才能做修改
                JOptionPane.showMessageDialog(panel, "請點選要修改資料");
            }

        });

        // 刪除事件
        delButton.addActionListener((ActionEvent e) ->
        {

            if (table.getSelectedRowCount() == 1)
            { // 先確定使用者有沒有選擇資料，沒有就提示使用者

                int num = JOptionPane.showConfirmDialog(panel, "是否要刪除所選資料?", "刪除", JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE);

                if (num == JOptionPane.YES_OPTION)
                {
                    // 刪除資料不能直接在view上做，而且在view上也不能用desc抓primary key與value，所以在這用實體資料表"book_order"
                    int selectedRow = table.getSelectedRow();
                    TableControl.delTableData("book_order", selectedRow, (MyTableModel) table.getModel());
                    checkJLable.setText("資料刪除成功");
                }

            } else
            {

                JOptionPane.showMessageDialog(panel, "請選擇一筆資料做刪除");

            }

        });

        // table被點選時，自動設定textfield
        select.addListSelectionListener((ListSelectionEvent e) ->
        {

            int num = table.getSelectedRow();

            if (!e.getValueIsAdjusting() && num > -1)
            {

                Vector<String> obj = TableControl.getRowValue(num, (MyTableModel) table.getModel());

                for (int i = 0; i < obj.size(); i++)
                {
                    JTextField myTextField = textList.get(i);
                    myTextField.setText(obj.get(i));

                }

                // 對明細檔做的動作
                if (obj.get(0).startsWith("od") && dtTable != null)
                {
                    OrderDetailView.setOrderNumber(obj.get(0));

                    // 這裡組織查詢條件
                    StringBuffer where = new StringBuffer(" WHERE od_no = ");

                    where.append("'" + noTextField.getText() + "'");
                    System.out.println(where);

                    MyTableModel model = (MyTableModel) dtTable.getModel();
                    TableControl.reloadTableData("book_orderdt_view", where.toString(), model);

                }

            }

        });

    }

    @Override
    public void paintComponent(Graphics g)
    {
        Image image = new ImageIcon("./image/background.jpg").getImage();
        g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);
    }
}
