package com.recruiters.model;

import com.recruiters.model.status.BidStatus;

import java.util.Date;

/**
 * Bid POJO
 */
public class Bid {
    private Long id = null;
    private Vacancy vacancy = null;
    private Recruiter recruiter = null;
    private String message = null;
    private BidStatus status = null;
    private Long dealId = 0L;
    private Boolean viewed = false;
    private Date dateCreated;
    private Date lastModified;

    public Bid() {

    }

    /** Create Bid with Active status */
    public Bid(final Long id, final Vacancy vacancy, final Recruiter recruiter) {
        this.id = id;
        this.vacancy = vacancy;
        this.recruiter = recruiter;
        this.status = BidStatus.ACTIVE;
    }

    /** Create Bid with Active status and current creation date */
    public Bid(final Long id, final Vacancy vacancy, final Recruiter recruiter, final String message) {
        this.id = id;
        this.vacancy = vacancy;
        this.recruiter = recruiter;
        this.message = message;
        this.status = BidStatus.ACTIVE;
        this.dateCreated = new Date();
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public Vacancy getVacancy() {
        return vacancy;
    }

    public void setVacancy(final Vacancy vacancy) {
        this.vacancy = vacancy;
    }

    public Recruiter getRecruiter() {
        return recruiter;
    }

    public void setRecruiter(final Recruiter recruiter) {
        this.recruiter = recruiter;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(final String message) {
        this.message = message;
    }

    public BidStatus getStatus() {
        return status;
    }

    public void setStatus(final BidStatus status) {
        this.status = status;
    }

    public Long getDealId() {
        return dealId;
    }

    public void setDealId(final Long dealId) {
        this.dealId = dealId;
    }

    public Boolean getViewed() {
        return viewed;
    }

    public void setViewed(final Boolean viewed) {
        this.viewed = viewed;
    }

    public Date getLastModified() {
        return lastModified;
    }

    public void setLastModified(final Date lastModified) {
        this.lastModified = lastModified;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(final Date dateCreated) {
        this.dateCreated = dateCreated;
    }
}
