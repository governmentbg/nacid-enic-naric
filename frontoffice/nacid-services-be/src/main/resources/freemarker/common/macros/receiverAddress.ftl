<#macro receiverAddress address>
    <#include "baseAddress.ftl" />

    <#escape x as x?html>
        <h3><@label "receiverAddress.title.label"></@label></h3>

        <#if address.name??>
            <div>
                <b><@label "address.name.label"></@label>:</b>
                ${address.name}
            </div>
        </#if>

        <@baseAddress address />
    </#escape>
</#macro>