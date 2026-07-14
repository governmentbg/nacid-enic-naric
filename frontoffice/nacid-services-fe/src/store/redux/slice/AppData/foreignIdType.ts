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

const sliceName = "appData/foreignIdType";
const foreignIdTypeSlice = createSlice({
  name: sliceName,
  initialState: initialThunkState([]),
  reducers: {},
  extraReducers: (builder) => {
    builder.addCase(foreignIdTypeThunk.pending, (state, action) => {
      return pendingThunkState(state);
    });
    builder.addCase(foreignIdTypeThunk.fulfilled, (state, action) => {
      const { data } = action.payload;
      return fulfilledThunkState(data);
    });
    builder.addCase(foreignIdTypeThunk.rejected, (state, action) => {
      return rejectedThunkState([]);
    });
  },
});

export const foreignIdTypeThunk = createAsyncThunk(
  `${sliceName}/foreignIdTypeThunk`,
  async () => {
    const response = await getReferenceDataOptions(ReferenceDataDomain.FOREIGN_IDENTIFIER_TYPE)();
    return response;
  },
  {
    condition: (_, { getState, extra }) => {
      return commonThunkCondition(getState()["AppData"]?.ForeignIdType);
    },
  }
);

export default foreignIdTypeSlice.reducer;
