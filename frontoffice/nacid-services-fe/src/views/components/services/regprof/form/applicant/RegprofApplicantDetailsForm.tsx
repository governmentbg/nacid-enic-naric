import { useTranslation } from "react-i18next";
import { BoxSpg, CertificateReceiveForm, DividerSpg } from "@duosoftbg/nacid-components";
import { FormProvider } from "react-hook-form";
import { toast } from "react-toastify";
import ApplicantFormSection from "../../../common/form/person/ApplicantFormSection";
import ContactAddressFormSection from "../../../common/form/address/ContactAddressFormSection";
import DeclarationsFormSection from "../../../common/form/applicant/DeclarationsFormSection";
import ResultReceiveFormSection from "../../../common/form/applicant/ResultReceiveFormSection";
import ReceiverAddressFormSection from "../../../common/form/address/ReceiverAddressFormSection";
import { Button, Typography } from "@mui/material";
import React from "react";
import QualificationNamesFormSection from "./section/QualificationNamesFormSection";
import RepresentativeFormSection from "../../../common/form/person/RepresentativeFormSection";
import CertificateReceiveFormSection from "../../../common/form/applicant/CertificateReceiveFormSection";

const RegprofApplicantDetailsForm = ({ methods, onSubmit }) => {
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
            naturalPersonBirthPlaceCitizenshipRequired={true}
            hasRepresentativeCompany={true}
          />
          <QualificationNamesFormSection />
          <ContactAddressFormSection />
          <DeclarationsFormSection showAgreeDeclarationLink={false} />
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
export default RegprofApplicantDetailsForm;
