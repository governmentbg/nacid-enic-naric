import { configureStore } from "@reduxjs/toolkit";
import formsReducers from "./slice/Forms";
import selectedServiceReducer from "./slice/SelectedService";
import appDataReducer from "./slice/AppData";
import myApplicationsReducer from "./slice/MyApplications";
import myCorrespondenceReducer from "./slice/MyCorrespondence";
import formResetReducer from "./slice/FormReset";
import { defaultFrontOfficeReduxReducers } from "@duosoftbg/nacid-frontoffice-components";

export const store = configureStore({
  reducer: {
    ...defaultFrontOfficeReduxReducers,
    Forms: formsReducers,
    MyApplications: myApplicationsReducer,
    MyCorrespondence: myCorrespondenceReducer,
    FormReset: formResetReducer,
    SelectedService: selectedServiceReducer,
    AppData: appDataReducer,
  },
  devTools: process.env.REACT_APP_PROFILE !== "production",
  middleware: (getDefaultMiddleware) =>
    getDefaultMiddleware({
      serializableCheck: false,
    }),
});

export type AppDispatch = typeof store.dispatch;
export type RootState = ReturnType<typeof store.getState>;
