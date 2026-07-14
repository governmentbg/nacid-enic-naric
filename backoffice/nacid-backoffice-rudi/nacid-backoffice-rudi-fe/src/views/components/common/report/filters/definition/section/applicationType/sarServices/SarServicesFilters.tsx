import {
  CheckboxListFormField,
  GridContainer,
  GridItem,
  RadiosFormField,
  ReferenceDataDomain,
} from "@duosoftbg/nacid-components";
import { useEffect } from "react";
import { sarServicesThunk } from "../../../../../../../../../store/redux/slice/AppData/sarServices";
import useAppDispatch from "../../../../../../../../../hooks/redux/base/useAppDispatch";
import { JoinType } from "../../../../../../../../../utils/constants";
import { useTranslation } from "react-i18next";

const SarServicesFilters = ({ baseField, thunkStateSarServices }) => {
  const dispatch = useAppDispatch();
  const { t } = useTranslation();

  useEffect(() => {
    dispatch(sarServicesThunk());
  }, [dispatch]);

  return (
    <>
      <GridContainer mt={0}>
        <GridItem sm={12} md={12} style={{ marginLeft: "5px" }}>
          <CheckboxListFormField
            fieldName={`${baseField}.sarServices`}
            row={true}
            size={"small"}
            checkboxOptions={thunkStateSarServices.data.map((option) => {
              return { value: option.id, text: option.name };
            })}
            valuesAreEqual={(checkboxVal, arrayVal) => checkboxVal === arrayVal.id}
            selectValueTransform={(selected) => {
              return {
                id: selected,
                name: thunkStateSarServices.data.find((arrayVal) => arrayVal.id === selected)?.name,
                domain: ReferenceDataDomain.SAR_APPLICATION_TYPE,
              };
            }}
          />
        </GridItem>
      </GridContainer>
      <GridContainer mt={0}>
        <GridItem sm={12} md={12} style={{ marginLeft: "5px" }}>
          <RadiosFormField
            fieldName={`${baseField}.sarServicesJoin`}
            isInline={true}
            size={"small"}
            radioOptions={Object.values(JoinType).map((type) => {
              return { value: type, text: t("l.joinType." + type.valueOf()) };
            })}
          ></RadiosFormField>
        </GridItem>
      </GridContainer>
    </>
  );
};
export default SarServicesFilters;
