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

        GridBagConstraints constraints;
        int EAST = GridBagConstraints.EAST;

        JLabel ipLabel = new JLabel("ip:");
        constraints = MyGridBagLayout.getGridBagConstraints(0, 0, 1, 1, EAST);
        this.add(ipLabel, constraints);
        JTextField ipTextField = new JTextField(10);
        constraints = MyGridBagLayout.getGridBagConstraints(1, 0, 1, 1, EAST);
        this.add(ipTextField, constraints);

        JLabel portLabel = new JLabel("port:");
        constraints = MyGridBagLayout.getGridBagConstraints(0, 1, 1, 1, EAST);
        this.add(portLabel, constraints);
        JTextField portTextField = new JTextField(10);
        constraints = MyGridBagLayout.getGridBagConstraints(1, 1, 1, 1, EAST);
        this.add(portTextField, constraints);

        JLabel dbNameLabel = new JLabel("資料庫名稱:");
        constraints = MyGridBagLayout.getGridBagConstraints(0, 2, 1, 1, EAST);
        this.add(dbNameLabel, constraints);
        JTextField dbNameTextField = new JTextField(10);
        constraints = MyGridBagLayout.getGridBagConstraints(1, 2, 1, 1, EAST);
        this.add(dbNameTextField, constraints);

        JLabel userJLabel = new JLabel("資料庫帳號:");
        constraints = MyGridBagLayout.getGridBagConstraints(0, 3, 1, 1, EAST);
        this.add(userJLabel, constraints);
        JTextField userTextField = new JTextField(10);
        constraints = MyGridBagLayout.getGridBagConstraints(1, 3, 1, 1, EAST);
        this.add(userTextField, constraints);

        JLabel passwordLabel = new JLabel("資料庫密碼:");
        constraints = MyGridBagLayout.getGridBagConstraints(0, 4, 1, 1, EAST);
        this.add(passwordLabel, constraints);
        JTextField passwordTextField = new JTextField(10);
        constraints = MyGridBagLayout.getGridBagConstraints(1, 4, 1, 1, EAST);
        this.add(passwordTextField, constraints);

        JLabel resultLabel = new JLabel();
        constraints = MyGridBagLayout.getGridBagConstraints(0, 5, 0, 1, EAST);
        this.add(resultLabel, constraints);

        JButton okButton = new JButton("確認");
        constraints = MyGridBagLayout.getGridBagConstraints(0, 6, 1, 1, EAST);
        this.add(okButton, constraints);
        JButton clearButton = new JButton("清除");
        constraints = MyGridBagLayout.getGridBagConstraints(1, 6, 1, 1, GridBagConstraints.WEST);
        this.add(clearButton, constraints);

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
