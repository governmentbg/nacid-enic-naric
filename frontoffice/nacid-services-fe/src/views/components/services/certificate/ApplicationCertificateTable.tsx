import { useTranslation } from "react-i18next";
import { NacidTableSimple } from "@duosoftbg/nacid-components";
import { TableBody, TableCell, TableRow } from "@mui/material";
import DmsFileDownload from "../common/dms/DmsFileDownload";

const ApplicationCertificateTable = ({ certificate }) => {
  const { t } = useTranslation();

  const headCells = [
    { id: "registrationNumber", label: t("h.certificate.registrationNumber"), sortable: false },
    { id: "files", label: t("h.certificate.files"), sortable: false },
  ];

  if (!certificate) {
    return null;
  }
  return (
    <>
      <NacidTableSimple headCells={headCells}>
        <TableBody>
          <TableRow key={certificate.applicationAttachedDocId}>
            <TableCell>{certificate.certificateNumber}</TableCell>
            <TableCell>
              {certificate.files.map((file) => (
                <DmsFileDownload file={file} key={file.id} />
              ))}
            </TableCell>
          </TableRow>
        </TableBody>
      </NacidTableSimple>
    </>
  );
};
export default ApplicationCertificateTable;
