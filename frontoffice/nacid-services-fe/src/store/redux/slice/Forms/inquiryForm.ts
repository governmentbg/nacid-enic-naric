import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import { initialInquiryApplication } from "../../../../init/inquiryInitialValues";
import { baseEndpointPaths, getApplicationById } from "../../../../services/serviceCalls";
import { fillNonNullValues, THUNK_STATUS } from "@duosoftbg/nacid-components";

export const fetchInquiryForm = createAsyncThunk("inquiryForm/fetchStatus", async (id: number | string) => {
  const response = await getApplicationById(baseEndpointPaths.inquiry, id)();
  return response;
});

const inquiryFormSlice = createSlice({
  name: "InquiryStoreSlice",
  initialState: initialInquiryApplication,
  reducers: {
    setRequestIdentifier: (state, action) => {
      state.id = action.payload;
    },
    completeApplicantStep: (state, action) => {
      state.applicantDetails = action.payload;
      state.steps[0].completed = true;
    },
    completeInquiryStep: (state, action) => {
      state.inquiryDetails = action.payload;
      state.steps[1].completed = true;
    },
    completeDocumentStep: (state, action) => {
      state.documentDetails = action.payload;
      state.steps[2].completed = true;
    },
    resetInquiryRequest: () => initialInquiryApplication,
    presetInquiryKinds: (state, action) => {
      state.inquiryDetails.inquiryKinds = action.payload;
    },
    setStepApplicantEdited: (state, action) => {
      state.steps[0].isEdited = action.payload;
    },
    setStepInquiryEdited: (state, action) => {
      state.steps[1].isEdited = action.payload;
    },
    setStepDocumentsEdited: (state, action) => {
      state.steps[2].isEdited = action.payload;
    },
  },
  extraReducers: (builder) => {
    builder.addCase(fetchInquiryForm.fulfilled, (state, action) => {
      state.dataStateStatus = THUNK_STATUS.FULFILLED;

      const updatedValues = { ...initialInquiryApplication };
      fillNonNullValues(action.payload.data, updatedValues);

      state.id = updatedValues.id;
      state.applicantDetails = updatedValues.applicantDetails;
      state.inquiryDetails = updatedValues.inquiryDetails;
      state.documentDetails = updatedValues.documentDetails;
      state.submittedOrFinalized = updatedValues.submittedOrFinalized;

      state.steps[0].completed = true;
      if (state.inquiryDetails.inquiryKinds && state.inquiryDetails.inquiryKinds.length > 0) {
        state.steps[1].completed = true;
      } else {
        state.steps[1].completed = false;
      }
      if (state.documentDetails.attachments.length > 0) {
        state.steps[2].completed = true;
      } else {
        state.steps[2].completed = false;
      }
    });
    builder.addCase(fetchInquiryForm.pending, (state, action) => {
      state.dataStateStatus = THUNK_STATUS.PENDING;
    });
    builder.addCase(fetchInquiryForm.rejected, (state, action) => {
      state.dataStateStatus = THUNK_STATUS.REJECTED;
    });
  },
});

export const {
  setRequestIdentifier,
  completeApplicantStep,
  completeInquiryStep,
  completeDocumentStep,
  resetInquiryRequest,
  presetInquiryKinds,
  setStepApplicantEdited,
  setStepInquiryEdited,
  setStepDocumentsEdited,
} = inquiryFormSlice.actions;
export default inquiryFormSlice.reducer;
