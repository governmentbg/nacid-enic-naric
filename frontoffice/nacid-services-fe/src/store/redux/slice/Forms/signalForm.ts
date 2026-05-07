import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import { initialSignalApplication } from "../../../../init/signalInitialValues";
import { baseEndpointPaths, getApplicationById } from "../../../../services/serviceCalls";
import { fillNonNullValues, THUNK_STATUS } from "@duosoftbg/nacid-components";

export const fetchSignalForm = createAsyncThunk("signalForm/fetchStatus", async (id: number | string) => {
  const response = await getApplicationById(baseEndpointPaths.signal, id)();
  return response;
});

const signalFormSlice = createSlice({
  name: "SignalStoreSlice",
  initialState: initialSignalApplication,
  reducers: {
    setRequestIdentifier: (state, action) => {
      state.id = action.payload;
    },
    completeApplicantStep: (state, action) => {
      state.applicantDetails = action.payload;
      state.steps[0].completed = true;
    },
    completeSignalStep: (state, action) => {
      state.signalDetails = action.payload;
      state.steps[1].completed = true;
    },
    completeDocumentStep: (state, action) => {
      state.documentDetails = action.payload;
      state.steps[2].completed = true;
    },
    resetSignalRequest: () => initialSignalApplication,
    setStepApplicantEdited: (state, action) => {
      state.steps[0].isEdited = action.payload;
    },
    setStepSignalEdited: (state, action) => {
      state.steps[1].isEdited = action.payload;
    },
    setStepDocumentsEdited: (state, action) => {
      state.steps[2].isEdited = action.payload;
    },
  },
  extraReducers: (builder) => {
    builder.addCase(fetchSignalForm.fulfilled, (state, action) => {
      state.dataStateStatus = THUNK_STATUS.FULFILLED;

      const updatedValues = { ...initialSignalApplication };
      fillNonNullValues(action.payload.data, updatedValues);

      state.id = updatedValues.id;
      state.applicantDetails = updatedValues.applicantDetails;
      state.signalDetails = updatedValues.signalDetails;
      state.documentDetails = updatedValues.documentDetails;

      state.steps[0].completed = true;
      if (state.signalDetails.violationDescription && state.signalDetails.violationDescription !== "") {
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
    builder.addCase(fetchSignalForm.pending, (state, action) => {
      state.dataStateStatus = THUNK_STATUS.PENDING;
    });
    builder.addCase(fetchSignalForm.rejected, (state, action) => {
      state.dataStateStatus = THUNK_STATUS.REJECTED;
    });
  },
});

export const {
  setRequestIdentifier,
  completeApplicantStep,
  completeSignalStep,
  completeDocumentStep,
  resetSignalRequest,
  setStepApplicantEdited,
  setStepSignalEdited,
  setStepDocumentsEdited,
} = signalFormSlice.actions;
export default signalFormSlice.reducer;
