<#include "../../common/macros/label.ftl">
<#escape x as x?html>
    <#if application.signalDetails??>
        <tr>
            <td>
                <h3><@label "signal.details.title.label"></@label></h3>
                <#if application.signalDetails.violationDescription??>
                    <div>
                        <b><@label "signal.details.violationDescription.label"/>: </b>
                        ${application.signalDetails.violationDescription}
                    </div>
                </#if>
                <#if application.signalDetails.violationPlace??>
                    <div>
                        <b><@label "signal.details.violationPlace.label"/>: </b>
                        ${application.signalDetails.violationPlace}
                    </div>
                </#if>
                <#if application.signalDetails.checkRequirement??>
                    <div>
                        <b><@label "signal.details.checkRequirement.label"/>: </b>
                        ${application.signalDetails.checkRequirement}
                    </div>
                </#if>
                <#if application.signalDetails.damagesDescription??>
                    <div>
                        <b><@label "signal.details.damagesDescription.label"/>: </b>
                        ${application.signalDetails.damagesDescription}
                    </div>
                </#if>
                <#if application.signalDetails.measuresTaken??>
                    <div>
                        <b><@label "signal.details.measuresTaken.label"/>: </b>
                        ${application.signalDetails.measuresTaken}
                    </div>
                </#if>
            </td>
        </tr>
    </#if>
</#escape>