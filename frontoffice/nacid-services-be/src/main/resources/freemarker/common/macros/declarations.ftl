<#macro declarations applicantDetails agreeDataUsageLabel documentsDeclarationLabel>
    <#escape x as x?html>
        <h3><@label "declarations.title.label"></@label></h3>
        <#if applicantDetails.agreeDataUsage>
            <div>
                <b><@label agreeDataUsageLabel /></b>
            </div>
        </#if>
        <#if applicantDetails.documentsDeclaration>
            <div>
                <b><@label documentsDeclarationLabel /></b>
            </div>
        </#if>
        <#if !applicantDetails.agreeDataUsage && !applicantDetails.documentsDeclaration>
            <@label "declarations.none.label"/>
        </#if>
    </#escape>
</#macro>