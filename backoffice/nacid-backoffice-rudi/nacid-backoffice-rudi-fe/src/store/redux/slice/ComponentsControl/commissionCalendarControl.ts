import { createSlice } from "@reduxjs/toolkit";
import { commissionParticipationInitialValues } from "../../../../init/commissionCalendar/commissionParticipationInitialValues";

const initialState = {
  modals: {
    addApplication: {
      open: false,
      excludedApplications: [],
    },
    deleteApplication: {
      open: false,
      id: null,
    },
    addMember: {
      open: false,
      excludedMembers: [],
    },
    deleteMember: {
      open: false,
      id: null,
    },
    memberAdditionalData: {
      open: false,
      member: commissionParticipationInitialValues,
    },
    deleteCalendar: {
      open: false,
      id: null,
    },
    viewCalendarMember: {
      open: false,
      id: null,
    },
  },
  editPageTab: { activeTab: 0 },
};

const commissionCalendarControlSlice = createSlice({
  name: "commissionCalendarControl",
  initialState: initialState,
  reducers: {
    openViewCalendarMemberModal: (state, action) => {
      const { id } = action.payload;
      state.modals.viewCalendarMember = { open: true, id };
    },
    closeViewCalendarMemberModal: (state) => {
      state.modals.viewCalendarMember = { open: false, id: null };
    },
    openDeleteCalendarModal: (state, action) => {
      const { id } = action.payload;
      state.modals.deleteCalendar = { open: true, id };
    },
    closeDeleteCalendarModal: (state) => {
      state.modals.deleteCalendar = { open: false, id: null };
    },
    tabChange: (state, action) => {
      const { activeTab } = action.payload;
      state.editPageTab = { activeTab };
    },
    resetTab: (state) => {
      state.editPageTab = { activeTab: 0 };
    },
    openMemberAdditionalData: (state, action) => {
      const { member } = action.payload;
      state.modals.memberAdditionalData = { open: true, member: member };
    },
    closeMemberAdditionalData: (state) => {
      state.modals.memberAdditionalData = { open: false, member: commissionParticipationInitialValues };
    },

    openDeleteMemberModal: (state, action) => {
      const { id } = action.payload;
      state.modals.deleteMember = { open: true, id };
    },
    closeDeleteMemberModal: (state) => {
      state.modals.deleteMember = { open: false, id: null };
    },
    openAddMemberDialog: (state, action) => {
      const { excludedMembers } = action.payload;
      state.modals.addMember = { open: true, excludedMembers };
    },
    closeAddMemberDialog: (state) => {
      state.modals.addMember = { open: false, excludedMembers: [] };
    },
    openDeleteApplicationModal: (state, action) => {
      const { id } = action.payload;
      state.modals.deleteApplication = { open: true, id };
    },
    closeDeleteApplicationModal: (state) => {
      state.modals.deleteApplication = { open: false, id: null };
    },
    openAddApplicationDialog: (state, action) => {
      const { excludedApplications } = action.payload;
      state.modals.addApplication = { open: true, excludedApplications };
    },
    closeAddApplicationDialog: (state) => {
      state.modals.addApplication = { open: false, excludedApplications: [] };
    },
  },
});

export const {
  openViewCalendarMemberModal,
  closeViewCalendarMemberModal,
  openDeleteCalendarModal,
  closeDeleteCalendarModal,
  tabChange,
  resetTab,
  openMemberAdditionalData,
  closeMemberAdditionalData,
  openAddMemberDialog,
  openDeleteMemberModal,
  closeDeleteMemberModal,
  closeAddMemberDialog,
  openDeleteApplicationModal,
  closeDeleteApplicationModal,
  openAddApplicationDialog,
  closeAddApplicationDialog,
} = commissionCalendarControlSlice.actions;
export default commissionCalendarControlSlice.reducer;
