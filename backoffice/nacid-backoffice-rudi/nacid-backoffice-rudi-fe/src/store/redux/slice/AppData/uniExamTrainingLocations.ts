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

const sliceName = "appData/uniExamTrainingLocations";
const uniExamTrainingLocationsSlice = createSlice({
  name: sliceName,
  initialState: initialThunkState([]),
  reducers: {},
  extraReducers: (builder) => {
    builder.addCase(uniExamTrainingLocationsThunk.pending, (state, action) => {
      return pendingThunkState(state);
    });
    builder.addCase(uniExamTrainingLocationsThunk.fulfilled, (state, action) => {
      const { data } = action.payload;
      return fulfilledThunkState(data);
    });
    builder.addCase(uniExamTrainingLocationsThunk.rejected, (state, action) => {
      return rejectedThunkState([]);
    });
  },
});

export const uniExamTrainingLocationsThunk = createAsyncThunk(
  `${sliceName}/uniExamTrainingLocationsThunk`,
  async () => {
    const response = await CoreApiServicesBase.getReferenceDataOptions(
      ReferenceDataDomain.UNI_EXAM_TRAINING_LOCATION,
    )();
    return { data: response };
  },
  {
    condition: (_, { getState, extra }) => {
      return commonThunkCondition(getState()["AppData"]?.uniExamTrainingLocations);
    },
  },
);

export default uniExamTrainingLocationsSlice.reducer;
