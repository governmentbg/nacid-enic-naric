<#include "../common/macros/naturalPersonNames.ftl">
<#include "../common/macros/label.ftl">
<#escape x as x?html>
    <#if application.applicantDetails.diplomaNamesDifferent && application.applicantDetails.diplomaNames??>
        <tr>
            <td>
                <h3><@label "diplomaNames.title.label"></@label></h3>
                <@naturalPersonNames application.applicantDetails.diplomaNames/>
            </td>
        </tr>
    </#if>
</#escape>