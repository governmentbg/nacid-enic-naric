import React, { useEffect } from "react";
import { FormSection } from "@duosoftbg/nacid-components";
import useAppSelector from "../../../../../../hooks/redux/base/useAppSelector";
import { receiveResultThunk } from "../../../../../../store/redux/slice/AppData/receiveResult";
import { ResultReceiveFormFields } from "@duosoftbg/nacid-frontoffice-components";
import { useFormContext, useWatch } from "react-hook-form";
import useAppDispatch from "../../../../../../hooks/redux/base/useAppDispatch";

const ResultReceiveFormSection = ({ titleCode = "t.resultReceive.way", baseField, certificateReceiveFormId }) => {
  const { setValue } = useFormContext();
  const dispatch = useAppDispatch();

  const resultReceiveId = useWatch({ name: `${baseField}.resultReceive.id` });
  const certificateReceiveForms = useWatch({ name: `certificateReceiveForms` });

  const thunkState = useAppSelector((state) => {
    return state.AppData.ReceiveResult;
  });

  useEffect(() => {
    dispatch(receiveResultThunk());
  }, [dispatch]);

  useEffect(() => {
    if (
      certificateReceiveFormId == null &&
      (!resultReceiveId || resultReceiveId === "") &&
      thunkState.data &&
      thunkState.data.length > 0
    ) {
      const defaults = thunkState.data.filter((r) => r.defaultValue === true);
      if (defaults.length > 0) {
        setValue(`${baseField}.resultReceive`, defaults[0]);
      }
    }
  }, [baseField, resultReceiveId, setValue, thunkState.data, certificateReceiveFormId]);

  if (
    certificateReceiveFormId != null &&
    certificateReceiveForms.filter((form) => form === certificateReceiveFormId).length === 0
  ) {
    return null;
  }
  return (
    <FormSection label={titleCode}>
      <ResultReceiveFormFields
        field={`${baseField}.resultReceive`}
        thunkState={thunkState}
        thunkFn={receiveResultThunk}
        filterFn={(method) =>
          certificateReceiveFormId == null || certificateReceiveFormId === method.certificateReceiveFormCode
        }
      />
    </FormSection>
  );
};
export default ResultReceiveFormSection;
