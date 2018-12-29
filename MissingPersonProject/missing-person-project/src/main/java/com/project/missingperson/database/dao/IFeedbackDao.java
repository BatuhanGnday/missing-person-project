package com.project.missingperson.database.dao;

import com.project.missingperson.database.feedback.Feedback;
import org.skife.jdbi.v2.sqlobject.*;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

import java.util.List;

public interface IFeedbackDao {

    @SqlUpdate("insert into feedbacks(content, missedPersonId) values (:object.content, :object.missedPersonId)")
    @GetGeneratedKeys
    int insert(@BindBean("object") Feedback Feedback);

    @SqlQuery("select * from feedbacks where missedPersonId = :id")
    @Mapper(Feedback.Mapper.class)
    List<Feedback> getByMissedPersonId(@Bind("id") int id);

}
