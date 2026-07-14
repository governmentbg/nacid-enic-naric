<#macro university university>
    <#escape x as x?html>
        <#if university.universityName??>
            <div>
                <b><@label "university.universityName.label"></@label>:</b>
                ${university.universityName}
            </div>
        </#if>
        <#if university.universityIdentifier??>
            <div>
                <b><@label "university.universityIdentifier.label"></@label>:</b>
                ${university.universityIdentifier}
            </div>
        </#if>
        <#if university.universityCountry?? && university.universityCountry.name??>
            <div>
                <b><@label "university.universityCountry.label"></@label>:</b>
                ${university.universityCountry.name}
            </div>
        </#if>
        <#if university.universitySettlement?? && university.universitySettlement.fullSettlementName??>
            <div>
                <b><@label "university.universitySettlement.label"></@label>:</b>
                ${university.universitySettlement.fullSettlementName}
            </div>
        </#if>

    </#escape>
</#macro>