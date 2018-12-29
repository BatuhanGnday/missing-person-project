package com.project.missingperson.database.dao;

import com.project.missingperson.database.model.MissedPerson;
import org.skife.jdbi.v2.sqlobject.*;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;
import java.util.List;

public interface IMissedPersonDao {

    @SqlQuery("select * from missed_persons where firstName = :firstName")
    @Mapper(MissedPerson.Mapper.class)
    MissedPerson getByFirstName(@Bind("firstName") String firstName);

    @SqlQuery("select * from missed_persons where id = :id")
    @Mapper(MissedPerson.Mapper.class)
    MissedPerson getById(@Bind("id") int id);

    @SqlQuery("select * from missed_persons order by id desc limit :x")
    @Mapper(MissedPerson.Mapper.class)
    List<MissedPerson> getLastxRows(@Bind("x") int x);

    @SqlQuery("select * from missed_persons where concat(firstName, ' ', lastName) like concat('%',:name,'%')")
    @Mapper(MissedPerson.Mapper.class)
    List<MissedPerson> searchByName(@Bind("name") String name);

    @SqlUpdate("insert into missed_persons(firstName, lastName, gender, phoneNumber, eyeColor, hairColor, nationality, history, lastSeen, isFound) " +
            "values(:object.firstName, :object.lastName, :object.gender, :object.phoneNumber, :object.eyeColor, :object.hairColor, :object.nationality, :object.history, :object.lastSeen, :object.isFound)")
    @GetGeneratedKeys
    int insert(@BindBean("object") MissedPerson missedPerson);

    @SqlUpdate("update missed_persons set firstName=:object.firstName, lastName=:object.lastName, gender=:object.gender," +
            " phoneNumber=:object.phoneNumber, eyeColor=:object.eyeColor, hairColor=:object.hairColor, nationality=:object.nationality," +
            " history=:object.history, lastSeen=:object.lastSeen, isFound=:object.isFound where id=:object.id")
    void updateMissedPerson(@BindBean("object") MissedPerson updatedMissedPerson);

    @SqlQuery("select * from missed_persons")
    @Mapper(MissedPerson.Mapper.class)
    List<MissedPerson> viewMissingPeople();

    @SqlUpdate("insert into missed_persons(userId, firstName, lastName, gender, phoneNumber, eyeColor, hairColor, nationality, history, lastSeen) " +
            "values(:object.userId, :object.firstName, :object.lastName, :object.gender, :object.phoneNumber, :object.eyeColor, :object.hairColor, :object.nationality, :object.history, :object.lastSeen)")
    @GetGeneratedKeys
    int defineMissedPerson(@BindBean("object") MissedPerson missedPerson);

}
