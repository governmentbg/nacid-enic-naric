<#include "../../common/macros/label.ftl">
<#escape x as x?html>
    <#if application.bibliographicReferenceDetails??>
        <tr>
            <td>
                <h3><@label "bibliographicReference.details.title.label"></@label></h3>
                <#if application.bibliographicReferenceDetails.nacidSearch?? && application.bibliographicReferenceDetails.nacidSearch && application.bibliographicReferenceDetails.nacidSearchKind??>
                    <div>
                        <b><@label "bibliographicReference.details.nacidSearch.label"/> - </b>
                        <@label "bibliographicReference.details.result.kind."+application.bibliographicReferenceDetails.nacidSearchKind?string />
                    </div>
                </#if>
                <#if application.bibliographicReferenceDetails.foreignSearch?? && application.bibliographicReferenceDetails.foreignSearch && application.bibliographicReferenceDetails.foreignSearchKind??>
                    <div>
                        <b><@label "bibliographicReference.details.foreignSearch.label"/> - </b>
                        <@label "bibliographicReference.details.result.kind."+application.bibliographicReferenceDetails.foreignSearchKind?string />
                    </div>
                </#if>
                <#if application.bibliographicReferenceDetails.theme??>
                    <div>
                        <b><@label "bibliographicReference.details.theme.label"/>: </b>
                        ${application.bibliographicReferenceDetails.theme}
                    </div>
                </#if>
                <#if application.bibliographicReferenceDetails.keywords??>
                    <div>
                        <b><@label "bibliographicReference.details.keywords.label"/>: </b>
                        ${application.bibliographicReferenceDetails.keywords}
                    </div>
                </#if>
                <#if application.bibliographicReferenceDetails.searchFrom?? && application.bibliographicReferenceDetails.searchTo?? >
                    <div>
                        <b><@label "bibliographicReference.details.searchFromTo.label"/>: </b>
                        ${application.bibliographicReferenceDetails.searchFrom} - ${application.bibliographicReferenceDetails.searchTo}
                    </div>
                </#if>
                <#if application.bibliographicReferenceDetails.searchLanguages?? && application.bibliographicReferenceDetails.searchLanguages?size &gt; 0 >
                    <div>
                        <b><@label "bibliographicReference.details.searchLanguages.label"/>: </b>
                        ${application.bibliographicReferenceDetails.searchLanguages?map(lang -> lang.name)?join("; ")}
                    </div>
                </#if>
            </td>
        </tr>
    </#if>
</#escape>