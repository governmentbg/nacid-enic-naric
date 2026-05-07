<#include "../common/macros/attachments.ftl">
<#escape x as x?html>
    <#if application.documentDetails??>
        <#if isOtherAttachments?? && isOtherAttachments>
            <#assign labelCode = "otherDocuments.title.label"/>
        <#else>
            <#assign labelCode = "documents.title.label"/>
        </#if>
        <tr>
            <td>
                <@attachments application.documentDetails.attachments![] labelCode/>
            </td>
        </tr>
    </#if>
</#escape>