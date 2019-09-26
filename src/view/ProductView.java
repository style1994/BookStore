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
        GridBagConstraints constraints;
        final int EAST = GridBagConstraints.EAST;
        final int WEST = GridBagConstraints.WEST;

        JLabel noLabel = new JLabel("*書本編號: ");
        constraints = MyGridBagLayout.getGridBagConstraints(0, 0, 1, 1, WEST);
        this.add(noLabel, constraints);
        JTextField noTextField = new JTextField(15);
        constraints = MyGridBagLayout.getGridBagConstraints(1, 0, 1, 1, WEST);
        this.add(noTextField, constraints);

        JLabel nameLabel = new JLabel("*書名: ");
        constraints = MyGridBagLayout.getGridBagConstraints(0, 1, 1, 1, WEST);
        this.add(nameLabel, constraints);
        JTextField nameTextField = new JTextField(15);
        constraints = MyGridBagLayout.getGridBagConstraints(1, 1, 1, 1, WEST);
        this.add(nameTextField, constraints);

        JLabel isbnJLabel = new JLabel("*ISBN: ");
        constraints = MyGridBagLayout.getGridBagConstraints(0, 2, 1, 1, WEST);
        this.add(isbnJLabel, constraints);
        JTextField isbnTextField = new JTextField(15); // isbn 13碼
        constraints = MyGridBagLayout.getGridBagConstraints(1, 2, 1, 1, WEST);
        this.add(isbnTextField, constraints);

        JLabel catnoLabel = new JLabel("類別: ");
        constraints = MyGridBagLayout.getGridBagConstraints(0, 3, 1, 1, WEST);
        this.add(catnoLabel, constraints);
        JTextField catnoTextField = new JTextField(15);
        constraints = MyGridBagLayout.getGridBagConstraints(1, 3, 1, 1, WEST);
        this.add(catnoTextField, constraints);

        JLabel pubLabel = new JLabel("出版社: ");
        constraints = MyGridBagLayout.getGridBagConstraints(0, 4, 1, 1, WEST);
        this.add(pubLabel, constraints);
        JTextField pubTextField = new JTextField(15);
        constraints = MyGridBagLayout.getGridBagConstraints(1, 4, 1, 1, WEST);
        this.add(pubTextField, constraints);

        JLabel unitLabel = new JLabel("單位: ");
        constraints = MyGridBagLayout.getGridBagConstraints(2, 0, 1, 1, EAST);
        this.add(unitLabel, constraints);
        JTextField uniTextField = new JTextField(15);
        constraints = MyGridBagLayout.getGridBagConstraints(3, 0, 3, 1, WEST);
        this.add(uniTextField, constraints);

        JLabel priceLabel = new JLabel("*定價: ");
        constraints = MyGridBagLayout.getGridBagConstraints(2, 1, 1, 1, EAST);
        this.add(priceLabel, constraints);
        JTextField priceTextField = new JTextField(15);
        constraints = MyGridBagLayout.getGridBagConstraints(3, 1, 3, 1, WEST);
        this.add(priceTextField, constraints);

        JLabel qtyLabel = new JLabel("*數量: ");
        constraints = MyGridBagLayout.getGridBagConstraints(2, 2, 1, 1, EAST);
        this.add(qtyLabel, constraints);
        JTextField qtyTextField = new JTextField(15);
        constraints = MyGridBagLayout.getGridBagConstraints(3, 2, 3, 1, WEST);
        this.add(qtyTextField, constraints);

        JLabel posLabel = new JLabel("儲位: ");
        constraints = MyGridBagLayout.getGridBagConstraints(2, 3, 1, 1, EAST);
        this.add(posLabel, constraints);
        JTextField posTextField = new JTextField(15);
        constraints = MyGridBagLayout.getGridBagConstraints(3, 3, 3, 1, WEST);
        this.add(posTextField, constraints);

        JLabel resultLabel = new JLabel("*號欄位不得為空");
        constraints = MyGridBagLayout.getGridBagConstraints(0, 5, 0, 1, WEST);
        resultLabel.setForeground(Color.red);
        resultLabel.setFont(new Font("微軟正黑體", Font.PLAIN, 16));
        this.add(resultLabel, constraints);

        // 按鈕
        JButton searchButton = new JButton("查詢");
        constraints = MyGridBagLayout.getGridBagConstraints(0, 6, 1, 1, WEST);
        this.add(searchButton, constraints);
        JButton clearButton = new JButton("Reset");
        constraints = MyGridBagLayout.getGridBagConstraints(2, 6, 1, 1, WEST);
        this.add(clearButton, constraints);
        JButton addButton = new JButton("新增");
        constraints = MyGridBagLayout.getGridBagConstraints(3, 6, 1, 1, WEST);
        this.add(addButton, constraints);
        JButton updataButton = new JButton("修改");
        constraints = MyGridBagLayout.getGridBagConstraints(4, 6, 1, 1, WEST);
        this.add(updataButton, constraints);
        JButton delButton = new JButton("刪除");
        constraints = MyGridBagLayout.getGridBagConstraints(5, 6, 1, 1, WEST);
        this.add(delButton, constraints);

        ArrayList<JLabel> lblArrayList = new ArrayList<>(); // Jlable 陣列
        ArrayList<JTextField> textArrayList = new ArrayList<>(); // textField 陣列

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

        int num = 0;// 獲取column的指標
        for (JTextField jTextField : textArrayList) // 統一設定JTextField
        {
            jTextField.setName(ProductTable.column.get(num));
            jTextField.setFont(new Font("微軟正黑體", Font.BOLD, 14));
            num++;
        }

        // 事件

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
            LinkedHashMap<String, String> map = new LinkedHashMap<>();
            for (JTextField jTextField : textArrayList) // 把textfield資料丟進map
            {
                System.out.println(jTextField.getName() + "," + jTextField.getText());
                map.put(jTextField.getName(), jTextField.getText());
            }

            int check = JOptionPane.showConfirmDialog(panel, "是否要新增資料?", "新增", JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE);

            boolean isSuccess = false; // 判斷新增資料是否成功

            if (check == JOptionPane.YES_OPTION)
            {
                isSuccess = TableControl.addTableData(tableName, map, (MyTableModel) table.getModel());
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
            int row = table.getSelectedRow(); // 被選取的row
            boolean isSuccess = false; // 紀錄執行sql指令後的結果

            if (row > -1)
            {// 確認使用者jtable有選擇資料欄位
             // 詢問使用者是否確定修改
                int check = JOptionPane.showConfirmDialog(panel, "是否要更新資料?", "更新", JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE);

                if (check == JOptionPane.YES_OPTION)
                {
                    LinkedHashMap<String, String> map = new LinkedHashMap<>();
                    MyTableModel model = (MyTableModel) table.getModel(); // 拿到自訂的table model
                    for (JTextField jTextField : textArrayList)// 把textField與真實資料庫的欄位對應後放入map
                    {
                        map.put(jTextField.getName(), jTextField.getText());
                    }

                    isSuccess = TableControl.updataTableData(tableName, map, row, model);

                    if (isSuccess) // 修改提示label訊息
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
