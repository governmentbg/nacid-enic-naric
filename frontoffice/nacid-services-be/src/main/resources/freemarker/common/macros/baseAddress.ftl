<#macro baseAddress address>
    <#escape x as x?html>
        <#if address.country?? && address.country.name??>
            <div>
                <b><@label "address.country.label"></@label>:</b>
                ${address.country.name}
            </div>
        </#if>
        <#if address.settlement?? && address.settlement.fullSettlementName??>
            <div>
                <b><@label "address.settlement.label"></@label>:</b>
                ${address.settlement.fullSettlementName}
            </div>
        </#if>
        <#if address.city??>
            <div>
                <b><@label "address.settlement.label"></@label>:</b>
                ${address.city}
            </div>
        </#if>
        <#if address.postCode??>
            <div>
                <b><@label "address.postCode.label"></@label>:</b>
                ${address.postCode}
            </div>
        </#if>
        <#if address.address??>
            <div>
                <b><@label "address.address.label"></@label>:</b>
                ${address.address}
            </div>
        </#if>
        <#if address.phone??>
            <div>
                <b><@label "address.phone.label"></@label>:</b>
                ${address.phone}
            </div>
        </#if>
    </#escape>
</#macro>