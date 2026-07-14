<#macro contactAddress address>
    <#include "baseAddress.ftl" />

    <#escape x as x?html>
        <h3><@label "contactAddress.title.label"></@label></h3>

        <@baseAddress address />

        <#if address.postBox??>
            <div>
                <b><@label "address.postBox.label"></@label>:</b>
                ${address.postBox}
            </div>
        </#if>
        <#if address.email??>
            <div>
                <b><@label "address.email.label"></@label>:</b>
                ${address.email}
            </div>
        </#if>
        <#if address.fax??>
            <div>
                <b><@label "address.fax.label"></@label>:</b>
                ${address.fax}
            </div>
        </#if>
    </#escape>
</#macro>