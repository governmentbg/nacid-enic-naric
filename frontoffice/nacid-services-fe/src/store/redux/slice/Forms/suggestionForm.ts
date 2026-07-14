import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import { initialSuggestionApplication } from "../../../../init/suggestionInitialValues";
import { baseEndpointPaths, getApplicationById } from "../../../../services/serviceCalls";
import { fillNonNullValues, THUNK_STATUS } from "@duosoftbg/nacid-components";

export const fetchSuggestionForm = createAsyncThunk("suggestionForm/fetchStatus", async (id: number | string) => {
  const response = await getApplicationById(baseEndpointPaths.suggestion, id)();
  return response;
});

const suggestionFormSlice = createSlice({
  name: "suggestionStoreSlice",
  initialState: initialSuggestionApplication,
  reducers: {
    setRequestIdentifier: (state, action) => {
      state.id = action.payload;
    },
    completeApplicantStep: (state, action) => {
      state.applicantDetails = action.payload;
      state.steps[0].completed = true;
    },
    completeSuggestionStep: (state, action) => {
      state.suggestionDetails = action.payload;
      state.steps[1].completed = true;
    },
    completeDocumentStep: (state, action) => {
      state.documentDetails = action.payload;
      state.steps[2].completed = true;
    },
    resetSuggestionRequest: () => initialSuggestionApplication,
    setStepApplicantEdited: (state, action) => {
      state.steps[0].isEdited = action.payload;
    },
    setStepSuggestionEdited: (state, action) => {
      state.steps[1].isEdited = action.payload;
    },
    setStepDocumentsEdited: (state, action) => {
      state.steps[2].isEdited = action.payload;
    },
  },
  extraReducers: (builder) => {
    builder.addCase(fetchSuggestionForm.fulfilled, (state, action) => {
      state.dataStateStatus = THUNK_STATUS.FULFILLED;

      const updatedValues = { ...initialSuggestionApplication };
      fillNonNullValues(action.payload.data, updatedValues);

      state.id = updatedValues.id;
      state.applicantDetails = updatedValues.applicantDetails;
      state.suggestionDetails = updatedValues.suggestionDetails;
      state.documentDetails = updatedValues.documentDetails;

      state.steps[0].completed = true;
      if (state.suggestionDetails.suggestion && state.suggestionDetails.suggestion !== "") {
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
    builder.addCase(fetchSuggestionForm.pending, (state, action) => {
      state.dataStateStatus = THUNK_STATUS.PENDING;
    });
    builder.addCase(fetchSuggestionForm.rejected, (state, action) => {
      state.dataStateStatus = THUNK_STATUS.REJECTED;
    });
  },
});

export const {
  setRequestIdentifier,
  completeApplicantStep,
  completeSuggestionStep,
  completeDocumentStep,
  resetSuggestionRequest,
  setStepApplicantEdited,
  setStepSuggestionEdited,
  setStepDocumentsEdited,
} = suggestionFormSlice.actions;
export default suggestionFormSlice.reducer;
