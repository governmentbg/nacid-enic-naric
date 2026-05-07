package bg.duosoft.nacid.ras.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.util.Date;

/**
 * User: ggeorgiev
 * Date: 22.10.2019 г.
 * Time: 18:53
 */
public class Dissertation {
    private String title;//tema na BG
    private String titleAlt; //tema na drug ezik
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Europe/Sofia")
    private LocalDate dateOfAcquire;//data na zashtita
    private Integer numberOfBibliography;//bibliografiq
    private Integer languageId;//ezik
    private Integer numberOfPages;
    private String annotation;//anotaciq
    private String annotationAlt;//anotaciq na anglijski
    private boolean dissertationIsNotDeposited; //Дисертацията не подлежи на депозиране в НАЦИД
    private String supervisor;
    private String supervisorAlt;
    private String reviewers;
    private String reviewersAlt;
    private String headOfJury;
    private String headOfJuryAlt;
    private String jury;
    private String juryAlt;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitleAlt() {
        return titleAlt;
    }

    public void setTitleAlt(String titleAlt) {
        this.titleAlt = titleAlt;
    }

    public LocalDate getDateOfAcquire() {
        return dateOfAcquire;
    }

    public void setDateOfAcquire(LocalDate dateOfAcquire) {
        this.dateOfAcquire = dateOfAcquire;
    }

    public Integer getNumberOfBibliography() {
        return numberOfBibliography;
    }

    public void setNumberOfBibliography(Integer numberOfBibliography) {
        this.numberOfBibliography = numberOfBibliography;
    }

    public Integer getLanguageId() {
        return languageId;
    }

    public void setLanguageId(Integer languageId) {
        this.languageId = languageId;
    }

    public Integer getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(Integer numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public String getAnnotation() {
        return annotation;
    }

    public void setAnnotation(String annotation) {
        this.annotation = annotation;
    }

    public String getAnnotationAlt() {
        return annotationAlt;
    }

    public void setAnnotationAlt(String annotationAlt) {
        this.annotationAlt = annotationAlt;
    }

    public boolean isDissertationIsNotDeposited() {
        return dissertationIsNotDeposited;
    }

    public void setDissertationIsNotDeposited(boolean dissertationIsNotDeposited) {
        this.dissertationIsNotDeposited = dissertationIsNotDeposited;
    }

    public String getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(String supervisor) {
        this.supervisor = supervisor;
    }

    public String getSupervisorAlt() {
        return supervisorAlt;
    }

    public void setSupervisorAlt(String supervisorAlt) {
        this.supervisorAlt = supervisorAlt;
    }

    public String getReviewers() {
        return reviewers;
    }

    public void setReviewers(String reviewers) {
        this.reviewers = reviewers;
    }

    public String getReviewersAlt() {
        return reviewersAlt;
    }

    public void setReviewersAlt(String reviewersAlt) {
        this.reviewersAlt = reviewersAlt;
    }

    public String getHeadOfJury() {
        return headOfJury;
    }

    public void setHeadOfJury(String headOfJury) {
        this.headOfJury = headOfJury;
    }

    public String getHeadOfJuryAlt() {
        return headOfJuryAlt;
    }

    public void setHeadOfJuryAlt(String headOfJuryAlt) {
        this.headOfJuryAlt = headOfJuryAlt;
    }

    public String getJury() {
        return jury;
    }

    public void setJury(String jury) {
        this.jury = jury;
    }

    public String getJuryAlt() {
        return juryAlt;
    }

    public void setJuryAlt(String juryAlt) {
        this.juryAlt = juryAlt;
    }
}
