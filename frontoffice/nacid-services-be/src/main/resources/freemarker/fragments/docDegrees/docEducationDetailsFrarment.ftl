<#include "../../common/macros/label.ftl">
<#include "../../common/macros/rudi/universitiesData.ftl">
<#include "../../common/macros/rudi/diploma.ftl">
<#include "../../common/macros/rudi/rudiEducation.ftl">
<#include "../../common/macros/rudi/graduationWay.ftl">
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
                <h3><@label "educationDegreeDetails.title.label"></@label></h3>
                <@rudiEducation application.educationDetails />
            </td>
        </tr>
        <#if application.educationDetails.graduationWay?? && application.educationDetails.graduationWay?size &gt; 0>
            <tr>
                <td>
                    <h3><@label "docGraduationWays.label"></@label></h3>
                    <@graduationWay application.educationDetails.graduationWay application.educationDetails.graduationWayOtherDetails!"" "docGraduationWays.label" "docGraduationWay.other.label"/>
                </td>
            </tr>
        </#if>
        <#if application.educationDetails.dissertationTheme??>
            <tr>
                <td>
                    <h3><@label "dissertation.title.label"></@label></h3>
                    <div>
                        <b><@label "dissertationTheme.label"></@label>:</b>
                        ${application.educationDetails.dissertationTheme}
                    </div>
                    <#if application.educationDetails.dissertationThemeEn??>
                        <div>
                            <b><@label "dissertationThemeEn.label"></@label>:</b>
                            ${application.educationDetails.dissertationThemeEn}
                        </div>
                    </#if>
                    <#if application.educationDetails.dissertationDate??>
                        <div>
                            <b><@label "dissertationDate.label"></@label>:</b>
                            ${application.educationDetails.dissertationDate}
                        </div>
                    </#if>
                    <#if application.educationDetails.dissertationLanguage?? && application.educationDetails.dissertationLanguage.name??>
                        <div>
                            <b><@label "dissertationLanguage.label"></@label>:</b>
                            ${application.educationDetails.dissertationLanguage.name}
                        </div>
                    </#if>
                    <#if application.educationDetails.dissertationBiblioTitlesCount??>
                        <div>
                            <b><@label "dissertationBiblioTitlesCount.label"></@label>:</b>
                            ${application.educationDetails.dissertationBiblioTitlesCount}
                        </div>
                    </#if>
                    <#if application.educationDetails.dissertationPagesCount??>
                        <div>
                            <b><@label "dissertationPagesCount.label"></@label>:</b>
                            ${application.educationDetails.dissertationPagesCount}
                        </div>
                    </#if>
                    <#if application.educationDetails.dissertationAnnotation??>
                        <div>
                            <b><@label "dissertationAnnotation.label"></@label>:</b>
                            ${application.educationDetails.dissertationAnnotation}
                        </div>
                    </#if>
                    <#if application.educationDetails.dissertationAnnotationEn??>
                        <div>
                            <b><@label "dissertationAnnotationEn.label"></@label>:</b>
                            ${application.educationDetails.dissertationAnnotationEn}
                        </div>
                    </#if>
                </td>
            </tr>
        </#if>
        <#if application.educationDetails.previousUniversityDiploma??>
            <#assign diploma = application.educationDetails.previousUniversityDiploma />
            <#if diploma.universityName?? || (diploma.gainedLevel?? && diploma.gainedLevel.name??)
            || diploma.speciality?? || (diploma.graduationYear?? && diploma.graduationYear != "") || diploma.notes??>
                <tr>
                    <td>
                        <@previousUniDiploma diploma />
                    </td>
                </tr>
            </#if>
        </#if>
    </#if>
</#escape>