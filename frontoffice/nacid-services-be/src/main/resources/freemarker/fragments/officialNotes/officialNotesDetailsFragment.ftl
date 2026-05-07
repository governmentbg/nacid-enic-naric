<#include "../../common/macros/label.ftl">
<#escape x as x?html>
    <#if application.officialNotesDetails??>
        <tr>
            <td>
                <h3><@label "officialNotes.details.title.label"></@label></h3>
                <#if application.officialNotesDetails.officialNotesKinds?? && application.officialNotesDetails.officialNotesKinds?size &gt; 0>
                    <div>
                        <b><@label "officialNotes.details.kind.label"/>: </b>
                        ${application.officialNotesDetails.officialNotesKinds?map(kind -> resourceBundle(messages, "officialNotes.details.kind."+kind?string, []))?join("; ")}
                    </div>
                </#if>
                <#if application.officialNotesDetails.additionalInformation??>
                    <div>
                        <b><@label "officialNotes.details.additionalInformation.label"/>: </b>
                        ${application.officialNotesDetails.additionalInformation}
                    </div>
                </#if>
            </td>
        </tr>
    </#if>
</#escape>
