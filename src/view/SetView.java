package view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SetView extends JPanel
{
    private JPanel panel = this;
    private JDialog dialog;

    public SetView(JDialog dialog)
    {

        setLayout(new GridBagLayout());
        this.dialog = dialog;

        int EAST = GridBagConstraints.EAST;

        // 排版
        JLabel ipLabel = new JLabel("ip:");
        MyGridBagLayout.getGridBagConstraints(this, ipLabel, 0, 0, 1, 1, EAST);
        JTextField ipTextField = new JTextField(10);
        MyGridBagLayout.getGridBagConstraints(this, ipTextField, 1, 0, 1, 1, EAST);

        JLabel portLabel = new JLabel("port:");
        MyGridBagLayout.getGridBagConstraints(this, portLabel, 0, 1, 1, 1, EAST);
        JTextField portTextField = new JTextField(10);
        MyGridBagLayout.getGridBagConstraints(this, portTextField, 1, 1, 1, 1, EAST);

        JLabel dbNameLabel = new JLabel("資料庫名稱:");
        MyGridBagLayout.getGridBagConstraints(this, dbNameLabel, 0, 2, 1, 1, EAST);
        JTextField dbNameTextField = new JTextField(10);
        MyGridBagLayout.getGridBagConstraints(this, dbNameTextField, 1, 2, 1, 1, EAST);

        JLabel userJLabel = new JLabel("資料庫帳號:");
        MyGridBagLayout.getGridBagConstraints(this, userJLabel, 0, 3, 1, 1, EAST);
        JTextField userTextField = new JTextField(10);
        MyGridBagLayout.getGridBagConstraints(this, userTextField, 1, 3, 1, 1, EAST);

        JLabel passwordLabel = new JLabel("資料庫密碼:");
        MyGridBagLayout.getGridBagConstraints(this, passwordLabel, 0, 4, 1, 1, EAST);
        JTextField passwordTextField = new JTextField(10);
        MyGridBagLayout.getGridBagConstraints(this, passwordTextField, 1, 4, 1, 1, EAST);

        JLabel resultLabel = new JLabel();
        MyGridBagLayout.getGridBagConstraints(this, resultLabel, 0, 5, 0, 1, EAST);
        JButton okButton = new JButton("確認");

        MyGridBagLayout.getGridBagConstraints(this, okButton, 0, 6, 1, 1, EAST);
        JButton clearButton = new JButton("清除");
        MyGridBagLayout.getGridBagConstraints(this, clearButton, 1, 6, 1, 1, GridBagConstraints.WEST);

        ArrayList<JTextField> textFields = new ArrayList<>();
        textFields.add(ipTextField);
        textFields.add(portTextField);
        textFields.add(dbNameTextField);
        textFields.add(userTextField);
        textFields.add(passwordTextField);

        String[] name = { "ip", "port", "database-name", "user", "password" };
        for (int i = 0; i < textFields.size(); i++)
        {
            textFields.get(i).setName(name[i]);
        }

        okButton.addActionListener((ActionEvent e) ->
        {

            int num = JOptionPane.showConfirmDialog(panel, "確認", "是否修改", JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE);

            if (num == JOptionPane.YES_OPTION)
            {

                try
                {
                    BufferedWriter bw = new BufferedWriter(new FileWriter("config.txt"));

                    for (JTextField textField : textFields)
                    {
                        bw.write(textField.getName() + ":" + textField.getText());
                        bw.newLine();
                    }

                    bw.close();

                } catch (IOException e1)
                {
                    System.out.println("寫入config發生錯誤");
                    e1.printStackTrace();
                }

                dialog.dispose();
            }

        });

        // 清除
        clearButton.addActionListener((ActionEvent e) ->
        {
            for (JTextField jTextField : textFields)
            {
                jTextField.setText("");
            }
        });
    }
}
