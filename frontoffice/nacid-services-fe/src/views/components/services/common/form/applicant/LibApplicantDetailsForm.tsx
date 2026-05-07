import { BoxSpg, DividerSpg } from "@duosoftbg/nacid-components";
import { FormProvider } from "react-hook-form";
import { toast } from "react-toastify";
import ApplicantFormSection from "../../../common/form/person/ApplicantFormSection";
import RepresentativeFormSection from "../../../common/form/person/RepresentativeFormSection";
import ResultReceiveFormSection from "./ResultReceiveFormSection";
import ReceiverAddressFormSection from "../../../common/form/address/ReceiverAddressFormSection";
import { Button, Typography } from "@mui/material";
import React from "react";
import { useTranslation } from "react-i18next";
import { ApplicantType } from "../../../../../../types/common/personTypes";
import LibContactAddressFormSection from "../address/LibContactAddressFormSection";

const typesWithoutUni = [ApplicantType.NATURAL_PERSON, ApplicantType.COMPANY];

const LibApplicantDetailsForm = ({ methods, onSubmit, hasApplicantTitleFields = false }) => {
  const { t } = useTranslation();

  return (
    <BoxSpg>
      <FormProvider {...methods}>
        <form
          onSubmit={methods.handleSubmit(onSubmit, (errors) => {
            console.log(errors);
            toast.error(t("m.validation.errors.present"));
          })}
        >
          <ApplicantFormSection
            hasType={true}
            naturalPersonBirthPlaceCitizenshipRequired={false}
            types={typesWithoutUni}
            hasApplicantTitleFields={hasApplicantTitleFields}
          />
          <RepresentativeFormSection
            naturalPersonBirthPlaceCitizenshipRequired={false}
            capacityRequired={false}
            hasRepresentativeCompany={false}
          />
          <LibContactAddressFormSection />
          <ResultReceiveFormSection baseField={"resultReceive"} certificateReceiveFormId={null} />
          <ReceiverAddressFormSection baseField={"resultReceive"} />
          <DividerSpg my={4} />
          <BoxSpg>
            <Typography align={"left"}>
              <Button type={"submit"} variant={"contained"}>
                {t("l.btn.saveData")}
              </Button>
            </Typography>
          </BoxSpg>
        </form>
      </FormProvider>
    </BoxSpg>
  );
};
export default LibApplicantDetailsForm;
