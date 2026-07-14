import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import {
  fulfilledThunkState,
  initialThunkState,
  pendingThunkState,
  rejectedThunkState,
  commonThunkCondition,
  ReferenceDataDomain,
} from "@duosoftbg/nacid-components";
import { CoreApiServicesBase } from "@duosoftbg/nacid-backoffice-components";

const sliceName = "appData/trainingProgramTypes";
const trainingProgramTypesThunkSlice = createSlice({
  name: sliceName,
  initialState: initialThunkState([]),
  reducers: {},
  extraReducers: (builder) => {
    builder.addCase(trainingProgramTypesThunk.pending, (state, action) => {
      return pendingThunkState(state);
    });
    builder.addCase(trainingProgramTypesThunk.fulfilled, (state, action) => {
      const { data } = action.payload;
      return fulfilledThunkState(data);
    });
    builder.addCase(trainingProgramTypesThunk.rejected, (state, action) => {
      return rejectedThunkState([]);
    });
  },
});

export const trainingProgramTypesThunk = createAsyncThunk(
  `${sliceName}/trainingProgramTypesThunk`,
  async () => {
    const response = await CoreApiServicesBase.getReferenceDataOptions(ReferenceDataDomain.TRAINING_PROGRAM_TYPE)();
    return { data: response };
  },
  {
    condition: (_, { getState, extra }) => {
      return commonThunkCondition(getState()["AppData"]?.trainingProgramTypes);
    },
  },
);

export default trainingProgramTypesThunkSlice.reducer;
