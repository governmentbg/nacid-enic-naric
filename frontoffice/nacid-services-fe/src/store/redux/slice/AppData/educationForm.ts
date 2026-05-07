import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import {
  fulfilledThunkState,
  initialThunkState,
  pendingThunkState,
  rejectedThunkState,
  commonThunkCondition,
  ReferenceDataDomain,
} from "@duosoftbg/nacid-components";
import { getReferenceDataOptions } from "../../../../services/coreServicesCalls";

const sliceName = "appData/educationForm";
const educationFormSlice = createSlice({
  name: sliceName,
  initialState: initialThunkState([]),
  reducers: {},
  extraReducers: (builder) => {
    builder.addCase(educationFormThunk.pending, (state, action) => {
      return pendingThunkState(state);
    });
    builder.addCase(educationFormThunk.fulfilled, (state, action) => {
      const { data } = action.payload;
      return fulfilledThunkState(data);
    });
    builder.addCase(educationFormThunk.rejected, (state, action) => {
      return rejectedThunkState([]);
    });
  },
});

export const educationFormThunk = createAsyncThunk(
  `${sliceName}/educationFormThunk`,
  async () => {
    const response = await getReferenceDataOptions(ReferenceDataDomain.TRAINING_FORM)();
    return response;
  },
  {
    condition: (_, { getState, extra }) => {
      return commonThunkCondition(getState()["AppData"]?.EducationForm);
    },
  }
);

export default educationFormSlice.reducer;
