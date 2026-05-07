<#include "../../common/macros/label.ftl">
<#escape x as x?html>
    <tr style="text-align: center">
        <td><img src="common/img/nacid-logo.png"/></td>
    </tr>
    <tr style="text-align: center">
        <td style="color: #2e42a1" class="titleText">
            <@label titleCode />
        </td>
    </tr>
    <tr>
        <td>
            <#if application.entryNumber?? && application.entryDate?? && !isStatusSubmitted>
                <#assign entryDateStr = (application.entryDate).format("dd.MM.yyyy") />
                <@label "apostilleApplication.accepted.message" application.entryNumber entryDateStr application.tempNumber/>
            <#else>
                <@label "application.submitted.message" application.tempNumber />
            </#if>
        </td>
    </tr>
    <#if application.ESigned?? && application.ESigned>
        <tr>
            <td>
                <@label "application.esigned.message" />
            </td>
        </tr>
    </#if>
</#escape>