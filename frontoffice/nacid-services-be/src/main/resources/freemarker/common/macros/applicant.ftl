<#macro applicant servicesApplicant showApplicantTpe>
    <#include "naturalPerson.ftl" />
    <#include "company.ftl" />
    <#include "university.ftl" />

    <#escape x as x?html>
        <h3><@label "applicant.title.label"></@label></h3>
        <#if showApplicantTpe>
            <div>
                <b><@label "person.type.label"/>: </b>
                <@label "person.type."+servicesApplicant.applicantType?string />
            </div>
        </#if>
        <#if servicesApplicant.applicantType?string == "NATURAL_PERSON" && servicesApplicant.naturalPerson??>
            <@naturalPerson servicesApplicant.naturalPerson/>
        </#if>
        <#if servicesApplicant.applicantType?string == "COMPANY" && servicesApplicant.company??>
            <@company servicesApplicant.company />
        </#if>
        <#if servicesApplicant.applicantType?string == "UNIVERSITY" && servicesApplicant.university??>
            <@university servicesApplicant.university />
        </#if>
    </#escape>
</#macro>