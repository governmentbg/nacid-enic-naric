import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import { initialOfficialNotesApplication } from "../../../../init/officialNotesInitialValues";
import { baseEndpointPaths, getApplicationById } from "../../../../services/serviceCalls";
import { fillNonNullValues, THUNK_STATUS } from "@duosoftbg/nacid-components";

export const fetchOfficialNotesForm = createAsyncThunk("officialNotesForm/fetchStatus", async (id: number | string) => {
  const response = await getApplicationById(baseEndpointPaths.officialNotes, id)();
  return response;
});

const officialNotesFormSlice = createSlice({
  name: "OfficialNotesStoreSlice",
  initialState: initialOfficialNotesApplication,
  reducers: {
    setRequestIdentifier: (state, action) => {
      state.id = action.payload;
    },
    completeApplicantStep: (state, action) => {
      state.applicantDetails = action.payload;
      state.steps[0].completed = true;
    },
    completeNoteStep: (state, action) => {
      state.officialNotesDetails = action.payload;
      state.steps[1].completed = true;
    },
    completeDocumentStep: (state, action) => {
      state.documentDetails = action.payload;
      state.steps[2].completed = true;
    },
    resetOfficialNotesRequest: () => initialOfficialNotesApplication,
    presetOfficialNotesKinds: (state, action) => {
      state.officialNotesDetails.officialNotesKinds = action.payload;
    },
    setStepApplicantEdited: (state, action) => {
      state.steps[0].isEdited = action.payload;
    },
    setStepNoteEdited: (state, action) => {
      state.steps[1].isEdited = action.payload;
    },
    setStepDocumentsEdited: (state, action) => {
      state.steps[2].isEdited = action.payload;
    },
  },
  extraReducers: (builder) => {
    builder.addCase(fetchOfficialNotesForm.fulfilled, (state, action) => {
      state.dataStateStatus = THUNK_STATUS.FULFILLED;

      const updatedValues = { ...initialOfficialNotesApplication };
      fillNonNullValues(action.payload.data, updatedValues);

      state.id = updatedValues.id;
      state.applicantDetails = updatedValues.applicantDetails;
      state.officialNotesDetails = updatedValues.officialNotesDetails;
      state.documentDetails = updatedValues.documentDetails;
      state.submittedOrFinalized = updatedValues.submittedOrFinalized;

      state.steps[0].completed = true;
      if (state.officialNotesDetails.officialNotesKinds && state.officialNotesDetails.officialNotesKinds.length > 0) {
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
    builder.addCase(fetchOfficialNotesForm.pending, (state, action) => {
      state.dataStateStatus = THUNK_STATUS.PENDING;
    });
    builder.addCase(fetchOfficialNotesForm.rejected, (state, action) => {
      state.dataStateStatus = THUNK_STATUS.REJECTED;
    });
  },
});

export const {
  setRequestIdentifier,
  completeApplicantStep,
  completeNoteStep,
  completeDocumentStep,
  resetOfficialNotesRequest,
  presetOfficialNotesKinds,
  setStepApplicantEdited,
  setStepNoteEdited,
  setStepDocumentsEdited,
} = officialNotesFormSlice.actions;
export default officialNotesFormSlice.reducer;
