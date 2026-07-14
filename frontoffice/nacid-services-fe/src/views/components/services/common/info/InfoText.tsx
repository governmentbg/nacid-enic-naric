import { BoxSpg, CircularTextLoader, THUNK_STATUS, HtmlParseText } from "@duosoftbg/nacid-components";
import { useTranslation } from "react-i18next";
import { useEffect } from "react";
import { Alert } from "@mui/material";
import useAppDispatch from "../../../../../hooks/redux/base/useAppDispatch";
import useAppSelector from "../../../../../hooks/redux/base/useAppSelector";
import { fetchServiceDescription } from "../../../../../store/redux/slice/AppData/servicesDescriptions";

const InfoText = ({ descriptionCode }) => {
  const { t } = useTranslation();
  const dispatch = useAppDispatch();

  const servicesDescriptions = useAppSelector((state) => {
    return state.AppData.ServicesDescriptions;
  });

  useEffect(() => {
    if (descriptionCode) {
      dispatch(fetchServiceDescription(descriptionCode));
    }
  }, [descriptionCode, dispatch]);

  if (!servicesDescriptions[descriptionCode]?.status) {
    return (
      <BoxSpg>
        <CircularTextLoader />
      </BoxSpg>
    );
  }
  if (servicesDescriptions[descriptionCode]?.status === THUNK_STATUS.REJECTED) {
    return (
      <BoxSpg>
        <Alert severity={"error"}>{t("m.generic.error.service.fail")}</Alert>
      </BoxSpg>
    );
  }
  return (
    <BoxSpg>
      {servicesDescriptions[descriptionCode].status === THUNK_STATUS.FULFILLED ? (
        <HtmlParseText text={servicesDescriptions[descriptionCode].content} />
      ) : (
        <CircularTextLoader />
      )}
    </BoxSpg>
  );
};

export default InfoText;
