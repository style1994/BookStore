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
import service.ProductTable;

public class ProductView extends JPanel
{
    final JPanel panel = this;
    JTable table;
    String tableName;
    ListSelectionModel select;

    public ProductView(JTable table, String tableName)
    {
        this.table = table;
        this.tableName = tableName;

        select = table.getSelectionModel();

        setLayout(new GridBagLayout());
        // 排版

        final int EAST = GridBagConstraints.EAST;
        final int WEST = GridBagConstraints.WEST;

        JLabel noLabel = new JLabel("*書本編號: ");
        MyGridBagLayout.getGridBagConstraints(this, noLabel, 0, 0, 1, 1, WEST);
        JTextField noTextField = new JTextField(15);
        MyGridBagLayout.getGridBagConstraints(this, noTextField, 1, 0, 1, 1, WEST);

        JLabel nameLabel = new JLabel("*書名: ");
        MyGridBagLayout.getGridBagConstraints(this, nameLabel, 0, 1, 1, 1, WEST);
        JTextField nameTextField = new JTextField(15);
        MyGridBagLayout.getGridBagConstraints(this, nameTextField, 1, 1, 1, 1, WEST);

        JLabel isbnJLabel = new JLabel("*ISBN: ");
        MyGridBagLayout.getGridBagConstraints(this, isbnJLabel, 0, 2, 1, 1, WEST);
        JTextField isbnTextField = new JTextField(15); // isbn13碼
        MyGridBagLayout.getGridBagConstraints(this, isbnTextField, 1, 2, 1, 1, WEST);

        JLabel catnoLabel = new JLabel("類別: ");
        MyGridBagLayout.getGridBagConstraints(this, catnoLabel, 0, 3, 1, 1, WEST);
        JTextField catnoTextField = new JTextField(15);
        MyGridBagLayout.getGridBagConstraints(this, catnoTextField, 1, 3, 1, 1, WEST);

        JLabel pubLabel = new JLabel("出版社: ");
        MyGridBagLayout.getGridBagConstraints(this, pubLabel, 0, 4, 1, 1, WEST);
        JTextField pubTextField = new JTextField(15);
        MyGridBagLayout.getGridBagConstraints(this, pubTextField, 1, 4, 1, 1, WEST);

        JLabel unitLabel = new JLabel("單位: ");
        MyGridBagLayout.getGridBagConstraints(this, unitLabel, 2, 0, 1, 1, EAST);
        JTextField uniTextField = new JTextField(15);
        MyGridBagLayout.getGridBagConstraints(this, uniTextField, 3, 0, 3, 1, WEST);

        JLabel priceLabel = new JLabel("*定價: ");
        MyGridBagLayout.getGridBagConstraints(this, priceLabel, 2, 1, 1, 1, EAST);
        JTextField priceTextField = new JTextField(15);
        MyGridBagLayout.getGridBagConstraints(this, priceTextField, 3, 1, 3, 1, WEST);

        JLabel qtyLabel = new JLabel("*數量: ");
        MyGridBagLayout.getGridBagConstraints(this, qtyLabel, 2, 2, 1, 1, EAST);
        JTextField qtyTextField = new JTextField(15);
        MyGridBagLayout.getGridBagConstraints(this, qtyTextField, 3, 2, 3, 1, WEST);

        JLabel posLabel = new JLabel("儲位: ");
        MyGridBagLayout.getGridBagConstraints(this, posLabel, 2, 3, 1, 1, EAST);
        JTextField posTextField = new JTextField(15);
        MyGridBagLayout.getGridBagConstraints(this, posTextField, 3, 3, 3, 1, WEST);

        JLabel resultLabel = new JLabel("*號欄位不得為空"); // 顯示結果的Label
        MyGridBagLayout.getGridBagConstraints(this, resultLabel, 0, 5, 0, 1, WEST);
        resultLabel.setForeground(Color.red);
        resultLabel.setFont(new Font("微軟正黑體", Font.PLAIN, 16));

        // 按鈕
        JButton searchButton = new JButton("查詢");
        MyGridBagLayout.getGridBagConstraints(this, searchButton, 0, 6, 1, 1, WEST);
        JButton clearButton = new JButton("Reset");
        MyGridBagLayout.getGridBagConstraints(this, clearButton, 2, 6, 1, 1, WEST);
        JButton addButton = new JButton("新增");
        MyGridBagLayout.getGridBagConstraints(this, addButton, 3, 6, 1, 1, WEST);
        JButton updataButton = new JButton("修改");
        MyGridBagLayout.getGridBagConstraints(this, updataButton, 4, 6, 1, 1, WEST);
        JButton delButton = new JButton("刪除");
        MyGridBagLayout.getGridBagConstraints(this, delButton, 5, 6, 1, 1, WEST);

        ArrayList<JLabel> lblArrayList = new ArrayList<>(); // JLable 陣列
        ArrayList<JTextField> textArrayList = new ArrayList<>(); // TextField 陣列

        lblArrayList.add(noLabel);
        lblArrayList.add(nameLabel);
        lblArrayList.add(isbnJLabel);
        lblArrayList.add(catnoLabel);
        lblArrayList.add(pubLabel);
        lblArrayList.add(unitLabel);
        lblArrayList.add(priceLabel);
        lblArrayList.add(qtyLabel);
        lblArrayList.add(posLabel);

        textArrayList.add(noTextField);
        textArrayList.add(nameTextField);
        textArrayList.add(isbnTextField);
        textArrayList.add(catnoTextField);
        textArrayList.add(pubTextField);
        textArrayList.add(uniTextField);
        textArrayList.add(priceTextField);
        textArrayList.add(qtyTextField);
        textArrayList.add(posTextField);

        for (JLabel jLabel : lblArrayList) // 統一設定JLabel
        {
            jLabel.setFont(new Font("微軟正黑體", Font.BOLD, 14));
        }

        int num = 0;// Column index
        for (JTextField jTextField : textArrayList) // 統一設定JTextField
        {
            jTextField.setName(ProductTable.column.get(num));
            jTextField.setFont(new Font("微軟正黑體", Font.BOLD, 14));
            num++;
        }

        // 搜尋
        searchButton.addActionListener((ActionEvent e) ->
        {

            JFrame searchFrame = new JFrame("搜尋");
            ProductSearchView searchView = new ProductSearchView(table, tableName);
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

        // 清除
        clearButton.addActionListener((ActionEvent e) ->
        {

            for (JTextField jTextField : textArrayList)
            {
                jTextField.setText("");
            }

        });

        // 新增
        addButton.addActionListener((ActionEvent e) ->
        {

            int check = JOptionPane.showConfirmDialog(panel, "是否要新增資料?", "新增", JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE);

            boolean isSuccess = false; // 判斷新增資料是否成功

            if (check == JOptionPane.YES_OPTION)
            {

                LinkedHashMap<String, String> map = new LinkedHashMap<>();
                for (JTextField jTextField : textArrayList) // 紀錄TextFied資料
                {
                    map.put(jTextField.getName(), jTextField.getText());
                }

                String message = DataCheckControl.productCheck(tableName, map);// 判斷資料是否新增成功
                if (message.equals("成功"))
                {
                    isSuccess = TableControl.addTableData(tableName, map, (MyTableModel) table.getModel());
                    if (isSuccess == true)
                        JOptionPane.showMessageDialog(panel, "新增" + message);
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
        updataButton.addActionListener((ActionEvent e) ->
        {
            int row = table.getSelectedRow(); // 被選取的Row
            boolean isSuccess = false; // 紀錄執行SQL指令後的結果

            if (row > -1) // 確認使用者JTable有選擇資料欄位
            {

                int check = JOptionPane.showConfirmDialog(panel, "是否要更新資料?", "更新", JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE);

                if (check == JOptionPane.YES_OPTION) // 詢問使用者是否確定修改
                {
                    LinkedHashMap<String, String> map = new LinkedHashMap<>();
                    MyTableModel model = (MyTableModel) table.getModel(); // 拿到自訂的table model
                    for (JTextField jTextField : textArrayList)// 把textField與真實資料庫的欄位對應後放入map
                    {
                        map.put(jTextField.getName(), jTextField.getText());
                    }

                    String message = DataCheckControl.productCheck(tableName, map); // 做資料檢查
                    if (message.equals("成功"))
                    {
                        isSuccess = TableControl.updataTableData(tableName, map, row, model);
                        if (isSuccess == true)
                            JOptionPane.showMessageDialog(panel, "更新" + message);
                    } else
                    {
                        JOptionPane.showMessageDialog(panel, message);
                    }

                    if (isSuccess) // 修改提示Label訊息
                    {
                        resultLabel.setText("資料更新成功");
                    } else
                    {
                        resultLabel.setText("資料更新失敗");
                    }
                }

            } else
            {
                // 如果使用者沒有選擇提示使用者
                JOptionPane.showMessageDialog(panel, "請選擇要修改的資料");
            }

        });

        // 刪除
        delButton.addActionListener((ActionEvent e) ->
        {

            if (table.getSelectedRowCount() == 1)
            {// 先確定使用者有沒有選擇資料，沒有就提示使用者

                int check = JOptionPane.showConfirmDialog(panel, "是否要刪除所選資料?", "刪除", JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE);

                if (check == JOptionPane.YES_OPTION)
                {
                    int selectedRow = table.getSelectedRow();
                    TableControl.delTableData(tableName, selectedRow, (MyTableModel) table.getModel());
                    resultLabel.setText("資料刪除成功");
                }

            } else
            {

                JOptionPane.showMessageDialog(panel, "請選擇一筆資料做刪除");

            }

        });

        // 選取時設定textField的值
        select.addListSelectionListener((ListSelectionEvent e) ->
        {

            int row = table.getSelectedRow();

            if (!e.getValueIsAdjusting() && row > -1)
            {
                Vector<String> obj = TableControl.getRowValue(row, (MyTableModel) table.getModel());

                for (int i = 0; i < obj.size(); i++)
                {

                    JTextField myTextField = textArrayList.get(i);
                    myTextField.setText(obj.get(i));

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
