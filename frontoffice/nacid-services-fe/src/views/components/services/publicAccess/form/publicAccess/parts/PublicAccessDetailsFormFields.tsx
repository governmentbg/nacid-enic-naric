import {
  CheckboxListFormField,
  GridContainer,
  GridItem,
  ReferenceDataDomain,
  TextareaFormField,
} from "@duosoftbg/nacid-components";
import React, { useEffect } from "react";
import useAppSelector from "../../../../../../../hooks/redux/base/useAppSelector";
import useAppDispatch from "../../../../../../../hooks/redux/base/useAppDispatch";
import { publicAccessInfoFormThunk } from "../../../../../../../store/redux/slice/AppData/publicAccessInfoForm";

const PublicAccessDetailsFormFields = () => {
  const dispatch = useAppDispatch();

  const infoFormThunkState = useAppSelector((state) => {
    return state.AppData.PublicAccessInfoForm;
  });

  useEffect(() => {
    dispatch(publicAccessInfoFormThunk());
  }, [dispatch]);

  return (
    <>
      <GridContainer spacing={4}>
        <GridItem sm={12} md={12}>
          <TextareaFormField required={true} rows={5} fieldName={"about"} labelCode={"l.publicAccess.about"} />
        </GridItem>
        <GridItem sm={12} md={12}>
          <CheckboxListFormField
            row={true}
            required={false}
            fieldName={"infoForms"}
            labelCode={"l.publicAccess.infoForms"}
            checkboxOptions={infoFormThunkState.data.map((infoForm) => {
              return {
                value: infoForm.id,
                text: infoForm.name,
                active: infoForm.isActive,
              };
            })}
            valuesAreEqual={(checkboxVal, arrayVal) => checkboxVal === arrayVal.id}
            selectValueTransform={(selected) => {
              return { id: selected, name: "", domain: ReferenceDataDomain.PUBLIC_ACCESS_INFO_FORM };
            }}
          />
        </GridItem>
        <GridItem sm={12} md={12}>
          <TextareaFormField required={false} rows={5} fieldName={"comment"} labelCode={"l.publicAccess.comment"} />
        </GridItem>
      </GridContainer>
    </>
  );
};
export default PublicAccessDetailsFormFields;
