<#macro naturalPersonId person>
    <#escape x as x?html>
        <div>
            <#if person.personalId??>
                <b><@label "person.indentifier.label"></@label>:</b>
                ${person.personalId+" "}
            <#elseif person.personalNacidId??>
                <b><@label "person.nacidIndentifier.label"></@label>:</b>
                ${person.personalNacidId+" "}
            </#if>
            <#if person.personalIdType??>(<@label "person.personalIdType."+person.personalIdType?string />)</#if>
        </div>

        <#if person.foreignerIdentifierKind?? && person.foreignerIdentifierKind.name??>
            <div>
                <b><@label "person.foreignerIdentifierKind.label"></@label>:</b>
                ${person.foreignerIdentifierKind.name}
            </div>
        </#if>
        <#if person.foreignerIdentifierCountry?? && person.foreignerIdentifierCountry.name??>
            <div>
                <b><@label "person.foreignerIdentifierCountry.label"></@label>:</b>
                ${person.foreignerIdentifierCountry.name}
            </div>
        </#if>
    </#escape>
</#macro>