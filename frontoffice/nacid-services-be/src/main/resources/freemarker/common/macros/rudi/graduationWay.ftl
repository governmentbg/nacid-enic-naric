<#macro graduationWay graduationWay graduationWayOtherDetails graduationWayLabel graduationWayOtherDetailsLabel>
    <#escape x as x?html>
        <#if graduationWay?? && graduationWay?size &gt; 0>
            <div>
                <b><@label graduationWayLabel></@label>:</b>
                <#assign ways = graduationWay?map(gw -> gw.name) />
                ${ways?join("; ")}
            </div>
        </#if>
        <#if graduationWayOtherDetails?? && graduationWayOtherDetails != "">
            <div>
                <b><@label graduationWayOtherDetailsLabel></@label>:</b>
                ${graduationWayOtherDetails}
            </div>
        </#if>
    </#escape>
</#macro>