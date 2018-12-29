package com.project.missingperson.ui;

import com.project.missingperson.database.DatabaseService;
import com.project.missingperson.database.model.User;
import com.project.missingperson.database.model.enums.Role;
import com.project.missingperson.service.AccountService;
import com.project.missingperson.service.MissingPersonService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VerifyUserForm extends JFrame {
    private JPanel panel1;
    private JTable table1;
    private JButton verifyUserButton;
    private JComboBox comboBox1;
    private TableModel tableModel;
    private Role[] roles = {Role.ADMIN,Role.USER,Role.INFORMER};
    private static String[] titles = {"id", "Full Name", "Creation Date"};

    public VerifyUserForm() {

        tableModel = new DefaultTableModel(listUser(),titles);
        table1.setModel(tableModel);
        add(panel1);
        setResizable(true);
        setSize(460,400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("Verify User");
        setVisible(true);

        verifyUserButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                User user;
                int id = Integer.parseInt((String) table1.getValueAt(table1.getSelectedRow(),0));
                user = DatabaseService.getUserDao().getById(id);
                user.setRole((Role)comboBox1.getSelectedItem());
                user.setVerified(true);
                AccountService.updateUser(user);
                JOptionPane.showMessageDialog(null,"user is verified now");
                tableModel = new DefaultTableModel(listUser(),titles);
                table1.setModel(tableModel);
            }
        });
    }

    private static String[][] listUser(){
        String[][] users = new String[AccountService.getUnverifiedUsers().size()][];
        for (int i = 0; i < AccountService.getUnverifiedUsers().size(); i++) {
            users[i] = new String[]{
                    String.valueOf(AccountService.getUnverifiedUsers().get(i).getId()),
                    AccountService.getUnverifiedUsers().get(i).getFirstName() + " " + AccountService.getUnverifiedUsers().get(i).getLastName(),
                    String.valueOf(AccountService.getUnverifiedUsers().get(i).getCreationDate())
            };
        }
        return users;
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        comboBox1 = new JComboBox(roles);
    }
}
