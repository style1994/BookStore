package test;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class SwingThreadTest
{

    public static void main(String[] args)
    {
        JFrame frm = new JFrame("EDT Test");
        frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton btn = new JButton("Update Data");
        JLabel lbl = new JLabel("狀態");
        frm.getContentPane().add(lbl, BorderLayout.NORTH);
        frm.getContentPane().add(btn, BorderLayout.SOUTH);
        frm.setSize(800, 600);
        frm.setVisible(true);

        btn.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e)
            {
                new Thread(() ->
                {

                    try
                    {
                        SwingUtilities.invokeLater(() -> lbl.setText("正在檢查資料"));
                        Thread.sleep(3000);
                        SwingUtilities.invokeLater(() -> lbl.setText("正在提交資料"));
                        Thread.sleep(3000);
                        SwingUtilities.invokeLater(() -> lbl.setText("提交成功"));

                    } catch (InterruptedException e2)
                    {
                        // TODO: handle exception
                    }

                }).start();

            }

        });

    }

}
