import { useTranslation } from "react-i18next";
import useAppSelector from "../../../../../../hooks/redux/base/useAppSelector";
import { FormProvider } from "react-hook-form";
import { toast } from "react-toastify";
import { ApplicationSubtype, BoxSpg, DividerSpg } from "@duosoftbg/nacid-components";
import { Button, Typography } from "@mui/material";
import { createOfficialNotesDetailsValidationSchema } from "../../../../../../yup/officialNotes/officialNotesValidationSchemas";
import { completeNoteStep, setStepNoteEdited } from "../../../../../../store/redux/slice/Forms/officialNotesForm";
import NoteDetailsFormSection from "./section/NoteDetailsFormSection";
import useFormReset from "../../../../../../hooks/useFormReset";
import { baseEndpointPaths } from "../../../../../../services/serviceCalls";
import { initialOfficialNotesDetails } from "../../../../../../init/officialNotesInitialValues";
import useStepSpecific from "../../../../../../hooks/useStepSpecific";
import OfficialNotesFeesSection from "./section/OfficialNoresFeesSection";
import NoteApplicationDetailsFormSection from "./section/NoteApplicationDetailsFormSection";

const OfficialNotesStepNote = () => {
  const { t } = useTranslation();

  const officialNotesForm = useAppSelector((state) => {
    return state.Forms.OfficialNotesForm;
  });

  const { onSubmit, methods } = useStepSpecific(
    officialNotesForm.officialNotesDetails,
    officialNotesForm.id,
    baseEndpointPaths.officialNotes,
    createOfficialNotesDetailsValidationSchema,
    setStepNoteEdited,
    completeNoteStep,
    initialOfficialNotesDetails
  );

  useFormReset(ApplicationSubtype.OFFICIAL_NOTE, officialNotesForm.officialNotesDetails, methods.reset);

  return (
    <BoxSpg>
      <FormProvider {...methods}>
        <form
          onSubmit={methods.handleSubmit(onSubmit, (errors) => {
            toast.error(t("m.validation.errors.present"));
            console.log(errors);
          })}
        >
          <OfficialNotesFeesSection />
          <NoteApplicationDetailsFormSection />
          <NoteDetailsFormSection />
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

export default OfficialNotesStepNote;
