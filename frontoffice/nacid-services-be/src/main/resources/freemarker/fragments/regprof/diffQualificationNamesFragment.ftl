<#include "../../common/macros/naturalPersonNames.ftl">
<#include "../../common/macros/naturalPersonId.ftl">
<#include "../../common/macros/label.ftl">
<#escape x as x?html>
    <#if application.applicantDetails.qualificationNames??>
        <tr>
            <td>
                <h3><@label "qualificationNames.title.label"></@label></h3>
                <@naturalPersonNames application.applicantDetails.qualificationNames/>
                <@naturalPersonId application.applicantDetails.qualificationNames/>
            </td>
        </tr>
    </#if>
</#escape>