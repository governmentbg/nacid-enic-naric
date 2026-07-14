import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import {
  fulfilledThunkState,
  initialThunkState,
  pendingThunkState,
  rejectedThunkState,
  THUNK_STATUS,
} from "@duosoftbg/nacid-components";
import { getLoggedUserDetails } from "../../../../services/userDetailsCalls";
import { keycloakInitObject as keycloak } from "@duosoftbg/nacid-frontoffice-components";

const initialState = {
  userDetails: {},
};

export const fetchLoggedUserDetails = createAsyncThunk(
  "loggedUserDetails/fetchStatus",
  async () => {
    const response = await getLoggedUserDetails()();
    return response;
  },
  {
    condition: (args, { getState, extra }) => {
      const userNotMatches =
        keycloak.authenticated &&
        keycloak.idTokenParsed.preferred_username !== getState()["AppData"]["LoggedUser"].data.userDetails.username;
      return (
        keycloak.authenticated &&
        (getState()["AppData"]["LoggedUser"].status !== THUNK_STATUS.FULFILLED || userNotMatches)
      );
    },
  }
);

const loggedUserSlice = createSlice({
  name: "loggedUserSlice",
  initialState: initialThunkState(initialState),
  reducers: {},
  extraReducers: (builder) => {
    builder.addCase(fetchLoggedUserDetails.pending, (state, action) => {
      return pendingThunkState(initialState);
    });
    builder.addCase(fetchLoggedUserDetails.fulfilled, (state, action) => {
      const data = action.payload.data;
      return fulfilledThunkState({ userDetails: data });
    });
    builder.addCase(fetchLoggedUserDetails.rejected, (state, action) => {
      return rejectedThunkState(initialState);
    });
  },
});

export default loggedUserSlice.reducer;
