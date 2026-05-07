<#macro universitiesData universities>
    <#escape x as x?html>
        <#if universities?? && universities?size &gt; 0>
            <h3><@label "universitiesData.title.label"></@label></h3>
            <div>
                <b><@label "university.label"></@label>:</b>
                ${universities?map(uni -> uni.name + "" +(uni.faculty??)?then(" - "+ uni.faculty, "")+(uni.universityContact??)?then(" ("+ uni.universityContact+")", ""))?join("; ")}
            </div>
        </#if>
    </#escape>
</#macro>