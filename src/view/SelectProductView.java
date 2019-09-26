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
import service.ProductTable;

public class SelectProductView extends JFrame
{
    ArrayList<JTextField> textFields;

    public SelectProductView(ArrayList<JTextField> textFields)
    {
        super("滑鼠點擊兩下選擇商品"); // 設定標題

        this.textFields = textFields;

        JTable productTable = new JTable();

        MyTableModel tableModel = TableControl.getTableModel(ProductTable.tableName);
        tableModel.setColumnName(ProductTable.columnName);
        tableModel.setType(ProductTable.type);

        productTable.setModel(tableModel);
        productTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        setContentPane(new JScrollPane(productTable));

        pack();
        setVisible(true);

        // 在滑鼠雙擊時設定orderView上的textField
        productTable.addMouseListener(new MouseAdapter()
        {

            @Override
            public void mouseClicked(MouseEvent e)
            {
                if (e.getClickCount() == 2 && !e.isConsumed())
                {
                    int row = productTable.getSelectedRow();
                    Vector<String> obj = TableControl.getRowValue(row, tableModel);

                    textFields.get(0).setText(obj.get(0)); // 書本編號
                    textFields.get(1).setText(obj.get(1)); // 書名
                    textFields.get(2).setText(obj.get(2)); // ISBN
                    textFields.get(3).setText(obj.get(3)); // 類別
                    textFields.get(4).setText(obj.get(5)); // 單位

                }

            }

        });

    }

}
