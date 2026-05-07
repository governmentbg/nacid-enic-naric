<#macro attachments attachments labelCode>
    <#escape x as x?html>
        <h3><@label labelCode></@label></h3>
        <#if attachments?? && attachments?size &gt; 0>
            <#list attachments as attachment>
                <#if attachment.file??>
                    <div>
                        ${attachment.file.fileName!""}
                        <#if (attachment.attachmentType?? && attachment.attachmentType.name??) || attachment.description??>
                            <i>(
                                ${(attachment.attachmentType?? && attachment.attachmentType.name??)?then(attachment.attachmentType.name, "")}
                                ${(attachment.attachmentType?? && attachment.attachmentType.name?? && attachment.description??)?then("; ", "")}
                                ${(attachment.description??)?then(attachment.description, "")}
                                )</i>
                        </#if>
                        <#if attachment.attachmentForm?? && attachment.attachmentForm.name??>
                            <i>(${attachment.attachmentForm.name})</i>
                        </#if>
                    </div>
                </#if>
            </#list>
        <#else>
            <@label "documents.not.attached.label"/>
        </#if>
    </#escape>
</#macro>