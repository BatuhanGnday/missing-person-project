package com.project.missingperson.database.dao;

import com.project.missingperson.database.model.User;
import org.skife.jdbi.v2.sqlobject.*;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

import java.util.List;

public interface IUserDao {

    @SqlQuery("select * from users where email = :email")
    @Mapper(User.Mapper.class)
    User getByEmail(@Bind("email") String email);

    @SqlQuery("select * from users where id = :id")
    @Mapper(User.Mapper.class)
    User getById(@Bind("id") int id);

    @SqlQuery("select * from users where verified = 0")
    @Mapper(User.Mapper.class)
    List<User> getUnverifiedUsers();

    @SqlUpdate("update users set email=:object.email, username=:object.username, password=:object.password, firstName=:object.firstName," +
            " lastName=:object.lastName, gender=:object.gender, role=:object.role, birthday=:object.birthday, verified=:object.verified where id=:object.id")
    void updateUser(@BindBean("object") User user);

    @SqlQuery("select * from users where username = :username")
    @Mapper(User.Mapper.class)
    User getByUsername(@Bind("username") String username);

    @SqlUpdate(value = "update users set password = :password where id=:object.id")
    @Mapper(User.Mapper.class)
    void changePassword(@BindBean("object") User user, @Bind("password") String newPassword);

    @SqlUpdate("insert into users(email, username, password, firstName, lastName, gender, role, birthday, verified) " +
            "values(:object.email, :object.username, :object.password, :object.firstName, :object.lastName, :object.gender, :object.role, :object.birthday, :object.verified)")
    @GetGeneratedKeys
    int insert(@BindBean("object") User user);

    @SqlUpdate(value = "update users set verified = true where id=:object.id")
    @Mapper(User.Mapper.class)
    void verifyUser(@BindBean("object") User user);
}
