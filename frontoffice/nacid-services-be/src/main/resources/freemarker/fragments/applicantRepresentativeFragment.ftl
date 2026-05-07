<#include "../common/macros/applicant.ftl">
<#include "../common/macros/representative.ftl">
<#escape x as x?html>
    <tr>
        <td>
            <@applicant application.applicantDetails.applicant showApplicantType />
            <#if application.applicantDetails.applicantTitleBefore??>
                <div>
                    <b><@label "applicant.titleBefore.label"></@label>:</b>
                    ${application.applicantDetails.applicantTitleBefore}
                </div>
            </#if>
            <#if application.applicantDetails.applicantTitleAfter??>
                <div>
                    <b><@label "applicant.titleAfter.label"></@label>:</b>
                    ${application.applicantDetails.applicantTitleAfter}
                </div>
            </#if>
        </td>
    </tr>
    <#if application.applicantDetails.representative??>
        <tr>
            <td>
                <@representative application.applicantDetails.representative
                application.applicantDetails.applicantHasRepresentative
                application.applicantDetails.representativeCompanyIdentifier!""
                application.applicantDetails.representativeCapacity!"" />
            </td>
        </tr>
    </#if>
</#escape>