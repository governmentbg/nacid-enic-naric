import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import {
  fulfilledThunkState,
  initialThunkState,
  pendingThunkState,
  rejectedThunkState,
  commonThunkCondition,
  iTxt,
  isNotEmpty,
} from "@duosoftbg/nacid-components";
import { getServicesPageContent } from "../../../../services/coreServicesCalls";
import {
  ServiceConfig,
  ServicesDisplayPanelConfig,
  ServicesDisplaySectionConfig,
} from "../../../../types/serviceTypes";
import { getI18n } from "react-i18next";

const sliceName = "appData/servicesPageContent";
const servicesPageContentSlice = createSlice({
  name: sliceName,
  initialState: initialThunkState([]),
  reducers: {},
  extraReducers: (builder) => {
    builder.addCase(servicesPageContentThunk.pending, (state, action) => {
      return pendingThunkState(state);
    });
    builder.addCase(servicesPageContentThunk.fulfilled, (state, action) => {
      const { data } = action.payload;
      return fulfilledThunkState(data);
    });
    builder.addCase(servicesPageContentThunk.rejected, (state, action) => {
      return rejectedThunkState([]);
    });
  },
});

export const transformContentToServicesConfig = (data, location) => {
  let internalAddress = null;
  const appBaseHref = `${process.env.PUBLIC_URL}`;
  if (location.href.indexOf(appBaseHref) > 0) {
    internalAddress = location.href.substr(0, location.href.indexOf(appBaseHref) + appBaseHref.length);
  }

  const list = data.filter((dt) => isNotEmpty(dt.showInServicesPage) && dt.showInServicesPage);

  const middle = list.length % 2 === 0 ? list.length / 2 : Math.round(list.length / 2) - 1;

  const panel1List = list.slice(0, middle);
  const panel2List = list.slice(middle, list.length);

  const sectionsPanel1 = transformSections(panel1List, internalAddress);
  const sectionsPanel2 = transformSections(panel2List, internalAddress);

  const leftPanel: ServicesDisplayPanelConfig = {
    sections: sectionsPanel1,
    panel: "left",
  };

  const rightPanel: ServicesDisplayPanelConfig = {
    sections: sectionsPanel2,
    panel: "right",
  };

  const config: ServicesDisplayPanelConfig[] = [leftPanel, rightPanel];
  return config;
};

const transformSections = (panelList, internalAddress) => {
  const i18n = getI18n();

  const sections = panelList.map((cat) => {
    const services: ServiceConfig[] = cat.links.map((link) => {
      const isInternal = link.href.startsWith(internalAddress);
      const href = !isInternal ? link.href : link.href.substr(link.href.lastIndexOf("/"));
      const serviceConfig: ServiceConfig = {
        baseHref: href,
        external: !isInternal,
        titleCode: iTxt(i18n.language, link.name, link.nameEn),
      };
      return serviceConfig;
    });
    const sect: ServicesDisplaySectionConfig = {
      titleCode: iTxt(i18n.language, cat.name, cat.nameEn),
      services: services,
    };
    return sect;
  });
  return sections;
};

export const servicesPageContentThunk = createAsyncThunk(
  `${sliceName}/servicesPageContentThunk`,
  async () => {
    const response = await getServicesPageContent()();
    return response;
  },
  {
    condition: (_, { getState, extra }) => {
      return commonThunkCondition(getState()["AppData"]?.ServicesPageContent);
    },
  }
);

export default servicesPageContentSlice.reducer;
