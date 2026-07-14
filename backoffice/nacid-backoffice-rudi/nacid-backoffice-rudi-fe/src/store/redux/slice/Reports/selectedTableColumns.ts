import { createSlice, PayloadAction } from "@reduxjs/toolkit";
import { REPORT_CONFIG } from "../../../../config/report/reportConfig";
import { generateConfigValue, LocalStorageActions, updateValueInLocalStorage } from "@duosoftbg/nacid-components";
import { ReportConfigName } from "@duosoftbg/nacid-backoffice-components";

const createInitialState = () => {
  let initialState = {};

  for (const item in REPORT_CONFIG) {
    let value = generateConfigValue(item, ReportConfigName.COLUMNS, REPORT_CONFIG);
    let storedColumns = LocalStorageActions.getStoredValues(item, ReportConfigName.COLUMNS, REPORT_CONFIG);
    if (storedColumns) {
      value = storedColumns;
    } else {
      LocalStorageActions.setValue(item, ReportConfigName.COLUMNS, value);
    }
    initialState[item] = value;
  }
  return initialState;
};

const selectedTableColumnsSlice = createSlice({
  name: "reportSelectedTableColumns",
  initialState: createInitialState(),
  reducers: {
    updateColumnValue: (state, action: PayloadAction<{ group: string; name: string; value: boolean }>) => {
      const { group, name, value } = action.payload;
      updateValueInLocalStorage({ group, configName: ReportConfigName.COLUMNS, config: REPORT_CONFIG, name, value });
      state[group][name] = value;
    },
  },
});

export const ReportSelectedTableColumnsActions = { ...selectedTableColumnsSlice.actions };
export default selectedTableColumnsSlice.reducer;
