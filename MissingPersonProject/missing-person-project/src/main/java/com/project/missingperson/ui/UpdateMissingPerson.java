package com.project.missingperson.ui;

import com.project.missingperson.database.model.MissedPerson;
import com.project.missingperson.database.model.enums.Gender;
import com.project.missingperson.database.model.enums.Nationality;
import com.project.missingperson.service.MissingPersonService;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

public class UpdateMissingPerson extends JFrame {

    private static MissedPerson person;
    private JPanel panel1;
    private JFormattedTextField GSMNoFormattedTextField;
    private JFormattedTextField lastSeenFormattedTextField;
    private JTextField nameTextField;
    private JTextField surnameTextField;
    private JTextField eyeColorTextField;
    private JTextField hairColorTextField;
    private JComboBox genderComboBox;
    private JComboBox nationalityComboBox;
    private JButton updateMissingPersonButton;
    private JEditorPane editorPane1;
    private Gender[] gender = {Gender.MALE,Gender.FEMALE};
    private Nationality[] nationality = {Nationality.AFRICAN,Nationality.AMERICAN,Nationality.ASIAN,Nationality.CAUCASIAN,Nationality.EUROPAN,Nationality.HISPANIC,Nationality.MIDDLE_EAST};

    public UpdateMissingPerson(final MissedPerson person) {

        add(panel1);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
        setSize(630,340);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("Update Missing Person");

        this.person = person;

        genderComboBox.setSelectedItem(person.getGender());
        nationalityComboBox.setSelectedItem(person.getNationality());
        nameTextField.setText(person.getFirstName());
        surnameTextField.setText(person.getLastName());
        GSMNoFormattedTextField.setText(person.getPhoneNumber());
        lastSeenFormattedTextField.setText(person.getLastSeen());
        eyeColorTextField.setText(person.getEyeColor());
        hairColorTextField.setText(person.getHairColor());
        editorPane1.setText(person.getHistory());

        updateMissingPersonButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {

                person.setFirstName(nameTextField.getText());
                person.setLastName(surnameTextField.getText());
                person.setPhoneNumber(GSMNoFormattedTextField.getText());
                person.setLastSeen(lastSeenFormattedTextField.getText());
                person.setEyeColor(eyeColorTextField.getText());
                person.setHairColor(hairColorTextField.getText());
                person.setGender((Gender)genderComboBox.getSelectedItem());
                person.setNationality((Nationality)nationalityComboBox.getSelectedItem());
                person.setHistory(editorPane1.getText());

                MissingPersonService.updateMissedPerson(person);

            }
        });
    }

    private void createUIComponents() throws ParseException {
        // TODO: place custom component creation code here
        genderComboBox = new JComboBox(gender);
        nationalityComboBox = new JComboBox(nationality);

        MaskFormatter gsmFormatter = new MaskFormatter("+## (###) ### ####");
        gsmFormatter.setPlaceholderCharacter('_');
        MaskFormatter dateFormatter = new MaskFormatter("##-##-####");
        dateFormatter.setPlaceholderCharacter('_');

        lastSeenFormattedTextField = new JFormattedTextField(dateFormatter);
        GSMNoFormattedTextField = new JFormattedTextField(gsmFormatter);



    }
}
