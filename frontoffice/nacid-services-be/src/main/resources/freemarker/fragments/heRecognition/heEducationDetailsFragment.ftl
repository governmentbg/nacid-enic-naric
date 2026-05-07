<#include "../../common/macros/label.ftl">
<#include "../../common/macros/rudi/universitiesData.ftl">
<#include "../../common/macros/rudi/diploma.ftl">
<#include "../../common/macros/rudi/specialities.ftl">
<#include "../../common/macros/rudi/rudiEducation.ftl">
<#include "../../common/macros/rudi/graduationWay.ftl">
<#include "../../common/macros/rudi/gainedQualification.ftl">
<#include "../../common/macros/rudi/previousUniDiploma.ftl">
<#include "../../common/macros/rudi/educationPlaces.ftl">
<#escape x as x?html>
    <#if application.educationDetails??>
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
                <h3><@label "educationDetails.title.label"></@label></h3>
                <@rudiEducation application.educationDetails />
                <@specialities application.educationDetails.specialities "specialities.label"/>
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
        <#if application.educationDetails.highSchoolDiploma?? && application.educationDetails.highSchoolDiploma.country?? && application.educationDetails.highSchoolDiploma.country.name??>
            <tr>
                <td>
                    <h3><@label "highSchoolDiploma.title.label"></@label></h3>
                    <#if application.educationDetails.highSchoolDiploma.country?? && application.educationDetails.highSchoolDiploma.country.name??>
                        <div>
                            <b><@label "highSchoolDiploma.country.label"></@label>:</b>
                            ${application.educationDetails.highSchoolDiploma.country.name}
                        </div>
                    </#if>
                    <#if application.educationDetails.highSchoolDiploma.city??>
                        <div>
                            <b><@label "highSchoolDiploma.city.label"></@label>:</b>
                            ${application.educationDetails.highSchoolDiploma.city}
                        </div>
                    </#if>
                    <#if application.educationDetails.highSchoolDiploma.school??>
                        <div>
                            <b><@label "highSchoolDiploma.school.label"></@label>:</b>
                            ${application.educationDetails.highSchoolDiploma.school}
                        </div>
                    </#if>
                    <#if application.educationDetails.highSchoolDiploma.graduationYear??>
                        <div>
                            <b><@label "highSchoolDiploma.graduationYear.label"></@label>:</b>
                            ${application.educationDetails.highSchoolDiploma.graduationYear}
                        </div>
                    </#if>
                    <#if application.educationDetails.highSchoolDiploma.notes??>
                        <div>
                            <b><@label "highSchoolDiploma.notes.label"></@label>:</b>
                            ${application.educationDetails.highSchoolDiploma.notes}
                        </div>
                    </#if>
                </td>
            </tr>
        </#if>
        <#if application.educationDetails.previousUniversityDiploma??>
            <tr>
                <td>
                    <@previousUniDiploma application.educationDetails.previousUniversityDiploma />
                </td>
            </tr>
        </#if>
        <#if application.educationDetails.recognitionAim?? && application.educationDetails.recognitionAim?size &gt; 0>
            <tr>
                <td>
                    <h3><@label "recognitionAim.title.label"></@label></h3>
                    <div>
                        <b><@label "recognitionAim.label"></@label>:</b>
                        <#assign aims = application.educationDetails.recognitionAim?map(ra -> ra.name) />
                        ${aims?join("; ")}
                    </div>
                    <#if  application.educationDetails.recognitionAimOtherDetails??>
                        <div>
                            <b><@label "recognitionAim.other.label"></@label>:</b>
                            ${application.educationDetails.recognitionAimOtherDetails}
                        </div>
                    </#if>
                </td>
            </tr>
        </#if>
    </#if>
</#escape>