import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import {
  fulfilledThunkState,
  initialThunkState,
  pendingThunkState,
  rejectedThunkState,
  commonThunkCondition,
} from "@duosoftbg/nacid-components";
import { getGraduationDocTypeOptions } from "../../../../services/coreServicesCalls";

const sliceName = "appData/graduationDocType";
const graduationDocTypeSlice = createSlice({
  name: sliceName,
  initialState: initialThunkState([]),
  reducers: {},
  extraReducers: (builder) => {
    builder.addCase(graduationDocTypeThunk.pending, (state, action) => {
      return pendingThunkState(state);
    });
    builder.addCase(graduationDocTypeThunk.fulfilled, (state, action) => {
      const { data } = action.payload;
      return fulfilledThunkState(data);
    });
    builder.addCase(graduationDocTypeThunk.rejected, (state, action) => {
      return rejectedThunkState([]);
    });
  },
});

export const graduationDocTypeThunk = createAsyncThunk(
  `${sliceName}/graduationDocTypeThunk`,
  async () => {
    const response = await getGraduationDocTypeOptions()();
    return response;
  },
  {
    condition: (_, { getState, extra }) => {
      return commonThunkCondition(getState()["AppData"]?.GraduationDocType);
    },
  }
);

export default graduationDocTypeSlice.reducer;
