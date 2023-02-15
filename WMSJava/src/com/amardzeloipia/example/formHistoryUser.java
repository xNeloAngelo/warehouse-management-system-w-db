package com.amardzeloipia.example;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class formHistoryUser extends JFrame{
    private JTable table1;
    private JPanel panel1;
    private JButton btnBack;
    void inputData()
    {
        String Database = "jdbc:mysql://localhost/ManagerDB";
        String Username = "root";
        String Password = "";

        try
        {
            Connection conn = DriverManager.getConnection(Database,Username,Password);
            String query = "SELECT * FROM productOrders";
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            ResultSetMetaData resultSetMetaData =resultSet.getMetaData();

            DefaultTableModel defaultTableModel = (DefaultTableModel) table1.getModel();

            int columns = resultSetMetaData.getColumnCount();

            String[] columName= new String[columns];

            for(int i=0;i<columns;i++)
            {
                columName[i]=resultSetMetaData.getCatalogName(i+1);
            }
            defaultTableModel.setColumnIdentifiers(columName);
            String productID,ProductName,ProductQuant,ManagerID,ManagerName;
            while(resultSet.next())
            {
                productID=resultSet.getString(1);
                ProductName=resultSet.getString(2);
                ProductQuant=resultSet.getString(3);
                ManagerID=resultSet.getString(4);
                ManagerName=resultSet.getString(5);

                String[] row={productID,ProductName,ProductQuant,ManagerID,ManagerName};
                defaultTableModel.addRow(row);
            }
            statement.close();
            conn.close();
        }
        catch (Exception exception)
        {

        }
    }
    public formHistoryUser() {
        inputData();
        add(panel1);
        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userForm openForm = new userForm();
                openForm.setMinimumSize(new Dimension(250, 350));
                openForm.setMaximumSize(new Dimension(250, 350));
                openForm.setLocationRelativeTo(null);
                openForm.setVisible(true);
                dispose();
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("formHistoryUser");
        frame.setContentPane(new formHistoryUser().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
