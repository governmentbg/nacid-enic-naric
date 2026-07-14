<#include "../../common/macros/label.ftl">
<#escape x as x?html>
    <#if application.bibliographicDetails?? && application.bibliographicDetails.entries?? && application.bibliographicDetails.entries?size &gt; 0>
        <tr>
            <td>
                <#list application.bibliographicDetails.entries as entry>
                    <div style="margin-bottom:10px; margin-top: 10px"><u><@label "docDelivery.details.title.label" /></u></div>
                    <#if entry.bibliographicDataText??>
                        <div>
                            <b><@label "docDelivery.details.bibliographicDataText.label"/>: </b>
                            ${entry.bibliographicDataText}
                        </div>
                    </#if>
                    <#if entry.file?? && entry.file.fileName??>
                        <div>
                            <b><@label "docDelivery.details.file.label"/>: </b>
                            ${entry.file.fileName}
                        </div>
                    </#if>
                    <div>
                        <b><@label "docDelivery.details.selected.label"/>: </b>
                        <#assign hasSomething = false />
                        <#if entry.electronicCatalogues?? && entry.electronicCatalogues>
                            <@label "docDelivery.details.electronicCatalogues.label"/>
                            <#assign hasSomething = true />
                        </#if>
                        <#if entry.bgLibraries?? && entry.bgLibraries>
                            ${hasSomething?then("; ", "")}
                            <@label "docDelivery.details.bgLibraries.label"/>
                            <#assign hasSomething = true />
                        </#if>
                        <#if entry.foreignLibraries?? && entry.foreignLibraries>
                            ${hasSomething?then("; ", "")}
                            <@label "docDelivery.details.foreignLibraries.label"/>
                        </#if>
                    </div>
                    <#if entry.deliveryResultKind?? && entry.deliveryResultKind.name??>
                        <div>
                            <b><@label "docDelivery.details.deliveryResultKind.label"/>: </b>
                            ${entry.deliveryResultKind.name}
                        </div>
                    </#if>
                </#list>
            </td>
        </tr>
    </#if>
</#escape>