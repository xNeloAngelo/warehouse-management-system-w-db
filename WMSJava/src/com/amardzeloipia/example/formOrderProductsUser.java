package com.amardzeloipia.example;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class formOrderProductsUser extends JFrame{
    private JPanel mainPanel;
    private JTextField txtProdID;
    private JTextField txtProdName;
    private JTextField txtProdQuant;
    private JTextField txtWorkerID;
    private JButton btnOrder;
    private JButton btnBack;
    private JTable listTable;
    private JLabel we;
    private JLabel wew;
    private JLabel wewe;
    private JLabel wewewe;
    private JTextField txtWorkerName;
    private JLabel wewewewe;

    void inputData()
    {
        String Database = "jdbc:mysql://localhost/ManagerDB";
        String Username = "root";
        String Password = "";

        try
        {
            Connection conn = DriverManager.getConnection(Database,Username,Password);
            String query = "SELECT * FROM products";
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            ResultSetMetaData resultSetMetaData =resultSet.getMetaData();

            DefaultTableModel defaultTableModel = (DefaultTableModel) listTable.getModel();

            int columns = resultSetMetaData.getColumnCount();

            String[] columName= new String[columns];

            for(int i=0;i<columns;i++)
            {
                columName[i]=resultSetMetaData.getCatalogName(i+1);
            }
            defaultTableModel.setColumnIdentifiers(columName);
            String productID,ProductName,ProductType,ProductPrice;
            while(resultSet.next())
            {
                productID=resultSet.getString(1);
                ProductName=resultSet.getString(2);
                ProductType=resultSet.getString(3);
                ProductPrice=resultSet.getString(4);

                String[] row={productID,ProductName,ProductType,ProductPrice};
                defaultTableModel.addRow(row);
            }
            statement.close();
            conn.close();
        }
        catch (Exception exception)
        {

        }
    }
    public formOrderProductsUser() {
        inputData();
        add(mainPanel);
        btnOrder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String Database = "jdbc:mysql://localhost/ManagerDB";
                String Username = "root";
                String Password = "";

                String prodID = txtProdID.getText();
                String prodName = txtProdName.getText();
                String quantity = txtProdQuant.getText();
                String workerID = txtWorkerID.getText();
                String workerName = txtWorkerName.getText();

                try
                {
                    Connection conn = DriverManager.getConnection(Database,Username,Password);

                    String Querry = "INSERT INTO productorders VALUES (?, ? ,? ,? ,?)";
                    PreparedStatement preparedStatement = conn.prepareStatement(Querry);
                    preparedStatement.setString(1,prodID);
                    preparedStatement.setString(2,prodName);
                    preparedStatement.setString(3,quantity);
                    preparedStatement.setString(4,workerID);
                    preparedStatement.setString(5,workerName);

                    int insert = preparedStatement.executeUpdate();

                    if(insert > 0)
                    {
                        JOptionPane.showMessageDialog(null, "Order was placed!");
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null, "Order failed check text fields all must be filed");
                    }
                }
                catch (Exception exception)
                {
                    JOptionPane.showMessageDialog(null, "error : " + exception);
                }
            }


        });
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
        JFrame frame = new JFrame("formOrderProductsUser");
        frame.setContentPane(new formOrderProductsUser().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(600, 700));
        frame.setMaximumSize(new Dimension(600, 700));
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setVisible(true);
    }
}
