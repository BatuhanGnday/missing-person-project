package com.project.missingperson.database.model;

import com.project.missingperson.database.model.enums.Gender;
import com.project.missingperson.database.model.enums.Nationality;
import lombok.Data;
import lombok.NonNull;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

@Data
public class MissedPerson {

    private int id;

    @NonNull
    private int userId;

    @NonNull
    private String firstName;

    @NonNull
    private String lastName;

    @NonNull
    private Gender gender;

    @NonNull
    private String phoneNumber;

    @NonNull
    private String eyeColor;

    @NonNull
    private String hairColor;

    @NonNull
    private Nationality nationality;

    @NonNull
    private String history;

    @NonNull
    private String lastSeen;

    private Timestamp creationDate;

    @NonNull
    public Boolean isFound;


    public static class Mapper implements ResultSetMapper<MissedPerson>{

        @Override
        public MissedPerson map(int i, ResultSet resultSet, StatementContext statementContext) throws SQLException {

            int userId = resultSet.getInt("userId");
            int id = resultSet.getInt("id");
            String firstName = resultSet.getString("firstName");
            String lastName = resultSet.getString("lastName");
            Gender gender = Gender.valueOf(resultSet.getString("gender"));
            String phoneNumber = resultSet.getString("phoneNumber");
            String eyeColor = resultSet.getString("eyeColor");
            String hairColor = resultSet.getString("hairColor");
            Nationality nationality = Nationality.valueOf(resultSet.getString("nationality"));
            String history = resultSet.getString("history");
            String lastSeen = resultSet.getString("lastSeen");
            Timestamp creationDate = resultSet.getTimestamp("creationDate");
            Boolean found = resultSet.getBoolean("isFound");

            MissedPerson missedPerson = new MissedPerson(userId,firstName,lastName,gender,phoneNumber,eyeColor,hairColor,nationality,history,lastSeen,found);

            missedPerson.setId(id);
            missedPerson.setCreationDate(creationDate);

            return missedPerson;
        }
    }


}
