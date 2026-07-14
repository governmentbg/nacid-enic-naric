<#include "../common/macros/declarations.ftl">
<#escape x as x?html>
    <#if application.applicantDetails.agreeDataUsage?? || application.applicantDetails.documentsDeclaration??>
        <tr>
            <td>
                <@declarations application.applicantDetails agreeDataUsageSpecialLabel!"agreeDataUsage.label" documentsDeclarationSpecialLabel!"documentsDeclaration.label"/>
            </td>
        </tr>
    </#if>
</#escape>