<#include "../../common/macros/label.ftl">
<#include "../../common/macros/naturalPerson.ftl">
<#include "../../common/macros/rudi/universitiesData.ftl">
<#include "../../common/macros/rudi/diploma.ftl">
<#include "../../common/macros/rudi/specialities.ftl">
<#include "../../common/macros/rudi/rudiEducation.ftl">
<#include "../../common/macros/rudi/graduationWay.ftl">
<#include "../../common/macros/rudi/gainedQualification.ftl">
<#include "../../common/macros/rudi/educationPlaces.ftl">
<#escape x as x?html>
    <#if application.educationDetails??>
        <tr>
            <td>
                <h3><@label "serviceDetails.title.label"></@label></h3>
                <#if application.educationDetails.statute?? || application.educationDetails.authenticity?? || application.educationDetails.recommendtion??>

                    <div>
                        <b><@label "serviceKind.label"></@label>:</b>
                        <#if application.educationDetails.statute?? && application.educationDetails.statute>
                            <@label "statute.label"/>;
                        </#if>
                        <#if application.educationDetails.authenticity?? && application.educationDetails.authenticity>
                            <@label "authenticity.label"/>;
                        </#if>
                        <#if application.educationDetails.recommendation?? && application.educationDetails.recommendation>
                            <@label "recommendation.label"/>;
                        </#if>
                    </div>

                </#if>
                <#if application.educationDetails.serviceType?? && application.educationDetails.serviceType.name??>
                    <div>
                        <b><@label "serviceType.label"/>:</b> ${application.educationDetails.serviceType.name}
                    </div>
                </#if>
                <#if application.educationDetails.nacidOutgoingNumber??>
                    <div>
                        <b><@label "nacidOutgoingNumber.label"/>:</b> ${application.educationDetails.nacidOutgoingNumber}
                    </div>
                </#if>
                <#if application.educationDetails.applicantIncomingNumber??>
                    <div>
                        <b><@label "applicantIncomingNumber.label"/>:</b> ${application.educationDetails.applicantIncomingNumber}
                    </div>
                </#if>

            </td>
        </tr>
        <#if application.educationDetails.diplomaHolder??>
            <tr>
                <td>
                    <h3><@label "diplomaHolder.title.label"></@label></h3>
                    <@naturalPerson application.educationDetails.diplomaHolder />
                    <#if application.educationDetails.diplomaHolderEan??>
                        <div>
                            <b><@label "diplomaHolderEan.label"/>:</b> ${application.educationDetails.diplomaHolderEan}
                        </div>
                    </#if>
                </td>
            </tr>
        </#if>
        <#if application.educationDetails.universitiesData??>
            <tr>
                <td>
                    <@universitiesData application.educationDetails.universitiesData />
                </td>
            </tr>
        </#if>
        <#if application.educationDetails.diploma??>
            <tr>
                <td>
                    <@diploma application.educationDetails.diploma />
                </td>
            </tr>
        </#if>
        <#if application.educationDetails.educationPlaces??>
            <tr>
                <td>
                    <@educationPlaces application.educationDetails.educationPlaces />
                </td>
            </tr>
        </#if>
        <tr>
            <td>
                <h3><@label "educationDegreeDetails.title.label"></@label></h3>
                <@rudiEducation application.educationDetails />
                <@specialities application.educationDetails.specialities "specialities.uniChecks.label"/>
                <@gainedQualification application.educationDetails.gainedQualification!"" application.educationDetails.originalGainedQualification!""/>
            </td>
        </tr>
        <#if application.educationDetails.graduationWay?? && application.educationDetails.graduationWay?size &gt; 0>
            <tr>
                <td>
                    <h3><@label "graduationWays.label"></@label></h3>
                    <@graduationWay application.educationDetails.graduationWay application.educationDetails.graduationWayOtherDetails!"" "graduationWays.label" "graduationWay.other.label"/>
                </td>
            </tr>
        </#if>
    </#if>
</#escape>