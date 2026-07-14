import useAppSelector from "../../../../hooks/redux/base/useAppSelector";
import { AlertSpg, NacidTable, RelativeBox } from "@duosoftbg/nacid-components";
import React, { useState } from "react";
import { useTranslation } from "react-i18next";
import useAppDispatch from "../../../../hooks/redux/base/useAppDispatch";
import {
  changeOrderBy,
  changePage,
  changePageSize,
} from "../../../../store/redux/slice/Forms/myCorrespondenceFilterForm";
import { filterCorrespondence } from "../../../../store/redux/slice/MyCorrespondence/myCorrespondence";
import CorrespondenceReadDialog from "../common/correspondence/CorrespondenceReadDialog";
import CorrespondenceTableBody from "../common/correspondence/CorrespondenceTableBody";

const MyCorrespondenceTable = () => {
  const { t } = useTranslation();
  const dispatch = useAppDispatch();
  const [correspondenceData, setCorrespondenceData] = useState({
    open: false,
    correspondence: null,
  });

  const myCorrespondence = useAppSelector((state) => {
    return state.MyCorrespondence;
  });

  const myCorrespondenceFilterForm = useAppSelector((state) => {
    return state.Forms.MyCorrespondenceFilterForm;
  });

  const headCells = [
    { id: "dateCreated", label: t("h.correspondence.dateCreated"), sortable: true },
    { id: "tempNumber", label: t("h.correspondence.tempNumber"), sortable: true },
    { id: "about", label: t("h.correspondence.about"), sortable: true },
    { id: "registrationNumber", label: t("h.correspondence.registrationNumber"), sortable: true },
    { id: "dateRead", label: t("h.correspondence.dateRead"), sortable: true },
    { id: "options", label: t("h.options"), sortable: false },
  ];

  if (myCorrespondence.list.data.totalElements === 0) {
    return <AlertSpg severity={"info"}>{t("m.empty.list")}</AlertSpg>;
  }
  return (
    <RelativeBox>
      <NacidTable
        total={myCorrespondence.list.data.totalElements}
        page={myCorrespondenceFilterForm.page}
        pageSize={myCorrespondenceFilterForm.pageSize}
        orderBy={myCorrespondenceFilterForm.orderBy}
        order={myCorrespondenceFilterForm.order}
        onSortClick={(e, prop) => {
          dispatch(changeOrderBy(prop));
        }}
        headCells={headCells}
        onPageChange={(e, page) => {
          dispatch(changePage(page));
        }}
        onRowsPerPageChange={(e) => {
          dispatch(changePageSize(e.target.value));
        }}
      >
        <CorrespondenceTableBody
          correspondence={myCorrespondence.list.data.content}
          setCorrespondenceData={setCorrespondenceData}
          showTempNumber={true}
        />
      </NacidTable>

      <CorrespondenceReadDialog
        correspondence={correspondenceData.correspondence}
        open={correspondenceData.open}
        onCloseDialog={(hasReadChanged) => {
          setCorrespondenceData({ open: false, correspondence: null });
          if (hasReadChanged) {
            dispatch(filterCorrespondence(myCorrespondenceFilterForm));
          }
        }}
      />
    </RelativeBox>
  );
};
export default MyCorrespondenceTable;
