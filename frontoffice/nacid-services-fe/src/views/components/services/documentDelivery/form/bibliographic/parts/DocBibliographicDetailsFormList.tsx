import { GridContainer, GridItem, NacidTableSimple } from "@duosoftbg/nacid-components";
import { useTranslation } from "react-i18next";
import { IconButton, TableBody, TableCell, TableRow } from "@mui/material";
import { buildFetchFileUrl } from "../../../../../../../services/coreServicesCalls";
import React, { useEffect } from "react";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faXmark } from "@fortawesome/free-solid-svg-icons";
import { documentDeliveryCopyTypeThunk } from "../../../../../../../store/redux/slice/AppData/documentDeliveryCopyType";
import useAppDispatch from "../../../../../../../hooks/redux/base/useAppDispatch";
import useAppSelector from "../../../../../../../hooks/redux/base/useAppSelector";

const DocBibliographicDetailsFormList = ({ entries, onEntryRemove }) => {
  const { t } = useTranslation();
  const dispatch = useAppDispatch();

  const documentDeliveryCopyType = useAppSelector((state) => {
    return state.AppData.DocumentDeliveryCopyType;
  });

  const headCells = [
    { id: "file", label: t("h.docBibliographicEntry.file"), sortable: false },
    { id: "sources", label: t("h.docBibliographicEntry.sources"), sortable: false },
    { id: "deliveryResultKind", label: t("h.docBibliographicEntry.deliveryResultKind"), sortable: false },
    { id: "options", label: t("h.docBibliographicEntry.options"), sortable: false },
  ];

  useEffect(() => {
    dispatch(documentDeliveryCopyTypeThunk());
  }, [dispatch]);

  return (
    <GridContainer>
      <GridItem sm={12} md={12}>
        <NacidTableSimple headCells={headCells}>
          <TableBody>
            {entries
              .filter((entry) => !entry.forRemoval)
              .map((entry, index) => (
                <TableRow key={entry.file && entry.file.fileId ? entry.file.fileId : index}>
                  <TableCell>
                    {entry.file && entry.file.fileId && (
                      <a
                        href={buildFetchFileUrl(entry.file.rootDirectory, entry.file.relativePath, entry.file.fileId)}
                        target={"_blank"}
                        rel="noreferrer"
                      >
                        {entry.file.fileName}
                      </a>
                    )}
                  </TableCell>
                  <TableCell>
                    {entry.electronicCatalogues && (
                      <>{t("l.docDelivery.bibliographicDetails.electronicCatalogues") + "; "}</>
                    )}
                    {entry.bgLibraries && <>{t("l.docDelivery.bibliographicDetails.bgLibraries") + "; "}</>}
                    {entry.foreignLibraries && <>{t("l.docDelivery.bibliographicDetails.foreignLibraries") + "; "}</>}
                  </TableCell>
                  <TableCell>
                    {entry.deliveryResultKind &&
                      documentDeliveryCopyType.data &&
                      documentDeliveryCopyType.data.find((copy) => copy.id === entry.deliveryResultKind.id)?.name}
                  </TableCell>
                  <TableCell>
                    <IconButton title={t("l.btn.remove")} color={"error"} onClick={(e) => onEntryRemove(index)}>
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
export default DocBibliographicDetailsFormList;
