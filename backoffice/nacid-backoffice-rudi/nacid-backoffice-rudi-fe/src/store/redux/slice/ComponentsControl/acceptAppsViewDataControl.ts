import { createSlice } from "@reduxjs/toolkit";

const initialState = {
  open: true,
};

const acceptAppsViewDataControlSlice = createSlice({
  name: "acceptAppsViewDataControl",
  initialState: initialState,
  reducers: {
    show: (state) => {
      state.open = true;
    },
    hide: (state) => {
      state.open = false;
    },
    toggle: (state) => {
      state.open = !state.open;
    },
  },
});

export const AcceptAppsViewDataActions = { ...acceptAppsViewDataControlSlice.actions };
export default acceptAppsViewDataControlSlice.reducer;
