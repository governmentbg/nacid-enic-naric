<#ftl encoding="UTF-8" />
<#escape x as x?html>
    <html>
    <head>
        <meta http-equiv="Content-type" content="text/html;charset=UTF-8"/>
        <#include "common/css/styles.ftl">
        <#include "common/macros/label.ftl">
        <#include "common/macros/naturalPerson.ftl" />
        <#include "common/macros/applicant.ftl">
        <#include "common/macros/representative.ftl">
        <#include "common/macros/naturalPersonNames.ftl">
        <#include "common/macros/contactAddress.ftl">
        <#include "common/macros/declarations.ftl">
        <#include "common/macros/resultReceive.ftl">
        <#include "common/macros/receiverAddress.ftl">
        <#include "common/macros/attachments.ftl">
        <#include "common/macros/rudi/universitiesData.ftl">
        <#include "common/macros/rudi/diploma.ftl">
        <#include "common/macros/rudi/rudiEducation.ftl">
        <#include "common/macros/rudi/graduationWay.ftl">
        <#include "common/macros/rudi/specialities.ftl">
        <#include "common/macros/rudi/gainedQualification.ftl">
        <#include "common/macros/rudi/educationPlaces.ftl">
    </head>
    <body>
    <#assign application = args[0] />
    <#assign isStatusFinalized = args[2] == "FIN" />
    <#assign isStatusSubmitted = args[2] == "SUB" />
    <#if args?size &gt; 3 && args[3]?? >
        <#assign fees = args[3] />
    </#if>
    <#assign titleCode = "uniChecks.title.label" />
    <#assign showApplicantType = true />
    <#assign agreeDataUsageSpecialLabel = "agreeDataUsage.uniChecks.label" />
    <#assign documentsDeclarationSpecialLabel = "documentsDeclaration.uniChecks.label" />

    <table class="bordered receiptTable">
        <tbody>
        <#include "fragments/headingFragment.ftl" />
        <#include "fragments/applicantRepresentativeFragment.ftl" />
        <#include "fragments/contactAddressFragment.ftl" />
        <#include "fragments/declarationsFragment.ftl" />
        <#include "fragments/resultReceiveAndAddressFragment.ftl" />
        <#include "fragments/uniChecks/uniChecksEducationDetailsFragment.ftl" />
        <#include "fragments/documentsFragment.ftl" />
        <#include "fragments/feesFragment.ftl" />
        </tbody>
    </table>
    </body>
    </html>
</#escape>