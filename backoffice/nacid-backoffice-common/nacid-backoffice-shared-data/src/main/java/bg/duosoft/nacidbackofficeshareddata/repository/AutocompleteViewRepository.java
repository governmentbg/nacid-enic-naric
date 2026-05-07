package bg.duosoft.nacidbackofficeshareddata.repository;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.filter.base.AutocompleteViewFilterDTO;

import java.util.List;

public interface AutocompleteViewRepository {
    List<String> selectRecords(AutocompleteViewFilterDTO filter);
}
