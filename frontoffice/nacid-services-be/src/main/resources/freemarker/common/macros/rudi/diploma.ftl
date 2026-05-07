<#macro diploma diploma>
    <#escape x as x?html>
        <h3><@label "diploma.title.label"></@label></h3>
        <#if diploma.series??>
            <div>
                <b><@label "diploma.series.label"></@label>:</b>
                ${diploma.series}
            </div>
        </#if>
        <#if diploma.number??>
            <div>
                <b><@label "diploma.number.label"></@label>:</b>
                ${diploma.number}
            </div>
        </#if>
        <#if diploma.registrationNumber??>
            <div>
                <b><@label "diploma.registrationNumber.label"></@label>:</b>
                ${diploma.registrationNumber}
            </div>
        </#if>
        <#if diploma.date??>
            <div>
                <b><@label "diploma.date.label"></@label>:</b>
                ${(diploma.date).format("dd.MM.yyyy")}
            </div>
        </#if>
    </#escape>
</#macro>