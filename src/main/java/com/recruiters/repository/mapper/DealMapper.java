package com.recruiters.repository.mapper;

import com.recruiters.model.Deal;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Mapper for Deal POJO
 */
public interface DealMapper {

    @Select("SELECT deals.id, deals.status, " +
            "vacancies.id as vacancy_id,  vacancies.employer_id, vacancies.title, " +
            "vacancies.description, vacancies.salary, vacancies.creation_date, " +
            "recruiters.id as recruiter_id, " +
            "users.firstname, users.lastname " +
            "FROM deals " +
            "INNER JOIN vacancies ON vacancies.id=deals.vacancy_id " +
            "INNER JOIN recruiters  ON recruiters.id=deals.recruiter_id " +
            "INNER JOIN users ON recruiters.user_id=users.id " +
            "WHERE deals.id=#{dealId}")
    @Results({
            @Result(column = "id", property = "id", javaType = Long.class),
            @Result(column = "status", property = "status"),
            @Result(column = "vacancy_id", property = "vacancy.id"),
            @Result(column = "employer_id", property = "vacancy.employer.id"),
            @Result(column = "title", property = "vacancy.title"),
            @Result(column = "description", property = "vacancy.description"),
            @Result(column = "salary", property = "vacancy.salary"),
            @Result(column = "creation_date", property = "vacancy.creationDate"),
            @Result(column = "recruiter_id", property = "recruiter.id"),
            @Result(column = "firstname", property = "recruiter.user.firstName"),
            @Result(column = "lastname", property = "recruiter.user.lastName"),
            @Result(property = "applicants", column = "id", javaType = List.class,
                    many = @Many(select = "com.recruiters.repository.mapper.ApplicantMapper.getApplicantsByDealId"))
    })
    Deal getById(final Long dealId);

    @Select("SELECT deals.id, deals.status, " +
            "vacancies.id as vacancy_id,  vacancies.employer_id, vacancies.title, " +
            "vacancies.description, vacancies.salary, vacancies.creation_date, " +
            "recruiters.id as recruiter_id, " +
            "users.firstname, users.lastname " +
            "FROM deals " +
            "INNER JOIN vacancies ON vacancies.id=deals.vacancy_id " +
            "INNER JOIN recruiters  ON recruiters.id=deals.recruiter_id " +
            "INNER JOIN users ON recruiters.user_id=users.id " +
            "WHERE deals.recruiter_id=#{recruiterId} AND deals.status=\"IN_PROGRESS\"")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "status", property = "status"),
            @Result(column = "vacancy_id", property = "vacancy.id"),
            @Result(column = "employer_id", property = "vacancy.employer.id"),
            @Result(column = "title", property = "vacancy.title"),
            @Result(column = "description", property = "vacancy.description"),
            @Result(column = "salary", property = "vacancy.salary"),
            @Result(column = "creation_date", property = "vacancy.creationDate"),
            @Result(column = "recruiter_id", property = "recruiter.id"),
            @Result(column = "firstname", property = "recruiter.user.firstName"),
            @Result(column = "lastname", property = "recruiter.user.lastName")
    })
    List<Deal> findActiveDealsByRecruiterId(final Long recruiterId);

    @Select("SELECT deals.id, deals.status, " +
            "vacancies.id as vacancy_id,  vacancies.employer_id, vacancies.title, " +
            "vacancies.description, vacancies.salary, vacancies.creation_date, " +
            "recruiters.id as recruiter_id, " +
            "users.firstname, users.lastname " +
            "FROM deals " +
            "INNER JOIN vacancies ON vacancies.id=deals.vacancy_id " +
            "INNER JOIN recruiters  ON recruiters.id=deals.recruiter_id " +
            "INNER JOIN users ON recruiters.user_id=users.id " +
            "WHERE vacancies.employer_id=#{employerId} AND deals.status=\"IN_PROGRESS\"")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "status", property = "status"),
            @Result(column = "vacancy_id", property = "vacancy.id"),
            @Result(column = "employer_id", property = "vacancy.employer.id"),
            @Result(column = "title", property = "vacancy.title"),
            @Result(column = "description", property = "vacancy.description"),
            @Result(column = "salary", property = "vacancy.salary"),
            @Result(column = "creation_date", property = "vacancy.creationDate"),
            @Result(column = "recruiter_id", property = "recruiter.id"),
            @Result(column = "firstname", property = "recruiter.user.firstName"),
            @Result(column = "lastname", property = "recruiter.user.lastName")
    })
    List<Deal> findActiveDealsByEmployerId(final Long employerId);
}