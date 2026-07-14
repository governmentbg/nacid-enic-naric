<#macro naturalPersonNames person>
    <#escape x as x?html>
        <#if person.firstName??>
            <div>
                <b><@label "person.name.label"></@label>:</b>
                ${person.firstName}
                <#if person.middleName??>${" "+person.middleName}</#if>
                <#if person.lastName??>${" "+person.lastName}</#if>
            </div>
        </#if>
    </#escape>
</#macro>