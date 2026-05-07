import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import { initialDocDegreesApplication } from "../../../../init/docDegreesInitialValues";
import { fillNonNullValues, THUNK_STATUS } from "@duosoftbg/nacid-components";
import { baseEndpointPaths, getApplicationById } from "../../../../services/serviceCalls";

export const fetchDocDegreesFormForEdit = createAsyncThunk(
  "docDegreesFormForEdit/fetchStatus",
  async (id: number | string) => {
    const response = await getApplicationById(baseEndpointPaths.docDegrees, id)();
    return response;
  }
);

const docDegreesFormSlice = createSlice({
  name: "DocDegreesStoreSlice",
  initialState: initialDocDegreesApplication,
  reducers: {
    setRequestIdentifier: (state, action) => {
      state.id = action.payload;
    },
    completeApplicantStep: (state, action) => {
      state.applicantDetails = action.payload;
      state.steps[0].completed = true;
    },
    completeEducationStep: (state, action) => {
      state.educationDetails = action.payload;
      state.steps[1].completed = true;
    },
    completeDocumentStep: (state, action) => {
      state.documentDetails = action.payload;
      state.steps[2].completed = true;
    },
    resetDocDegreesRequest: () => initialDocDegreesApplication,
    setStepApplicantEdited: (state, action) => {
      state.steps[0].isEdited = action.payload;
    },
    setStepEducationEdited: (state, action) => {
      state.steps[1].isEdited = action.payload;
    },
    setStepDocumentsEdited: (state, action) => {
      state.steps[2].isEdited = action.payload;
    },
  },
  extraReducers: (builder) => {
    builder.addCase(fetchDocDegreesFormForEdit.fulfilled, (state, action) => {
      state.dataStateStatus = THUNK_STATUS.FULFILLED;

      const updatedValues = { ...initialDocDegreesApplication };
      fillNonNullValues(action.payload.data, updatedValues);

      state.id = updatedValues.id;
      state.applicantDetails = updatedValues.applicantDetails;
      state.educationDetails = updatedValues.educationDetails;
      state.documentDetails = updatedValues.documentDetails;

      state.steps[0].completed = true;
      if (
        state.educationDetails.universitiesData.length > 0 &&
        state.educationDetails.universitiesData[0].name !== null &&
        state.educationDetails.universitiesData[0].name !== ""
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
    builder.addCase(fetchDocDegreesFormForEdit.pending, (state, action) => {
      state.dataStateStatus = THUNK_STATUS.PENDING;
    });
    builder.addCase(fetchDocDegreesFormForEdit.rejected, (state, action) => {
      state.dataStateStatus = THUNK_STATUS.REJECTED;
    });
  },
});

export const {
  setRequestIdentifier,
  completeApplicantStep,
  completeEducationStep,
  completeDocumentStep,
  resetDocDegreesRequest,
  setStepApplicantEdited,
  setStepEducationEdited,
  setStepDocumentsEdited,
} = docDegreesFormSlice.actions;
export default docDegreesFormSlice.reducer;
