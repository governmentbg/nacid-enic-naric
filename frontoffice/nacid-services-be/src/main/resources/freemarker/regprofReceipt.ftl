<#ftl encoding="UTF-8" />
<#escape x as x?html>
    <html>
    <head>
        <meta http-equiv="Content-type" content="text/html;charset=UTF-8"/>
        <#include "common/css/styles.ftl">
        <#include "common/macros/label.ftl">
        <#include "common/macros/applicant.ftl">
        <#include "common/macros/representative.ftl">
        <#include "common/macros/naturalPersonNames.ftl">
        <#include "common/macros/contactAddress.ftl">
        <#include "common/macros/declarations.ftl">
        <#include "common/macros/resultReceive.ftl">
        <#include "common/macros/receiverAddress.ftl">
        <#include "common/macros/attachments.ftl">
    </head>
    <body>
    <#assign application = args[0] />
    <#assign isStatusFinalized = args[2] == "FIN" />
    <#assign isStatusSubmitted = args[2] == "SUB" />
    <#if args?size &gt; 3 && args[3]?? >
        <#assign fees = args[3] />
    </#if>
    <#assign titleCode = "regprof.title.label" />
    <#assign resultReceiveLabelCode = "resultReceive.admAct.title.label" />
    <#assign showApplicantType = false />

    <table class="bordered receiptTable">
        <tbody>
        <#include "fragments/headingFragment.ftl" />
        <#include "fragments/applicantRepresentativeFragment.ftl" />
        <#include "fragments/regprof/diffQualificationNamesFragment.ftl" />
        <#include "fragments/contactAddressFragment.ftl" />
        <#include "fragments/declarationsFragment.ftl" />
        <#include "fragments/resultReceiveAndAddressFragment.ftl" />
        <#include "fragments/regprof/regprofEducationExperienceFragment.ftl" />
        <#include "fragments/documentsFragment.ftl" />
        <#include "fragments/feesFragment.ftl" />
        </tbody>
    </table>
    </body>
    </html>
</#escape>