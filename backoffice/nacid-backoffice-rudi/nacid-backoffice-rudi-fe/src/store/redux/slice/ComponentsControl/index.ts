import { combineReducers } from "@reduxjs/toolkit";
import universityControlReducer from "./baseUniversityControl";
import commissionCalendarControlReducer from "./commissionCalendarControl";
import selectedIdsControlReducer from "./selectedIdsControl";
import applicationsControlReducer from "./applicationsControl";
import sarApplicationsControlReducer from "./sarApplicationsControl";
import udirecApplicationsControlReducer from "./udirecApplicationsControl";
import docrecApplicationsControlReducer from "./docrecApplicationsControl";
import uniExaminationControlReducer from "./uniExaminationControl";
import acceptAppsViewDataControlReducer from "./acceptAppsViewDataControl";

const componentsControlReducers = combineReducers({
  universityControl: universityControlReducer,
  commissionCalendarControl: commissionCalendarControlReducer,
  selectedIdsControl: selectedIdsControlReducer,
  applicationsControl: applicationsControlReducer,
  sarApplicationsControl: sarApplicationsControlReducer,
  udirecApplicationsControl: udirecApplicationsControlReducer,
  docrecApplicationsControl: docrecApplicationsControlReducer,
  uniExaminationControl: uniExaminationControlReducer,
  acceptAppsViewDataControl: acceptAppsViewDataControlReducer,
});

export default componentsControlReducers;
