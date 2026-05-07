<#macro resultReceive applicantDetails labelCode>
    <#include "baseAddress.ftl" />

    <#escape x as x?html>
        <h3><@label labelCode></@label></h3>

        <#if applicantDetails.resultReceive?? && applicantDetails.resultReceive.resultReceive?? && applicantDetails.resultReceive.resultReceive.name??>
            <div>
                <b>${applicantDetails.resultReceive.resultReceive.name}</b>
            </div>
        </#if>
        <#if applicantDetails.resultReceiveElectronic?? && applicantDetails.resultReceiveElectronic.resultReceive?? && applicantDetails.resultReceiveElectronic.resultReceive.name??>
            <div>
                <@label "resultReceive.electronic.label"></@label>: <b>${applicantDetails.resultReceiveElectronic.resultReceive.name}</b>
            </div>
        </#if>
        <#if applicantDetails.resultReceivePaper?? && applicantDetails.resultReceivePaper.resultReceive?? && applicantDetails.resultReceivePaper.resultReceive.name??>
            <div>
                <@label "resultReceive.paper.label"></@label>: <b>${applicantDetails.resultReceivePaper.resultReceive.name}</b>
            </div>
        </#if>
    </#escape>
</#macro>