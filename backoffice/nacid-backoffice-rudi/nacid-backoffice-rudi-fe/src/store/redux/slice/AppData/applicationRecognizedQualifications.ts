import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import {
  fulfilledThunkState,
  initialThunkState,
  pendingThunkState,
  rejectedThunkState,
  commonThunkCondition,
} from "@duosoftbg/nacid-components";

import { getApplicationRecognizedQualifications } from "../../../../axios/api/services";

const sliceName = "appData/applicationRecognizedQualifications";
const applicationRecognizedQualificationsSlice = createSlice({
  name: sliceName,
  initialState: initialThunkState([]),
  reducers: {},
  extraReducers: (builder) => {
    builder.addCase(applicationRecognizedQualificationsThunk.pending, (state, action) => {
      return pendingThunkState(state);
    });
    builder.addCase(applicationRecognizedQualificationsThunk.fulfilled, (state, action) => {
      const { data } = action.payload;
      return fulfilledThunkState(data);
    });
    builder.addCase(applicationRecognizedQualificationsThunk.rejected, (state, action) => {
      return rejectedThunkState([]);
    });
  },
});

export const applicationRecognizedQualificationsThunk = createAsyncThunk(
  `${sliceName}/applicationRecognizedQualificationsThunk`,
  async () => {
    const response = await getApplicationRecognizedQualifications()();
    return { data: response };
  },
  {
    condition: (_, { getState, extra }) => {
      return commonThunkCondition(getState()["AppData"]?.applicationRecognizedQualifications);
    },
  },
);

export default applicationRecognizedQualificationsSlice.reducer;
