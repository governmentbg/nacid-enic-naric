import React from "react";
import ReportApplicationTypeSection from "../../views/components/common/report/filters/definition/section/applicationType/ReportApplicationTypeSection";
import ReportCommissionSection from "../../views/components/common/report/filters/definition/section/commission/ReportCommissionSection";
import ReportDocumentReceiveMethod from "../../views/components/common/report/filters/definition/section/documentReceiveMethod/ReportDocumentReceiveMethod";
import ReportApplicationUserCreatedSection from "../../views/components/common/report/filters/definition/section/applicationUserCreated/ReportApplicationUserCreatedSection";
import ReportUniversitySection from "../../views/components/common/report/filters/definition/section/university/ReportUniversitySection";
import ReportLegalApplicantSection from "../../views/components/common/report/filters/definition/section/legalApplicant/ReportLegalApplicantSection";
import ReportTrainingInstitutionSection from "../../views/components/common/report/filters/definition/section/trainingInstitution/ReportTrainingInstitutionSection";
import ReportDiplomaSpecialitySection from "../../views/components/common/report/filters/definition/section/diplomaSpeciality/ReportDiplomaSpecialitySection";
import ReportRecognizedDiplomaSpecialitySection from "../../views/components/common/report/filters/definition/section/recognizedDiplomaSpeciality/ReportRecognizedDiplomaSpecialitySection";
import ReportDiplomaOwnerSection from "../../views/components/common/report/filters/definition/section/diplomaOwner/ReportDiplomaOwnerSection";
import ReportDiplomaNameSection from "../../views/components/common/report/filters/definition/section/diplomaName/ReportDiplomaNameSection";
import { REPORT_FILTERS_DEFINITION } from "../../config/report/filters/definition";
import ReportDiplomaSection from "../../views/components/common/report/filters/definition/section/diploma/ReportDiplomaSection";
import {
  AppTypeCode,
  ReportApplicationSection,
  ReportApplicationResponsibleUserSection,
  ReportDocumentTypeSection,
  ReportServiceTypeSection,
  ReportStatusSection,
  ReportSubmissionMethodSection,
  ReportRepresentativeSection,
} from "@duosoftbg/nacid-backoffice-components";
import { REPORT_CONFIG } from "../../config/report/reportConfig";
import ReportNaturalPersonApplicantSection from "../../views/components/common/report/filters/definition/section/applicant/ReportNaturalPersonApplicantSection";

export const renderReportFilter = (id, group) => {
  const appType = AppTypeCode.RUDI;

  switch (id) {
    case REPORT_FILTERS_DEFINITION.applicationType.id:
      return <ReportApplicationTypeSection reportGroup={group} />;
    case REPORT_FILTERS_DEFINITION.commission.id:
      return <ReportCommissionSection reportGroup={group} />;
    case REPORT_FILTERS_DEFINITION.submissionMethod.id:
      return (
        <ReportSubmissionMethodSection
          reportGroup={group}
          reportFiltersDefinition={REPORT_FILTERS_DEFINITION}
          reportConfig={REPORT_CONFIG}
        />
      );
    case REPORT_FILTERS_DEFINITION.status.id:
      return (
        <ReportStatusSection
          reportGroup={group}
          appType={appType}
          reportFiltersDefinition={REPORT_FILTERS_DEFINITION}
          reportConfig={REPORT_CONFIG}
        />
      );
    case REPORT_FILTERS_DEFINITION.application.id:
      return (
        <ReportApplicationSection
          reportGroup={group}
          reportFiltersDefinition={REPORT_FILTERS_DEFINITION}
          reportConfig={REPORT_CONFIG}
        />
      );
    case REPORT_FILTERS_DEFINITION.documentType.id:
      return (
        <ReportDocumentTypeSection
          reportGroup={group}
          reportFiltersDefinition={REPORT_FILTERS_DEFINITION}
          reportConfig={REPORT_CONFIG}
          appType={appType}
        />
      );
    case REPORT_FILTERS_DEFINITION.documentReceiveMethod.id:
      return <ReportDocumentReceiveMethod reportGroup={group} />;
    case REPORT_FILTERS_DEFINITION.applicationUserCreated.id:
      return <ReportApplicationUserCreatedSection reportGroup={group} />;
    case REPORT_FILTERS_DEFINITION.applicationResponsibleUser.id:
      return (
        <ReportApplicationResponsibleUserSection
          reportGroup={group}
          reportFiltersDefinition={REPORT_FILTERS_DEFINITION}
          reportConfig={REPORT_CONFIG}
          appType={appType}
        />
      );
    case REPORT_FILTERS_DEFINITION.university.id:
      return <ReportUniversitySection reportGroup={group} />;
    case REPORT_FILTERS_DEFINITION.legalApplicant.id:
      return <ReportLegalApplicantSection reportGroup={group} />;
    case REPORT_FILTERS_DEFINITION.trainingInstitution.id:
      return <ReportTrainingInstitutionSection reportGroup={group} />;
    case REPORT_FILTERS_DEFINITION.diplomaSpeciality.id:
      return <ReportDiplomaSpecialitySection reportGroup={group} />;
    case REPORT_FILTERS_DEFINITION.recognizedDiplomaSpeciality.id:
      return <ReportRecognizedDiplomaSpecialitySection reportGroup={group} />;
    case REPORT_FILTERS_DEFINITION.naturalPersonApplicant.id:
      return <ReportNaturalPersonApplicantSection reportGroup={group} />;
    case REPORT_FILTERS_DEFINITION.diplomaOwner.id:
      return <ReportDiplomaOwnerSection reportGroup={group} />;
    case REPORT_FILTERS_DEFINITION.diplomaName.id:
      return <ReportDiplomaNameSection reportGroup={group} />;
    case REPORT_FILTERS_DEFINITION.representative.id:
      return (
        <ReportRepresentativeSection
          reportGroup={group}
          reportFiltersDefinition={REPORT_FILTERS_DEFINITION}
          reportConfig={REPORT_CONFIG}
        />
      );
    case REPORT_FILTERS_DEFINITION.serviceType.id:
      return (
        <ReportServiceTypeSection
          reportGroup={group}
          reportFiltersDefinition={REPORT_FILTERS_DEFINITION}
          reportConfig={REPORT_CONFIG}
        />
      );
    case REPORT_FILTERS_DEFINITION.diploma.id:
      return <ReportDiplomaSection reportGroup={group} />;
    default:
      return null;
  }
};
