package com.project.missingperson.ui;

import com.project.missingperson.database.feedback.Feedback;
import com.project.missingperson.database.model.MissedPerson;
import com.project.missingperson.service.FeedbackService;
import com.project.missingperson.service.MissingPersonService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ReadFeedbackForm extends JFrame{

    private static MissedPerson missedPerson;
    private JTable table1;
    private TableModel tableModel;
    private JPanel panel1;
    private JLabel missedPersonId;
    private JButton personIsFoundButton;
    private JTextArea textArea1;
    private String[] titles = {"id", "Full Name", "Creation Date"};
    private static String[][] feedbacks;

    public ReadFeedbackForm(final MissedPerson missedPerson) {

        this.missedPerson = missedPerson;
        feedbacks = new String[FeedbackService.getByMissedPersonId(missedPerson.getId()).size()][];
        tableModel = new DefaultTableModel(listFeedback(),titles);
        table1.setModel(tableModel);
        add(panel1);
        setVisible(true);
        setResizable(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("Read Feedback");
        setSize(480,580);


        this.missedPerson=missedPerson;
        missedPersonId.setText(missedPerson.getFirstName() + " " + missedPerson.getLastName());

        personIsFoundButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                missedPerson.setIsFound(true);
                MissingPersonService.updateMissedPerson(missedPerson);
                JOptionPane.showMessageDialog(null,"Case closed.");
                setVisible(false);
            }
        });
        table1.addMouseListener(new MouseAdapter() {
            /**
             * {@inheritDoc}
             *
             * @param e
             */
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                textArea1.setText(FeedbackService.getByMissedPersonId(missedPerson.getId()).get(table1.getSelectedRow()).getContent());
            }
        });
    }

    public static String[][] listFeedback(){
        for (int i = 0; i < FeedbackService.getByMissedPersonId(missedPerson.getId()).size(); i++) {

            feedbacks[i] = new String[]{
                    String.valueOf(FeedbackService.getByMissedPersonId(missedPerson.getId()).get(i).getMissedPersonId()),
                    missedPerson.getFirstName() + " " + missedPerson.getLastName(),
                    String.valueOf(FeedbackService.getByMissedPersonId(missedPerson.getId()).get(i).getIssuedDate())
            };

        }
        return feedbacks;
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
