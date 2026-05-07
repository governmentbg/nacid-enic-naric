import { createSlice } from "@reduxjs/toolkit";
import { ServiceConfig } from "../../../../types/serviceTypes";

const initialState: ServiceConfig = {
  descriptionCode: "",
  titleCode: "",
  external: false,
  baseHref: "/",
};

const selectedServiceSlice = createSlice({
  name: "selectedServiceSlice",
  initialState: initialState,
  reducers: {
    selectService: (state, action) => {
      return action.payload;
    },
  },
});

export const { selectService } = selectedServiceSlice.actions;
export default selectedServiceSlice.reducer;
