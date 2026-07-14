import useAppDispatch from "../../../../../../../hooks/redux/base/useAppDispatch";
import { useEffect, useState } from "react";
import { ReportSelectedTableColumnsActions } from "../../../../../../../store/redux/slice/Reports/selectedTableColumns";
import useReportTableSelectedColumns from "../../../../../../../hooks/redux/report/useReportTableSelectedColumns";
import { REPORT_TABLE_COLUMNS_DEFINITION } from "../../../../../../../config/report/table/definition";
import { ReportTableColumnsDialogContent, useDownloadReport } from "@duosoftbg/nacid-backoffice-components";
import { REPORT_GROUP } from "../../../../../../../config/report/reportConfig";
import { generateCommonReport } from "../../../../../../../axios/api/services";

const ReportTableColumnsDialog = ({ group, title = "t.modal.selectTableColumns" }) => {
  const dispatch = useAppDispatch();
  const [columns, setColumns] = useState([]);
  let tableColumns = useReportTableSelectedColumns(group);

  const { handleReportDownload } = useDownloadReport(generateCommonReport, `common_report`);

  useEffect(() => {
    setColumns(tableColumns);
  }, [tableColumns]);

  const handleChange = (event) => {
    dispatch(
      ReportSelectedTableColumnsActions.updateColumnValue({
        group: group,
        name: event.target.name,
        value: event.target.checked,
      }),
    );
  };

  return (
    <ReportTableColumnsDialogContent
      title={title}
      columns={columns}
      handleChange={handleChange}
      reportColumnsDefinition={REPORT_TABLE_COLUMNS_DEFINITION}
      handleReportDownload={group === REPORT_GROUP.COMMON_REPORT ? handleReportDownload : undefined}
    />
  );
};
export default ReportTableColumnsDialog;
