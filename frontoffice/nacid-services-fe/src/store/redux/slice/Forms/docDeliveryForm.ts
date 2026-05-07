import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import { initialDocDeliveryApplication } from "../../../../init/docDeliveryInitialValues";
import { baseEndpointPaths, getApplicationById } from "../../../../services/serviceCalls";
import { fillNonNullValues, THUNK_STATUS } from "@duosoftbg/nacid-components";

export const fetchDocDeliveryForm = createAsyncThunk("docDeliveryForm/fetchStatus", async (id: number | string) => {
  const response = await getApplicationById(baseEndpointPaths.documentDelivery, id)();
  return response;
});

const docDeliveryFormSlice = createSlice({
  name: "DocDeliveryStoreSlice",
  initialState: initialDocDeliveryApplication,
  reducers: {
    setRequestIdentifier: (state, action) => {
      state.id = action.payload;
    },
    completeApplicantStep: (state, action) => {
      state.applicantDetails = action.payload;
      state.steps[0].completed = true;
    },
    completeBibliographicStep: (state, action) => {
      state.bibliographicDetails = action.payload;
      state.steps[1].completed = true;
    },
    completeDocumentStep: (state, action) => {
      state.documentDetails = action.payload;
      state.steps[2].completed = true;
    },
    resetDocDeliveryRequest: () => initialDocDeliveryApplication,
    setStepApplicantEdited: (state, action) => {
      state.steps[0].isEdited = action.payload;
    },
    setStepBibliographicEdited: (state, action) => {
      state.steps[1].isEdited = action.payload;
    },
    setStepDocumentsEdited: (state, action) => {
      state.steps[2].isEdited = action.payload;
    },
  },
  extraReducers: (builder) => {
    builder.addCase(fetchDocDeliveryForm.fulfilled, (state, action) => {
      state.dataStateStatus = THUNK_STATUS.FULFILLED;

      const updatedValues = { ...initialDocDeliveryApplication };
      fillNonNullValues(action.payload.data, updatedValues);

      state.id = updatedValues.id;
      state.applicantDetails = updatedValues.applicantDetails;
      state.bibliographicDetails = updatedValues.bibliographicDetails;
      state.documentDetails = updatedValues.documentDetails;

      state.steps[0].completed = true;
      if (
        state.bibliographicDetails.entries &&
        state.bibliographicDetails.entries.length > 0 &&
        state.bibliographicDetails.entries.filter((entry) => !entry.file || !entry.file.fileId).length === 0
      ) {
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
    builder.addCase(fetchDocDeliveryForm.pending, (state, action) => {
      state.dataStateStatus = THUNK_STATUS.PENDING;
    });
    builder.addCase(fetchDocDeliveryForm.rejected, (state, action) => {
      state.dataStateStatus = THUNK_STATUS.REJECTED;
    });
  },
});

export const {
  setRequestIdentifier,
  completeApplicantStep,
  completeBibliographicStep,
  completeDocumentStep,
  resetDocDeliveryRequest,
  setStepApplicantEdited,
  setStepBibliographicEdited,
  setStepDocumentsEdited,
} = docDeliveryFormSlice.actions;
export default docDeliveryFormSlice.reducer;
