package test;

import java.awt.GridLayout;

import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;

public class SetTableType extends JPanel
{

    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(new Runnable()
        {

            @Override
            public void run()
            {
                showGUI();

            }
        });
    }

    // 建構式 做table相關事項
    public SetTableType()
    {

        String[] column = { "FirstName", "LastName", "Sport", "# of Years", "Vegetarian" }; // 表頭資料
        Object[][] data = { { "Kathy", "Smith", "Snoborading", new Integer(5), new Boolean(false) }, // 攔位資料
                { "John", "Doe", "Rowing", new Integer(3), new Boolean(true) },
                { "Sue", "Black", "Knitting", new Integer(2), new Boolean(false) },
                { "John", "White", "Speed reading", new Integer(20), new Boolean(true) },
                { "Joe", "Brown", "Pool", new Integer(10), new Boolean(false) },
                { "Cat", "Tommy", "Game", new Integer(25), new Boolean(false) }

        };

        JTable table = new JTable(new MyTableModle(data, column));
        table.setFillsViewportHeight(true);
        setUpSportColumn(table, table.getColumnModel().getColumn(2));

        JScrollPane scrollPane = new JScrollPane(table);

        setLayout(new GridLayout(1, 0));
        add(scrollPane);
    }

    public static void showGUI()
    {
        SetTableType type = new SetTableType();
        JFrame frm = new JFrame("SetTableType");
        frm.setContentPane(type);
        frm.setDefaultCloseOperation(frm.EXIT_ON_CLOSE);
        frm.setSize(800, 600);
        frm.setVisible(true);
    }

    public static void setUpSportColumn(JTable table, TableColumn col)
    {

        String[] item = { "Snowboarding", "Rowing", "Knitting", "Speed reading", "Pool", "None of the above" };
        JComboBox<String> comboBox = new JComboBox<>(item);
        col.setCellEditor(new DefaultCellEditor(comboBox));

        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setToolTipText("Click for combo box");
        col.setCellRenderer(renderer);

    }

    // 自己的tablemodle
    class MyTableModle extends AbstractTableModel
    {
        Object[][] data;
        String[] column;

        public MyTableModle(Object[][] data, String[] column)
        {
            this.data = data;
            this.column = column;
        }

        @Override
        public int getRowCount()
        {

            return data.length;
        }

        @Override
        public int getColumnCount()
        {

            return column.length;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex)
        {
            return data[rowIndex][columnIndex];
        }

        @Override
        public Class getColumnClass(int c)
        {
            return data[0][c].getClass();
        }

        @Override
        public boolean isCellEditable(int row, int col)
        {
            return true;
        }

        @Override
        public String getColumnName(int col)
        {
            return column[col];
        }

        @Override
        public void setValueAt(Object value, int row, int col)
        {
            data[row][col] = value;
            fireTableDataChanged();
        }

    }

}
