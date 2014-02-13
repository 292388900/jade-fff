package com.recruiters.repository.mapper;

import com.recruiters.model.Feedback;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * Mapper for Feedback
 */
public interface FeedbackMapper {

    @Select("SELECT * FROM feedback " +
            "WHERE deal_id=#{dealId}")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "deal_id", property = "deal.id"),
            @Result(column = "recruiter_id", property = "recruiter.id"),
            @Result(column = "employer_id", property = "employer.id"),
            @Result(column = "employer_feedback", property = "employerFeedback"),
            @Result(column = "employer_time", property = "employerTime"),
            @Result(column = "recruiter_feedback", property = "recruiterFeedback"),
            @Result(column = "recruiter_time", property = "recruiterTime")
    })
    Feedback findByDealId(final Long dealId);



    @Insert("INSERT INTO feedback (deal_id, recruiter_id, employer_id) " +
            "VALUES (#{deal.id}, #{recruiter.id}, #{employer.id})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    void create(final Feedback feedback);

    @Update("UPDATE feedback SET employer_feedback=#{employerFeedback}, employer_time=NOW() " +
            "WHERE deal_id=#{dealId}")
    void updateEmployerFeedback(
            @Param("dealId") final Long dealId,
            @Param("employerFeedback") final String employerFeedback
    );

    @Update("UPDATE feedback SET recruiter_feedback=#{recruiterFeedback}, recruiter_time=NOW() " +
            "WHERE deal_id=#{dealId}")
    void updateRecruiterFeedback(
            @Param("dealId") final Long dealId,
            @Param("recruiterFeedback") final String recruiterFeedback
    );
}
