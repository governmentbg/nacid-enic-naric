import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";

import {
  fulfilledThunkState,
  initialThunkState,
  pendingThunkState,
  rejectedThunkState,
  commonThunkCondition,
} from "@duosoftbg/nacid-components";
import { getAllNationalUniversities } from "../../../../services/coreServicesCalls";

const sliceName = "appData/nationalUniversities";
const nationalUniversitiesSlice = createSlice({
  name: sliceName,
  initialState: initialThunkState([]),
  reducers: {},
  extraReducers: (builder) => {
    builder.addCase(nationalUniversitiesDataThunk.pending, (state, action) => {
      return pendingThunkState(state);
    });
    builder.addCase(nationalUniversitiesDataThunk.fulfilled, (state, action) => {
      const { data } = action.payload;
      return fulfilledThunkState(data);
    });
    builder.addCase(nationalUniversitiesDataThunk.rejected, (state, action) => {
      return rejectedThunkState([]);
    });
  },
});

export const nationalUniversitiesDataThunk = createAsyncThunk(
  `${sliceName}/nationalUniversitiesThunk`,
  async () => {
    const response = await getAllNationalUniversities()();
    return { data: response };
  },
  {
    condition: (_, { getState, extra }) => {
      return commonThunkCondition(getState()["AppData"]?.NationalUniversities);
    },
  }
);

export default nationalUniversitiesSlice.reducer;
