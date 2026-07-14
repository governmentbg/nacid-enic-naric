<#macro regprofEducationEntry entry title>
    <div style="margin-bottom:10px; margin-top: 10px"><u><@label title /></u></div>
    <#if entry.oldEducationInstitutionName??>
        <div>
            <b><@label "regprofEducationDetails.education.entry.oldEducationInstitutionName.label"/>: </b>
            ${entry.oldEducationInstitutionName}
        </div>
    </#if>
    <#if entry.newEducationInstitutionName??>
        <div>
            <b><@label "regprofEducationDetails.education.entry.newEducationInstitutionName.label"/>: </b>
            ${entry.newEducationInstitutionName}
        </div>
    </#if>
    <#if entry.professionalQualification??>
        <div>
            <b><@label "regprofEducationDetails.education.entry.professionalQualification.label"/>: </b>
            ${entry.professionalQualification}
        </div>
    </#if>
    <#if entry.specialities?? && entry.specialities?size &gt; 0>
        <div>
            <b><@label "regprofEducationDetails.education.entry.specialities.label"/>: </b>
            ${entry.specialities?map(spec -> spec.name )?join(", ")}
        </div>
    </#if>
    <#if entry.documentKind?? && entry.documentKind.name??>
        <div>
            <b><@label "regprofEducationDetails.education.entry.documentKind.label"/>: </b>
            ${entry.documentKind.name}
        </div>
    </#if>
    <#if entry.documentSeries??>
        <div>
            <b><@label "regprofEducationDetails.education.entry.documentSeries.label"/>: </b>
            ${entry.documentSeries}
        </div>
    </#if>
    <#if entry.documentNumber??>
        <div>
            <b><@label "regprofEducationDetails.education.entry.documentNumber.label"/>: </b>
            ${entry.documentNumber}
        </div>
    </#if>
    <#if entry.documentRegistrationNumber??>
        <div>
            <b><@label "regprofEducationDetails.education.entry.documentRegistrationNumber.label"/>: </b>
            ${entry.documentRegistrationNumber}
        </div>
    </#if>
    <#if entry.documentDate??>
        <div>
            <b><@label "regprofEducationDetails.education.entry.documentDate.label"/>: </b>
            ${(entry.documentDate).format("dd.MM.yyyy")}
        </div>
    </#if>
    <#if entry.qualificationRank?? && entry.qualificationRank.name??>
        <div>
            <b><@label "regprofEducationDetails.education.entry.qualificationRank.label"/>: </b>
            ${entry.qualificationRank.name}
        </div>
    </#if>
    <#if entry.eduLevel?? && entry.eduLevel.name??>
        <div>
            <b><@label "regprofEducationDetails.education.entry.eduLevel.label"/>: </b>
            ${entry.eduLevel.name}
        </div>
    </#if>
</#macro>