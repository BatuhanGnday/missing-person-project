package com.project.missingperson.database.model;

import com.project.missingperson.database.model.enums.Gender;
import com.project.missingperson.database.model.enums.Role;
import lombok.*;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

@Data
public class User {
    private int id;

    @NonNull
    private String email;

    @NonNull
    private String username;

    @NonNull
    private String password;

    @NonNull
    private String firstName;

    @NonNull
    private String lastName;

    @NonNull
    private Gender gender;

    @NonNull
    private Role role;

    @NonNull
    private String birthday;

    @NonNull
    private Boolean verified;

    private Timestamp creationDate;

    public static class Mapper implements ResultSetMapper<User> {
        @Override
        public User map(int i, ResultSet resultSet, StatementContext statementContext) throws SQLException {
            int id = resultSet.getInt("id");
            String email = resultSet.getString("email");
            String username = resultSet.getString("username");
            String password = resultSet.getString("password");
            String firstName = resultSet.getString("firstName");
            String lastName = resultSet.getString("lastName");
            Gender gender = Gender.valueOf(resultSet.getString("gender"));
            Role role = Role.valueOf(resultSet.getString("role"));
            String birthday = resultSet.getString("birthday");
            Boolean verified = resultSet.getBoolean("verified");
            Timestamp creationDate = resultSet.getTimestamp("creationDate");

            User user = new User(email, username, password, firstName, lastName, gender, role, birthday, verified);

            user.setId(id);
            user.setCreationDate(creationDate);

            return user;
        }
    }
}
