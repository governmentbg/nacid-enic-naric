import qs from "qs";
import { ApiEndpoints } from "./endpoints";
import {
  AppType,
  axiosClientCore,
  axiosClientRudi,
  selectAppTypeAndSubtypeByAppTypeEnum,
} from "@duosoftbg/nacid-backoffice-components";
import { RudiApplication } from "../../utils/constants";
// TODO: NACIDSE-16

const commonFilterFunction = async (url, params, withIndeces = true) => {
  const response = await axiosClientRudi.get(url, {
    params,
    paramsSerializer: (params) => {
      return qs.stringify(params, { indices: withIndeces });
    },
  });
  return response.data;
};

const getCommonCoreAutocompleteResponse = async (url, params) => {
  return await axiosClientCore.get(url, {
    params,
    paramsSerializer: (params) => {
      return qs.stringify(params);
    },
  });
};

export const generateGlobalReport = (globalReportData) => async () => {
  const response = await axiosClientRudi.post(
    ApiEndpoints.applications.attachedDocs.generateGlobalReport,
    globalReportData,
  );
  return response.data;
};

//COMMON
export const getRudiApplicationSubtypes = () => async () => {
  const response = await axiosClientCore.get(
    `${ApiEndpoints.common.applicationSubtype}/bytype?applicationType=${RudiApplication.rudiApplicationType}`,
  );
  return response.data;
};

export const getCommissionCalendarApplicationStatuses = () => async () => {
  const response = await axiosClientCore.get(
    `${ApiEndpoints.common.status.applicationStatusesByTypes}?applicationType=${RudiApplication.rudiApplicationType}&applicationSubType=${RudiApplication.rudiUDIApplicationSybType}`,
  );
  return response.data;
};

export const getLegalReasonByApplicationAndStatus = (applicationId, status) => async () => {
  const response = await axiosClientCore.get(
    `${ApiEndpoints.common.legalNature}/by-application-and-status/${applicationId}/${status}`,
  );
  return response.data;
};

export const getLegalReasonByMemberPositionCode = (code) => async () => {
  const response = await axiosClientCore.get(`${ApiEndpoints.common.legalNature}/by-member-position-code/${code}`);
  return response.data;
};

export const getCommissionApplicationStatuses = () => async () => {
  const response = await axiosClientCore.get(`${ApiEndpoints.common.status.base}/commission`);
  return response.data;
};

export const getDocumentReceiveMethods = () => async () => {
  const response = await axiosClientCore.get(`${ApiEndpoints.common.documentReceiveMethods}`);
  return response.data;
};

export const getSpecialitiesAutocomplete = (inputValue, page, resultsLimit) => async () => {
  const response = await getCommonCoreAutocompleteResponse(ApiEndpoints.common.speciality.base, {
    name: inputValue,
    page: page,
    pageSize: resultsLimit,
  });

  return response.data.map(function (type) {
    return { id: type, name: type };
  });
};

export const getApplicationRecognizedQualifications = () => async () => {
  const response = await axiosClientRudi.get(ApiEndpoints.applicationRecognizedDetails.base);
  return response.data;
};

export const getOriginalSpecialitiesAutocomplete = (inputValue, page, resultsLimit) => async () => {
  const response = await getCommonCoreAutocompleteResponse(ApiEndpoints.common.originalSpeciality.base, {
    name: inputValue,
    page: page,
    pageSize: resultsLimit,
  });

  return response.data.map(function (type) {
    return { id: type, name: type };
  });
};

export const getQualificationsAutocomplete = (inputValue, page, resultsLimit) => async () => {
  const response = await getCommonCoreAutocompleteResponse(ApiEndpoints.common.qualifications.base, {
    name: inputValue,
    page: page,
    pageSize: resultsLimit,
  });

  return response.data.map(function (type) {
    return { id: type, name: type };
  });
};

export const getOriginalQualificationsAutocomplete = (inputValue, page, resultsLimit) => async () => {
  const response = await getCommonCoreAutocompleteResponse(ApiEndpoints.common.originalQualifications.base, {
    name: inputValue,
    page: page,
    pageSize: resultsLimit,
  });

  return response.data.map(function (type) {
    return { id: type, name: type };
  });
};

export const getQualificationsFreeSolo = (inputValue) => async () => {
  const response = await getCommonCoreAutocompleteResponse(ApiEndpoints.common.qualifications.base, {
    name: inputValue,
    page: 0,
    pageSize: 100,
  });
  return response.data;
};

export const getOriginalQualificationsFreeSolo = (inputValue) => async () => {
  const response = await getCommonCoreAutocompleteResponse(ApiEndpoints.common.originalQualifications.base, {
    name: inputValue,
    page: 0,
    pageSize: 100,
  });
  return response.data;
};

export const getSpecialitiesFreeSolo = (inputValue) => async () => {
  const response = await getCommonCoreAutocompleteResponse(ApiEndpoints.common.speciality.base, {
    name: inputValue,
    page: 0,
    pageSize: 100,
  });
  return response.data;
};

export const getOriginalSpecialitiesFreeSolo = (inputValue) => async () => {
  const response = await getCommonCoreAutocompleteResponse(ApiEndpoints.common.originalSpeciality.base, {
    name: inputValue,
    page: 0,
    pageSize: 100,
  });
  return response.data;
};

export const getRudiApplicationUsersCreated = () => async () => {
  const url = ApiEndpoints.applications.core.autocomplete.createdUsers.replace(
    "{applicationType}",
    RudiApplication.rudiApplicationType,
  );

  const response = await axiosClientCore.get(url);
  return response.data.map(function (type) {
    return { id: type.username, name: type.fullName, isActive: type.isActive };
  });
};

export const getCommissionApplicationIdsByCalendarId = (calendarId) => async () => {
  const url = ApiEndpoints.commissionApplications.getIdsByCalendarId.replace("{calendarId}", calendarId);
  const response = await axiosClientRudi.get(url);
  return response.data;
};

export const getApplicationCommissionMembers = (applicationId) => async () => {
  const url = ApiEndpoints.applications.commissionMembers.replace("{applicationId}", applicationId);
  const response = await axiosClientRudi.get(url);
  return response.data;
};

export const getApplicationCommissionMemberStatements = (applicationId) => async () => {
  const url = ApiEndpoints.applications.commissionMemberStatements.replace("{applicationId}", applicationId);
  const response = await axiosClientRudi.get(url);
  return response.data;
};

export const getApplicationCommissionMember = (id) => async () => {
  const url = ApiEndpoints.applications.commissionMember.replace("{id}", id);
  const response = await axiosClientRudi.get(url);
  return response.data;
};

export const getApplicationCommissionMemberStatement = (id) => async () => {
  const url = ApiEndpoints.applications.commissionMemberStatement.replace("{id}", id);
  const response = await axiosClientRudi.get(url);
  return response.data;
};

export const getCommissionMembersByCalendarId = (calendarId) => async () => {
  const url = ApiEndpoints.commissionParticipations.getMembersByCalendarId.replace("{calendarId}", calendarId);
  const response = await axiosClientRudi.get(url);
  return response.data;
};

export const getCommissionMembersByIds = (ids) => async () => {
  return commonFilterFunction(ApiEndpoints.commissionParticipations.getMembersByIds, { ids }, false);
};

export const getCommissionMember = (id: string) => async () => {
  const response = await axiosClientRudi.get(`${ApiEndpoints.commissionMembers.base}/${id}`);
  return response.data;
};

export const getCommissionApplications = (ids, sortColumn, ascOrder) => async () => {
  return commonFilterFunction(ApiEndpoints.commissionApplications.getDataByIds, { ids, sortColumn, ascOrder }, false);
};

export const getCommissionApplicationsByIdsAndCalendarId = (ids, calendarId, sortColumn, ascOrder) => async () => {
  return commonFilterFunction(
    ApiEndpoints.commissionApplications.getDataByIdsAndCalendarId,
    { ids, calendarId, sortColumn, ascOrder },
    false,
  );
};
export const searchCommissionMembers = (params) => async () => {
  const response = await axiosClientRudi.post(ApiEndpoints.commissionMembers.search, params);
  return response.data;
};

export const getProfGroups = () => async () => {
  const response = await axiosClientCore.get(ApiEndpoints.profGroups.base);
  return response.data;
};

export const selectProfGroup = (id) => async () => {
  const response = await axiosClientCore.get(`${ApiEndpoints.profGroups.base}/${id}`);
  return response.data;
};

export const getProfGroupsWithAreas = () => async () => {
  const response = await axiosClientCore.get(ApiEndpoints.profGroups.base);
  return response.data.map(function (group) {
    let educationArea = group?.educationArea?.name;
    return {
      id: group.id,
      name: educationArea ? educationArea + " - " + group.name : group.name,
      isActive: group.isActive,
    };
  });
};

export const getRudiResponsibleUsers = () => async () => {
  const url = ApiEndpoints.applications.core.autocomplete.responsibleUsers.replace(
    "{applicationType}",
    RudiApplication.rudiApplicationType,
  );

  const response = await axiosClientCore.get(url);
  return response.data.map(function (type) {
    return { id: type.username, name: type.fullName, isActive: type.isActive };
  });
};

export const searchApplications = (params) => async () => {
  const response = await axiosClientRudi.post(ApiEndpoints.applications.search, params);
  return response.data;
};

export const generateApplicationsReport = (params) => async () => {
  const response = await axiosClientRudi.post(ApiEndpoints.applications.generateReport, params, {
    responseType: "blob",
  });
  return response;
};

export const generateCommonReport = (params) => async () => {
  const response = await axiosClientRudi.post(ApiEndpoints.report.generateCommonReport, params, {
    responseType: "blob",
  });
  return response;
};

export const searchCommissionCalendars = (params) => async () => {
  return commonFilterFunction(ApiEndpoints.commissionCalendar.search, params);
};

export const getCalendarProcessData = (calendarId, applicationId) => async () => {
  return commonFilterFunction(ApiEndpoints.commissionCalendar.getProcessData, { calendarId, applicationId });
};

export const getCalendarProtocol = (calendarId) => async () => {
  const response = await axiosClientRudi.get(
    ApiEndpoints.commissionCalendar.getProtocol.replace("{calendarId}", calendarId),
  );
  return response.data;
};

export const getCalendarProtocols = (calendarId) => async () => {
  const response = await axiosClientRudi.get(
    ApiEndpoints.commissionCalendar.getProtocols.replace("{calendarId}", calendarId),
  );
  return response.data;
};

export const getSecretary = (calendarId) => async () => {
  const response = await axiosClientRudi.get(
    ApiEndpoints.commissionCalendar.getSecretary.replace("{calendarId}", calendarId),
  );
  return response.data;
};

export const selectCommissionCalendar = (id) => async () => {
  const response = await axiosClientRudi.get(`${ApiEndpoints.commissionCalendar.mainDataSection}/${id}`);
  return response.data;
};

export const createCommissionCalendar = (calendar) => async () => {
  const response = await axiosClientRudi.post(ApiEndpoints.commissionCalendar.mainDataSection, calendar);
  return response.data;
};

export const saveCalendarApplications = (calendarId, applicationIds) => async () => {
  const response = await axiosClientRudi.post(ApiEndpoints.commissionApplications.saveApplications, {
    calendarId: calendarId,
    applicationIds: applicationIds,
  });
  return response.data;
};

export const saveCalendarProcessData = (processData) => async () => {
  const response = await axiosClientRudi.post(ApiEndpoints.commissionCalendar.saveProcessData, processData);
  return response.data;
};

export const saveCalendarMembers = (calendarId, members, secretary) => async () => {
  const response = await axiosClientRudi.post(ApiEndpoints.commissionParticipations.saveMembers, {
    calendarId,
    participations: members,
    secretary,
  });
  return response.data;
};

export const updateCommissionCalendar = (calendar) => async () => {
  const response = await axiosClientRudi.put(ApiEndpoints.commissionCalendar.mainDataSection, calendar);
  return response.data;
};

export const updateCalendarProtocol = (calendarId, protocol) => async () => {
  const response = await axiosClientRudi.put(
    ApiEndpoints.commissionCalendar.updateProtocol.replace("{calendarId}", calendarId),
    protocol,
  );
  return response.data;
};

export const updateCalendarProtocols = (calendarId, protocol, scannedProtocol) => async () => {
  const response = await axiosClientRudi.put(
    ApiEndpoints.commissionCalendar.updateProtocols.replace("{calendarId}", calendarId),
    { commissionProtocol: protocol, scannedCommissionProtocol: scannedProtocol },
  );
  return response.data;
};
export const selectRudiApplicationById = (id) => async () => {
  return await axiosClientRudi.get(`${ApiEndpoints.applications.base}/${id}`);
};

export const selectApplicationSummary = (id, appType: AppType) => async () => {
  let url = "";
  switch (appType) {
    case AppType.SAR_APPLICATION: {
      url = ApiEndpoints.applications.summary.sar;
      break;
    }
    case AppType.UDIREC_APPLICATION: {
      url = ApiEndpoints.applications.summary.udirec;
      break;
    }
    case AppType.DOCREC_APPLICATION: {
      url = ApiEndpoints.applications.summary.docrec;
      break;
    }
  }

  const response = await axiosClientRudi.get(`${url}/${id}`);
  return response.data;
};

export const checkIfRudiApplicationExists = (id, appType: AppType) => async () => {
  const types = selectAppTypeAndSubtypeByAppTypeEnum(appType);

  const url = ApiEndpoints.applications.exists.replace("{id}", id).replace("{appSubType}", types.appSubType);
  const response = await axiosClientRudi.get(url);
  return response.data;
};

export const checkIfCommissionCalendarExist = (id) => async () => {
  const url = ApiEndpoints.commissionCalendar.exists.replace("{id}", id);
  const response = await axiosClientRudi.get(url);
  return response.data;
};
export const getCommissionCalendarFullNumber = (id) => async () => {
  const url = ApiEndpoints.commissionCalendar.fullNumber.replace("{id}", id);
  const response = await axiosClientRudi.get(url);
  return response.data;
};
export const deleteCalendar = (id: string) => async () => {
  const response = await axiosClientRudi.delete(`${ApiEndpoints.commissionCalendar.base}/${id}`);
  return response.data;
};

export const deleteApplicationExpert = (id: string) => async () => {
  const response = await axiosClientRudi.delete(`${ApiEndpoints.applications.commissionMembersBase}/${id}`);
  return response.data;
};

export const deleteApplicationExpertStatement = (id: string) => async () => {
  const response = await axiosClientRudi.delete(`${ApiEndpoints.applications.commissionMemberStatementsBase}/${id}`);
  return response.data;
};

export const selectAppMainData = (id) => async () => {
  const response = await axiosClientRudi.get(`${ApiEndpoints.applications.dataManagement.mainData}/${id}`);
  return response.data;
};

export const updateAppMainData = (id, bodyData) => async () => {
  const response = await axiosClientRudi.patch(`${ApiEndpoints.applications.dataManagement.mainData}/${id}`, bodyData);
  return response.data;
};

export const selectAppEduData = (id) => async () => {
  const response = await axiosClientRudi.get(`${ApiEndpoints.applications.dataManagement.eduData}/${id}`);
  return response.data;
};

export const selectAppEduDataNotes = (id) => async () => {
  const response = await axiosClientRudi.get(`${ApiEndpoints.applications.dataManagement.eduData}/notes/${id}`);
  return response.data;
};

export const updateAppEduData = (id, bodyData) => async () => {
  const response = await axiosClientRudi.patch(`${ApiEndpoints.applications.dataManagement.eduData}/${id}`, bodyData);
  return response.data;
};

export const updateStatusData = (id, bodyData) => async () => {
  const response = await axiosClientRudi.patch(`${ApiEndpoints.applications.status.base}/${id}`, bodyData);
  return response.data;
};

export const saveApplicationCommissionMember = (id, bodyData) => async () => {
  const response = await axiosClientRudi.post(`${ApiEndpoints.applications.commissionMemberSave}/${id}`, bodyData);
  return response.data;
};

export const transferMissingAbdocsDocuments = (calendarId) => async () => {
  const response = await axiosClientRudi.post(
    ApiEndpoints.commissionCalendar.transferMissingAbdocsDocuments.replace("{calendarId}", calendarId),
  );
  return response.data;
};

export const saveApplicationCommissionMemberStatement = (id, bodyData) => async () => {
  const response = await axiosClientRudi.post(
    `${ApiEndpoints.applications.commissionMemberStatementSave}/${id}`,
    bodyData,
  );
  return response.data;
};

export const getUniversityByBgNameWithAdditionalParamsAutocomplete =
  (inputValue, page, resultsLimit, additionalParams) => async () => {
    const response = await commonFilterFunction(ApiEndpoints.common.university.search, {
      ...additionalParams,
      bgName: inputValue,
      page: page,
      pageSize: resultsLimit,
    });

    return response.content.map(function (type) {
      return { id: type.id, name: type.bgName, orgName: type.orgName, isActive: type.isActive };
    });
  };

export const getUniversityByOrgNameWithAdditionalParamsAutocomplete =
  (inputValue, page, resultsLimit, additionalParams) => async () => {
    const response = await commonFilterFunction(ApiEndpoints.common.university.search, {
      ...additionalParams,
      orgName: inputValue,
      page: page,
      pageSize: resultsLimit,
    });

    return response.content.map(function (type) {
      return { id: type.id, name: type.bgName, orgName: type.orgName, isActive: type.isActive };
    });
  };

export const getLegalApplicantsWithAdditionalParamsAutocomplete =
  (inputValue, page, resultsLimit, additionalParams) => async () => {
    const response = await axiosClientCore.post(ApiEndpoints.common.person.legalApplicant.search, {
      ...additionalParams,
      legalName: inputValue,
      page: page,
      pageSize: resultsLimit,
    });

    return response.data;
  };

export const getTrainingInstitutionWithAdditionalParamsAutocomplete =
  (inputValue, page, resultsLimit, additionalParams) => async () => {
    const response = await commonFilterFunction(ApiEndpoints.common.trainingInstitution.search, {
      ...additionalParams,
      name: inputValue,
      page: page,
      pageSize: resultsLimit,
    });

    return response.content;
  };

export const selectUniversityOptionById = (id) => async () => {
  const response = await axiosClientRudi.get(`${ApiEndpoints.common.university.base}/${id}`);
  return { id: response.data.id, name: response.data.bgName, nameEn: response.data.orgName };
};

export const selectUniversityById = (id) => async () => {
  const response = await axiosClientRudi.get(`${ApiEndpoints.common.university.base}/${id}`);
  return response.data;
};

export const searchUniversities = (params) => async () => {
  params.page = 0;
  params.pageSize = 50;
  if (params?.country?.id) params.countryCode = params.country.id;
  const response = await commonFilterFunction(ApiEndpoints.common.university.search, params);
  return response.content.map(function (uni) {
    return { id: uni.id, bgName: uni.bgName, orgName: uni.orgName, country: uni.country };
  });
};

export const selectCompetentInstitutionByCountry = (id) => async () => {
  const response = await axiosClientRudi.get(`${ApiEndpoints.common.competentInstitution.byCountry}/${id}`);
  return response?.data;
};

export const selectCompetentInstitutionByCountries = (ids) => async () => {
  return commonFilterFunction(ApiEndpoints.common.competentInstitution.byCountries, { ids }, false);
};

export const rudiCommonReport = (searchFilters) => async () => {
  const response = await axiosClientRudi.post(ApiEndpoints.report.commonReport, searchFilters);
  return response.data;
};

export const downloadCommissionReport = (template, reportType, commissionCalendarId) => async () => {
  return axiosClientCore.get(
    ApiEndpoints.report.commissionReport
      .replace("{template}", template)
      .replace("{reportType}", reportType)
      .replace("{commissionCalendarId}", commissionCalendarId),
    {
      responseType: "blob",
    },
  );
};

export const getUniversitiesAutocomplete = (inputValue, page, resultsLimit) => async () => {
  return commonFilterFunction(ApiEndpoints.common.university.autocomplete, {
    bgName: inputValue,
    page: page,
    pageSize: resultsLimit,
  });
};

export const getCommissionMembersAutocomplete = (inputValue, page, resultsLimit) => async () => {
  const response = await axiosClientRudi.post(ApiEndpoints.commissionMembers.autocomplete, {
    fullName: inputValue,
    page: page,
    pageSize: resultsLimit,
  });

  return response.data;
};

export const examineStatusInitialData = (applicationId) => async () => {
  const response = await axiosClientRudi.get(
    ApiEndpoints.applications.status.initialStatusExamination.replace("{applicationId}", applicationId),
  );

  return response.data;
};

export const getApplicationStatusData = (applicationId) => async () => {
  const response = await axiosClientRudi.get(
    ApiEndpoints.applications.status.statusData.replace("{applicationId}", applicationId),
  );

  return response.data;
};

export const getApplicationUniExaminationData = (applicationId) => async () => {
  const response = await axiosClientRudi.get(
    ApiEndpoints.applications.uniExamination.uniExaminationData.replace("{applicationId}", applicationId),
  );

  return response.data;
};

export const updateApplicationUniExaminationData = (id, bodyData) => async () => {
  const response = await axiosClientRudi.patch(`${ApiEndpoints.applications.uniExamination.base}/${id}`, bodyData);
  return response.data;
};

export const getUniversityExaminations = (universityId) => async () => {
  const response = await axiosClientRudi.get(
    ApiEndpoints.uniExamination.byUniversity.replace("{universityId}", universityId),
  );

  return response.data;
};

export const getUniversityExaminationSubsectionData = (applicationId, uniExaminationId) => async () => {
  const response = await axiosClientRudi.get(
    ApiEndpoints.applications.uniExamination.uniExaminationSubsectionData
      .replace("{applicationId}", applicationId)
      .replace("{uniExaminationId}", uniExaminationId),
  );

  return response.data;
};

export const getUniversityExamination = (uniExaminationId) => async () => {
  const response = await axiosClientRudi.get(`${ApiEndpoints.uniExamination.base}/${uniExaminationId}`);
  return response.data;
};

export const getAppProgramExamData = (applicationId) => async () => {
  const response = await axiosClientRudi.get(
    ApiEndpoints.applications.programExam.programExamData.replace("{applicationId}", applicationId),
  );

  return response.data;
};

export const saveAppProgramExamData = (id, bodyData) => async () => {
  const response = await axiosClientRudi.patch(`${ApiEndpoints.applications.programExam.base}/${id}`, bodyData);
  return response.data;
};

export const getAppDiplomaExamData = (applicationId) => async () => {
  const response = await axiosClientRudi.get(
    ApiEndpoints.applications.diplomaExam.diplomaExamData.replace("{applicationId}", applicationId),
  );

  return response.data;
};

export const saveAppDiplomaExamData = (id, bodyData) => async () => {
  const response = await axiosClientRudi.patch(`${ApiEndpoints.applications.diplomaExam.base}/${id}`, bodyData);
  return response.data;
};

export const getAppTrainingLocationExamData = (applicationId) => async () => {
  const response = await axiosClientRudi.get(
    ApiEndpoints.applications.trainingLocationExam.trainingLocationExamData.replace("{applicationId}", applicationId),
  );

  return response.data;
};

export const saveAppTrainingLocationExamData = (id, bodyData) => async () => {
  const response = await axiosClientRudi.patch(
    `${ApiEndpoints.applications.trainingLocationExam.base}/${id}`,
    bodyData,
  );
  return response.data;
};

export const getTrainingLocationExamUniversitiesData = (applicationId) => async () => {
  const response = await axiosClientRudi.get(
    ApiEndpoints.applications.trainingLocationExam.universitiesData.replace("{applicationId}", applicationId),
  );

  return response.data;
};

export const getTrainingInstitutionsByIds = (ids) => async () => {
  return commonFilterFunction(ApiEndpoints.common.trainingInstitution.byUniversities, { ids }, false);
};

const replaceAppTypeInBaseUrl = (appType, baseUrl) => {
  switch (appType) {
    case AppType.SAR_APPLICATION: {
      return baseUrl.replace("{appType}", "sar");
    }
    case AppType.UDIREC_APPLICATION: {
      return baseUrl.replace("{appType}", "udirec");
    }
    case AppType.DOCREC_APPLICATION: {
      return baseUrl.replace("{appType}", "docrec");
    }
  }

  return baseUrl;
};

export const initializeReception = (appType: AppType) => async () => {
  let url = replaceAppTypeInBaseUrl(appType, ApiEndpoints.applications.reception.init);
  const response = await axiosClientRudi.get(url);
  return response.data;
};

export const initializeFoAppsAcceptForm = (appType: AppType, foAppId) => async () => {
  let url = `${replaceAppTypeInBaseUrl(appType, ApiEndpoints.applications.fo.accept.init)}/${foAppId}`;
  const response = await axiosClientRudi.get(url);
  return response.data;
};

export const acceptFoApplication = (appType: AppType, foAppId, formData) => async () => {
  let url = `${replaceAppTypeInBaseUrl(appType, ApiEndpoints.applications.fo.accept.base)}/${foAppId}`;
  const response = await axiosClientRudi.post(url, formData);
  return response.data;
};

export const checkIfFoAppIsForAcception = (appType, foAppId) => async () => {
  let url = `${replaceAppTypeInBaseUrl(appType, ApiEndpoints.applications.fo.accept.check)}/${foAppId}`;
  const response = await axiosClientRudi.get(url);
  return response.data;
};

export const createReception = (appType: AppType, formData) => async () => {
  let url = replaceAppTypeInBaseUrl(appType, ApiEndpoints.applications.reception.create);
  const response = await axiosClientRudi.post(url, formData);
  return response.data;
};

export const getOriginalEduLevelsAutocomplete = (inputValue, page, resultsLimit) => async () => {
  const response = await getCommonCoreAutocompleteResponse(ApiEndpoints.common.originalEduLevelsAutocomplete.base, {
    name: inputValue,
    page: page,
    pageSize: resultsLimit,
  });

  return response.data.map(function (type) {
    return { id: type, name: type };
  });
};

export const getOriginalEduLevelsFreeSolo = (inputValue) => async () => {
  const response = await getCommonCoreAutocompleteResponse(ApiEndpoints.common.originalEduLevelsAutocomplete.base, {
    name: inputValue,
    page: 0,
    pageSize: 100,
  });
  return response.data;
};

export const getOriginalEduLevelTranslationsFreeSolo = (inputValue) => async () => {
  const response = await getCommonCoreAutocompleteResponse(
    ApiEndpoints.common.originalEduLevelsAutocomplete.translation,
    {
      name: inputValue,
      page: 0,
      pageSize: 100,
    },
  );
  return response.data;
};

export const selectDocrecRasInfo = (id) => async () => {
  const response = await axiosClientRudi.get(`${ApiEndpoints.applications.ras.info}/${id}`);
  return response.data;
};

export const transferApplicationToRAS = (id, formData) => async () => {
  const response = await axiosClientRudi.post(`${ApiEndpoints.applications.ras.register}/${id}`, formData);
  return response.data;
};

export const getSimilarDiplomas =
  (
    applicationId,
    diplomaDate,
    countryName,
    eduLevel,
    originalEduLevel,
    civilId,
    ownerFirstName,
    ownerLastName,
    birthDate,
    birthCountry,
    diplomaOwnerEan,
  ) =>
  async () => {
    return commonFilterFunction(ApiEndpoints.applications.similarDiplomas, {
      applicationId,
      diplomaDate,
      countryName,
      eduLevel,
      originalEduLevel,
      civilId,
      ownerFirstName,
      ownerLastName,
      birthDate,
      birthCountry,
      diplomaOwnerEan,
    });
  };

export const selectRasCertificatePublicFiles = (id) => async () => {
  const response = await axiosClientRudi.get(`${ApiEndpoints.applications.ras.certPublicFiles}/${id}`);
  return response.data;
};

export const getUniversityFaculties = (universityId) => async () => {
  const response = await axiosClientRudi.get(ApiEndpoints.common.university.faculties, {
    params: { universityId: universityId },
    paramsSerializer: (params) => {
      return qs.stringify(params);
    },
  });
  return response.data.map(function (dt) {
    return { value: dt.id, text: dt.name };
  });
};

export const checkEduDataUnfilledUniversities = (id) => async () => {
  const response = await axiosClientRudi.get(`${ApiEndpoints.common.checkUnfilledUniversities}/${id}`);
  return response.data;
};

// TODO: NACIDSE-16
export const getCountryData = () => async () => {
  const response = await axiosClientRudi.get(ApiEndpoints.gradingScale.countries);
  return response.data;
};

// TODO: NACIDSE-16
export const getSubjectAutocomplete = (name, page, pageSize) => async () => {
  const response = await axiosClientRudi.get(`${ApiEndpoints.gradingScale.subject}`, {
    params: {
      name: name,
      page,
      pageSize,
    },
  });
  return response.data.map((c) => ({
    id: c.id,
    name: c.subjectBg,
    isActive: true,
  }));
};

// TODO: NACIDSE-16
export const getGradeData = (country, year) => async () => {
  const response = await axiosClientRudi.get(
    `${ApiEndpoints.gradingScale.gradingScales}/${country}${year ? "?year=" + year : ""}`,
  );
  return response.data.map((c) => ({
    id: c.id,
    name: c.scaleName,
    isActive: true,
  }));
};

// TODO: NACIDSE-16
export const calculateGrade = (data) => async () => {
  const response = await axiosClientRudi.post(`${ApiEndpoints.gradingScale.calculate}`, data);
  return response.data;
};

// TODO: NACIDSE-16
export const getGradeScaleInfo = (scaleId) => async () => {
  const response = await axiosClientRudi.get(`${ApiEndpoints.gradingScale.scaleInfo}/${scaleId}`);
  return response.data;
};

// TODO: NACIDSE-16
export const getDiplomaPdf = (data) => async () => {
  return await axiosClientRudi.post(`${ApiEndpoints.gradingScale.pdf}`, data, {
    responseType: "blob",
  });
};
