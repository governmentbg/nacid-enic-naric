import { createSlice } from "@reduxjs/toolkit";

const initialState = {
  modals: {
    deleteExpert: {
      open: false,
      id: null,
    },
    deleteStatement: {
      open: false,
      id: null,
    },
    deleteAttachment: {
      open: false,
      id: null,
    },
    editAttachment: {
      open: false,
      applicationId: null,
      attachmentId: null,
      direction: null,
    },
  },
  editExpertTab: { activeTab: 0 },
  statusTab: { activeTab: 0 },
  editAttachmentsTab: { activeTab: 0 },
};

const applicationsControlSlice = createSlice({
  name: "applicationsControl",
  initialState: initialState,
  reducers: {
    expertTabChange: (state, action) => {
      const { activeTab } = action.payload;
      state.editExpertTab = { activeTab };
    },
    resetExpertTab: (state) => {
      state.editExpertTab = { activeTab: 0 };
    },
    statusTabChange: (state, action) => {
      const { activeTab } = action.payload;
      state.statusTab = { activeTab };
    },
    resetStatusTab: (state) => {
      state.statusTab = { activeTab: 0 };
    },
    attachmentsTabChange: (state, action) => {
      const { activeTab } = action.payload;
      state.editAttachmentsTab = { activeTab };
    },
    resetAttachmentsTab: (state) => {
      state.editAttachmentsTab = { activeTab: 0 };
    },
    openDeleteExpertModal: (state, action) => {
      const { id } = action.payload;
      state.modals.deleteExpert = { open: true, id };
    },
    closeDeleteExpertModal: (state) => {
      state.modals.deleteExpert = { open: false, id: null };
    },
    openDeleteStatementModal: (state, action) => {
      const { id } = action.payload;
      state.modals.deleteStatement = { open: true, id };
    },
    closeDeleteStatementModal: (state) => {
      state.modals.deleteStatement = { open: false, id: null };
    },
    openDeleteAttachmentModal: (state, action) => {
      const { id } = action.payload;
      state.modals.deleteAttachment = { open: true, id };
    },
    closeDeleteAttachmentModal: (state) => {
      state.modals.deleteAttachment = { open: false, id: null };
    },
    openEditAttachmentModal: (state, action) => {
      const { applicationId, attachmentId, direction } = action.payload;
      state.modals.editAttachment = { open: true, applicationId, attachmentId, direction };
    },
    closeEditAttachmentModal: (state) => {
      state.modals.editAttachment = { open: false, applicationId: null, attachmentId: null, direction: null };
    },
  },
});

export const {
  openEditAttachmentModal,
  closeEditAttachmentModal,
  openDeleteAttachmentModal,
  closeDeleteAttachmentModal,
  attachmentsTabChange,
  resetAttachmentsTab,
  openDeleteExpertModal,
  closeDeleteExpertModal,
  openDeleteStatementModal,
  closeDeleteStatementModal,
  expertTabChange,
  resetExpertTab,
  statusTabChange,
  resetStatusTab,
} = applicationsControlSlice.actions;
export default applicationsControlSlice.reducer;
