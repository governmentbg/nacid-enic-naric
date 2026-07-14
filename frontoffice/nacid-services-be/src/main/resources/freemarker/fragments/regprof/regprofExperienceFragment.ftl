<#include "../../common/macros/label.ftl">
<#include "../../common/macros/regprof/regprofExperienceDocument.ftl">
<#escape x as x?html>
    <#if application.educationDetails.experience??>
        <#assign experience = application.educationDetails.experience />
        <tr>
            <td>
                <h3><@label "regprofEducationDetails.experience.title.label"></@label></h3>
                <#if experience.profession??>
                    <div>
                        <b><@label "regprofEducationDetails.experience.profession.label"/>: </b>
                        ${experience.profession}
                    </div>
                </#if>
                <#if experience.experienceDocuments?? && experience.experienceDocuments?size &gt; 0>
                    <#list experience.experienceDocuments as document>
                        <@regprofExperienceDocument document />
                    </#list>
                </#if>
            </td>
        </tr>
    </#if>
</#escape>