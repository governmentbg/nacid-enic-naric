import { createSlice } from "@reduxjs/toolkit";

const initialState = {
  newIds: [],
};

const selectedIdsControlSlice = createSlice({
  name: "selectedIdsControl",
  initialState: initialState,
  reducers: {
    addNewId: (state, action) => {
      const { newId } = action.payload;
      state.newIds.push(newId);
    },
    addNewIds: (state, action) => {
      const { newIds } = action.payload;
      newIds.forEach((id) => {
        const idIndex = state.newIds.indexOf(id);
        if (idIndex === -1) {
          state.newIds.push(id);
        }
      });
    },
    removeId: (state, action) => {
      const { newId } = action.payload;
      const idsAfterRemove = state.newIds.filter((id) => id !== newId);
      state.newIds = idsAfterRemove;
    },
    removeIds: (state, action) => {
      const { newIds } = action.payload;
      newIds.forEach((deletedId) => {
        const idIndex = state.newIds.indexOf(deletedId);
        if (idIndex !== -1) {
          const idsAfterRemove = state.newIds.filter((id) => id !== deletedId);
          state.newIds = idsAfterRemove;
        }
      });
    },
    removeAll: (state) => {
      state.newIds = [];
    },
  },
});

export const { removeAll, addNewId, addNewIds, removeId, removeIds } = selectedIdsControlSlice.actions;
export default selectedIdsControlSlice.reducer;
