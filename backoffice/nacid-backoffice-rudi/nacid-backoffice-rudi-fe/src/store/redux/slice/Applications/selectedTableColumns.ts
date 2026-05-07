import { createSlice, PayloadAction } from "@reduxjs/toolkit";
import { APPLICATION_CONFIG } from "../../../../config/applications/applicationConfig";

const validateLocalStorageValue = (localStorageValue, groupName) => {
  let configValues = { ...generateTableColumnValue(groupName) };
  for (const item in configValues) {
    let value = localStorageValue[item];
    if (!(undefined !== value && null !== value && typeof value == "boolean")) {
      return false;
    }
  }
  return true;
};

const LocalStorageTableColumns = {
  getStoredColumns: (groupName) => {
    let result = null;
    try {
      let value = localStorage.getItem(`${groupName}_tableColumns`);
      if (value != null) {
        const isValid = validateLocalStorageValue(JSON.parse(value), groupName);
        if (isValid) {
          result = JSON.parse(value);
        }
      }
    } catch (exception) {
      console.log(exception);
    }
    return result;
  },
  setColumn: (groupName, value) => {
    localStorage.setItem(`${groupName}_tableColumns`, JSON.stringify(value));
  },
};

function generateTableColumnValue(name) {
  let value = {};
  APPLICATION_CONFIG[name].tableColumns.forEach((item) => (value[item.id] = !!item.active));
  return value;
}

const createInitialState = () => {
  let initialState = {};

  for (const item in APPLICATION_CONFIG) {
    let value = generateTableColumnValue(item);
    let storedColumns = LocalStorageTableColumns.getStoredColumns(item);
    if (storedColumns) {
      value = storedColumns;
    } else {
      LocalStorageTableColumns.setColumn(item, value);
    }
    initialState[item] = value;
  }
  return initialState;
};

function updateTableColumnsInLocalStorage(action) {
  const group = action.group;

  let storedColumns = LocalStorageTableColumns.getStoredColumns(group);
  if (undefined === storedColumns || null === storedColumns) {
    storedColumns = generateTableColumnValue(group);
  }
  storedColumns[action.name] = action.value;
  LocalStorageTableColumns.setColumn(group, storedColumns);
}

const selectedTableColumnsSlice = createSlice({
  name: "applicationSelectedTableColumns",
  initialState: createInitialState(),
  reducers: {
    updateColumnValue: (state, action: PayloadAction<{ group: string; name: string; value: boolean }>) => {
      const { group, name, value } = action.payload;
      updateTableColumnsInLocalStorage({ group, name, value });
      state[group][name] = value;
    },
  },
});

export const ApplicationSelectedTableColumnsActions = { ...selectedTableColumnsSlice.actions };
export default selectedTableColumnsSlice.reducer;
