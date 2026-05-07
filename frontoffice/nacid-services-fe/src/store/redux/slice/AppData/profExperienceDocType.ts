import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import {
  fulfilledThunkState,
  initialThunkState,
  pendingThunkState,
  rejectedThunkState,
  commonThunkCondition,
} from "@duosoftbg/nacid-components";
import { getProfExperienceDocTypeOptions } from "../../../../services/coreServicesCalls";

const sliceName = "appData/profExperienceDocType";
const profExperienceDocTypeSlice = createSlice({
  name: sliceName,
  initialState: initialThunkState([]),
  reducers: {},
  extraReducers: (builder) => {
    builder.addCase(profExperienceDocTypeThunk.pending, (state, action) => {
      return pendingThunkState(state);
    });
    builder.addCase(profExperienceDocTypeThunk.fulfilled, (state, action) => {
      const { data } = action.payload;
      return fulfilledThunkState(data);
    });
    builder.addCase(profExperienceDocTypeThunk.rejected, (state, action) => {
      return rejectedThunkState([]);
    });
  },
});

export const profExperienceDocTypeThunk = createAsyncThunk(
  `${sliceName}/profExperienceDocTypeThunk`,
  async () => {
    const response = await getProfExperienceDocTypeOptions()();
    return response;
  },
  {
    condition: (_, { getState, extra }) => {
      return commonThunkCondition(getState()["AppData"]?.ProfExperienceDocType);
    },
  }
);

export default profExperienceDocTypeSlice.reducer;
