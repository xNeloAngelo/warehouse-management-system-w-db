package com.amardzeloipia.example;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class formHistory extends  JFrame{
    private JButton Back;
    private JTable table1;
    private JPanel mainPanel;
    private JScrollPane scrol;

    public static void main(String[] args) {
        JFrame frame = new JFrame("formHistory");
        frame.setContentPane(new formHistory().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
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
    public formHistory() {
        add(mainPanel);
        inputData();
    Back.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            managerForm openForm = new managerForm();
            openForm.setMinimumSize(new Dimension(250, 350));
            openForm.setMaximumSize(new Dimension(250, 350));
            openForm.setLocationRelativeTo(null);
            openForm.setVisible(true);
            dispose();
        }
    });
}
}
