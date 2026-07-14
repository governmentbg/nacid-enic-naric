import { createSlice } from "@reduxjs/toolkit";

const initialState = {};

const formResetSlice = createSlice({
  name: "FormResetSlice",
  initialState: initialState,
  reducers: {
    setFormResetValue: (state, action) => {
      state[action.payload.applicationSubtype] = action.payload.resetValue;
    },
  },
});

export const { setFormResetValue } = formResetSlice.actions;
export default formResetSlice.reducer;
