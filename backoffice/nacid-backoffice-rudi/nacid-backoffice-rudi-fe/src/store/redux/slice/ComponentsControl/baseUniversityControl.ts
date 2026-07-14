import { deepCopy } from "@duosoftbg/nacid-components";
import { createSlice } from "@reduxjs/toolkit";

const initialState = {
  modals: {
    search: {
      open: false,
      universityIdPointer: null,
      tempDataKey: null,
      searchFormValues: null,
    },
    create: {
      open: false,
      universityIdPointer: null,
      tempDataKey: null,
      initialData: null,
    },
    edit: {
      open: false,
      universityId: null,
      universityIdPointer: null,
      tempDataKey: null,
    },
    view: {
      open: false,
      universityId: null,
    },
  },
  searchTable: {
    records: [],
    status: "initial",
  },
};

const universityControlSlice = createSlice({
  name: "universityControl",
  initialState: initialState,
  reducers: {
    openSearchUniversityModal: (state, action) => {
      const { universityIdPointer, tempDataKey, searchFormValues = null } = action.payload;
      state.modals.search = { open: true, universityIdPointer, tempDataKey, searchFormValues };
    },
    closeSearchUniversityModal: (state, action) => {
      state.modals.search = { open: false, universityIdPointer: null, tempDataKey: null, searchFormValues: null };
      state.searchTable = { records: [], status: "initial" };
    },
    updateSearchUniversityModalFormValues: (state, action) => {
      const { searchFormValues } = action.payload;
      state.modals.search.searchFormValues = deepCopy(searchFormValues);
    },
    updateSearchUniversityModalRecords: (state, action) => {
      const { records, status } = action.payload;
      state.searchTable = { records, status };
    },
    openCreateUniversityModal: (state, action) => {
      const { universityIdPointer, tempDataKey, initialData } = action.payload;
      state.modals.create = { open: true, universityIdPointer, tempDataKey, initialData };
    },
    closeCreateUniversityModal: (state, action) => {
      state.modals.create = {
        open: false,
        universityIdPointer: null,
        tempDataKey: null,
        initialData: null,
      };
    },
    openEditUniversityModal: (state, action) => {
      const { universityId, universityIdPointer, tempDataKey } = action.payload;
      state.modals.edit = { open: true, universityId, universityIdPointer, tempDataKey };
    },
    closeEditUniversityModal: (state, action) => {
      state.modals.edit = { open: false, universityId: null, universityIdPointer: null, tempDataKey: null };
    },
    openViewUniversityModal: (state, action) => {
      const { universityId } = action.payload;
      state.modals.view = { open: true, universityId };
    },
    closeViewUniversityModal: (state, action) => {
      state.modals.view = { open: false, universityId: null };
    },
  },
});

export const UniversityControlActions = { ...universityControlSlice.actions };
export default universityControlSlice.reducer;
