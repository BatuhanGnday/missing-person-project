package com.project.missingperson.service;

import com.project.missingperson.database.DatabaseService;
import com.project.missingperson.database.feedback.Feedback;
import com.project.missingperson.database.model.MissedPerson;

import java.util.List;

public class FeedbackService {

    public static void writeFeedback(MissedPerson missedPerson, String feedback){
        Feedback feedback1 = new Feedback(missedPerson.getId(),feedback);
        feedback1.setMissedPersonId(missedPerson.getId());
        DatabaseService.getFeedbackDao().insert(feedback1);
    }

    public static List<Feedback> getByMissedPersonId(int personId){
        return DatabaseService.getFeedbackDao().getByMissedPersonId(personId);
    }
}
