package com.project.missingperson.service;

import com.project.missingperson.database.DatabaseService;
import com.project.missingperson.database.model.User;
import com.project.missingperson.database.model.enums.Gender;
import com.project.missingperson.database.model.enums.Role;
import com.project.missingperson.ui.DashBoardForm;

import javax.swing.*;
import java.sql.Date;
import java.util.List;

public class AccountService {

    public static User login(String username, String password){

        User user = DatabaseService.getUserDao().getByUsername(username);
        if(user==null || !user.getPassword().equals(password)){
            if(user==null){
                System.out.println("username cannot found");
                JOptionPane.showMessageDialog(null,"username cannot found");
            }else if(!user.getPassword().equals(password)){
                System.out.println("wrong password");
                JOptionPane.showMessageDialog(null,"wrong password");
            }
            System.out.println("error");
            return null;
        }
        System.out.println("success");
        new DashBoardForm(user).setVisible(true);
        return user;
    }

    public static void changePassword(String email, String newPassword){
        User user = DatabaseService.getUserDao().getByEmail(email);
        DatabaseService.getUserDao().changePassword(user,newPassword);
    }

    public static boolean createAccount(String email, String username, String password, String firstName, String lastName, Gender gender, Role role, String birthday){
        if(!emailValid(email)){
            System.out.println("email invalid");
            JOptionPane.showMessageDialog(null,"Invalid email");
            return false;
        }
        if(!usernameValid(username)){
            System.out.println("username invalid");
            JOptionPane.showMessageDialog(null,"Invalid Username");
            return false;
        }
        int id = DatabaseService.getUserDao().insert(new User(email, username, password, firstName, lastName, gender, role, birthday, false));
        return true;
    }


    public static void logout(){
        //TODO close dashboard
    }

    public static boolean emailValid(String email){
        User user = DatabaseService.getUserDao().getByEmail(email);

        if(user!=null){
            return false;
        }else{
            return true;
        }
    }

    public static List<User> getUnverifiedUsers(){
        return DatabaseService.getUserDao().getUnverifiedUsers();
    }

    public static boolean usernameValid(String username){
        User tempUser = DatabaseService.getUserDao().getByUsername(username);
        if(tempUser!=null){
            return false;
        }else{
            return true;
        }
    }

    public static void updateUser(User user){
        DatabaseService.getUserDao().updateUser(user);
    }

    public static void verifyUser(User user){

        //TODO add feature: works if current user is admin
        DatabaseService.getUserDao().verifyUser(user);
    }
}
