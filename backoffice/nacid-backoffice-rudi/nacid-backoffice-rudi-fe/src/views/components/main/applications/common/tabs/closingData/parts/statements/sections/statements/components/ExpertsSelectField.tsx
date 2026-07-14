import React, { useEffect, useState } from "react";
import { AsyncCallArgs, isArrayNotEmpty, isNotEmpty, SelectFormField, useAsyncCall } from "@duosoftbg/nacid-components";
import { useDispatch } from "react-redux";
import { useParams } from "react-router-dom";
import { getApplicationCommissionMembers } from "../../../../../../../../../../../../axios/api/services";
import { useFormContext } from "react-hook-form";

type ExpertsSelectFieldProps = {
  field: string;
  label?: string;
  required?: boolean;
  disabled?: boolean;
  isCreate?: boolean;
};

const ExpertsSelectField = ({
  field,
  label = "l.application.commission.member",
  required = false,
  disabled = false,
  isCreate = undefined,
}: ExpertsSelectFieldProps) => {
  const { id } = useParams();
  const dispatch = useDispatch();
  const { asyncCall } = useAsyncCall();
  const [options, setOptions] = useState(null);
  const { setValue } = useFormContext();

  useEffect(() => {
    const asyncCallArgs: AsyncCallArgs = {
      promise: getApplicationCommissionMembers(id),
      processResponseErrors: false,
      onSuccess: (response) => {
        setOptions(response);
        if (isCreate && isArrayNotEmpty(response) && isNotEmpty(response[0]?.commissionMember?.id)) {
          setValue(field, response[0]?.commissionMember?.id);
        }
      },
      onError: () => {
        setOptions(null);
      },
    };
    asyncCall(asyncCallArgs);

    return () => {
      setOptions(null);
    };
    // eslint-disable-next-line
  }, [asyncCall, dispatch, field, id]);

  if (options) {
    return (
      <SelectFormField
        required={required}
        isDisabled={disabled}
        fieldName={field}
        labelCode={label}
        addEmptyOption={true}
        selectOptions={options.map((option) => {
          return {
            value: option?.commissionMember?.id,
            text: isNotEmpty(option?.commissionMember?.middleName)
              ? `${option?.commissionMember?.firstName} ${option?.commissionMember?.middleName} ${option?.commissionMember?.lastName}`
              : `${option?.commissionMember?.firstName} ${option?.commissionMember?.lastName}`,
          };
        })}
      />
    );
  }

  return null;
};

export default ExpertsSelectField;
