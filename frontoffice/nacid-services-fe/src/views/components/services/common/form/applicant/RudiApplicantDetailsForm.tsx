import { FormProvider } from "react-hook-form";
import { toast } from "react-toastify";
import ApplicantFormSection from "../person/ApplicantFormSection";
import ContactAddressFormSection from "../address/ContactAddressFormSection";
import ReceiverAddressFormSection from "../address/ReceiverAddressFormSection";
import { BoxSpg, CertificateReceiveForm, DividerSpg } from "@duosoftbg/nacid-components";
import { Button, Typography } from "@mui/material";
import React from "react";
import { useTranslation } from "react-i18next";
import DeclarationsFormSection from "./DeclarationsFormSection";
import ResultReceiveFormSection from "./ResultReceiveFormSection";
import DiplomaNamesFormSection from "./DiplomaNamesFormSection";
import RepresentativeFormSection from "../person/RepresentativeFormSection";
import CertificateReceiveFormSection from "./CertificateReceiveFormSection";

const RudiApplicantDetailsForm = ({
  methods,
  onSubmit,
  hasRepresentativeCompany = false,
  representativeCapacityRequired = false,
}) => {
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
          <ApplicantFormSection hasType={false} naturalPersonBirthPlaceCitizenshipRequired={true} />
          <RepresentativeFormSection
            hasRepresentativeCompany={hasRepresentativeCompany}
            capacityRequired={representativeCapacityRequired}
            naturalPersonBirthPlaceCitizenshipRequired={true}
          />
          <DiplomaNamesFormSection />
          <ContactAddressFormSection />
          <DeclarationsFormSection />
          <CertificateReceiveFormSection />
          <ResultReceiveFormSection
            titleCode={"t.resultReceive.admin.act.way.electronic"}
            baseField={"resultReceiveElectronic"}
            certificateReceiveFormId={CertificateReceiveForm.ELECTRONIC}
          />
          <ResultReceiveFormSection
            titleCode={"t.resultReceive.admin.act.way.paper"}
            baseField={"resultReceivePaper"}
            certificateReceiveFormId={CertificateReceiveForm.PAPER}
          />
          <ReceiverAddressFormSection baseField={"resultReceivePaper"} />
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
export default RudiApplicantDetailsForm;
