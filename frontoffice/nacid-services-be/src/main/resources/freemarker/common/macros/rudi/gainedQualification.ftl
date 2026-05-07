<#macro gainedQualification qualification originalQualification>
    <#escape x as x?html>
        <#if qualification != "">
            <div>
                <b><@label "gainedQualification.label"></@label>:</b>
                ${qualification}
            </div>
        </#if>
        <#if originalQualification != "">
            <div>
                <b><@label "originalGainedQualification.label"></@label>:</b>
                ${qualification}
            </div>
        </#if>
    </#escape>
</#macro>