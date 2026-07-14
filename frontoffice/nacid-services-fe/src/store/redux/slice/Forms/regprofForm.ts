import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import { initialRegprofApplication } from "../../../../init/regprofInitialValues";
import { fillNonNullValues, THUNK_STATUS } from "@duosoftbg/nacid-components";
import { baseEndpointPaths, getApplicationById } from "../../../../services/serviceCalls";

export const fetchRegprofForm = createAsyncThunk("regprofForm/fetchStatus", async (id: number | string) => {
  const response = await getApplicationById(baseEndpointPaths.regprof, id)();
  return response;
});

const regprofFormSlice = createSlice({
  name: "RegprofStoreSlice",
  initialState: initialRegprofApplication,
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
    resetRegprofRequest: () => initialRegprofApplication,
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
    builder.addCase(fetchRegprofForm.fulfilled, (state, action) => {
      state.dataStateStatus = THUNK_STATUS.FULFILLED;

      const updatedValues = { ...initialRegprofApplication };
      fillNonNullValues(action.payload.data, updatedValues);

      state.id = updatedValues.id;
      state.applicantDetails = updatedValues.applicantDetails;
      state.educationDetails = updatedValues.educationDetails;
      state.documentDetails = updatedValues.documentDetails;

      state.steps[0].completed = true;
      if (
        state.educationDetails.country &&
        state.educationDetails.country.id &&
        state.educationDetails.country.id !== ""
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
    builder.addCase(fetchRegprofForm.pending, (state, action) => {
      state.dataStateStatus = THUNK_STATUS.PENDING;
    });
    builder.addCase(fetchRegprofForm.rejected, (state, action) => {
      state.dataStateStatus = THUNK_STATUS.REJECTED;
    });
  },
});

export const {
  setRequestIdentifier,
  completeApplicantStep,
  completeEducationStep,
  completeDocumentStep,
  resetRegprofRequest,
  setStepApplicantEdited,
  setStepEducationEdited,
  setStepDocumentsEdited,
} = regprofFormSlice.actions;
export default regprofFormSlice.reducer;
