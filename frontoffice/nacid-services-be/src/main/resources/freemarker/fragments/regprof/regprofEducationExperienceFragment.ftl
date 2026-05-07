<#include "../../common/macros/label.ftl">
<#escape x as x?html>
    <#if application.educationDetails??>
        <tr>
            <td>
                <h3><@label "regprofEducationDetails.base.title.label"></@label></h3>
                <#if application.educationDetails.serviceType?? && application.educationDetails.serviceType.name??>
                    <div>
                        <b><@label "regprofEducationDetails.serviceType.label"/>: </b>
                        ${application.educationDetails.serviceType.name}
                    </div>
                </#if>
                <#if application.educationDetails.country?? && application.educationDetails.country.name??>
                    <div>
                        <b><@label "regprofEducationDetails.country.label"/>: </b>
                        ${application.educationDetails.country.name}
                    </div>
                </#if>
                <#if application.educationDetails.nonRevokedRightToPractice?? && application.educationDetails.nonRevokedRightToPractice>
                    <div>
                        <b><@label "regprofEducationDetails.nonRevokedRightToPractice.label"/></b>
                    </div>
                </#if>
                <#if application.educationDetails.professionalQualificationRequested?? && application.educationDetails.professionalQualificationRequested != "">
                    <div>
                        <b><@label "regprofEducationDetails.professionalQualificationRequested.label"/>: </b>
                        ${application.educationDetails.professionalQualificationRequested}
                    </div>
                </#if>
            </td>
        </tr>
        <#if application.educationDetails.educationSelected?? && application.educationDetails.educationSelected>
            <#include "regprofEducationFragment.ftl" />
        </#if>
        <#if application.educationDetails.experienceSelected?? && application.educationDetails.experienceSelected>
            <#include "regprofExperienceFragment.ftl" />
        </#if>
    </#if>
</#escape>