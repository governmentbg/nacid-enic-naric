import { NacidTableSimple, GridItem, GridContainer, SelectFormField } from "@duosoftbg/nacid-components";
import { IconButton, TableBody, TableCell, TableRow } from "@mui/material";
import { useTranslation } from "react-i18next";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faXmark } from "@fortawesome/free-solid-svg-icons";
import React, { useEffect } from "react";
import { buildFetchFileUrl } from "../../../../../../services/coreServicesCalls";
import useAppSelector from "../../../../../../hooks/redux/base/useAppSelector";
import { copyTypeThunk } from "../../../../../../store/redux/slice/AppData/copyType";
import useAppDispatch from "../../../../../../hooks/redux/base/useAppDispatch";

const AttachmentsFormList = ({ attachments, onAttachmentRemove, hasAttachmentForm, hasAttachmentType, docTypes }) => {
  const { t } = useTranslation();
  const dispatch = useAppDispatch();

  const headCells = [{ id: "fileName", label: t("h.attachment.fileName"), sortable: false }];

  if (hasAttachmentType) {
    headCells.push({ id: "attachmentType", label: t("h.attachment.attachmentType"), sortable: false });
  }
  if (hasAttachmentForm) {
    headCells.push({ id: "attachmentForm", label: t("h.attachment.attachmentForm"), sortable: false });
  }
  headCells.push({ id: "description", label: t("h.attachment.description"), sortable: false });
  headCells.push({ id: "options", label: t("h.options"), sortable: false });

  const thunkStateCopyType = useAppSelector((state) => {
    return state.AppData.CopyType;
  });

  useEffect(() => {
    dispatch(copyTypeThunk());
  }, [dispatch]);

  return (
    <GridContainer>
      <GridItem sm={12} md={12}>
        <NacidTableSimple headCells={headCells}>
          <TableBody>
            {attachments
              .filter((att) => !att.forRemoval)
              .map((att, index) => (
                <TableRow key={att.file.fileId}>
                  <TableCell>
                    <a
                      href={buildFetchFileUrl(att.file.rootDirectory, att.file.relativePath, att.file.fileId)}
                      target={"_blank"}
                      rel="noreferrer"
                    >
                      {att.file.fileName}
                    </a>
                  </TableCell>
                  {hasAttachmentType && (
                    <TableCell
                      sx={{ color: docTypes.filter((d) => d.id === att.attachmentType?.id).length > 0 ? null : "red" }}
                    >
                      {att.attachmentType && att.attachmentType?.name}
                    </TableCell>
                  )}
                  {hasAttachmentForm && (
                    <TableCell>
                      <SelectFormField
                        key={index}
                        fieldName={`attachments.${index}.attachmentForm.id`}
                        addEmptyOption={true}
                        selectOptions={thunkStateCopyType.data.map((option) => {
                          return { value: option.id, text: option.name, active: option.isActive };
                        })}
                      />
                    </TableCell>
                  )}
                  <TableCell>{att.description}</TableCell>
                  <TableCell>
                    <IconButton title={t("l.btn.remove")} color={"error"} onClick={(e) => onAttachmentRemove(index)}>
                      <FontAwesomeIcon style={{ fontSize: 20 }} icon={faXmark} />
                    </IconButton>
                  </TableCell>
                </TableRow>
              ))}
          </TableBody>
        </NacidTableSimple>
      </GridItem>
    </GridContainer>
  );
};
export default AttachmentsFormList;
