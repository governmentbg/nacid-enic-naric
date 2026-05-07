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

const sliceName = "appData/degreeRank";
const degreeRankSlice = createSlice({
  name: sliceName,
  initialState: initialThunkState([]),
  reducers: {},
  extraReducers: (builder) => {
    builder.addCase(degreeRankThunk.pending, (state, action) => {
      return pendingThunkState(state);
    });
    builder.addCase(degreeRankThunk.fulfilled, (state, action) => {
      const { data } = action.payload;
      return fulfilledThunkState(data);
    });
    builder.addCase(degreeRankThunk.rejected, (state, action) => {
      return rejectedThunkState([]);
    });
  },
});

export const degreeRankThunk = createAsyncThunk(
  `${sliceName}/degreeRankThunk`,
  async () => {
    const response = await getReferenceDataOptions(ReferenceDataDomain.QUALIFICATION_RANK)();
    return response;
  },
  {
    condition: (_, { getState, extra }) => {
      return commonThunkCondition(getState()["AppData"]?.DegreeRank);
    },
  }
);

export default degreeRankSlice.reducer;
