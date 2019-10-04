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
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;

import control.DataCheckControl;
import control.TableControl;
import service.MyTableModel;
import service.OrderDtTable;

public class OrderDetailView extends JPanel
{
    private String tableName;
    private JTable table;
    final JPanel panel = this;
    private ListSelectionModel select;

    private static JTextField noTextField; // 在主檔jtable點選時明細檔也要跟著改變用

    public OrderDetailView(JTable table, String tableName)
    {

        this.table = table;
        this.tableName = tableName;
        select = table.getSelectionModel();

        setLayout(new GridBagLayout());

        // 排版
        GridBagConstraints constraints;
        int EAST = GridBagConstraints.EAST;

        JLabel noLabel = new JLabel("訂單編號");
        MyGridBagLayout.getGridBagConstraints(this, noLabel, 0, 0, 1, 1, EAST);
        noTextField = new JTextField(15);
        MyGridBagLayout.getGridBagConstraints(this, noTextField, 1, 0, 1, 1, EAST);

        JLabel b_noLabel = new JLabel("書本編號");
        MyGridBagLayout.getGridBagConstraints(this, b_noLabel, 0, 1, 1, 1, EAST);
        JTextField b_noTextField = new JTextField(15);
        MyGridBagLayout.getGridBagConstraints(this, b_noTextField, 1, 1, 1, 1, EAST);

        JLabel b_nameLabel = new JLabel("書名");
        MyGridBagLayout.getGridBagConstraints(this, b_nameLabel, 0, 2, 1, 1, EAST);
        JTextField b_nameTextField = new JTextField(15);
        MyGridBagLayout.getGridBagConstraints(this, b_nameTextField, 1, 2, 1, 1, EAST);

        JLabel b_isbnLabel = new JLabel("ISBN");
        MyGridBagLayout.getGridBagConstraints(this, b_isbnLabel, 0, 3, 1, 1, EAST);
        JTextField b_isbnTextField = new JTextField(15);
        MyGridBagLayout.getGridBagConstraints(this, b_isbnTextField, 1, 3, 1, 1, EAST);

        JLabel b_catnoLabel = new JLabel("類別");
        MyGridBagLayout.getGridBagConstraints(this, b_catnoLabel, 2, 0, 1, 1, EAST);
        JTextField b_catnoTextField = new JTextField(15);
        MyGridBagLayout.getGridBagConstraints(this, b_catnoTextField, 3, 0, 4, 1, GridBagConstraints.WEST);

        JLabel b_unitLabel = new JLabel("單位");
        MyGridBagLayout.getGridBagConstraints(this, b_unitLabel, 2, 1, 1, 1, EAST);
        JTextField b_unitTextField = new JTextField(15);
        MyGridBagLayout.getGridBagConstraints(this, b_unitTextField, 3, 1, 4, 1, GridBagConstraints.WEST);

        JLabel qtyLabel = new JLabel("數量");
        MyGridBagLayout.getGridBagConstraints(this, qtyLabel, 2, 2, 1, 1, EAST);
        JTextField qtyTextField = new JTextField(15);
        MyGridBagLayout.getGridBagConstraints(this, qtyTextField, 3, 2, 4, 1, GridBagConstraints.WEST);

        JLabel priceLabel = new JLabel("售價");
        MyGridBagLayout.getGridBagConstraints(this, priceLabel, 2, 3, 1, 1, EAST);
        JTextField priceTextField = new JTextField(15);
        MyGridBagLayout.getGridBagConstraints(this, priceTextField, 3, 3, 4, 1, GridBagConstraints.WEST);

        JLabel resultLabel = new JLabel("*號欄位不得為空");
        MyGridBagLayout.getGridBagConstraints(this, resultLabel, 0, 4, 0, 1, GridBagConstraints.WEST);
        resultLabel.setForeground(Color.red);

        // 按鈕
        JButton searchButton = new JButton("搜尋");
        MyGridBagLayout.getGridBagConstraints(this, searchButton, 0, 5, 1, 1, GridBagConstraints.WEST);
        JButton selectButton = new JButton("選擇商品");
        MyGridBagLayout.getGridBagConstraints(this, selectButton, 1, 5, 1, 1, GridBagConstraints.WEST);
        JButton clearButton = new JButton("清除");
        MyGridBagLayout.getGridBagConstraints(this, clearButton, 3, 5, 1, 1, GridBagConstraints.WEST);
        JButton addButton = new JButton("新增");
        MyGridBagLayout.getGridBagConstraints(this, addButton, 4, 5, 1, 1, GridBagConstraints.WEST);
        JButton updateButton = new JButton("修改");
        MyGridBagLayout.getGridBagConstraints(this, updateButton, 5, 5, 1, 1, GridBagConstraints.WEST);
        JButton delButton = new JButton("刪除");
        MyGridBagLayout.getGridBagConstraints(this, delButton, 6, 5, 1, 1, GridBagConstraints.WEST);

        ArrayList<JLabel> lblList = new ArrayList<>(); // 存放所有jlabel
        ArrayList<JTextField> textList = new ArrayList<>();// 存放所有 jtextfield
        ArrayList<JButton> btnList = new ArrayList<>(); // 存放所有jbutton

        lblList.add(noLabel);
        lblList.add(b_noLabel);
        lblList.add(b_nameLabel);
        lblList.add(b_isbnLabel);
        lblList.add(b_catnoLabel);
        lblList.add(b_unitLabel);
        lblList.add(qtyLabel);
        lblList.add(priceLabel);
        lblList.add(resultLabel);

        textList.add(noTextField);
        textList.add(b_noTextField);
        textList.add(b_nameTextField);
        textList.add(b_isbnTextField);
        textList.add(b_catnoTextField);
        textList.add(b_unitTextField);
        textList.add(qtyTextField);
        textList.add(priceTextField);

        btnList.add(searchButton);
        btnList.add(selectButton);
        btnList.add(clearButton);
        btnList.add(addButton);
        btnList.add(updateButton);
        btnList.add(delButton);

        for (JLabel lbl : lblList)
        {
            lbl.setFont(new Font("微軟正黑體", Font.PLAIN, 14));
        }

        for (int i = 0; i < textList.size(); i++)
        {
            JTextField myTextField = textList.get(i);
            myTextField.setFont(new Font("微軟正黑體", Font.PLAIN, 14));

            Vector<String> column = OrderDtTable.column;
            myTextField.setName(column.get(i)); // 設定每個textfield的name,資料表相關資訊都在 OrderDtTable

            if (i < 6)
            {
                myTextField.setEditable(false);
            }
        }

        for (JButton jButton : btnList)
        {
            jButton.setFont(new Font("微軟正黑體", Font.PLAIN, 14));
        }

        // search
        searchButton.addActionListener((ActionEvent e) ->
        {

            OrderDetailSearchView searchView = new OrderDetailSearchView(table, tableName);
            JFrame searchFrame = new JFrame("訂單明細檔搜尋");
            searchFrame.setContentPane(searchView);
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

        // 選擇商品
        selectButton.addActionListener((ActionEvent e) ->
        {

            ArrayList<JTextField> list = new ArrayList<>();

            for (int i = 0; i < 5; i++)
            {
                list.add(textList.get(i + 1));
            }

            SelectProductView selectProductView = new SelectProductView(list);

        });

        clearButton.addActionListener((ActionEvent e) ->
        {

            for (JTextField textField : textList)
            {
                textField.setText("");
            }

        });

        // 新增
        addButton.addActionListener((ActionEvent e) ->
        {

            int check = JOptionPane.showConfirmDialog(panel, "是否要新增資料?", "新增", JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE);
            boolean isSuccess = false;

            if (check == JOptionPane.YES_OPTION)
            {

                LinkedHashMap<String, String> mapForDatabase = new LinkedHashMap<>(); // 給資料庫的資料
                LinkedHashMap<String, String> mapForModel = new LinkedHashMap<>(); // 給model的資料

                for (int i = 0; i < textList.size(); i++)
                {

                    JTextField myTextField = textList.get(i);

                    mapForModel.put(myTextField.getName(), myTextField.getText());
                    if (i <= 1 || i >= 6)
                    {
                        mapForDatabase.put(myTextField.getName(), myTextField.getText());
                    }

                }

                String message = DataCheckControl.orderDtCheck(tableName, mapForModel);

                if (message.equals("成功"))
                {
                    // 新增資料可以直接進去view裡面，所以在這裡直接給view名稱就可以，不必給實體資料表名稱
                    isSuccess = TableControl.add2TableData(tableName, mapForDatabase, mapForModel,
                            (MyTableModel) table.getModel());
                    if (isSuccess)
                        JOptionPane.showMessageDialog(panel, "更新" + message);
                } else
                {
                    JOptionPane.showMessageDialog(panel, message);
                }

            }

            if (isSuccess)
            {
                resultLabel.setText("新增資料成功");

            } else
            {
                resultLabel.setText("新增資料失敗，請確認資料是否齊全");
            }

        });

        // 更新
        updateButton.addActionListener((ActionEvent e) ->
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

                    LinkedHashMap<String, String> mapForDatabase = new LinkedHashMap<>();
                    LinkedHashMap<String, String> mapForModel = new LinkedHashMap<>();

                    for (int i = 0; i < textList.size(); i++)
                    {
                        JTextField myTextField = textList.get(i);

                        mapForModel.put(myTextField.getName(), myTextField.getText());

                        if (i <= 1 || i >= 6)
                        {
                            mapForDatabase.put(myTextField.getName(), myTextField.getText());
                        }

                    }

                    MyTableModel model = (MyTableModel) table.getModel();
                    // 這裡不用view用實體table，更新要抓PK，用view會抓不到
                    isSuccess = TableControl.updata2TableData("book_orderdetail", mapForDatabase, mapForModel, row,
                            model);

                    if (isSuccess) // 修改使用者提示label
                    {
                        resultLabel.setText("資料更新成功");
                    } else
                    {
                        resultLabel.setText("資料更新失敗");
                    }
                }
            } else
            {
                // 如果沒有選擇，提示使用者要選擇資料後才能做修改
                JOptionPane.showMessageDialog(panel, "請點選要修改資料");
            }

        });

        // 刪除
        delButton.addActionListener((ActionEvent e) ->
        {

            if (table.getSelectedRowCount() == 1)
            { // 先確定使用者有沒有選擇資料，沒有就提示使用者

                int num = JOptionPane.showConfirmDialog(panel, "是否要刪除所選資料?", "刪除", JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE);

                if (num == JOptionPane.YES_OPTION)
                {
                    // 刪除資料不能直接在view上做，而且在view上也不能用desc抓primary key，所以在這用實體資料表"book_orderdetail"
                    int selectedRow = table.getSelectedRow();
                    TableControl.delTableData("book_orderdetail", selectedRow, (MyTableModel) table.getModel());
                    resultLabel.setText("資料刪除成功");
                }

            } else
            {

                JOptionPane.showMessageDialog(panel, "請選擇一筆資料做刪除");

            }

        });

        // 點選jtable設定textfield
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

            }

        });

    }// 建構式

    public static void setOrderNumber(String str)
    {

        noTextField.setText(str);
    }

    @Override
    public void paintComponent(Graphics g)
    {
        Image image = new ImageIcon("./image/bg_dt.jpg").getImage();
        g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);
    }

}
