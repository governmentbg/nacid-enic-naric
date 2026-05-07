import { AlertSpg, BoxSpg, GridContainer, GridItem, TableSkeleton, THUNK_STATUS } from "@duosoftbg/nacid-components";
import React, { useEffect } from "react";
import MyApplicationsTable from "./MyApplicationsTable";
import { useTranslation } from "react-i18next";
import useAppSelector from "../../../../hooks/redux/base/useAppSelector";
import { filterApplications } from "../../../../store/redux/slice/MyApplications/myApplications";
import useAppDispatch from "../../../../hooks/redux/base/useAppDispatch";

const MyApplicationsList = () => {
  const { t } = useTranslation();
  const dispatch = useAppDispatch();

  const myApps = useAppSelector((state) => {
    return state.MyApplications;
  });

  const myAppsFilterForm = useAppSelector((state) => {
    return state.Forms.MyApplicationsFilterForm;
  });

  useEffect(() => {
    dispatch(filterApplications(myAppsFilterForm));
  }, [dispatch, myAppsFilterForm]);

  return (
    <BoxSpg>
      <GridContainer mt={0}>
        <GridItem sm={12} md={12} pr={0}>
          {(myApps.list.status === THUNK_STATUS.PENDING || myApps.list.status === THUNK_STATUS.INITIAL) && (
            <TableSkeleton />
          )}
          {myApps.list.status === THUNK_STATUS.REJECTED && (
            <AlertSpg severity={"error"}>{t("m.generic.error.service.fail")}</AlertSpg>
          )}
          {myApps.list.status === THUNK_STATUS.FULFILLED && <MyApplicationsTable />}
        </GridItem>
      </GridContainer>
    </BoxSpg>
  );
};
export default MyApplicationsList;
