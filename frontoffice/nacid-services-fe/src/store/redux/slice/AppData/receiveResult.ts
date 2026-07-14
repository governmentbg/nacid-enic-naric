import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import {
  fulfilledThunkState,
  initialThunkState,
  pendingThunkState,
  rejectedThunkState,
  commonThunkCondition,
} from "@duosoftbg/nacid-components";
import { getDocumentReceiveMethodOptions } from "../../../../services/coreServicesCalls";

const sliceName = "appData/receiveResult";
const receiveResultSlice = createSlice({
  name: sliceName,
  initialState: initialThunkState([]),
  reducers: {},
  extraReducers: (builder) => {
    builder.addCase(receiveResultThunk.pending, (state, action) => {
      return pendingThunkState(state);
    });
    builder.addCase(receiveResultThunk.fulfilled, (state, action) => {
      const { data } = action.payload;
      return fulfilledThunkState(data);
    });
    builder.addCase(receiveResultThunk.rejected, (state, action) => {
      return rejectedThunkState([]);
    });
  },
});

export const receiveResultThunk = createAsyncThunk(
  `${sliceName}/receiveResultThunk`,
  async () => {
    const response = await getDocumentReceiveMethodOptions()();
    return response;
  },
  {
    condition: (_, { getState, extra }) => {
      return commonThunkCondition(getState()["AppData"]?.ReceiveResult);
    },
  }
);

export default receiveResultSlice.reducer;
