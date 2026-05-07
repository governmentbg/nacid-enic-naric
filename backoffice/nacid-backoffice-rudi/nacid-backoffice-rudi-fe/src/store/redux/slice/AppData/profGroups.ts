import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import {
  fulfilledThunkState,
  initialThunkState,
  pendingThunkState,
  rejectedThunkState,
  commonThunkCondition,
} from "@duosoftbg/nacid-components";
import { getProfGroups } from "../../../../axios/api/services";

const sliceName = "appData/profGroups";
const profGroupsSlice = createSlice({
  name: sliceName,
  initialState: initialThunkState([]),
  reducers: {},
  extraReducers: (builder) => {
    builder.addCase(profGroupsThunk.pending, (state, action) => {
      return pendingThunkState(state);
    });
    builder.addCase(profGroupsThunk.fulfilled, (state, action) => {
      const { data } = action.payload;
      return fulfilledThunkState(data);
    });
    builder.addCase(profGroupsThunk.rejected, (state, action) => {
      return rejectedThunkState([]);
    });
  },
});

export const profGroupsThunk = createAsyncThunk(
  `${sliceName}/profGroupsThunk`,
  async () => {
    const response = await getProfGroups()();
    return { data: response };
  },
  {
    condition: (_, { getState, extra }) => {
      return commonThunkCondition(getState()["AppData"]?.profGroups);
    },
  },
);

export default profGroupsSlice.reducer;
