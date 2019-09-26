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

public class MemberSearchView extends JPanel
{

    private JTable table;
    private JPanel panel = this;
    private int count = 1;
    private String tableName;

    public MemberSearchView(JTable table, String tableName)
    {
        // 初始化
        this.table = table;
        this.tableName = tableName;
        this.setLayout(new GridBagLayout());

        // 排版
        GridBagConstraints constraints;
        int east = GridBagConstraints.EAST;
        int west = GridBagConstraints.WEST;

        Vector<Vector<Object>> list = new Vector<>(); // 儲存實例出來的視窗原件(按按鈕產生的)

        String[] colArray = { "欄位", "會員編號", "會員名稱", "電話", "生日", "地址", "Email" };
        String[] logicArray = { "查詢條件", "=", ">", "<", ">=", "<=", "!=", "Like" };

        JButton addRow = new JButton("+");
        constraints = MyGridBagLayout.getGridBagConstraints(0, 0, 1, 1, west);
        this.add(addRow, constraints);

        JButton okButton = new JButton("確認");
        constraints = MyGridBagLayout.getGridBagConstraints(1, 0, 1, 1, west);
        this.add(okButton, constraints);

        JButton clearButton = new JButton("清除");
        constraints = MyGridBagLayout.getGridBagConstraints(2, 0, 1, 1, west);
        this.add(clearButton, constraints);

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

            GridBagConstraints c = MyGridBagLayout.getGridBagConstraints(0, count, 1, 1, east);
            panel.add(colComboBox, c);

            c = MyGridBagLayout.getGridBagConstraints(1, count, 1, 1, east);
            panel.add(logicComboBox, c);

            c = MyGridBagLayout.getGridBagConstraints(2, count, 1, 1, east);
            panel.add(myText, c);

            list.add(vector);

            panel.updateUI();
            ;
            count++;

            colComboBox.addItemListener((ItemEvent item) ->
            {

                String str = (String) item.getItem();
                JComboBox<String> obj = (JComboBox<String>) item.getSource();

                switch (str) // combobox欄位被選取時設定相對應欄位名稱
                {
                    case "會員編號":
                        colComboBox.setName("m_no");
                        break;
                    case "會員名稱":
                        colComboBox.setName("m_name");
                        break;
                    case "電話":
                        colComboBox.setName("m_tel");
                        break;
                    case "生日":
                        colComboBox.setName("m_bir");
                        break;
                    case "地址":
                        colComboBox.setName("m_addr");
                        break;
                    case "Email":
                        colComboBox.setName("m_email");
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

        });

    }
    
    @Override
    public void paintComponent(Graphics g)
    {
        Image image = new ImageIcon("./image/bg_search.png").getImage();
        g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);
    }

}
