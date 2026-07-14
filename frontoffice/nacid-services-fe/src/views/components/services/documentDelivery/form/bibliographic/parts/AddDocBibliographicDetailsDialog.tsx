import { Button, Dialog, DialogActions, DialogContent, DialogTitle } from "@mui/material";
import { useTranslation } from "react-i18next";
import { FormProvider } from "react-hook-form";
import { useReactHookForm, GridContainer } from "@duosoftbg/nacid-components";
import { DocBibliographicEntryDetails } from "../../../../../../../types/docDeliveryTypes";
import { initialBibliographicEntryDetails } from "../../../../../../../init/docDeliveryInitialValues";
import { createDocBibliographicEntryDetailsValidationSchema } from "../../../../../../../yup/documentDelivery/docDeliveryValidationSchemas";
import DocBibliographicDetailsFormFields from "./DocBibliographicDetailsFormFields";
import FileErrorAlert from "../../../../common/form/document/FileErrorAlert";
import { useEffect } from "react";

const AddDocBibliographicDetailsDialog = ({ open, onCloseDialog, onAddEntry }) => {
  const { t } = useTranslation();

  const { methods } = useReactHookForm<DocBibliographicEntryDetails>({
    defaultValues: initialBibliographicEntryDetails,
    validationSchema: createDocBibliographicEntryDetailsValidationSchema,
  });

  const onSubmitDialog = () => {
    methods.handleSubmit(
      (values) => onAddEntry(values),
      (errors) => {
        console.log(errors);
      }
    )();
  };

  useEffect(() => {
    methods.reset(initialBibliographicEntryDetails);
  }, [open, methods]);

  return (
    <Dialog open={open} onClose={onCloseDialog}>
      <DialogTitle>{t("t.docDelivery.bibliographicDetails.sub")}</DialogTitle>
      <DialogContent>
        <FormProvider {...methods}>
          <GridContainer spacing={4} mt={0}>
            <FileErrorAlert methods={methods} />
            <DocBibliographicDetailsFormFields methods={methods} />
          </GridContainer>
        </FormProvider>
      </DialogContent>
      <DialogActions>
        <Button color="primary" variant="contained" type="submit" onClick={onSubmitDialog}>
          {t("l.btn.save")}
        </Button>
        <Button variant="outlined" onClick={onCloseDialog} sx={{ ml: 3 }}>
          {t("l.btn.cancel")}
        </Button>
      </DialogActions>
    </Dialog>
  );
};
export default AddDocBibliographicDetailsDialog;
