import { useTranslation } from "react-i18next";
import React, { useEffect } from "react";
import { BoxSpg, GridContainer, GridItem, THUNK_STATUS, CircularLoader, AlertSpg } from "@duosoftbg/nacid-components";
import PageWrapper from "../components/common/layout/PageWrapper";
import ServicesPanel from "../components/services/common/servicesPage/ServicesPanel";
import useAppDispatch from "../../hooks/redux/base/useAppDispatch";
import {
  servicesPageContentThunk,
  transformContentToServicesConfig,
} from "../../store/redux/slice/AppData/servicesPageContent";
import useAppSelector from "../../hooks/redux/base/useAppSelector";

const ServicesPage = () => {
  const { t } = useTranslation();
  const dispatch = useAppDispatch();

  const servicesPageContent = useAppSelector((state) => {
    return state.AppData.ServicesPageContent;
  });

  useEffect(() => {
    dispatch(servicesPageContentThunk());
  }, [dispatch]);

  return (
    <PageWrapper centerTitle title={t("t.services")}>
      <BoxSpg mt={6}>
        {(servicesPageContent.status === THUNK_STATUS.INITIAL ||
          servicesPageContent.status === THUNK_STATUS.PENDING) && <CircularLoader />}

        {servicesPageContent.status === THUNK_STATUS.FULFILLED && (
          <GridContainer>
            {transformContentToServicesConfig(servicesPageContent.data, window.location).map((config) => (
              <GridItem key={config.panel} sm={6} md={6} lg={6}>
                <ServicesPanel config={config} />
              </GridItem>
            ))}
          </GridContainer>
        )}

        {servicesPageContent.status === THUNK_STATUS.REJECTED && (
          <AlertSpg severity={"error"}>{t("m.generic.error.service.fail")}</AlertSpg>
        )}
      </BoxSpg>
    </PageWrapper>
  );
};

export default ServicesPage;
