package com.amardzeloipia.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class selectRegister extends JFrame {
    private JPanel mainPanel;
    private JButton btnUser;
    private JButton btnManager;
    private JButton txtBack;

    public selectRegister() {
        add(mainPanel);
        btnUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerUser openForm = new registerUser();
                openForm.setMinimumSize(new Dimension(250, 350));
                openForm.setMaximumSize(new Dimension(250, 350));
                openForm.setLocationRelativeTo(null);
                openForm.setVisible(true);
                dispose();
            }
        });
        btnManager.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerManager openForm = new registerManager();
                openForm.setMinimumSize(new Dimension(250, 350));
                openForm.setMaximumSize(new Dimension(250, 350));
                openForm.setLocationRelativeTo(null);
                openForm.setVisible(true);
                dispose();

            }
        });
        txtBack.addActionListener(new ActionListener() {
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
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("selectRegister");
        frame.setContentPane(new selectRegister().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setMaximumSize(new Dimension(250,350));
        frame.setMinimumSize(new Dimension(250,350));
        frame.pack();
        frame.setVisible(true);
    }
}
