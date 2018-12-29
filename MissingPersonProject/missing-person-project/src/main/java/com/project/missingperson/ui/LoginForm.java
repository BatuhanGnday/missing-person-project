package com.project.missingperson.ui;

import com.project.missingperson.service.AccountService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class LoginForm extends JFrame {

    private JPanel panel1;
    private JTextField usernameTextField;
    private JPasswordField passwordPasswordField;
    private JButton registerButton;
    private JButton loginButton;
    private JLabel jLabel;

    public LoginForm(String message){
        add(panel1);
        setTitle("Login");
        setSize(310,150);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        jLabel.setText(message);
        jLabel.setForeground(Color.blue);
        setLocationRelativeTo(null);

        registerButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new RegisterForm().setVisible(true);
            }
        });
        loginButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                if(AccountService.login(usernameTextField.getText(),passwordPasswordField.getText())!=null){
                    setVisible(false);
                }
            }
        });
        passwordPasswordField.addKeyListener(new KeyAdapter() {
            /**
             * Invoked when a key has been pressed.
             *
             * @param e
             */
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if(e.getKeyCode()==KeyEvent.VK_ENTER){
                    if(AccountService.login(usernameTextField.getText(),passwordPasswordField.getText())!=null){
                        setVisible(false);
                    }
                }
            }
        });

    }

    public LoginForm() {

        setTitle("Login");
        add(panel1);
        setSize(310,150);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);



        registerButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new RegisterForm().setVisible(true);
            }
        });
        loginButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                if(AccountService.login(usernameTextField.getText(),passwordPasswordField.getText())!=null){
                    setVisible(false);
                }
            }
        });
        passwordPasswordField.addKeyListener(new KeyAdapter() {
            /**
             * Invoked when a key has been pressed.
             *
             * @param e
             */
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if(e.getKeyCode()==KeyEvent.VK_ENTER){
                    if(AccountService.login(usernameTextField.getText(),passwordPasswordField.getText())!=null){
                        setVisible(false);
                    }
                }
            }
        });
    }
}
