<#include "../../common/macros/label.ftl">
<#escape x as x?html>
    <#if application.publicAccessDetails??>
        <tr>
            <td>
                <h3><@label "publicAccess.details.title.label"></@label></h3>
                <#if application.publicAccessDetails.about??>
                    <div>
                        <b><@label "publicAccess.details.about.label"/>: </b>
                        ${application.publicAccessDetails.about}
                    </div>
                </#if>
                <#if application.publicAccessDetails.infoForms?? && application.publicAccessDetails.infoForms?size &gt; 0>
                    <div>
                        <b><@label "publicAccess.details.infoForms.label"/>: </b>
                        ${application.publicAccessDetails.infoForms?map(inform -> inform.name)?join("; ")}
                    </div>
                </#if>
                <#if application.publicAccessDetails.comment??>
                    <div>
                        <b><@label "publicAccess.details.comment.label"/>: </b>
                        ${application.publicAccessDetails.comment}
                    </div>
                </#if>
            </td>
        </tr>
    </#if>
</#escape>