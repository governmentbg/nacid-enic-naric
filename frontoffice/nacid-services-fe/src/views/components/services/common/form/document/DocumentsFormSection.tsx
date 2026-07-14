import { Button, Typography } from "@mui/material";
import { FileUpload } from "@mui/icons-material";
import { useState } from "react";
import AddAttachmentDialog from "./AddAttachmentDialog";
import { DividerSpg, GridContainer, GridItem, FormSection, AlertSpg } from "@duosoftbg/nacid-components";
import { useTranslation } from "react-i18next";

const DocumentsFormSection = ({
  docTypes,
  onAddAttachment,
  hasAttachmentForm,
  hasAttachmentType,
  informingMessageCode,
  fileGroup,
}) => {
  const { t } = useTranslation();

  const [open, setOpen] = useState(false);

  const handleAddAttachment = (attachment) => {
    onAddAttachment(attachment);
    setOpen(false);
  };

  return (
    <FormSection label={"t.documents.details"}>
      <GridContainer spacing={4} mt={0}>
        {informingMessageCode && (
          <GridItem sm={12} md={12}>
            <AlertSpg severity={"info"}>{t(informingMessageCode)}</AlertSpg>
          </GridItem>
        )}
        <GridItem sm={12} md={12}>
          <Typography align={"right"}>
            <Button startIcon={<FileUpload />} variant="outlined" onClick={() => setOpen(true)}>
              {t("l.btn.add.attachments")}
            </Button>
          </Typography>
          <AddAttachmentDialog
            docTypes={docTypes}
            open={open}
            onCloseDialog={() => setOpen(false)}
            onAddAttachment={handleAddAttachment}
            hasAttachmentForm={hasAttachmentForm}
            hasAttachmentType={hasAttachmentType}
            fileGroup={fileGroup}
          />
        </GridItem>
      </GridContainer>
      <DividerSpg my={4} />
    </FormSection>
  );
};
export default DocumentsFormSection;
