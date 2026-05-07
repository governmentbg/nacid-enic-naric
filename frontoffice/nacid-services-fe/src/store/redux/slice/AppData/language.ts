import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import {
  fulfilledThunkState,
  initialThunkState,
  pendingThunkState,
  rejectedThunkState,
  commonThunkCondition,
} from "@duosoftbg/nacid-components";
import { getLanguageOptions } from "../../../../services/coreServicesCalls";

const sliceName = "appData/language";
const languageSlice = createSlice({
  name: sliceName,
  initialState: initialThunkState([]),
  reducers: {},
  extraReducers: (builder) => {
    builder.addCase(languageThunk.pending, (state, action) => {
      return pendingThunkState(state);
    });
    builder.addCase(languageThunk.fulfilled, (state, action) => {
      const { data } = action.payload;
      return fulfilledThunkState(data);
    });
    builder.addCase(languageThunk.rejected, (state, action) => {
      return rejectedThunkState([]);
    });
  },
});

export const languageThunk = createAsyncThunk(
  `${sliceName}/languageThunk`,
  async () => {
    const response = await getLanguageOptions()();
    return response;
  },
  {
    condition: (_, { getState, extra }) => {
      return commonThunkCondition(getState()["AppData"]?.Language);
    },
  }
);

export default languageSlice.reducer;
