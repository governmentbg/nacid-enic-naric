<#include "../common/macros/contactAddress.ftl">
<#escape x as x?html>
    <#if application.applicantDetails.contactAddress?? >
        <tr>
            <td>
                <@contactAddress application.applicantDetails.contactAddress />
            </td>
        </tr>
    </#if>
</#escape>