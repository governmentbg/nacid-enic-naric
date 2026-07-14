<#include "../common/macros/label.ftl">
<#escape x as x?html>
    <#if fees??>
        <tr>
            <td>
                <h3><@label "fees.title.label"/></h3>
                <#if fees.forApproval?? && fees.forApproval>
                    <div>
                        <b><@label "fees.require.approval"></@label></b>
                    </div>
                <#elseif fees.fees?? && fees.fees?size &gt; 0>
                    <#list fees.fees as fee>
                        <div>
                            ${fee.feeName}: ${fee.feeAmount?string["0.00"]}${" "}<@label "fees."+fees.currencyCode></@label>
                        </div>
                    </#list>
                    <div>
                        <b><@label "fees.total"></@label>: ${fees.total?string["0.00"]}${" "}<@label "fees."+fees.currencyCode></@label></b>
                    </div>
                <#elseif fees?? && (!fees.fees?? || fees.fees?size == 0)>
                    <div>
                        <b><@label "fees.total"></@label>: 0.00</b>
                    </div>
                </#if>
            </td>
        </tr>
    </#if>
</#escape>