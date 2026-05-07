package bg.duosoft.nacid.backoffice.core.be.service.common;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.PersonUniversityAdditionalDetailsDTO;

public interface PersonUniversityAdditionalDetailsService {
    PersonUniversityAdditionalDetailsDTO selectById(Integer id);

    PersonUniversityAdditionalDetailsDTO save(PersonUniversityAdditionalDetailsDTO personUniversityAdditionalDetailsDTO);

    void delete(Integer id);

    PersonUniversityAdditionalDetailsDTO process(PersonUniversityAdditionalDetailsDTO personUniversityAdditionalDetailsDTO);

}
