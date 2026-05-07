import { createSlice } from "@reduxjs/toolkit";

const initialState = {
  editPageTab: { activeTab: 1 },
};

const udirecApplicationsControlSlice = createSlice({
  name: "udirecApplicationsControl",
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

export const UdirecAppControlActions = udirecApplicationsControlSlice.actions;
export default udirecApplicationsControlSlice.reducer;
