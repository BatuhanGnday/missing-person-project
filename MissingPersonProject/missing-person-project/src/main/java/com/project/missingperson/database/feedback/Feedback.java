package com.project.missingperson.database.feedback;

import lombok.Data;
import lombok.NonNull;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

@Data
public class Feedback {

    private int id;

    @NonNull
    private int missedPersonId;

    @NonNull
    private String content;

    private Timestamp issuedDate;

    public static class Mapper implements ResultSetMapper<Feedback>{
        @Override
        public Feedback map(int i, ResultSet resultSet, StatementContext statementContext) throws SQLException {
            int id = resultSet.getInt("id");
            String content = resultSet.getString("content");
            Timestamp issuedDate = resultSet.getTimestamp("issuedDate");
            int missedPersonId = resultSet.getInt("missedPersonId");

            Feedback feedback = new Feedback(missedPersonId,content);
            feedback.setId(id);
            feedback.setIssuedDate(issuedDate);

            return feedback;
        }
    }
}
