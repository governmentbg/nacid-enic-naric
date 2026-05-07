import { ArrayFormField, FormSection, useReloadWatcherReader } from "@duosoftbg/nacid-components";
import { getRudiApplicationSubtypes } from "../../../../../../../../axios/api/services";
import SarServicesFilters from "./sarServices/SarServicesFilters";
import { useEffect, useState } from "react";
import { RudiApplication } from "../../../../../../../../utils/constants";
import useAppSelector from "../../../../../../../../hooks/redux/base/useAppSelector";
import { ReloadWatcherObject, useReportSectionClearOnUnmount } from "@duosoftbg/nacid-backoffice-components";
import { REPORT_FILTERS_DEFINITION } from "../../../../../../../../config/report/filters/definition";
import { REPORT_CONFIG } from "../../../../../../../../config/report/reportConfig";

const ReportApplicationTypeSection = ({ reportGroup }) => {
  const baseField = "applicationType";

  const [showSarFilters, setShowSarFilters] = useState(false);
  const { reloadWatcher } = useReloadWatcherReader(ReloadWatcherObject.Report.clear());

  const { label } = REPORT_FILTERS_DEFINITION[baseField];
  useReportSectionClearOnUnmount(reportGroup, baseField, REPORT_CONFIG);

  useEffect(() => {
    if (reloadWatcher) {
      setShowSarFilters(false);
    }
  }, [reloadWatcher]);

  const thunkStateSarServices = useAppSelector((state) => {
    return state.AppData.sarServices;
  });

  const onAdd = (option) => {
    if (option.id === RudiApplication.rudiSARApplication) {
      setShowSarFilters(false);
    }
  };

  const onSelectRecord = (option) => {
    setShowSarFilters(option?.id === RudiApplication.rudiSARApplication);
  };

  return (
    <FormSection label={label}>
      <ArrayFormField
        fieldName={`${baseField}.applicationTypes`}
        listLabel={"l.selected.applicationTypes"}
        autocompleteLabel={"l.reportFilter.applicationType"}
        autocompleteFn={getRudiApplicationSubtypes}
        onAdd={onAdd}
        onSelect={onSelectRecord}
        reloadObject={ReloadWatcherObject.Report.clear()}
        filterItemsAdditionalInfo={[
          {
            id: RudiApplication.rudiSARApplication,
            infoItems: [
              { fieldName: `${baseField}.sarServices`, delimiter: "/", thunkState: thunkStateSarServices },
              {
                fieldName: `${baseField}.sarServicesJoin`,
                labelPrefix: "l.joinType",
              },
            ],
          },
        ]}
      >
        {showSarFilters && <SarServicesFilters baseField={baseField} thunkStateSarServices={thunkStateSarServices} />}
      </ArrayFormField>
    </FormSection>
  );
};
export default ReportApplicationTypeSection;
