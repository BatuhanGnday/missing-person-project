package com.project.missingperson.ui;

import com.project.missingperson.database.model.enums.Gender;
import com.project.missingperson.database.model.enums.Role;
import com.project.missingperson.service.AccountService;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.ParseException;
import java.util.Calendar;

public class RegisterForm extends JFrame{
    private JPanel panel1;
    private JTextField nameTextField;
    private JTextField usernameTextField;
    private JPasswordField passwordPasswordField;
    private JComboBox genderComboBox;
    private JTextField surnameTextField;
    private JTextField emailTextField;
    private JPasswordField passwordAgainPasswordField;
    private JFormattedTextField birthDateFormattedTextField;
    private JButton registerButton;
    private JButton alreadyHaveAnAccountButton;
    private Gender[] genders = {Gender.MALE,Gender.FEMALE};

    public RegisterForm() {

        add(panel1);
        setSize(550,210);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Create an account");
        setVisible(true);
        setLocationRelativeTo(null);

        registerButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameTextField.getText();
                String surname = surnameTextField.getText();
                String username = usernameTextField.getText();
                String email = emailTextField.getText();
                String password = passwordPasswordField.getText();
                String password2 = passwordAgainPasswordField.getText();
                Gender gender = (Gender) genderComboBox.getSelectedItem();
                String birthDate = birthDateFormattedTextField.getText();

                if(isValid(name)&&isValid(surname)&&isValid(username)&&isValid(email)&&isValid(password)){
                    if(passwordMatch(passwordPasswordField,passwordAgainPasswordField) && AccountService.createAccount(email,username,password,name,surname,gender,Role.USER,birthDate)){
                        setVisible(false);
                        new LoginForm("Account Created").setVisible(true);
                        clearForm();
                    }else if(!passwordMatch(passwordPasswordField,passwordAgainPasswordField)){
                        System.out.println("doesnt match");
                        passwordAgainPasswordField.setForeground(Color.RED);
                        JOptionPane.showMessageDialog(null,"Passwords does not match");
                        passwordAgainPasswordField.setText("");
                        return;
                    }
                }else {
                    JOptionPane.showMessageDialog(null,"enter all fields correctly");
                }
            }
        });

    }

    private static Boolean passwordMatch(JPasswordField field1, JPasswordField field2){
        return field1.getText().equals(field2.getText());
    }

    private static boolean isValid(String string){
        return string.length()>0;
    }

    private void clearForm(){
        emailTextField.setText("");
        usernameTextField.setText("");
        passwordAgainPasswordField.setText("");
        passwordPasswordField.setText("");
        nameTextField.setText("");
        surnameTextField.setText("");
        genderComboBox.setSelectedItem(null);
    }

    private void createUIComponents() throws ParseException {
        // TODO: place custom component creation code here
        genderComboBox = new JComboBox(genders);
        MaskFormatter maskFormatter = new MaskFormatter("##-##-####");
        maskFormatter.setPlaceholderCharacter('_');
        birthDateFormattedTextField = new JFormattedTextField(maskFormatter);
    }
}
