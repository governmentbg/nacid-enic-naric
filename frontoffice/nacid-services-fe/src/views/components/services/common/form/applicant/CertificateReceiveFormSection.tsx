import { CheckboxListFormField, FormSection, GridContainer, GridItem } from "@duosoftbg/nacid-components";
import React, { useEffect } from "react";
import { useWatch } from "react-hook-form";
import useAppDispatch from "../../../../../../hooks/redux/base/useAppDispatch";
import useAppSelector from "../../../../../../hooks/redux/base/useAppSelector";
import { certificateReceiveFormThunk } from "../../../../../../store/redux/slice/AppData/certificateReceiveForm";

const CertificateReceiveFormSection = () => {
  const dispatch = useAppDispatch();

  const thunkStateCertificateReceiveForm = useAppSelector((state) => {
    return state.AppData.CertificateReceiveForm;
  });

  useWatch({ name: "certificateReceiveForm.id" });

  useEffect(() => {
    dispatch(certificateReceiveFormThunk());
  }, [dispatch]);

  return (
    <FormSection label={"t.certificateReceiveForm.details"}>
      <GridContainer>
        <GridItem xs={12} sm={12} md={12}>
          <CheckboxListFormField
            row={true}
            required={true}
            fieldName={"certificateReceiveForms"}
            labelCode={"l.certificateReceiveForm"}
            checkboxOptions={thunkStateCertificateReceiveForm.data.map((option) => {
              return { value: option.id, text: option.name, active: option.isActive };
            })}
          />
        </GridItem>
      </GridContainer>
    </FormSection>
  );
};
export default CertificateReceiveFormSection;
