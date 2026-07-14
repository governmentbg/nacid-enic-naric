import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import {
  fulfilledThunkState,
  initialThunkState,
  pendingThunkState,
  rejectedThunkState,
  commonThunkCondition,
} from "@duosoftbg/nacid-components";
import { getCfgRecognitionCategories } from "../../../../services/coreServicesCalls";

const sliceName = "appData/cfgRecognitionCategory";
const cfgRecognitionCategorySlice = createSlice({
  name: sliceName,
  initialState: initialThunkState([]),
  reducers: {},
  extraReducers: (builder) => {
    builder.addCase(cfgRecognitionCategoryThunk.pending, (state, action) => {
      return pendingThunkState(state);
    });
    builder.addCase(cfgRecognitionCategoryThunk.fulfilled, (state, action) => {
      const { data } = action.payload;
      return fulfilledThunkState(data);
    });
    builder.addCase(cfgRecognitionCategoryThunk.rejected, (state, action) => {
      return rejectedThunkState([]);
    });
  },
});

export const cfgRecognitionCategoryThunk = createAsyncThunk(
  `${sliceName}/cfgRecognitionCategoryThunk`,
  async () => {
    const response = await getCfgRecognitionCategories()();
    return response;
  },
  {
    condition: (_, { getState, extra }) => {
      return commonThunkCondition(getState()["AppData"]?.CfgRecognitionCategory);
    },
  }
);

export default cfgRecognitionCategorySlice.reducer;
