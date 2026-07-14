<#macro representative representative applicantHasRepresentative representativeCompanyIdentifier representativeCapacity>
    <#include "naturalPerson.ftl" />

    <#escape x as x?html>
        <#if representative?? && applicantHasRepresentative>
            <h3><@label "representative.title.label"></@label></h3>
        </#if>
        <@naturalPerson representative />
        <#if representativeCompanyIdentifier?? && representativeCompanyIdentifier != "">
            <div>
                <b><@label "representative.companyIdentifier.label"></@label>:</b>
                ${representativeCompanyIdentifier}
            </div>
        </#if>
        <#if representativeCapacity?? && representativeCapacity != "">
            <div>
                <b><@label "representative.capacity.label"></@label>:</b>
                ${representativeCapacity}
            </div>
        </#if>
    </#escape>
</#macro>