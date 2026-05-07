<#include "../common/macros/resultReceive.ftl">
<#include "../common/macros/receiverAddress.ftl">
<#escape x as x?html>
    <tr>
        <td>
            <#assign resultReceiveLabel = (resultReceiveLabelCode??)?then(resultReceiveLabelCode, "resultReceive.title.label") />
            <@resultReceive application.applicantDetails resultReceiveLabel />
        </td>
    </tr>
    <#if application.applicantDetails.resultReceive?? && application.applicantDetails.resultReceive.receiverAddress??>
        <tr>
            <td>
                <@receiverAddress application.applicantDetails.resultReceive.receiverAddress />
            </td>
        </tr>
    </#if>
    <#if application.applicantDetails.resultReceivePaper?? && application.applicantDetails.resultReceivePaper.receiverAddress??>
        <tr>
            <td>
                <@receiverAddress application.applicantDetails.resultReceivePaper.receiverAddress />
            </td>
        </tr>
    </#if>
</#escape>