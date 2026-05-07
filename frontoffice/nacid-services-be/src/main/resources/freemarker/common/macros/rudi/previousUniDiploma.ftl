<#macro previousUniDiploma diploma>
    <#escape x as x?html>
        <h3><@label "previousUniDiploma.title.label"></@label></h3>
        <#if diploma.universityName??>
            <div>
                <b><@label "previousUniDiploma.universityName.label"></@label>:</b>
                ${diploma.universityName}
            </div>
        </#if>
        <#if diploma.gainedLevel?? && diploma.gainedLevel.name??>
            <div>
                <b><@label "previousUniDiploma.gainedLevel.label"></@label>:</b>
                ${diploma.gainedLevel.name}
            </div>
        </#if>
        <#if diploma.speciality??>
            <div>
                <b><@label "previousUniDiploma.speciality.label"></@label>:</b>
                ${diploma.speciality}
            </div>
        </#if>
        <#if diploma.graduationYear?? && diploma.graduationYear != "">
            <div>
                <b><@label "previousUniDiploma.graduationYear.label"></@label>:</b>
                ${diploma.graduationYear}
            </div>
        </#if>
        <#if diploma.notes??>
            <div>
                <b><@label "previousUniDiploma.notes.label"></@label>:</b>
                ${diploma.notes}
            </div>
        </#if>
    </#escape>
</#macro>