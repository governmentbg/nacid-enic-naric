import { GridContainer, GridItem, RadiosFormField } from "@duosoftbg/nacid-components";
import React, { useEffect } from "react";
import useAppDispatch from "../../../../../../../hooks/redux/base/useAppDispatch";
import useAppSelector from "../../../../../../../hooks/redux/base/useAppSelector";
import { cfgServiceTypeThunk } from "../../../../../../../store/redux/slice/AppData/cfgServiceType";

const ServiceTypeFormFields = ({ applicationType, applicationSubtype }) => {
  const dispatch = useAppDispatch();

  const cfgServiceTypeState = useAppSelector((state) => {
    return state.AppData.CfgServiceType;
  });

  useEffect(() => {
    dispatch(cfgServiceTypeThunk());
  }, [dispatch]);

  return (
    <GridContainer>
      <GridItem sm={12} md={12}>
        <RadiosFormField
          required={true}
          isInline={true}
          fieldName={"serviceType.id"}
          labelCode={"l.uniChecks.serviceType"}
          radioOptions={cfgServiceTypeState.data
            .filter(
              (cfg) =>
                cfg.applicationType === applicationType &&
                (cfg.applicationSubtype === applicationSubtype || !cfg.applicationSubtype)
            )
            .sort((cfg1, cfg2) =>
              cfg1.executionDays > cfg2.executionDays ? -1 : cfg1.executionDays < cfg2.executionDays ? 1 : 0
            )
            .map((cfg) => {
              return {
                value: cfg.serviceType.id,
                text: cfg.executionDays
                  ? `${cfg.serviceType.name} (${cfg.executionDays} ${
                      cfg.executionDaysType ? cfg.executionDaysType.name.toLowerCase() : ""
                    })`
                  : `${cfg.serviceType.name}`,
                active: cfg.serviceType.isActive,
              };
            })}
        ></RadiosFormField>
      </GridItem>
    </GridContainer>
  );
};
export default ServiceTypeFormFields;
