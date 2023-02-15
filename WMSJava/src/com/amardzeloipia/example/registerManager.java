package com.amardzeloipia.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class registerManager extends JFrame {
    private JPanel mainPanel;
    private JTextField txtRoot;
    private JPasswordField txtRPass;
    private JTextField txtBoxID;
    private JTextField txtBoxUser;
    private JPasswordField txtBoxPass;
    private JButton Register;
    private JLabel lbl1;
    private JLabel lbl2;
    private JLabel lbl3;
    private JLabel lbl4;
    private JLabel lbl5;
    private JButton btnBack;

    public registerManager() {
        add(mainPanel);
        Register.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String admin = txtRoot.getText();
                String aPassword = String.valueOf(txtRPass.getPassword());

                if(admin.equals("admin")&&aPassword.equals("admin"))
                {


                    String id = txtBoxID.getText();
                    String username = txtBoxUser.getText();
                    String password = String.valueOf(txtBoxPass.getPassword());

                    String Database = "jdbc:mysql://localhost/ManagerDB";
                    String Username = "root";
                    String Password = "";


                    try
                    {
                        Connection conn = DriverManager.getConnection(Database,Username,Password);

                        Statement statement = conn.createStatement();

                        String Querry = "INSERT INTO manager VALUES ( ? , ? , ? )";
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
                        conn.close();
                        statement.close();

                    }
                    catch (Exception ex)
                    {
                        JOptionPane.showMessageDialog(null, "Error");
                    }

                }
                else
                {
                    JOptionPane.showMessageDialog(null,"Failed admin auth.");
                }


            }
        });
        btnBack.addActionListener(new ActionListener() {
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
        JFrame frame = new JFrame("registerManager");
        frame.setContentPane(new registerManager().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
