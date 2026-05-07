<#macro company company>
    <#escape x as x?html>
        <#if company.companyName??>
            <div>
                <b><@label "company.companyName.label"></@label>:</b>
                ${company.companyName}
            </div>
        </#if>
        <#if company.companyIdentifier??>
            <div>
                <b><@label "company.companyIdentifier.label"></@label>:</b>
                ${company.companyIdentifier}
            </div>
        </#if>
        <#if company.companyCountry?? && company.companyCountry.name??>
            <div>
                <b><@label "company.companyCountry.label"></@label>:</b>
                ${company.companyCountry.name}
            </div>
        </#if>
        <#if company.companyCity??>
            <div>
                <b><@label "company.companySettlement.label"></@label>:</b>
                ${company.companyCity}
            </div>
        </#if>
        <#if company.companySettlement?? && company.companySettlement.fullSettlementName??>
            <div>
                <b><@label "company.companySettlement.label"></@label>:</b>
                ${company.companySettlement.fullSettlementName}
            </div>
        </#if>

    </#escape>
</#macro>