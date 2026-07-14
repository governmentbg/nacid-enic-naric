import React, { useEffect } from "react";
import { useFormContext, useWatch } from "react-hook-form";
import {
  InputFormField,
  CheckboxListFormField,
  GridItem,
  GridContainer,
  ReferenceDataCode,
  ReferenceDataDomain,
} from "@duosoftbg/nacid-components";
import useAppSelector from "../../../../../../../hooks/redux/base/useAppSelector";
import { cfgGraduationWayThunk } from "../../../../../../../store/redux/slice/AppData/cfgGraduationWay";
import useAppDispatch from "../../../../../../../hooks/redux/base/useAppDispatch";

const GraduationWayFormFields = ({ applicationType, applicationSubtype, labelCode = "l.graduationWay", required }) => {
  const { getValues } = useFormContext();
  const dispatch = useAppDispatch();

  useWatch({ name: "graduationWay.id" });

  const cfgGraduationWayThunkState = useAppSelector((state) => {
    return state.AppData.CfgGraduationWay;
  });

  useEffect(() => {
    dispatch(cfgGraduationWayThunk());
  }, [dispatch]);

  return (
    <React.Fragment>
      <GridContainer spacing={4} mt={0}>
        <GridItem sm={6} md={6}>
          <CheckboxListFormField
            required={required}
            fieldName={"graduationWay"}
            labelCode={labelCode}
            checkboxOptions={cfgGraduationWayThunkState.data
              .filter((cfg) => cfg.applicationType === applicationType && cfg.applicationSubtype === applicationSubtype)
              .map((cfg) => {
                return {
                  value: cfg.graduationWay.id,
                  text: cfg.graduationWay.name,
                  active: cfg.graduationWay.isActive,
                };
              })}
            valuesAreEqual={(checkboxVal, arrayVal) => checkboxVal === arrayVal.id}
            selectValueTransform={(selected) => {
              return { id: selected, name: "", domain: ReferenceDataDomain.GRADUATION_WAY };
            }}
          />
        </GridItem>
      </GridContainer>
      {getValues().graduationWay.filter((val) => val.id === ReferenceDataCode.OTHER).length > 0 ? (
        <GridContainer spacing={4} mt={0}>
          <GridItem sm={6} md={6}>
            <InputFormField fieldName={"graduationWayOtherDetails"} labelCode={"l.graduationWayOtherDetails"} />
          </GridItem>
        </GridContainer>
      ) : null}
    </React.Fragment>
  );
};

export default GraduationWayFormFields;
