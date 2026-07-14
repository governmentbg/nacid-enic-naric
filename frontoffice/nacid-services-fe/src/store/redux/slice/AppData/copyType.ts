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

const sliceName = "appData/copyType";
const copyTypeSlice = createSlice({
  name: sliceName,
  initialState: initialThunkState([]),
  reducers: {},
  extraReducers: (builder) => {
    builder.addCase(copyTypeThunk.pending, (state, action) => {
      return pendingThunkState(state);
    });
    builder.addCase(copyTypeThunk.fulfilled, (state, action) => {
      const { data } = action.payload;
      return fulfilledThunkState(data);
    });
    builder.addCase(copyTypeThunk.rejected, (state, action) => {
      return rejectedThunkState([]);
    });
  },
});

export const copyTypeThunk = createAsyncThunk(
  `${sliceName}/copyTypeThunk`,
  async () => {
    const response = await getReferenceDataOptions(ReferenceDataDomain.COPY_TYPE)();
    return response;
  },
  {
    condition: (_, { getState, extra }) => {
      return commonThunkCondition(getState()["AppData"]?.CopyType);
    },
  }
);

export default copyTypeSlice.reducer;
