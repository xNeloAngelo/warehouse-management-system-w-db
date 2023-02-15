package com.amardzeloipia.example;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.*;

public class formProducts extends  JFrame{
    private JPanel mainPanel;
    private JButton btnAddProduct;
    private JTextField txtPrice;
    private JTextField txtType;
    private JTextField txtName;
    private JTextField txtID;
    private JLabel lblPID;
    private JLabel lblprice;
    private JLabel lbltype;
    private JLabel lblname;
    private JButton btnAddImage;
    private JButton btnBack;
    private JLabel lblPicture;
    private JTextField txtSearch;
    private JButton btnSearch;
    private JLabel lblSearch;
    private JTextField txtBoxDelete;
    private JButton btnDelete;
    private JLabel lblDelete;
    private JButton btnUpdate;

    private String imgLocationString;

    private ImageIcon ResizeImage(String path)
    {
        int width = 200;
        int height = 200;

        lblPicture.setSize(width,height);

        ImageIcon imageIcon = new ImageIcon(path);
        Image image = imageIcon.getImage();
        Image newImage = image.getScaledInstance(lblPicture.getWidth(),lblPicture.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon updateImage = new ImageIcon(newImage);
        return updateImage;
    }
    private ImageIcon SetSearchImage(String path, byte[] imageByte)
    {
        int width = 200;
        int height = 200;

        lblPicture.setSize(width,height);
        ImageIcon imageIcon = null;

        if(path != null)
        {
            imageIcon = new ImageIcon(path);
        }
        else
        {
            imageIcon = new ImageIcon(imageByte);
        }


        Image image = imageIcon.getImage();
        Image newImage = image.getScaledInstance(lblPicture.getWidth(),lblPicture.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon updateImage = new ImageIcon(newImage);
        return updateImage;

    }
    public formProducts() {
        add(mainPanel);

        btnBack.addActionListener(new ActionListener() {
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
        btnAddProduct.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String Database = "jdbc:mysql://localhost/ManagerDB";
                String Username = "root";
                String Password = "";

                String id = txtID.getText();
                String name = txtName.getText();
                String type = txtType.getText();
                String price = txtPrice.getText();

                try {
                    Connection conn = DriverManager.getConnection(Database,Username,Password);
                    Statement statement = conn.createStatement();

                    String querry = "INSERT INTO products VALUES (?,?,?,?,?)";
                    PreparedStatement preparedStatement = conn.prepareStatement(querry);
                    preparedStatement.setString(1, id);
                    preparedStatement.setString(2, name);
                    preparedStatement.setString(3, type);
                    preparedStatement.setString(4, price);
                    try
                    {
                        InputStream inputStream = new FileInputStream(new File(imgLocationString));
                        preparedStatement.setBlob(5,inputStream);
                    }
                    catch (Exception exception)
                    {
                        JOptionPane.showMessageDialog(null,"Error"+exception);
                    }
                    int insert = preparedStatement.executeUpdate();

                    if(insert>0)
                    {
                        JOptionPane.showMessageDialog(null, "Product added!");
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null, "Failed");
                    }


                }
                catch (Exception exception)
                {
                    JOptionPane.showMessageDialog(null,"Error"+exception);
                }

            }
        });
        btnAddImage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser choseFile = new JFileChooser();
                FileNameExtensionFilter filterFile = new FileNameExtensionFilter("*.IMAGE","jpg","png","gif");
                choseFile.addChoosableFileFilter(filterFile);
                int result = choseFile.showSaveDialog(null);
                if(result == JFileChooser.APPROVE_OPTION)
                {
                    File image = choseFile.getSelectedFile();
                    String imageLocation = image.getAbsolutePath();
                    try
                    {
                        lblPicture.setIcon(ResizeImage(imageLocation));
                        imgLocationString = imageLocation;

                    }
                    catch (Exception ex)
                    {
                        JOptionPane.showMessageDialog(null,"error");
                    }
                }
            }
        });
        btnSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String Database = "jdbc:mysql://localhost/ManagerDB";
                String Username = "root";
                String Password = "";

                String idOrName = txtSearch.getText();

                try
                {
                    Connection conn = DriverManager.getConnection(Database,Username,Password);
                    String Querry = "SELECT * FROM products WHERE product_id = ? OR product_name = ?";
                    PreparedStatement preparedStatement = conn.prepareStatement(Querry);

                    preparedStatement.setString(1,idOrName);
                    preparedStatement.setString(2,idOrName);
                    ResultSet resultSet = preparedStatement.executeQuery();

                    if (resultSet.next())
                    {
                        lblPicture.setIcon(SetSearchImage(null,resultSet.getBytes("product_image")));
                        txtID.setText(resultSet.getString("product_id"));
                        txtName.setText(resultSet.getString("product_name"));
                        txtType.setText(resultSet.getString("product_type"));
                        txtPrice.setText(resultSet.getString("product_price"));
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null, "Error not found");
                    }
                }
                catch (Exception exception)
                {
                    JOptionPane.showMessageDialog(null, "error" + exception);

                }
            }
        });
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String Database = "jdbc:mysql://localhost/ManagerDB";
                String Username = "root";
                String Password = "";

                String idOrName = txtBoxDelete.getText();
                try
                {
                    Connection conn=DriverManager.getConnection(Database,Username,Password);

                    String Querry = "DELETE FROM products WHERE product_id = ? OR product_name = ?";
                    PreparedStatement preparedStatement = conn.prepareStatement(Querry);

                    preparedStatement.setString(1, idOrName);
                    preparedStatement.setString(2, idOrName);

                    int resultSet = preparedStatement.executeUpdate();

                    if(resultSet>0)
                    {
                        JOptionPane.showMessageDialog(null, "Product removed");
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "Error, product not removed or found");
                    }

                }
                catch (Exception exception)
                {
                    JOptionPane.showMessageDialog(null, "error "+exception);
                }
            }
        });
        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String Database = "jdbc:mysql://localhost/ManagerDB";
                String Username = "root";
                String Password = "";
                String id = txtID.getText();
                String name = txtName.getText();
                String type = txtType.getText();
                String price = txtPrice.getText();

                try {
                    Connection conn = DriverManager.getConnection(Database,Username,Password);
                    Statement statement = conn.createStatement();

                    String querry = "UPDATE products SET product_name=?, product_type=?,product_price=?,product_image=? WHERE product_id = ?";
                    PreparedStatement preparedStatement = conn.prepareStatement(querry);
                    preparedStatement.setString(5, id);
                    preparedStatement.setString(1, name);
                    preparedStatement.setString(2, type);
                    preparedStatement.setString(3, price);
                    try
                    {
                        InputStream inputStream = new FileInputStream(new File(imgLocationString));
                        preparedStatement.setBlob(4,inputStream);
                    }
                    catch (Exception exception)
                    {
                        JOptionPane.showMessageDialog(null,"Error"+exception);
                    }
                    int insert = preparedStatement.executeUpdate();

                    if(insert>0)
                    {
                        JOptionPane.showMessageDialog(null, "Product updated!");
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null, "Failed");
                    }


                }
                catch (Exception exception)
                {
                    JOptionPane.showMessageDialog(null,"Error"+exception);
                }
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("formProducts");
        frame.setContentPane(new formProducts().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(600, 700));
        frame.setMaximumSize(new Dimension(600, 700));
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setVisible(true);
    }



}
