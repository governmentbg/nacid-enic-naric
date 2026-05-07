<#include "../../common/macros/label.ftl">
<#escape x as x?html>
    <#if application.totalFeesAmount??>
        <tr>
            <td>
                <h3><@label "fees.title.label"/></h3>
                <div>
                    <b><@label "fees.total"></@label>: ${application.totalFeesAmount?string["0.00"]}</b>${" "}
                    <#if application.feesCurrencyCode??>
                        <b><@label "fees."+application.feesCurrencyCode></@label></b>
                    </#if>
                </div>
            </td>
        </tr>
    </#if>
</#escape>