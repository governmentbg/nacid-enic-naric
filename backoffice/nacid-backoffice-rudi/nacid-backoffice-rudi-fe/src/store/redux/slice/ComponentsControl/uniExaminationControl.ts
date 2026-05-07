import { createSlice } from "@reduxjs/toolkit";

const initialState = {
  modals: {
    view: {
      open: false,
      uniExaminationId: null,
    },
  },
};

const uniExaminationControlSlice = createSlice({
  name: "uniExaminationControl",
  initialState: initialState,
  reducers: {
    openViewUniExaminationModal: (state, action) => {
      const { uniExaminationId } = action.payload;
      state.modals.view = { open: true, uniExaminationId };
    },
    closeViewUniExaminationModal: (state, action) => {
      state.modals.view = { open: false, uniExaminationId: null };
    },
  },
});

export const UniExaminationControlActions = { ...uniExaminationControlSlice.actions };
export default uniExaminationControlSlice.reducer;
