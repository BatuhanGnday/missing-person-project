package com.project.missingperson.ui;

import com.project.missingperson.database.model.MissedPerson;
import com.project.missingperson.database.model.User;
import com.project.missingperson.service.FeedbackService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WriteFeedbackForm extends JFrame{

    private MissedPerson missedPerson;
    private User user;
    private JPanel panel1;
    private JTextField fullNameTextField;
    private JEditorPane editorPane1;
    private JButton submitButton;

    public WriteFeedbackForm(final User user, final MissedPerson missedPerson) {

        this.user = user;
        this.missedPerson = missedPerson;

        add(panel1);
        setResizable(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(510,250);
        setLocationRelativeTo(null);

        submitButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                FeedbackService.writeFeedback(missedPerson,editorPane1.getText());
            }
        });
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        fullNameTextField = new JTextField(missedPerson.getFirstName() + " " + missedPerson.getLastName());
    }
}
