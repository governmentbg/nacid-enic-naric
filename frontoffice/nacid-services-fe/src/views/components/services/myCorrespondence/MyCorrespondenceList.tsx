import { useTranslation } from "react-i18next";
import useAppDispatch from "../../../../hooks/redux/base/useAppDispatch";
import useAppSelector from "../../../../hooks/redux/base/useAppSelector";
import React, { useEffect } from "react";
import { filterCorrespondence } from "../../../../store/redux/slice/MyCorrespondence/myCorrespondence";
import { AlertSpg, BoxSpg, GridContainer, GridItem, TableSkeleton, THUNK_STATUS } from "@duosoftbg/nacid-components";
import MyCorrespondenceTable from "./MyCorrespondenceTable";

const MyCorrespondenceList = () => {
  const { t } = useTranslation();
  const dispatch = useAppDispatch();

  const myCorrespondence = useAppSelector((state) => {
    return state.MyCorrespondence;
  });

  const myCorrespondenceFilterForm = useAppSelector((state) => {
    return state.Forms.MyCorrespondenceFilterForm;
  });

  useEffect(() => {
    dispatch(filterCorrespondence(myCorrespondenceFilterForm));
  }, [dispatch, myCorrespondenceFilterForm]);

  return (
    <BoxSpg>
      <GridContainer mt={0}>
        <GridItem sm={12} md={12} pr={0}>
          {(myCorrespondence.list.status === THUNK_STATUS.PENDING ||
            myCorrespondence.list.status === THUNK_STATUS.INITIAL) && <TableSkeleton />}
          {myCorrespondence.list.status === THUNK_STATUS.REJECTED && (
            <AlertSpg severity={"error"}>{t("m.generic.error.service.fail")}</AlertSpg>
          )}
          {myCorrespondence.list.status === THUNK_STATUS.FULFILLED && <MyCorrespondenceTable />}
        </GridItem>
      </GridContainer>
    </BoxSpg>
  );
};
export default MyCorrespondenceList;
