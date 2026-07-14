<#macro rudiEducation educationDetails>
    <#escape x as x?html>
        <#if educationDetails.recognitionCategory?? && educationDetails.recognitionCategory.name??>
            <div>
                <b><@label "recognitionCategory.label"></@label>:</b>
                ${educationDetails.recognitionCategory.name}
            </div>
        </#if>
        <#if educationDetails.originalGainedLevel??>
            <div>
                <b><@label "originalGainedLevel.label"></@label>:</b>
                ${educationDetails.originalGainedLevel}
            </div>
        </#if>
        <#if educationDetails.originalGainedLevelTranslated??>
            <div>
                <b><@label "originalGainedLevelTranslated.label"></@label>:</b>
                ${educationDetails.originalGainedLevelTranslated}
            </div>
        </#if>
        <#if educationDetails.gainedLevelProfGroup?? && educationDetails.gainedLevelProfGroup.name?? && educationDetails.gainedLevelProfGroup.educationArea??>
            <div>
                <b><@label "gainedLevelProfGroup.label"></@label>:</b>
                ${application.educationDetails.gainedLevelProfGroup.name} - ${application.educationDetails.gainedLevelProfGroup.educationArea.name}
            </div>
        </#if>
        <#if educationDetails.credits??>
            <div>
                <b><@label "credits.label"></@label>:</b>
                ${educationDetails.credits}
            </div>
        </#if>
        <#if educationDetails.startOfEducation?? && educationDetails.endOfEducation?? >
            <div>
                <b><@label "educationPeriod.label"></@label>:</b>
                ${educationDetails.startOfEducation} - ${educationDetails.endOfEducation}
            </div>
        </#if>
        <#if educationDetails.educationDuration?? && educationDetails.educationDurationType?? && educationDetails.educationDurationType.name??>
            <div>
                <b><@label "educationDuration.label"></@label>:</b>
                ${educationDetails.educationDuration} (${educationDetails.educationDurationType.name})
            </div>
        </#if>
        <#if educationDetails.educationForm?? && educationDetails.educationForm.name??>
            <div>
                <b><@label "educationForm.label"></@label>:</b>
                ${educationDetails.educationForm.name}
            </div>
        </#if>
        <#if educationDetails.educationFormOtherDetails??>
            <div>
                <b><@label "educationForm.other.label"></@label>:</b>
                ${educationDetails.educationFormOtherDetails}
            </div>
        </#if>
    </#escape>
</#macro>