package com.project.missingperson.ui;

import com.project.missingperson.database.DatabaseService;
import com.project.missingperson.database.model.MissedPerson;
import com.project.missingperson.database.model.User;
import com.project.missingperson.database.model.enums.Gender;
import com.project.missingperson.database.model.enums.Role;
import com.project.missingperson.service.FeedbackService;
import com.project.missingperson.service.MissingPersonService;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class DashBoardForm extends JFrame{

    private static User user;
    private JPanel panel1;
    private JButton searchButton;
    private JTable table1;
    private JTextField searchTextField;
    private JTextArea historyTextArea;
    private JTextField nameTextField;
    private JButton defineMissingPersonButton;
    private JButton removeMissingPersonButton;
    private JButton writeFeedbackButton;
    private JButton refreshButton;
    private JButton updateMissingPersonButton;
    private JButton readFeedbacksButton;
    private JLabel userLabel;
    private JButton verifyUserButton;
    private TableModel tableModel;
    private static String[] titles = {"ID","Name","Surname","Sex","Phone No","Eye Color","Hair Color","Nationality","History","Last Seen"};

    public DashBoardForm(final User user) {

        this.user = user;
        tableModel = new DefaultTableModel(listMissedPeople(),titles);
        table1.setModel(tableModel);
        add(panel1);
        setSize(840,520);
        setResizable(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Missing Person System");
        setVisible(true);
        setLocationRelativeTo(null);
        userLabel.setText(user.getFirstName() + " " + user.getLastName());

        searchButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                TableModel searchTable = new DefaultTableModel(searchMissedPeople(searchTextField.getText()),titles);
                table1.setModel(searchTable);
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
                String history = table1.getValueAt(table1.getSelectedRow(),8).toString();
                String name = table1.getValueAt(table1.getSelectedRow(),1).toString() + " " + table1.getValueAt(table1.getSelectedRow(),2);
                nameTextField.setText(name);
                historyTextArea.setText(history);
            }
        });
        defineMissingPersonButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                if(user.getVerified()) {
                    new DefineMissingPersonForm(user).setVisible(true);
                }else{
                    JOptionPane.showMessageDialog(null,"you are net able to define a missing person.");
                }
            }
        });
        removeMissingPersonButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        writeFeedbackButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                MissedPerson person;
                int id = Integer.parseInt((String) table1.getValueAt(table1.getSelectedRow(),0));
                person = DatabaseService.getMissedPersonDao().getById(id);
                new WriteFeedbackForm(user, person).setVisible(true);
            }
        });
        refreshButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                tableModel = new DefaultTableModel(listMissedPeople(),titles);
                table1.setModel(tableModel);
            }
        });
        readFeedbacksButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                MissedPerson person;
                int id = Integer.parseInt((String) table1.getValueAt(table1.getSelectedRow(),0));
                person = DatabaseService.getMissedPersonDao().getById(id);
                new ReadFeedbackForm(person).setVisible(true);
            }
        });
        updateMissingPersonButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                MissedPerson person;
                int id = Integer.parseInt((String) table1.getValueAt(table1.getSelectedRow(),0));
                person = DatabaseService.getMissedPersonDao().getById(id);
                new UpdateMissingPerson(person).setVisible(true);
            }
        });
        verifyUserButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                new VerifyUserForm().setVisible(true);
            }
        });
    }

    public static String[][] listMissedPeople(){
        String[][] missedPeople = new String[MissingPersonService.viewMissingPersons().size()][];
        for(int i=0;i<MissingPersonService.viewMissingPersons().size();i++){
            missedPeople[i] = new String[]{
                    String.valueOf(MissingPersonService.viewMissingPersons().get(i).getId()),
                    MissingPersonService.viewMissingPersons().get(i).getFirstName(),
                    MissingPersonService.viewMissingPersons().get(i).getLastName(),
                    String.valueOf(MissingPersonService.viewMissingPersons().get(i).getGender()),
                    MissingPersonService.viewMissingPersons().get(i).getPhoneNumber(),
                    MissingPersonService.viewMissingPersons().get(i).getEyeColor(),
                    MissingPersonService.viewMissingPersons().get(i).getHairColor(),
                    String.valueOf(MissingPersonService.viewMissingPersons().get(i).getNationality()),
                    MissingPersonService.viewMissingPersons().get(i).getHistory(),
                    String.valueOf(MissingPersonService.viewMissingPersons().get(i).getLastSeen())

            };
        }
        return missedPeople;
    }

    public static String[][] searchMissedPeople(String searchText){
        String[][] missedPeople = new String[MissingPersonService.searchByName(searchText).size()][];
        for (int i = 0; i < MissingPersonService.searchByName(searchText).size(); i++) {
            missedPeople[i] = new String[]{
                    String.valueOf(MissingPersonService.searchByName(searchText).get(i).getId()),
                    MissingPersonService.searchByName(searchText).get(i).getFirstName(),
                    MissingPersonService.searchByName(searchText).get(i).getLastName(),
                    String.valueOf(MissingPersonService.searchByName(searchText).get(i).getGender()),
                    MissingPersonService.searchByName(searchText).get(i).getPhoneNumber(),
                    MissingPersonService.searchByName(searchText).get(i).getEyeColor(),
                    MissingPersonService.searchByName(searchText).get(i).getHairColor(),
                    String.valueOf(MissingPersonService.searchByName(searchText).get(i).getNationality()),
                    MissingPersonService.searchByName(searchText).get(i).getHistory(),
                    String.valueOf(MissingPersonService.searchByName(searchText).get(i).getLastSeen())
            };
        }
        return missedPeople;
    }



    private void createUIComponents() {
        // TODO: place custom component creation code here
        historyTextArea = new JTextArea();
        historyTextArea.setEditable(false);
        removeMissingPersonButton = new JButton();
        defineMissingPersonButton = new JButton();
        writeFeedbackButton = new JButton();
        readFeedbacksButton = new JButton();
        verifyUserButton = new JButton();

        if(user.getRole()!=Role.ADMIN){
            verifyUserButton.setEnabled(false);
        }
        if(user.getRole()!=Role.ADMIN){
            readFeedbacksButton.setEnabled(false);
        }
        if(user.getRole()!=Role.USER){
            defineMissingPersonButton.setEnabled(false);
        }
        if(user.getRole()!=Role.ADMIN){
            removeMissingPersonButton.setEnabled(false);
        }
        if(user.getRole()!=Role.INFORMER){
            writeFeedbackButton.setEnabled(false);
        }

    }

}
