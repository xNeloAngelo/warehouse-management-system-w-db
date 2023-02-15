package com.amardzeloipia.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class userForm extends  JFrame{
    private JPanel mainPanel;
    private JButton btnHistory;
    private JButton btnLogout;
    private JButton btnExit;
    private JButton btnOrder;

    public userForm() {
        add(mainPanel);

        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        btnOrder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                formOrderProductsUser openForm = new formOrderProductsUser();
                openForm.setMinimumSize(new Dimension(600, 700));
                openForm.setMaximumSize(new Dimension(600, 700));
                openForm.setLocationRelativeTo(null);
                openForm.setVisible(true);
                dispose();
            }
        });
        btnHistory.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                formHistoryUser openForm = new formHistoryUser();
                openForm.setMinimumSize(new Dimension(250, 350));
                openForm.setMaximumSize(new Dimension(250, 350));
                openForm.setLocationRelativeTo(null);
                openForm.setVisible(true);
                dispose();
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
        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("userForm");
        frame.setContentPane(new userForm().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMaximumSize(new Dimension(250,350));
        frame.setMinimumSize(new Dimension(250,350));
        frame.pack();
        frame.setVisible(true);
    }
}
