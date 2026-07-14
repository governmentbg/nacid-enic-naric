<#include "../../common/macros/label.ftl">
<#escape x as x?html>
    <#if application.suggestionDetails??>
        <tr>
            <td>
                <h3><@label "suggestion.details.title.label"></@label></h3>
                <#if application.suggestionDetails.suggestion??>
                    <div>
                        <b><@label "suggestion.details.suggestion.label"/>: </b>
                        ${application.suggestionDetails.suggestion}
                    </div>
                </#if>
            </td>
        </tr>
    </#if>
</#escape>