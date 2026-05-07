<#include "../../common/macros/label.ftl">
<#include "../../common/macros/regprof/regprofEducationEntry.ftl">
<#escape x as x?html>
    <#if application.educationDetails.education??>
        <#assign education = application.educationDetails.education />
        <tr>
            <td>
                <h3><@label "regprofEducationDetails.education.title.label"></@label></h3>
                <#if education.kind??>
                    <#if education.kind?string == "AFTER_DIPLOMA_QUALIFICATION">
                        <@regprofEducationEntry education.educationEntryHigher "regprofEducationDetails.education.kind.HIGHER_EDUCATION"/>
                        <@regprofEducationEntry education.educationEntryADQ "regprofEducationDetails.education.kind.AFTER_DIPLOMA_QUALIFICATION"/>
                    <#elseif  education.kind?string == "HIGHER_EDUCATION">
                        <@regprofEducationEntry education.educationEntryHigher "regprofEducationDetails.education.kind."+education.kind?string/>
                    <#else>
                        <@regprofEducationEntry education.educationEntrySecondary "regprofEducationDetails.education.kind."+education.kind?string/>
                    </#if>
                </#if>
            </td>
        </tr>
    </#if>
</#escape>