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
    <#assign isStatusSubmitted = args[2] == "SUB" />
    <#assign titleCode = "regprofApostille.title.label" />
    <#assign showApplicantType = false />

    <table class="bordered receiptTable">
        <tbody>
        <#include "fragments/regprofApostille/regprofApostilleHeadingFragment.ftl" />
        <#include "fragments/applicantRepresentativeFragment.ftl" />
        <#include "fragments/regprof/diffQualificationNamesFragment.ftl" />
        <#include "fragments/contactAddressFragment.ftl" />
        <#include "fragments/declarationsFragment.ftl" />
        <#include "fragments/resultReceiveAndAddressFragment.ftl" />
        <#include "fragments/regprof/regprofEducationExperienceFragment.ftl" />
        <#include "fragments/documentsFragment.ftl" />
        <#include "fragments/regprofApostille/regprofApostilleFeesFragment.ftl" />
        </tbody>
    </table>
    </body>
    </html>
</#escape>