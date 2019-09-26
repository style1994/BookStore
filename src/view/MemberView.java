package view;

import java.awt.Color;
import java.awt.FlowLayout;
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

public class MemberView extends JPanel
{
    JTable table;
    JPanel panel; // 儲存自己的參照，再JOptionpanel有用
    ListSelectionModel select;

    final static String tableName = "membership"; // 儲存資料表名稱

    public MemberView(JTable table)
    {
        // 資料表實際欄位名稱
        Vector<String> columnName = TableControl.getColumn(tableName);

        // TABLE設定
        this.table = table;
        select = table.getSelectionModel();

        setLayout(new GridBagLayout());
        setBackground(new Color(135, 210, 220)); // 藍色

        GridBagConstraints constraints;
        int east = GridBagConstraints.EAST;
        int west = GridBagConstraints.WEST;

        // 原件的排版
        JLabel noLabel = new JLabel("*會員編號: ");
        JTextField noTextField = new JTextField(10);
        constraints = MyGridBagLayout.getGridBagConstraints(0, 0, 1, 1, east);
        this.add(noLabel, constraints);
        constraints = MyGridBagLayout.getGridBagConstraints(1, 0, 1, 1, east);
        this.add(noTextField, constraints);

        JLabel nameLabel = new JLabel("*會員名稱: ");
        JTextField nameTextField = new JTextField(10);
        constraints = MyGridBagLayout.getGridBagConstraints(0, 1, 1, 1, east);
        this.add(nameLabel, constraints);
        constraints = MyGridBagLayout.getGridBagConstraints(1, 1, 1, 1, east);
        this.add(nameTextField, constraints);

        JLabel telLabel = new JLabel("*電話: ");
        JTextField telTextField = new JTextField(10);
        constraints = MyGridBagLayout.getGridBagConstraints(0, 2, 1, 1, east);
        this.add(telLabel, constraints);
        constraints = MyGridBagLayout.getGridBagConstraints(1, 2, 1, 1, east);
        this.add(telTextField, constraints);

        JLabel birLabel = new JLabel("生日: ");
        JTextField birTextField = new JTextField(10);
        constraints = MyGridBagLayout.getGridBagConstraints(2, 0, 1, 1, east);
        this.add(birLabel, constraints);
        constraints = MyGridBagLayout.getGridBagConstraints(3, 0, 1, 1, west);
        this.add(birTextField, constraints);

        JLabel addrLabel = new JLabel("地址: ");
        JTextField addrTextField = new JTextField(25);
        constraints = MyGridBagLayout.getGridBagConstraints(2, 1, 1, 1, east);
        this.add(addrLabel, constraints);
        constraints = MyGridBagLayout.getGridBagConstraints(3, 1, 4, 1, west);
        this.add(addrTextField, constraints);

        JLabel emaiLabel = new JLabel("Email: ");
        JTextField emailField = new JTextField(25);
        constraints = MyGridBagLayout.getGridBagConstraints(2, 2, 1, 1, east);
        this.add(emaiLabel, constraints);
        constraints = MyGridBagLayout.getGridBagConstraints(3, 2, 4, 1, west);
        this.add(emailField, constraints);

        JLabel resultLabel = new JLabel("*號攔位不得為空");// 提示使用者訊息用Label
        constraints = MyGridBagLayout.getGridBagConstraints(0, 3, 0, 1, west);
        this.add(resultLabel, constraints);
        resultLabel.setFont(new Font("微軟正黑體", Font.PLAIN, 14));
        resultLabel.setForeground(Color.red);

        // 按鈕
        JButton searchButton = new JButton("搜尋");
        constraints = MyGridBagLayout.getGridBagConstraints(0, 4, 1, 1, east);
        this.add(searchButton, constraints);
        JButton cancleButton = new JButton("Reset");
        constraints = MyGridBagLayout.getGridBagConstraints(3, 4, 1, 1, east);
        this.add(cancleButton, constraints);
        JButton addButton = new JButton("新增");
        constraints = MyGridBagLayout.getGridBagConstraints(4, 4, 1, 1, east);
        this.add(addButton, constraints);
        JButton updataButton = new JButton("修改");
        constraints = MyGridBagLayout.getGridBagConstraints(5, 4, 1, 1, east);
        this.add(updataButton, constraints);
        JButton delButton = new JButton("刪除");
        constraints = MyGridBagLayout.getGridBagConstraints(6, 4, 0, 1, east);
        this.add(delButton, constraints);

        ArrayList<JLabel> labelList = new ArrayList<>(); // jlabel集合，做統一操作用
        labelList.add(noLabel);
        labelList.add(nameLabel);
        labelList.add(telLabel);
        labelList.add(birLabel);
        labelList.add(addrLabel);
        labelList.add(emaiLabel);

        ArrayList<JTextField> textFieldList = new ArrayList<JTextField>(); // 儲存textField的集合，按照資料表欄位順序
        textFieldList.add(noTextField);
        textFieldList.add(nameTextField);
        textFieldList.add(telTextField);
        textFieldList.add(birTextField);
        textFieldList.add(addrTextField);
        textFieldList.add(emailField);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new FlowLayout(FlowLayout.LEADING, 20, 20));

        for (int i = 0; i < labelList.size(); i++) // 做排版與設定統一屬性
        {
            JLabel myLabel = labelList.get(i);
            JTextField myTextField = textFieldList.get(i);

            myLabel.setFont(new Font("微軟正黑體", Font.PLAIN, 14));
            myTextField.setFont(new Font("微軟正黑體", Font.PLAIN, 14));
        }

        for (int i = 0; i < textFieldList.size(); i++)

        { // 設定各textfield欄位名稱

            textFieldList.get(i).setName(columnName.get(i));
        }

        // 以下監聽

        // 搜尋按鈕
        searchButton.addActionListener((ActionEvent e) ->
        {

            JFrame searchFrame = new JFrame("搜尋");
            MemberSearchView searchView = new MemberSearchView(table, tableName);
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

        // 清除textfield欄位資料
        cancleButton.addActionListener((ActionEvent e) ->
        {
            // 將所有textField內容設為空字串
            textFieldList.forEach((JTextField text) ->
            {
                text.setText("");
            });
        });

        // 新增資料
        addButton.addActionListener((ActionEvent e) ->
        {

            LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
            for (int i = 0; i < textFieldList.size(); i++)
            {
                JTextField myTextField = textFieldList.get(i);
                map.put(myTextField.getName(), myTextField.getText());

            }

            int check = JOptionPane.showConfirmDialog(panel, "是否要新增資料?", "新增", JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE);
            boolean isSuccess = false;

            if (check == JOptionPane.YES_OPTION)
            {
                boolean checkNull = DataCheckControl.memberCheck(tableName, map);

                if (checkNull)
                {
                    isSuccess = TableControl.addTableData(tableName, map, (MyTableModel) table.getModel());
                } else
                {
                    JOptionPane.showMessageDialog(panel, "*號欄位不得為空");
                }

                if (isSuccess)
                {
                    resultLabel.setText("新增資料成功");

                } else
                {
                    resultLabel.setText("新增資料失敗，請確認資料是否齊全");
                }
            }

        });

        // 刪除資料
        delButton.addActionListener((ActionEvent e) ->
        {

            if (table.getSelectedRowCount() == 1)
            { // 先確定使用者有沒有選擇資料，沒有就提示使用者

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

        // 修改資料
        updataButton.addActionListener((ActionEvent e) ->
        {
            int row = table.getSelectedRow(); // 被選取的row
            boolean isSuccess = false; // 確認是否成功布林值

            if (row > -1)
            { // 確認是否被選取
              // 詢問使用者是否確定新增
                int check = JOptionPane.showConfirmDialog(panel, "是否要更新資料?", "更新", JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE);

                if (check == JOptionPane.YES_OPTION) // 如果使用者選擇是
                {

                    LinkedHashMap<String, String> map = new LinkedHashMap<>();
                    MyTableModel model = (MyTableModel) table.getModel(); // 拿到自訂的table model
                    for (JTextField jTextField : textFieldList)// 把textField與真實資料庫的欄位對應後放入
                    {
                        map.put(jTextField.getName(), jTextField.getText());
                    }

                    isSuccess = TableControl.updataTableData(tableName, map, row, model);

                    if (isSuccess)
                    {
                        resultLabel.setText("資料更新成功");
                    } else
                    {
                        resultLabel.setText("資料更新失敗");
                    }
                }

            } else
            {
                // 當使用者jtable沒有選擇時提示
                JOptionPane.showMessageDialog(panel, "請選擇要修改的資料");
            }

        });

        // 當滑鼠點擊再jtable 上面textField會對應顯示所點擊列的資料

        select.addListSelectionListener((ListSelectionEvent e) ->
        {

            int num = table.getSelectedRow();

            if (!e.getValueIsAdjusting() && num > -1)
            {

                Vector<String> obj = TableControl.getRowValue(num, (MyTableModel) table.getModel());

                for (int i = 0; i < obj.size(); i++)
                {

                    JTextField myTextField = textFieldList.get(i);
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