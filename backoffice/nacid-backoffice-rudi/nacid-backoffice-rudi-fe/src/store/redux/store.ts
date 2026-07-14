import { configureStore } from "@reduxjs/toolkit";
import searchDataReducers from "./slice/SearchData";
import componentsControlReducers from "./slice/ComponentsControl";
import { defaultBackofficeReduxReducers } from "@duosoftbg/nacid-backoffice-components";
import appDataReducers from "./slice/AppData";
import reportsReducers from "./slice/Reports";
import applicationsReducers from "./slice/Applications";

export const store = configureStore({
  reducer: {
    ...defaultBackofficeReduxReducers,
    SearchData: searchDataReducers,
    AppData: appDataReducers,
    ComponentsControl: componentsControlReducers,
    Reports: reportsReducers,
    Applications: applicationsReducers,
  },
  devTools: process.env.REACT_APP_PROFILE !== "production",
});

export type AppDispatch = typeof store.dispatch;
export type RootState = ReturnType<typeof store.getState>;
