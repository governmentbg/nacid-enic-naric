import { useParams } from "react-router-dom";
import React, { useEffect, useState } from "react";
import { BoxSpg, concatNotEmptyBy, isEmpty, isNotEmpty, useAsyncCall } from "@duosoftbg/nacid-components";
import { AppType, CoreApiServicesBase } from "@duosoftbg/nacid-backoffice-components";
import { useTranslation } from "react-i18next";
import { TitleUtils } from "../../../../../../utils/helpers";

const FoAppSectionTitle = ({ appType }) => {
  const { id } = useParams();
  const { asyncCall } = useAsyncCall();
  const { t } = useTranslation();
  const [title, setTitle] = useState(null);

  useEffect(() => {
    if (appType === AppType.SAR_APPLICATION) {
      setSarFoAppTitle();
    } else {
      setTitle(t(TitleUtils.selectTitleByAppType(appType)));
    }
    // eslint-disable-next-line
  }, [appType, id]);

  const getApplicationTypeTitle = (educationDetails) => {
    if (isEmpty(educationDetails)) {
      return "";
    }
    return concatNotEmptyBy(", ")(
      educationDetails?.statute ? t("l.statuteFlag") : null,
      educationDetails?.authenticity ? t("l.authenticityFlag") : null,
      educationDetails?.recommendation ? t("l.recommendationFlag") : null,
    );
  };

  const getServiceTypeTitle = (educationDetails) => {
    if (isEmpty(educationDetails?.serviceType)) {
      return "";
    }
    return " - " + educationDetails.serviceType.name;
  };
  const setSarFoAppTitle = () => {
    asyncCall({
      promise: CoreApiServicesBase.getFoApplicationByEfilingId(id, appType),
      processResponseErrors: false,
      onSuccess: (response) => {
        let constructedTitle = t("t.sar.applications.short")
          .concat(": ")
          .concat(getApplicationTypeTitle(response?.data?.educationDetails))
          .concat(getServiceTypeTitle(response?.data?.educationDetails));
        setTitle(constructedTitle);
      },
      onError: () => {},
    });
  };
  return (
    <>
      {isNotEmpty(title) && (
        <BoxSpg my={5} mt={-3} textAlign={"center"}>
          {title}
        </BoxSpg>
      )}
    </>
  );
};

export default FoAppSectionTitle;
