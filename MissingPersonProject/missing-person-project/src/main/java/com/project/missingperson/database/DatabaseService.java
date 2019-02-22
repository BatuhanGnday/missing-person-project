package com.project.missingperson.database;

import com.project.missingperson.database.dao.IFeedbackDao;
import com.project.missingperson.database.dao.IMissedPersonDao;
import com.project.missingperson.database.dao.IUserDao;
import lombok.Getter;
import org.skife.jdbi.v2.DBI;

public class DatabaseService {
    @Getter
    private static IUserDao userDao;

    @Getter
    private static IMissedPersonDao missedPersonDao;

    @Getter
    private static IFeedbackDao feedbackDao;

    static {
        DBI dbi = new DBI("jdbc:mysql://localhost:3306/missing_person?useSSL=false", "root", "password");

        DatabaseService.userDao = dbi.onDemand(IUserDao.class);
        DatabaseService.missedPersonDao = dbi.onDemand(IMissedPersonDao.class);
        DatabaseService.feedbackDao = dbi.onDemand(IFeedbackDao.class);
    }
}
