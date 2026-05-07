import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import {
  fulfilledThunkState,
  initialThunkState,
  pendingThunkState,
  rejectedThunkState,
  commonThunkCondition,
} from "@duosoftbg/nacid-components";
import { getProfGroupOptions } from "../../../../services/coreServicesCalls";

const sliceName = "appData/profGroup";
const profGroupSlice = createSlice({
  name: sliceName,
  initialState: initialThunkState([]),
  reducers: {},
  extraReducers: (builder) => {
    builder.addCase(profGroupThunk.pending, (state, action) => {
      return pendingThunkState(state);
    });
    builder.addCase(profGroupThunk.fulfilled, (state, action) => {
      const { data } = action.payload;
      return fulfilledThunkState(data);
    });
    builder.addCase(profGroupThunk.rejected, (state, action) => {
      return rejectedThunkState([]);
    });
  },
});

export const profGroupThunk = createAsyncThunk(
  `${sliceName}/profGroupThunk`,
  async () => {
    const response = await getProfGroupOptions()();
    return response;
  },
  {
    condition: (_, { getState, extra }) => {
      return commonThunkCondition(getState()["AppData"]?.ProfGroup);
    },
  }
);

export default profGroupSlice.reducer;
