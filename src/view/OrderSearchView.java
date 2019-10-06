package view;

import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

import control.TableControl;
import service.MyTableModel;

public class OrderSearchView extends JPanel
{

    private JTable table;
    private JPanel panel = this;
    private int count = 1;
    private String tableName; // book_order_view

    public OrderSearchView(JTable table, String tableName)
    {
        // 初始化
        this.table = table;
        this.tableName = tableName;
        this.setLayout(new GridBagLayout());

        // 排版
        int east = GridBagConstraints.EAST;

        Vector<Vector<Object>> list = new Vector<>(); // 儲存實例出來的視窗原件(按按鈕產生的)

        String[] colArray = { "欄位", "訂單編號", "訂單日期", "會員編號", "會員名稱", "電話", "地址" };
        String[] logicArray = { "查詢條件", "=", ">", "<", ">=", "<=", "!=", "Like" };

        JButton addRow = new JButton("+");
        MyGridBagLayout.getGridBagConstraints(this, addRow, 0, 0, 1, 1, GridBagConstraints.CENTER);

        JButton okButton = new JButton("確認");
        MyGridBagLayout.getGridBagConstraints(this, okButton, 1, 0, 1, 1, GridBagConstraints.CENTER);

        JButton clearButton = new JButton("清除");
        MyGridBagLayout.getGridBagConstraints(this, clearButton, 2, 0, 1, 1, GridBagConstraints.CENTER);

        // 新增搜尋條件事件
        addRow.addActionListener((ActionEvent e) ->
        {
            Vector<Object> vector = new Vector<>();

            JComboBox<String> colComboBox = new JComboBox<String>(colArray);
            JComboBox<String> logicComboBox = new JComboBox<>(logicArray);
            JTextField myText = new JTextField(10);

            vector.add(colComboBox);
            vector.add(logicComboBox);
            vector.add(myText);

            MyGridBagLayout.getGridBagConstraints(panel, colComboBox, 0, count, 1, 1, east);
            MyGridBagLayout.getGridBagConstraints(panel, logicComboBox, 1, count, 1, 1, east);
            MyGridBagLayout.getGridBagConstraints(panel, myText, 2, count, 1, 1, east);

            list.add(vector);
            panel.updateUI();
            count++;

            colComboBox.addItemListener((ItemEvent item) ->
            {

                String str = (String) item.getItem();

                switch (str) // ComboBox欄位被選取時設定相對應欄位名稱
                {
                    case "訂單編號":
                        colComboBox.setName("o_no");
                        break;
                    case "訂單日期":
                        colComboBox.setName("o_date");
                        break;
                    case "會員編號":
                        colComboBox.setName("o_m_no");
                        break;
                    case "會員名稱":
                        colComboBox.setName("m_name");
                        break;
                    case "電話":
                        colComboBox.setName("m_tel");
                        break;
                    case "地址":
                        colComboBox.setName("m_addr");
                        break;

                }

            });

        });

        // 確認事件
        okButton.addActionListener((ActionEvent e) ->
        {

            StringBuffer where = new StringBuffer(" where ");

            for (Vector<Object> object : list)
            {
                JComboBox<String> myCol = (JComboBox<String>) object.get(0);
                JComboBox<String> myLogic = (JComboBox<String>) object.get(1);
                JTextField myTextField = (JTextField) object.get(2);

                if (myCol.getSelectedIndex() > 0 && myLogic.getSelectedIndex() > 0 && !myTextField.getText().equals(""))
                {

                    String selectCol = myCol.getName();
                    String selectLogic = (String) myLogic.getSelectedItem();
                    String text = myTextField.getText();

                    if (selectLogic.equals("Like"))
                    {
                        where.append(selectCol + " " + selectLogic + "'%" + text + "%' AND ");
                    } else if (selectCol.equals("o_date") && !text.matches("\\d{4}(-\\d\\d){2}")) // 如果日期沒有輸入完整格式都用Like來處理
                    {

                        where.append(selectCol + " " + "Like" + "'%" + text + "%' AND ");

                    } else
                    {
                        where.append(selectCol + selectLogic + "'" + text + "' AND ");
                    }

                }

            }

            where.delete(where.length() - 4, where.length() - 1);
            System.out.println(where);

            TableControl.reloadTableData(tableName, where.toString(), (MyTableModel) table.getModel());

        });

        // 清除所有條件內容事件
        clearButton.addActionListener((ActionEvent e) ->
        {

            for (Vector<Object> obj : list)
            {
                ((JComboBox<String>) obj.get(0)).setSelectedIndex(0);
                ((JComboBox<String>) obj.get(1)).setSelectedIndex(1);
                ((JTextField) obj.get(2)).setText("");

            }

            TableControl.reloadTableData(tableName, "", (MyTableModel) table.getModel());

        });

    }

    @Override
    public void paintComponent(Graphics g)
    {
        Image image = new ImageIcon("./image/bg_search.png").getImage();
        g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);
    }

}
