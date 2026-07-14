import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import {
  fulfilledThunkState,
  initialThunkState,
  pendingThunkState,
  rejectedThunkState,
  commonThunkCondition,
} from "@duosoftbg/nacid-components";
import { getProfGroupsWithAreas } from "../../../../axios/api/services";

const sliceName = "appData/profGroupsWithAreas";
const profGroupsWithAreasSlice = createSlice({
  name: sliceName,
  initialState: initialThunkState([]),
  reducers: {},
  extraReducers: (builder) => {
    builder.addCase(profGroupsWithAreasThunk.pending, (state, action) => {
      return pendingThunkState(state);
    });
    builder.addCase(profGroupsWithAreasThunk.fulfilled, (state, action) => {
      const { data } = action.payload;
      return fulfilledThunkState(data);
    });
    builder.addCase(profGroupsWithAreasThunk.rejected, (state, action) => {
      return rejectedThunkState([]);
    });
  },
});

export const profGroupsWithAreasThunk = createAsyncThunk(
  `${sliceName}/profGroupsWithAreasThunk`,
  async () => {
    const response = await getProfGroupsWithAreas()();
    return { data: response };
  },
  {
    condition: (_, { getState, extra }) => {
      return commonThunkCondition(getState()["AppData"]?.profGroupsWithAreas);
    },
  },
);

export default profGroupsWithAreasSlice.reducer;
