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

const sliceName = "appData/humanitarianStatus";
const humanitarianStatusSlice = createSlice({
  name: sliceName,
  initialState: initialThunkState([]),
  reducers: {},
  extraReducers: (builder) => {
    builder.addCase(humanitarianStatusThunk.pending, (state, action) => {
      return pendingThunkState(state);
    });
    builder.addCase(humanitarianStatusThunk.fulfilled, (state, action) => {
      const { data } = action.payload;
      return fulfilledThunkState(data);
    });
    builder.addCase(humanitarianStatusThunk.rejected, (state, action) => {
      return rejectedThunkState([]);
    });
  },
});

export const humanitarianStatusThunk = createAsyncThunk(
  `${sliceName}/humanitarianStatusThunk`,
  async () => {
    const response = await getReferenceDataOptions(ReferenceDataDomain.HUMANITARIAN_STATUS)();
    return response;
  },
  {
    condition: (_, { getState, extra }) => {
      return commonThunkCondition(getState()["AppData"]?.HumanitarianStatus);
    },
  }
);

export default humanitarianStatusSlice.reducer;
