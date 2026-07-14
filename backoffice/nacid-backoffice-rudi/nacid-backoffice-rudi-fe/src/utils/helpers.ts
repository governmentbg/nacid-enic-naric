import { AppType } from "@duosoftbg/nacid-backoffice-components";

export const TitleUtils = {
  selectTitleByAppType: (appType: AppType) => {
    switch (appType) {
      case AppType.SAR_APPLICATION: {
        return "t.sar.applications";
      }
      case AppType.UDIREC_APPLICATION: {
        return "t.diploma.recognitions";
      }
      case AppType.DOCREC_APPLICATION: {
        return "t.doctoral.degrees.recognitions";
      }
    }
    return null;
  },
};

export const UniversitySearchForm = {
  transformData: (university) => {
    if (!university) {
      return null;
    }

    const result = { ...university };
    if (university) {
      //Map if required
    }
    return result;
  },
};
