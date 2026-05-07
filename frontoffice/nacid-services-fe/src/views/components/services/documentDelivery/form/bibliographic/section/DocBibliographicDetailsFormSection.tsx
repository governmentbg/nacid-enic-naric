import {
  FormSection,
  GridItem,
  GridContainer,
  DividerSpg,
  AlertSpg,
  shouldShowFieldError,
  getFieldError,
  BoxSpg,
} from "@duosoftbg/nacid-components";
import { Button, Typography } from "@mui/material";
import { Add } from "@mui/icons-material";
import { useTranslation } from "react-i18next";
import { useState } from "react";
import AddDocBibliographicDetailsDialog from "../parts/AddDocBibliographicDetailsDialog";
import useAppDispatch from "../../../../../../../hooks/redux/base/useAppDispatch";
import { setStepBibliographicEdited } from "../../../../../../../store/redux/slice/Forms/docDeliveryForm";
import DocBibliographicDetailsFormList from "../parts/DocBibliographicDetailsFormList";

const DocBibliographicDetailsFormSection = ({ methods }) => {
  const [open, setOpen] = useState(false);
  const { t } = useTranslation();
  const dispatch = useAppDispatch();

  const entries = methods.watch("entries", []);

  const handleAddEntry = (entry) => {
    const newArray = [...methods.getValues().entries, entry];
    methods.setValue("entries", newArray, { shouldDirty: true });
    setOpen(false);
  };

  const handleRemoveEntry = (index) => {
    methods.setValue(`entries.${index}.forRemoval`, true, { shouldDirty: true });
    dispatch(setStepBibliographicEdited(true));
  };

  return (
    <FormSection label={"t.docDelivery.bibliographicDetails"}>
      <GridContainer spacing={4} mt={0}>
        {shouldShowFieldError("entries", methods.formState, methods.getFieldState) && (
          <GridItem sm={12} md={12}>
            <AlertSpg severity={"error"}>
              <BoxSpg>{getFieldError("entries", methods.getFieldState)}</BoxSpg>
            </AlertSpg>
          </GridItem>
        )}
        <GridItem sm={12} md={12}>
          <AlertSpg severity={"info"}>{t("m.documentDelivery.bibliographicDetails.info")}</AlertSpg>
        </GridItem>
        <GridItem sm={12} md={12}>
          <Typography align={"right"}>
            <Button startIcon={<Add />} variant="outlined" onClick={() => setOpen(true)}>
              {t("l.btn.bibliographicDetails.add")}
            </Button>
          </Typography>

          <AddDocBibliographicDetailsDialog
            open={open}
            onCloseDialog={() => setOpen(false)}
            onAddEntry={handleAddEntry}
          />
        </GridItem>
      </GridContainer>
      <DividerSpg my={4} />
      <DocBibliographicDetailsFormList entries={entries} onEntryRemove={(index) => handleRemoveEntry(index)} />
    </FormSection>
  );
};
export default DocBibliographicDetailsFormSection;
