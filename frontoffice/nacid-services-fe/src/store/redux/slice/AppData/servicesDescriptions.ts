import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import { THUNK_STATUS } from "@duosoftbg/nacid-components";
import { getContentManagementDataById } from "../../../../services/coreServicesCalls";

const initialState = {
  descriptions: {},
};

export const fetchServiceDescription = createAsyncThunk(
  "serviceDescription/fetchStatus",
  async (id: string) => {
    const response = await getContentManagementDataById(id)();
    return { content: response.data.content, id: id };
  },
  {
    condition: (args, { getState, extra }) => {
      return (
        !getState()["AppData"]["ServicesDescriptions"][args] ||
        !getState()["AppData"]["ServicesDescriptions"][args]["status"] ||
        getState()["AppData"]["ServicesDescriptions"][args]["status"] !== THUNK_STATUS.FULFILLED
      );
    },
  }
);

const servicesDescriptionsSlice = createSlice({
  name: "servicesDescriptionsSlice",
  initialState: initialState,
  reducers: {},
  extraReducers: (builder) => {
    builder.addCase(fetchServiceDescription.pending, (state, action) => {
      return { ...state, [action.meta.arg]: { status: THUNK_STATUS.PENDING } };
    });
    builder.addCase(fetchServiceDescription.fulfilled, (state, action) => {
      const data = action.payload;
      return { ...state, [data.id]: { content: data.content, status: THUNK_STATUS.FULFILLED } };
    });
    builder.addCase(fetchServiceDescription.rejected, (state, action) => {
      return { ...state, [action.meta.arg]: { status: THUNK_STATUS.REJECTED } };
    });
  },
});

export default servicesDescriptionsSlice.reducer;
