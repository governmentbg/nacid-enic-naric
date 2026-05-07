const ApiEndpoints = {
  common: {
    applicationSubtype: "/application-subtypes",
    bolognaCycles: "/bologna-cycle",
    nationalQualificationsFrameworks: "/national-qualifications-framework/select-by-country",
    europeanQualificationsFrameworks: "/european-qualifications-framework",
    legalNature: "/legal-reason",
    documentTypes: { all: "/document-types" },
    documentReceiveMethods: "/document-receive-methods",
    university: {
      search: "/universities/search",
      base: "/universities",
      byDiplomaType: "/universities/select-by-diploma-type",
      autocomplete: "/universities/autocomplete",
      nameById: "/universities/select-name-by-id",
      faculties: "/universities/faculties",
    },
    checkUnfilledUniversities: "/applications/data/education/check-unfilled-universities",
    competentInstitution: {
      byCountry: "/competent-institution/by-country",
      byCountries: "/competent-institution/by-country/ids",
    },
    trainingInstitution: {
      search: "/training-institution/search",
      byUniversities: "/training-institution/university/ids",
    },
    speciality: {
      base: "/speciality",
    },
    originalSpeciality: {
      base: "/original-speciality",
    },
    qualifications: {
      base: "/qualifications",
    },
    originalQualifications: {
      base: "/original-qualifications",
    },
    originalEduLevelsAutocomplete: {
      base: "/original-edu-levels",
      translation: "/original-edu-levels/translations",
    },
    person: {
      legalApplicant: {
        search: "/persons/legal-applicants/autocomplete",
      },
    },
    status: {
      base: "/application-status",
      applicationStatusesByTypes: "/application-status/by-types",
      normalStatusesByApplication: "/application-status/by-application/{applicationId}/normal",
    },
  },
  applications: {
    fo: {
      accept: {
        base: "/fo-applications/accept/{appType}",
        check: "/fo-applications/accept/{appType}/check",
        init: "/fo-applications/accept/{appType}/initialize",
      },
    },
    reception: {
      init: "/applications/reception/{appType}/initialize",
      create: "/applications/reception/{appType}/create",
    },
    dataManagement: {
      mainData: "/applications/data/main",
      eduData: "/applications/data/education",
    },
    summary: {
      sar: "/applications/summary/sar",
      udirec: "/applications/summary/udirec",
      docrec: "/applications/summary/docrec",
    },
    ras: {
      info: "/ras/info",
      register: "/ras/register",
      certPublicFiles: "/ras/certificate/public-files",
    },
    base: "/applications",
    search: "/applications/search",
    generateReport: "/applications/report-generation",
    exists: "/applications/{id}/exists/{appSubType}",
    commissionMembersBase: "/applications/commission-members",
    commissionMemberStatementsBase: "/applications/commission-member-statements",
    commissionMembers: "/applications/commission-members/by-application/{applicationId}",
    commissionMemberStatements: "/applications/commission-member-statements/by-application/{applicationId}",
    commissionMember: "/applications/commission-members/member/{id}",
    commissionMemberStatement: "/applications/commission-member-statements/statement/{id}",
    commissionMemberSave: "/applications/commission-members/save",
    commissionMemberStatementSave: "/applications/commission-member-statements/save",
    similarDiplomas: "/applications/similar-diplomas",
    attachedDocs: {
      generateGlobalReport: "/applications/attachments/generate-global-report",
    },
    status: {
      base: "/applications/data/status",
      initialStatusExamination: "/applications/data/status/initial-data-examination/{applicationId}",
      statusData: "/applications/data/status/{applicationId}",
    },
    uniExamination: {
      base: "/applications/data/status/uni-exam",
      uniExaminationData: "/applications/data/status/uni-exam/{applicationId}",
      uniExaminationSubsectionData: "/applications/data/status/uni-exam/{applicationId}/exam/{uniExaminationId}",
    },
    programExam: {
      base: "/applications/data/status/program-exam",
      programExamData: "/applications/data/status/program-exam/{applicationId}",
    },
    diplomaExam: {
      base: "/applications/data/status/diploma-exam",
      diplomaExamData: "/applications/data/status/diploma-exam/{applicationId}",
    },
    trainingLocationExam: {
      base: "/applications/data/status/training-location-exam",
      trainingLocationExamData: "/applications/data/status/training-location-exam/{applicationId}",
      universitiesData: "/applications/data/status/training-location-exam/universities/{applicationId}",
    },
    core: {
      autocomplete: {
        createdUsers: "/applications/by-type/{applicationType}/autocomplete-created-user",
        responsibleUsers: "/applications/by-type/{applicationType}/autocomplete-responsible-user",
      },
    },
  },
  commissionCalendar: {
    base: "/commission-calendars",
    mainDataSection: "/commission-calendars/main-data",
    fullNumber: "/commission-calendars/{id}/full-number",
    search: "/commission-calendars/search",
    exists: "/commission-calendars/{id}/exists",
    editPage: "/nacid-backoffice-rudi/commission-calendars/edit/",
    getProcessData: "/commission-calendars/process-data",
    saveProcessData: "/commission-calendars/process-data/save",
    getProtocol: "/commission-calendars/protocol?calendarId={calendarId}",
    getProtocols: "/commission-calendars/protocols?calendarId={calendarId}",
    getSecretary: "/commission-calendars/secretary?calendarId={calendarId}",
    updateProtocol: "/commission-calendars/protocol/{calendarId}",
    updateProtocols: "/commission-calendars/protocols/{calendarId}",
    transferMissingAbdocsDocuments: "/commission-calendars/transfer-missing-abdocs-documents/{calendarId}",
  },
  commissionApplications: {
    getIdsByCalendarId: "/commission-applications/ids/calendar/{calendarId}",
    getDataByIds: "/commission-applications/ids",
    getDataByIdsAndCalendarId: "/commission-applications/by-calendar/ids",
    saveApplications: "/commission-applications/save",
  },
  commissionParticipations: {
    getMembersByCalendarId: "/commission-participations/members/calendar/{calendarId}",
    getMembersByIds: "/commission-participations/members",
    saveMembers: "/commission-participations/save",
  },
  commissionMembers: {
    base: "/commission-members",
    search: "/commission-members/search",
    autocomplete: "/commission-members/autocomplete",
    viewMember: "/nacid-backoffice-rudi/commission-members/view?id={memberId}",
  },
  profGroups: {
    base: "/prof-group",
    search: "/prof-group/search",
  },
  applicationRecognizedSpecialities: {
    base: "/application-recognized-specialities",
  },
  applicationRecognizedDetails: {
    base: "/application-recognized-details",
  },
  report: {
    base: "/report",
    commonReport: "/report/common-report",
    generateCommonReport: "/report/common-report-generation",
    commissionReport:
      "/report/generate-commission-report?template={template}&reportType={reportType}&commissionCalendarId={commissionCalendarId}",
  },
  uniExamination: {
    base: "/university-examination",
    byUniversity: "/university-examination/by-university/{universityId}",
  },
  // TODO: NACIDSE-16
  gradingScale: {
    countries: "/countries",
    gradingScales: "/grading-scales",
    subject: "/school-subjects",
    calculate: "/equalization",
    scaleInfo: "/info",
    pdf: "/pdf",
  },
};

export { ApiEndpoints };
