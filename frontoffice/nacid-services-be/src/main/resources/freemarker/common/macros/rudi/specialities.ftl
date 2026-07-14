<#macro specialities specialities labelCode>
    <#escape x as x?html>
        <#if specialities?? && specialities?size &gt; 0>
            <div>
                <b><@label labelCode></@label>:</b>
                ${specialities?map(spec -> spec.name+((spec.originalName??)?then(" / "+spec.originalName, "")))?join("; ")}
            </div>
        </#if>
    </#escape>
</#macro>