package LabCode.test;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DatabaseLoginSwingApp
{
    private JFrame frame;
    private JButton adminButton;
    private JButton userButton;

    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(() ->
        {
            try
            {
                DatabaseLoginSwingApp app = new DatabaseLoginSwingApp();
                app.frame.setVisible(true);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        });
    }

    public DatabaseLoginSwingApp()
    {
        initialize();
    }

    private void initialize()
    {
        frame = new JFrame("数据库管理信息系统");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);

        Container contentPane = frame.getContentPane();
        contentPane.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        JLabel welcomeLabel = new JLabel("欢迎使用数据库管理信息系统");
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        c.insets = new Insets(10, 0, 10, 0);
        contentPane.add(welcomeLabel, c);

        adminButton = new JButton("管理员登录");
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 1;
        c.insets = new Insets(0, 0, 0, 10);
        contentPane.add(adminButton, c);
        adminButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                // 进行管理员登录操作
            }
        });

        userButton = new JButton("普通用户登录");
        c.gridx = 1;
        c.gridy = 1;
        c.gridwidth = 1;
        c.insets = new Insets(0, 10, 0, 0);
        contentPane.add(userButton, c);
        userButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                // 进行普通用户登录操作
            }
        });
    }
}
