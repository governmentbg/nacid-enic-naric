import { useTranslation } from "react-i18next";
import { useEffect, useState } from "react";
import { GridContainer, GridItem, TextButton, ViewDialog } from "@duosoftbg/nacid-components";
import { FileDownload, FilterList } from "@mui/icons-material";
import { Checkbox, DialogContentText, FormControl, FormControlLabel, FormGroup, Toolbar } from "@mui/material";
import useAppDispatch from "../../../../../../hooks/redux/base/useAppDispatch";
import useAppTableSelectedColumns from "../../../../../../hooks/redux/application/useAppTableSelectedColumns";
import { ApplicationSelectedTableColumnsActions } from "../../../../../../store/redux/slice/Applications/selectedTableColumns";
import { APPLICATION_TABLE_COLUMNS_DEFINITION } from "../../../../../../config/applications/table/definition";
import { generateApplicationsReport } from "../../../../../../axios/api/services";
import { AttachmentLink, useDownloadReport } from "@duosoftbg/nacid-backoffice-components";

const AppListTableColumnsDialog = ({ group, title }) => {
  const dispatch = useAppDispatch();
  const { t } = useTranslation();
  const [open, setOpen] = useState(false);
  const [columns, setColumns] = useState([]);
  let tableColumns = useAppTableSelectedColumns(group);
  const { handleReportDownload } = useDownloadReport(generateApplicationsReport, `applications_report_${group}`);

  useEffect(() => {
    setColumns(tableColumns);
  }, [tableColumns]);

  const handleChange = (event) => {
    dispatch(
      ApplicationSelectedTableColumnsActions.updateColumnValue({
        group: group,
        name: event.target.name,
        value: event.target.checked,
      }),
    );
  };

  return (
    <>
      <Toolbar variant={"dense"} style={{ background: "rgb(238, 238, 238)" }}>
        <TextButton disableRipple startIcon={<FilterList />} color="primary" onClick={() => setOpen(true)}>
          {t(title)}
        </TextButton>
        <TextButton disableRipple startIcon={<FileDownload />} color="primary" style={{ marginLeft: "auto" }}>
          <AttachmentLink onClick={handleReportDownload} target="_blank" style={{ textDecoration: "none" }}>
            {t("t.export.data")}
          </AttachmentLink>
        </TextButton>
      </Toolbar>

      <ViewDialog open={open} onClose={() => setOpen(false)} title={title}>
        <DialogContentText>{t("t.modal.columns.select.instruction")}</DialogContentText>
        <FormControl fullWidth component="fieldset" variant="standard">
          <FormGroup>
            <GridContainer spacing={1}>
              {columns &&
                columns.map((item) => (
                  <GridItem xs={12} sm={6} md={4} key={item.id}>
                    <FormControlLabel
                      control={<Checkbox checked={item.value} onChange={handleChange} name={item.id} />}
                      label={t(APPLICATION_TABLE_COLUMNS_DEFINITION[item.id].label) as string}
                    />
                  </GridItem>
                ))}
            </GridContainer>
          </FormGroup>
        </FormControl>
      </ViewDialog>
    </>
  );
};
export default AppListTableColumnsDialog;
