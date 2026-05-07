import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import { baseEndpointPaths, getApplicationById } from "../../../../services/serviceCalls";
import { fillNonNullValues, THUNK_STATUS } from "@duosoftbg/nacid-components";
import { initialPublicAccessApplication } from "../../../../init/publicAccessInitialValues";

export const fetchPublicAccessForm = createAsyncThunk("publicAccessForm/fetchStatus", async (id: number | string) => {
  const response = await getApplicationById(baseEndpointPaths.publicAccess, id)();
  return response;
});

const publicAccessFormSlice = createSlice({
  name: "PublicAccessStoreSlice",
  initialState: initialPublicAccessApplication,
  reducers: {
    setRequestIdentifier: (state, action) => {
      state.id = action.payload;
    },
    completeApplicantStep: (state, action) => {
      state.applicantDetails = action.payload;
      state.steps[0].completed = true;
    },
    completePublicAccessStep: (state, action) => {
      state.publicAccessDetails = action.payload;
      state.steps[1].completed = true;
    },
    completeDocumentStep: (state, action) => {
      state.documentDetails = action.payload;
      state.steps[2].completed = true;
    },
    resetPublicAccessRequest: () => initialPublicAccessApplication,
    setStepApplicantEdited: (state, action) => {
      state.steps[0].isEdited = action.payload;
    },
    setStepePublicAccessEdited: (state, action) => {
      state.steps[1].isEdited = action.payload;
    },
    setStepDocumentsEdited: (state, action) => {
      state.steps[2].isEdited = action.payload;
    },
  },
  extraReducers: (builder) => {
    builder.addCase(fetchPublicAccessForm.fulfilled, (state, action) => {
      state.dataStateStatus = THUNK_STATUS.FULFILLED;

      const updatedValues = { ...initialPublicAccessApplication };
      fillNonNullValues(action.payload.data, updatedValues);

      state.id = updatedValues.id;
      state.applicantDetails = updatedValues.applicantDetails;
      state.publicAccessDetails = updatedValues.publicAccessDetails;
      state.documentDetails = updatedValues.documentDetails;

      state.steps[0].completed = true;
      if (state.publicAccessDetails.about && state.publicAccessDetails.about !== "") {
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
    builder.addCase(fetchPublicAccessForm.pending, (state, action) => {
      state.dataStateStatus = THUNK_STATUS.PENDING;
    });
    builder.addCase(fetchPublicAccessForm.rejected, (state, action) => {
      state.dataStateStatus = THUNK_STATUS.REJECTED;
    });
  },
});

export const {
  setRequestIdentifier,
  completeApplicantStep,
  completePublicAccessStep,
  completeDocumentStep,
  resetPublicAccessRequest,
  setStepApplicantEdited,
  setStepePublicAccessEdited,
  setStepDocumentsEdited,
} = publicAccessFormSlice.actions;
export default publicAccessFormSlice.reducer;
