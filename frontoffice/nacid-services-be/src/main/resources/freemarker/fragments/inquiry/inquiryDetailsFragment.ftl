<#include "../../common/macros/label.ftl">
<#escape x as x?html>
    <#if application.inquiryDetails??>
        <tr>
            <td>
                <h3><@label "inquiry.details.title.label"></@label></h3>
                <#if application.inquiryDetails.inquiryKinds?? && application.inquiryDetails.inquiryKinds?size &gt; 0>
                    <div>
                        <b><@label "inquiry.details.kind.label"/>: </b>
                        ${application.inquiryDetails.inquiryKinds?map(kind -> resourceBundle(messages, "inquiry.details.kind."+kind?string, []))?join("; ")}
                    </div>
                </#if>
                <#if application.inquiryDetails.previousInquiryNum??>
                    <div>
                        <b><@label "inquiry.details.previousInquiryNum.label"/>: </b>
                        ${application.inquiryDetails.previousInquiryNum}
                    </div>
                </#if>
                <#if application.inquiryDetails.inquiryAim??>
                    <div>
                        <b><@label "inquiry.details.inquiryAim.label"/>: </b>
                        ${application.inquiryDetails.inquiryAim}
                    </div>
                </#if>
                <#if application.inquiryDetails.periodFrom?? && application.inquiryDetails.periodTo??>
                    <div>
                        <b><@label "inquiry.details.period.label"/>: </b>
                        ${application.inquiryDetails.periodFrom} - ${application.inquiryDetails.periodTo}
                    </div>
                </#if>
            </td>
        </tr>
    </#if>
</#escape>