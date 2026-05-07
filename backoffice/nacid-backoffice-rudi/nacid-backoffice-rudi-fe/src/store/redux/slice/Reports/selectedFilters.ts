import { createSlice, PayloadAction } from "@reduxjs/toolkit";
import { REPORT_CONFIG } from "../../../../config/report/reportConfig";
import {
  generateConfigValue,
  LocalStorageActions,
  overrideRequiredValues,
  updateValueInLocalStorage,
} from "@duosoftbg/nacid-components";
import { ReportConfigName } from "@duosoftbg/nacid-backoffice-components";

const createInitialState = () => {
  let initialState = {};

  for (const item in REPORT_CONFIG) {
    let value = generateConfigValue(item, ReportConfigName.FILTERS, REPORT_CONFIG);
    let storedFilters = LocalStorageActions.getStoredValues(item, ReportConfigName.FILTERS, REPORT_CONFIG);
    if (storedFilters) {
      value = overrideRequiredValues(storedFilters, item, ReportConfigName.FILTERS, REPORT_CONFIG);
    } else {
      LocalStorageActions.setValue(item, ReportConfigName.FILTERS, value);
    }
    initialState[item] = value;
  }
  return initialState;
};

const selectedFiltersSlice = createSlice({
  name: "reportSelectedFilters",
  initialState: createInitialState(),
  reducers: {
    updateFilterValue: (state, action: PayloadAction<{ group: string; name: string; value: boolean }>) => {
      const { group, name, value } = action.payload;
      updateValueInLocalStorage({ group, configName: ReportConfigName.FILTERS, config: REPORT_CONFIG, name, value });
      state[group][name] = value;
    },
  },
});

export const ReportSelectedFiltersActions = { ...selectedFiltersSlice.actions };
export default selectedFiltersSlice.reducer;
