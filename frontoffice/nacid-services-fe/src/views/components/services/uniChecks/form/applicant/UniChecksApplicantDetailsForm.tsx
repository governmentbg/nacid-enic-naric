import { useTranslation } from "react-i18next";
import { BoxSpg, DividerSpg } from "@duosoftbg/nacid-components";
import { FormProvider } from "react-hook-form";
import { toast } from "react-toastify";
import ApplicantFormSection from "../../../common/form/person/ApplicantFormSection";
import RepresentativeFormSection from "../../../common/form/person/RepresentativeFormSection";
import ContactAddressFormSection from "../../../common/form/address/ContactAddressFormSection";
import DeclarationsFormSection from "../../../common/form/applicant/DeclarationsFormSection";
import ResultReceiveFormSection from "../../../common/form/applicant/ResultReceiveFormSection";
import ReceiverAddressFormSection from "../../../common/form/address/ReceiverAddressFormSection";
import { Button, Typography } from "@mui/material";
import React from "react";
import { ApplicantType } from "../../../../../../types/common/personTypes";

const typesWithUni = [ApplicantType.NATURAL_PERSON, ApplicantType.COMPANY, ApplicantType.UNIVERSITY];

const UniChecksApplicantDetailsForm = ({ methods, onSubmit }) => {
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
          <ApplicantFormSection hasType={true} naturalPersonBirthPlaceCitizenshipRequired={true} types={typesWithUni} />
          <RepresentativeFormSection
            naturalPersonBirthPlaceCitizenshipRequired={true}
            capacityRequired={true}
            hasRepresentativeCompany={true}
          />
          <ContactAddressFormSection />
          <DeclarationsFormSection
            agreeDataUsageLabelCode={"l.declaration.agreeDataUsage.uniChecks.input"}
            documentDeclarationLabelCode={"l.declaration.documentsDeclaration.uniChecks"}
          />
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
export default UniChecksApplicantDetailsForm;
