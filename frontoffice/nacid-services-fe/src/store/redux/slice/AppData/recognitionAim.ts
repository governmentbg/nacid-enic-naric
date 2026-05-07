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

const sliceName = "appData/recognitionAim";
const recognitionAimSlice = createSlice({
  name: sliceName,
  initialState: initialThunkState([]),
  reducers: {},
  extraReducers: (builder) => {
    builder.addCase(recognitionAimThunk.pending, (state, action) => {
      return pendingThunkState(state);
    });
    builder.addCase(recognitionAimThunk.fulfilled, (state, action) => {
      const { data } = action.payload;
      return fulfilledThunkState(data);
    });
    builder.addCase(recognitionAimThunk.rejected, (state, action) => {
      return rejectedThunkState([]);
    });
  },
});

export const recognitionAimThunk = createAsyncThunk(
  `${sliceName}/recognitionAimThunk`,
  async () => {
    const response = await getReferenceDataOptions(ReferenceDataDomain.RECOGNITION_PURPOSE)();
    return response;
  },
  {
    condition: (_, { getState, extra }) => {
      return commonThunkCondition(getState()["AppData"]?.RecognitionAim);
    },
  }
);

export default recognitionAimSlice.reducer;
