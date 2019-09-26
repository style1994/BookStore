package test;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JSplitPane;

public class JSplitPaneTest
{
    public static void main(String[] args)
    {
        JFrame frm = new JFrame("JSplitePane Test");
        frm.setSize(800, 600);
        frm.setLocationRelativeTo(null);
        frm.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        JButton btnLeft = new JButton("Left Button");
        JButton btnRight = new JButton("Right Button");
        JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, btnLeft, btnRight);
        split.setDividerLocation(400);
        split.setDividerSize(5);

        frm.getContentPane().add(split);
        frm.setVisible(true);

        System.out.println("DividerLocation: " + split.getDividerLocation());
        System.out.println("DividerSize: " + split.getDividerSize());
        System.out.println("MaximumDividerLocation: " + split.getMaximumDividerLocation());
        System.out.println("MinimumDividerLocation: " + split.getMinimumDividerLocation());
        System.out.println("Orientation: " + split.getOrientation());
        System.out.println("isContinuousLayout: " + split.isContinuousLayout());
        System.out.println("isOneTouchExpandable: " + split.isOneTouchExpandable());

        frm.addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e)
            {
                int i = JOptionPane.showConfirmDialog(frm, "確定要結束程式嗎?", "確認訊息", JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE);

                if (i == JOptionPane.YES_OPTION)
                {
                    System.exit(0);
                } else
                {
                    System.out.println("hello");
                }
            }
        });

    }
}
