package com.recruiters.model;

/**
 * Applicant POJO Class
 */
public class Applicant {
    private Long id;
    private Deal deal;
    private String firstName;
    private String lastName;
    private String description;
    private String resumeFile;
    private String testAnswerFile;

    public Applicant() {
    }

    public Applicant(final Long id, final Deal deal, final String firstName, final String lastName) {
        this.id = id;
        this.deal = deal;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Applicant(final Long id, final Deal deal, final String firstName,
                     final String lastName, final String description) {
        this.id = id;
        this.deal = deal;
        this.firstName = firstName;
        this.lastName = lastName;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public Deal getDeal() {
        return deal;
    }

    public void setDeal(final Deal deal) {
        this.deal = deal;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public String getResumeFile() {
        return resumeFile;
    }

    public void setResumeFile(final String resumeFile) {
        this.resumeFile = resumeFile;
    }

    public String getTestAnswerFile() {
        return testAnswerFile;
    }

    public void setTestAnswerFile(final String testAnswerFile) {
        this.testAnswerFile = testAnswerFile;
    }
}
