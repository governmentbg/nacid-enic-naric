import { combineReducers } from "@reduxjs/toolkit";
import commissionCalendarStatusesReducer from "./commissionCalendarStatuses";
import applicationStatusesReducer from "./applicationStatuses";
import sarServicesReducer from "./sarServices";
import legalNaturesReducer from "./legalNatures";
import applicationRecognizedQualificationsReducer from "./applicationRecognizedQualifications";
import commissionApplicationStatusesReducer from "./commissionApplicationStatuses";
import commissionCalendarApplicationsStatusesReducer from "./commissionCalendarApplicationsStatuses";
import profGroupsReducer from "./profGroups";
import profGroupsWithAreasReducer from "./profGroupsWithAreas";
import uniExamTrainingLocationsReducer from "./uniExamTrainingLocations";
import trainingProgramTypesReducer from "./trainingProgramTypes";
// TODO: NACIDSE-16
import countryDataReducer from "./countryData";

const appDataReducers = combineReducers({
  commissionCalendarStatuses: commissionCalendarStatusesReducer,
  applicationStatuses: applicationStatusesReducer,
  applicationRecognizedQualifications: applicationRecognizedQualificationsReducer,
  sarServices: sarServicesReducer,
  legalNatures: legalNaturesReducer,
  profGroups: profGroupsReducer,
  profGroupsWithAreas: profGroupsWithAreasReducer,
  commissionApplicationStatuses: commissionApplicationStatusesReducer,
  commissionCalendarApplicationsStatuses: commissionCalendarApplicationsStatusesReducer,
  uniExamTrainingLocations: uniExamTrainingLocationsReducer,
  trainingProgramTypes: trainingProgramTypesReducer,
  // TODO: NACIDSE-16
  countryData: countryDataReducer,
});

export default appDataReducers;
