import { createSlice } from "@reduxjs/toolkit";

const initialState = {
  editPageTab: { activeTab: 1 },
};

const sarApplicationsControlSlice = createSlice({
  name: "sarApplicationsControl",
  initialState: initialState,
  reducers: {
    tabChange: (state, action) => {
      const { activeTab } = action.payload;
      state.editPageTab = { activeTab };
    },
    resetTab: (state) => {
      state.editPageTab = { activeTab: 1 };
    },
  },
});

export const SarAppControlActions = sarApplicationsControlSlice.actions;
export default sarApplicationsControlSlice.reducer;
