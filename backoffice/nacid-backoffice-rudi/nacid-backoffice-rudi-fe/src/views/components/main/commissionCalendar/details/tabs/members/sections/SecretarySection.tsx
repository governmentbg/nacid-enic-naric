import React from "react";

import { useEffectSkipFirstRender, useReactHookForm } from "@duosoftbg/nacid-components";
import { createCommissionCalendarSecretaryValidationSchema } from "../../../../../../../../yup/schema/commissionCalendar/commissionCalendarSecretaryValidationSchema";
import { SecretaryDataDetails } from "../../../../../../../../types/commissionCalendar/commissionCalendarTypes";
import { FormProvider, useWatch } from "react-hook-form";
import { ResponsibleUserAutocomplete } from "@duosoftbg/nacid-backoffice-components";

const SecretarySection = ({ secretary, changeSecretary = null, isViewMode = false }) => {
  const { methods } = useReactHookForm<SecretaryDataDetails>({
    defaultValues: { responsibleUser: secretary },
    validationSchema: createCommissionCalendarSecretaryValidationSchema,
  });

  return (
    <FormProvider {...methods}>
      <form onSubmit={() => {}}>
        <ResponsibleUserAutocomplete disabled={isViewMode} label={"l.secretary"} />
        <ResponsibleUserWatchSection changeSecretary={changeSecretary} />
      </form>
    </FormProvider>
  );
};

const ResponsibleUserWatchSection = ({ changeSecretary }) => {
  const responsibleUser = useWatch({ name: "responsibleUser" });

  useEffectSkipFirstRender(() => {
    changeSecretary(responsibleUser);
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [responsibleUser]);
  return null;
};

export default SecretarySection;
