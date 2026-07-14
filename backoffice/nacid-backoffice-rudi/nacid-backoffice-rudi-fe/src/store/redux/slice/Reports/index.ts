import { combineReducers } from "@reduxjs/toolkit";
import selectedFiltersReducer from "./selectedFilters";
import selectedTableColumnsReducer from "./selectedTableColumns";

const reportsReducers = combineReducers({
  selectedFilters: selectedFiltersReducer,
  selectedTableColumns: selectedTableColumnsReducer,
});

export default reportsReducers;
