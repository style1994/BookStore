package view;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import control.TableControl;
import service.MemberTable;
import service.MyTableModel;

public class SelectMemberView extends JFrame
{
    ArrayList<JTextField> textFields;

    public SelectMemberView(ArrayList<JTextField> textFields)
    {
        super("滑鼠點擊兩下選擇廠商"); // 設定標題

        this.textFields = textFields;

        JTable memberTable = new JTable();

        MyTableModel tableModel = TableControl.getTableModel("membership");
        tableModel.setColumnName(MemberTable.columnName);
        tableModel.setType(MemberTable.type);

        memberTable.setModel(tableModel);
        memberTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        setContentPane(new JScrollPane(memberTable));

        pack();
        setVisible(true);

        // 在滑鼠雙擊時設定orderView上的textField
        memberTable.addMouseListener(new MouseAdapter()
        {

            @Override
            public void mouseClicked(MouseEvent e)
            {
                if (e.getClickCount() == 2 && !e.isConsumed())
                {
                    int row = memberTable.getSelectedRow();
                    Vector<String> obj = TableControl.getRowValue(row, tableModel);

                    textFields.get(0).setText(obj.get(0)); // 會員編號
                    textFields.get(1).setText(obj.get(1)); // 會員姓名
                    textFields.get(2).setText(obj.get(2)); // 電話
                    textFields.get(3).setText(obj.get(4)); // 地址

                }

            }

        });

    }

}
