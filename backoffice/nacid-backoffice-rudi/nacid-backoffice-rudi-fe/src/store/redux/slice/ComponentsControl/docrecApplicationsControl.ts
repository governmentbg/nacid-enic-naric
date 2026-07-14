import { createSlice } from "@reduxjs/toolkit";

const initialState = {
  editPageTab: { activeTab: 1 },
};

const docrecApplicationsControlSlice = createSlice({
  name: "docrecApplicationsControl",
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

export const DocrecAppControlActions = docrecApplicationsControlSlice.actions;
export default docrecApplicationsControlSlice.reducer;
