<#macro regprofExperienceDocument document>
    <div style="margin-bottom:10px; margin-top: 10px"><u><@label "regprofEducationDetails.experience.document.title.label" /></u></div>
    <#if document.type?? && document.type.name??>
        <div>
            <b><@label "regprofEducationDetails.experience.document.type.label"/>: </b>
            ${document.type.name}
        </div>
    </#if>
    <#if document.documentNumber??>
        <div>
            <b><@label "regprofEducationDetails.experience.document.documentNumber.label"/>: </b>
            ${document.documentNumber}
        </div>
    </#if>
    <#if document.documentDate??>
        <div>
            <b><@label "regprofEducationDetails.experience.document.documentDate.label"/>: </b>
            ${(document.documentDate).format("dd.MM.yyyy")}
        </div>
    </#if>
    <#if document.institutionName??>
        <div>
            <b><@label "regprofEducationDetails.experience.document.institutionName.label"/>: </b>
            ${document.institutionName}
        </div>
    </#if>
    <#if document.workPeriods?? && document.workPeriods?size &gt; 0>
        <#list document.workPeriods as period>
            <#if period.fromDate?? && period.toDate??>
                <div>
                    <b><@label "regprofEducationDetails.experience.document.workPeriod.fromTo.label"/>: </b>
                    ${(period.fromDate).format("dd.MM.yyyy")} - ${(period.toDate).format("dd.MM.yyyy")}
                    <#if period.workDayHours?? && period.workDayHours.name??>
                        (${period.workDayHours.name})
                    </#if>
                </div>
            </#if>
        </#list>
    </#if>
</#macro>