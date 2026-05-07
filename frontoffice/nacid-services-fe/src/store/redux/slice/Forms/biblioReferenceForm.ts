import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import { initialBiblioReferenceApplication } from "../../../../init/biblioReferenceInitialValues";
import { baseEndpointPaths, getApplicationById } from "../../../../services/serviceCalls";
import { fillNonNullValues, THUNK_STATUS } from "@duosoftbg/nacid-components";

export const fetchBiblioReferenceForm = createAsyncThunk(
  "biblioReferenceForm/fetchStatus",
  async (id: number | string) => {
    const response = await getApplicationById(baseEndpointPaths.bibliographicReference, id)();
    return response;
  }
);

const biblioReferenceFormSlice = createSlice({
  name: "BiblioReferenceStoreSlice",
  initialState: initialBiblioReferenceApplication,
  reducers: {
    setRequestIdentifier: (state, action) => {
      state.id = action.payload;
    },
    completeApplicantStep: (state, action) => {
      state.applicantDetails = action.payload;
      state.steps[0].completed = true;
    },
    completeBibliographicStep: (state, action) => {
      state.bibliographicReferenceDetails = action.payload;
      state.steps[1].completed = true;
    },
    completeDocumentStep: (state, action) => {
      state.documentDetails = action.payload;
      state.steps[2].completed = true;
    },
    resetBiblioReferenceRequest: () => initialBiblioReferenceApplication,
    presetBiblioReferenceNacid: (state) => {
      state.bibliographicReferenceDetails.nacidSearch = true;
    },
    presetBiblioReferenceForeign: (state) => {
      state.bibliographicReferenceDetails.foreignSearch = true;
    },
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
    builder.addCase(fetchBiblioReferenceForm.fulfilled, (state, action) => {
      state.dataStateStatus = THUNK_STATUS.FULFILLED;

      const updatedValues = { ...initialBiblioReferenceApplication };
      fillNonNullValues(action.payload.data, updatedValues);

      state.id = updatedValues.id;
      state.applicantDetails = updatedValues.applicantDetails;
      state.bibliographicReferenceDetails = updatedValues.bibliographicReferenceDetails;
      state.documentDetails = updatedValues.documentDetails;
      state.submittedOrFinalized = updatedValues.submittedOrFinalized;

      state.steps[0].completed = true;
      if (state.bibliographicReferenceDetails.theme && state.bibliographicReferenceDetails.theme !== "") {
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
    builder.addCase(fetchBiblioReferenceForm.pending, (state, action) => {
      state.dataStateStatus = THUNK_STATUS.PENDING;
    });
    builder.addCase(fetchBiblioReferenceForm.rejected, (state, action) => {
      state.dataStateStatus = THUNK_STATUS.REJECTED;
    });
  },
});

export const {
  setRequestIdentifier,
  completeApplicantStep,
  completeBibliographicStep,
  completeDocumentStep,
  resetBiblioReferenceRequest,
  presetBiblioReferenceNacid,
  presetBiblioReferenceForeign,
  setStepApplicantEdited,
  setStepBibliographicEdited,
  setStepDocumentsEdited,
} = biblioReferenceFormSlice.actions;
export default biblioReferenceFormSlice.reducer;
