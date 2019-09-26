package test;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

public class SetColumnsSize extends JPanel
{
    boolean deBug = true;

    public SetColumnsSize()
    {

        String[] header = { "FirstName", "LastName", "Sport", "# of Years", "Vegetarian" }; // 表頭資料
        Object[][] data = { { "Kathy", "Smith", "Snoborading", new Integer(5), new Boolean(false) }, // 攔位資料
                { "John", "Doe", "Rowing", new Integer(3), new Boolean(true) },
                { "Sue", "Black", "Knitting", new Integer(2), new Boolean(false) },
                { "John", "White", "Speed reading", new Integer(20), new Boolean(true) },
                { "Joe", "Brown", "Pool", new Integer(10), new Boolean(false) },
                { "Cat", "Tommy", "Game", new Integer(25), new Boolean(false) }

        };

        // 建立一個JTable
        // TableModleg使用預設DefaultTableModel,DefaultTableColumnModel,DefaultListSelectionMode
        JTable table = new JTable(data, header);
        table.setBackground(Color.yellow); // 背景使用黃色方便比較

//		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS); 
//		table.setPreferredScrollableViewportSize(new Dimension(700, 400)); //設定表格大小

        if (deBug)
        {
            showTable(table);
        }

        JScrollPane scroll = new JScrollPane(table);
        scroll.setBackground(Color.green);
        add(scroll);

        JPanel panel = new JPanel();
        JButton btn1 = new JButton("表格填充整個視圖");
        JButton btn2 = new JButton("表格不添加整個視圖(默認不填充)");

        panel.add(btn1);
        panel.add(btn2);
        panel.setBackground(Color.blue);

        add(panel);
        columnsSize(table); // 改變攔位大小

        btn1.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e)
            {
                table.setFillsViewportHeight(true);

            }
        });

        btn2.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e)
            {
                table.setFillsViewportHeight(false);

            }
        });

    }

    public void showTable(JTable table)
    {
        int columnCount = table.getColumnCount(); // table 欄數
        int rowCount = table.getRowCount(); // table 列數
        TableModel model = table.getModel();
        // 印出所有table資料再console
        for (int i = 0; i < rowCount; i++)
        {
            System.out.print("row " + i + ":");
            for (int j = 0; j < columnCount; j++)
            {
                System.out.print(" " + model.getValueAt(i, j));
            }
            System.out.println(); // 換行
        }
    }

    public static void GUIBulid()
    {
        JFrame frm = new JFrame("Simple table");
        SetColumnsSize simpleTable = new SetColumnsSize();
        simpleTable.setOpaque(true); // 不透明
        simpleTable.setLayout(new FlowLayout());

        frm.setContentPane(simpleTable);
        frm.setDefaultCloseOperation(frm.EXIT_ON_CLOSE);
        frm.setSize(800, 600);
        frm.setLocationRelativeTo(null); // frame置中
        frm.setVisible(true);
    }

    public void columnsSize(JTable table)
    {
        TableColumnModel columnModle = table.getColumnModel();

        int columnCount = table.getColumnCount();

        for (int i = 0; i < columnCount; i++)
        {
            TableColumn column = columnModle.getColumn(i);
            column.setPreferredWidth((i + 1) * 50);
        }

    }

    public static void main(String[] args)
    {
        GUIBulid();
    }
}
