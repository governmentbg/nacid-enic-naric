package bg.duosoft.nacid.backoffice.core.be.repository.nomenclature.custom;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.filter.base.AutocompleteViewFilterDTO;

import java.util.List;

public interface SpecialitySearchRepository {
    List<String> selectSpecialities(AutocompleteViewFilterDTO filter);
}
