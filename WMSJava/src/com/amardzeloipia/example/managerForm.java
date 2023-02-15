package com.amardzeloipia.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class managerForm extends JFrame {
    private JPanel mainPanel;
    private JButton btnProducts;
    private JButton btnOrder;
    private JButton btnHistory;
    private JButton btnExit;
    private JButton btnLogout;

    public managerForm() {
        add(mainPanel);

        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        btnLogout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginForm openForm = new LoginForm();
                openForm.setMinimumSize(new Dimension(250, 450));
                openForm.setMaximumSize(new Dimension(250, 450));
                openForm.setLocationRelativeTo(null);
                openForm.setVisible(true);
                dispose();

            }
        });
        btnProducts.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                formProducts openForm = new formProducts();
                openForm.setMinimumSize(new Dimension(600, 700));
                openForm.setMaximumSize(new Dimension(600, 700));
                openForm.setLocationRelativeTo(null);
                openForm.setVisible(true);
                dispose();

            }
        });
        btnOrder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                formOrderProducts openForm = new formOrderProducts();
                openForm.setMaximumSize(new Dimension(600,700));
                openForm.setMinimumSize(new Dimension(600,700));
                openForm.setLocationRelativeTo(null);
                openForm.setVisible(true);
                dispose();
            }
        });
        btnHistory.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                formHistory openForm = new formHistory();
                openForm.setMinimumSize(new Dimension(600, 700));
                openForm.setMaximumSize(new Dimension(600, 700));
                openForm.setLocationRelativeTo(null);
                openForm.setVisible(true);
                dispose();

            }
        });

    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("managerForm");
        frame.setContentPane(new managerForm().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setMaximumSize(new Dimension(250,350));
        frame.setMinimumSize(new Dimension(250,350));
        frame.pack();
        frame.setVisible(true);
    }
}
