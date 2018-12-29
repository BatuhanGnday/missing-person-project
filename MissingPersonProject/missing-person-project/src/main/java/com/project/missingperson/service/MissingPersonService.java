package com.project.missingperson.service;

import com.project.missingperson.database.DatabaseService;
import com.project.missingperson.database.model.MissedPerson;

import javax.swing.*;
import java.util.List;
import java.util.Vector;

public class MissingPersonService {

    public static boolean defineMissingPerson(MissedPerson missedPerson){
        DatabaseService.getMissedPersonDao().defineMissedPerson(missedPerson);
        return true;
    }
    public static void updateMissedPerson(MissedPerson missedPerson){
        DatabaseService.getMissedPersonDao().updateMissedPerson(missedPerson);
    }

    public static List<MissedPerson> getLastxRows(int row){
        return DatabaseService.getMissedPersonDao().getLastxRows(row);
    }

    public static List<MissedPerson> searchByName(String name){
        List<MissedPerson> tempList = DatabaseService.getMissedPersonDao().searchByName(name);
        if(tempList.isEmpty()){
            System.out.println("cannot found");
            JOptionPane.showMessageDialog(null,"cannot found");
            return null;
        }
        return tempList;
    }

    public static List<MissedPerson> viewMissingPersons(){
        return DatabaseService.getMissedPersonDao().viewMissingPeople();
    }

}
