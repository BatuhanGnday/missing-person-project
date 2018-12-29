package com.project.missingperson.ui;

import com.project.missingperson.database.model.MissedPerson;
import com.project.missingperson.database.model.User;
import com.project.missingperson.database.model.enums.Gender;
import com.project.missingperson.database.model.enums.Nationality;
import com.project.missingperson.service.MissingPersonService;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

public class DefineMissingPersonForm extends JFrame{

    private static User user;
    private JTextField nameTextField;
    private JTextField surnameTextField;
    private JTextField eyeColorTextField;
    private JTextField hairColorTextField;
    private JComboBox genderComboBox;
    private JComboBox nationalityComboBox;
    private JEditorPane editorPane1;
    private JButton submitButton;
    private JPanel mainPanel;
    private JFormattedTextField formattedTextField1;
    private JFormattedTextField GSMNoFormattedTextField;
    private Gender[] genders = {Gender.MALE,Gender.FEMALE};
    private Nationality[] nationalities = {Nationality.AFRICAN,Nationality.AMERICAN,Nationality.ASIAN,Nationality.CAUCASIAN,Nationality.EUROPAN,Nationality.HISPANIC,Nationality.MIDDLE_EAST};

    public DefineMissingPersonForm(final User user) {

        this.user = user;
        add(mainPanel);
        setResizable(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setTitle("Define Missing Person");
        setSize(510,300);
        setResizable(false);
        setVisible(true);

        submitButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                String firstName = nameTextField.getText();
                String lastName = surnameTextField.getText();
                Gender gender = (Gender)genderComboBox.getSelectedItem();
                String phoneNumber = GSMNoFormattedTextField.getText();
                String eyeColor = eyeColorTextField.getText();
                String hairColor = hairColorTextField.getText();
                Nationality nationality = (Nationality)nationalityComboBox.getSelectedItem();
                String history = editorPane1.getText();
                String lastSeen = formattedTextField1.getText();

                if(MissingPersonService.defineMissingPerson(new MissedPerson(user.getId(), firstName,lastName,gender,phoneNumber,eyeColor,hairColor,nationality,history,lastSeen,false))){
                    JOptionPane.showMessageDialog(null,"Missed Person Added Successfully");
                    setVisible(false);
                }
            }
        });
    }

    private void createUIComponents() throws ParseException {
        // TODO: place custom component creation code here
        genderComboBox = new JComboBox(genders);
        nationalityComboBox = new JComboBox(nationalities);
        MaskFormatter dateFormatter = new MaskFormatter("##-##-####");
        dateFormatter.setPlaceholderCharacter('_');
        formattedTextField1 = new JFormattedTextField(dateFormatter);
        MaskFormatter phoneFormatter = new MaskFormatter("+## (###) ### ####");
        phoneFormatter.setPlaceholderCharacter('_');
        GSMNoFormattedTextField = new JFormattedTextField(phoneFormatter);

    }
}
