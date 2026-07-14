import { combineReducers } from "@reduxjs/toolkit";
import selectedTableColumnsReducer from "./selectedTableColumns";

const applicationsReducers = combineReducers({
  selectedTableColumns: selectedTableColumnsReducer,
});

export default applicationsReducers;
