package com.amardzeloipia.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class LoginForm extends JFrame {
    private JPanel mainPanel;
    private JTextField txtBoxUsername;
    private JPasswordField txtBoxPassword;
    private JButton btnLogin;
    private JLabel lblPic;
    private JLabel lblUser;
    private JLabel lblPass;
    private JPanel btnPanel;
    private JButton btnRegister;
    private JButton quitButton;

    public LoginForm() {

        add(mainPanel);
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String username = txtBoxUsername.getText();
                String password = String.valueOf(txtBoxPassword.getPassword());

                String Database = "jdbc:mysql://localhost/ManagerDB";
                String Username = "root";
                String Password = "";

                try
                {
                    Connection conn = DriverManager.getConnection(Database,Username,Password);

                    Statement statement = conn.createStatement();

                    String Querry = "SELECT * FROM manager WHERE username = ? AND manager_password = ?";
                    PreparedStatement preparedStatement = conn.prepareStatement(Querry);
                    preparedStatement.setString(1, username);
                    preparedStatement.setString(2, password);

                    String Querry2 = "SELECT * FROM users WHERE user_name = ? and user_password = ?";
                    PreparedStatement preparedStatement2 = conn.prepareStatement(Querry2);
                    preparedStatement2.setString(1, username);
                    preparedStatement2.setString(2, password);

                    ResultSet resultSet = preparedStatement.executeQuery();
                    ResultSet resultSet2 = preparedStatement2.executeQuery();

                    if(resultSet.next())
                    {
                       managerForm openForm = new managerForm();
                       openForm.setMinimumSize(new Dimension(250, 350));
                       openForm.setMaximumSize(new Dimension(250, 350));
                       openForm.setLocationRelativeTo(null);
                       openForm.setVisible(true);
                       dispose();
                    }
                    else if(resultSet2.next())
                    {
                        userForm openForm = new userForm();
                        openForm.setMinimumSize(new Dimension(250, 350));
                        openForm.setMaximumSize(new Dimension(250, 350));
                        openForm.setLocationRelativeTo(null);
                        openForm.setVisible(true);
                        dispose();

                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null, "Invalid login");
                    }

                }
                catch (Exception ex)
                {
                    JOptionPane.showMessageDialog(null, "Error");

                }

            }
        });
        btnRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                selectRegister openForm = new selectRegister();
                openForm.setMinimumSize(new Dimension(400, 200));
                openForm.setMaximumSize(new Dimension(400, 200));
                openForm.setLocationRelativeTo(null);
                openForm.setVisible(true);
                dispose();


            }
        });

    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("LoginForm");
        frame.setContentPane(new LoginForm().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setMaximumSize(new Dimension(250,450));
        frame.setMinimumSize(new Dimension(250,450));
        frame.pack();
        frame.setVisible(true);
    }
}
