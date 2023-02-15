package com.amardzeloipia.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class registerUser extends JFrame {
    private JTextField txtBoxUser;
    private JPasswordField txtBoxPas;
    private JTextField txtBoxID;
    private JButton registerUser;
    private JLabel lbl1;
    private JLabel lbl2;
    private JLabel lbl3;
    private JPanel mainPanel;
    private JButton txtBoxBack;


    public registerUser() {
        add(mainPanel);
        registerUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = txtBoxID.getText();
                String username = txtBoxUser.getText();
                String password = String.valueOf(txtBoxPas.getPassword());

                String Database = "jdbc:mysql://localhost/ManagerDB";
                String Username = "root";
                String Password = "";

                try
                {
                    Connection conn = DriverManager.getConnection(Database,Username,Password);

                    Statement statement = conn.createStatement();

                    String Querry = "INSERT INTO users VALUES ( ? , ? , ? )";
                    PreparedStatement preparedStatement = conn.prepareStatement(Querry);
                    preparedStatement.setString(1, id);
                    preparedStatement.setString(2, username);
                    preparedStatement.setString(3, password);

                    int insert = preparedStatement.executeUpdate();

                    if(insert>0)
                    {
                        JOptionPane.showMessageDialog(null, "Registration done!");
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null, "Failed");
                    }




                }
                catch (Exception ex)
                {

                }


            }
        });
        txtBoxBack.addActionListener(new ActionListener() {
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
        JFrame frame = new JFrame("registerUser");
        frame.setContentPane(new registerUser().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setMaximumSize(new Dimension(250,350));
        frame.setMinimumSize(new Dimension(250,350));
        frame.pack();
        frame.setVisible(true);
    }
}
